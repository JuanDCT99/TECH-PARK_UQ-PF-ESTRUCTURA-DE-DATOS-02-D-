import { useState, useEffect } from 'react'
import './App.css'
import bienvenidosImg from './assets/Bienvenidos.png'
import rolImg from './assets/Rol.png'
import MapaParque from './components/MapaParque'

function App() {
  const [view, setView] = useState('welcome') // 'welcome', 'role-selection', 'dashboard'
  const [selectedRole, setSelectedRole] = useState(null)
  const [atracciones, setAtracciones] = useState([])
  const [senderos, setSenderos] = useState([])
  const [estadoParque, setEstadoParque] = useState('CARGANDO...')
  const [mensajeCarga, setMensajeCarga] = useState('')
  const [rutaOptima, setRutaOptima] = useState(null)
  const [origenRuta, setOrigenRuta] = useState('')
  const [destinoRuta, setDestinoRuta] = useState('')

  const fetchDatosBase = () => {
    fetch('http://localhost:8080/api/parque/atracciones')
      .then(res => res.json())
      .then(data => setAtracciones(data))
      .catch(err => console.error("Error cargando atracciones:", err))

    fetch('http://localhost:8080/api/parque/estado')
      .then(res => res.text())
      .then(data => setEstadoParque(data))
      .catch(err => console.error("Error cargando estado:", err))

    // Nota: Necesitaríamos un endpoint para senderos, pero por ahora los definimos manual 
    // o podemos crear el endpoint en el backend. Vamos a asumir que los senderos vienen de un JSON local o endpoint.
    fetch('http://localhost:8080/api/parque/zonas') // Como ejemplo, pero lo ideal es un endpoint de senderos
      .then(() => {
        // Hardcoded por ahora para el frontend si no hay endpoint
        setSenderos([
          { origen: "A1", destino: "A2", peso: 50 },
          { origen: "A2", destino: "A3", peso: 30 },
          { origen: "A1", destino: "A3", peso: 70 }
        ]);
      });
  }

  const cargarDatosPrueba = () => {
    setMensajeCarga('Cargando...');
    fetch('http://localhost:8080/api/parque/cargar-datos', { method: 'POST' })
      .then(res => res.text())
      .then(data => {
        setMensajeCarga('✅ ' + data);
        fetchDatosBase();
      })
      .catch(err => setMensajeCarga('❌ Error: ' + err));
  };

  const calcularRuta = () => {
    if (!origenRuta || !destinoRuta) return;
    fetch(`http://localhost:8080/api/parque/ruta?origen=${origenRuta}&destino=${destinoRuta}`)
      .then(res => res.json())
      .then(data => setRutaOptima(data))
      .catch(err => alert("No se pudo calcular la ruta: " + err));
  }

  const unirseAFila = (atraccionId, tipo) => {
    // Para el demo usamos un visitanteId fijo "V-1"
    fetch(`http://localhost:8080/api/parque/unirse-fila?visitanteId=V1&atraccionId=${atraccionId}&tipoTiquete=${tipo}`, {
      method: 'POST'
    })
      .then(res => res.text())
      .then(data => alert(data))
      .catch(err => alert("Error: " + err));
  }

  useEffect(() => {
    if (view === 'dashboard') {
      fetchDatosBase();
    }
  }, [view])

  const handleStart = () => {
    setView('role-selection')
  }

  const handleRoleSelect = (role) => {
    setSelectedRole(role)
    setView('dashboard')
  }

  if (view === 'welcome') {
    return (
      <div className="welcome-container" style={{ backgroundImage: `url(${bienvenidosImg})` }}>
        <div className="overlay">
          <div className="content">
            <h1 className="title">TECH-PARK UQ</h1>
            <p className="subtitle">Donde la tecnología y la diversión se encuentran</p>
            <button className="btn-primary" onClick={handleStart}>
              Ingresar al Parque
            </button>
          </div>
        </div>
      </div>
    )
  }

  if (view === 'role-selection') {
    return (
      <div className="role-container" style={{ backgroundImage: `url(${rolImg})` }}>
        <div className="overlay">
          <div className="content">
            <h2 className="title">Selecciona tu Rol</h2>
            <div className="role-grid">
              <button className="role-card" onClick={() => handleRoleSelect('Visitante')}>
                <span className="role-icon">🎟️</span>
                <h3>Visitante</h3>
                <p>Calcula rutas y únete a filas</p>
              </button>
              <button className="role-card" onClick={() => handleRoleSelect('Empleado')}>
                <span className="role-icon">🛠️</span>
                <h3>Empleado</h3>
                <p>Gestiona atracciones</p>
              </button>
              <button className="role-card" onClick={() => handleRoleSelect('Administrador')}>
                <span className="role-icon">🔐</span>
                <h3>Administrador</h3>
                <p>Alertas y estadísticas</p>
              </button>
            </div>
            <button className="btn-secondary" onClick={() => setView('welcome')}>
              Volver
            </button>
          </div>
        </div>
      </div>
    )
  }

  return (
    <div className="dashboard-container">
      <nav className="navbar">
        <h2>TECH-PARK | {selectedRole}</h2>
        <button className="btn-logout" onClick={() => setView('role-selection')}>Cerrar Sesión</button>
      </nav>
      
      <main className="main-content">
        <header className="dashboard-header">
          <div>
            <h1>{selectedRole === 'Visitante' ? 'Explora el Parque' : 'Panel de Gestión'}</h1>
            <p>Estado global: <strong>{estadoParque}</strong></p>
          </div>
          <button className="btn-primary" onClick={cargarDatosPrueba}>
            🔄 Sincronizar Datos
          </button>
        </header>

        <div className="dashboard-grid">
          {/* SECCIÓN MAPA (Fase 4) */}
          <section className="map-section card">
            <h3>🗺️ Mapa del Parque (Grafo)</h3>
            <MapaParque 
              atracciones={atracciones} 
              senderos={senderos} 
              rutaResaltada={rutaOptima}
              onNodoClick={(atr) => setDestinoRuta(atr.id)}
            />
            {selectedRole === 'Visitante' && (
              <div className="route-controls">
                <input 
                  placeholder="ID Origen (ej: A1)" 
                  value={origenRuta} 
                  onChange={(e) => setOrigenRuta(e.target.value.toUpperCase())}
                />
                <input 
                  placeholder="ID Destino (ej: A3)" 
                  value={destinoRuta} 
                  onChange={(e) => setDestinoRuta(e.target.value.toUpperCase())}
                />
                <button className="btn-action" onClick={calcularRuta}>Calcular Dijkstra</button>
              </div>
            )}
          </section>

          {/* SECCIÓN LISTA / ACCIONES */}
          <section className="list-section card">
            <h3>🎢 Catálogo de Atracciones</h3>
            <div className="table-wrapper">
              <table className="data-table">
                <thead>
                  <tr>
                    <th>Atracción</th>
                    <th>Estado</th>
                    <th>Acciones</th>
                  </tr>
                </thead>
                <tbody>
                  {atracciones.map(atr => (
                    <tr key={atr.id}>
                      <td>
                        <strong>{atr.nombre}</strong> <br/>
                        <small>{atr.tipo} | ID: {atr.id}</small>
                      </td>
                      <td>
                        <span className={`badge estado-${atr.estado.toLowerCase()}`}>
                          {atr.estado}
                        </span>
                      </td>
                      <td>
                        {selectedRole === 'Visitante' && (
                          <div className="action-buttons">
                            <button className="btn-mini fast" onClick={() => unirseAFila(atr.id, 'FAST_PASS')}>FastPass</button>
                            <button className="btn-mini" onClick={() => unirseAFila(atr.id, 'GENERAL')}>Fila</button>
                          </div>
                        )}
                        {selectedRole === 'Empleado' && (
                          <button className="btn-mini">Mantenimiento</button>
                        )}
                      </td>
                    </tr>
                  ))}
                </tbody>
              </table>
            </div>
          </section>
        </div>
      </main>
    </div>
  )
}

export default App

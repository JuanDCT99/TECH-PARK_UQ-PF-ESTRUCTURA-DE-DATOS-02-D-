import { useState, useEffect } from 'react'
import './App.css'
import bienvenidosImg from './assets/Bienvenidos.png'
import rolImg from './assets/Rol.png'

function App() {
  const [view, setView] = useState('welcome') // 'welcome', 'role-selection', 'dashboard'
  const [selectedRole, setSelectedRole] = useState(null)
  const [atracciones, setAtracciones] = useState([])
  const [estadoParque, setEstadoParque] = useState('CARGANDO...')
  const [mensajeCarga, setMensajeCarga] = useState('')

  const cargarDatosPrueba = () => {
    setMensajeCarga('Cargando...');
    fetch('http://localhost:8080/api/parque/cargar-datos', { method: 'POST' })
      .then(res => res.text())
      .then(data => {
        setMensajeCarga('✅ ' + data);
        // Recargar datos en el dashboard
        fetch('http://localhost:8080/api/parque/atracciones')
          .then(res => res.json())
          .then(data => setAtracciones(data));
        fetch('http://localhost:8080/api/parque/estado')
          .then(res => res.text())
          .then(data => setEstadoParque(data));
      })
      .catch(err => setMensajeCarga('❌ Error: ' + err));
  };

  useEffect(() => {
    if (view === 'dashboard') {
      fetch('http://localhost:8080/api/parque/atracciones')
        .then(res => res.json())
        .then(data => setAtracciones(data))
        .catch(err => console.error("Error cargando atracciones:", err))

      fetch('http://localhost:8080/api/parque/estado')
        .then(res => res.text())
        .then(data => setEstadoParque(data))
        .catch(err => console.error("Error cargando estado:", err))
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
                <p>Compra entradas y disfruta de las atracciones</p>
              </button>
              <button className="role-card" onClick={() => handleRoleSelect('Empleado')}>
                <span className="role-icon">🛠️</span>
                <h3>Empleado</h3>
                <p>Gestiona atracciones y turnos</p>
              </button>
              <button className="role-card" onClick={() => handleRoleSelect('Administrador')}>
                <span className="role-icon">🔐</span>
                <h3>Administrador</h3>
                <p>Control total del sistema y estadísticas</p>
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
            <h1>Panel de Control</h1>
            <p>Bienvenido, gestiona tu experiencia en el parque desde aquí.</p>
          </div>
          <div className={`status-badge ${estadoParque.toLowerCase()}`}>
            ● Parque {estadoParque}
          </div>
        </header>

        <section className="data-section">
          <div className="section-header">
            <h3>Catálogo de Atracciones</h3>
            <button className="btn-primary" onClick={cargarDatosPrueba} style={{ marginLeft: '1rem' }}>
              📂 Cargar Datos de Prueba
            </button>
          </div>
          {mensajeCarga && (
            <div style={{ 
              padding: '1rem', 
              margin: '1rem 0', 
              backgroundColor: mensajeCarga.includes('✅') ? '#d4edda' : '#f8d7da',
              color: mensajeCarga.includes('✅') ? '#155724' : '#721c24',
              borderRadius: '4px'
            }}>
              {mensajeCarga}
            </div>
          )}
          
          <div className="data-table-container">
            <table className="data-table">
              <thead>
                <tr>
                  <th>Nombre</th>
                  <th>Tipo</th>
                  <th>Capacidad</th>
                  <th>Restricciones</th>
                  <th>Costo</th>
                  <th>Estado</th>
                </tr>
              </thead>
              <tbody>
                {atracciones.length > 0 ? (
                  atracciones.map(atr => (
                    <tr key={atr.id}>
                      <td><strong>{atr.nombre}</strong></td>
                      <td><span className="atraccion-tag">{atr.tipo}</span></td>
                      <td>{atr.capacidadMax} pax</td>
                      <td>📏 {atr.alturaMin}m | 🔞 {atr.edadMin} años</td>
                      <td>${atr.costoAdicional}</td>
                      <td>
                        <span className={`estado-${atr.estado.toLowerCase()}`}>
                          {atr.estado}
                        </span>
                      </td>
                    </tr>
                  ))
                ) : (
                  <tr>
                    <td colSpan="6" style={{ textAlign: 'center', padding: '2rem' }}>
                      Cargando atracciones... Verifica que el backend esté corriendo.
                    </td>
                  </tr>
                )}
              </tbody>
            </table>
          </div>
        </section>
      </main>
    </div>
  )
}

export default App

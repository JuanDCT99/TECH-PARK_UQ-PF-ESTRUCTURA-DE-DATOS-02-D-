import React, { useState, useEffect } from 'react';
import './App.css';
import { LoginView } from './components/LoginView';
import { RegistroView } from './components/RegistroView';
import { MapaParque } from './components/MapaParque';
import welcomeImg from './assets/Bienvenidos.png' 
import roleImg from './assets/Rol.png'

function App() {
  // ==========================================
  // ESTADOS (ESTADO INICIAL)
  // =========================================
  const [pantallaActual, setPantallaActual] = useState('landing'); // 'landing', 'login', 'registro', 'dashboard'
  const [rolSeleccionado, setRolSeleccionado] = useState('');
  const [usuario, setUsuario] = useState(null);
  const [view, setView] = useState('welcome')
  const [selectedRole, setSelectedRole] = useState(null)
  const [atracciones, setAtracciones] = useState([])
  const [senderos, setSenderos] = useState([])
  const [estadoParque, setEstadoParque] = useState('CARGANDO...')
  const [mensajeCarga, setMensajeCarga] = useState('')
  const [rutaOptima, setRutaOptima] = useState(null)
  const [origenRuta, setOrigenRuta] = useState('')
  const [destinoRuta, setDestinoRuta] = useState('')
  const [reporteDiario, setReporteDiario] = useState(null)
  const [populares, setPopulares] = useState([])
  const [usuarios, setUsuarios] = useState([])
  const [selectedUser, setSelectedUser] = useState(null)
  const [mensajeProcesar, setMensajeProcesar] = useState('')
  const [mensajeTicket, setMensajeTicket] = useState('')
  const [misTiquetes, setMisTiquetes] = useState([])
  const [favoritos, setFavoritos] = useState([])
  const [mensajeFav, setMensajeFav] = useState('')
  const [historial, setHistorial] = useState([])
  const [montoRecarga, setMontoRecarga] = useState(10000)
  const [mensajeRecarga, setMensajeRecarga] = useState('')
  const [mensajeMant, setMensajeMant] = useState('')
  const [visitorTab, setVisitorTab] = useState('tickets')

  // ==========================================
  // FUNCIONES DE CONTROL / PETICIONES API
  // =========================================

  const manejarSeleccionRol = (rol) => {
    setRolSeleccionado(rol);
    setPantallaActual('login'); // Pasamos automáticamente a la pantalla de login
  };

  const manejarLoginExitoso = (datosUsuario) => {
    setUsuario(datosUsuario);
    setPantallaActual('dashboard'); // Lo mandamos al mapa o panel del parque
  };

  const fetchDatosBase = () => {
    fetch('http://localhost:8080/api/parque/atracciones')
      .then(res => res.json())
      .then(data => setAtracciones(data))
      .catch(err => console.error("Error cargando atracciones:", err))

    fetch('http://localhost:8080/api/parque/estado')
      .then(res => res.text())
      .then(data => setEstadoParque(data))
      .catch(err => console.error("Error cargando estado:", err))

    fetch('http://localhost:8080/api/parque/usuarios')
      .then(res => res.json())
      .then(data => {
        setUsuarios(data)
        if (data.length > 0 && !selectedUser) setSelectedUser(data[0].id)
      })
      .catch(err => console.error("Error cargando usuarios:", err))

    fetch('http://localhost:8080/api/parque/zonas')
      .then(() => {
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
      .then(data => { setMensajeCarga('✅ ' + data); fetchDatosBase(); })
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
    const vid = selectedUser || 'V1'
    fetch(`http://localhost:8080/api/parque/unirse-fila?visitanteId=${vid}&atraccionId=${atraccionId}&tipoTiquete=${tipo}`, { method: 'POST' })
      .then(res => res.text())
      .then(data => alert(data))
      .catch(err => alert("Error: " + err));
  }

  const procesarFila = (atraccionId) => {
    setMensajeProcesar('Procesando...');
    fetch(`http://localhost:8080/api/parque/procesar-fila?atraccionId=${atraccionId}`, { method: 'POST' })
      .then(res => res.text())
      .then(data => { setMensajeProcesar(data); fetchDatosBase(); })
      .catch(err => setMensajeProcesar('❌ Error: ' + err))
  }

  const fetchFavoritos = () => {
    const vid = selectedUser || 'V1'
    fetch(`http://localhost:8080/api/parque/mis-favoritos?visitanteId=${vid}`)
      .then(res => res.json())
      .then(data => setFavoritos(data))
      .catch(err => console.error("Error cargando favoritos:", err))
  }

  const fetchHistorial = () => {
    const vid = selectedUser || 'V1'
    fetch(`http://localhost:8080/api/parque/historial?visitanteId=${vid}`)
      .then(res => res.json())
      .then(data => setHistorial(data))
      .catch(err => console.error("Error cargando historial:", err))
  }

  const recargarSaldo = () => {
    const vid = selectedUser || 'V1'
    fetch(`http://localhost:8080/api/parque/recargar-saldo?visitanteId=${vid}&monto=${montoRecarga}`, { method: 'POST' })
      .then(res => res.text())
      .then(data => setMensajeRecarga(data))
      .catch(err => setMensajeRecarga('❌ Error: ' + err))
  }

  const toggleMantenimiento = (atraccionId, accion) => {
    fetch(`http://localhost:8080/api/parque/mantenimiento?atraccionId=${atraccionId}&accion=${accion}`, { method: 'POST' })
      .then(res => res.text())
      .then(data => { setMensajeMant(data); fetchDatosBase(); })
      .catch(err => setMensajeMant('❌ Error: ' + err))
  }

  const toggleFavorito = (atraccionId, esFavorito) => {
    const vid = selectedUser || 'V1'
    const accion = esFavorito ? 'eliminar-favorito' : 'agregar-favorito'
    fetch(`http://localhost:8080/api/parque/${accion}?visitanteId=${vid}&atraccionId=${atraccionId}`, { method: 'POST' })
      .then(res => res.text())
      .then(data => { setMensajeFav(data); fetchFavoritos(); })
      .catch(err => setMensajeFav('❌ Error: ' + err))
  }

  const fetchMisTiquetes = () => {
    const vid = selectedUser || 'V1'
    fetch(`http://localhost:8080/api/parque/mis-tiquetes?visitanteId=${vid}`)
      .then(res => res.json())
      .then(data => setMisTiquetes(data))
      .catch(err => console.error("Error cargando tiquetes:", err))
  }

  const comprarTicket = (tipo) => {
    const vid = selectedUser || 'V1'
    setMensajeTicket('Procesando...');
    fetch(`http://localhost:8080/api/parque/comprar-ticket?visitanteId=${vid}&tipoTiquete=${tipo}`, { method: 'POST' })
      .then(res => res.text())
      .then(data => { setMensajeTicket(data); fetchMisTiquetes(); })
      .catch(err => setMensajeTicket('❌ Error: ' + err));
  }

  const fetchReportes = () => {
    fetch('http://localhost:8080/api/parque/reportes/diario')
      .then(res => res.json())
      .then(data => setReporteDiario(data))
      .catch(err => console.error("Error cargando reportes:", err))

    fetch('http://localhost:8080/api/parque/reportes/populares')
      .then(res => res.json())
      .then(data => setPopulares(data))
      .catch(err => console.error("Error cargando populares:", err))
  }

  // ==========================================
  // EFECTOS (useEffect)
  // =========================================
  useEffect(() => {
    if (view === 'dashboard') {
      fetchDatosBase();
      if (selectedRole === 'Administrador') fetchReportes();
    }
  }, [view, selectedRole])

  useEffect(() => {
    if (view === 'dashboard' && selectedRole === 'Visitante' && selectedUser) {
      fetchMisTiquetes();
      fetchFavoritos();
      fetchHistorial();
    }
  }, [view, selectedRole, selectedUser])

  // ==========================================
  // MANEJADORES DE VISTA
  // =========================================
  const handleStart = () => setView('role-selection')
  const handleRoleSelect = (role) => { setRolSeleccionado(role); setView('login') }

  // ==========================================
  // RENDER CONDICIONAL: PANTALLA DE BIENVENIDA
  // ==========================================
 if (view === 'welcome') {
    return (
      <div className="welcome-container">
        {/* Capas de fondo animadas y oscurecidas */}
        <div className="bg-image-layer" style={{ backgroundImage: `url(${welcomeImg})` }}></div>
        <div className="bg-overlay-tint"></div>

        {/* Puntos de confeti decorativos */}
        {[
          { size: 14, color: '#FF6B6B', top: '15%', left: '10%', duration: '7s', delay: '0s' },
          { size: 10, color: '#FF9F43', top: '25%', left: '85%', duration: '9s', delay: '1s' },
          { size: 18, color: '#FECA57', top: '70%', left: '8%', duration: '11s', delay: '2s' },
          { size: 12, color: '#48DBFB', top: '80%', left: '90%', duration: '8s', delay: '0.5s' },
          { size: 16, color: '#A29BFE', top: '40%', left: '92%', duration: '10s', delay: '1.5s' },
          { size: 10, color: '#FF6B9D', top: '60%', left: '5%', duration: '12s', delay: '3s' },
          { size: 14, color: '#74B9FF', top: '10%', left: '70%', duration: '8s', delay: '2.5s' },
          { size: 8,  color: '#FECA57', top: '50%', left: '50%', duration: '15s', delay: '4s' },
        ].map((dot, i) => (
          <div
            key={i}
            className="confetti-dot"
            style={{
              width: dot.size, height: dot.size,
              background: dot.color,
              top: dot.top, left: dot.left,
              animationDuration: dot.duration,
              animationDelay: dot.delay,
            }}
          />
        ))}

        <div className="overlay">
          <div className="welcome-card">
            <div className="welcome-emoji-banner">
              🎡 🎢 🎠
            </div>

            <h1 className="title">TECH-PARK UQ</h1>

            <div className="color-dots">
              {[1,2,3,4,5,6].map(i => <div key={i} className="color-dot" />)}
            </div>

            <p className="subtitle">✨ Donde la tecnología y la diversión se encuentran ✨</p>

            {/* MODIFICADO: Ahora este botón te manda directo a elegir el rol */}
            <button className="btn-primary" onClick={() => setView('role-selection')}>
              🎟️ Ingresar al Parque
            </button>

            <div className="welcome-badges">
              <span className="welcome-badge">🎢 Atracciones</span>
              <span className="welcome-badge">🗺️ Mapa interactivo</span>
              <span className="welcome-badge">⚡ Fast Pass</span>
              <span className="welcome-badge">📊 Reportes en vivo</span>
            </div>
          </div>
        </div>
        {/* SE ELIMINÓ EL BOTÓN DUPLICADO QUE QUEDABA AQUÍ AFUERA */}
      </div>
    );
  }

  // ==========================================
  // RENDER CONDICIONAL: SELECCIÓN DE ROL
  // ==========================================
  if (view === 'role-selection') {
    return (
      <div className="role-container">
        {/* Capas de fondo para la selección de rol */}
        <div className="bg-image-layer" style={{ backgroundImage: `url(${roleImg})` }}></div>
        <div className="bg-overlay-tint"></div>

        <div className="overlay">
          <div className="content">
            <div style={{ marginBottom: '0.5rem', fontSize: '2.5rem' }}>🎪</div>
            <h2 className="title">¿Quién eres hoy?</h2>
            <p className="role-subtitle">Selecciona tu rol para continuar</p>

           <div className="role-grid">
              {/* Dejamos que tu función original maneje todo limpiamente */}
              <button className="role-card" onClick={() => handleRoleSelect('Visitante')}>
                <span className="role-icon">🎟️</span>
                <h3>Visitante</h3>
                <p>Explora atracciones, compra tickets y calcula rutas</p>
              </button>
              <button className="role-card" onClick={() => handleRoleSelect('Empleado')}>
                <span className="role-icon">🛠️</span>
                <h3>Empleado</h3>
                <p>Gestiona filas y atracciones del parque</p>
              </button>
              <button className="role-card" onClick={() => handleRoleSelect('Administrador')}>
                <span className="role-icon">🔐</span>
                <h3>Administrador</h3>
                <p>Alertas, reportes y estadísticas globales</p>
              </button>
            </div>

            <button className="btn-secondary" onClick={() => setView('welcome')}>
              ← Volver al inicio
            </button>
          </div>
        </div>
      </div>
    )
  }

  // ==========================================
  // RENDER CONDICIONAL: LOGIN
  // ==========================================
 if (view === 'login') {
    return (
      <LoginView 
        rolSeleccionado={rolSeleccionado} 
        alIniciarSesion={(usuario) => {
          setUsuarioAutenticado(usuario); // Revisa si tu estado se llama setUsuario o setUsuarioAutenticado
          setView('mapa'); 
        }} 
        alCambiarDeVista={(nuevaVista) => setView(nuevaVista)} 
      />
    );
  }

  // ==========================================
  // RENDER CONDICIONAL: REGISTRO
  // ==========================================
  if (view === 'registro') {
    return (
      <RegistroView 
        rolSeleccionado={rolSeleccionado} 
        alCambiarDeVista={(nuevaVista) => setView(nuevaVista)} 
      />
    );
  }

  // ==========================================
  // RENDER POR DEFECTO: DASHBOARD PRINCIPAL
  // ==========================================
return (
    <>
      {/* =========================================================
          1. PANTALLA DE BIENVENIDA (LANDING PAGE)
         ========================================================= */}
      {view === 'landing' && (
        <div className="auth-outer-container" style={{ display: 'flex', justifyContent: 'center', alignItems: 'center', minHeight: '100vh', background: '#f5f6fa', padding: '20px' }}>
          <div className="card auth-card" style={{ textAlign: 'center', padding: '3rem', maxWidth: '500px', width: '100%', boxShadow: '0 10px 30px rgba(0,0,0,0.1)', borderRadius: '15px', background: '#fff' }}>
            <div style={{ fontSize: '3.5rem', marginBottom: '1rem' }}>🎡 🎢 🎠</div>
            <h1 style={{ fontSize: '3rem', color: '#ff7a59', margin: 0, fontWeight: 'bold' }}>TECH-PARK UQ</h1>
            <div style={{ display: 'flex', justifyContent: 'center', gap: '8px', margin: '1.5rem 0' }}>
              <span style={{ width: '12px', height: '12px', borderRadius: '50%', backgroundColor: '#ff6b6b' }}></span>
              <span style={{ width: '12px', height: '12px', borderRadius: '50%', backgroundColor: '#feca57' }}></span>
              <span style={{ width: '12px', height: '12px', borderRadius: '50%', backgroundColor: '#1dd1a1' }}></span>
              <span style={{ width: '12px', height: '12px', borderRadius: '50%', backgroundColor: '#54a0ff' }}></span>
            </div>
            <p style={{ color: '#666', fontSize: '1.1rem', marginBottom: '2.5rem' }}>Donde la tecnología y la diversión se encuentran.</p>
            <button 
              className="btn-primary" 
              onClick={() => setView('role-selection')}
              style={{ padding: '0.8rem 2rem', borderRadius: '25px', fontSize: '1.1rem', cursor: 'pointer', border: 'none' }}
            >
              🎟️ Ingresar al Sistema
            </button>
          </div>
        </div>
      )}

      {/* =========================================================
          2. PANTALLA DE SELECCIÓN DE ROL
         ========================================================= */}
      {view === 'role-selection' && (
        <div className="auth-outer-container" style={{ display: 'flex', justifyContent: 'center', alignItems: 'center', minHeight: '100vh', background: '#f5f6fa', padding: '20px' }}>
          <div className="card auth-card" style={{ textAlign: 'center', padding: '2.5rem', maxWidth: '450px', width: '100%', boxShadow: '0 10px 30px rgba(0,0,0,0.1)', borderRadius: '15px', background: '#fff' }}>
            <h2 style={{ marginBottom: '0.5rem' }}>Selecciona tu Rol</h2>
            <p style={{ color: '#666', marginBottom: '2rem' }}>Por favor, elige cómo deseas ingresar a TECH-PARK UQ</p>
            <div style={{ display: 'grid', gap: '1rem' }}>
              <button className="btn-primary" style={{ padding: '0.75rem', fontSize: '1rem' }} onClick={() => { setSelectedRole('Visitante'); setView('login'); }}>Visitante</button>
              <button className="btn-primary" style={{ padding: '0.75rem', fontSize: '1rem' }} onClick={() => { setSelectedRole('Empleado'); setView('login'); }}>Empleado</button>
              <button className="btn-primary" style={{ padding: '0.75rem', fontSize: '1rem' }} onClick={() => { setSelectedRole('Administrador'); setView('login'); }}>Administrador</button>
            </div>
            <button onClick={() => setView('landing')} style={{ marginTop: '1.5rem', background: 'none', border: 'none', color: '#888', cursor: 'pointer', textDecoration: 'underline' }}>
              Volver al Inicio
            </button>
          </div>
        </div>
      )}

      {/* =========================================================
          3. VISTAS DE AUTENTICACIÓN MODULARES (LOGIN / REGISTRO)
         ========================================================= */}
      {view === 'login' && (
        <LoginView 
          selectedRole={rolSeleccionado} // <-- Cambiado de selectedRole a rolSeleccionado
          alIniciarSesion={() => setView('dashboard')} 
          alCambiarDeVista={setView} 
        />
      )}

      {view === 'registro' && (
        <RegistroView 
          selectedRole={rolSeleccionado} // <-- Cambiado de selectedRole a rolSeleccionado
          alCambiarDeVista={setView} 
        />
      )}

      {/* =========================================================
          4. DASHBOARD PRINCIPAL (TU CÓDIGO ORIGINAL COMPLETO)
         ========================================================= */}
      {(view === 'dashboard' || !view || view === '') && (
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
                {selectedRole === 'Visitante' && usuarios.length > 0 && (
                  <div className="user-selector">
                    <label>Visitante: </label>
                    <select value={selectedUser || ''} onChange={(e) => setSelectedUser(e.target.value)}>
                      {usuarios.map(u => (
                        <option key={u.id} value={u.id}>{u.nombre} ({u.id})</option>
                      ))}
                    </select>
                  </div>
                )}
              </div>
              <button className="btn-primary" onClick={cargarDatosPrueba}>
                🔄 Sincronizar Datos
              </button>
            </header>

            <div className="dashboard-grid">
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
                                <button
                                  className={`btn-mini fav ${favoritos.some(f => f.id === atr.id) ? 'active' : ''}`}
                                  onClick={() => toggleFavorito(atr.id, favoritos.some(f => f.id === atr.id))}
                                >
                                  {favoritos.some(f => f.id === atr.id) ? '❤️' : '🤍'}
                                </button>
                                <button className="btn-mini fast" onClick={() => unirseAFila(atr.id, 'FAST_PASS')}>FastPass</button>
                                <button className="btn-mini" onClick={() => unirseAFila(atr.id, 'GENERAL')}>Fila</button>
                              </div>
                            )}
                            {selectedRole === 'Empleado' && (
                              <div className="action-buttons">
                                <span className="cola-badge">{atr.colaSize} en cola</span>
                                <button className="btn-mini fast" onClick={() => procesarFila(atr.id)}>Procesar</button>
                                {atr.estado === 'ACTIVA' ? (
                                  <button className="btn-mini warn" onClick={() => toggleMantenimiento(atr.id, 'iniciar')}>Mantenimiento</button>
                                ) : atr.estado === 'EN_MANTENIMIENTO' ? (
                                  <button className="btn-mini success" onClick={() => toggleMantenimiento(atr.id, 'revisar')}>Revisar</button>
                                ) : null}
                              </div>
                            )}
                          </td>
                        </tr>
                      ))}
                    </tbody>
                  </table>
                </div>
              </section>
            </div>

            {selectedRole === 'Empleado' && mensajeProcesar && (
              <div className="procesar-mensaje">{mensajeProcesar}</div>
            )}
            {selectedRole === 'Empleado' && mensajeMant && (
              <div className="procesar-mensaje">{mensajeMant}</div>
            )}

            {selectedRole === 'Visitante' && selectedUser && (
              <>
                <div className="tab-bar">
                  <button className={`tab-btn ${visitorTab === 'tickets' ? 'active' : ''}`} onClick={() => setVisitorTab('tickets')}>
                    🎫 Tickets
                  </button>
                  <button className={`tab-btn ${visitorTab === 'perfil' ? 'active' : ''}`} onClick={() => setVisitorTab('perfil')}>
                    ⭐ Mi Perfil
                  </button>
                </div>

                <div className="tab-content">
                  {visitorTab === 'tickets' && (
                    <>
                      <div className="section-card">
                        <h3 className="section-title">🎫 Comprar Tiquete</h3>
                        <div className="ticket-grid">
                          <div className="ticket-card" onClick={() => comprarTicket('GENERAL')}>
                            <h3>General</h3>
                            <p className="ticket-price">$20,000</p>
                            <p className="ticket-desc">Acceso estándar al parque</p>
                            <button className="btn-action">Comprar</button>
                          </div>
                          <div className="ticket-card" onClick={() => comprarTicket('FAST_PASS')}>
                            <h3>Fast-Pass</h3>
                            <p className="ticket-price">$50,000</p>
                            <p className="ticket-desc">Acceso prioritario a filas</p>
                            <button className="btn-action">Comprar</button>
                          </div>
                          <div className="ticket-card" onClick={() => comprarTicket('FAMILIAR')}>
                            <h3>Familiar</h3>
                            <p className="ticket-price">$45,000</p>
                            <p className="ticket-desc">Descuento para grupos (hasta 4 pers.)</p>
                            <button className="btn-action">Comprar</button>
                          </div>
                        </div>
                        {mensajeTicket && (
                          <div className={`ticket-mensaje ${mensajeTicket.includes('✅') ? 'exito' : 'error'}`}>
                            {mensajeTicket}
                          </div>
                        )}
                      </div>

                      {misTiquetes.length > 0 && (
                        <div className="section-card">
                          <h3>🎟️ Mis Tiquetes</h3>
                          <div className="table-wrapper">
                            <table className="table"> 
                              <thead>
                                <tr>
                                  <th>ID</th>
                                  <th>Tipo</th>
                                  <th>Precio</th>
                                  <th>Descripción</th>
                                </tr>
                              </thead>
                              <tbody>
                                {misTiquetes.map(t => (
                                  <tr key={t.id}>
                                    <td><small>{t.id}</small></td>
                                    <td><strong>{t.tipo}</strong></td>
                                    <td>${t.precio.toLocaleString()}</td>
                                    <td>{t.descripcion}</td>
                                  </tr>
                                ))}
                              </tbody>
                            </table>
                          </div>
                        </div>
                      )}

                      <div className="section-card">
                        <h3>💰 Recargar Saldo</h3>
                        <div className="recarga-section">
                          <div className="recarga-controls">
                            <select value={montoRecarga} onChange={(e) => setMontoRecarga(Number(e.target.value))}>
                              <option value={10000}>$10,000</option>
                              <option value={20000}>$20,000</option>
                              <option value={50000}>$50,000</option>
                              <option value={100000}>$100,000</option>
                            </select>
                            <button className="btn-action" onClick={recargarSaldo}>Recargar</button>
                          </div>
                          {mensajeRecarga && (
                            <div className={`ticket-mensaje ${mensajeRecarga.includes('✅') ? 'exito' : 'error'}`}>
                              {mensajeRecarga}
                            </div>
                          )}
                        </div>
                      </div>
                    </>
                  )}

                  {visitorTab === 'perfil' && (
                    <>
                      {favoritos.length > 0 ? (
                        <div className="section-card">
                          <h3>⭐ Mis Favoritos</h3>
                          {mensajeFav && (
                            <div className={`ticket-mensaje ${mensajeFav.includes('✅') ? 'exito' : 'error'}`}>
                              {mensajeFav}
                            </div>
                          )}
                          <div className="table-wrapper">
                            <table className="data-table">
                              <thead>
                                <tr><th>Atracción</th><th>Tipo</th><th>Estado</th></tr>
                              </thead>
                              <tbody>
                                {favoritos.map(f => (
                                  <tr key={f.id}>
                                    <td><strong>{f.nombre}</strong></td>
                                    <td>{f.tipo}</td>
                                    <td><span className={`badge estado-${f.estado.toLowerCase()}`}>{f.estado}</span></td>
                                  </tr>
                                ))}
                              </tbody>
                            </table>
                          </div>
                        </div>
                      ) : (
                        <div className="section-card">
                          <h3>⭐ Mis Favoritos</h3>
                          {mensajeFav && (
                            <div className={`ticket-mensaje ${mensajeFav.includes('✅') ? 'exito' : 'error'}`}>
                              {mensajeFav}
                            </div>
                          )}
                          <p style={{ color: 'var(--text-muted)', fontSize: '0.9rem' }}>
                            No tienes favoritos aún. Ve al mapa 🗺️ y haz clic en 🤍 para agregar.
                          </p>
                        </div>
                      )}

                      {historial.length > 0 ? (
                        <div className="section-card">
                          <h3>📋 Historial de Visitas</h3>
                          <div className="table-wrapper">
                            <table className="data-table">
                              <thead>
                                <tr><th>Atracción</th><th>Tipo</th><th>Estado</th></tr>
                              </thead>
                              <tbody>
                                {historial.map(h => (
                                  <tr key={h.id}>
                                    <td><strong>{h.nombre}</strong></td>
                                    <td>{h.tipo}</td>
                                    <td><span className={`badge estado-${h.estado.toLowerCase()}`}>{h.estado}</span></td>
                                  </tr>
                                ))}
                              </tbody>
                            </table>
                          </div>
                        </div>
                      ) : (
                        <div className="section-card">
                          <h3>📋 Historial de Visitas</h3>
                          <p style={{ color: 'var(--text-muted)', fontSize: '0.9rem' }}>
                            No hay visitas registradas. Pídele a un Empleado que procese tu fila.
                          </p>
                        </div>
                      )}
                    </>
                  )}
                </div>
              </>
            )}

            {selectedRole === 'Administrador' && reporteDiario && (
              <section className="reports-section">
                <h2>📊 Reportes del Parque</h2>
                <div className="stats-grid">
                  <div className="stat-card">
                    <span className="stat-value">${reporteDiario.ingresosDiarios.toLocaleString()}</span>
                    <span className="stat-label">Ingresos Diarios</span>
                  </div>
                  <div className="stat-card">
                    <span className="stat-value">{reporteDiario.totalVisitantes}</span>
                    <span className="stat-label">Visitantes Totales</span>
                  </div>
                  <div className="stat-card">
                    <span className="stat-value">{reporteDiario.tiempoPromEspera.toFixed(1)} min</span>
                    <span className="stat-label">Tiempo Prom. Espera</span>
                  </div>
                  <div className="stat-card">
                    <span className="stat-value">{reporteDiario.cierresClima}</span>
                    <span className="stat-label">Cierres por Clima</span>
                  </div>
                  <div className="stat-card">
                    <span className="stat-value">{reporteDiario.alertasMantenimiento}</span>
                    <span className="stat-label">Alertas Mantenimiento</span>
                  </div>
                </div>

                {populares.length > 0 && (
                  <div className="popular-section">
                    <h3>🏆 Atracciones Más Visitadas</h3>
                    <table className="data-table">
                      <thead>
                        <tr>
                          <th>#</th><th>Atracción</th><th>Tipo</th><th>Visitas</th><th>Ingresos Generados</th>
                        </tr>
                      </thead>
                      <tbody>
                        {populares.map((atr, idx) => (
                          <tr key={atr.id}>
                            <td>{idx + 1}</td>
                            <td><strong>{atr.nombre}</strong></td>
                            <td>{atr.tipo}</td>
                            <td>{atr.contadorVisitantes}</td>
                            <td>${(atr.contadorVisitantes * atr.costoAdicional).toLocaleString()}</td>
                          </tr>
                        ))}
                      </tbody>
                    </table>
                  </div>
                )}
              </section>
            )}
          </main>
        </div>
      )}
    </>
  );
}
export default App;
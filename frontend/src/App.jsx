import { useState, useEffect } from 'react'
import './App.css'
import MapaParque from './components/MapaParque'
import { LoginView } from './components/LoginView'
import { RegistroView } from './components/RegistroView'
import { VisitanteIcon, EmpleadoIcon, AdminIcon } from './components/Icons'
import welcomeImg from './assets/Bienvenidos.png'
import roleImg from './assets/Rol.png'

function App() {
  const [view, setView] = useState('welcome') // 'welcome', 'role-selection', 'login', 'registro', 'dashboard'
  const [selectedRole, setSelectedRole] = useState(null)
  const [usuario, setUsuario] = useState(null)
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
  const [mensajeClima, setMensajeClima] = useState('')
  const [alertaActiva, setAlertaActiva] = useState(false)
  const [visitorTab, setVisitorTab] = useState('tickets')
  const [empleadoTab, setEmpleadoTab] = useState('colas')
  const [adminTab, setAdminTab] = useState('reportes')

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

    fetch('http://localhost:8080/api/parque/senderos')
      .then(res => res.json())
      .then(data => setSenderos(data))
      .catch(err => console.error("Error cargando senderos:", err))
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
    const vid = selectedUser || 'V1'
    fetch(`http://localhost:8080/api/parque/unirse-fila?visitanteId=${vid}&atraccionId=${atraccionId}&tipoTiquete=${tipo}`, {
      method: 'POST'
    })
      .then(res => res.text())
      .then(data => alert(data))
      .catch(err => alert("Error: " + err));
  }

  const procesarFila = (atraccionId) => {
    setMensajeProcesar('Procesando...');
    fetch(`http://localhost:8080/api/parque/procesar-fila?atraccionId=${atraccionId}`, {
      method: 'POST'
    })
      .then(res => res.text())
      .then(data => {
        setMensajeProcesar(data);
        fetchDatosBase();
      })
      .catch(err => setMensajeProcesar('❌ Error: ' + err));
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
    fetch(`http://localhost:8080/api/parque/recargar-saldo?visitanteId=${vid}&monto=${montoRecarga}`, {
      method: 'POST'
    })
      .then(res => res.text())
      .then(data => setMensajeRecarga(data))
      .catch(err => setMensajeRecarga('❌ Error: ' + err))
  }

  const toggleMantenimiento = (atraccionId, accion) => {
    fetch(`http://localhost:8080/api/parque/mantenimiento?atraccionId=${atraccionId}&accion=${accion}`, {
      method: 'POST'
    })
      .then(res => res.text())
      .then(data => {
        setMensajeMant(data)
        fetchDatosBase()
      })
      .catch(err => setMensajeMant('❌ Error: ' + err))
  }

  const toggleAlertaClima = () => {
    const accion = alertaActiva ? 'desactivar' : 'activar';
    fetch(`http://localhost:8080/api/parque/alerta-clima?accion=${accion}`, { method: 'POST' })
      .then(res => res.text())
      .then(data => {
        setMensajeClima(data);
        setAlertaActiva(!alertaActiva);
        fetchDatosBase();
      })
      .catch(err => setMensajeClima('❌ Error: ' + err));
  }

  const toggleFavorito = (atraccionId, esFavorito) => {
    const vid = selectedUser || 'V1'
    const accion = esFavorito ? 'eliminar-favorito' : 'agregar-favorito'
    fetch(`http://localhost:8080/api/parque/${accion}?visitanteId=${vid}&atraccionId=${atraccionId}`, {
      method: 'POST'
    })
      .then(res => res.text())
      .then(data => {
        setMensajeFav(data)
        fetchFavoritos()
      })
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
    fetch(`http://localhost:8080/api/parque/comprar-ticket?visitanteId=${vid}&tipoTiquete=${tipo}`, {
      method: 'POST'
    })
      .then(res => res.text())
      .then(data => {
        setMensajeTicket(data);
        fetchMisTiquetes();
      })
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

  useEffect(() => {
    if (view === 'dashboard') {
      fetchDatosBase();
      if (selectedRole === 'Administrador') {
        fetchReportes();
      }
    }
  }, [view, selectedRole])

  useEffect(() => {
    if (view === 'dashboard' && selectedRole === 'Visitante' && selectedUser) {
      fetchMisTiquetes();
      fetchFavoritos();
      fetchHistorial();
    }
  }, [view, selectedRole, selectedUser])

  useEffect(() => {
    if (usuario?.id && view === 'dashboard') {
      setSelectedUser(usuario.id);
    }
  }, [usuario, view])

  const handleStart = () => {
    setView('role-selection')
  }

  const handleRoleSelect = (role) => {
    setSelectedRole(role)
    setView('login')
  }

  if (view === 'welcome') {
    return (
      <div className="welcome-container">
        <div className="bg-image-layer" style={{ backgroundImage: `url(${welcomeImg})` }}></div>
        <div className="bg-overlay-tint"></div>
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
      <div className="role-container">
        <div className="bg-image-layer" style={{ backgroundImage: `url(${roleImg})` }}></div>
        <div className="bg-overlay-tint"></div>
        <div className="overlay">
          <div className="content">
            <h2 className="title">Selecciona tu Rol</h2>
            <div className="role-grid">
              <button className="role-card" onClick={() => handleRoleSelect('Visitante')}>
                <VisitanteIcon size={56} />
                <h3>Visitante</h3>
                <p>Calcula rutas y únete a filas</p>
              </button>
              <button className="role-card" onClick={() => handleRoleSelect('Empleado')}>
                <EmpleadoIcon size={56} />
                <h3>Empleado</h3>
                <p>Gestiona atracciones</p>
              </button>
              <button className="role-card" onClick={() => handleRoleSelect('Administrador')}>
                <AdminIcon size={56} />
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

  if (view === 'login') {
    return (
      <LoginView
        rolSeleccionado={selectedRole}
        alIniciarSesion={(data) => {
          setUsuario(data);
          setView('dashboard');
        }}
        alCambiarDeVista={setView}
      />
    )
  }

  if (view === 'registro') {
    return (
      <RegistroView
        rolSeleccionado={selectedRole}
        alCambiarDeVista={setView}
      />
    )
  }

  return (
    <div className="dashboard-container">
      <nav className="navbar">
        <h2>TECH-PARK | {usuario?.nombre || selectedRole}</h2>
        <button className="btn-logout" onClick={() => {
          fetch('http://localhost:8080/api/auth/logout', { method: 'POST' })
            .catch(err => console.error("Error en logout:", err));
          setUsuario(null);
          setView('role-selection');
        }}>Cerrar Sesión</button>
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
          <button className="btn-primary" onClick={cargarDatosPrueba} title="Recarga los datos desde los archivos JSON del servidor">
            🔄 Recargar desde JSON
          </button>
        </header>

        <div className="dashboard-grid">
          {/* SECCIÓN MAPA Y RUTA ÓPTIMA */}
          <section className="map-section card">
            <h3>🗺️ Ruta Óptima</h3>
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
                <button className="btn-action" onClick={calcularRuta}>Buscar Ruta más Corta</button>
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
                            <button className="btn-mini fast" onClick={() => procesarFila(atr.id)}>
                              Procesar
                            </button>
                            {atr.estado === 'ACTIVA' ? (
                              <button className="btn-mini warn" onClick={() => toggleMantenimiento(atr.id, 'iniciar')}>
                                Mantenimiento
                              </button>
                            ) : atr.estado === 'EN_MANTENIMIENTO' ? (
                              <button className="btn-mini success" onClick={() => toggleMantenimiento(atr.id, 'revisar')}>
                                Revisar
                              </button>
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

        {selectedRole === 'Empleado' && (
          <>
            <div className="tab-bar">
              <button className={`tab-btn ${empleadoTab === 'colas' ? 'active' : ''}`}
                onClick={() => setEmpleadoTab('colas')}>
                🎢 Gestión de Colas
              </button>
              <button className={`tab-btn ${empleadoTab === 'mantenimiento' ? 'active' : ''}`}
                onClick={() => setEmpleadoTab('mantenimiento')}>
                🔧 Mantenimiento
              </button>
            </div>

            <div className="tab-content">
              {empleadoTab === 'colas' && (
                <div className="section-card">
                  <h3>🎢 Colas de Espera</h3>
                  <p style={{ color: 'var(--text-muted)', fontSize: '0.85rem', marginBottom: '0.8rem' }}>
                    Atención prioritaria: Fast-Pass sale primero que General.
                  </p>
                  {mensajeProcesar && (
                    <div className={`ticket-mensaje ${mensajeProcesar.includes('✅') || !mensajeProcesar.includes('Error') ? 'exito' : 'error'}`}>
                      {mensajeProcesar}
                    </div>
                  )}
                  <div className="table-wrapper">
                    <table className="data-table">
                      <thead>
                        <tr>
                          <th>Atracción</th>
                          <th>Estado</th>
                          <th>En Cola</th>
                          <th>Acción</th>
                        </tr>
                      </thead>
                      <tbody>
                        {atracciones.filter(a => a.colaSize > 0).length === 0 ? (
                          <tr>
                            <td colSpan={4} style={{ textAlign: 'center', color: 'var(--text-muted)', padding: '1.5rem' }}>
                              No hay visitantes en cola en este momento.
                            </td>
                          </tr>
                        ) : (
                          atracciones.filter(a => a.colaSize > 0).map(atr => (
                            <tr key={atr.id}>
                              <td><strong>{atr.nombre}</strong><br/><small>{atr.tipo}</small></td>
                              <td>
                                <span className={`badge estado-${atr.estado.toLowerCase()}`}>{atr.estado}</span>
                              </td>
                              <td><span className="cola-badge">{atr.colaSize}</span></td>
                              <td>
                                <button className="btn-mini fast" onClick={() => procesarFila(atr.id)}>
                                  Procesar Siguiente
                                </button>
                              </td>
                            </tr>
                          ))
                        )}
                      </tbody>
                    </table>
                  </div>
                </div>
              )}

              {empleadoTab === 'mantenimiento' && (
                <div className="section-card">
                  <h3>🔧 Mantenimiento de Atracciones</h3>
                  <p style={{ color: 'var(--text-muted)', fontSize: '0.85rem', marginBottom: '0.8rem' }}>
                    Las atracciones se bloquean automáticamente tras 500 visitas.
                  </p>
                  {mensajeMant && (
                    <div className={`ticket-mensaje ${mensajeMant.includes('✅') ? 'exito' : 'error'}`}>
                      {mensajeMant}
                    </div>
                  )}
                  <div className="table-wrapper">
                    <table className="data-table">
                      <thead>
                        <tr>
                          <th>Atracción</th>
                          <th>Estado</th>
                          <th>Visitas</th>
                          <th>Acción</th>
                        </tr>
                      </thead>
                      <tbody>
                        {atracciones.map(atr => (
                          <tr key={atr.id}>
                            <td><strong>{atr.nombre}</strong><br/><small>{atr.tipo}</small></td>
                            <td>
                              <span className={`badge estado-${atr.estado.toLowerCase()}`}>{atr.estado}</span>
                            </td>
                            <td>{atr.contadorVisitantes || 0}</td>
                            <td>
                              {atr.estado === 'ACTIVA' ? (
                                <button className="btn-mini warn" onClick={() => toggleMantenimiento(atr.id, 'iniciar')}>
                                  Iniciar Mantenimiento
                                </button>
                              ) : atr.estado === 'EN_MANTENIMIENTO' ? (
                                <button className="btn-mini success" onClick={() => toggleMantenimiento(atr.id, 'revisar')}>
                                  Revisar y Reactivar
                                </button>
                              ) : (
                                <span style={{ color: 'var(--text-muted)', fontSize: '0.8rem' }}>No disponible</span>
                              )}
                            </td>
                          </tr>
                        ))}
                      </tbody>
                    </table>
                  </div>
                </div>
              )}
            </div>
          </>
        )}

        {selectedRole === 'Visitante' && selectedUser && (
          <>
            <div className="tab-bar">
              <button className={`tab-btn ${visitorTab === 'tickets' ? 'active' : ''}`}
                onClick={() => setVisitorTab('tickets')}>
                🎫 Tickets
              </button>
              <button className={`tab-btn ${visitorTab === 'perfil' ? 'active' : ''}`}
                onClick={() => setVisitorTab('perfil')}>
                ⭐ Mi Perfil
              </button>
            </div>

            <div className="tab-content">
              {visitorTab === 'tickets' && (
                <>
                  <div className="section-card">
                    <h3 className="section-title">💰 Saldo Actual</h3>
                    <p style={{ fontSize: '1.8rem', fontWeight: 700, color: 'var(--accent-3)' }}>
                      ${usuario?.saldoVirtual?.toLocaleString() || '0'}
                    </p>
                    <p style={{ color: 'var(--text-muted)', fontSize: '0.8rem' }}>
                      Este saldo se descuenta al comprar tickets y al usar atracciones con costo adicional.
                    </p>
                  </div>

                  <div className="section-card">
                    <h3 className="section-title">🎫 Comprar Tiquete</h3>
                    <p style={{ color: 'var(--text-muted)', fontSize: '0.85rem', marginBottom: '0.8rem' }}>
                      Compra un tiquete y úsalo para unirte a la fila de cualquier atracción.
                      {usuario && ` Saldo disponible: $${usuario.saldoVirtual?.toLocaleString() || '0'}`}
                    </p>
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
                        <table className="data-table">
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
                    <p style={{ color: 'var(--text-muted)', fontSize: '0.85rem', marginBottom: '0.8rem' }}>
                      Tu saldo actual: <strong>${usuario?.saldoVirtual?.toLocaleString() || '0'}</strong>
                    </p>
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
                            <tr>
                              <th>Atracción</th>
                              <th>Tipo</th>
                              <th>Estado</th>
                            </tr>
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
                        No tienes favoritos aún. Ve a la pestaña 🗺️ Parque y haz clic en 🤍 para agregar.
                      </p>
                    </div>
                  )}

                  {historial.length > 0 ? (
                    <div className="section-card">
                      <h3>📋 Historial de Visitas</h3>
                      <div className="table-wrapper">
                        <table className="data-table">
                          <thead>
                            <tr>
                              <th>Atracción</th>
                              <th>Tipo</th>
                              <th>Estado</th>
                            </tr>
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

        {selectedRole === 'Administrador' && (
          <>
            <div className="tab-bar">
              <button className={`tab-btn ${adminTab === 'reportes' ? 'active' : ''}`}
                onClick={() => setAdminTab('reportes')}>
                📊 Reportes
              </button>
              <button className={`tab-btn ${adminTab === 'gestion' ? 'active' : ''}`}
                onClick={() => setAdminTab('gestion')}>
                🔐 Gestión
              </button>
            </div>

            <div className="tab-content">
              {adminTab === 'reportes' && reporteDiario && (
                <div className="reports-section">
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
                    <div className="popular-section" style={{ marginTop: '1.5rem' }}>
                      <h3>🏆 Atracciones Más Visitadas</h3>
                      <div className="table-wrapper">
                        <table className="data-table">
                          <thead>
                            <tr>
                              <th>#</th>
                              <th>Atracción</th>
                              <th>Tipo</th>
                              <th>Visitas</th>
                              <th>Ingresos</th>
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
                    </div>
                  )}
                </div>
              )}

              {adminTab === 'reportes' && !reporteDiario && (
                <div className="section-card">
                  <h3>📊 Reportes del Parque</h3>
                  <p style={{ color: 'var(--text-muted)', fontSize: '0.9rem' }}>
                    Cargando reportes... Si el problema persiste, haz clic en 🔄 Sincronizar Datos.
                  </p>
                </div>
              )}

              {adminTab === 'gestion' && (
                <div className="section-card">
                  <h3>🔐 Gestión del Parque</h3>
                  <p style={{ color: 'var(--text-muted)', fontSize: '0.85rem', marginBottom: '1rem' }}>
                    Administra el estado del parque, alertas climáticas y personal.
                  </p>
                  {mensajeClima && (
                    <div className={`ticket-mensaje ${mensajeClima.includes('✅') ? 'exito' : 'error'}`}>
                      {mensajeClima}
                    </div>
                  )}
                  <div style={{ display: 'flex', gap: '0.8rem', flexWrap: 'wrap' }}>
                    <button
                      className={`btn-action ${alertaActiva ? 'danger' : ''}`}
                      onClick={toggleAlertaClima}
                      style={alertaActiva ? { background: '#D07070', color: '#fff' } : {}}
                    >
                      {alertaActiva ? '⛔ Desactivar Alerta Climática' : '🌩️ Activar Alerta Climática'}
                    </button>
                    <button className="btn-action" disabled style={{ opacity: 0.6, cursor: 'not-allowed', fontSize: '0.85rem' }}>
                      👥 Gestionar Empleados — Próximamente
                    </button>
                    <button className="btn-action" disabled style={{ opacity: 0.6, cursor: 'not-allowed', fontSize: '0.85rem' }}>
                      🗺️ Gestionar Zonas — Próximamente
                    </button>
                  </div>
                </div>
              )}
            </div>
          </>
        )}
      </main>
    </div>
  )
}

export default App

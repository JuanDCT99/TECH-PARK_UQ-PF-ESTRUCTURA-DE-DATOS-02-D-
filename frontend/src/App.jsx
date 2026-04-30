import { useState } from 'react'
import './App.css'
import bienvenidosImg from './assets/Bienvenidos.png'
import rolImg from './assets/Rol.png'

function App() {
  const [view, setView] = useState('welcome') // 'welcome', 'role-selection', 'dashboard'
  const [selectedRole, setSelectedRole] = useState(null)

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
        <div className="placeholder-card">
          <h3>¡Bienvenido, {selectedRole}!</h3>
          <p>Esta es la base de tu panel de control. Pronto conectaremos la lógica de Java aquí.</p>
          <div className="stats-grid">
            <div className="stat-item"><h4>Estado</h4><p>Activo</p></div>
            <div className="stat-item"><h4>Conexión</h4><p>Pendiente (Java)</p></div>
          </div>
        </div>
      </main>
    </div>
  )
}

export default App

import React, { useState } from 'react';

export const RegistroView = ({ rolSeleccionado, alCambiarDeVista }) => {
  const [formData, setFormData] = useState({
    nombre: '',
    documento: '',
    edad: '',
    estatura: '',
    contrasena: '',
  });

  const [mensaje, setMensaje] = useState({ tipo: '', texto: '' });
  const [cargando, setCargando] = useState(false);

  const rolKey = rolSeleccionado?.toLowerCase() || '';

  const handleChange = (e) => {
    setFormData({ ...formData, [e.target.name]: e.target.value });
  };

  const handleSubmit = async (e) => {
    e.preventDefault();
    setMensaje({ tipo: '', texto: '' });
    setCargando(true);

    const payload = { ...formData, rol: 'visitante' };

    try {
      const response = await fetch('http://localhost:8080/api/auth/registro', {
        method: 'POST',
        headers: { 'Content-Type': 'application/json' },
        body: JSON.stringify(payload),
      });

      const data = await response.json();

      if (response.ok) {
        setMensaje({ tipo: 'exito', texto: '✅ ' + (data.mensaje || 'Registro exitoso') });
        setTimeout(() => alCambiarDeVista('login'), 2000);
      } else {
        setMensaje({ tipo: 'error', texto: '❌ ' + (data.mensaje || 'Error en el registro.') });
      }
    } catch (err) {
      setMensaje({ tipo: 'error', texto: '❌ Error al conectar con el servidor.' });
    } finally {
      setCargando(false);
    }
  };

  if (rolKey !== 'visitante') {
    return (
      <div className="auth-container">
        <div className="auth-card">
          <div className="auth-emoji">🔒</div>
          <h2 className="auth-title">Registro no disponible</h2>
          <p className="auth-subtitle">
            Solo los visitantes pueden crear una cuenta. Si eres empleado o administrador,
            usa las credenciales que te fueron asignadas.
          </p>
          <button className="btn-primary auth-submit" onClick={() => alCambiarDeVista('login')}>
            Volver al Inicio de Sesión
          </button>
        </div>
      </div>
    );
  }

  return (
    <div className="auth-container">
      <div className="auth-card" style={{ maxWidth: '520px' }}>
        <div className="auth-emoji">🎟️</div>
        <h2 className="auth-title">Registro de Visitante</h2>
        <p className="auth-subtitle">Crea tu credencial digital para TECH-PARK UQ</p>

        {mensaje.texto && (
          <div className={`auth-msg ${mensaje.tipo}`}>{mensaje.texto}</div>
        )}

        <form onSubmit={handleSubmit}>
          <div className="form-group">
            <label className="form-label">Nombre Completo</label>
            <input type="text" name="nombre" className="form-input" value={formData.nombre} onChange={handleChange} required />
          </div>

          <div className="form-row">
            <div className="form-group">
              <label className="form-label">Documento de Identidad</label>
              <input type="text" name="documento" className="form-input" value={formData.documento} onChange={handleChange} required />
            </div>
            <div className="form-group">
              <label className="form-label">Contraseña</label>
              <input type="password" name="contrasena" className="form-input" value={formData.contrasena} onChange={handleChange} required />
            </div>
          </div>

          <div className="form-row">
            <div className="form-group">
              <label className="form-label">Edad</label>
              <input type="number" name="edad" className="form-input" value={formData.edad} onChange={handleChange} required />
            </div>
            <div className="form-group">
              <label className="form-label">Estatura (metros)</label>
              <input type="number" step="0.01" name="estatura" className="form-input" value={formData.estatura} onChange={handleChange} required />
            </div>
          </div>

          <button type="submit" className="btn-primary auth-submit" disabled={cargando}>
            {cargando ? '⏳ Registrando...' : 'Crear Cuenta y Entrar'}
          </button>
        </form>

        <button className="auth-link back" onClick={() => alCambiarDeVista('login')}>
          ← Ya tengo cuenta, iniciar sesión
        </button>
      </div>
    </div>
  );
};

export default RegistroView;

import React, { useState } from 'react';

export const RegistroView = ({ rolSeleccionado, alCambiarDeVista }) => {
  const [formData, setFormData] = useState({
    id: '',
    nombre: '',
    correo: '',
    contrasena: '',
    documento: '',
    edad: '',
    estatura: '',
    saldoVirtual: '0',
    codigoEmpleado: '',
    salario: '',
    turno: 'MAÑANA',
    nivelAcceso: '1',
  });

  const [mensaje, setMensaje] = useState({ tipo: '', texto: '' });
  const [cargando, setCargando] = useState(false);

  const rolKey = rolSeleccionado?.toLowerCase() || '';
  const esEmpleado = rolKey === 'empleado' || rolKey === 'operador';
  const esAdmin = rolKey === 'administrador';

  const handleChange = (e) => {
    setFormData({ ...formData, [e.target.name]: e.target.value });
  };

  const handleSubmit = async (e) => {
    e.preventDefault();
    setMensaje({ tipo: '', texto: '' });
    setCargando(true);

    const rolParaBackend = esEmpleado ? 'operador' : rolKey;

    const payload = { ...formData, rol: rolParaBackend };

    try {
      const response = await fetch('http://localhost:8080/api/auth/registro', {
        method: 'POST',
        headers: { 'Content-Type': 'application/json' },
        body: JSON.stringify(payload),
      });

      const data = await response.json();

      if (response.ok) {
        setMensaje({ tipo: 'exito', texto: '✅ ' + data.mensaje });
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

  const rolEmoji = rolKey === 'visitante' ? '🎟️' : esAdmin ? '🔐' : '🛠️';

  return (
    <div className="auth-container">
      <div className="auth-card" style={{ maxWidth: '520px' }}>
        <div className="auth-emoji">{rolEmoji}</div>
        <h2 className="auth-title">Registro de {rolSeleccionado}</h2>
        <p className="auth-subtitle">Crea tu credencial digital única para TECH-PARK UQ</p>

        {mensaje.texto && (
          <div className={`auth-msg ${mensaje.tipo}`}>{mensaje.texto}</div>
        )}

        <form onSubmit={handleSubmit}>
          <div className="form-row">
            <div className="form-group">
              <label className="form-label">ID de Usuario</label>
              <input type="text" name="id" className="form-input" value={formData.id} onChange={handleChange} required />
            </div>
            <div className="form-group">
              <label className="form-label">Nombre Completo</label>
              <input type="text" name="nombre" className="form-input" value={formData.nombre} onChange={handleChange} required />
            </div>
          </div>

          <div className="form-row">
            <div className="form-group">
              <label className="form-label">Correo Electrónico</label>
              <input type="email" name="correo" className="form-input" value={formData.correo} onChange={handleChange} required />
            </div>
            <div className="form-group">
              <label className="form-label">Contraseña</label>
              <input type="password" name="contrasena" className="form-input" value={formData.contrasena} onChange={handleChange} required />
            </div>
          </div>

          {rolKey === 'visitante' && (
            <>
              <div className="form-group">
                <label className="form-label">Documento de Identidad</label>
                <input type="text" name="documento" className="form-input" value={formData.documento} onChange={handleChange} required />
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
              <div className="form-group">
                <label className="form-label">Saldo Virtual Inicial ($)</label>
                <input type="number" name="saldoVirtual" className="form-input" value={formData.saldoVirtual} onChange={handleChange} required />
              </div>
            </>
          )}

          {(esEmpleado || esAdmin) && (
            <div className="form-row">
              <div className="form-group">
                <label className="form-label">Código Empleado</label>
                <input type="text" name="codigoEmpleado" className="form-input" value={formData.codigoEmpleado} onChange={handleChange} required />
              </div>
              <div className="form-group">
                <label className="form-label">Salario Mensual</label>
                <input type="number" name="salario" className="form-input" value={formData.salario} onChange={handleChange} required />
              </div>
            </div>
          )}

          {esEmpleado && (
            <div className="form-group">
              <label className="form-label">Turno Asignado</label>
              <select name="turno" className="form-input" value={formData.turno} onChange={handleChange}>
                <option value="MAÑANA">Mañana</option>
                <option value="TARDE">Tarde</option>
                <option value="NOCHE">Noche</option>
              </select>
            </div>
          )}

          {esAdmin && (
            <div className="form-group">
              <label className="form-label">Nivel de Acceso (1-5)</label>
              <input type="number" min="1" max="5" name="nivelAcceso" className="form-input" value={formData.nivelAcceso} onChange={handleChange} required />
            </div>
          )}

          <button type="submit" className="btn-primary auth-submit" disabled={cargando}>
            {cargando ? '⏳ Registrando...' : 'Registrar y Guardar'}
          </button>
        </form>

        <button className="auth-link back" onClick={() => alCambiarDeVista('login')}>
          ← Volver al Inicio de Sesión
        </button>
      </div>
    </div>
  );
};

export default RegistroView;

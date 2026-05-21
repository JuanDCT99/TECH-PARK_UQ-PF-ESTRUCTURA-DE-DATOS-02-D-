import React, { useState } from 'react';

const ICONOS_POR_ROL = {
  visitante: '🎟️',
  empleado: '🛠️',
  administrador: '🔐',
};

export const LoginView = ({ rolSeleccionado, alIniciarSesion, alCambiarDeVista }) => {
  const [identificador, setIdentificador] = useState('');
  const [contrasena, setContrasena] = useState('');
  const [error, setError] = useState('');
  const [cargando, setCargando] = useState(false);

  const rolKey = rolSeleccionado?.toLowerCase() || '';
  const icono = ICONOS_POR_ROL[rolKey] || '🎟️';
  const soloVisitanteRegistra = rolKey === 'visitante';

  const handleSubmit = async (e) => {
    e.preventDefault();
    setError('');
    setCargando(true);
    try {
      const response = await fetch('http://localhost:8080/api/auth/login', {
        method: 'POST',
        headers: { 'Content-Type': 'application/json' },
        body: JSON.stringify({ identificador, contrasena, rol: rolKey }),
      });
      const data = await response.json();

      if (response.ok) {
        alIniciarSesion(data);
      } else {
        setError(data.mensaje || 'Error al iniciar sesión');
      }
    } catch (err) {
      setError('No se pudo conectar con el servidor del parque.');
    } finally {
      setCargando(false);
    }
  };

  return (
    <div className="auth-container">
      <div className="auth-card">
        <div className="auth-emoji">{icono}</div>
        <h2 className="auth-title">Iniciar Sesión</h2>
        <p className="auth-subtitle">
          Ingresando como <strong className={`auth-role-badge role-${rolKey}`}>{rolSeleccionado}</strong>
        </p>

        {error && <div className="auth-error">{error}</div>}

        <form onSubmit={handleSubmit}>
          <div className="form-group">
            <label className="form-label">ID de Usuario</label>
            <input
              type="text"
              className="form-input"
              value={identificador}
              onChange={(e) => setIdentificador(e.target.value)}
              placeholder="Ej: V1, E1, A1"
              required
            />
          </div>

          <div className="form-group">
            <label className="form-label">Contraseña</label>
            <input
              type="password"
              className="form-input"
              value={contrasena}
              onChange={(e) => setContrasena(e.target.value)}
              placeholder="••••••••"
              required
            />
          </div>

          <button
            type="submit"
            className={`btn-primary auth-submit role-${rolKey}`}
            disabled={cargando}
          >
            {cargando ? '⏳ Verificando...' : `Ingresar como ${rolSeleccionado}`}
          </button>
        </form>

        {soloVisitanteRegistra && (
          <p className="auth-footer">
            ¿No tienes una cuenta?{' '}
            <button className="auth-link" onClick={() => alCambiarDeVista('registro')}>
              Regístrate aquí
            </button>
          </p>
        )}

        <p className="auth-footer" style={{ marginTop: '0.5rem' }}>
          <button className="auth-link back" onClick={() => alCambiarDeVista('role-selection')}>
            ← Volver a selección de rol
          </button>
        </p>
      </div>
    </div>
  );
};

export default LoginView;

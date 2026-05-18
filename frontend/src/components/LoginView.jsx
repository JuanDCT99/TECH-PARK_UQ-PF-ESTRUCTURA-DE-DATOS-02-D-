import React, { useState } from 'react';

export const LoginView = ({ rolSeleccionado, alIniciarSesion, alCambiarDeVista }) => {
  const [id, setId] = useState('');
  const [contrasena, setContrasena] = useState('');
  const [error, setError] = useState('');

  const handleSubmit = async (e) => {
    e.preventDefault();
    setError('');
    try {
      const response = await fetch('http://localhost:8080/api/auth/login', {
        method: 'POST',
        headers: { 'Content-Type': 'application/json' },
        // CAMBIA AQUÍ: Accede mediante props.selectedRole
        body: JSON.stringify({ id, contrasena, rol: props.selectedRole }),
      });
      const data = await response.json();

      if (response.ok) {
        alIniciarSesion(data); // Pasamos los datos del usuario al flujo principal
      } else {
        setError(data.mensaje || 'Error al iniciar sesión');
      }
    } catch (err) {
      setError('No se pudo conectar con el servidor del parque.');
    }
  };

  return (
    <div className="login-container">
      <div className="section-card" style={{ maxWidth: '420px', margin: '0 auto', padding: '2rem' }}>
        <h2 style={{ textAlign: 'center', marginBottom: '0.5rem' }}>Iniciar Sesión</h2>
        <p style={{ textAlign: 'center', color: '#666', marginBottom: '1.5rem' }}>
          Ingresando como: <strong style={{ color: '#ff6b6b', textTransform: 'capitalize' }}>{rolSeleccionado}</strong>
        </p>

        {error && <div style={{ color: 'red', backgroundColor: '#ffe3e3', padding: '0.7rem', borderRadius: '8px', marginBottom: '1rem', fontSize: '0.9rem' }}>{error}</div>}

        <form onSubmit={handleSubmit}>
          <div style={{ marginBottom: '1.2rem' }}>
            <label style={{ display: 'block', marginBottom: '0.4rem', fontWeight: 'bold' }}>Identificación (ID)</label>
            <input 
              type="text" 
              className="form-control" 
              value={id} 
              onChange={(e) => setId(e.target.value)} 
              required 
              style={{ width: '100%', padding: '0.7rem', borderRadius: '8px', border: '1px solid #ccc' }}
            />
          </div>

          <div style={{ marginBottom: '1.5rem' }}>
            <label style={{ display: 'block', marginBottom: '0.4rem', fontWeight: 'bold' }}>Contraseña</label>
            <input 
              type="password" 
              className="form-control" 
              value={contrasena} 
              onChange={(e) => setContrasena(e.target.value)} 
              required 
              style={{ width: '100%', padding: '0.7rem', borderRadius: '8px', border: '1px solid #ccc' }}
            />
          </div>

          <button type="submit" className="btn-primary" style={{ width: '100%', padding: '0.8rem', borderRadius: '25px', backgroundColor: '#ff7a59', color: 'white', border: 'none', fontWeight: 'bold', cursor: 'pointer', marginBottom: '1rem' }}>
            Ingresar al Parque
          </button>
        </form>

        <div style={{ textAlign: 'center', marginTop: '1rem', fontSize: '0.9rem' }}>
          <span>¿No tienes una cuenta? </span>
          <button onClick={() => alCambiarDeVista('registro')} style={{ background: 'none', border: 'none', color: '#ff7a59', fontWeight: 'bold', cursor: 'pointer', padding: 0 }}>
            Regístrate aquí
          </button>
        </div>
      </div>
    </div>
  );
};
import React, { useState } from 'react';

export const RegistroView = ({ rolSeleccionado, alCambiarDeVista }) => {
  // Campos base (Clase abstracta Usuario / Empleado)
  const [formData, setFormData] = useState({
    id: '',
    nombre: '',
    correo: '',
    contrasena: '',
    // Atributos específicos del Visitante
    documento: '',
    edad: '',
    estatura: '',
    saldoVirtual: '0',
    // Atributos específicos de Empleados (Operador / Admin)
    codigoEmpleado: '',
    salario: '',
    // Específico Operador
    turno: 'MAÑANA',
    // Específico Administrador
    nivelAcceso: '1'
  });

  const [mensaje, setMensaje] = useState({ tipo: '', texto: '' });

  const handleChange = (e) => {
    setFormData({ ...formData, [e.target.name]: e.target.value });
  };

  const handleSubmit = async (e) => {
    e.preventDefault();
    setMensaje({ tipo: '', texto: '' });

    // Traducimos 'Empleado' a 'operador' para que Java no lo rechace
    let rolParaBackend = rolSeleccionado;
    if (rolParaBackend?.toLowerCase() === 'empleado') {
      rolParaBackend = 'operador';
    }

    // Armamos el payload incluyendo de manera explícita el rol
    const payload = { ...formData, rol: rolParaBackend };

    try {
      const response = await fetch('http://localhost:8080/api/auth/registro', {
        method: 'POST',
        headers: { 'Content-Type': 'application/json' },
        body: JSON.stringify(payload),
      });

      const data = await response.json();

      if (response.ok) {
        setMensaje({ tipo: 'exito', texto: data.mensaje });
        setTimeout(() => alCambiarDeVista('login'), 2000); // Redirige al login tras dos segundos
      } else {
        setMensaje({ tipo: 'error', texto: data.mensaje || 'Error en el registro.' });
      }
    } catch (err) {
      setMensaje({ tipo: 'error', texto: 'Error al conectar con la base de datos central.' });
    }
  };

  return (
    <div className="registro-container">
      <div className="section-card" style={{ maxWidth: '500px', margin: '0 auto', padding: '2rem' }}>
        <h2 style={{ textAlign: 'center', marginBottom: '0.5rem' }}>Registro de {rolSeleccionado}</h2>
        <p style={{ textAlign: 'center', color: '#666', marginBottom: '1.5rem' }}>Crea tu credencial digital única para TECH-PARK UQ</p>

        {mensaje.texto && (
          <div style={{ 
            color: mensaje.tipo === 'exito' ? 'green' : 'red', 
            backgroundColor: mensaje.tipo === 'exito' ? '#e3fde3' : '#ffe3e3', 
            padding: '0.7rem', borderRadius: '8px', marginBottom: '1rem', fontSize: '0.9rem' 
          }}>
            {mensaje.texto}
          </div>
        )}

        <form onSubmit={handleSubmit} style={{ display: 'grid', gap: '1rem' }}>
          {/* CAMPOS COMUNES A TODOS LOS USUARIOS */}
          <div>
            <label style={{ display: 'block', marginBottom: '0.2rem', fontWeight: 'bold' }}>ID de Usuario</label>
            <input type="text" name="id" value={formData.id} onChange={handleChange} required style={{ width: '100%', padding: '0.6rem', borderRadius: '8px', border: '1px solid #ccc' }} />
          </div>

          <div>
            <label style={{ display: 'block', marginBottom: '0.2rem', fontWeight: 'bold' }}>Nombre Completo</label>
            <input type="text" name="nombre" value={formData.nombre} onChange={handleChange} required style={{ width: '100%', padding: '0.6rem', borderRadius: '8px', border: '1px solid #ccc' }} />
          </div>

          <div>
            <label style={{ display: 'block', marginBottom: '0.2rem', fontWeight: 'bold' }}>Correo Electrónico</label>
            <input type="email" name="correo" value={formData.correo} onChange={handleChange} required style={{ width: '100%', padding: '0.6rem', borderRadius: '8px', border: '1px solid #ccc' }} />
          </div>

          <div>
            <label style={{ display: 'block', marginBottom: '0.2rem', fontWeight: 'bold' }}>Contraseña</label>
            <input type="password" name="contrasena" value={formData.contrasena} onChange={handleChange} required style={{ width: '100%', padding: '0.6rem', borderRadius: '8px', border: '1px solid #ccc' }} />
          </div>

          {/* CAMPOS EXCLUSIVOS SI ES UN VISITANTE */}
          {rolSeleccionado?.toLowerCase() === 'visitante' && (
            <>
              <div>
                <label style={{ display: 'block', marginBottom: '0.2rem', fontWeight: 'bold' }}>Documento de Identidad</label>
                <input type="text" name="documento" value={formData.documento} onChange={handleChange} required style={{ width: '100%', padding: '0.6rem', borderRadius: '8px', border: '1px solid #ccc' }} />
              </div>
              <div style={{ display: 'flex', gap: '1rem' }}>
                <div style={{ flex: 1 }}>
                  <label style={{ display: 'block', marginBottom: '0.2rem', fontWeight: 'bold' }}>Edad</label>
                  <input type="number" name="edad" value={formData.edad} onChange={handleChange} required style={{ width: '100%', padding: '0.6rem', borderRadius: '8px', border: '1px solid #ccc' }} />
                </div>
                <div style={{ flex: 1 }}>
                  <label style={{ display: 'block', marginBottom: '0.2rem', fontWeight: 'bold' }}>Estatura (metros)</label>
                  <input type="number" step="0.01" name="estatura" value={formData.estatura} onChange={handleChange} required style={{ width: '100%', padding: '0.6rem', borderRadius: '8px', border: '1px solid #ccc' }} />
                </div>
              </div>
              <div>
                <label style={{ display: 'block', marginBottom: '0.2rem', fontWeight: 'bold' }}>Cargar Saldo Virtual Inicial ($)</label>
                <input type="number" name="saldoVirtual" value={formData.saldoVirtual} onChange={handleChange} required style={{ width: '100%', padding: '0.6rem', borderRadius: '8px', border: '1px solid #ccc' }} />
              </div>
            </>
          )}

          {/* CAMPOS COMUNES PARA EMPLEADOS (OPERADOR Y ADMINISTRADOR) */}
          {(rolSeleccionado?.toLowerCase() === 'empleado' || rolSeleccionado?.toLowerCase() === 'operador' || rolSeleccionado?.toLowerCase() === 'administrador') && (
            <div style={{ display: 'flex', gap: '1rem' }}>
              <div style={{ flex: 1 }}>
                <label style={{ display: 'block', marginBottom: '0.2rem', fontWeight: 'bold' }}>Código Empleado</label>
                <input type="text" name="codigoEmpleado" value={formData.codigoEmpleado} onChange={handleChange} required style={{ width: '100%', padding: '0.6rem', borderRadius: '8px', border: '1px solid #ccc' }} />
              </div>
              <div style={{ flex: 1 }}>
                <label style={{ display: 'block', marginBottom: '0.2rem', fontWeight: 'bold' }}>Salario Mensual</label>
                <input type="number" name="salario" value={formData.salario} onChange={handleChange} required style={{ width: '100%', padding: '0.6rem', borderRadius: '8px', border: '1px solid #ccc' }} />
              </div>
            </div>
          )}

          {/* CAMPO EXCLUSIVO PARA OPERADORES */}
          {(rolSeleccionado?.toLowerCase() === 'empleado' || rolSeleccionado?.toLowerCase() === 'operador') && (
            <div>
              <label style={{ display: 'block', marginBottom: '0.2rem', fontWeight: 'bold' }}>Turno Asignado</label>
              <select name="turno" value={formData.turno} onChange={handleChange} style={{ width: '100%', padding: '0.6rem', borderRadius: '8px', border: '1px solid #ccc' }}>
                <option value="MAÑANA">Mañana</option>
                <option value="TARDE">Tarde</option>
                <option value="NOCHE">Noche</option>
              </select>
            </div>
          )}

          {/* CAMPO EXCLUSIVO PARA ADMINISTRADORES */}
          {rolSeleccionado === 'administrador' && (
            <div>
              <label style={{ display: 'block', marginBottom: '0.2rem', fontWeight: 'bold' }}>Nivel de Acceso</label>
              <input type="number" min="1" max="5" name="nivelAcceso" value={formData.nivelAcceso} onChange={handleChange} required style={{ width: '100%', padding: '0.6rem', borderRadius: '8px', border: '1px solid #ccc' }} />
            </div>
          )}

          <button type="submit" className="btn-primary" style={{ width: '100%', padding: '0.8rem', borderRadius: '25px', backgroundColor: '#ff7a59', color: 'white', border: 'none', fontWeight: 'bold', cursor: 'pointer', marginTop: '0.5rem' }}>
            Registrar y Guardar
          </button>
        </form>

        <button onClick={() => alCambiarDeVista('login')} style={{ background: 'none', border: 'none', color: '#666', width: '100%', marginTop: '1rem', cursor: 'pointer', textDecoration: 'underline', fontSize: '0.9rem' }}>
          Volver al Inicio de Sesión
        </button>
      </div>
    </div>
  );
};
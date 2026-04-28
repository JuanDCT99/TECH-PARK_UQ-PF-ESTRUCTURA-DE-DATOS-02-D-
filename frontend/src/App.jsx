import { useState, useEffect } from 'react'
import './App.css'

function App() {
  const [backendData, setBackendData] = useState(null)
  const [loading, setLoading] = useState(true)

  useEffect(() => {
    // Intentamos conectar con el servidor Java (Spring Boot)
    // El puerto 8080 es el puerto por defecto de Spring Boot
    fetch('http://localhost:8080/api/test')
      .then(response => {
        if (!response.ok) throw new Error('Error en la respuesta del servidor');
        return response.json();
      })
      .then(data => {
        setBackendData(data);
        setLoading(false);
      })
      .catch(error => {
        console.error('Error conectando con Java:', error);
        setLoading(false);
      })
  }, [])

  return (
    <div style={{ 
      padding: '40px', 
      fontFamily: 'Arial, sans-serif', 
      textAlign: 'center',
      backgroundColor: '#242424',
      color: 'white',
      minHeight: '100vh'
    }}>
      <h1>🎡 Tech Park UQ - Sistema de Gestión</h1>
      <hr style={{ borderColor: '#646cff', width: '50%' }} />
      
      <div style={{ 
        marginTop: '30px', 
        padding: '20px', 
        border: '1px solid #646cff', 
        borderRadius: '10px',
        display: 'inline-block',
        minWidth: '300px'
      }}>
        <h2>Estado del Backend (Java)</h2>
        
        {loading ? (
          <p style={{ color: '#ffd700' }}>⏳ Buscando servidor Java en http://localhost:8080...</p>
        ) : backendData ? (
          <div style={{ textAlign: 'left' }}>
            <p style={{ color: '#4caf50', fontSize: '1.2em' }}>✅ <strong>{backendData.mensaje}</strong></p>
            <p><strong>Proyecto:</strong> {backendData.proyecto}</p>
            <p><strong>Estatus:</strong> {backendData.estado}</p>
          </div>
        ) : (
          <div style={{ color: '#f44336' }}>
            <p>❌ <strong>No se pudo conectar con Java</strong></p>
            <p style={{ fontSize: '0.9em' }}>Asegúrate de ejecutar <code>mvn spring-boot:run</code> en la carpeta <code>ProyectCode</code></p>
          </div>
        )}
      </div>

      <p style={{ marginTop: '40px', color: '#888' }}>
        Arquitectura: Java (Spring Boot) + React (Vite)
      </p>
    </div>
  )
}

export default App

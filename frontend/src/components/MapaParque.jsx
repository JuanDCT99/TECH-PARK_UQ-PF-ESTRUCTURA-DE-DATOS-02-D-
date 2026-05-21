import React from 'react';

/**
 * Componente MapaParque: Visualiza el Grafo del parque usando SVG.
 * 
 * @param {Array} atracciones - Lista de atracciones (nodos)
 * @param {Array} senderos - Lista de conexiones (aristas)
 * @param {Object} rutaResaltada - Resultado de Dijkstra (camino de IDs y peso)
 * @param {Function} onNodoClick - Función al hacer click en una atracción
 */
const MapaParque = ({ atracciones, senderos, rutaResaltada, onNodoClick }) => {
  const width = 600;
  const height = 500;

  // Función para saber si un sendero (arista) es parte de la ruta resaltada
  const esParteDeRuta = (id1, id2) => {
    if (!rutaResaltada || !rutaResaltada.camino) return false;
    const camino = rutaResaltada.camino;
    for (let i = 0; i < camino.length - 1; i++) {
      if ((camino[i] === id1 && camino[i + 1] === id2) || (camino[i] === id2 && camino[i + 1] === id1)) {
        return true;
      }
    }
    return false;
  };

  return (
    <div className="mapa-container">
      <svg width={width} height={height} viewBox={`0 0 ${width} ${height}`} className="mapa-svg">
        {/* Dibujar Aristas (Senderos) */}
        {senderos.map((sendero, index) => {
          const a1 = atracciones.find(a => a.id === sendero.origen);
          const a2 = atracciones.find(a => a.id === sendero.destino);
          if (!a1 || !a2) return null;

          const resaltado = esParteDeRuta(a1.id, a2.id);

          return (
            <g key={`trail-${index}`}>
              <line 
                x1={a1.x} y1={a1.y} 
                x2={a2.x} y2={a2.y} 
                className={`trail-line ${resaltado ? 'highlighted' : ''}`}
              />
              <text 
                x={(a1.x + a2.x) / 2} 
                y={(a1.y + a2.y) / 2 - 5} 
                className="trail-weight"
              >
                {sendero.peso}m
              </text>
            </g>
          );
        })}

        {/* Dibujar Nodos (Atracciones) */}
        {atracciones.map(atr => (
          <g 
            key={atr.id} 
            className="node-group" 
            onClick={() => onNodoClick(atr)}
            style={{ cursor: 'pointer' }}
          >
            <circle 
              cx={atr.x} cy={atr.y} r="15" 
              className={`node-circle estado-${atr.estado.toLowerCase()} ${rutaResaltada?.camino?.includes(atr.id) ? 'node-highlight' : ''}`} 
            />
            <text x={atr.x} y={atr.y + 30} className="node-label" textAnchor="middle">
              {atr.nombre}
            </text>
            <text x={atr.x} y={atr.y + 5} className="node-id" textAnchor="middle">
              {atr.id}
            </text>
          </g>
        ))}
      </svg>
      {rutaResaltada && (
        <div className="ruta-info">
          <strong>Ruta Óptima:</strong> {rutaResaltada.camino.join(' → ')} <br/>
          <strong>Distancia Total:</strong> {rutaResaltada.pesoTotal} metros
        </div>
      )}
    </div>
  );
};

export default MapaParque;

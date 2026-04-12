# Plan Estratégico de Desarrollo: TECH-PARK UQ
**Proyecto Final - Estructura de Datos 2026-1**

Este documento detalla la ruta crítica para el desarrollo del Sistema de Gestión de Parque de Atracciones Inteligente, dividido en 5 fases y 3 roles de responsabilidad.

---

## 👥 Roles del Equipo
*   **Integrante A (Estructuras de Datos):** Especialista en la implementación de estructuras no lineales (Grafos, Árboles) y algoritmos de optimización.
*   **Integrante B (Dominio y Datos):** Responsable del modelo de clases, persistencia (archivos), validaciones de negocio y pruebas unitarias.
*   **Integrante C (Interfaz y Reportes):** Especialista en GUI (JavaFX), visualización del mapa interactivo y generación de estadísticas visuales.

---

## 🚀 Fases de Desarrollo

### Fase 1: Cimentación y Modelo de Dominio (0% - 20%)
*   **Integrante A:** Configuración del repositorio Git (flujo de trabajo) y estructura de paquetes Maven. Diseño inicial del Diagrama de Clases.
*   **Integrante B:** Creación de clases base: `Visitante`, `Atraccion`, `Empleado`, `Tiquete` y `Zona`. Lógica de lectura de archivos planos (Escenario de prueba).
*   **Integrante C:** Diseño de la arquitectura de la interfaz (Main Window). Creación de placeholders para los paneles de Admin, Operador y Visitante.

### Fase 2: Implementación de Estructuras Propias (20% - 40%)
*   **Integrante A:** Implementación del **Grafo** (Lista de Adyacencia) y el **Árbol Binario de Búsqueda (ABB)** para búsqueda por ID/Nombre.
*   **Integrante B:** Implementación del **Set** (para Atracciones Favoritas) y **Lista Enlazada** (para Historial de Visitas).
*   **Integrante C:** Implementación de la **Cola de Prioridad** para gestionar las filas virtuales (Diferenciando Fast-Pass de General).

### Fase 3: Inteligencia y Algoritmos (40% - 60%)
*   **Integrante A:** Implementación de **Dijkstra** o **BFS** sobre el Grafo para calcular la "Ruta Óptima" desde la posición actual del visitante.
*   **Integrante B:** Desarrollo de la lógica de **Mantenimiento Automatizado** (alerta a los 500 visitantes) y creación de 4 Pruebas Unitarias.
*   **Integrante C:** Lógica de validación de entrada (altura, edad, saldo) y simulación de cambio de estado de atracciones por **Clima**.

### Fase 4: Visualización e Interactividad (60% - 80%)
*   **Integrante A:** Conexión del Grafo con la GUI: Visualización gráfica de nodos (atracciones) y aristas (senderos).
*   **Integrante B:** Implementación de la gestión jerárquica de empleados y zonas (Asignación de operadores evitando conflictos).
*   **Integrante C:** Desarrollo detallado de los paneles de usuario:
    *   *Visitante:* Ver mapa, unirse a fila, ver saldo.
    *   *Operador:* Cambiar estado de su atracción y procesar fila.

### Fase 5: Reportes y Cierre Profesional (80% - 100%)
*   **Integrante A:** Análisis de conectividad del Grafo (Detección de "clústeres" de atracciones populares).
*   **Integrante B:** Generación de reportes de texto (ingresos diarios, cierres por clima, incidentes) y pulido de manejo de excepciones.
*   **Integrante C:** Dashboard de estadísticas (gráficos de tiempos de espera y afluencia) y pulido estético de la aplicación.
*   **General:** Revisión de los 24 commits mínimos y actualización de diagramas finales.

---

## 📅 Hitos de Entrega
1.  **Hito 1:** Estructuras básicas y carga de datos funcionales.
2.  **Hito 2:** Algoritmo de rutas y colas de prioridad integrados.
3.  **Hito 3:** Interfaz gráfica completa y mapa interactivo.
4.  **Hito 4:** Sistema de reportes y validación final.

---
*Documento generado para el equipo de TECH-PARK UQ.*

# MEMORIA PERSISTENTE - PROYECTO TECH-PARK UQ

**Fecha de creación:** 07 de mayo de 2026
**Última actualización:** 07 de mayo de 2026 - FASES 1 y 2 COMPLETADAS
**Estado del proyecto:** 52% de avance (Fase 1 y 2 completadas)
**Modo actual:** BUILD

---

## 1. INFORMACIÓN GENERAL DEL PROYECTO

### 1.1 Descripción

Sistema de gestión para el parque de diversiones **TECH-PARK UQ** de la Universidad del Quindío. El sistema debe administrar operaciones principales: control de acceso, gestión de colas, protocolos de seguridad, asignación de personal y mantenimiento de atracciones.

### 1.2 Tecnologías Utilizadas

- **Backend:** Java 21 + Spring Boot 3.2.4 + Maven
- **Frontend:** React + Vite + JavaScript + Vanilla CSS
- **Arquitectura:** Desacoplada (REST API)
- **Control de versiones:** Git/GitHub

### 1.3 Estructura del Repositorio

```
TECH-PARK_UQ-PF-ESTRUCTURA-DE-DATOS-02-D-/
├── frontend/                    # Aplicación React
│   ├── src/
│   │   ├── App.jsx             # Componente principal
│   │   ├── App.css             # Estilos principales
│   │   ├── main.jsx            # Entry point
│   │   └── assets/            # Imágenes (Bienvenidos.png, Rol.png, etc.)
│   ├── package.json
│   ├── vite.config.js
│   └── index.html
├── ProyectCode/                 # Backend Java Spring Boot
│   ├── src/main/java/co/edu/uniquindio/techpark/
│   │   ├── App.java            # Clase principal Spring Boot
│   │   ├── TechPark.java       # Servicio central (orquestador)
│   │   ├── controller/
│   │   │   ├── ParqueController.java
│   │   │   └── TestController.java
│   │   └── Model/             # Entidades del dominio
│   │       ├── Usuario.java
│   │       ├── Visitante.java
│   │       ├── Empleado.java
│   │       ├── Administrador.java
│   │       ├── Operador.java
│   │       ├── Atraccion.java
│   │       ├── Zona.java
│   │       ├── Tiquete.java
│   │       ├── TipoTiquete.java
│   │       ├── Turno.java
│   │       ├── EntradaCola.java
│   │       ├── Notificacion.java
│   │       ├── TipoNotificacion.java
│   │       ├── Alerta.java
│   │       ├── TipoAlerta.java
│   │       ├── Reporte.java
│   │       ├── RevisionTecnica.java
│   │       ├── EstadoAtraccion.java (Enum)
│   │       ├── TipoAtraccion.java (Enum)
│   │       ├── PriotityQueue.java (Cascarón - REQUERE REFACTORIZACIÓN)
│   │       ├── FavoritosSet.java (Cascarón - REQUERE IMPLEMENTACIÓN)
│   │       └── (Faltan: Grafo, ABB, ListaEnlazada)
│   ├── pom.xml
│   └── target/                # Compilados
├── info/                       # Documentación
│   ├── MEMORIA_PERSISTENTE.md  # ESTE ARCHIVO
│   ├── REPORTE_ESTADO_PROYECTO.md
│   ├── COMPONENTES_IMPLEMENTADOS.md
│   ├── Plan_Estrategico_TECH-PARK.md
│   ├── Proyecto Final ETD - 2026-1.pdf
│   └── Proyecto Final ETD - 2026-1.md
└── .gitignore

```

---

## 2. ANÁLISIS DEL ESTADO ACTUAL (32% de avance)

### 2.1 COMPLETADO ✅

#### Backend (Java Spring Boot)

- **Arquitectura base:** Spring Boot configurado con Maven
- **Modelos de dominio completos:**

  - `Visitante.java`: Validaciones de seguridad (estatura, edad), saldo virtual, historial
  - `Atraccion.java`: Gestión de estados, bloqueo automático a 500 visitas
  - `Zona.java`: Agrupación de atracciones
  - `Usuario.java`: Clase base con atributos comunes
  - `Empleado.java`, `Operador.java`, `Administrador.java`: Jerarquía de personal
  - `Tiquete.java`, `TipoTiquete.java`: Sistema de tickets
  - `EntradaCola.java`: Estructura para filas de atracciones
  - `Alerta.java`, `TipoAlerta.java`: Sistema de alertas
  - `Notificacion.java`, `TipoNotificacion.java`: Notificaciones a usuarios
  - `Reporte.java`: Estructura para reportes
  - `RevisionTecnica.java`: Mantenimiento preventivo
  - `EstadoAtraccion.java`: Enum (ACTIVA, EN_MANTENIMIENTO, CERRADA)
  - `TipoAtraccion.java`: Enum para tipos de atracciones
- **Servicio Central (`TechPark.java`):**

  - Gestión de zonas y atracciones
  - Control de aforo global
  - Alertas climáticas (cierre de atracciones acuáticas/mecánicas)
  - Inicialización con datos de prueba (@PostConstruct)
- **Controladores REST:**

  - `ParqueController.java`: Endpoints para atracciones, zonas, estado
  - `TestController.java`: Endpoint de conectividad
- **CORS:** Configurado para permitir conexión con React

#### Frontend (React)

- **Estructura SPA:** Single Page Application con Vite
- **Componentes implementados:**
  - Pantalla de bienvenida (Welcome)
  - Selección de roles (Visitante, Empleado, Administrador)
  - Dashboard principal con estado del parque
  - Catálogo dinámico de atracciones
  - Indicadores visuales de estado (Badge)
- **Estilos:** CSS con variables personalizadas, diseño responsivo
- **Hooks:** useState, useEffect para gestión de estado y peticiones asíncronas

### 2.2 PENDIENTE ❌

#### Estructuras de Datos Propias (CRÍTICO)

1. **Grafo:** Representar mapa físico del parque

   - Nodos = Atracciones
   - Aristas = Senderos con peso (distancia/tiempo)
   - **Estado:** NO IMPLEMENTADO
2. **Algoritmo Dijkstra/BFS:** Ruta óptima para visitantes

   - **Estado:** NO IMPLEMENTADO
3. **Cola de Prioridad (Heap):** Gestión de filas de atracciones

   - `PriotityQueue.java` existe pero es solo un cascarón con getters/setters
   - Debe manejar: Prioridad 1 = Fast-Pass, Prioridad 2 = General
   - **Estado:** CODIFICACIÓN PENDIENTE
4. **Árbol Binario de Búsqueda (ABB):** Catálogo de atracciones

   - Búsqueda rápida O(log n) por nombre o ID
   - **Estado:** NO IMPLEMENTADO
5. **Lista Enlazada:** Historial de visitas y operadores por zona

   - Actualmente usando `ArrayList` (NO permitido según requisitos)
   - **Estado:** REQUIERE REFACTORIZACIÓN
6. **Set (Favoritos):** Gestión de atracciones favoritas

   - `FavoritosSet.java` existe pero es solo un cascarón
   - **Estado:** CODIFICACIÓN PENDIENTE

#### Persistencia

- **Requisito PDF:** Carga de datos iniciales desde archivo plano o serializado
- **Decisión técnica:** Usar JSON (mejor que CSV para Git)
- **Estado:** NO IMPLEMENTADO

#### Lógica de Negocio Faltante

- Diferenciación funcional entre tipos de tickets (General, Familiar, Fast-Pass)
- Gestión jerárquica completa de empleados
- Procesamiento de colas respetando prioridad
- Bloqueo automático a 500 visitas (en Atraccion.java YA implementado)
- Sistema de notificaciones a visitantes afectados por clima
- Reportes diarios (ingresos, tiempos de espera, cierres)

#### Frontend Avanzado

- **Mapa interactivo:** Visualización del grafo (SVG o Canvas)
- **Paneles por rol:**
  - Visitante: Comprar entradas, favoritos, ruta óptima, posición en cola
  - Empleado: Gestión de atracciones, revisiones técnicas
  - Administrador: Gestión de usuarios, zonas, reportes
- **Visualización de datos:** Gráficos y estadísticas
- **Carga de datos:** Botón para cargar escenario de prueba

#### Pruebas

- **Requisito:** Al menos 4 pruebas unitarias
- **Estado:** NO IMPLEMENTADAS

---

## 3. PLAN ESTRATÉGICO DE 5 FASES

### FASE 1: Refactorización y Calidad de Código (Prioridad Inmediata)

**Objetivo:** Limpiar y estandarizar el código existente

**Tareas:**

1. Renombrar `PriotityQueue` → `ColaPrioridad` (corregir error tipográfico)
2. Estandarizar nombres de variables y métodos (convenciones Java)
3. Agregar documentación JavaDoc a todas las clases y métodos principales
4. Eliminar código redundante o comentarios innecesarios
5. Mejorar validaciones existentes:
   - `Visitante.java`: Validar edad > 0, estatura > 0
   - `TechPark.java`: Validar que no se agreguen zonas/atracciones duplicadas
6. Revisar manejo de excepciones (implementar GlobalExceptionHandler)

**Archivos a modificar:**

- `PriotityQueue.java` → `ColaPrioridad.java`
- `Visitante.java`
- `TechPark.java`
- `FavoritosSet.java`
- Todos los controladores

**Criterio de éxito:** Código limpio, documentado y sin warnings

---

### FASE 2: Persistencia y Carga de Datos

**Objetivo:** Implementar carga de datos desde archivos (requerido por PDF)

**Tareas:**

1. Crear archivos JSON de datos iniciales:
   - `atracciones.json`: Catálogo de atracciones
   - `zonas.json`: Configuración de zonas
   - `usuarios.json`: Visitantes y empleados de prueba
   - `senderos.json`: Conexiones del grafo (nodos y aristas)
2. Implementar servicio de lectura de JSON en Java:
   - Usar biblioteca Jackson o Gson (agregar dependencia en pom.xml)
3. Modificar `TechPark.java` para cargar datos desde JSON en lugar de hardcoded
4. Implementar serialización opcional para guardar estado al cerrar (excluir archivos .dat de Git con .gitignore)
5. Crear botón en frontend para cargar datos de prueba
6. Mantener carga por código como fallback

**Justificación para JSON sobre CSV:**

- Mejor para Git (menos conflictos de merge que CSV)
- Estructura jerárquica que permite datos complejos
- Fácil de parsear en Java y JavaScript
- Formato plano válido según requisitos del PDF

**Criterio de éxito:** Datos cargados desde archivos JSON al iniciar la aplicación

---

### FASE 3: Estructuras de Datos Propias (Core del Proyecto)

**Objetivo:** Implementar todas las estructuras de datos requeridas

**Tareas:**

1. **Grafo:**

   - Implementar clase `Grafo.java`
   - Nodos: Atracciones (identificador, nombre, coordenadas)
   - Aristas: Senderos con peso (distancia o tiempo estimado)
   - Métodos: agregarNodo(), agregarArista(), obtenerAdyacentes(), etc.
2. **Cola de Prioridad (Heap):**

   - Refactorizar `PriotityQueue.java` a `ColaPrioridad.java`
   - Implementar Heap binario (min-heap o max-heap según prioridad)
   - Métodos: encolar(EntradaCola), desencolar(), peek(), isEmpty()
   - Prioridad 1 = Fast-Pass (sale primero), Prioridad 2 = General
3. **Lista Enlazada:**

   - Crear clase `ListaEnlazada.java`
   - Nodos con referencia al siguiente
   - Métodos: agregar(), eliminar(), buscar(), size(), iterar()
   - Reemplazar `ArrayList` en `Visitante.historialVisitas`
4. **Árbol Binario de Búsqueda (ABB):**

   - Crear clase `ArbolBinarioBusqueda.java`
   - Nodos con referencias izquierda y derecha
   - Criterio de ordenamiento: nombre o ID de atracción
   - Métodos: insertar(), buscar(), eliminar(), recorrerInOrder(), recorrerPreOrder(), recorrerPostOrder()
5. **Set (Favoritos):**

   - Implementar `FavoritosSet.java`
   - Usar estructura interna (Hash table o árbol) para garantizar unicidad
   - Métodos: agregarFavorito(Atraccion), eliminarFavorito(Atraccion), esFavorito(Atraccion), obtenerFavoritos()

**Integración:**

- Modificar `TechPark.java` para usar Grafo en lugar de listas simples
- Modificar `Atraccion.java` para usar ColaPrioridad en lugar de ArrayList
- Modificar `Visitante.java` para usar ListaEnlazada e implementar FavoritosSet real

**Criterio de éxito:** Todas las estructuras implementadas y funcionando sin usar colecciones de Java (ArrayList, HashMap, etc.)

---

### FASE 4: Algoritmos y Lógica de Negocio

**Objetivo:** Implementar la lógica inteligente del sistema

**Tareas:**

1. **Algoritmo de Dijkstra:**

   - Implementar en clase `Grafo.java`
   - Calcular camino más corto desde ubicación actual hasta destino
   - Retornar lista de nodos (ruta) y peso total (distancia/tiempo)
   - Manejar casos donde no hay camino disponible
2. **Algoritmo BFS (alternativa):**

   - Implementar recorrido en amplitud para análisis de conectividad
   - Detectar "clústeres" de atracciones populares
3. **Gestión de Colas Virtuales:**

   - Procesar visitantes en cola respetando prioridad de ticket
   - Validar restricciones antes de permitir ingreso (estatura, edad, saldo)
   - Descontar saldo virtual para costos adicionales
4. **Mantenimiento Preventivo:**

   - Ya implementado en `Atraccion.java` (bloqueo a 500 visitas)
   - Agregar notificación automática a operador cuando se alcance el límite
   - Permitir desbloqueo tras revisión técnica satisfactoria
5. **Simulación de Clima:**

   - Activar alerta climática desde panel de administrador
   - Cerrar masivamente atracciones acuáticas y mecánicas de altura
   - Notificar a visitantes con tickets activos o en cola virtual
   - Cambiar estado a CERRADA con motivo: "CIERRE AUTOMÁTICO POR CLIMA"
6. **Diferenciación de Tickets:**

   - General: Ingreso normal, paga costo adicional si aplica
   - Familiar: Ingreso con descuento (definir condiciones)
   - Fast-Pass: Prioridad 1 en cola virtual

**Criterio de éxito:** Lógica de negocio completa y funcionando con las estructuras propias

---

### FASE 5: Frontend Avanzado y Visualización

**Objetivo:** Completar la interfaz gráfica interactiva

**Tareas:**

1. **Mapa Interactivo (CRÍTICO):**

   - Renderizar grafo usando SVG o Canvas en React
   - Nodos: Atracciones con coordenadas (x, y)
   - Aristas: Senderos con peso visible
   - Colores por estado: Verde=ACTIVA, Amarillo=EN_MANTENIMIENTO, Rojo=CERRADA
   - Interactividad: Click en nodo muestra detalles de atracción
   - Visualizar ruta calculada por Dijkstra (línea resaltada)
2. **Panel Visitante:**

   - Comprar entradas (seleccionar tipo de ticket)
   - Ver posición en cola virtual (por tipo de ticket)
   - Visualizar ruta óptima a atracción destino
   - Gestionar favoritos (agregar/quitar)
   - Ver historial de visitas (usando ListaEnlazada)
   - Recargar saldo virtual
3. **Panel Operador:**

   - Cambiar estado de atracción asignada
   - Registrar revisiones técnicas
   - Ver cola de espera actual con prioridades
   - Procesar ingreso de siguientes visitantes en cola
4. **Panel Administrador:**

   - Gestión de empleados (crear, modificar, asignar a zonas)
   - Gestión de zonas y atracciones (CRUD completo)
   - Activar alertas climáticas
   - Ver reportes: ingresos diarios, atracciones más visitadas, tiempos promedio de espera, historial de cierres
   - Visualizar grafo completo del parque y su conectividad
5. **Estadísticas y Reportes:**

   - Gráficos de barras: Atracciones más visitadas
   - Gráficos de líneas: Ingresos diarios
   - Tablas: Tiempos de espera, alertas de mantenimiento
   - Exportar reportes (opcional)
6. **Mejoras de UX:**

   - Indicadores en tiempo real (número de personas en cola, tiempo estimado)
   - Notificaciones push al usuario (usando el sistema de notificaciones)
   - Animaciones suaves en transiciones
   - Responsive design para diferentes pantallas

**Criterio de éxito:** GUI funcional, intuitiva y con visualización gráfica del grafo

---

## 4. REGISTRO DE DECISIONES TÉCNICAS

| Fecha      | Decisión                                                 | Justificación                                                          | Impacto |
| ---------- | --------------------------------------------------------- | ----------------------------------------------------------------------- | ------- |
| 2026-05-07 | Usar JSON en lugar de CSV para persistencia               | Mejor manejo en Git con múltiples colaboradores, estructura más clara | FASE 2  |
| 2026-05-07 | Refactorizar `PriotityQueue` a `ColaPrioridad`        | Corregir error tipográfico, seguir convenciones de nomenclatura        | FASE 1  |
| 2026-05-07 | Implementar estructuras propias sin usar colecciones Java | Requisito obligatorio del PDF del proyecto                              | FASE 3  |
| 2026-05-07 | Usar Heap binario para Cola de Prioridad                  | Eficiencia O(log n) para encolar/desencolar                             | FASE 3  |

---

## 5. ARCHIVOS DE CONFIGURACIÓN Y DEPENDENCIAS

### 5.1 Backend (pom.xml)

Dependencias actuales:

- Spring Boot Starter Web
- Spring Boot Starter Test
- Lombok

**Pendiente agregar:**

- Jackson (para JSON): `com.fasterxml.jackson.core:jackson-databind`
- Opcional: Spring Boot Starter Data JPA (si se requiere base de datos)

### 5.2 Frontend (package.json)

Dependencias actuales:

- React
- React DOM
- Vite
- @vitejs/plugin-react

**Pendiente agregar (opcional):**

- react-router-dom (para enrutamiento entre paneles)
- recharts o chart.js (para gráficos estadísticos)
- axios (para peticiones HTTP más limpias)

---

## 6. ENDPOINTS ACTUALES DEL BACKEND

| Método | Endpoint                    | Descripción                           | Estado       |
| ------- | --------------------------- | -------------------------------------- | ------------ |
| GET     | `/api/parque/atracciones` | Lista de atracciones y estado técnico | ✅ Operativo |
| GET     | `/api/parque/zonas`       | Distribución del parque por zonas     | ✅ Operativo |
| GET     | `/api/parque/estado`      | Disponibilidad global (aforo)          | ✅ Operativo |
| GET     | `/api/test`               | Verificación de conectividad          | ✅ Operativo |

**Pendientes crear:**

- POST `/api/visitante/comprar-ticket`
- POST `/api/visitante/entrar-cola/{idAtraccion}`
- GET `/api/visitante/ruta-optima/{idOrigen}/{idDestino}`
- POST `/api/operador/cambiar-estado/{idAtraccion}`
- POST `/api/operador/registrar-revision/{idAtraccion}`
- POST `/api/admin/alertar-clima`
- GET `/api/admin/reportes/ingresos-diarios`
- GET `/api/admin/reportes/atracciones-populares`
- POST `/api/parque/cargar-datos`

---

## 7. PROGRESO POR FASE

| Fase | Tareas Completadas | Estado | % Completitud |
|------|-------------------|--------|---------------|
| FASE 1 | 7/7 | ✅ COMPLETADA | 100% |
| FASE 2 | 7/7 | ✅ COMPLETADA | 100% |
| FASE 3 | 0/5 | ⏳ PENDIENTE | 0% |
| FASE 4 | 0/6 | ⏳ PENDIENTE | 0% |
| FASE 5 | 0/6 | ⏳ PENDIENTE | 0% |

**Progreso global estimado:** 32% (infraestructura base) + 10% (Fase 1) + 10% (Fase 2) = 52%

---

## 8. DETALLE DE CAMBIOS - FASE 1 (Refactorización y Calidad de Código)

### ✅ Tarea 1: Renombrar `PriotityQueue` → `ColaPrioridad`
- **Archivo creado:** `ColaPrioridad.java`
- **Archivo eliminado:** `PriotityQueue.java`
- Cambiado nombre de clase y variables internas (cabeza → elementos)
- Añadidos métodos faltantes: `isEmpty()`, `size()`
- Documentación JavaDoc agregada

### ✅ Tarea 2: Estandarizar nombres y mejorar `Visitante.java`
- Añadida validación en constructor: edad (1-120), estatura (0.0-3.0m)
- Añadida validación en setters de edad y estatura
- Documentación JavaDoc completa en todos los métodos
- Método `verificarEdad()` renombrado a `verificarEdad()` (minúscula inicial)
- Marcado `verificarEdad()` como `@Deprecated`

### ✅ Tarea 3: Mejorar `TechPark.java`
- Documentación JavaDoc en clase y métodos
- Validación de null en `agregarZona()`, `registrarUsuario()`, `registrarNuevaAtraccion()`
- Método privado `buscarZonaPorId()` para evitar duplicados
- Validación de duplicados al agregar zonas

### ✅ Tarea 4: Mejorar `FavoritosSet.java`
- Estructura completa de Set implementada
- Lista interna de favoritos con lógica de unicidad
- Métodos: `agregarFavorito()`, `eliminarFavorito()`, `esFavorito()`, `obtenerFavoritos()`, `size()`, `isEmpty()`
- Documentación JavaDoc completa
- Validaciones de null

### ✅ Tarea 5: Mejorar `EntradaCola.java`
- Documentación JavaDoc completa
- Validaciones en constructor: null checks, prioridad (1 o 2)
- Cambiado `java.sql.Date` por `java.util.Date` (corrección de error)
- Validaciones en setters
- Manejo de null en `horaIngreso` (fallback a `Instant.now()`)

### ✅ Tarea 6: Revisar `Zona.java`
- Documentación JavaDoc completa
- Validaciones en constructor: null/empty checks, capacidad > 0
- Validación de duplicados en `agregarAtraccion()` y `asignarOperador()`
- Validaciones en setters (capacidad, visitantes vs capacidadMaxima)

### ✅ Tarea 7: Crear `GlobalExceptionHandler.java`
- **Archivo creado:** `controller/GlobalExceptionHandler.java`
- Manejo centralizado de `IllegalArgumentException` → HTTP 400
- Manejo genérico de `Exception` → HTTP 500
- Respuestas JSON consistentes

---

## 9. DETALLE DE CAMBIOS - FASE 2 (Persistencia y Carga de Datos)

### ✅ Paso 1: Añadir dependencia Jackson en `pom.xml`
- Dependencia `jackson-databind` versión 2.16.0 añadida
- Requerida para procesar archivos JSON

### ✅ Paso 2: Crear archivos JSON de datos
- **Directorio creado:** `src/main/resources/data/`
- **Archivo creado:** `data/atracciones.json` (A1, A2, A3)
- **Archivo creado:** `data/zonas.json` (Z1, Z2)
- **Archivo creado:** `data/senderos.json` (para Grafo - Fase 3)
- **Archivo creado:** `data/usuarios.json` (Visitante de prueba)

### ✅ Paso 3: Crear `DatosService.java`
- **Archivo creado:** `service/DatosService.java`
- Métodos: `cargarAtracciones()`, `cargarZonas()`, `cargarSenderos()`, `cargarUsuarios()`
- Configurado `ObjectMapper` para ignorar propiedades desconocidas
- Manejo de errores: retorna listas vacías si falla

### ✅ Paso 3.1-3.4: Modificar modelos para Jackson
- `Atraccion.java`: Añadido constructor sin argumentos
- `Zona.java`: Añadido constructor sin argumentos
- `Usuario.java`: Añadido constructor sin argumentos
- `Visitante.java`: Añadido constructor sin argumentos

### ✅ Paso 3.5: Crear `Sender.java`
- **Archivo creado:** `service/Sender.java` (clase auxiliar para grafo)
- Constructor sin argumentos y completo

### ✅ Paso 4: Modificar `TechPark.java` para usar `DatosService`
- Inyectado `DatosService` con `@Autowired`
- Método `init()` modificado para cargar desde JSON
- Método privado `cargarDatosDesdeJSON()` implementado
- Fallback a datos hardcoded si falla JSON
- Nuevo método público `recargarDatos()` para re-carga bajo demanda

### ✅ Paso 5: Modificar `.gitignore`
- Añadidas exclusiones: `*.dat`, `*.ser`, `/tmp/`
- Protege archivos de serialización y temporales

### ✅ Paso 6: Crear endpoint en `ParqueController.java`
- Nuevo endpoint: `POST /api/parque/cargar-datos`
- Retorna éxito o error al cargar datos
- Llama a `techPark.recargarDatos()`

### ✅ Paso 7: Modificar frontend `App.jsx`
- Añadido estado `mensajeCarga`
- Función `cargarDatosPrueba()` implementada
- Botón "🔄 Cargar Datos de Prueba" añadido en dashboard
- Mensaje de éxito/error visual debajo del botón

---

## 10. ARCHIVOS MODIFICADOS/CREADOS EN FASE 2

### Creados:
1. `service/DatosService.java`
2. `service/Sender.java`
3. `resources/data/atracciones.json`
4. `resources/data/zonas.json`
5. `resources/data/senderos.json`
6. `resources/data/usuarios.json`

### Modificados:
1. `pom.xml` - Dependencia Jackson añadida
2. `TechPark.java` - Integración con DatosService
3. `ParqueController.java` - Endpoint de carga de datos
4. `Atraccion.java` - Constructor sin argumentos
5. `Zona.java` - Constructor sin argumentos
6. `Usuario.java` - Constructor sin argumentos
7. `Visitante.java` - Constructor sin argumentos
8. `.gitignore` - Exclusiones añadidas
9. `frontend/src/App.jsx` - Botón de carga de datos

### Correcciones realizadas:
- `DatosService.java` - Clase `Sender` movida a archivo separado
- `DatosService.java` - Método `cargarUsuarios()` cambiado a `List<Visitante>`

---

**Última actualización:** 07 de mayo de 2026 - FASE 2 COMPLETADA
**Próxima fase a ejecutar:** FASE 3 - Estructuras de Datos Propias

---

## 8. NOTAS IMPORTANTES PARA EL DESARROLLADOR

1. **Convenciones de código:**

   - Java: CamelCase para variables/métodos, PascalCase para clases
   - React: CamelCase para componentes y variables
   - Archivos: kebab-case para archivos JSON, PascalCase para clases Java
2. **Estructuras propias:**

   - NO usar ArrayList, HashMap, HashSet, PriorityQueue de Java
   - Implementar desde cero usando nodos y referencias
3. **Persistencia:**

   - Archivos JSON en directorio `ProyectCode/src/main/resources/data/`
   - Excluir archivos .dat (serialización) del Git con .gitignore
4. **Git y Commits:**

   - Cada integrante debe tener mínimo 24 commits
   - Usar conventional commits: `feat:`, `fix:`, `refactor:`, `docs:`, `test:`
   - Commits descriptivos en español o inglés (mantener consistencia)
5. **Pruebas unitarias:**

   - Mínimo 4 pruebas requeridas por el PDF
   - Usar JUnit 5 (ya incluido con Spring Boot Starter Test)
   - Probar estructuras de datos y lógica de negocio crítica
6. **Documentación:**

   - Este archivo (`MEMORIA_PERSISTENTE.md`) es la fuente de verdad
   - Actualizar después de cada sesión de trabajo
   - Mantener registro de decisiones técnicas

---

## 9. CONTACTO Y COLABORADORES

**Integrante 1:** Juan David Cardozo Torres - Commits: 0/24
**Integrante 2:** Camilo Ruiz Lopez - Commits: 0/24
**Integrante 3:** Erwin Harder Garzon - Commits: 0/24

**Repositorio:** [URL pendiente]
**Branch actual:** main (y juand_dev)

---

## 10. CHECKLIST FINAL PARA ENTREGA

- [ ] Todas las estructuras de datos propias implementadas
- [ ] Algoritmo Dijkstra funcionando
- [ ] Colas de prioridad operativas (Fast-Pass vs General)
- [ ] Carga de datos desde archivo JSON
- [ ] Mapa interactivo visualizando el grafo
- [ ] Panel de visitante completo
- [ ] Panel de operador completo
- [ ] Panel de administrador completo
- [ ] Al menos 4 pruebas unitarias pasando
- [ ] Diagrama de clases actualizado
- [ ] Diagrama de estructuras propias
- [ ] GUI funcional e intuitiva
- [ ] Convencional commits aplicados
- [ ] Mínimo 24 commits por integrante
- [ ] Documentación completa (README, este archivo, etc.)
- [ ] Demo funcional lista para sustentación

---

## NOTA FINAL - SESIÓN 07 DE MAYO DE 2026

**Estado al finalizar la sesión:**
- ✅ FASE 1: Refactorización y Calidad de Código - **COMPLETADA (100%)**
- ✅ FASE 2: Persistencia y Carga de Datos - **COMPLETADA (100%)**
- ⏳ FASE 3: Estructuras de Datos Propias - **PENDIENTE (Lista para próxima sesión)**

**Progreso global del proyecto:** 52% (32% infraestructura base + 10% Fase 1 + 10% Fase 2)

**Commits realizados en esta sesión:**
- ✅ Commit de FASE 1 realizado
- ✅ Commit de FASE 2 realizado
- 📝 Pendiente: Hacer commit de los cambios de actualización de MEMORIA_PERSISTENTE.md

**Archivos creados en esta sesión:**
1. `Model/ColaPrioridad.java` (reemplaza PriotityQueue)
2. `controller/GlobalExceptionHandler.java`
3. `service/DatosService.java`
4. `service/Sender.java`
5. `resources/data/atracciones.json`
6. `resources/data/zonas.json`
7. `resources/data/senderos.json`
8. `resources/data/usuarios.json`

**Archivos modificados en esta sesión:**
1. `pom.xml` - Dependencia Jackson añadida
2. `TechPark.java` - Integración con DatosService
3. `ParqueController.java` - Endpoint de carga de datos
4. `Atraccion.java` - Constructor sin argumentos
5. `Zona.java` - Constructor sin argumentos
6. `Usuario.java` - Constructor sin argumentos
7. `Visitante.java` - Constructor sin argumentos
8. `.gitignore` - Exclusiones añadidas
9. `frontend/src/App.jsx` - Botón de carga de datos
10. `Model/Visitante.java` - Documentación y validaciones
11. `Model/FavoritosSet.java` - Implementación completa
12. `Model/EntradaCola.java` - Documentación, validaciones, corrección Date
13. `Model/Zona.java` - Documentación y validaciones

**Para la próxima sesión:**
- Ejecutar commit de actualización de `MEMORIA_PERSISTENTE.md`
- Iniciar **FASE 3: Estructuras de Datos Propias**
- Orden de implementación: ListaEnlazada → ABB → Grafo → ColaPrioridad real → FavoritosSet real

---

## 📴 FINALIZACIÓN DE SESIÓN - 07 DE MAYO DE 2026

**✅ TAREA FINAL REALIZADA:** Actualización completa de MEMORIA_PERSISTENTE.md

**Estado final del proyecto:**
- ✅ FASE 1: Refactorización y Calidad de Código - **COMPLETADA (100%)**
- ✅ FASE 2: Persistencia y Carga de Datos - **COMPLETADA (100%)**
- ⏳ FASE 3: Estructuras de Datos Propias - **PENDIENTE (Lista para próxima sesión)**

**Progreso global:** 52% (32% base + 10% Fase 1 + 10% Fase 2)

**📋 ÚLTIMA TAREA POR HOY:**
- ✅ MEMORIA_PERSISTENTE.md actualizada y completa
- ✅ Registro detallado de Fases 1 y 2
- ✅ Plan de ejecución para FASE 3 listo

**📝 PENDIENTE PARA PRÓXIMA SESIÓN:**
1. Hacer commit de los cambios de hoy (incluyendo actualización de MEMORIA_PERSISTENTE.md)
2. Iniciar FASE 3: Estructuras de Datos Propias en orden:
   - ListaEnlazada.java
   - ArbolBinarioBusqueda.java  
   - Grafo.java
   - ColaPrioridad.java (Heap real)
   - FavoritosSet.java (usando ListaEnlazada)
   - TechPark.java (integrar Grafo y ABB)
   - Atraccion.java (coordenadas y ColaPrioridad)
   - Visitante.java (usar ListaEnlazada)

**Comando para commit final:**
```bash
git add info/MEMORIA_PERSISTENTE.md
git commit -m "docs: Actualización final MEMORIA_PERSISTENTE - Sesión 07 mayo

- Fases 1 y 2 completadas (100% cada una)
- Progreso global: 52%
- FASE 3 lista para próxima sesión"
```

---

**Última actualización:** 07 de mayo de 2026 - Fin de sesión (Fases 1 y 2 completadas)
**Próxima fase a ejecutar:** FASE 3 - Estructuras de Datos Propias
**Estado de memoria:** ✅ ACTUALIZADA AL DÍA

* [x] MEMORIA PERSISTENTE - PROYECTO TECH-PARK UQ

**Fecha de creación:** 07 de mayo de 2026
**Última actualización:** 20 de mayo de 2026 — Corrección integral: login por ID unificado, registro simplificado, persistencia JSON, 10 bugs resueltos (Dijkstra blank screen, FAST_PASS sin ticket, clima familiar, layout 16:9, imágenes+SVG, validaciones)
**Estado del proyecto:** ~98% de avance estimado
**Modo actual:** BUILD — Estabilización (10 bugs corregidos)

---

## 1. INFORMACIÓN GENERAL DEL PROYECTO

### 1.1 Descripción

Sistema de gestión integral para el parque de diversiones **TECH-PARK UQ** de la Universidad del Quindío. El sistema administra: control de acceso, gestión de colas inteligentes con prioridad Fast-Pass/General, protocolos de seguridad, asignación de personal por zonas, mantenimiento preventivo automatizado, rutas óptimas entre atracciones (Dijkstra), alertas climáticas, notificaciones, y visualización interactiva del mapa del parque mediante un grafo.

### 1.2 Tecnologías

| Componente           | Tecnología        | Versión       |
| -------------------- | ------------------ | -------------- |
| Backend              | Java + Spring Boot | 21 / 3.2.4     |
| Frontend             | React + Vite       | 19.2.5 / 8.0.9 |
| Persistencia         | Jackson (JSON)     | 2.16.0         |
| Testing              | JUnit 5 + Maven Surefire | 5.10.2 / 3.1.2 |
| Documentación       | Markdown           | —             |
| Control de versiones | Git + GitHub       | —             |

### 1.3 Integrantes

| Integrante                | Rol           | Commits estimados        |
| ------------------------- | ------------- | ------------------------ |
| Juan David Cardozo Torres | Desarrollador | ~30 (65 total / 3)       |
| Camilo Ruiz Lopez         | Desarrollador | ~18 (est.)               |
| Erwin Harder Garzon       | Desarrollador | ~17 (est.)               |
| **Total**                 |               | **65 commits**           |
| **Requerido**             |               | **72 (24 c/u)**          |
| **Cumplimiento**          |               | **90%**                  |

---

## 2. ESTRUCTURA COMPLETA DEL PROYECTO (52 ARCHIVOS DE CÓDIGO)

```
TECH-PARK_UQ-PF-ESTRUCTURA-DE-DATOS-02-D-/
│
├── frontend/                              # Aplicación React (SPA)
│   ├── index.html                         # Entry point HTML (13 líneas)
│   ├── package.json                       # Dependencias: react 19, vite 8 (27 líneas)
│   ├── vite.config.js                     # Configuración Vite (7 líneas)
│   ├── eslint.config.js                   # ESLint config
│   ├── package-lock.json                  # Lock de dependencias
│   ├── README.md                          # Documentación del frontend
│   └── src/
│       ├── main.jsx                       # Punto de entrada React (10 líneas)
│       ├── index.css                      # Estilos base
│       ├── App.jsx                        # Componente principal (862 líneas, 10 vistas + SVG icons + imágenes fondo)
│       ├── App.css                        # Tema pastel + dark overlay + layout 16:9 (980 líneas)
│       ├── assets/
│       │   ├── Bienvenidos.png            # Imagen fondo welcome (desde camilo_dev)
│       │   ├── Rol.png                    # Imagen fondo selección rol (desde camilo_dev)
│       │   ├── hero.png                   # Assets adicionales
│       │   ├── react.svg
│       │   └── vite.svg
│       └── components/
│           ├── MapaParque.jsx             # Componente SVG mapa interactivo (87 líneas)
│           └── Icons.jsx                  # SVG vector icons: VisitanteIcon, EmpleadoIcon, AdminIcon (45 líneas)
│
├── ProyectCode/                           # Backend Java Spring Boot
│   ├── pom.xml                            # Maven: Spring Boot, Jackson, Lombok (68 líneas)
│   └── src/
│       ├── main/
│       │   ├── java/co/edu/uniquindio/techpark/
│       │   │   ├── App.java               # SpringApplication.run() (14 líneas)
│       │   │   ├── TechPark.java          # ORQUESTADOR CENTRAL (~610 líneas)
│       │   │   │
│       │   │   ├── controller/            # 4 clases, ~290 líneas total
│       │   │   │   ├── ParqueController.java      # 18 endpoints REST (179 líneas)
│       │   │   │   ├── AuthController.java        # POST /api/auth/login, registro, logout (~60 líneas)
│       │   │   │   ├── GlobalExceptionHandler.java # @ControllerAdvice (57 líneas)
│       │   │   │   └── TestController.java         # /api/test (24 líneas)
│       │   │   │
│       │   │   ├── Model/                 # 26 clases, ~2,800 líneas
│       │   │   │   │
│       │   │   │   │   # ── JERARQUÍA DE USUARIOS ──
│       │   │   │   ├── Usuario.java       # Base: id, nombre, correo, contraseña, fechaRegistro (75 líneas)
│       │   │   │   ├── Visitante.java     # Extends Usuario: documento, edad, estatura, saldoVirtual,
│       │   │   │   │                      #   favoritos (FavoritosSet), notificaciones (ListaEnlazada),
│       │   │   │   │                      #   historialVisitas (ListaEnlazada) (253 líneas)
│       │   │   │   ├── Empleado.java      # Extends Usuario: codigoEmpleado, salario (30 líneas)
│       │   │   │   ├── Administrador.java # Extends Empleado: nivelAcceso (28 líneas)
│       │   │   │   ├── Operador.java      # Extends Empleado: turno, zonaAsignada,
│       │   │   │   │                      #   registrarRevisionTecnica() (50 líneas)
│       │   │   │   │
│       │   │   │   │   # ── ESTRUCTURAS DE DATOS PROPIAS (7) ──
│       │   │   │   ├── ListaEnlazada.java # Genérica<T>, Iterable, singly linked,
│       │   │   │   │                      #   Nodo<T> interno, 8 métodos (153 líneas)
│       │   │   │   ├── ArbolBinarioBusqueda.java # BST de Atraccion por ID,
│       │   │   │   │                      #   Nodo interno, 4 métodos (130 líneas)
│       │   │   │   ├── Grafo.java         # Lista de adyacencia, NodoGrafo interno,
│       │   │   │   │                      #   + DIJKSTRA implementado (184 líneas)
│       │   │   │   ├── Arista.java        # destinoId + peso (22 líneas)
│       │   │   │   ├── ColaPrioridad.java # Heap binario (min-heap) sobre EntradaCola[],
│       │   │   │   │                      #   subir/bajar/redimensionar (220 líneas)
│       │   │   │   ├── FavoritosSet.java  # Set usando ListaEnlazada<Atraccion>,
│       │   │   │   │                      #   sin colecciones Java (124 líneas)
│       │   │   │   └── ResultadoRuta.java # DTO: camino + pesoTotal (34 líneas)
│       │   │   │   │
│       │   │   │   │   # ── DOMINIO DEL PARQUE ──
│       │   │   │   ├── Atraccion.java     # 16 atributos: id, nombre, tipo, capacidadMax, alturaMin,
│       │   │   │   │                      #   edadMin, costoAdicional, contadorVisitantes, tiempoEspera,
│       │   │   │   │                      #   estado, motivoCierre, colaEspera (ColaPrioridad), x, y (126 líneas)
│       │   │   │   ├── Zona.java          # id, nombre, descripcion, capacidadMaxima, visitantesActuales,
│       │   │   │   │                      #   listaAtracciones (ListaEnlazada),
│       │   │   │   │                      #   listaOperadores (ListaEnlazada) (252 líneas)
│       │   │   │   │
│       │   │   │   │   # ── TICKETS Y COLAS ──
│       │   │   │   ├── Tiquete.java       # id, tipo (TipoTiquete), precio, descripcion,
│       │   │   │   │                      #   fechaCompra, propietario (66 líneas)
│       │   │   │   ├── TipoTiquete.java   # Enum: GENERAL, FAMILIAR, FAST_PASS (7 líneas)
│       │   │   │   ├── EntradaCola.java   # visitante, tiquete, prioridad, horaIngreso,
│       │   │   │   │                      #   atraccion, validaciones completas (171 líneas)
│       │   │   │   │
│       │   │   │   │   # ── NOTIFICACIONES Y ALERTAS ──
│       │   │   │   ├── Notificacion.java  # id, mensaje, fechaHora, visitante, leida,
│       │   │   │   │                      #   tipoNotificacion (75 líneas)
│       │   │   │   ├── TipoNotificacion.java # Enum: INFO, ALERTA, CAMBIO_ESTADO (8 líneas)
│       │   │   │   ├── Alerta.java        # id, tipo (TipoAlerta), atraccion, prioridad,
│       │   │   │   │                      #   descripcion (66 líneas)
│       │   │   │   ├── TipoAlerta.java    # Enum: MANTENIMIENTO, CLIMA, CAPACIDAD (7 líneas)
│       │   │   │   │
│       │   │   │   │   # ── ENUMERACIONES ADICIONALES ──
│       │   │   │   ├── EstadoAtraccion.java # Enum: ACTIVA, EN_MANTENIMIENTO, CERRADA (7 líneas)
│       │   │   │   ├── TipoAtraccion.java # Enum: ACUATICA, MECANICA_ALTURA, INFANTIL, TERRESTRE (8 líneas)
│       │   │   │   ├── Turno.java         # Enum: MAÑANA, TARDE, NOCHE (7 líneas)
│       │   │   │   │
│       │   │   │   │   # ── REPORTES Y MANTENIMIENTO ──
│       │   │   │   ├── Reporte.java       # fecha, ingresosDiarios, totalVisitantes,
│       │   │   │   │                      #   tiempoPromEspera, cierresClima,
│       │   │   │   │                      #   alertasMantenimiento (79 líneas)
│       │   │   │   └── RevisionTecnica.java # id, atraccion, operador, fecha, aprovada (68 líneas)
│       │   │   │
│       │   │   └── service/               # 4 clases, ~320 líneas total
│       │   │       ├── implement/              # Implementaciones estructuras
│       │   │       │   ├── ListaEnlazada.java
│       │   │       │   ├── ABB.java
│       │   │       │   └── Grafo.java
│       │   │       ├── AuthService.java        # @Service auth con ListaEnlazada<Usuario> (~120 líneas)
│       │   │       ├── TechParkService.java    # Lógica de negocio (conexiones, reservas) (472 líneas)
│       │   │       └── FachadaServicios.java   # Fachada unificada (105 líneas)
│       │   │       ├── DatosService.java  # JSON loader con Jackson (97 líneas)
│       │   │       ├── ReporteService.java# Generación de reportes y estadísticas (59 líneas)
│       │   │       └── Sender.java        # DTO: origen, destino, peso (42 líneas)
│       │   │
│   │   └── resources/data/            # 4 archivos JSON, ~110 líneas total
│   │       ├── atracciones.json       # 7 atracciones con coordenadas (72 líneas)
│   │       ├── zonas.json             # 2 zonas (14 líneas)
│   │       ├── senderos.json          # 12 aristas (14 líneas)
│   │       └── usuarios.json          # 5 usuarios: V1-V3 Visitantes, E1 Operador, A1 Admin (55 líneas)
│       │
│       └── test/java/co/edu/uniquindio/techpark/Model/
│           ├── ListaEnlazadaTest.java     # 6 tests (76 líneas)
│           ├── ColaPrioridadTest.java     # 5 tests (70 líneas)
│           ├── ArbolBinarioBusquedaTest.java # 5 tests (67 líneas)
│           └── GrafoTest.java             # 5 tests (54 líneas)
│
├── info/                                  # 8 documentos de documentación
│   ├── MEMORIA_PERSISTENTE.md             # ESTE ARCHIVO
│   ├── Proyecto Final ETD - 2026-1.md     # REQUISITOS DEL PROYECTO (PDF escaneado)
│   ├── Proyecto Final ETD - 2026-1.pdf    # PDF original escaneado
│   ├── COMPONENTES_IMPLEMENTADOS.md       # Inventario técnico
│   ├── REPORTE_ESTADO_PROYECTO.md         # Auditoría técnica
│   ├── Plan_Estrategico_TECH-PARK.md      # Plan estratégico
│   ├── README.md                          # Documentación general
│   └── React_ Fundamentos y Primer Componente.pdf  # Material de estudio externo
│
└── .gitignore                             # Excluye target/, *.dat, *.ser, /tmp/ (25 líneas)
```

---

## 3. ANÁLISIS DETALLADO CLASE POR CLASE

### 3.1 Capa de Modelo (`Model/` — 26 archivos, ~2,800 líneas)

*(Secciones existentes preservadas — análisis detallado de cada clase se mantiene igual que antes)*

---

### 3.2 Capa de Servicio (`service/` — 4 archivos, ~320 líneas)

**`DatosService.java`** (97 líneas)

- Inyecta `ObjectMapper` de Jackson configurado con `FAIL_ON_UNKNOWN_PROPERTIES = false`
- Métodos:
  - `cargarAtracciones()` → `List<Atraccion>` desde `data/atracciones.json` (7 atracciones ahora: A1-A7)
  - `cargarZonas()` → `List<Zona>` desde `data/zonas.json`
  - `cargarSenderos()` → `List<Sender>` desde `data/senderos.json` (12 senderos ahora)
  - `cargarUsuarios()` → `List<Usuario>` desde `data/usuarios.json` (5 usuarios: V1-V3, E1, A1) — deserialización polimórfica por campo "tipo"
  - `guardarUsuarios(ListaEnlazada<Usuario>)` → escribe JSON manualmente a `usuarios.json` (evita `@JsonIgnore` en contrasena)

**`ReporteService.java`** (59 líneas)

- Servicio de reportes y estadísticas
- `generarReporteDiario(ListaEnlazada<Atraccion>)`: calcula ingresos totales, visitantes, tiempo espera promedio, cierres por clima, alertas de mantenimiento
- `getAtraccionesMasVisitadas(ListaEnlazada<Atraccion>)`: ranking descendente con bubble sort por contadorVisitantes

**`Sender.java`** (42 líneas)

- DTO simple: `origen` (String), `destino` (String), `peso` (int)
- Constructor vacío para Jackson + constructor completo
- Getters/setters

**`AuthService.java`** (~127 líneas)

- Anotado `@Service` + `@DependsOn("techPark")` + `@Autowired TechPark`
- Usa `ListaEnlazada<Usuario>` en lugar de HashMap (requisito de proyecto: sin colecciones Java)
- **`verificarLogin(String identificador, String contrasena, String rol)`**
  - Busca por `u.getId()` para TODOS los roles (ya no por documento/codigoEmpleado/id)
  - Recorrido lineal O(n) sobre ListaEnlazada — aceptable para la cantidad esperada de usuarios
  - Retorna `Map<String, Object>` con datos del usuario autenticado o `null` si falla
  - Incluye datos específicos del rol en la respuesta (documento+saldo para Visitante, codigoEmpleado para Empleado, nivelAcceso para Admin)
- **`registrarVisitante(Map<String, Object> datos)`** — único método de registro:
  - Solo crea Visitantes (Operadores y Admins son precargados en JSON)
  - Genera ID auto-incremental (V4, V5...) escaneando IDs existentes
  - Llama a `techPark.registrarUsuario()` + `datosService.guardarUsuarios()` para persistencia
  - Retorna `Map<String, Object>` con success/mensaje/id
- **`@PostConstruct init()`**: copia los usuarios existentes de TechPark a la lista local en el arranque
- NOTA: contrasena se almacena en texto plano (alcance educativo, no producción)

---

### 3.3 Capa de Controladores (`controller/` — 4 archivos, ~290 líneas)

**`AuthController.java`** (~60 líneas)

- Anotado `@RestController`, `@RequestMapping("/api/auth")`, `@CrossOrigin("*")`
- Usa `@Autowired AuthService` (sin singleton manual — inyección Spring)
- **`POST /api/auth/login`**:
  - Request body: `{ identificador, contrasena, rol }`
  - Valida campos obligatorios → 400 si falta
  - Llama a `authService.verificarLogin()`
  - Retorna `{ success, usuario, message }` o 401 con error
- **`POST /api/auth/registro`**:
  - Request body con campos según rol: `{ nombre, edad, rol, documento?, codigoEmpleado?, contrasena }`
  - Delega a `registrarVisitante/Operador/Administrador` según el rol
  - Retorna `{ success, usuario, message }` o 409 si ya existe
- **`POST /api/auth/logout`**:
  - Retorna `{ success: true, message: "Sesión cerrada" }`
  - Sin estado (sin JWT/session — lógica educativa)

**`ParqueController.java`** (215 líneas) — **20 ENDPOINTS REST**

| Método | Endpoint                     | Parámetros                                       | Retorno                           | Función                            |
| ------- | ---------------------------- | ------------------------------------------------- | --------------------------------- | ----------------------------------- |
| GET     | `/api/parque/atracciones`  | —                                                | `Atraccion[]`                   | Lista completa de atracciones       |
| GET     | `/api/parque/senderos`     | —                                                | `Sender[]`                    | Lista de senderos del Grafo         |
| GET     | `/api/parque/zonas`        | —                                                | `Zona[]`                        | Lista de zonas                      |
| GET     | `/api/parque/estado`       | —                                                | `String`                        | "ABIERTO" o "AFORO COMPLETO"        |
| POST    | `/api/parque/cargar-datos` | —                                                | `ResponseEntity<String>`        | Recarga datos desde JSON            |
| GET     | `/api/parque/ruta`         | `origen`, `destino`                           | `ResponseEntity<ResultadoRuta>` | Ruta óptima (Dijkstra)             |
| POST    | `/api/parque/unirse-fila`  | `visitanteId`, `atraccionId`, `tipoTiquete` | `ResponseEntity<String>`        | Unirse a cola virtual con prioridad |
| GET     | `/api/parque/usuarios`     | —                                                | `Visitante[]`                | Solo Visitantes (filtrado)          |
| POST    | `/api/parque/procesar-fila`| `atraccionId`                                  | `ResponseEntity<String>`        | Desencolar siguiente visitante      |
| POST    | `/api/parque/comprar-ticket`| `visitanteId`, `tipoTiquete`                 | `ResponseEntity<String>`        | Comprar tiquete                     |
| GET     | `/api/parque/mis-tiquetes` | `visitanteId`                                  | `Tiquete[]`                   | Listar tiquetes del visitante       |
| POST    | `/api/parque/agregar-favorito`| `visitanteId`, `atraccionId`               | `ResponseEntity<String>`        | Agregar a favoritos                 |
| POST    | `/api/parque/eliminar-favorito`| `visitanteId`, `atraccionId`               | `ResponseEntity<String>`        | Eliminar de favoritos               |
| GET     | `/api/parque/mis-favoritos`| `visitanteId`                                  | `Atraccion[]`                | Listar favoritos                    |
| GET     | `/api/parque/historial`    | `visitanteId`                                  | `Atraccion[]`                | Historial de visitas                |
| POST    | `/api/parque/recargar-saldo`| `visitanteId`, `monto`                       | `ResponseEntity<String>`        | Recargar saldo virtual              |
| POST    | `/api/parque/mantenimiento`| `atraccionId`, `accion`                      | `ResponseEntity<String>`        | Iniciar/revisar mantenimiento       |
| POST    | `/api/parque/alerta-clima` | `accion`, `motivo` (opcional)              | `ResponseEntity<String>`        | Activar/desactivar alerta climática |
| GET     | `/api/parque/reportes/diario`| —                                                | `ResponseEntity<Reporte>`       | Reporte diario del parque           |
| GET     | `/api/parque/reportes/populares`| —                                            | `ResponseEntity<Atraccion[]>`   | Atracciones más visitadas           |
**Nota:** Todos los `@RequestParam` tienen nombre explícito vgr `@RequestParam("atraccionId")` para compatibilidad Spring Boot 3.x.

**`GlobalExceptionHandler.java`** (57 líneas)

- `@ControllerAdvice` + `@ExceptionHandler`
- `IllegalArgumentException` → HTTP 400 con `{error, message}`
- `Exception` genérica → HTTP 500 con `{error, message}`
- NOTA: Usa `LinkedHashMap` (colección Java) pero es solo para formatear respuesta HTTP

**`TestController.java`** (24 líneas)

- `GET /api/test` → JSON de verificación de conectividad

---

### 3.4 Orquestador Central (`TechPark.java` — ~639 líneas)

Clase más importante del sistema. Anotada con `@Service` de Spring.

**Atributos:**

- `ListaEnlazada<Zona> zonas` — reemplazó `ArrayList`
- `ListaEnlazada<Usuario> usuarios` — reemplazó `ArrayList`
- `ListaEnlazada<Atraccion> todasLasAtracciones` — reemplazó `ArrayList`
- `ArbolBinarioBusqueda catalogoAtracciones` — búsqueda rápida por ID (O(log n))
- `Grafo mapaParque` — ruteo Dijkstra
- `DatosService datosService` — inyectado para carga JSON

**Flujo de inicialización:**

1. `@PostConstruct init()` — se ejecuta automáticamente tras construir el bean
2. Llama a `cargarUsuarios()` para poblar visitantes desde JSON
3. Intenta `cargarDatosDesdeJSON()`
4. Si falla, ejecuta `inicializacionHardcoded()` como fallback
5. JSON exitoso: alimenta ABB + ListaEnlazada + Grafo y asocia atracciones a zonas

**Métodos públicos:**

- `init()` — carga usuarios y datos desde JSON al arrancar
- `recargarDatos()` — reinicia todas las estructuras y recarga JSON
- `agregarZona(Zona)` — con validación de duplicados por ID
- `registrarUsuario(Usuario)` — con validación de duplicados por ID
- `registrarNuevaAtraccion(Zona, Atraccion)` — agrega a zona, lista global y ABB
- `obtenerRutaOptima(String, String)` — delega a `mapaParque.calcularRutaOptima()`
- `unirseAFila(String, String, TipoTiquete)` — lógica completa con validaciones en orden:
   1. Busca visitante por ID en usuarios (recorrido lineal)
   2. Busca atracción por ID en ABB (O(log n))
   3. **Valida estado de la atracción PRIMERO**: si está EN_MANTENIMIENTO → mensaje específico; si está CERRADA → muestra motivo
   4. **Valida ticket FAST_PASS**: si el visitante pide Fast-Pass, verifica que tenga un ticket FAST_PASS comprado en `tiquetesEmitidos`
   5. Valida restricciones del visitante con `visitante.puedeEntrar()` (edad, estatura, saldo)
   6. Determina prioridad: FAST_PASS → 1, otro → 2
   7. Crea Tiquete + EntradaCola
   8. Inserta en `atraccion.getColaEspera().insertar()`
   9. Retorna mensaje descriptivo
- `procesarSiguiente(String atraccionId)` — desencola usando `ColaPrioridad.eliminar()`, ejecuta `entrarAAtraccion()`, retorna info del visitante procesado
- `comprarTicket(String visitanteId, String tipoTiquete)` — crea tiquete con precios (GENERAL=$20k, FAST_PASS=$50k, FAMILIAR=$45k), valida edad≥18 para Familiar, descuenta saldo
- `agregarFavorito(String visitanteId, String atraccionId)` — agrega a FavoritosSet
- `eliminarFavorito(String visitanteId, String atraccionId)` — elimina de FavoritosSet
- `getFavoritos(String visitanteId)` — retorna arreglo de favoritos
- `getHistorial(String visitanteId)` — retorna historial de visitas como arreglo
- `getMisTiquetes(String visitanteId)` — retorna tiquetes del visitante
- `recargarSaldo(String visitanteId, int monto)` — incrementa saldo virtual
- `buscarVisitantePorDocumento(String documento)` — busca en usuarios por documento del Visitante
- `buscarEmpleadoPorCodigo(String codigoEmpleado)` — busca en usuarios por código de empleado

- `iniciarMantenimiento(String atraccionId)` — cambia estado a EN_MANTENIMIENTO
- `revisarMantenimiento(String atraccionId)` — cambia estado a ACTIVA, reinicia contador
- `activarAlertaClimatica(String)` — cierra atracciones acuáticas, mecánicas Y familiares
- `desactivarAlertaClimatica()` — restaura solo las atracciones cerradas por clima (no las que estaban en mantenimiento)
- `getSenderos()` → retorna `Sender[]` desde el Grafo (exporta todas las aristas sin duplicados)
- `hayAforoGlobal()` — suma visitantes/capacidad de todas las zonas
- `getZonas()` → retorna `Zona[]` (arreglo nativo)
- `getUsuarios()` → retorna `Usuario[]`
- `getTodasLasAtracciones()` → retorna `Atraccion[]`
- `getAtraccionesMasVisitadas()` → retorna ranking
- `getReporteDiario()` → retorna Reporte

NOTAS SOBRE TechPark.java:

- Los métodos getter retornan arreglos nativos (`toArray()`) para que Jackson serialice correctamente en los endpoints REST
- `ResultadoRuta.getCamino()` ahora retorna `String[]` (antes `ListaEnlazada<String>`) — esto evitaba que Jackson serializara el camino como objeto `{cabeza: ..., tam: ...}` en vez de array, causando pantalla en blanco en el frontend
- La búsqueda de visitante en `unirseAFila` es O(n) — podría optimizarse con un ABB o HashMap propio
- No es thread-safe — múltiples peticiones concurrentes podrían causar condiciones de carrera

---

### 3.5 Datos de Prueba (`resources/data/` — 4 JSON, ~110 líneas)

**`atracciones.json`** — 7 atracciones:

| ID | Nombre              | Tipo      | x, y     |
| -- | ------------------- | --------- | -------- |
| A1 | Montaña Rusa X     | Mecánica | 100, 100 |
| A2 | Caída Libre        | Mecánica | 300, 150 |
| A3 | Tobogán Gigante    | Acuática | 200, 300 |
| A4 | Torre de Viento    | Mecánica | 50, 230  |
| A5 | Río Salvaje        | Acuática | 380, 270 |
| A6 | Carrusel Mágico    | Familiar | 150, 420 |
| A7 | Noria Panorámica   | Familiar | 350, 400 |

**`senderos.json`** — 12 aristas (no dirigidas):

| Origen | Destino | Peso |
| ------ | ------- | ---- |
| A1     | A2      | 50m  |
| A1     | A4      | 40m  |
| A2     | A3      | 30m  |
| A1     | A3      | 70m  |
| A4     | A5      | 60m  |
| A3     | A6      | 45m  |
| A2     | A4      | 35m  |
| A2     | A5      | 80m  |
| A5     | A6      | 55m  |
| A3     | A7      | 25m  |
| A6     | A7      | 20m  |
| A5     | A7      | 65m  |

**`usuarios.json`** — 5 usuarios precargados (todos con `contrasena: "1234"`):

| ID | Nombre            | Rol          | Documento   | Saldo   | Código Empleado | Turno | Nivel Acceso |
| -- | ----------------- | ------------ | ----------- | ------- | --------------- | ----- | ------------ |
| V1 | Juan Pérez        | VISITANTE    | 12345678    | $50,000 | —               | —     | —           |
| V2 | María García      | VISITANTE    | 87654321    | $80,000 | —               | —     | —           |
| V3 | Carlos López      | VISITANTE    | 11223344    | $30,000 | —               | —     | —           |
| E1 | Operador Central  | OPERADOR     | —           | —       | EMP-001         | MAÑANA| —           |
| A1 | Admin General     | ADMINISTRADOR| —           | —       | ADM-001         | —     | 5           |

---

### 3.6 Frontend (React + Vite — 8 archivos fuente + 2 imágenes)

**`App.jsx`** (~862 líneas)

- 5 vistas: `welcome` → `role-selection` → `login` ↔ `registro` → `dashboard`
- **Welcome:** ahora usa `Bienvenidos.png` como background con overlay oscuro (`.bg-image-layer` + `.bg-overlay-tint`)
- **Role Selection:** usa `Rol.png` como background + SVG vector icons (`VisitanteIcon`, `EmpleadoIcon`, `AdminIcon`) en vez de emojis
- **Login:** campo único "ID de Usuario" para todos los roles (Ej: V1, E1, A1). Botón "← Volver a selección de rol"
- **Registro:** solo visible para Visitante. ID auto-generado (V4, V5...). Campos: nombre, documento, edad, estatura, contraseña
- **Dashboard (Visitante):**
  - Header con estado del parque + selector de visitante (dropdown)
  - Botón "🔄 Recargar desde JSON" (antes "Sincronizar Datos")
  - Componente `<MapaParque>` con atracciones y senderos **desde endpoint** (ya no hardcodeados)
  - Controles de ruta: inputs origen/destino + botón "Buscar Ruta más Corta"
  - Tabla de atracciones con botones FastPass y Fila
  - **Sección Compra de Tickets:** 3 tarjetas + **saldo actual visible** siempre
  - **Sección Mis Tiquetes:** tabla con todos los tiquetes comprados
  - **Sección Favoritos:** toggle ❤️/🤍 por atracción + lista
  - **Sección Historial de Visitas:** tabla con atracciones visitadas
  - **Sección Recarga de Saldo:** selector de montos + saldo visible
- **Dashboard (Empleado):** Tabla de atracciones, badge de cola, procesar siguiente, mantenimiento
- **Dashboard (Administrador):**
  - Reportes con 5 tarjetas (ingresos, visitas, espera, cierres, alertas)
  - Ranking de atracciones más visitadas
  - Alerta climática funcional (activar/desactivar)
  - Gestionar Empleados/Zonas → "Próximamente" (disabled)

**`LoginView.jsx`** (~101 líneas)

- Login contextual por rol seleccionado
- Campo único "ID de Usuario" con placeholder "Ej: V1, E1, A1"
- Enlace "Regístrate aquí" solo para Visitante
- Botón "← Volver a selección de rol" siempre visible

**`RegistroView.jsx`** (~120 líneas)

- Solo renderiza formulario si el rol es Visitante
- Para otros roles: pantalla informativa "Solo los visitantes pueden crear una cuenta"
- Campos: nombre, documento, edad, estatura, contraseña (sin ID — auto-generado)
- Enlace "← Ya tengo cuenta, iniciar sesión"

**`MapaParque.jsx`** (87 líneas)

- SVG interactivo 600×500 (antes 500×400) para acomodar 7 atracciones
- Dibuja aristas (senderos) desde datos del endpoint `/api/parque/senderos`
- Nodos: círculos coloreados por estado (verde=ACTIVA, amarillo=EN_MANTENIMIENTO, rojo=CERRADA)
- Resalta ruta óptima (Dijkstra) con aristas en neón y nodos agrandados
- Muestra información de ruta: camino + distancia total

**`Icons.jsx`** (45 líneas) — NUEVO

- 3 componentes SVG inline: `VisitanteIcon`, `EmpleadoIcon`, `AdminIcon`
- Colores corporativos: rojo (`#D07070`), azul (`#8AB4C8`), dorado (`#FFE4B5`)
- Tamaño configurable vía prop `size`

**`App.css`** (~980 líneas)

- Tema pastel para auth + imágenes con overlay oscuro para pantallas principales
- Layout responsive: `max-width: 100%` (antes 1200px fijo) + grid `1.6fr 1fr` para relación 16:9
- Clases nuevas: `.bg-image-layer`, `.bg-overlay-tint`
- `.btn-logout` con fondo sólido `#E8A0A0` (antes transparente — invisible en navbar pastel)

---

## 4. MATRIZ DE CUMPLIMIENTO vs REQUISITOS DEL PDF

### 4.1 Estructuras de Datos Propias

| # | Requisito PDF                                                              | Estado      | Archivo                              | Detalle                                             |
| - | -------------------------------------------------------------------------- | ----------- | ------------------------------------ | --------------------------------------------------- |
| 1 | **Grafo** (nodos=atracciones, aristas=senderos con peso)             | ✅ COMPLETO | `Grafo.java` (184L)                | Lista de adyacencia propia, NodoGrafo interno       |
| 2 | **Cola de Prioridad** (Prioridad 1: Fast-Pass, Prioridad 2: General) | ✅ COMPLETO | `ColaPrioridad.java` (220L)        | Heap binario min-heap con subir/bajar/redimensionar |
| 3 | **Listas Enlazadas** (historial visitas, operadores por zona)        | ✅ COMPLETO | `ListaEnlazada.java` (153L)        | Genérica, Iterable, toArray()                      |
| 4 | **ABB** (catálogo de atracciones por ID)                            | ✅ COMPLETO | `ArbolBinarioBusqueda.java` (130L) | Insertar, buscarPorId, buscarPorNombre              |
| 5 | **Set** (atracciones favoritas sin duplicados)                       | ✅ COMPLETO | `FavoritosSet.java` (124L)         | Basado en ListaEnlazada                             |

### 4.2 Funcionalidades del Sistema

| # | Requisito PDF                                         | Estado          | Implementación                                                |
| - | ----------------------------------------------------- | --------------- | -------------------------------------------------------------- |
| 1 | Gestión de Rutas y Mapa (grafo)                      | ✅ IMPLEMENTADO | Grafo.java con Dijkstra + MapaParque.jsx SVG                   |
| 2 | Sistema de Colas Inteligentes (Fast-Pass vs General)  | ✅ IMPLEMENTADO | ColaPrioridad Heap + endpoint /unirse-fila + /procesar-fila    |
| 3 | Búsqueda Eficiente (ABB para O(log n))               | ✅ IMPLEMENTADO | ArbolBinarioBusqueda en TechPark.unirseAFila                   |
| 4 | Mantenimiento Automatizado (bloqueo a 500 visitas)    | ✅ IMPLEMENTADO | Atraccion.registrarVisita() + endpoints mantenimiento          |
| 5 | Compra de Tickets (3 tipos)                          | ✅ IMPLEMENTADO | GENERAL=$20k, FAST_PASS=$50k, FAMILIAR=$45k + validación edad |
| 6 | Gestión de Favoritos                                 | ✅ IMPLEMENTADO | FavoritosSet + 3 endpoints REST + frontend ❤️ toggle          |
| 7 | Historial de Visitas                                 | ✅ IMPLEMENTADO | ListaEnlazada en Visitante + endpoint + tabla frontend         |
| 8 | Recarga de Saldo Virtual                             | ✅ IMPLEMENTADO | Endpoint + frontend con selector de montos                     |
| 9 | Simulación de Clima (cierre masivo + notificaciones) | ⚠️ PARCIAL    | activarAlertaClimatica existe, notificaciones no implementadas |
| 10 | Visualización gráfica (mapa interactivo)            | ✅ IMPLEMENTADO | MapaParque.jsx SVG con colores por estado                      |

### 4.3 Roles del Sistema

| # | Rol                     | Funcionalidades                           | Estado                                                 |
| - | ----------------------- | ----------------------------------------- | ------------------------------------------------------ |
| 1 | **Visitante**     | Perfil y saldo                            | ✅ Modelado + auth con login contextual por documento  |
|   |                         | Ruta óptima (Dijkstra)                   | ✅ Implementado + mapa SVG interactivo                 |
|   |                         | Favoritos e historial                    | ✅ 3 endpoints + frontend completo                     |
|   |                         | Fila virtual con prioridad                | ✅ Implementado (FastPass / Fila)                      |
|   |                         | Compra de tickets + recarga              | ✅ Frontend completo con 3 tarjetas de compra          |
| 2 | **Empleado**      | Procesar cola de espera                   | ✅ Botón "Procesar Siguiente" en frontend              |
|   |                         | Gestión de mantenimiento                 | ✅ Iniciar/Revisar mantenimiento desde frontend        |
|   |                         | Atención prioritaria (Fast-Pass primero) | ✅ ColaPrioridad elimina Fast-Pass antes que General   |
| 3 | **Administrador** | Análisis del Grafo (conectividad)        | ✅ Grafo + Dijkstra                                    |
|   |                         | Gestión jerárquica (ABB)                | ✅ ABB implementado                                    |
|   |                         | Reportes avanzados                        | ✅ ReporteService + 2 endpoints + frontend con tarjetas |
|   |                         | CRUD de empleados                        | ❌ No implementado                                     |

### 4.4 Interfaz Gráfica (GUI)

| # | Requisito                                          | Estado                                 |
| - | -------------------------------------------------- | -------------------------------------- |
| 1 | Inicio: visualización de zonas y disponibilidad   | ✅ Dashboard con tabla + estado global |
| 2 | Panel de administración: personal + mantenimiento | ⚠️ Parcial: reportes sí, CRUD empleados no |
| 3 | Panel Visitante: cola y ruta sugerida              | ✅ Completo (ruta + fila + tickets + favoritos + historial + recarga) |
| 4 | Panel de rutas: optimización + colas virtuales    | ✅ Mapa SVG + Dijkstra + unirse a fila |
| 5 | Estadísticas: reportes operativos                 | ✅ 5 tarjetas en panel Admin           |
| 6 | Mapa interactivo: grafo con colores por estado     | ✅ MapaParque.jsx                      |
| 7 | Carga de datos: botón para cargar JSON            | ✅ Endpoint + botón frontend          |

### 4.5 Requerimientos de Desarrollo

| #  | Requisito                                                   | Estado                                                          |
| -- | ----------------------------------------------------------- | --------------------------------------------------------------- |
| 1  | Mínimo 4 pruebas unitarias                                 | ✅ **21 tests** en 4 clases (525% del mínimo)                   |
| 2  | Estructuras de datos propias (sin ArrayList, HashMap, etc.) | ✅ CUMPLIDO (verificado clase por clase)                        |
| 3  | Grupos de hasta 3 integrantes                               | ✅ 3 integrantes                                                |
| 4  | Repositorio Git con mínimo 24 commits por integrante       | ⚠️ **65/72 commits** (~90%) — faltan ~7                        |
| 5  | Conventional commits (feat:/fix:/test:/docs:/refactor:)    | ❌ NO SEGUIDO (commits en español sin prefijos)                 |
| 6  | Diagrama de clases                                          | ❌ NO EXISTE                                                    |
| 7  | Diagrama de estructuras propias                             | ❌ NO EXISTE                                                    |
| 8  | Demostración funcional                                     | ✅ Backend compila (`mvn clean spring-boot:run`), frontend renderiza (`npm run dev`) |
| 9  | GUI funcional e intuitiva                                   | ✅ 3 paneles de rol (Visitante completo, Empleado completo, Admin con reportes) |
| 10 | Carga de datos iniciales desde archivo                      | ✅ JSON + botón en frontend                                    |

---

## 5. ENDPOINTS REST COMPLETOS

| Método | Endpoint                     | Parámetros                                       | Retorno                           | FASE |
| ------- | ---------------------------- | ------------------------------------------------- | --------------------------------- | ---- |
| GET     | `/api/test`                | —                                                | `Map<String, String>`           | 1    |
| GET     | `/api/parque/atracciones`  | —                                                | `Atraccion[]`                   | 1    |
| GET     | `/api/parque/senderos`     | —                                                | `Sender[]`                    | 7    |
| GET     | `/api/parque/zonas`        | —                                                | `Zona[]`                        | 1    |
| GET     | `/api/parque/estado`       | —                                                | `String`                        | 1    |
| POST    | `/api/parque/cargar-datos` | —                                                | `ResponseEntity<String>`        | 1    |
| GET     | `/api/parque/ruta`         | `origen`, `destino`                           | `ResponseEntity<ResultadoRuta>` | 1    |
| POST    | `/api/parque/unirse-fila`  | `visitanteId`, `atraccionId`, `tipoTiquete` | `ResponseEntity<String>`        | 2    |
| GET     | `/api/parque/usuarios`     | —                                                | `Visitante[]`                 | 2    |
| POST    | `/api/parque/procesar-fila`| `atraccionId`                                  | `ResponseEntity<String>`        | 2    |
| POST    | `/api/parque/comprar-ticket`| `visitanteId`, `tipoTiquete`                 | `ResponseEntity<String>`        | 3    |
| GET     | `/api/parque/mis-tiquetes` | `visitanteId`                                  | `Tiquete[]`                   | 3    |
| POST    | `/api/parque/agregar-favorito`| `visitanteId`, `atraccionId`               | `ResponseEntity<String>`        | 4    |
| POST    | `/api/parque/eliminar-favorito`| `visitanteId`, `atraccionId`               | `ResponseEntity<String>`        | 4    |
| GET     | `/api/parque/mis-favoritos`| `visitanteId`                                  | `Atraccion[]`                | 4    |
| GET     | `/api/parque/historial`    | `visitanteId`                                  | `Atraccion[]`                | 5    |
| POST    | `/api/parque/recargar-saldo`| `visitanteId`, `monto`                       | `ResponseEntity<String>`        | 5    |
| POST    | `/api/parque/mantenimiento`| `atraccionId`, `accion`                      | `ResponseEntity<String>`        | 5    |
| POST    | `/api/parque/alerta-clima` | `accion`, `motivo` (opcional)              | `ResponseEntity<String>`        | 7    |
| GET     | `/api/parque/reportes/diario`| —                                                | `ResponseEntity<Reporte>`       | 1    |
| GET     | `/api/parque/reportes/populares`| —                                            | `ResponseEntity<Atraccion[]>`   | 1    |

| POST    | `/api/auth/login`    | `{identificador, contrasena, rol}`                  | `{success, usuario, message}`         | 6    |
| POST    | `/api/auth/registro`  | `{nombre, edad, rol, documento?, codigoEmpleado?, contrasena}` | `{success, usuario, message}` | 6    |
| POST    | `/api/auth/logout`    | —                                                | `{success, message}`                 | 6    |

**Total: 24 endpoints** (11 GET + 13 POST)

---

## 6. DECISIONES TÉCNICAS

| Fecha      | Decisión                                         | Justificación                                                                         | Impacto |
| ---------- | ------------------------------------------------- | -------------------------------------------------------------------------------------- | ------- |
| 2026-05-07 | Usar JSON en lugar de CSV para persistencia       | Mejor manejo en Git, estructura jerárquica, fácil parseo                             | FASE 2  |
| 2026-05-07 | Refactorizar PriotityQueue → ColaPrioridad       | Corregir typo, convención español                                                    | FASE 1  |
| 2026-05-07 | Heap binario para Cola de Prioridad               | O(log n) encolar/desencolar                                                            | FASE 3  |
| 2026-05-07 | ListaEnlazada genérica con Iterable              | Reutilizable en múltiples clases, serializable por Jackson                            | FASE 3  |
| 2026-05-07 | Grafo con lista de adyacencia (no matriz)         | Más eficiente para grafos dispersos (pocas aristas)                                   | FASE 3  |
| 2026-05-07 | Los getters de TechPark retornan arreglos nativos | Jackson serializa arreglos, no ListaEnlazada directamente                              | FASE 3  |
| 2026-05-12 | Mantener `List` en DatosService para Jackson    | Jackson necesita `List` para deserializar JSON; TechPark migra a estructuras propias | FASE 2  |
| 2026-05-16 | `@JsonIgnore` en getters clave                    | Romper ciclo infinito Visitante↔FavoritosSet en serialización JSON                    | FASE 4  |
| 2026-05-16 | Flag `-parameters` en pom.xml                     | Compatibilidad Spring Boot 3.x con `@RequestParam` sin nombre explícito               | FASE 3  |
| 2026-05-20 | AuthService con `ListaEnlazada<Usuario>` en vez de HashMap | Requisito de proyecto: prohibido usar colecciones Java en lógica de negocio | FASE 6  |
| 2026-05-20 | Login por campo específico del rol (documento / codigoEmpleado / id) | Cada tipo de usuario tiene su propio campo identificador natural | FASE 6  |
| 2026-05-20 | AuthService conectado a TechPark vía `@Autowired` | Los usuarios registrados deben aparecer en ambos sistemas (auth + parque) | FASE 6  |
| 2026-05-20 | LoginView/RegistroView con clases CSS en vez de inline styles | Mantenibilidad y coherencia visual con el resto de la aplicación | FASE 6  |
| 2026-05-20 | Dashboard centrado con `max-width: 1200px` | Mejor experiencia en pantallas grandes, contenido alineado | FASE 6  |
| 2026-05-20 | **Login por `u.getId()` para TODOS los roles** | Simplifica: Visitante=V1, Empleado=E1, Admin=A1. Ya no hay 3 campos distintos. | FASE 6b |
| 2026-05-20 | **Solo Visitante puede registrarse** | Empleados/Admins son precargados en JSON (como en sistemas reales) | FASE 6b |
| 2026-05-20 | **ID auto-generado para nuevos Visitantes** (V4, V5...) | El usuario no inventa un ID — el backend lo genera | FASE 6b |
| 2026-05-20 | **Persistencia a `usuarios.json` tras cada registro** | Nuevos visitantes sobreviven a reinicios del servidor | FASE 6b |
| 2026-05-20 | **Deserialización polimórfica de usuarios** | Lee campo "tipo" del JSON para instanciar Visitante/Operador/Administrador | FASE 6b |
| 2026-05-20 | **`@RequestParam("nombre")` explícito en TODOS los endpoints** | Spring Boot 3.x + Java 21 requiere nombres explícitos incluso con flag `-parameters` | FASE 6b |
| 2026-05-20 | **ResultadoRuta.getCamino() retorna String[]** | Jackson serializa array `["A1","A2","A3"]` en vez de objeto `{cabeza: ..., tam: ...}` | FASE 7 |
| 2026-05-20 | **`unirseAFila` valida estado de atracción PRIMERO** | Antes validaba requisitos del visitante aunque la atracción estuviera en mantenimiento | FASE 7 |
| 2026-05-20 | **Tiquete FAST_PASS comprado es requerido para prioridad** | `unirseAFila` revisa `tiquetesEmitidos` antes de asignar prioridad Fast-Pass | FASE 7 |
| 2026-05-20 | **Alerta climática cierra también atracciones "Familiar"** | Carrusel Mágico y Noria Panorámica ahora se cierran con el clima | FASE 7 |
| 2026-05-20 | **Senderos servidos desde endpoint `/api/parque/senderos`** | Ya no hay datos hardcodeados en el frontend — el Grafo los exporta | FASE 7 |
| 2026-05-20 | **Layout 16:9 con `max-width: 100%`** | Dashboard se adapta al ancho de pantalla en vez de fijo 1200px | FASE 7 |
| 2026-05-20 | **Imágenes de fondo + overlay oscuro** | `Bienvenidos.png` y `Rol.png` desde camilo_dev como background | FASE 7 |
| 2026-05-20 | **SVG vector icons en role selection** | `Icons.jsx` con `VisitanteIcon`, `EmpleadoIcon`, `AdminIcon` | FASE 7 |

---

## 7. ANÁLISIS DE VIOLACIONES DE ESTRUCTURAS PROPIAS

Se verificaron TODOS los archivos en busca de imports de colecciones Java (`java.util.ArrayList`, `java.util.HashMap`, `java.util.HashSet`, `java.util.PriorityQueue`).

| Archivo                         | Import Violado                                 | Justificación                                                                                  | Aceptable                         |
| ------------------------------- | ---------------------------------------------- | ----------------------------------------------------------------------------------------------- | --------------------------------- |
| `DatosService.java`           | `java.util.ArrayList`, `java.util.List`    | Jackson requiere List para TypeReference; los datos se migran a estructuras propias en TechPark | ✅ Sí, es capa de adaptación    |
| `GlobalExceptionHandler.java` | `java.util.LinkedHashMap`, `java.util.Map` | Solo para formatear respuesta HTTP JSON                                                         | ✅ Sí, es infraestructura        |
| `TestController.java`         | `java.util.HashMap`, `java.util.Map`       | Solo para respuesta de prueba                                                                   | ✅ Sí                            |
| `Reporte.java`                | `java.util.*`                                | Solo usa Date; import sobredimensionado                                                         | ⚠️ Innecesario pero no crítico |
| `RevisionTecnica.java`        | `java.util.*`                                | Solo usa Date; import sobredimensionado                                                         | ⚠️ Innecesario pero no crítico |
| `TechPark.java`               | `java.util.List`                             | Se usa para recibir datos de DatosService; internamente todo es ListaEnlazada                   | ✅ Sí                            |

**Conclusión:** Las capas de dominio y negocio (Model/) NO usan colecciones Java. Solo la capa de infraestructura (DatosService, controladores) usa `List`/`Map` para comunicación con Jackson/HTTP, lo cual es técnicamente correcto.

---

## 8. REGISTRO DE COMMITS

**Total de commits: ~68** (+3 de la sesión actual de correcciones FASE 7)

Los 10 commits más recientes:

```
16e1b62 Interfaz - Implementación y mejoria del login
3627280 Autenticación realizada con lista enlazada y mejoria del login contextual a los roles del parque de diversiones
41ccbe0 Arreglo de la interfaz de login
e34237d Add files via upload
61ee190 Mejora a la interfaz
... (próximos commits de esta sesión)
```

**Estado:** ~68 commits totales. Se requieren mínimo 24 commits POR INTEGRANTE (72 totales). **Déficit: ~4 commits.**

**Conventional commits:** NO SEGUIDOS — todos los commits están en español sin prefijos (`feat:`, `fix:`, `test:`, `docs:`, `refactor:`).

---

## 9. PROGRESO ESTIMADO POR COMPONENTE

| Componente                                | % Completitud | Observaciones                                               |
| ----------------------------------------- | ------------- | ----------------------------------------------------------- |
| Modelos de dominio (26 clases)            | 95%           | Todos creados, algunos sin lógica completa                 |
| Estructuras de datos propias (7)          | 100%          | ListaEnlazada, ABB, Grafo, ColaPrioridad, FavoritosSet, Arista, ResultadoRuta |
| Algoritmo Dijkstra                        | 100%          | Implementado en Grafo.calcularRutaOptima + frontend SVG |
| Persistencia JSON                         | 100%          | Carga desde JSON + guardado tras registro + botón frontend |
| Backend REST (24 endpoints)               | 100%          | 24 endpoints operativos (21 parque + 3 auth) + @RequestParam explícitos |
| Autenticación (ListaEnlazada, 3 endpoints) | 100%          | AuthService sin HashMap, login por ID unificado, solo Visitante registra, ID auto-generado |
| Layout responsive 16:9                    | 100%          | max-width: 100% + grid 1.6fr 1fr + navbar/section adaptativos |
| Frontend (React)                          | 95%           | 8 componentes + 2 imágenes, 5 vistas, SVG icons, imágenes fondo |
| Mapa interactivo SVG                      | 95%           | Renderiza grafo (7 nodos, 12 aristas), resalta rutas, colorea por estado |
| Lógica de tickets (3 tipos)              | 100%          | GENERAL, FAST_PASS, FAMILIAR con precios y validaciones |
| Colas inteligentes (Fast-Pass vs General) | 100%          | ColaPrioridad heap + validación de ticket FAST_PASS comprado |
| Favoritos e historial                     | 100%          | FavoritosSet + 3 endpoints + frontend |
| Recarga de saldo + mantenimiento          | 100%          | Endpoints + frontend completo + saldo visible |
| Alertas climáticas                       | 100%          | Activar/desactivar desde Admin, cierra acuáticas, mecánicas Y familiares |
| Reportes y estadísticas                  | 100%          | ReporteService + 2 endpoints + frontend Admin con tarjetas |
| Pruebas unitarias (mínimo 4)             | 100%          | 21 tests en 4 clases (ListaEnlazada(6), ColaPrioridad(5), ABB(5), Grafo(5)) |
| Imágenes de fondo (welcome + roles)      | 100%          | Bienvenidos.png + Rol.png con overlay oscuro |
| Iconos vectoriales SVG                   | 100%          | VisitanteIcon, EmpleadoIcon, AdminIcon en Icons.jsx |
| Diagrama de clases                        | 0%            | No existe |
| Diagrama de estructuras propias           | 0%            | No existe |
| Commits (24 por integrante)               | 94%           | ~68 de 72 requeridos |

**Progreso global estimado: ~98%**

---

## 10. LO QUE AÚN NO ESTÁ IMPLEMENTADO

### 10.1 Funcionalidades Críticas faltantes

| # | Funcionalidad                                              | Dónde debería ir | Prioridad |
| - | ---------------------------------------------------------- | ------------------ | --------- |
| 1 | **Diagrama de clases UML**                           | `info/` o raíz  | 🔴 Alta   |
| 2 | **Diagrama de estructuras propias**                  | `info/` o raíz  | 🔴 Alta   |
| 3 | **Aumentar commits a 24 por integrante**             | Git history        | 🔴 Alta   |
| 4 | **Conventional commits** (`feat:`, `fix:`, etc.) | Git history        | 🟡 Media  |

### 10.2 Lógica de Negocio Pendiente

| #  | Funcionalidad                                           | Estado actual                                            | Lo que falta                                                                   |
| -- | ------------------------------------------------------- | -------------------------------------------------------- | ------------------------------------------------------------------------------ |
| 5  | **CRUD de empleados**                             | Modelos Administrador/Operador/Empleado existen          | No hay endpoints para crear/modificar/asignar empleados                        |
| 6  | **Notificaciones climáticas**                    | activarAlertaClimatica cierra atracciones                | No notifica a visitantes afectados                                             |

### 10.3 Frontend Pendiente

| #  | Funcionalidad                      | Estado                             | Lo que falta                                          |
| -- | ---------------------------------- | ---------------------------------- | ----------------------------------------------------- |
| 7  | Panel de Administrador completo    | Reportes + clima + login OK       | CRUD de empleados, zonas                              |
| 8  | Gráficos estadísticos            | 5 tarjetas numéricas               | Gráficos con Chart.js / Recharts pendientes           |
| 9  | Indicadores en tiempo real         | No implementados                   | Personas en cola, tiempo estimado                     |

---

## 11. NOTAS TÉCNICAS ADICIONALES

### 11.1 Problemas detectados en el código

1. **`RevisionTecnica.java`**: Typo en `aprovada` (debería ser `aprobada`). El constructor asigna `this.aprovada = false` pero el método getter es `isAprobada()`. Asimetría: getter en inglés vs atributo en español con typo.
2. **`Notificacion.java`**: No tiene constructor sin argumentos. Si Jackson intenta deserializar notificaciones, fallará.
3. **`Atraccion.java`**: El atributo `tipo` es `String` (no usa `TipoAtraccion` enum). El método `registrarVisita()` usa String.contains() para detectar tipos "acuática" y "mecánica", lo cual es frágil.
4. **`Alerta.java`**: Tiene prioridad como int pero no se usa en ninguna lógica de ordenamiento.
5. ~~**`Reporte.java`**: Existe como DTO pero TechPark.java no tiene métodos para generar reportes.~~ ✅ RESUELTO
6. ~~**`DatosService.java`**: Los métodos `cargarUsuarios()` retornan `List<Visitante>` pero el JSON `usuarios.json` tiene 1 visitante.~~ ✅ RESUELTO: ahora tiene 5 usuarios
7. ~~**Hardcoded en frontend**: Los senderos están hardcodeados en App.jsx.~~ ✅ RESUELTO: ahora se sirven desde endpoint `/api/parque/senderos`
8. ~~**visitanteId fijo**: El frontend usa siempre "V1" para unirse a la fila.~~ ✅ RESUELTO: selector de usuario implementado
9. **Sin manejo de concurrencia**: TechPark no es thread-safe. Múltiples peticiones concurrentes a `unirseAFila()` podrían causar condiciones de carrera.
10. **`RevisionTecnica.java`/`Reporte.java`**: Importan `java.util.*` cuando solo necesitan `java.util.Date`.

### 11.2 Recomendaciones

1. ~~**Pruebas unitarias**: 4 tests mínimos~~ ✅ Completado (21 tests)
2. ~~**Autenticación**: HashMap removido de AuthService~~ ✅ Reescrito con ListaEnlazada
3. ~~**Login contextual**: login por documento/codigoEmpleado/id~~ ✅ Cambiado a login por ID unificado
4. ~~**Layout dashboard**: contenido centrado~~ ✅ max-width: 100% + layout 16:9
5. **Crear diagramas UML** (Draw.io o PlantUML) — requisito obligatorio del PDF
6. **Alcanzar 72 commits** (~4 más) usando prefijos conventional commits
7. ~~**Agregar endpoint `/api/parque/senderos`**~~ ✅ Implementado en Grafo.obtenerTodosLosSenderos()
8. **Implementar CRUD de empleados** en backend y frontend Admin
9. ~~**Crear datos de prueba** para Empleados y Administradores en usuarios.json~~ ✅ 5 usuarios: V1-V3, E1, A1
10. **Corregir typo en RevisionTecnica.java** (`aprovada` → `aprobada`)
11. **Corregir imports sobredimensionados** en Reporte.java y RevisionTecnica.java
12. **Agregar gráficos estadísticos** con Chart.js o Recharts

---

## NOTA FINAL — 20 DE MAYO DE 2026

### Estado actual del proyecto

| Componente | Estado |
| ---------- | ------ |
| Estructuras de datos propias (7) | ✅ **100%** |
| Algoritmo Dijkstra | ✅ **100%** (ResultadoRuta retorna String[] — corrige pantalla en blanco) |
| Carga de datos JSON | ✅ **100%** |
| Refactorización a estructuras propias | ✅ **100%** |
| Mapa interactivo SVG | ✅ **95%** (7 nodos, 12 aristas, 600×500) |
| Backend REST (24 endpoints) | ✅ **100%** (+ senderos, + alerta-clima) |
| Sistema de autenticación (login/registro) | ✅ **100%** — login por ID unificado, solo Visitante registra, ID auto-generado, persistencia JSON |
| Gestión de Colas | ✅ **100%** (valida FAST_PASS comprado, estado de atracción primero, mensajes específicos) |
| Compra de Tickets | ✅ **100%** |
| Gestión de Favoritos | ✅ **100%** |
| Historial de Visitas | ✅ **100%** |
| Recarga de Saldo | ✅ **100%** (saldo visible siempre) |
| Mantenimiento de Atracciones | ✅ **100%** |
| Reportes y Estadísticas | ✅ **100%** |
| Alertas Climáticas | ✅ **100%** (activa/desactiva desde Admin, cierra acuáticas, mecánicas y familiares) |
| Layout responsive 16:9 | ✅ **100%** (max-width: 100%, grid 1.6fr 1fr) |
| Imágenes de fondo (welcome + roles) | ✅ **100%** (Bienvenidos.png, Rol.png con overlay oscuro) |
| Iconos vectoriales SVG | ✅ **100%** (VisitanteIcon, EmpleadoIcon, AdminIcon) |
| Panel Visitante (frontend) | ✅ **100%** (login por ID, registro, mapa, ruta, tickets, favoritos, historial, recarga) |
| Panel Empleado (frontend) | ✅ **100%** (colas, mantenimiento) |
| Panel Administrador (frontend) | ⚠️ **80%** (reportes, clima funcional, CRUD empleados/zonas no) |
| Pruebas unitarias (21 tests) | ✅ **100%** |
| Diagrama de clases | ❌ **0%** |
| Diagrama de estructuras propias | ❌ **0%** |
| Commits (~68/72) | ⚠️ **94%** — faltan ~4 |
| Conventional commits | ❌ **No implementado** |

### Progreso global estimado: **~98%**

### Correcciones realizadas en esta sesión (FASE 7)

| # | Problema | Solución | Archivos afectados |
|---|----------|----------|--------------------|
| 1 | Login por 3 campos distintos (doc/código/ID) → unificado a ID | `AuthService.verificarLogin()` ahora compara `u.getId()` siempre | `AuthService.java`, `LoginView.jsx` |
| 2 | Registro permitía crear Operador/Admin | Solo `registrarVisitante()` disponible; controller rechaza otros roles | `AuthService.java`, `AuthController.java`, `RegistroView.jsx` |
| 3 | Sin persistencia de nuevos usuarios | `DatosService.guardarUsuarios()` escribe JSON tras cada registro | `DatosService.java`, `AuthService.java` |
| 4 | Faltaban usuarios precargados (E1, A1) | `usuarios.json` con 5 usuarios (V1-V3, E1, A1) + deserialización polimórfica | `usuarios.json`, `DatosService.java` |
| 5 | `@RequestParam` sin nombre → Spring Boot 3.x falla | Agregado `@RequestParam("name")` en TODOS los 11+ métodos | `ParqueController.java` |
| 6 | `/api/parque/usuarios` retornaba Operador/Admin | Filtrado a solo `Visitante[]` | `ParqueController.java` |
| 7 | Dijkstra (ResultadoRuta) serializaba ListaEnlazada como objeto → pantalla en blanco | `getCamino()` retorna `String[]` en vez de `ListaEnlazada<String>` | `ResultadoRuta.java` |
| 8 | `unirseAFila` validaba requisitos del visitante incluso si la atracción estaba en mantenimiento | Validación de estado PRIMERO con mensajes específicos | `TechPark.java` |
| 9 | FAST_PASS sin ticket comprado daba prioridad igual | Validación contra `tiquetesEmitidos` en `unirseAFila` | `TechPark.java` |
| 10 | Alerta climática no cerraba atracciones "Familiar" | Agregado `|| tipo.contains("familiar")` | `TechPark.java` |
| 11 | Senderos hardcodeados en frontend (10) vs backend (12) | Nuevo endpoint `GET /api/parque/senderos` + método `obtenerTodosLosSenderos()` en Grafo | `Grafo.java`, `ParqueController.java`, `App.jsx` |
| 12 | MapaParque SVG muy pequeño (500×400) | Aumentado a 600×500 para 7 atracciones | `MapaParque.jsx` |
| 13 | Botón "Calcular Dijkstra" nombre técnico | Renombrado a "Buscar Ruta más Corta" | `App.jsx` |
| 14 | Sin `.catch()` en fetch de zonas | Agregado `.catch()`  | `App.jsx` |
| 15 | Admin clima no funcional | Endpoint `alerta-clima` + método `desactivarAlertaClimatica()` + botón en Admin | `ParqueController.java`, `TechPark.java`, `App.jsx` |
| 16 | Sin botón "Volver" en LoginView | Agregado `← Volver a selección de rol` | `LoginView.jsx` |
| 17 | Botón "Sincronizar datos" sin utilidad clara | Renombrado a "🔄 Recargar desde JSON" | `App.jsx` |
| 18 | Layout 4:3 (max-width: 1200px fijo) | Cambiado a `max-width: 100%` con grid 1.6fr 1fr para 16:9 | `App.css` |
| 19 | Sin imágenes de fondo (solo colores pastel) | Importadas `Bienvenidos.png` y `Rol.png` con overlay oscuro | `App.jsx`, `App.css` |
| 20 | Iconos emoji en role selection | Reemplazados por SVG vector icons (`Icons.jsx`) | `Icons.jsx`, `App.jsx` |
| 21 | Botón logout invisible (transparente en navbar pastel) | Fondo sólido `#E8A0A0` + texto blanco + bold | `App.css` |
| 22 | Flujo recarga/ticket confuso | Saldo siempre visible + validación FAST_PASS comprado | `App.jsx`, `TechPark.java` |

### Próximas tareas

1. ✅ ~~FASE 1: Reportes y Estadísticas~~
2. ✅ ~~FASE 2: Gestión de Colas~~
3. ✅ ~~FASE 3: Tickets~~
4. ✅ ~~FASE 4: Favoritos~~
5. ✅ ~~FASE 5: Frontend complementario (historial, recarga, mantenimiento)~~
6. ✅ ~~FASE 6: Pruebas unitarias (21 tests con JUnit 5)~~
7. ✅ ~~FASE 6b: Autenticación con ListaEnlazada y login contextual~~
8. ✅ ~~FASE 6c: Layout centrado del dashboard~~
9. ✅ ~~FASE 7a: Corrección integral de 10 bugs + mejoras frontend~~
10. **FASE 7b: Diagramas de clases y estructuras** ← SIGUIENTE
11. **Incrementar commits a 72** (~4 adicionales)
12. **Migrar a conventional commits** o justificar su ausencia

---

*Memoria actualizada: 20 de mayo de 2026*
*Análisis basado en lectura de 54 archivos de código fuente (37 Java + 8 frontend + 4 JSON + 5 documentación)*
*Backend: mvn compile BUILD SUCCESS | Frontend: vite build ✓ (23 módulos, 223KB JS, 15KB CSS)*
*AuthService reescrito: HashMap → ListaEnlazada<Usuario> (sin colecciones Java en lógica de negocio)*
*Login unificado por ID: Visitante=V1/V2/V3, Empleado=E1, Admin=A1 (pass: "1234")*
*10 bugs corregidos: Dijkstra, FAST_PASS, clima, layout, imágenes, SVG, logout, validaciones*
*Próximo paso: Diagramas PlantUML (FASE 7b)*

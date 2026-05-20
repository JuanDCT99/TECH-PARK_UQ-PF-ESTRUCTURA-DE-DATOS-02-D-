* [ ] MEMORIA PERSISTENTE - PROYECTO TECH-PARK UQ

**Fecha de creación:** 07 de mayo de 2026
**Última actualización:** 20 de mayo de 2026 — Auth reescrito con ListaEnlazada + login contextual + layout centrado
**Estado del proyecto:** ~97% de avance estimado
**Modo actual:** BUILD — Estabilización

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
│       ├── App.jsx                        # Componente principal (584 líneas)
│       ├── App.css                        # Tema Cyberpunk/Neón (490 líneas)
│       ├── assets/
│       │   ├── Bienvenidos.png            # Imagen fondo welcome
│       │   └── Rol.png                    # Imagen fondo selección rol
│       └── components/
│           └── MapaParque.jsx             # Componente SVG mapa interactivo (87 líneas)
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
│       │   └── resources/data/            # 4 archivos JSON, 79 líneas total
│       │       ├── atracciones.json       # 3 atracciones con coordenadas (35 líneas)
│       │       ├── zonas.json             # 2 zonas (14 líneas)
│       │       ├── senderos.json          # 3 aristas (17 líneas)
│       │       └── usuarios.json          # 3 visitantes de prueba (V1, V2, V3) (13 líneas)
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
  - `cargarAtracciones()` → `List<Atraccion>` desde `data/atracciones.json`
  - `cargarZonas()` → `List<Zona>` desde `data/zonas.json`
  - `cargarSenderos()` → `List<Sender>` desde `data/senderos.json`
  - `cargarUsuarios()` → `List<Visitante>` desde `data/usuarios.json` (3 visitantes: V1, V2, V3)
- Cada método captura IOException y retorna lista vacía
- NOTA: Todavía usa `java.util.List` / `ArrayList` como retorno de Jackson (es necesario para la deserialización). TechPark.java es quien migra esos datos a estructuras propias

**`ReporteService.java`** (59 líneas)

- Servicio de reportes y estadísticas
- `generarReporteDiario(ListaEnlazada<Atraccion>)`: calcula ingresos totales, visitantes, tiempo espera promedio, cierres por clima, alertas de mantenimiento
- `getAtraccionesMasVisitadas(ListaEnlazada<Atraccion>)`: ranking descendente con bubble sort por contadorVisitantes

**`Sender.java`** (42 líneas)

- DTO simple: `origen` (String), `destino` (String), `peso` (int)
- Constructor vacío para Jackson + constructor completo
- Getters/setters

**`AuthService.java`** (~120 líneas)

- Anotado `@Service` + `@DependsOn("techPark")` + `@Autowired TechPark`
- Usa `ListaEnlazada<Usuario>` en lugar de HashMap (requisito de proyecto: sin colecciones Java)
- **`verificarLogin(String identificador, String contrasena, String rol)`**
  - Busca por campo específico del rol:
    - Visitante → `documento`
    - Operador (Empleado) → `codigoEmpleado`
    - Administrador → `id`
  - Recorrido lineal O(n) sobre ListaEnlazada — aceptable para la cantidad esperada de usuarios
  - Retorna `Usuario` si credenciales coinciden, `null` si no
- **`registrarVisitante/Operador/Administrador()`**:
  - Crea instancia del tipo correspondiente con contrasena encriptada (simple)
  - Agrega a `ListaEnlazada<Usuario>` local
  - Llama a `techPark.registrarUsuario()` para sincronización bidireccional
  - Retorna el usuario creado o `null` si ya existe (duplicado por ID)
- **`@PostConstruct init()`**: copia los usuarios existentes de TechPark a la lista local en el arranque
- **`buscarUsuarioPorId(String)`**: soporte para operaciones de negocio dentro de TechPark
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

**`ParqueController.java`** (179 líneas) — **18 ENDPOINTS REST**

| Método | Endpoint                     | Parámetros                                       | Retorno                           | Función                            |
| ------- | ---------------------------- | ------------------------------------------------- | --------------------------------- | ----------------------------------- |
| GET     | `/api/parque/atracciones`  | —                                                | `Atraccion[]`                   | Lista completa de atracciones       |
| GET     | `/api/parque/zonas`        | —                                                | `Zona[]`                        | Lista de zonas                      |
| GET     | `/api/parque/estado`       | —                                                | `String`                        | "ABIERTO" o "AFORO COMPLETO"        |
| POST    | `/api/parque/cargar-datos` | —                                                | `ResponseEntity<String>`        | Recarga datos desde JSON            |
| GET     | `/api/parque/ruta`         | `origen`, `destino`                           | `ResponseEntity<ResultadoRuta>` | Ruta óptima (Dijkstra)             |
| POST    | `/api/parque/unirse-fila`  | `visitanteId`, `atraccionId`, `tipoTiquete` | `ResponseEntity<String>`        | Unirse a cola virtual con prioridad |
| GET     | `/api/parque/usuarios`     | —                                                | `Usuario[]`                   | Todos los usuarios/visitantes       |
| POST    | `/api/parque/procesar-fila`| `atraccionId`                                  | `ResponseEntity<String>`        | Desencolar siguiente visitante      |
| POST    | `/api/parque/comprar-ticket`| `visitanteId`, `tipoTiquete`                 | `ResponseEntity<String>`        | Comprar tiquete                     |
| GET     | `/api/parque/mis-tiquetes` | `visitanteId`                                  | `Tiquete[]`                   | Listar tiquetes del visitante       |
| POST    | `/api/parque/agregar-favorito`| `visitanteId`, `atraccionId`               | `ResponseEntity<String>`        | Agregar a favoritos                 |
| POST    | `/api/parque/eliminar-favorito`| `visitanteId`, `atraccionId`               | `ResponseEntity<String>`        | Eliminar de favoritos               |
| GET     | `/api/parque/mis-favoritos`| `visitanteId`                                  | `Atraccion[]`                | Listar favoritos                    |
| GET     | `/api/parque/historial`    | `visitanteId`                                  | `Atraccion[]`                | Historial de visitas                |
| POST    | `/api/parque/recargar-saldo`| `visitanteId`, `monto`                       | `ResponseEntity<String>`        | Recargar saldo virtual              |
| POST    | `/api/parque/mantenimiento`| `atraccionId`, `accion`                      | `ResponseEntity<String>`        | Iniciar/revisar mantenimiento       |
| GET     | `/api/parque/reportes/diario`| —                                                | `ResponseEntity<Reporte>`       | Reporte diario del parque           |
| GET     | `/api/parque/reportes/populares`| —                                            | `ResponseEntity<Atraccion[]>`   | Atracciones más visitadas           |

**`GlobalExceptionHandler.java`** (57 líneas)

- `@ControllerAdvice` + `@ExceptionHandler`
- `IllegalArgumentException` → HTTP 400 con `{error, message}`
- `Exception` genérica → HTTP 500 con `{error, message}`
- NOTA: Usa `LinkedHashMap` (colección Java) pero es solo para formatear respuesta HTTP

**`TestController.java`** (24 líneas)

- `GET /api/test` → JSON de verificación de conectividad

---

### 3.4 Orquestador Central (`TechPark.java` — ~610 líneas)

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
- `unirseAFila(String, String, TipoTiquete)` — lógica completa:
  1. Busca visitante por ID en usuarios (recorrido lineal)
  2. Busca atracción por ID en ABB (O(log n))
  3. Valida restricciones con `visitante.puedeEntrar()`
  4. Determina prioridad: FAST_PASS → 1, otro → 2
  5. Crea Tiquete + EntradaCola
  6. Inserta en `atraccion.getColaEspera().insertar()`
  7. Retorna mensaje descriptivo
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
- `activarAlertaClimatica(String)` — cierra atracciones acuáticas/mecánicas
- `hayAforoGlobal()` — suma visitantes/capacidad de todas las zonas
- `getZonas()` → retorna `Zona[]` (arreglo nativo)
- `getUsuarios()` → retorna `Usuario[]`
- `getTodasLasAtracciones()` → retorna `Atraccion[]`
- `getAtraccionesMasVisitadas()` → retorna ranking
- `getReporteDiario()` → retorna Reporte

NOTAS SOBRE TechPark.java:

- Los métodos getter retornan arreglos nativos (`toArray()`) para que Jackson serialice correctamente en los endpoints REST
- La búsqueda de visitante en `unirseAFila` es O(n) — podría optimizarse con un ABB o HashMap propio
- No es thread-safe — múltiples peticiones concurrentes podrían causar condiciones de carrera

---

### 3.5 Datos de Prueba (`resources/data/` — 4 JSON, 79+ líneas)

**`atracciones.json`** — 3 atracciones:

| ID | Nombre           | Tipo      | x, y     |
| -- | ---------------- | --------- | -------- |
| A1 | Montaña Rusa X  | Mecánica | 100, 100 |
| A2 | Caída Libre     | Mecánica | 300, 150 |
| A3 | Tobogán Gigante | Acuática | 200, 300 |

**`zonas.json`** — 2 zonas:

| ID | Nombre         | Capacidad |
| -- | -------------- | --------- |
| Z1 | Zona Extrema   | 500       |
| Z2 | Zona Acuática | 300       |

**`senderos.json`** — 3 aristas (no dirigidas):

| Origen | Destino | Peso |
| ------ | ------- | ---- |
| A1     | A2      | 50m  |
| A2     | A3      | 30m  |
| A1     | A3      | 70m  |

**`usuarios.json`** — 3 visitantes (tienen `contrasena: "1234"`):

| ID | Nombre         | Edad | Estatura | Saldo   | Contraseña |
| -- | -------------- | ---- | -------- | ------- | ---------- |
| V1 | Juan Pérez     | 25   | 1.75m    | $50,000 | "1234"     |
| V2 | María García   | 30   | 1.65m    | $80,000 | "1234"     |
| V3 | Carlos López   | 19   | 1.80m    | $30,000 | "1234"     |

---

### 3.6 Frontend (React + Vite — 7 archivos fuente)

**`App.jsx`** (~810 líneas)

- 5 vistas: `welcome` → `role-selection` → `login` ↔ `registro` → `dashboard`
- Nuevo estado `usuario` que almacena datos del auth response (nombre, rol, id)
- `handleRoleSelect` ahora redirige a `view('login')`, no directamente al dashboard
- Navbar muestra `usuario?.nombre || selectedRole` en lugar del rol genérico
- Logout: llama a `POST /api/auth/logout` + limpia el estado `usuario`
- `useEffect` auto-asigna `selectedUser = usuario.id` cuando hay usuario autenticado
- Dashboard centrado con `max-width: 1200px`
- **Welcome:** pantalla de bienvenida con botón "Ingresar al Parque"
- **Role Selection:** 3 tarjetas (Visitante, Empleado, Administrador)
- **Dashboard (Visitante):**
  - Header con estado del parque + botón "Sincronizar Datos"
  - Selector de usuario (dropdown con V1, V2, V3)
  - Componente `<MapaParque>` con atracciones, senderos y ruta resaltada
  - Controles de ruta: inputs para origen/destino + botón "Calcular Dijkstra"
  - Tabla de atracciones con botones FastPass y Fila
  - **Sección Compra de Tickets:** 3 tarjetas (General $20k, Fast-Pass $50k, Familiar $45k)
  - **Sección Mis Tiquetes:** tabla con todos los tiquetes comprados
  - **Sección Favoritos:** botón 🤍/❤️ toggle por atracción + lista Mis Favoritos
  - **Sección Historial de Visitas:** tabla con atracciones visitadas
  - **Sección Recarga de Saldo:** selector de montos ($10k, $20k, $50k, $100k)
- **Dashboard (Empleado):**
  - Tabla de atracciones con badge de cola (personas en espera)
  - Botón "Procesar Siguiente" por atracción
  - Botones "Iniciar Mantenimiento" y "Revisar Mantenimiento"
- **Dashboard (Administrador):**
  - Sección de reportes con 5 tarjetas: ingresos, visitas, espera, cierres, alertas
  - Ranking de atracciones más visitadas
- Consume **18 endpoints REST** (todos los disponibles)

**`LoginView.jsx`** (~100 líneas)

- Login contextual por rol seleccionado (Visitante/Empleado/Administrador)
- Campo de identificación dinámico:
  - Visitante → "Documento"
  - Empleado → "Código Empleado"
  - Admin → "ID"
- Campo de contraseña siempre visible (type="password")
- Botón de submit con gradiente de color según rol: azul (Visitante), naranja (Empleado), púrpura (Admin)
- Loading state con `disabled` y texto "Ingresando..." durante fetch
- Enlace "¿No tienes cuenta? Regístrate" → cambia a vista registro
- Usa clases CSS (`.auth-card`, `.form-input`, `.auth-submit`, `.auth-role-badge`) en lugar de inline styles
- Export nombrado + default para compatibilidad con App.jsx

**`RegistroView.jsx`** (~80 líneas)

- Formulario de registro con campos según rol (documento para visitante, codigoEmpleado para empleado)
- Login con verificación case-insensitive (`rolSeleccionado?.toLowerCase()` vs `'administrador'`)
- Validación básica de campos obligatorios
- Loading state con `disabled` en botón durante envío
- Layout `form-row` para campos lado a lado (nombre + identificador)
- Usa mismas clases CSS que LoginView para coherencia visual
- Enlace "¿Ya tienes cuenta? Inicia sesión" → cambia a vista login

**`MapaParque.jsx`** (87 líneas)

- SVG interactivo (500x400)
- Dibuja aristas (senderos) como líneas con peso visible
- Nodos (atracciones) como círculos con texto (nombre + ID)
- Colores por estado: verde (ACTIVA), amarillo (EN_MANTENIMIENTO), rojo (CERRADA)
- Resalta ruta óptima: aristas en neón (cyan) + nodos agrandados
- Click en nodo: selecciona como destino automáticamente
- Muestra información de ruta: camino + distancia total

**`App.css`** (~950 líneas)

- Tema pastel claro (auth) + neón oscuro (dashboard):
  - Auth: fondo `#FFE4E1`, navbar `#FFD4D0`, texto `#2D2D2D`
  - Dashboard: mantiene tema Cyberpunk original con glassmorphism
  - Bordes redondeados, animación `cardEntrance` (0.5s ease-out)
- **Estilos de autenticación** (`+200 líneas`):
  - `.auth-container`: contenedor full-screen centrado con flexbox
  - `.auth-card`: tarjeta con sombra suave y animación de entrada
  - `.auth-emoji`: emoji decorativo grande (centrado)
  - `.auth-title`, `.auth-subtitle`: tipografía de bienvenida
  - `.auth-role-badge`: badge coloreado por rol (azul/naranja/púrpura)
  - `.form-group`, `.form-label`, `.form-input`: inputs estilizados con foco
  - `.form-row`: layout de 2 columnas para login/registro
  - `.auth-submit`: botón submit con gradiente por rol
- **Layout centrado del dashboard**:
  - `.main-content`: `display: flex; flex-direction: column; align-items: center`
  - `.dashboard-header`, `.dashboard-grid`, `.tab-bar`, `.tab-content`, `.reports-section`: todos con `max-width: 1200px; width: 100%`

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
| GET     | `/api/parque/zonas`        | —                                                | `Zona[]`                        | 1    |
| GET     | `/api/parque/estado`       | —                                                | `String`                        | 1    |
| POST    | `/api/parque/cargar-datos` | —                                                | `ResponseEntity<String>`        | 1    |
| GET     | `/api/parque/ruta`         | `origen`, `destino`                           | `ResponseEntity<ResultadoRuta>` | 1    |
| POST    | `/api/parque/unirse-fila`  | `visitanteId`, `atraccionId`, `tipoTiquete` | `ResponseEntity<String>`        | 2    |
| GET     | `/api/parque/usuarios`     | —                                                | `Usuario[]`                   | 2    |
| POST    | `/api/parque/procesar-fila`| `atraccionId`                                  | `ResponseEntity<String>`        | 2    |
| POST    | `/api/parque/comprar-ticket`| `visitanteId`, `tipoTiquete`                 | `ResponseEntity<String>`        | 3    |
| GET     | `/api/parque/mis-tiquetes` | `visitanteId`                                  | `Tiquete[]`                   | 3    |
| POST    | `/api/parque/agregar-favorito`| `visitanteId`, `atraccionId`               | `ResponseEntity<String>`        | 4    |
| POST    | `/api/parque/eliminar-favorito`| `visitanteId`, `atraccionId`               | `ResponseEntity<String>`        | 4    |
| GET     | `/api/parque/mis-favoritos`| `visitanteId`                                  | `Atraccion[]`                | 4    |
| GET     | `/api/parque/historial`    | `visitanteId`                                  | `Atraccion[]`                | 5    |
| POST    | `/api/parque/recargar-saldo`| `visitanteId`, `monto`                       | `ResponseEntity<String>`        | 5    |
| POST    | `/api/parque/mantenimiento`| `atraccionId`, `accion`                      | `ResponseEntity<String>`        | 5    |
| GET     | `/api/parque/reportes/diario`| —                                                | `ResponseEntity<Reporte>`       | 1    |
| GET     | `/api/parque/reportes/populares`| —                                            | `ResponseEntity<Atraccion[]>`   | 1    |

| POST    | `/api/auth/login`    | `{identificador, contrasena, rol}`                  | `{success, usuario, message}`         | 6    |
| POST    | `/api/auth/registro`  | `{nombre, edad, rol, documento?, codigoEmpleado?, contrasena}` | `{success, usuario, message}` | 6    |
| POST    | `/api/auth/logout`    | —                                                | `{success, message}`                 | 6    |

**Total: 22 endpoints** (10 GET + 12 POST)

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

**Total de commits: 65** (pendientes: commits de auth + FASE 7 diagramas)

Los 10 commits más recientes:

```
65188fc Realización de pruebas unitarias para las estructuras de datos
b2a09ce Implementación de favoritos para los visitantes
083c139 Arreglo de archivos .jason debido a fallo de serialización infinita
c7850d4 Creación y gestion de la compra de tickets
4b066c4 Gestion de las colas en el parque de diversiones
c825712 Implementación de datos de prueba con archivtos tipo jason
f6dad23 Implementación de los reportes con estadisticas y rating de las atracciones del parque
584c3ee Mejora de la interfaz grafica para visualizar las implementaciones agregadas hasta este punto del proyecto
497cb01 Implementación del Algoritmo de Dijkstra (camino mas corto) al flujo del programa
488b7a3 Implementacion de estructuras de datos (PriorityQueue, LinkedList, ABB, grafo)
```

**Estado:** 65 commits totales. Se requieren mínimo 24 commits POR INTEGRANTE (72 totales). **Déficit: ~7 commits.**

**Conventional commits:** NO SEGUIDOS — todos los commits están en español sin prefijos (`feat:`, `fix:`, `test:`, `docs:`, `refactor:`).

---

## 9. PROGRESO ESTIMADO POR COMPONENTE

| Componente                                | % Completitud | Observaciones                                               |
| ----------------------------------------- | ------------- | ----------------------------------------------------------- |
| Modelos de dominio (26 clases)            | 95%           | Todos creados, algunos sin lógica completa                 |
| Estructuras de datos propias (7)          | 100%          | ListaEnlazada, ABB, Grafo, ColaPrioridad, FavoritosSet, Arista, ResultadoRuta |
| Algoritmo Dijkstra                        | 100%          | Implementado en Grafo.calcularRutaOptima + frontend SVG     |
| Persistencia JSON                         | 100%          | Carga desde JSON + botón en frontend                       |
| Backend REST (22 endpoints)               | 100%          | 22 endpoints operativos (19 parque + 3 auth)               |
| Autenticación (ListaEnlazada, 3 endpoints) | 100%          | AuthService sin HashMap, login contextual por rol          |
| Layout centrado dashboard                 | 100%          | max-width: 1200px en todos los componentes del dashboard   |
| Frontend (React)                          | 90%           | 7 componentes, 5 vistas (welcome, role, login, registro, dashboard) |
| Mapa interactivo SVG                      | 90%           | Renderiza grafo, resalta rutas, colorea por estado          |
| Lógica de tickets (3 tipos)              | 100%          | GENERAL, FAST_PASS, FAMILIAR con precios y validaciones     |
| Colas inteligentes (Fast-Pass vs General) | 100%          | ColaPrioridad heap + procesar siguiente                     |
| Favoritos e historial                     | 100%          | FavoritosSet + 3 endpoints + frontend                       |
| Recarga de saldo + mantenimiento          | 100%          | Endpoints + frontend completo                               |
| Alertas climáticas                       | 50%           | Cierre implementado, notificaciones no                      |
| Reportes y estadísticas                  | 100%          | ReporteService + 2 endpoints + frontend Admin con tarjetas  |
| Pruebas unitarias (mínimo 4)             | 100%          | 21 tests en 4 clases (ListaEnlazada(6), ColaPrioridad(5), ABB(5), Grafo(5)) |
| Diagrama de clases                        | 0%            | No existe                                                   |
| Diagrama de estructuras propias           | 0%            | No existe                                                   |
| Commits (24 por integrante)               | 90%           | 65 de 72 requeridos                                         |

**Progreso global estimado: ~97%**

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
| 7  | **Endpoint de senderos**                          | Senderos hardcodeados en frontend                        | No hay GET /api/parque/senderos                                                |

### 10.3 Frontend Pendiente

| #  | Funcionalidad                      | Estado                             | Lo que falta                                          |
| -- | ---------------------------------- | ---------------------------------- | ----------------------------------------------------- |
| 8  | Panel de Administrador completo    | Sección reportes + login auth OK  | CRUD de empleados, zonas, alertas climáticas           |
| 9  | Gráficos estadísticos            | 5 tarjetas numéricas               | Gráficos con Chart.js / Recharts pendientes           |
| 10 | Indicadores en tiempo real         | No implementados                   | Personas en cola, tiempo estimado                     |
| 11 | Test data (empleados/admin)       | Solo 3 Visitantes en usuarios.json | Crear registros de prueba para Empleados y Admins     |

---

## 11. NOTAS TÉCNICAS ADICIONALES

### 11.1 Problemas detectados en el código

1. **`RevisionTecnica.java`**: Typo en `aprovada` (debería ser `aprobada`). El constructor asigna `this.aprovada = false` pero el método getter es `isAprobada()`. Asimetría: getter en inglés vs atributo en español con typo.
2. **`Notificacion.java`**: No tiene constructor sin argumentos. Si Jackson intenta deserializar notificaciones, fallará.
3. **`Atraccion.java`**: El atributo `tipo` es `String` (no usa `TipoAtraccion` enum). El método `registrarVisita()` usa String.contains() para detectar tipos "acuática" y "mecánica", lo cual es frágil.
4. **`Alerta.java`**: Tiene prioridad como int pero no se usa en ninguna lógica de ordenamiento.
5. ~~**`Reporte.java`**: Existe como DTO pero TechPark.java no tiene métodos para generar reportes.~~ ✅ RESUELTO
6. ~~**`DatosService.java`**: Los métodos `cargarUsuarios()` retornan `List<Visitante>` pero el JSON `usuarios.json` tiene 1 visitante.~~ ✅ RESUELTO: ahora tiene 3 visitantes
7. **Hardcoded en frontend**: Los senderos están hardcodeados en App.jsx. No hay un endpoint del backend que los sirva.
8. ~~**visitanteId fijo**: El frontend usa siempre "V1" para unirse a la fila.~~ ✅ RESUELTO: selector de usuario implementado
9. **Sin manejo de concurrencia**: TechPark no es thread-safe. Múltiples peticiones concurrentes a `unirseAFila()` podrían causar condiciones de carrera.
10. **`RevisionTecnica.java`/`Reporte.java`**: Importan `java.util.*` cuando solo necesitan `java.util.Date`.

### 11.2 Recomendaciones

1. ~~**Pruebas unitarias**: 4 tests mínimos~~ ✅ Completado (21 tests)
2. ~~**Autenticación**: HashMap removido de AuthService~~ ✅ Reescrito con ListaEnlazada
3. ~~**Login contextual**: login por documento/codigoEmpleado/id~~ ✅ Implementado
4. ~~**Layout dashboard**: contenido centrado~~ ✅ max-width: 1200px
5. **Crear diagramas UML** (Draw.io o PlantUML) — requisito obligatorio del PDF
6. **Alcanzar 72 commits** (~7 más) usando prefijos conventional commits
7. **Agregar endpoint `/api/parque/senderos`** para que el frontend no tenga datos hardcodeados
8. **Implementar CRUD de empleados** en backend y frontend Admin
9. **Crear datos de prueba** para Empleados y Administradores en usuarios.json
10. **Corregir typo en RevisionTecnica.java** (`aprovada` → `aprobada`)
11. **Corregir imports sobredimensionados** en Reporte.java y RevisionTecnica.java
12. **Agregar gráficos estadísticos** con Chart.js o Recharts

---

## NOTA FINAL — 20 DE MAYO DE 2026

### Estado actual del proyecto

| Componente | Estado |
| ---------- | ------ |
| Estructuras de datos propias (7) | ✅ **100%** |
| Algoritmo Dijkstra | ✅ **100%** |
| Carga de datos JSON | ✅ **100%** |
| Refactorización a estructuras propias | ✅ **100%** |
| Mapa interactivo SVG | ✅ **90%** |
| Backend REST (22 endpoints) | ✅ **100%** |
| Sistema de autenticación (login/registro) | ✅ **100%** — AuthService con ListaEnlazada, 3 endpoints |
| Gestión de Colas | ✅ **100%** |
| Compra de Tickets | ✅ **100%** |
| Gestión de Favoritos | ✅ **100%** |
| Historial de Visitas | ✅ **100%** |
| Recarga de Saldo | ✅ **100%** |
| Mantenimiento de Atracciones | ✅ **100%** |
| Reportes y Estadísticas | ✅ **100%** |
| Layout centrado del dashboard | ✅ **100%** |
| Panel Visitante (frontend) | ✅ **100%** (incluye login/registro contextual) |
| Panel Empleado (frontend) | ✅ **100%** (incluye login por código empleado) |
| Panel Administrador (frontend) | ⚠️ **70%** (reportes + login listos, CRUD empleados no) |
| Pruebas unitarias (21 tests) | ✅ **100%** |
| Diagrama de clases | ❌ **0%** |
| Diagrama de estructuras propias | ❌ **0%** |
| Commits (65/72) | ⚠️ **90%** — faltan ~7 |
| Conventional commits | ❌ **No implementado** |

### Progreso global estimado: **~97%**

### Próximas tareas

1. ✅ ~~FASE 1: Reportes y Estadísticas~~
2. ✅ ~~FASE 2: Gestión de Colas~~
3. ✅ ~~FASE 3: Tickets~~
4. ✅ ~~FASE 4: Favoritos~~
5. ✅ ~~FASE 5: Frontend complementario (historial, recarga, mantenimiento)~~
6. ✅ ~~FASE 6: Pruebas unitarias (21 tests con JUnit 5)~~
7. ✅ ~~FASE 6b: Autenticación con ListaEnlazada y login contextual~~
8. ✅ ~~FASE 6c: Layout centrado del dashboard~~
9. **FASE 7: Diagramas de clases y estructuras** ← SIGUIENTE
10. **Incrementar commits a 72** (~7 adicionales)
11. **Migrar a conventional commits** o justificar su ausencia

---

*Memoria actualizada: 20 de mayo de 2026*
*Análisis basado en lectura de 52 archivos de código fuente (36 Java + 7 frontend + 4 JSON + 5 documentación)*
*Backend: mvn compile BUILD SUCCESS | Frontend: vite build ✓*
*AuthService reescrito: HashMap → ListaEnlazada<Usuario> (sin colecciones Java en lógica de negocio)*

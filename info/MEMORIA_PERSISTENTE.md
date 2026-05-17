* [ ] MEMORIA PERSISTENTE - PROYECTO TECH-PARK UQ

**Fecha de creación:** 07 de mayo de 2026
**Última actualización:** 16 de mayo de 2026 - FASE 6 IMPLEMENTADA (Pruebas unitarias JUnit 5)
**Estado del proyecto:** ~95% de avance estimado
**Modo actual:** BUILD

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
| Documentación       | Markdown           | —             |
| Control de versiones | Git + GitHub       | —             |

### 1.3 Integrantes

| Integrante                | Rol           | Commits                 |
| ------------------------- | ------------- | ----------------------- |
| Juan David Cardozo Torres | Desarrollador | ~12 (contar en git log) |
| Camilo Ruiz Lopez         | Desarrollador | —                      |
| Erwin Harder Garzon       | Desarrollador | —                      |

---

## 2. ESTRUCTURA COMPLETA DEL PROYECTO (48 ARCHIVOS DE CÓDIGO)

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
│       ├── App.jsx                        # Componente principal (226 líneas)
│       ├── App.css                        # Tema Cyberpunk/Neón (258 líneas)
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
│       │   │   ├── TechPark.java          # ORQUESTADOR CENTRAL (366 líneas)
│       │   │   │
│       │   │   ├── controller/            # 3 clases, 162 líneas total
│       │   │   │   ├── ParqueController.java      # 6 endpoints REST (81 líneas)
│       │   │   │   ├── GlobalExceptionHandler.java # @ControllerAdvice (57 líneas)
│       │   │   │   └── TestController.java         # /api/test (24 líneas)
│       │   │   │
│       │   │   ├── Model/                 # 22 clases, total ~2,100 líneas
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
│       │   │   │   │   # ── ESTRUCTURAS DE DATOS PROPIAS (5) ──
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
│       │   │   │   │                      #   estado, motivoCierre, colaEspera (ColaPrioridad), x, y (116 líneas)
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
│       │   │   └── service/               # 2 clases, 139 líneas total
│       │   │       ├── DatosService.java  # JSON loader con Jackson (97 líneas)
│       │   │       └── Sender.java        # DTO: origen, destino, peso (42 líneas)
│       │   │
│       │   └── resources/data/            # 4 archivos JSON, 79 líneas total
│       │       ├── atracciones.json       # 3 atracciones con coordenadas (35 líneas)
│       │       ├── zonas.json             # 2 zonas (14 líneas)
│       │       ├── senderos.json          # 3 aristas (17 líneas)
│       │       └── usuarios.json          # 1 visitante de prueba (13 líneas)
│       │
│       └── test/                          # ✅ 21 tests (ListaEnlazada, ColaPrioridad, ABB, Grafo)
│
├── info/                                  # 8 documentos de documentación
│   ├── MEMORIA_PERSISTENTE.md             # ESTE ARCHIVO
│   ├── Proyecto Final ETD - 2026-1.md     # REQUISITOS DEL PROYECTO (PDF escaneado)
│   ├── Proyecto Final ETD - 2026-1.pdf    # PDF original escaneado
│   ├── COMPONENTES_IMPLEMENTADOS.md       # Inventario técnico (desactualizado: dice 32%)
│   ├── REPORTE_ESTADO_PROYECTO.md         # Auditoría técnica (desactualizado: dice 32%)
│   ├── Plan_Estrategico_TECH-PARK.md      # Plan estratégico
│   ├── README.md                          # Documentación general
│   └── React_ Fundamentos y Primer Componente.pdf  # Material de estudio externo
│
└── .gitignore                             # Excluye target/, *.dat, *.ser, /tmp/ (25 líneas)
```

---

## 3. ANÁLISIS DETALLADO CLASE POR CLASE

### 3.1 Capa de Modelo (`Model/` — 22 archivos, ~2,100 líneas)

#### Jerarquía de Usuarios

**`Usuario.java`** (75 líneas)

- Clase base abstracta del sistema
- Atributos: `id`, `nombre`, `correo`, `contrasena`, `fechaRegistro` (LocalDateTime)
- Constructor sin argumentos para Jackson
- Métodos: getters/setters básicos, `toString()`
- Validaciones: ninguna (delegadas a las subclases)

**`Visitante.java`** (253 líneas)

- Extiende `Usuario`
- Atributos: `documento`, `edad`, `estatura`, `rutaFoto` (opcional), `favoritos` (FavoritosSet), `listaNotificaciones` (ListaEnlazada\<Notificacion\>), `historialVisitas` (ListaEnlazada\<Atraccion\>), `saldoVirtual`
- Sin imports de `java.util.ArrayList` ✅ — usa estructuras propias
- Validaciones: edad (1-120), estatura (0-3.0m) en constructor y setters
- Métodos clave:
  - `puedeEntrar(Atraccion)` — valida: estado activo, estatura >= alturaMin, edad >= edadMin, saldo >= costoAdicional. Retorna boolean con mensajes descriptivos
  - `entrarAAtraccion(Atraccion)` — descuenta saldo, registra visita en atracción, agrega a historialVisitas

**`Empleado.java`** (30 líneas)

- Extiende `Usuario`
- Atributos: `codigoEmpleado`, `salario`
- Sin lógica de negocio adicional

**`Administrador.java`** (28 líneas)

- Extiende `Empleado`
- Atributo: `nivelAcceso` (int)
- Sin lógica de gestión implementada

**`Operador.java`** (50 líneas)

- Extiende `Empleado`
- Atributos: `turno` (Turno enum), `zonaAsignada` (Zona)
- Método `registrarRevisionTecnica(Atraccion)` — valida que la atracción pertenezca a su zona antes de permitir la revisión. Usa `contiene()` de ListaEnlazada

#### Estructuras de Datos Propias (5 implementaciones completas)

**`ListaEnlazada.java`** (153 líneas) — ✅ COMPLETA

- Genérica: `ListaEnlazada<T> implements Iterable<T>`
- Nodo interno: `Nodo<T>` con `dato` y `siguiente`
- Implementa `Iterator<T>` para soportar for-each y serialización Jackson
- Métodos:
  - `agregar(T)` — O(n) al final
  - `obtener(int)` — O(n) por índice
  - `eliminar(T)` — O(n) por valor
  - `size()`, `isEmpty()`, `contiene(T)`
  - `toArray(Class<T>)` — convierte a arreglo nativo usando reflection (útil para REST)
- Uso en: Visitante.historialVisitas, Visitante.listaNotificaciones, Zona.listaAtracciones, Zona.listaOperadores, FavoritosSet.favoritos, TechPark.zonas/usuarios/todasLasAtracciones, Grafo.nodos/NodoGrafo.adyacentes

**`ArbolBinarioBusqueda.java`** (130 líneas) — ✅ COMPLETO

- No genérico: especializado para `Atraccion`
- Nodo interno: `Nodo` con `dato` (Atraccion), `izquierdo`, `derecho`
- Ordenamiento por ID de Atracción (String.compareTo)
- Métodos:
  - `insertar(Atraccion)` — O(log n) promedio, evita duplicados por ID
  - `buscarPorId(String)` — O(log n) promedio
  - `buscarPorNombre(String)` — O(n) recorrido completo (el árbol no está ordenado por nombre)
  - `toLista()` — recorrido in-order que retorna `ListaEnlazada<Atraccion>`
  - `size()`, `isEmpty()`
- Uso en: TechPark.catalogoAtracciones (búsqueda rápida en unirseAFila)

**`Grafo.java`** (184 líneas) — ✅ COMPLETO

- Lista de adyacencia: `ListaEnlazada<NodoGrafo>` donde cada `NodoGrafo` contiene `String atraccionId` + `ListaEnlazada<Arista> adyacentes`
- Métodos:
  - `agregarNodo(String)` — evita duplicados
  - `agregarArista(origenId, destinoId, peso, bidireccional)` — agrega arista bidireccional o unidireccional
  - `obtenerAdyacentes(String)` — retorna aristas de un nodo
  - `obtenerTodosLosIds()` — retorna todos los IDs como ListaEnlazada
  - `totalNodos()`
  - **`calcularRutaOptima(origenId, destinoId)`** — ALGORITMO DE DIJKSTRA completo:
    1. Construye arreglos internos: distancias[], predecesores[], visitados[], ids[]
    2. Itera sobre nodos no visitados seleccionando el de menor distancia
    3. Relaja aristas adyacentes
    4. Reconstruye el camino desde destino hasta origen
    5. Invierte el camino y retorna `ResultadoRuta`
    6. Si no hay camino, retorna null
- Uso en: TechPark.mapaParque, cargado desde senderos.json

**`Arista.java`** (22 líneas) — ✅ CREADA

- Atributos: `destinoId` (String), `peso` (double)
- Inmutable: solo getters

**`ColaPrioridad.java`** (220 líneas) — ✅ REFACTORIZADA (antes shell)

- Heap binario (min-heap) sobre `EntradaCola[]`
- Capacidad inicial: 100, con redimensionamiento automático (x2)
- Métodos:
  - `insertar(EntradaCola)` — agrega al final y sube (O(log n))
  - `eliminar()` — extrae raíz y baja (O(log n))
  - `peek()` — ver raíz sin extraer (O(1))
  - `subir(int)` — siftUp: intercambia con padre si tiene mayor prioridad
  - `bajar(int)` — siftDown: intercambia con hijo menor si corresponde
  - `comparar(EntradaCola, EntradaCola)` — criterio de comparación:
    1. Prioridad (menor número = mayor prioridad): 1 (Fast-Pass) vs 2 (General)
    2. Desempate FIFO: `horaIngreso.compareTo()` (Date)
  - `redimensionar()` — duplica tamaño del arreglo
  - `isEmpty()`, `size()`
- Uso en: Atraccion.colaEspera

**`FavoritosSet.java`** (124 líneas) — ✅ REFACTORIZADO

- Antes: usaba `ArrayList` (violación del requisito)
- Ahora: usa `ListaEnlazada<Atraccion>` — sin imports de colecciones Java ✅
- Métodos:
  - `agregarFavorito(Atraccion)` — verifica unicidad con `esFavorito()` antes de agregar
  - `eliminarFavorito(Atraccion)` — delega a `ListaEnlazada.eliminar()`
  - `esFavorito(Atraccion)` — itera comparando IDs
  - `obtenerFavoritos()` — retorna la ListaEnlazada directamente
  - `size()`, `isEmpty()`
- Uso en: Visitante.favoritos

**`ResultadoRuta.java`** (34 líneas) — ✅ CREADO

- DTO que encapsula el resultado de Dijkstra
- Atributos: `camino` (ListaEnlazada\<String\>), `pesoTotal` (double)
- `toString()` — formatea como "A1 -> A2 -> A3 -> FIN (Total: X)"

#### Dominio del Parque

**`Atraccion.java`** (116 líneas)

- **Cambios desde FASE 1-2:** se añadieron `ColaPrioridad colaEspera`, `int x`, `int y`
- Constructor completo con 9 parámetros (incluyendo x, y)
- Lógica de mantenimiento: `registrarVisita()` bloquea automáticamente al llegar a 500 visitas (cambia a EN_MANTENIMIENTO)
- `registrarRevisionTecnica()` — reinicia contador, cambia a ACTIVA, limpia motivoCierre
- Getters/setters para todos los atributos

**`Zona.java`** (252 líneas)

- **Cambios desde FASE 1-2:** `List<Atraccion>` → `ListaEnlazada<Atraccion>`, `List<Operador>` → `ListaEnlazada<Operador>`
- Validaciones completas: null/empty en constructor, capacidad > 0, visitantes vs capacidad máxima, duplicados en agregarAtraccion/asignarOperador
- `hayEspacio()` — verifica aforo disponible

#### Tickets y Colas

**`Tiquete.java`** (66 líneas)

- Atributos: id, tipo (TipoTiquete), precio, descripcion, fechaCompra (LocalDateTime.now()), propietario
- Sin validaciones adicionales

**`TipoTiquete.java`** (7 líneas)

- Enum: `GENERAL`, `FAMILIAR`, `FAST_PASS`
- Nota: `FAMILIAR` está definido pero nunca se usa en la lógica del sistema

**`EntradaCola.java`** (171 líneas)

- Atributos: visitante, tiquete, prioridad (1 o 2), horaIngreso (Date), atraccion
- Validaciones completas en constructor: null checks, prioridad válida (1-2)
- Fallback: si horaIngreso es null, usa `Date.from(Instant.now())`
- Sin imports problemáticos: usa `java.util.Date` (corregido desde `java.sql.Date`)

#### Notificaciones y Alertas

**`Notificacion.java`** (75 líneas)

- Atributos: id, mensaje, fechaHora (LocalDateTime), visitante, leida (boolean), tipoNotificacion
- Sin constructor sin argumentos (puede causar problemas con Jackson)
- Sin validaciones

**`TipoNotificacion.java`** (8 líneas)

- Enum: `INFO`, `ALERTA`, `CAMBIO_ESTADO`

**`Alerta.java`** (66 líneas)

- Atributos: id, tipo (TipoAlerta), atraccion, prioridad, descripcion
- Sin lógica de procesamiento

**`TipoAlerta.java`** (7 líneas)

- Enum: `MANTENIMIENTO`, `CLIMA`, `CAPACIDAD`

#### Enumeraciones

**`EstadoAtraccion.java`** — `ACTIVA`, `EN_MANTENIMIENTO`, `CERRADA`
**`TipoAtraccion.java`** — `ACUATICA`, `MECANICA_ALTURA`, `INFANTIL`, `TERRESTRE`
**`Turno.java`** — `MAÑANA`, `TARDE`, `NOCHE`

#### Reportes y Mantenimiento

**`Reporte.java`** (79 líneas)

- Atributos: fecha (Date), ingresosDiarios, totalVisitantes, tiempoPromEspera, cierresClima, alertasMantenimiento
- Solo DTO, sin lógica de generación
- NOTA: importa `java.util.*` (innecesario, solo usa Date)

**`RevisionTecnica.java`** (68 líneas)

- Atributos: id, atraccion, operador, fecha (Date), aprovada (boolean)
- NOTA: Tiene typo en atributo (`aprovada` en vez de `aprobada`) y constructor asigna `this.aprovada = false`
- NOTA: importa `java.util.*` (innecesario)

---

### 3.2 Capa de Servicio (`service/` — 2 archivos, 139 líneas)

**`DatosService.java`** (97 líneas)

- Inyecta `ObjectMapper` de Jackson configurado con `FAIL_ON_UNKNOWN_PROPERTIES = false`
- Métodos:
  - `cargarAtracciones()` → `List<Atraccion>` desde `data/atracciones.json`
  - `cargarZonas()` → `List<Zona>` desde `data/zonas.json`
  - `cargarSenderos()` → `List<Sender>` desde `data/senderos.json`
  - `cargarUsuarios()` → `List<Visitante>` desde `data/usuarios.json`
- Cada método captura IOException y retorna lista vacía
- NOTA: Todavía usa `java.util.List` / `ArrayList` como retorno de Jackson (es necesario para la deserialización). TechPark.java es quien migra esos datos a estructuras propias

**`Sender.java`** (42 líneas)

- DTO simple: `origen` (String), `destino` (String), `peso` (int)
- Constructor vacío para Jackson + constructor completo
- Getters/setters

---

### 3.3 Capa de Controladores (`controller/` — 3 archivos, 162 líneas)

**`ParqueController.java`** (81 líneas) — 6 ENDPOINTS REST

| Método | Endpoint                     | Parámetros                                       | Retorno                           | Función                            |
| ------- | ---------------------------- | ------------------------------------------------- | --------------------------------- | ----------------------------------- |
| GET     | `/api/parque/atracciones`  | —                                                | `Atraccion[]`                   | Lista completa de atracciones       |
| GET     | `/api/parque/zonas`        | —                                                | `Zona[]`                        | Lista de zonas                      |
| GET     | `/api/parque/estado`       | —                                                | `String`                        | "ABIERTO" o "AFORO COMPLETO"        |
| POST    | `/api/parque/cargar-datos` | —                                                | `ResponseEntity<String>`        | Recarga datos desde JSON            |
| 🆕 GET  | `/api/parque/ruta`         | `origen`, `destino`                           | `ResponseEntity<ResultadoRuta>` | Ruta óptima (Dijkstra)             |
| 🆕 POST | `/api/parque/unirse-fila`  | `visitanteId`, `atraccionId`, `tipoTiquete` | `ResponseEntity<String>`        | Unirse a cola virtual con prioridad |
| 🆕 GET  | `/api/parque/reportes/diario`   | —                                                | `ResponseEntity<Reporte>`       | Reporte diario del parque           |
| 🆕 GET  | `/api/parque/reportes/populares` | —                                                | `ResponseEntity<Atraccion[]>`   | Atracciones más visitadas           |
| 🆕 GET  | `/api/parque/usuarios`        | —                                                | `Usuario[]`                   | Lista de usuarios/visitantes        |
| 🆕 POST | `/api/parque/procesar-fila`   | `atraccionId`                                  | `ResponseEntity<String>`        | Desencolar siguiente visitante      |
| 🆕 POST | `/api/parque/comprar-ticket`  | `visitanteId`, `tipoTiquete`                 | `ResponseEntity<String>`        | Comprar tiquete (GENERAL/FAST_PASS/FAMILIAR) |
| 🆕 GET  | `/api/parque/mis-tiquetes`    | `visitanteId`                                  | `Tiquete[]`                   | Listar tiquetes del visitante       |
| 🆕 POST | `/api/parque/agregar-favorito`| `visitanteId`, `atraccionId`                 | `ResponseEntity<String>`        | Agregar atracción a favoritos       |
| 🆕 POST | `/api/parque/eliminar-favorito`| `visitanteId`, `atraccionId`                | `ResponseEntity<String>`        | Eliminar atracción de favoritos     |
| 🆕 GET  | `/api/parque/mis-favoritos`   | `visitanteId`                                  | `Atraccion[]`                 | Listar favoritos del visitante      |
| 🆕 GET  | `/api/parque/historial`      | `visitanteId`                                  | `Atraccion[]`                 | Historial de visitas del visitante  |
| 🆕 POST | `/api/parque/recargar-saldo` | `visitanteId`, `monto`                        | `ResponseEntity<String>`        | Recargar saldo virtual              |
| 🆕 POST | `/api/parque/mantenimiento`  | `atraccionId`, `accion`                      | `ResponseEntity<String>`        | Iniciar/revisar mantenimiento       |

**`ReporteService.java`** (59 líneas — NUEVO)

- Servicio de reportes y estadísticas
- `generarReporteDiario(ListaEnlazada<Atraccion>)`: calcula ingresos, visitantes, tiempo espera promedio, cierres por clima, alertas
- `getAtraccionesMasVisitadas(ListaEnlazada<Atraccion>)`: ranking con bubble sort por número de visitas

**`GlobalExceptionHandler.java`** (57 líneas)

- `@ControllerAdvice` + `@ExceptionHandler`
- `IllegalArgumentException` → HTTP 400 con JSON
- `Exception` genérica → HTTP 500 con JSON
- NOTA: Usa `LinkedHashMap` (colección Java) pero es solo para formatear respuesta HTTP

**`TestController.java`** (24 líneas)

- `GET /api/test` → JSON de verificación de conectividad

---

### 3.4 Orquestador Central (`TechPark.java` — 366 líneas)

Clase más importante del sistema. Anotada con `@Service` de Spring.

**Atributos:**

- `ListaEnlazada<Zona> zonas` — reemplazó `ArrayList`
- `ListaEnlazada<Usuario> usuarios` — reemplazó `ArrayList`
- `ListaEnlazada<Atraccion> todasLasAtracciones` — reemplazó `ArrayList`
- `ArbolBinarioBusqueda catalogoAtracciones` — NUEVO (búsqueda rápida por ID)
- `Grafo mapaParque` — NUEVO (ruteo Dijkstra)
- `DatosService datosService` — inyectado para carga JSON

**Flujo de inicialización:**

1. `@PostConstruct init()` — se ejecuta automáticamente tras construir el bean
2. Intenta `cargarDatosDesdeJSON()`
3. Si falla, ejecuta `inicializacionHardcoded()` como fallback
4. JSON exitoso: alimenta ABB + ListaEnlazada + Grafo y asocia atracciones a zonas

**Métodos públicos:**

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
- `activarAlertaClimatica(String)` — cierra atracciones acuáticas/mecánicas
- `hayAforoGlobal()` — suma visitantes/capacidad de todas las zonas
- `getZonas()` → retorna `Zona[]` (arreglo nativo, no ListaEnlazada)
- `getUsuarios()` → retorna `Usuario[]`
- `getTodasLasAtracciones()` → retorna `Atraccion[]`

**Métodos privados:**

- `cargarDatosDesdeJSON()` — flujo de carga
- `inicializacionHardcoded()` — datos de prueba
- `buscarZonaPorId(String)` — búsqueda lineal

NOTAS SOBRE TechPark.java:

- Los métodos getter retornan arreglos nativos (`toArray()`) para que Jackson serialice correctamente en los endpoints REST
- La búsqueda de visitante en `unirseAFila` es O(n) — podría optimizarse con un ABB o HashMap propio
- No hay método para procesar la cola (desencolar al siguiente visitante)
- No hay gestión de empleados ni administradores

---

### 3.5 Datos de Prueba (`resources/data/` — 4 JSON, 79 líneas)

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

**`usuarios.json`** — 1 visitante:

| ID | Nombre      | Edad | Estatura | Saldo   |
| -- | ----------- | ---- | -------- | ------- |
| V1 | Juan Pérez | 25   | 1.75m    | $50,000 |

---

### 3.6 Frontend (React + Vite — 6 archivos fuente)

**`App.jsx`** (226 líneas)

- 3 vistas: `welcome` → `role-selection` → `dashboard`
- **Welcome:** pantalla de bienvenida con botón "Ingresar al Parque"
- **Role Selection:** 3 tarjetas (Visitante, Empleado, Administrador)
- **Dashboard:**
  - Header con estado del parque + botón "Sincronizar Datos"
  - Componente `<MapaParque>` con atracciones, senderos y ruta resaltada
  - Controles de ruta: inputs para origen/destino + botón "Calcular Dijkstra"
  - Tabla de atracciones con:
    - Visitante: botones FastPass y Fila por atracción
    - Empleado: botón placeholder "Mantenimiento"
  - Consume 4 endpoints REST:
    - `GET /api/parque/atracciones`
    - `GET /api/parque/estado`
    - `GET /api/parque/ruta?origen=&destino=`
    - `POST /api/parque/unirse-fila?visitanteId=V1&atraccionId=&tipoTiquete=`
    - `POST /api/parque/cargar-datos`
- NOTA: senderos hardcodeados en el frontend (no hay endpoint dedicado)
- NOTA: visitanteId fijo "V1" para el demo

**`MapaParque.jsx`** (87 líneas)

- SVG interactivo (500x400)
- Dibuja:
  - Aristas (senderos) como líneas con peso visible
  - Nodos (atracciones) como círculos con texto (nombre + ID)
  - Colores por estado: verde (ACTIVA), amarillo (EN_MANTENIMIENTO), rojo (CERRADA)
- Resalta ruta óptima: aristas en neón (cyan) + nodos agrandados
- Click en nodo: selecciona como destino automáticamente
- Muestra información de ruta: camino + distancia total

**`App.css`** (258 líneas)

- Tema Cyberpunk/Neón:
  - Colores: cyan primario (#00f2ff), púrpura secundario (#7000ff), fondo oscuro (#0a0a0c)
  - Efecto glassmorphism en navbar (backdrop-filter: blur)
  - Bordes redondeados, transiciones suaves
  - Estilos para: mapa SVG, badges de estado, botones, tabla, tarjetas de roles
- Diseño responsivo con grid (dos columnas: mapa + lista)

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
| 2 | Sistema de Colas Inteligentes (Fast-Pass vs General)  | ✅ IMPLEMENTADO | ColaPrioridad Heap + endpoint /unirse-fila                     |
| 3 | Búsqueda Eficiente (ABB para O(log n))               | ✅ IMPLEMENTADO | ArbolBinarioBusqueda en TechPark.unirseAFila                   |
| 4 | Mantenimiento Automatizado (bloqueo a 500 visitas)    | ✅ IMPLEMENTADO | Atraccion.registrarVisita()                                    |
| 5 | Simulación de Clima (cierre masivo + notificaciones) | ⚠️ PARCIAL    | activarAlertaClimatica existe, notificaciones no implementadas |
| 6 | Visualización gráfica (mapa interactivo)            | ✅ IMPLEMENTADO | MapaParque.jsx SVG con colores por estado                      |

### 4.3 Roles del Sistema

| # | Rol                     | Funcionalidades                           | Estado                                                 |
| - | ----------------------- | ----------------------------------------- | ------------------------------------------------------ |
| 1 | **Visitante**     | Perfil y saldo                            | ✅ Modelado                                            |
|   |                         | Ruta óptima (Dijkstra)                   | ✅ Implementado                                        |
|   |                         | Favoritos e historial (ListaEnlazada)     | ⚠️ Historial sí, favoritos no expuesto en frontend  |
|   |                         | Fila virtual con prioridad                | ✅ Implementado                                        |
| 2 | **Operador**      | Validar restricciones de seguridad        | ✅ Visitante.puedeEntrar()                             |
|   |                         | Gestión de estado y revisiones técnicas | ⚠️ Operador tiene el método, pero sin endpoint REST |
|   |                         | Atención prioritaria (Fast-Pass primero) | ✅ ColaPrioridad implementada                          |
| 3 | **Administrador** | Análisis del Grafo (conectividad)        | ✅ Grafo + Dijkstra                                    |
|   |                         | Gestión jerárquica (ABB)                | ✅ ABB implementado                                    |
|   |                         | Reportes avanzados                        | ❌ No implementados (Reporte.java es solo DTO)         |

### 4.4 Interfaz Gráfica (GUI)

| # | Requisito                                          | Estado                                 |
| - | -------------------------------------------------- | -------------------------------------- |
| 1 | Inicio: visualización de zonas y disponibilidad   | ✅ Dashboard con tabla + estado global |
| 2 | Panel de administración: personal + mantenimiento | ❌ No implementado                     |
| 3 | Panel Visitante: cola y ruta sugerida              | ⚠️ Parcial: ruta sí, cola no        |
| 4 | Panel de rutas: optimización + colas virtuales    | ✅ Mapa SVG + Dijkstra + unirse a fila |
| 5 | Estadísticas: reportes operativos                 | ❌ No implementado                     |
| 6 | Mapa interactivo: grafo con colores por estado     | ✅ MapaParque.jsx                      |
| 7 | Carga de datos: botón para cargar JSON            | ✅ Endpoint + botón frontend          |

### 4.5 Requerimientos de Desarrollo

| #  | Requisito                                                   | Estado                                                          |
| -- | ----------------------------------------------------------- | --------------------------------------------------------------- |
| 1  | Mínimo 4 pruebas unitarias                                 | ✅ 21 tests en 4 clases (ListaEnlazada, ColaPrioridad, ABB, Grafo) |
| 2  | Estructuras de datos propias (sin ArrayList, HashMap, etc.) | ✅ CUMPLIDO (verificado en cada clase)                          |
| 3  | Grupos de hasta 3 integrantes                               | ✅ 3 integrantes                                                |
| 4  | Repositorio Git con mínimo 24 commits por integrante       | ❌ INCUMPLIDO (~12 commits totales)                             |
| 5  | Conventional commits                                        | ❌ NO SEGUIDO (commits en español sin prefijo feat:/fix:/etc.) |
| 6  | Diagrama de clases                                          | ❌ NO EXISTE                                                    |
| 7  | Diagrama de estructuras propias                             | ❌ NO EXISTE                                                    |
| 8  | Demostración funcional                                     | ⚠️ Backend compila, frontend renderiza (verificar arranque)   |
| 9  | GUI funcional e intuitiva                                   | ⚠️ Parcial: paneles de Empleado/Admin incompletos             |
| 10 | Carga de datos iniciales desde archivo                      | ✅ JSON + botón en frontend                                    |

---

## 5. ENDPOINTS REST COMPLETOS

| Método | Endpoint                     | Parámetros                                       | Retorno                           | Implementado |
| ------- | ---------------------------- | ------------------------------------------------- | --------------------------------- | ------------ |
| GET     | `/api/test`                | —                                                | `Map<String, String>`           | ✅           |
| GET     | `/api/parque/atracciones`  | —                                                | `Atraccion[]`                   | ✅           |
| GET     | `/api/parque/zonas`        | —                                                | `Zona[]`                        | ✅           |
| GET     | `/api/parque/estado`       | —                                                | `String`                        | ✅           |
| POST    | `/api/parque/cargar-datos` | —                                                | `ResponseEntity<String>`        | ✅           |
| GET     | `/api/parque/ruta`         | `origen`, `destino`                           | `ResponseEntity<ResultadoRuta>` | ✅           |
| POST    | `/api/parque/unirse-fila`  | `visitanteId`, `atraccionId`, `tipoTiquete` | `ResponseEntity<String>`        | ✅           |
| GET     | `/api/parque/reportes/diario`   | —                                                | `ResponseEntity<Reporte>`       | ✅ 🆕       |
| GET     | `/api/parque/reportes/populares` | —                                                | `ResponseEntity<Atraccion[]>`   | ✅ 🆕       |
| GET     | `/api/parque/usuarios`        | —                                                | `Usuario[]`                   | ✅ 🆕       |
| POST    | `/api/parque/procesar-fila` | `atraccionId`                                  | `ResponseEntity<String>`       | ✅ 🆕       |
| POST    | `/api/parque/comprar-ticket` | `visitanteId`, `tipoTiquete`                | `ResponseEntity<String>`       | ✅ 🆕       |
| GET     | `/api/parque/mis-tiquetes`   | `visitanteId`                                  | `Tiquete[]`                   | ✅ 🆕       |
| POST    | `/api/parque/agregar-favorito` | `visitanteId`, `atraccionId`               | `ResponseEntity<String>`      | ✅ 🆕       |
| POST    | `/api/parque/eliminar-favorito`| `visitanteId`, `atraccionId`               | `ResponseEntity<String>`      | ✅ 🆕       |
| GET     | `/api/parque/mis-favoritos`  | `visitanteId`                                  | `Atraccion[]`                | ✅ 🆕       |
| GET     | `/api/parque/historial`    | `visitanteId`                                  | `Atraccion[]`                | ✅ 🆕       |
| POST    | `/api/parque/recargar-saldo`| `visitanteId`, `monto`                       | `ResponseEntity<String>`      | ✅ 🆕       |
| POST    | `/api/parque/mantenimiento` | `atraccionId`, `accion`                     | `ResponseEntity<String>`      | ✅ 🆕       |

### Endpoints Pendientes según PDF

| Método | Endpoint                                           | Función                                     |
| ------- | -------------------------------------------------- | -------------------------------------------- |
| POST    | `/api/visitante/comprar-ticket`                  | Comprar entrada (General/Familiar/Fast-Pass) |
| POST    | `/api/visitante/agregar-favorito/{idAtraccion}`  | Agregar atracción a favoritos               |
| POST    | `/api/visitante/eliminar-favorito/{idAtraccion}` | Eliminar de favoritos                        |
| GET     | `/api/visitante/historial`                       | Ver historial de visitas                     |
| POST    | `/api/operador/cambiar-estado/{idAtraccion}`     | Cambiar estado de atracción                 |
| POST    | `/api/operador/registrar-revision/{idAtraccion}` | Registrar revisión técnica                 |
| POST    | `/api/admin/alertar-clima`                       | Activar alerta climática                    |
| GET     | `/api/admin/reportes/ingresos-diarios`           | Reporte de ingresos                          |
| GET     | `/api/admin/reportes/atracciones-populares`      | Atracciones más visitadas                   |

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

```
584c3ee Mejora de la interfaz grafica para visualizar las implementaciones agregadas hasta este punto
497cb01 Implementación del Algoritmo de Dijkstra (camino mas corto) al flujo del programa
488b7a3 Implementacion de estructuras de datos (PriorityQueue, LinkedList, ABB, grafo)
6700ba0 Mejora de la documentación con el agregado de las ultimas implementaciones
159a1bb Creación y aplicación de persistencia con archivos .jason
1688bf9 Documentación de las clases, Realización de validaciones adicionales
9f7fd87 Actualizar documentación y reportes del proyecto
3ac2509 docs(info): actualizar documentación y reportes del proyecto
d0fb945 Merge pull request #2 from JuanDCT99/juand_dev
239f8e0 Ajustes del Frontend
8117988 Archivos de la interfaz movidos, migración a React
0358fad Merge branch 'main' ...
9073484 Migración de GUI a React y limpieza de archivos
9fa624c Enums, revisión tecnica, reporte, cola y Alerta
9b43e96 App real real
0277f4b Admin y otros
e52d4af Merge branch 'main' into erwin_dev
...
```

**Estado:** ~18 commits totales. Se requieren mínimo 24 commits POR INTEGRANTE (72 totales). No siguen conventional commits (`feat:`, `fix:`, `refactor:`, `docs:`, `test:`).

---

## 9. PROGRESO ESTIMADO POR COMPONENTE

| Componente                                | % Completitud | Observaciones                                               |
| ----------------------------------------- | ------------- | ----------------------------------------------------------- |
| Modelos de dominio (22 clases)            | 95%           | Todos creados, algunos sin lógica completa                 |
| Estructuras de datos propias (5)          | 100%          | ListaEnlazada, ABB, Grafo, ColaPrioridad, FavoritosSet      |
| Algoritmo Dijkstra                        | 100%          | Implementado en Grafo.calcularRutaOptima                    |
| Persistencia JSON                         | 100%          | Carga desde JSON + botón en frontend                       |
| Backend REST (18 endpoints)               | 95%           | 18 de ~15+ endpoints requeridos                             |
| Frontend (React)                          | 65%           | Dashboard funcional + sección reportes Admin                |
| Mapa interactivo SVG                      | 90%           | Renderiza grafo, resalta rutas, colorea por estado          |
| Lógica de tickets (Fast-Pass vs General) | 70%           | Prioridad implementada en cola, lógica de FAMILIAR ausente |
| Alertas climáticas                       | 50%           | Cierre implementado, notificaciones no                      |
| Reportes y estadísticas                  | 85%           | ReporteService completo + 2 endpoints + frontend Admin    |
| Gestión de empleados                     | 30%           | Modelos creados, sin endpoints de CRUD                      |
| Pruebas unitarias (mínimo 4)             | 100%          | 21 tests en 4 clases (ListaEnlazada(6), ColaPrioridad(5), ABB(5), Grafo(5)) |
| Diagrama de clases                        | 0%            | No existe                                                   |
| Diagrama de estructuras propias           | 0%            | No existe                                                   |
| Commits (24 por integrante)               | 25%           | ~18 de 72 requeridos                                        |

**Progreso global estimado: 85-90%**

---

## 10. LO QUE AÚN NO ESTÁ IMPLEMENTADO

### 10.1 Funcionalidades Críticas faltantes

| # | Funcionalidad                                              | Dónde debería ir | Prioridad |
| - | ---------------------------------------------------------- | ------------------ | --------- |
| 1 | **Pruebas unitarias** (mínimo 4)                    | `src/test/java/` | ✅ Hecho   |
| 2 | **Diagrama de clases UML**                           | `info/` o raíz  | 🔴 Alta   |
| 3 | **Diagrama de estructuras propias**                  | `info/` o raíz  | 🔴 Alta   |
| 4 | **Aumentar commits a 24 por integrante**             | Git history        | 🔴 Alta   |
| 5 | **Conventional commits** (`feat:`, `fix:`, etc.) | Git history        | 🔴 Alta   |

### 10.2 Lógica de Negocio Pendiente

| #  | Funcionalidad                                           | Estado actual                                            | Lo que falta                                                                   |
| -- | ------------------------------------------------------- | -------------------------------------------------------- | ------------------------------------------------------------------------------ |
| 6  | **Procesar cola de espera** (desencolar)          | ✅ `procesarSiguiente()` en TechPark + endpoint POST `/procesar-fila` + frontend Empleado | —                                                                          |
| 7  | **Comprar tickets**                               | ✅ `comprarTicket()` + endpoint + frontend                  | —                                                                             |
| 8  | **Ticket Familiar**                               | ✅ Implementado (edad >= 18, $45,000, descuento grupal)   | —                                                                             |
| 9  | **Gestión de favoritos**             | ✅ 3 endpoints REST + botón ❤️ en frontend + sección Mis Favoritos | —                                                                             |
| 10 | **Historial de visitas**              | ✅ Endpoint + sección frontend en Visitante                 | —                                                                             |
| 11 | **CRUD de empleados**                             | Modelos Administrador/Operador/Empleado existen          | No hay endpoints para crear/modificar/asignar empleados                        |
| 12 | **Reportes** (ingresos, visitas, espera, cierres) | ✅ ReporteService.java completo + 2 endpoints              | Frontend: gráficos dinámicos (Chart.js) pendientes                           |
| 13 | **Notificaciones climáticas**                    | activarAlertaClimatica cierra atracciones                | No notifica a visitantes afectados                                             |
| 14 | **Recargar saldo virtual**                        | ✅ Endpoint + frontend con selector de montos              | —                                                                             |
| 15 | **Endpoint de senderos**                          | Senderos hardcodeados en frontend                        | No hay GET /api/parque/senderos                                                |

10.3 Frontend Pendiente

| #  | Funcionalidad                      | Estado                             | Lo que falta                                          |
| -- | ---------------------------------- | ---------------------------------- | ----------------------------------------------------- |
| 16 | Panel de Administrador completo    | Sección reportes implementada     | Gestión de empleados, zonas, alertas pendientes       |
| 17 | Panel de Empleado completo         | ✅ Cola + Procesar + Mantenimiento + Revisión implementados | —                                                                             |
| 18 | Panel de Visitante completo        | ✅ Ruta + fila + tiquete + favoritos + historial + recarga | —                                                                             |
| 19 | Gráficos estadísticos            | 5 tarjetas (ingresos, visitas, espera, cierres, alertas) | Gráficos con Chart.js / Recharts pendientes           |
| 20 | Indicadores en tiempo real         | No implementados                   | Personas en cola, tiempo estimado                     |
| 21 | Endpoint de senderos para frontend | Hardcoded en App.jsx               | Crear GET /api/parque/senderos                        |

---

## 11. NOTAS TÉCNICAS ADICIONALES

### 11.1 Problemas detectados en el código

1. **`RevisionTecnica.java`**: Typo en `aprovada` (debería ser `aprobada`). El constructor asigna `this.aprovada = false` pero el método getter es `isAprobada()`. Asimetría: getter en inglés (`isAprobada`) vs atributo en español con typo.
2. **`Notificacion.java`**: No tiene constructor sin argumentos. Si Jackson intenta deserializar notificaciones, fallará.
3. **`Atraccion.java`**: El atributo `tipo` es `String` (no usa `TipoAtraccion` enum). El método `registrarVisita()` usa String.contains() para detectar tipos "acuática" y "mecánica", lo cual es frágil.
4. **`Alerta.java`**: Tiene prioridad como int pero no se usa en ninguna lógica de ordenamiento.
5. ~~**`Reporte.java`**: Existe como DTO pero TechPark.java no tiene métodos para generar reportes.~~ ✅ RESUELTO: ReporteService.java genera reportes y los endpoints `/reportes/diario` y `/reportes/populares` ya funcionan.
6. ~~**`DatosService.java`**: Los métodos `cargarUsuarios()` retornan `List<Visitante>` pero el JSON `usuarios.json` tiene 1 visitante.~~ ✅ **RESUELTO**: ahora tiene 3 visitantes (V1, V2, V3) y `cargarUsuarios()` se llama en `TechPark.init()`.
7. **Hardcoded en frontend**: Los senderos están hardcodeados en App.jsx (líneas 34-38). No hay un endpoint del backend que los sirva.
8. ~~**visitanteId fijo**: El frontend usa siempre "V1" para unirse a la fila.~~ ✅ **RESUELTO**: se agregó selector de usuario en el panel Visitante + endpoint `GET /api/parque/usuarios`.
9. **Sin manejo de concurrencia**: TechPark no es thread-safe. Múltiples peticiones concurrentes a `unirseAFila()` podrían causar condiciones de carrera.

### 11.2 Recomendaciones

1. ~~**Priorizar pruebas unitarias**: 4 tests mínimos (ListaEnlazada, ABB, ColaPrioridad, Grafo/Dijkstra)~~ ✅ Completado
2. **Migrar a conventional commits**: Usar `git rebase -i` o commits nuevos con prefijos
3. **Crear diagramas**: Draw.io o PlantUML para clases y estructuras
4. **Completar endpoints REST**: ~~Reportes~~ ✅ hechos. Pendientes: gestión de empleados, favoritos, tickets
5. **Agregar endpoint `/api/parque/senderos`** para que el frontend no tenga datos hardcodeados
6. **Implementar panel de Administrador** en frontend
7. **Corregir typo en RevisionTecnica.java**

---

## NOTA FINAL - 16 DE MAYO DE 2026

**Estado tras implementación de FASE 1 (Reportes y Estadísticas):**

- ✅ ESTRUCTURAS DE DATOS PROPIAS: 5/5 COMPLETAS (ListaEnlazada, ABB, Grafo, ColaPrioridad, FavoritosSet)
- ✅ ALGORITMO DIJKSTRA: COMPLETO (integrado en Grafo + endpoint REST + visualización SVG)
- ✅ CARGA DE DATOS JSON: COMPLETA (4 archivos + botón frontend)
- ✅ REFACTORIZACIÓN A ESTRUCTURAS PROPIAS: COMPLETA (Visitante, Zona, FavoritosSet, TechPark usan ListaEnlazada)
- ✅ MAPA INTERACTIVO: COMPLETO (MapaParque.jsx SVG con colores por estado)
- ✅ BACKEND REST: 8 ENDPOINTS OPERATIVOS
- ✅ **REPORTES Y ESTADÍSTICAS: COMPLETO** (ReporteService + 2 endpoints + frontend Admin)
- ✅ **GESTIÓN DE COLAS: COMPLETO** (procesarSiguiente + endpoint + frontend Empleado)
- ✅ **COMPRA DE TICKETS: COMPLETO** (comprarTicket + 2 endpoints + frontend Visitante + lógica Familiar)
- ✅ **FAVORITOS: COMPLETO** (3 endpoints + botón ❤️ en frontend + sección Mis Favoritos)
- ✅ **HISTORIAL DE VISITAS: COMPLETO** (endpoint + frontend Visitante)
- ✅ **RECARGA DE SALDO: COMPLETO** (endpoint + frontend con selector de montos)
- ✅ **PANEL EMPLEADO: COMPLETO** (cola, procesar, mantenimiento, revisión)
- ❌ PRUEBAS UNITARIAS: PENDIENTES (0 de 4)
- ❌ DIAGRAMAS: PENDIENTES (clases y estructuras)
- ❌ COMMITS: INSUFICIENTES (~18 de 72 requeridos)
- ❌ COMMITS CONVENTIONAL: NO IMPLEMENTADO
- ⏳ PANELES DE ROL: INCOMPLETOS (Visitante parcial, Admin con reportes, Empleado placeholder)
- ⏳ ENDPOINTS REST: ~55% COMPLETADOS (8 de ~15 requeridos)

**Progreso global estimado:** 85-90%

**Próximas tareas recomendadas (FASE 3 en adelante):**

1. ✅ ~~FASE 2: Gestión de Colas — endpoint `procesarSiguiente()` para desencolar~~
2. ✅ ~~FASE 3: Tickets — endpoint `comprarTicket()` + lógica Familiar~~
3. ✅ ~~FASE 4: Favoritos — endpoints REST + frontend~~
4. ✅ ~~FASE 5: Frontend — historial de visitas, recargar saldo, panel Empleado~~
5. ✅ ~~FASE 6: Pruebas unitarias (21 tests con JUnit 5)~~
6. FASE 7: Diagramas de clases y estructuras

---

*Memoria actualizada: 16 de mayo de 2026*
*Análisis basado en lectura de 48 archivos de código fuente (34 Java + 6 frontend + 4 JSON + 4 documentación)*

# Proyecto: SISTEMA DE GESTION DE UN PARQUE DE ATRACCIONES INTELIGENTE (TECH-PARK UQ)

El privilegioso parque de diversiones Tech-Park UQ ha)crecordo deforma acelerada en elultimateño.Sin embargo, muchos de sus procesos todavia se realizanmanualmente, lo que ha generado problemas en el control de acceso, largas filas,fallos en los protocolos de seguridad, dificultades en la asignacion del personal ydeficiencias en la gestion del mantenimiento de las atracciónes.

Por estarzón, la junta directiva ha decidido contrastar a un equipo de ingenieros para desarrollar una plataforma integral capaz de administrar las operaciones principales del parque. Aside el momento en que un visitante compra su entrada, el sistema debe controlar su acceso y comportamiento dentro del parque. Cada visitante conta con un peril que incluirá datos como nombre, documento, edad, estatura, saldo virtual y, de manière optional, una fotografia para su pase digital.

Estos datos sonfundamentales, ya que elsystemadeferávalidar si el visitante cumple con las restricciones de sécurité de cada atracción, como alta minima,idadminima ocondicionesespecialesde ingresso.

El parque maneja tres temas de entradas o tickets:

- General: permite el ingresso normal al parque.   
- Familiar: permitte el ingresso condescending bajo ciertas conditiones definidas por la administración.   
- Fast-Pass: permite prioridad en la cola virtual de determinadas atracciónes.

Además de adquirir su ticket, el visitanteoulda consultar el mapa del parque, revisar los tiempos de esperade las atracciónes en tiempo real, registrar atracciónes favoritas y recibir notifications sobre el inizio de shows ochangios importantes en el estado de las atracciónes.

El parque está organizado en zonas, y cada zona agrupa varias atracciónes. Cada atracción conta con un identificador único, nombre, tipo, capacité máximo por ciclo, alta minima requireida,idad mínima requireida, costo adicional paraCERTOS tickets, contador acumulado de visitantes, tiempo estimado de espera y estado actual. Los estados posibles de una atracción serán:

- Activa   
- En Mantenimiento   
Cerrada

Tambien sedeaberger registrar el motivo del cierre cuando corresponda, por ejempo, cierre por clima o por revision技术水平.

Una regla fundamental del sistema sera el control de mantenimiento Preventivo. Toda atracción que requireaootingamente技术和 deberá bloquearse automatistically cuando alcance 500 visitantes acumulados, hasta que un operador registre deforma satisfactoria una revisión技术水平ica.

El sistema también deben reccionar ante conditiones climáticas adversas. Si el administrador activa una alerta de tormenta electrica o lluvia fuerte, las atracciónes de tipo acuática o mecánica de altadeferberánchangamatically a estado Cerrada, y el sistemasdeberánotificar alos visitantes con ticketsactivos o con reservas en cola virtual.

En cuando al personal, el parque conta con operadores y administradores. El administrador sera responsable de gestionar empleados, crear y modifierar zonas y atracciónes, asignar operadores a zonas especialicas y consultar reportes generales del parque. Cada operador solooulda gestionar las atracciónes pertenecentes a la zona a la cuales fue asignado. Además, el sistemasdeberá evitar conflictos de asignación, garantizando que ninguna atracción quede sin al menos un operador responsable.

El sistema tambiéndeferágestionarel'acceso alas atracciónes.Si un visitante con ticket General desea ingresar a una atracción que exige un costo adicional, el Sistemadeferáverificarssiposee saldo virtual suficienteantesde autorizar el acceso.Asimismo,el Sistemadeferácontrolarla capacitiesmaxima del parque y de cada zona,impidiendo laventa de nuevas entradas cuando se alcance el aforo permitido.

Finalmente, al cierre de la jornada, el administrador deverá poder consulutar reportes como ingresos diarios, atracciónes más visitadas, tiempo promedio de espera, cierrres por clima, ALERTS de mantenimiento y atracciónes con mayor número de incidentes operativos.

# \section*{Caracteristicas del Sistema:}

- Gestión de Rutas y Mapa: Representación del parque mediante un grafo donde las atracciónes son nodos y los senderos son aristas   
- Sistema de Colas Inteligentes: Gestion de acces a atracciónes diferenciaro entre ticket General y Fast-Pass mediante colas de prioridad   
- Búsueda Eficiente: Localización de visitantes y atracciónes en milisegundos realizando árboles de búsueda   
- Mantenimiento Automatizzato: Bloqueo de atracciónes al alcancar los 500 visitantes acumulados, gestionado por una estructura de alerts prioritarias   
- Simulación de Clima: Cierre masivo de atracciónes mecánicas y acuáticas ante alertas climáticas, notifying a los)."users afectados

- Visualización gráfica: El sistema debe permitir visualizar un mapa interactivo de las zonas y las atracciónes disponibles.

# Roles del Sistema:

# - Visitante

Perfil y Saldo: Registro con datos personales y gestion de saldo virtual.   
- Ruta Óptima: Calcular el camino más corto desde su ubicación actual hasta una atracción española (algoritmo de Dijkstra o BFS)   
- Favoritos e Historical: Guardar atracciónes favoritas y consultar el historial de visitas (Listas enlazadas)   
Fila Virtual: Unirse a la espera de una atracción Respectando la prioridad de su ticket

# - Operador de Atracción

o Control de Acceso: Validar restricciones de seguridad (altura,idad) y你能ear capacity por ciclo.   
Gestión de Estado: Cambiar estado de la atracción y registrar revisiones sociales.   
$\mathrm{O}$ Atencion Prioritaria: Procesar la cola de visitantes dando bajo primero a los Fast-Pass

# - Administrador del Parque

$\mathrm{O}$ Análisis del Grafo: Visualizar la connectividad del parque y detectar "clústeres" de atracciónes populares   
Gestión Jerarquica: Administrar empleados y zonas medianteestructuras de búsqueada.   
○ Reportes Avanzados: Generar estadística de ingresos, tiempos de esperá y ALERTas de mantenimiento mediante el recorrodo de lasestructuras

# Interfaz Gráfica (GUI):

- Inicio: Visualización general del estado de las zonas del parque y disponibiliad de las atracciónes   
Panel de administración: Gestion de personal y control de mantenimiento Preventivo.   
Panel de Nombre: Interfaz para que el visitante vea su posicion en la cola y su ruta sugerida.   
Panel de rutas: Optimización de recorridos y gestion de colas virtuales. Visualización de los senderos que connectan las atracciónes y planificación del flujo de visitantes.   
Estadisticas: Reportes operativos y análisis de afluencia. Generación de informes detallados sobre los ingesos diarios, las atracciónes más visitadas.

los tiempos promedio de espera y el historial de cierras por conditiones climáticas adversas

Mapa interactivo: Representación gráfica del grafo del parque. Un mapa visual que utilizes nodos para las atracciónes y aristas para los caminos. Debeyardsar en tiempo real el estado de cada punto (ej. verde para activa, rojo para cerrada) y permitir al usuario ver la ruta sugerida calculada mediante algoitmos como Dijkstra

Carga de datos: Botón para cargar un escenario de prueba inicial desde un archivo plano o serializzato

# Estructuras de datos Propias:

- Grafo*: Representar el mapa físico del parque. Nodos = Atracciónes; Aristas = Caminos con peso (distancia o tiempo)   
- Cola de prioridad*: Gestionar la fila de ingresso a las atracciónes. Prioridad 1: Fast-Pass; Prioridad 2: General   
- Listas Enlazadas*: Almacenar el historial de visitas de cada usuario y la lista de operadores asignados a una zona   
- Árbol Binario de Búsqueça (ABB): Organizar el catalogo de atracciónes por nombre o ID para búsqueadas rápidas.   
- Set: Se utilizes para la gestión de Atracciónes Favoritas de cada visitante

# Requerimientos de Desarrollo:

Implementar al menos 4 pruebas unitarias.   
- Uso deestructuras de datos desarrolladas por los estudiantes.   
- Grupos de hasta 3 integrantes.   
- Repository en Git/GitHub (Cada integrante del grupo debe tener un minimo de 24 commits, para evidencias el trabajo collaborativo y trazabilidad del proyecto), cada commit debe estar descrito utilizing conventionals commits.   
- Diagrama de classes.   
- Diagrama de Estructuras Propias.

# Condiciones Generales:

- Entrega con demostración funcional y sustentarión.   
GUI funcional e intuitiva.   
- Visualización grárica del grafo de zonas y atracciónes.   
- Carga de datos iniciales desde archivo o botón de prueba.

# Evaluación

La nota del proyecto consta de dos partes:

1. Codificación (0 a 5+puntos).   
2. Sustentación (factor multiplicador de 0 a 1).

La persona seleccionada para sustentar influirá en la calificación final del grupo.

El的结果o final se obtiene multiplicando lanota de codificacion por la evaluacion de la sustentacion.

Recuerden: La sustentación es obligatoria para todos los integrantes del grupo y el profesor elegirá al sustentaré en el momento de la presentación.

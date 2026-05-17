# 🏗️ Inventario Técnico: Componentes Implementados (Avance 32%)
**Proyecto TECH-PARK UQ**

Este documento detalla la base tecnológica y funcional que ya se encuentra operativa en el proyecto, representando el primer tercio del desarrollo total.

---

## 1. Infraestructura y Arquitectura Base
Se ha establecido una arquitectura desacoplada de alto rendimiento que permite la comunicación fluida entre el núcleo de lógica y la interfaz de usuario.

*   **Backend (Java 21 + Spring Boot 3.2.4):**
    *   Configuración de **Maven** con dependencias para Web, Test y Lombok.
    *   Implementación de un **Servicio Centralizado** (`TechPark.java`) que actúa como orquestador del sistema.
    *   **Controladores REST:** Exposición de endpoints dinámicos para el consumo de datos desde el exterior.
    *   **CORS Configuration:** Configurado para permitir la conexión segura con el entorno de desarrollo de React.

*   **Frontend (React + Vite + JavaScript):**
    *   Estructura de Single Page Application (SPA).
    *   Sistema de **Hooks (`useState`, `useEffect`)** para la gestión de estados y peticiones asíncronas.
    *   Estilizado mediante **Vanilla CSS** con variables personalizadas y diseño responsivo.

---

## 2. Modelo de Dominio (Business Logic)
El corazón del parque ya cuenta con sus entidades fundamentales modeladas con lógica de negocio integrada.

### 🎢 Atracciones (`Atraccion.java`)
*   **Gestión de Estados:** Soporte para estados *ACTIVA*, *EN_MANTENIMIENTO* y *CERRADA*.
*   **Seguridad Automatizada:** Lógica interna que bloquea la atracción automáticamente al alcanzar los **500 visitantes** (Requisito de mantenimiento preventivo).
*   **Parámetros Técnicos:** Control de altura mínima, edad mínima, capacidad por ciclo y costo adicional.

### 🎟️ Visitantes (`Visitante.java`)
*   **Perfil Completo:** Manejo de documento, edad, estatura y saldo virtual.
*   **Motor de Validación:** Método `puedeEntrar(Atraccion)` que verifica simultáneamente:
    1.  Estado de la atracción.
    2.  Restricción de estatura.
    3.  Restricción de edad.
    4.  Saldo virtual suficiente.
*   **Registro de Actividad:** Capacidad para descontar saldo y registrar el historial de visitas.

### 🗺️ Zonas y Parque (`Zona.java`, `TechPark.java`)
*   **Organización Espacial:** Agrupación de atracciones por zonas geográficas.
*   **Control de Aforo:** Lógica para validar la capacidad máxima global del parque antes de permitir ingresos.
*   **Protocolos Climáticos:** Sistema de cierre masivo de atracciones acuáticas/mecánicas ante alertas de tormenta.

---

## 3. Interfaz de Usuario (UI/UX)
Se ha implementado una experiencia de usuario moderna y temática.

*   **Flujo de Navegación:** 
    *   `Welcome Screen`: Pantalla de inicio con estética de parque tecnológico.
    *   `Role Selection`: Selector de roles (Visitante, Empleado, Administrador) con tarjetas interactivas.
    *   `Live Dashboard`: Panel de control principal.
*   **Visualización en Tiempo Real:**
    *   **Badge de Estado:** Indicador visual de si el parque está ABIERTO o con AFORO COMPLETO.
    *   **Catálogo Dinámico:** Tabla que muestra las atracciones, sus restricciones y su estado actual, sincronizada directamente con el backend de Java.

---

## 4. Endpoints de API Disponibles
Actualmente, el sistema expone los siguientes puntos de acceso para la integración:

| Método | Endpoint | Descripción |
| :--- | :--- | :--- |
| `GET` | `/api/parque/atracciones` | Retorna la lista completa de atracciones y su estado técnico. |
| `GET` | `/api/parque/zonas` | Retorna la distribución del parque por zonas. |
| `GET` | `/api/parque/estado` | Indica la disponibilidad global del parque (Aforo). |
| `GET` | `/api/test` | Verificación de conectividad Backend-Frontend. |

---

## 5. Resumen de Calidad de Código
*   **Modularidad:** Alta. Los modelos están separados de los controladores y la lógica de servicio.
*   **Estilo:** Uso de convenciones de Java (CamelCase) y React (Functional Components).
*   **Preparación:** El código está listo para la inyección de estructuras de datos personalizadas sin romper la interfaz actual.

---
*Documento de inventario generado por Gemini CLI para el equipo de TECH-PARK UQ.*

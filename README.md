# Aplicación Móvil CRUD - Arquitectura y Flujo de Datos

Este proyecto es una aplicación Android desarrollada con **Kotlin** y **Jetpack Compose**, estructurada bajo los principios de **Clean Architecture** y el patrón de diseño **MVVM** (Model-View-ViewModel). La aplicación integra el consumo de servicios REST externos y la persistencia de datos en la nube mediante Firebase.

## 1. Estructura del Proyecto

El código está organizado en capas para garantizar la escalabilidad y facilitar el mantenimiento:

### Capa de Dominio (`domain/`)
Contiene la lógica de negocio pura e independiente de cualquier infraestructura.
- **Modelos:** Definición de los objetos de negocio (ej. `ProductModel`, `TaskModel`).
- **Casos de Uso:** Acciones específicas que el usuario realiza en la aplicación (ej. `GetProductUseCase`).
- **Interfaces de Repositorio:** Definen los contratos de los métodos de acceso a datos.

### Capa de Datos (`data/`)
Implementa el acceso a las fuentes de datos externas e internas.
- **Remoto:** Definición de servicios API con **Retrofit** y objetos de transferencia de datos (DTO).
- **Repositorios:** Implementaciones de las interfaces de dominio que gestionan la lógica de red y base de datos (Firebase Firestore).
- **Mappers:** Transforman los DTOs (datos crudos del servidor) a Modelos de Dominio.

### Capa de UI (`ui/`)
Gestiona la presentación y la interacción con el usuario.
- **Pantallas (Screens):** Composables que definen la interfaz de usuario.
- **ViewModels:** Gestionan el estado de la pantalla y se comunican con los casos de uso.
- **Estado (State):** Estructuras que representan el estado actual de la vista (cargando, éxito, error).

---

## 2. Flujo de Comunicación y Actualización (Update)

A continuación se describe el proceso completo de actualización de un dato (por ejemplo, el precio de un producto) desde la interfaz hasta el servidor:

1.  **Captura en UI:** El usuario introduce el nuevo valor en el formulario de edición (`ProductDetails.kt`). Jetpack Compose gestiona este cambio mediante un estado reactivo (`mutableStateOf`).
2.  **Acción del Usuario:** Al presionar "Guardar", la UI invoca una función en el `ProductViewModel`.
3.  **Procesamiento en ViewModel:** El ViewModel actualiza el estado visual (ej. muestra un indicador de carga) y llama al caso de uso correspondiente (`UpdateProductUseCase`).
4.  **Mediación del Dominio:** El Caso de Uso solicita al repositorio la actualización de la información.
5.  **Transformación en Datos:** El repositorio recibe el objeto de negocio, lo transforma en un DTO mediante el Mapper y utiliza **Retrofit** para enviar una petición HTTP `PUT` al backend.
6.  **Respuesta del Backend:** Una vez que el servidor responde, el repositorio mapea el resultado de vuelta al modelo de dominio.
7.  **Actualización Final:** El ViewModel recibe la confirmación y actualiza el `uiState`, lo que provoca que la UI refleje el éxito de la operación automáticamente.

---

## 3. Tecnologías Utilizadas

- **Jetpack Compose:** Para la construcción de la interfaz de usuario declarativa.
- **Retrofit & Moshi:** Para el consumo de APIs REST y el procesamiento de JSON.
- **Firebase Firestore:** Para la persistencia y sincronización de datos en tiempo real.
- **Dagger Hilt:** Para la inyección de dependencias.
- **Corrutinas y Flow:** Para la gestión de operaciones asíncronas y flujo de datos reactivo.

## 4. Requisitos de Configuración

Para la correcta conexión con los servicios de Google, es necesario incluir el archivo `google-services.json` en el directorio `app/` del proyecto.

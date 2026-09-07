# Plan de Refactorización y Limpieza del Taller (CRUD)

Este plan aborda las inconsistencias de nombres, errores de convenciones y optimizaciones identificadas en la revisión inicial.

## Cambios Propuestos

### 1. Dominio y Repositorio (Convenciones y Naming)
Renombrar métodos para usar `camelCase` y corregir referencias a "Character".

#### [MODIFY] [ProductRepository.kt](file:///C:/Workspace_Dev/1_Proyectos/CRUD_ANDROID/app/src/main/java/com/sena/crud/domain/repository/ProductRepository.kt)
- Renombrar `GetProductById` a `getProductById`.

#### [MODIFY] [GetProductUseCase.kt](file:///C:/Workspace_Dev/1_Proyectos/CRUD_ANDROID/app/src/main/java/com/sena/crud/domain/useCase/GetProductUseCase.kt)
- Actualizar llamada al repositorio.

#### [MODIFY] [ProductRepositoryImpl.kt](file:///C:/Workspace_Dev/1_Proyectos/CRUD_ANDROID/app/src/main/java/com/sena/crud/data/repository/ProductRepositoryImpl.kt)
- Implementar los nuevos nombres de métodos y actualizar llamadas a la API.

#### [MODIFY] [ProductApiService.kt](file:///C:/Workspace_Dev/1_Proyectos/CRUD_ANDROID/app/src/main/java/com/sena/crud/data/remote/api/ProductApiService.kt)
- Renombrar `GetProductByid` a `getProductById`.

---

### 2. Inyección de Dependencias (Limpieza)
Eliminar restos de proyectos anteriores y estandarizar anotaciones.

#### [MODIFY] [NetworkModule.kt](file:///C:/Workspace_Dev/1_Proyectos/CRUD_ANDROID/app/src/main/java/com/sena/crud/di/NetworkModule.kt)
- Renombrar `provideCharacterApiService` a `provideProductApiService`.

#### [MODIFY] [RepositoryModule.kt](file:///C:/Workspace_Dev/1_Proyectos/CRUD_ANDROID/app/src/main/java/com/sena/crud/di/RepositoryModule.kt)
- Renombrar `bindCharacterRepository` a `bindProductRepository`.
- Cambiar `jakarta.inject.Singleton` por `javax.inject.Singleton`.

---

### 3. Datos y UI (Optimización)

#### [MODIFY] [ProductMapper.kt](file:///C:/Workspace_Dev/1_Proyectos/CRUD_ANDROID/app/src/main/java/com/sena/crud/data/mapper/ProductMapper.kt)
- Simplificar `toData()` aprovechando los valores por defecto del DTO `Product`.

#### [MODIFY] [ProductDetails.kt](file:///C:/Workspace_Dev/1_Proyectos/CRUD_ANDROID/app/src/main/java/com/sena/crud/ui/section/ProductDetails.kt)
- Centrar el `CircularProgressIndicator` en el estado de carga.

---

## Plan de Verificación

### Pruebas Automatizadas
- Ejecutar `./gradlew app:assembleDebug` para asegurar que todo compila correctamente después del refactor.

### Verificación Manual
- Revisar que la navegación y carga de productos en el `MainActivity` siga funcionando tras los cambios de nombres.

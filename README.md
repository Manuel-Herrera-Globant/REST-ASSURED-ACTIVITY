
# TECHNICAL TEST REST ASSURED - Swagger Petstore 

Manuel Herrera 

## Requisitos del taller y cómo se cumplieron

### 1) Automatizar funcionalidades con Rest Assured
Se implementó un test por cada funcionalidad solicitada, consumiendo los endpoints correspondientes con Rest Assured.

### 2) Cada invocación debe tener al menos un test
Cada endpoint requerido tiene al menos un test dedicado.

### 3) Todos los tests deben ser distintos por cada invocación
Cada clase valida un escenario diferente y realiza aserciones acordes al endpoint que prueba.

### 4) Cada test debe ser independiente
- Los tests que requieren usuario (crear/login/logout) generan datos únicos en cada ejecución (por ejemplo, `username` con UUID) para evitar colisiones.
- Los tests que requieren una mascota obtienen una mascota válida consultando primero `status=available` dentro del mismo test.
- No hay dependencia entre clases ni necesidad de orden de ejecución.

### 5) Usar Maven o Gradle
El proyecto está construido con **Maven**.

### 6) TestNG como runner
Los tests se ejecutan con **TestNG** a través de `maven-surefire-plugin`.

### 7) Documentación
Se agregaron descripciones breves (JavaDoc) en la base y en los tests para explicar el objetivo de cada flujo.

---

## Tecnologías usadas
- Java 8+
- Maven
- Rest Assured
- TestNG
- Jackson Databind (serialización JSON para bodies)

---

## Estructura principal

- `src/test/java/com/perfdog/automation/base/BaseApiTest.java`
  - Configuración común (baseURI) para Rest Assured.

- `src/test/java/com/perfdog/automation/tests/`
  - `CreateUserTest.java`
    - Crea un usuario con `POST /user`.
  - `LoginUserTest.java`
    - Crea un usuario y hace login con `GET /user/login`.
  - `ListAvailablePetsTest.java`
    - Lista mascotas disponibles con `GET /pet/findByStatus?status=available`.
  - `GetSinglePetTest.java`
    - Obtiene una mascota disponible y consulta su detalle con `GET /pet/{petId}`.
  - `CreateOrderTest.java`
    - Obtiene una mascota disponible y crea una orden con `POST /store/order`.
  - `LogOutUserTest.java`
    - Crea usuario, hace login y ejecuta logout con `GET /user/logout`.

---

## Cómo ejecutar

`cd PetStoreRestAssured`

`mvn test`


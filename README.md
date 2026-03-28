# kinal-app

Es una aplicación desarrollada con Spring Boot que permite la gestión de información comercial mediante un sistema estructurado en múltiples entidades, el proyecto está diseñado bajo principios de arquitectura REST, facilitando operaciones CRUD para el manejo eficiente de datos.
## Tecnologías utilizadas
* **Java 21**
* **SpringBoot 4.0.2**
* **Maven** (Gestor de dependencias)
* **MySQL Workbench** (Sistema de base de datos)

## Requisitos Previos
Antes del proyecto, es importante tener:
* JDK 17 o superior instalado
* Maven Instalado
* Una instancia activa de MySQL

## Cómo se instala y se ejecuta
### 1. Clonar el repositorio
git clone https://github.com/yperez-2025291/kinal-app.git
### 2. Entrar en la carpeta
cd kinal-app
### 3. Configura el archivo application.properties
spring.application.name=KinalRestApplication
```bash
#Conexion MySQL
spring.datasource.url = jdbc:mysql://localhost:3306/dbClientes_in5av?createDatabaseIfNotExist=true
spring.datasource.username = tu_usuario
spring.datasource.password = tu_contraseña
server.port=8001

#JPA / hibernate
spring.jpa.hibernate.ddl-auto = update
spring.jpa.show-sql = true
```
### 4. Ejecuta el proyecto

## Funcionalidades del proyecto
* CRUD completo para todas las entidades
* Manejo de relaciones entre entidades
* Validaciones básicas
* Persistencia con JPA/Hibernate
* API REST estructurada
## Estructura del proyecto
```bash
src/
└── main/
├── java/com/kinalapp/
│    ├── controller/
│    │    ├── ClienteController.java
│    │    ├── ProductoController.java
│    │    ├── UsuarioController.java
│    │    ├── DetalleVentaController.java
│    │    └── VentaController.java
│    │
│    ├── entity/
│    │    ├── Cliente.java
│    │    ├── Producto.java
│    │    ├── Usuario.java
│    │    ├── Venta.java
│    │    └── DetalleVenta.java
│    │
│    ├── repository/
│    │    ├── ClienteRepository.java
│    │    ├── ProductoRepository.java
│    │    ├── UsuarioRepository.java
│    │    ├── VentaRepository.java
│    │    └── DetalleVentaRepository.java
│    │
│    ├── service/
│    │    ├── IClienteService.java
│    │    ├── IProductoService.java
│    │    ├── IUsuarioService.java
│    │    ├── IVentaService.java
│    │    ├── IDetalleVentaService.java
│    │    ├── ClienteService.java
│    │    ├── ProductoService.java
│    │    ├── UsuarioService.java
│    │    ├── VentaService.java
│    │    └── DetalleVentaService.java
│    │
│    └── KinalRestApplication.java
│
└── resources/
├── static/
├── templates/
└── application.properties
```
# Kinal App - Sistema de Gestión

Aplicación empresarial desarrollada con **Spring Boot** que permite la gestión integral de ventas, clientes, productos, usuarios y detalles de venta. El sistema combina una **API REST** para operaciones backend con una **interfaz web** construida con Thymeleaf, ofreciendo una experiencia completa para administración de datos comerciales.

---

## Tecnologías utilizadas en el proyecto

| Tecnología | Versión | Propósito |
|------------|---------|------------|
| **Java** | 21 | Lenguaje base |
| **Spring Boot** | 4.0.2 | Framework principal |
| **Spring MVC** | - | Controladores web y API REST |
| **Spring Data JPA** | - | Persistencia y ORM |
| **Thymeleaf** | 3.1.3 | Motor de plantillas HTML |
| **Maven** | - | Gestor de dependencias |
| **MySQL** | 8.0 | Base de datos relacional |
| **HTML5 / CSS3** | - | Interfaz de usuario |

---

## Requisitos previos

Antes de ejecutar el proyecto, asegúrate de tener instalado:

- JDK 21 o superior
- Maven 3.8+
- MySQL Server 8.0 (activo)
- Git (opcional, para clonar)

---

## Instalación y ejecución

### 1. Clonar el repositorio

```bash
git clone https://github.com/yperez-2025291/kinal-app.git
cd kinal-app
```
## Configura application.properties
```bash
spring.application.name=KinalRestApplication

#Conexion MySQL
spring.datasource.url = jdbc:mysql://localhost:3306/dbClientes_in5av?createDatabaseIfNotExist=true
spring.datasource.username = tu_user
spring.datasource.password = tu_password
server.port= 8041

#JPA / hibernate
spring.jpa.hibernate.ddl-auto = update
spring.jpa.show-sql = true
```

## Estructura del proyecto
```bash
kinal-app/
├── src/
│   └── main/
│       ├── java/com/yubiniperez/kinalapp/
│       │   ├── controller/
│       │   │   ├── api/
│       │   │   │   ├── ClienteController.java
│       │   │   │   ├── ProductoController.java
│       │   │   │   ├── UsuarioController.java
│       │   │   │   ├── VentaController.java
│       │   │   │   └── DetalleVentaController.java
│       │   │   │
│       │   │   └── controller/
│       │   │       ├── HomeViewController.java
│       │   │       ├── ClienteViewController.java
│       │   │       ├── ProductoViewController.java
│       │   │       ├── UsuarioViewController.java
│       │   │       ├── VentaViewController.java
│       │   │       └── DetalleVentaViewController.java
│       │   │
│       │   ├── entity/
│       │   │   ├── Cliente.java
│       │   │   ├── Producto.java
│       │   │   ├── Usuario.java
│       │   │   ├── Venta.java
│       │   │   └── DetalleVenta.java
│       │   │
│       │   ├── repository/
│       │   │   ├── ClienteRepository.java
│       │   │   ├── ProductoRepository.java
│       │   │   ├── UsuarioRepository.java
│       │   │   ├── VentaRepository.java
│       │   │   └── DetalleVentaRepository.java
│       │   │
│       │   ├── service/
│       │   │   ├── interfaces/
│       │   │   │   ├── IClienteService.java
│       │   │   │   ├── IProductoService.java
│       │   │   │   ├── IUsuarioService.java
│       │   │   │   ├── IVentaService.java
│       │   │   │   └── IDetalleVentaService.java
│       │   │   │
│       │   │   └── impl/
│       │   │       ├── ClienteService.java
│       │   │       ├── ProductoService.java
│       │   │       ├── UsuarioService.java
│       │   │       ├── VentaService.java
│       │   │       └── DetalleVentaService.java
│       │   │
│       │   └── KinalRestApplication.java
│       │
│       └── resources/
│           ├── static/
│           │   └── css/
│           │       ├── colors.css
│           │       ├── global.css
│           │       ├── home.css
│           │       ├── usuarios.css
│           │       ├── clientes.css
│           │       ├── productos.css
│           │       ├── ventas.css
│           │       ├── detalles.css
│           │       └── form/
│           │           └── form-fk.css
│           │
│           └── templates/
│               ├── index.html
│               ├── pages/
│               │   ├── usuarios.html
│               │   ├── clientes.html
│               │   ├── productos.html
│               │   ├── ventas.html
│               │   ├── detalles.html
│               │   └── form/
│               │       ├── usuario-form.html
│               │       ├── cliente-form.html
│               │       ├── producto-form.html
│               │       ├── venta-form.html
│               │       └── detalle-form.html
│               │
│               └── application.properties
```
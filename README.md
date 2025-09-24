# El Buen Sabor - Backend

Este es el backend del proyecto **El Buen Sabor**, diseñado para administrar y gestionar las operaciones de un sistema
de pedidos de rotisería, basado en tecnologías modernas como **Spring Framework** y **Jakarta EE**. Este repositorio contiene la
lógica de negocio, exposiciones de servicios mediante APIs RESTful y manejo de base de datos.

---

## 🚀 Tecnologías Utilizadas

- **Java 17**: Lenguaje principal utilizado para el desarrollo de la lógica del backend.
- **Spring Framework**:
  - **Spring MVC**: Para la gestión de las rutas y controladores REST.
  - **Spring Data JPA**: Para la interacción y persistencia con la base de datos utilizando JPA.
- **Jakarta EE**: Orientado al soporte de estándares empresariales en Java.
- **Lombok**: Reducción de boilerplate (código repetitivo) usando anotaciones.
- **Maven**: Herramienta de gestión de dependencias y construcción del proyecto.

---

## 📂 Estructura del Proyecto

La siguiente es la estructura base del proyecto:

```
el-buen-sabor-backend
├── .idea/               # Configuración del proyecto para IntelliJ IDEA
├── .mvn/                # Archivos de configuración de Maven
├── src/                 # Código fuente principal
├── uploads/             # Carpeta destinada a la subida de archivos
├── .gitignore           # Archivos y configuraciones ignoradas por Git
├── mvnw / mvnw.cmd      # Wrapper de Maven para construir el proyecto sin Maven instalado
├── pom.xml              # Archivo principal para la gestión de dependencias Maven
```

- **`src`**: Contiene el código fuente del backend. En esta carpeta se encuentran los paquetes principales, controladores, servicios, repositorios y demás elementos.
- **`resources`: Contiene datos para inicializar la base de datos y archivos necesarios para el funcionamiento del sistema.
- **`uploads`**: Es utilizada para manejar la subida y almacenamiento de archivos relevantes al sistema.

---

## 🛠️ Configuración del Entorno

### Prerrequisitos

- **Java 17 o superior** debe estar instalado.
- **Maven** (opcional). El proyecto incluye el Wrapper de Maven (`mvnw`), por lo que no es estrictamente necesario instalarlo globalmente si deseas usar el wrapper.
- **Node.js v22 LTS o superior.**

### Pasos para correr el proyecto localmente:

1. Clonar el repositorio:
   ```bash
   git clone https://github.com/ilbardin/el-buen-sabor-backend.git
   cd el-buen-sabor-backend
   ```

2. Configurar las propiedades del proyecto.
    - Agregar un archivo `.env` en la raíz del proyecto con lo siguiente:
      - `PROD_ACCESS_TOKEN=<clave de acceso para API de Mercado Pago>`
      - `LT_PATH=<ubicación del archivo lt.cmd del paquete de Node.js localtunnel>`
      - `LT_SUBDOMAIN=<dominio personalizado para URL de localtunnel>` (opcional)

3. Construir y ejecutar:
   - Usando Maven Wrapper:
     ```bash
     ./mvnw spring-boot:run
     ```
   - Con Maven instalado globalmente:
     ```bash
     mvn spring-boot:run
     ```

4. Abrir en el navegador:
   - Una vez levantado, el backend estará disponible en `http://localhost:8080`.

---

## 🌐 Endpoints principales

Los endpoints están expuestos usando **Spring MVC** y generalmente siguen mejores prácticas de REST.  
La mayoría de endpoints están protegidos a través de autenticación por token JWT, a excepción de algunos endpoints públicos,
tales como aquellos relacionados al registro de usuarios.

Para mayor información, referirse a la clase `SecurityConstants`, ubicada en el paquete `config`.

---

## 🗂️ Gestión de Dependencias

El proyecto utiliza **Maven** para la gestión de dependencias. Puedes ver y administrar todas ellas en el archivo `pom.xml`.

Para instalar las dependencias:

```bash
./mvnw install
```

---

## 📸 Subida de Archivos

La carpeta `uploads` está configurada para manejar subidas de imágenes, tanto para artículos insumo como para artículos manufacturados.

Es importante verificar que tengas permisos de escritura en la carpeta `uploads` para poder subir archivos correctamente.

---

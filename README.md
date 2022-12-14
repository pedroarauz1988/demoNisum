# demoNisum

- Clonar proyecto desde el repositorio.

- Descargar las dependencias del build.gradle.

- La base de datos cuenta con liquibase para que se cree automáticamente, en el archivo db.changelog estan las sentencias para crear las tablas.

- El db.changelog tiene la carga inicial de los datos de las expresiones regulares.

- El proyecto se despliega en el puerto 8081 como esta en el application.yml, tiene un context-path: /api.

- Tomar en cuenta que el servicio valida que para poder ingresar usuarios deben existir las configuraciones de las expresiones regulares en la base de datos.

- Las rutas de los end point son las siguientes:
  
   Para crear o actualizar las expresiones regulares del correo y clave:

        Petición POST:
        http://localhost:8081/api/saveUpdatePattern   

        JSON de entrada para RequestBody de la expresión regular del correo:
        
        {
          "name": "DEFAULT_EMAIL_REGULAR_EXPRESSION",
          "pattern": "^[^@]+@[^@]+\\.[a-zA-Z]{2,}$"
        }
  
        JSON de entrada para RequestBody de la expresión regular de la clave:
  
        {
          "name": "DEFAULT_PASSWORD_REGULAR_EXPRESSION",
          "pattern": "^(?=\\w*\\d)(?=\\w*[A-Z])(?=\\w*[a-z])\\S{8,16}$"
        }

   Para crear o actualizar los usuarios:

        Petición POST:        
        http://localhost:8081/api/saveUpdateUser
  
        JSON de entrada para RequestBody:
  
        {
          "name": "Pedro Arauz",
          "email": "pedro.arauz.88@gmail.com",
          "password": "Passw0rd",
          "phones": [
                   {
                     "number": "123456",
                     "cityCode": "1",
                     "countryCode": "593"
                   }
                    ]
        }
  
- La url de ingreso a la base de datos es la siguiente:
        http://localhost:8081/api/h2-console
        Datos para configurar conexión a la base de datos:
        Driver Class: org.h2.Driver
        JDBC URL: jdbc:h2:mem:nisum
        USER Name: sa

- La url de ingreso a la documentación swagger es la siguiente:
        http://localhost:8081/api/swagger-ui/index.html

- El diagrama de la solución se encuentra en la ruta:
        src\main\resources\diagrama\diagrama-solucion.png

Hecho por Pedro Arauz
Correo: pedro.arauz.88@gmail.com
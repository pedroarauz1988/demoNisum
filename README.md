# demoNisum

- Clonar proyecto desde el repositorio.

- Descargar las dependencias del build.gradle.

- La base de datos cuenta con liquibase para que se cree automáticamente, en el archivo db.changelog estan las sentencias para crear las tablas.

- El proyecto se despliega en el puerto 8081 como esta en el application.yml, tiene un context-path: /api.

- Tomar en cuenta que el servicio valida que para poder ingresar usuarios deben existir las configuraciones de las expresiones regulares en la base de datos.

- Las rutas de los end point son las siguientes:
  
   Para crear o actualizar las expresiones regulares del correo y clave:

        http://localhost:8081/api/saveUpdatePattern

        JSON de entrada para RequestBody:
        correo: 
        
        {
          "name": "DEFAULT_EMAIL_REGULAR_EXPRESSION",
          "pattern": "^[^@]+@[^@]+\\.[a-zA-Z]{2,}$"
        }
  
        clave:
  
        {
          "name": "DEFAULT_PASSWORD_REGULAR_EXPRESSION",
          "pattern": "^(?=\\w*\\d)(?=\\w*[A-Z])(?=\\w*[a-z])\\S{8,16}$"
        }

   Para crear o actualizar los usuarios:
  
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

- La url de ingreso a la documentación swagger es la siguiente:
        http://localhost:8081/api/swagger-ui/index.html

- El diagrama de la solución se encuentra en la ruta:
        src\main\resources\diagrama\diagrama-solucion.png

Hecho por Pedro Arauz
Correo: pedro.arauz.88@gmail.com
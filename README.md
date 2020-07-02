# digital-harbor-test
Entregable
---------------------------

Se llego a completar lo siguiente

Formulario				Insertar	Eliminar	Actualizar 	Buscar

Hospital				Si			Si			Si			Si
Paciente				Si			Si			Si			Si
Doctor					Si			X			X			Si
Especialidad			Si			Si			Si			*
Nota					X			X			X			X


Se realizo con las herramientas establecidas SprintBoot, Angular y NgBootstrap

-- bases de datos

La base de datos es MySql. Se incluye un archivo de Script que se utilizo donde se poblo la informacion
La basese de datos Mysql Corre en el puerto 3306

    url: "jdbc:mysql://localhost:3306/digitalharbor_hospital?serverTimezone=UTC"
    username: "digitalharbor"
    password: "digitalharbor"


Se dividio en 2 el proyecto 

- hospital-dh-backend
- hospital-dh-frontend


hospital-dh-backend
-------------------

El proyecto corre se realizo con maven. Se puede cargar el proyecto con un editor y lanzarlo
si lanza desde consola ejecutar el projecto como 

$ java -jar hospital-dh-backend-0.0.1-SNAPSHOT.jar


La url del backend es : 

http://localhost:8081/hospital-dh-backend/

Se lograron implementar los metodos :

EndPoint:

/login					POST
/hospital   			POST   	para crear
/hospital/update   		POST   	para actualizar
/hospital/delete   		POST   	para eliminar
/hospital/search   		POST   	para buscar
/hospital/{id}   		GET   	para buscar un elemento
/hospital/   			GET   	devuelve todos los elementos


/paciente   			POST   	para crear
/paciente/update   		POST   	para actualizar
/paciente/delete   		POST   	para eliminar
/paciente/search   		POST   	para buscar
/paciente/hospital/{id} GET   	para buscar pacientes por hospital
/paciente/{id}   		GET   	para buscar un elemento
/paciente/   			GET   	devuelve todos los elementos

/doctor   				POST   	para crear
/doctor/update   		POST   	para actualizar
/doctor/delete   		POST   	para eliminar
/doctor/search   		POST   	para buscar
/doctor/hospital/{id} 	GET   	para buscar doctores por hospital
/doctor/{id}   			GET   	para buscar un elemento
/doctor/   				GET   	devuelve todos los elementos

/especialidad   			POST   	para crear
/especialidad/update   		POST   	para actualizar
/especialidad/delete   		POST   	para eliminar
/especialidad/search   		POST   	para buscar
/especialidad/hospital/{id} GET   	para buscar especialidades por hospital
/especialidad/{id}   		GET   	para buscar un elemento
/especialidad/   			GET   	devuelve todos los elementos

/nota   			POST   	para crear
/nota/update   		POST   	para actualizar
/nota/delete   		POST   	para eliminar
/nota/{id}   		GET   	para buscar un elemento
/nota/   			GET   	devuelve todos los elementos

Cada endpoint tiene una estructura en la package :

	com.digitalharbor.eval.rest.ui.model.request

Si se va consumir desde Postman, cada Dto en el package describe los campos necesarior para colocar en el body
En caso de faltar un elemento, el backed se encargara de avisarle el error

Se sigue el modelo MVC de Spring para el desarrollo del applicativo.
Tambien se usa JPA
Se aplicaron Patrones de Disenho de Software para los Servicios y los Dto

hospital-dh-frontend
-------------------
Se utilizo Angular+7 el npm 6.13

Angular CLI: 9.1.9
Node: 12.16.1
OS: win32 x64

Angular: 9.1.11
... animations, common, compiler, compiler-cli, core, forms
... localize, platform-browser, platform-browser-dynamic, router
Ivy Workspace: Yes

Package                           Version
-----------------------------------------------------------
@angular-devkit/architect         0.901.9
@angular-devkit/build-angular     0.901.9
@angular-devkit/build-optimizer   0.901.9
@angular-devkit/build-webpack     0.901.9
@angular-devkit/core              9.1.9
@angular-devkit/schematics        9.1.9
@angular/cli                      9.1.9
@angular/http                     7.2.16
@ngtools/webpack                  9.1.9
@schematics/angular               9.1.9
@schematics/update                0.901.9
rxjs                              6.5.5
typescript                        3.8.3
webpack                           4.42.0


Se utilizo Ng-Bootstrap como framework de estilos.

La aplicacion corre en el puerto 4200

http:/localhost:4200/


La pantalla principal esta como login utilizar el usuario por defecto que viene con la Bases de datos
usuario : josemiguel
password: 12345



Adicional
----------------

Se realizo un Inicio de Sesion con Autentificacion en el lado de SpringBoot, se aplicaron filters 
el cual retorna un jsonwebtoken. Se planeaba realizar un abm para los usuario, pero no dio mucho el tiempo.

Cualquier informacion adicional escribir a cheyo.lanza@gmail.com

Saludos y Espero verlos pronto!

Jose Miguel

# Back End Challenge

## Tecnologias

1. Java 8 y SpringBoot 
2. Base de datos documental MongoDB
3. Eureka para registrar los servicios y realizar el balanceo.
4. Docker
5. Gestor de configuración gradle. 
6. Despliegue en instancias AWS.


### Perfiles de configuración

Se realiza un perfil de configuración en formato JSON en repositorio https://github.com/fjgmateu/configuration.git.

El servicio obtendrá la configuración en función del perfil asociado para cada uno de los proyectos. Esto permite una gestión de configuración en función del entorno dónse se ejecutara

Para arrancar los servicios a través de gradle se ejecutará el siguiente comando:gradle bootRun -Pprofile=test

### Almacenamiento

El almacenamiento se realiza en una base de datos documental, MongoDB desplegada en una instancia en AWS.

### Servicio phone-service

Este servicio tiene los siguientes endpoints:

1. GET /phones --> Retorna todos los telefonos existentes
2. GET /phone/{reference}  --> Retorna télefono a través de su referencia
3. POST /phone   --> Alta de teléfono. Retorna HTTP 201 con Header Location con URI del recurso creado. 
   Los teléfonos dados de alta se encuentra en: https://github.com/fjgmateu/phone-demo/tree/master/phone-service/src/main/resources

El servicio se encuentra desplegado en instancia aws: http://ec2-52-14-26-165.us-east-2.compute.amazonaws.com:8091


### Servicio order-service

Este servicio tiene los siguientes endpoints:

1. GET /orders --> Retorna todos las ordenes existentes
2. GET /order/{reference}  --> Retorna orden a través de su referencia
3. POST /order   --> Alta de order. Retorna HTTP 201 con Header Location con URI del recurso creado. 
   Los pasos de esta operación son:
     - Recibe orden de un cliente con la referencia de los teléfonos.
	 - Validación de input de entrada. (Datos obligatorios)
	 - Invocación a servicio phone-service para obtener teléfonos existentes.
	 - Comprobación de existencia de todos los teléfonos recibidos en la orden (todos deben existir), obtención de precios y suma de los mismos.
	 - Persistencia en MongoDB la ordencomprueba que todos los 
	 - Retorno con código HTTP 201 con Header Location con URI del recurso creado. 
	 
	 Las ordenes se encuentran en: https://github.com/fjgmateu/phone-demo/tree/master/order-service/src/main/resources
	
	 Ejemplo:
	 ```
	{    
		"name":"cliente 2",
		"surname":"apellido cliente 2",
		"email":"cliente1@cliente.es",
		"phone":[{
				"reference":"ABCD123456"	
			},{
			"reference":"ABCD123418"
			},
			{
			"reference":"ABCD123419"
			}		
		]
	}
    ```

El servicio se encuentra desplegado en instancia aws: http://ec2-18-216-43-22.us-east-2.compute.amazonaws.com:8092

### Docker

En la carpeta docker de todos los servicios se encuentra el fichero DockerFile para la generación de la imagen correspondiente de cada uno de ellos.


### Excepciones

El control de excepciones se realiza con un controlador @ControllerAdvice. 
Todas las excepciones son capturadas por el controlador, lanzando el correspondiente código HTTP asociada al tipo de excepción producida.
 Ejemplos de código HTTP retornados pueden ser: 
     204 --> No se han encontrado datos.
	 500 --> Error interno del servicio.

### Tests

Los dos servicios tienen tests unitarios de funcionamiento de los reposiorios, mapeo de datos DTO-Entity y funcionamiento del Controller.

### Mejoras

Las mejoras a realizar sería montar el sistema en un entorno cloud que permita el balanceo de la instanciación dinámica de las imagenes generadas, 
ya que el despliegue en aws ha sido realizado de manera manual.

Utilización de Swagger para documentación de API.
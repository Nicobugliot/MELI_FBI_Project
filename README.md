# API en Java

* Para poder levantar la aplicación se tiene que ejecutar ProjectApplication.java, por defecto el puerto seteado es el 9898, en el caso de querer cambiarlo se debe de hacer en el application.properties.
* La base de datos que se está usando es MySQL. Para poder utilizar una base de datos ya creada se debe ir al archivo application.properties y cambiar el datasource.url cambiando "demo" por el nombre de la base de datos que hayan creado y también se debería de cambiar tanto el username como la password.

| GET                     | Description                       |
|:----------------------------|:----------------------------------|
| `/api/charges`      | Devuelve una lista con todos los cargos de todos los usuarios |
| `/api/charges/{user_id}`| Devuelve una lista con todos los cargos de un usuario ingresado por url |
| `/api/charges/` | Devuelve una lista con todos los cargos de un usuario especificando el mes y el año |
| `/api/debt/{user_id}` | Devuelve una lista con todos los cargos no pagos de un usuarios |


| POST                     | Description                       |
|:----------------------------|:----------------------------------|
| `/api/charges`      | Crea un cargo |


### Request body
    
    {
      "eventId":1,
      "userId":1,
      "amount": 100.00,
      "currency": "AR",
      "eventType": "VENTA",
      "date":"2019-10-30T14:18:26"
    }
    
### Responses

* 201 - Created
* 400 - Bad Request
* 500 - Internal Server Error


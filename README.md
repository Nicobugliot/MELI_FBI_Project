# API de facturación en Java

* Para poder levantar la aplicación se tiene que ejecutar ProjectApplication.java, por defecto el puerto seteado es el 9898, en el caso de querer cambiarlo se debe de hacer en el application.properties.
* La base de datos que se está usando es MySQL. Para poder utilizar una base de datos ya creada se debe ir al archivo application.properties y cambiar el datasource.url cambiando "demo" por el nombre de la base de datos que hayan creado y también se debería de cambiar tanto el username como la password.

# GET Charges

| GET                     | Description                       |
|:----------------------------|:----------------------------------|
| `/api/charges`      | Devuelve una lista con todos los cargos de todos los usuarios |
| `/api/charges/{user_id}`| Devuelve una lista con todos los cargos de un usuario |
| `/api/debt/{user_id}` | Devuelve una lista con todos los cargos no pagos de un usuarios |

| GET                     | Description                       |
|:----------------------------|:----------------------------------
| `/api/charges/`| Devuelve una lista con los cargos de un usuario especificando mes y año |

### Parameters

* user_id* long int
* month* int
* year* int


# POST Charges

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

* 200 - OK
* 201 - Created
* 400 - Bad Request
* 500 - Internal Server Error


# GET Payments

| GET                     | Description                       |
|:----------------------------|:----------------------------------|
| `/api/payments`      | Devuelve una lista con todos los pagos de todos los usuarios |
| `/api/payments/{user_id}`| Devuelve una lista con todos los pagos de un usuario |

| GET                     | Description                       |
|:----------------------------|:----------------------------------
| `/api/payments/`| Devuelve una lista con los pagos de un usuario especificando mes y año |

### Parameters

* user_id* long int
* month* int
* year* int


# POST Payments

| POST                     | Description                       |
|:----------------------------|:----------------------------------|
| `/api/payments`      | Crea un pago |


### Request body
    
    {
        "userId": 10,
        "amount": 5.00,
        "currency": "AR"
    }
    
### Responses

* 200 - OK
* 201 - Created
* 400 - Bad Request
* 500 - Internal Server Error

# GET Invoice

| GET                     | Description                       |
|:----------------------------|:----------------------------------|
| `/api/invoice`      | Devuelve una lista con todas las facturas de todos los usuarios |
| `/api/invoice/{user_id}`| Devuelve todas las facturas de un usuario |

| GET                     | Description                       |
|:----------------------------|:----------------------------------
| `/api/invoice/`| Devuelve la factura de un usuario especificando mes y año |

### Parameters

* user_id* long int
* month* int
* year* int

### Responses

* 200 - OK
* 500 - Internal Server Error

# GET User status


| GET                     | Description                       |
|:----------------------------|:----------------------------------|
| `/api/status/`      | Devuelve el estado de un usuario dependiendo el mes y el año |

### Parameters

* user_id* long int
* month* int
* year* int

### Responses

* 200 - OK
* 500 - Internal Server Error

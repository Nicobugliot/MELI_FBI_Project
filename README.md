# API de facturación en Java

* Para poder levantar la aplicación se tiene que ejecutar ProjectApplication.java, por defecto el puerto seteado es el 9898, en el caso de querer cambiarlo se debe de hacer en el application.properties.
* La base de datos que se está usando es MySQL. Para poder utilizar una base de datos ya creada se debe ir al archivo application.properties y cambiar el datasource.url cambiando "demo" por el nombre de la base de datos que hayan creado y también se debería de cambiar tanto el username como la password.

# Charges

| GET                     | Description                       |
|:----------------------------|:----------------------------------|
| `/api/charges`      | Devuelve una lista con todos los cargos de todos los usuarios |


### Reponse body
    
    [
        {
            "id": 2,
            "eventId": 1,
            "userId": 1,
            "amount": 100,
            "currency": "AR",
            "eventType": "VENTA",
            "date": "2019-10-30T14:18:26.000+0000",
            "debt": 100,
            "paid_out": 0,
            "invoiceId": 1
        },
        {
            "id": 4,
            "eventId": 2,
            "userId": 2,
            "amount": 140,
            "currency": "USD",
            "eventType": "VENTA",
            "date": "2019-10-30T14:20:26.000+0000",
            "debt": 0,
            "paid_out": 1,
            "invoiceId": 3
        }
    ]
    
### Responses

* 200 - OK
* 400 - Bad Request


| GET                     | Description                       |
|:----------------------------|:----------------------------------|
| `/api/user/{user_id}/charges`| Devuelve una lista con todos los cargos de un usuario |

### Reponse body
    
    [
        {
            "id": 2,
            "eventId": 1,
            "userId": 1,
            "amount": 100,
            "currency": "AR",
            "eventType": "VENTA",
            "date": "2019-10-30T14:18:26.000+0000",
            "debt": 100,
            "paid_out": 0,
            "invoiceId": 1
        }
    ]
    
### Responses

* 200 - OK
* 400 - Bad Request


| GET                     | Description                       |
|:----------------------------|:----------------------------------|
| `/api/user/{user_id}/debt` | Devuelve una lista con todos los cargos no pagos de un usuarios |

### Reponse body
    
    [
        {
            "id": 2,
            "eventId": 1,
            "userId": 1,
            "amount": 100,
            "currency": "AR",
            "eventType": "VENTA",
            "date": "2019-10-30T14:18:26.000+0000",
            "debt": 100,
            "paid_out": 0,
            "invoiceId": 1
        }
    ]
    
### Responses

* 200 - OK
* 400 - Bad Request


| GET                     | Description                       |
|:----------------------------|:----------------------------------
| `/api/charges/`| Devuelve una lista con los cargos de un usuario especificando mes y año |

### Parameters

* user_id *required long*
* month *required int*
* year *required int*

### Response body

    [
        {
            "id": 2,
            "eventId": 1,
            "userId": 1,
            "amount": 100,
            "currency": "AR",
            "eventType": "VENTA",
            "date": "2019-10-30T14:18:26.000+0000",
            "debt": 100,
            "paid_out": 0,
            "invoiceId": 1
        },
        {
            "id": 7,
            "eventId": 3,
            "userId": 1,
            "amount": 25.32,
            "currency": "USD",
            "eventType": "VENTA",
            "date": "2019-10-30T15:20:26.000+0000",
            "debt": 25.32,
            "paid_out": 0,
            "invoiceId": 1
        }
    ]
    
### Responses

* 200 - OK
* 400 - Bad Request


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


# Payments

| GET                     | Description                       |
|:----------------------------|:----------------------------------|
| `/api/payments`      | Devuelve una lista con todos los pagos de todos los usuarios |


### Response body

    [
        {
            "id": 5,
            "userId": 2,
            "amount": 140,
            "currency": "USD",
            "date": "2019-10-08T03:35:35.000+0000"
        },
        {
            "id": 8,
            "userId": 1,
            "amount": 10,
            "currency": "AR",
            "date": "2019-10-08T03:44:25.000+0000"
        }
    ]
    
### Responses

* 200 - OK
* 400 - Bad Request

| GET                     | Description                       |
|:----------------------------|:----------------------------------|
| `/api/user/{user_id}/payments`| Devuelve una lista con todos los pagos de un usuario |


### Response body

    [
        {
            "id": 5,
            "userId": 2,
            "amount": 140,
            "currency": "USD",
            "date": "2019-10-08T03:35:35.000+0000"
        }
    ]
    
### Responses

* 200 - OK
* 400 - Bad Request

| GET                     | Description                       |
|:----------------------------|:----------------------------------
| `/api/payments/`| Devuelve una lista con los pagos de un usuario especificando mes y año |

### Parameters

* user_id *required long*
* month *required int*
* year *required int*

### Reponse body
    [
        {
            "id": 8,
            "userId": 1,
            "amount": 10,
            "currency": "AR",
            "date": "2019-10-08T03:44:25.000+0000"
        },
        {
            "id": 10,
            "userId": 1,
            "amount": 51.32,
            "currency": "AR",
            "date": "2019-10-08T03:45:42.000+0000"
        }
    ]

### Responses

* 200 - OK
* 400 - Bad Request

# Payments

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

* 201 - Created
* 400 - Bad Request

# Invoice

| GET                     | Description                       |
|:----------------------------|:----------------------------------|
| `/api/invoice`      | Devuelve una lista con todas las facturas de todos los usuarios |

### Response body

    [
        {
            "id": 1,
            "userId": 1,
            "month": 10,
            "year": 2019,
            "debt": 1519.9
        },
        {
            "id": 3,
            "userId": 2,
            "month": 10,
            "year": 2019,
            "debt": 0
        }
    ]

### Responses

* 200 - OK
* 400 - Bad Request


| GET                     | Description                       |
|:----------------------------|:----------------------------------|
| `/api/user/{user_id}/invoice`| Devuelve todas las facturas de un usuario |

### Response body

    [
        {
            "id": 1,
            "userId": 1,
            "month": 10,
            "year": 2019,
            "debt": 1519.9
        }
    ]

### Responses

* 200 - OK
* 400 - Bad Request


| GET                     | Description                       |
|:----------------------------|:----------------------------------
| `/api/invoice/`| Devuelve la factura de un usuario especificando mes y año |

### Parameters

* user_id *required long*
* month *required int*
* year *required int*

### Response body

    [
        {
            "id": 3,
            "userId": 2,
            "month": 10,
            "year": 2019,
            "debt": 0
        }
    ]

### Responses

* 200 - OK
* 400 - Bad Request

# User status


| GET                     | Description                       |
|:----------------------------|:----------------------------------|
| `/api/status/`      | Devuelve el estado de un usuario dependiendo el mes y el año |

### Parameters

* user_id *required long*
* month *required int*
* year* *required int*

### Request body

    {
        "charges": [
            {
                "id": 2,
                "eventId": 1,
                "userId": 1,
                "amount": 25.32,
                "currency": "USD",
                "eventType": "VENTA",
                "date": "2019-10-30T15:20:26.000+0000",
                "debt": 1429.9,
                "paid_out": 0,
                "invoiceId": 1
            },
            {
                "id": 3,
                "eventId": 2,
                "userId": 1,
                "amount": 40.12,
                "currency": "AR",
                "eventType": "VENTA",
                "date": "2019-10-30T15:20:26.000+0000",
                "debt": 40.12,
                "paid_out": 0,
                "invoiceId": 1
            }
        ],
        "payments": [
            {
                "id": 4,
                "userId": 1,
                "amount": 51.32,
                "currency": "AR",
                "date": "2019-10-08T03:55:12.000+0000"
            }
        ],
        "debt": 1470.02
    }


### Responses

* 200 - OK

# Association table

| GET                     | Description                       |
|:----------------------------|:----------------------------------|
| `/api/associations`      | Devuelve una lista que muestra qué pagos están asociados a qué cargos |


### Request body

     [
        {
            "id": 4,
            "userId": 1,
            "amount": 51.32,
            "currency": "AR",
            "date": "2019-10-08T03:55:12.000+0000"
        },
        {
            "id": 6,
            "userId": 1,
            "amount": 1.32,
            "currency": "AR",
            "date": "2019-10-08T04:19:49.000+0000"
        }
    ]   


### Responses

* 200 - OK

# Supuestos

1. Los cargos y los pagos se almacenan con la moneda en la cual se ingresó ( USD o AR ) pero dentro del servidor se maneja únicamente la moneda AR, esto se puede ver por ejemplo en el campo "debt" de los cargos.
2. Las facturas se dividieron por id, mes y año con su respectiva deuda en el caso de tenerla.
3. El status del usuario se prefirió hacerlo por mes también y contiene todos los cargos y pagos que haya realizado.

# Consideraciones

1. Se consideró realizar la respuesta lo más rápido posible, es por eso la función asincrónica dentro de los cargos, la idea tras eso fue que, una vez que sepamos que el cargo estaba apto para ser guardado, devolver un status correcto y luego proceder a guardar el cargo en la base de datos.
2. La tabla de asociación no se utiliza dentro del servicio pero se puede usar para fijarse qué pago está asociado a qué cargo y es por eso que se disponibiliza una url para obtenerla.

# Proximos pasos para mejorar
1. Faltan agregar un par de excepciones (en proceso por falta de tiempo) que deberían de verificar que para los GET request, si no existe el usuario dentro de la base de datos devuelva 404 en vez de una lista vacía.
2. Agregar lo del monto a favor del cliente en caso de que se pague más de lo que se debía.

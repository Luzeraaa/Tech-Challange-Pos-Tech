# Watchwatt

## Endpoints

***

### User:

Validate user em password

```bash
curl --request GET \
  --url 'http://localhost:8080/api/watchwatt/user/validate_user?cpf=46714251220&password=123456' \
  --header 'X-API-Version: 1'
```

Get User by cpf

```bash
curl --request GET \
  --url 'http://localhost:8080/api/watchwatt/user?cpf=46714251220' \
  --header 'X-API-Version: 1'
```

Get all user

```bash
curl --request GET \
  --url http://localhost:8080/api/watchwatt/user/all \
  --header 'X-API-Version: 1'
```

Create User

```bash
curl --request POST \
  --url http://localhost:8080/api/watchwatt/user \
  --header 'Content-Type: application/json' \
  --header 'X-API-Version: 1' \
  --data '{
	"cpf": "46714251220",
	"name": "Fulano de tal",
	"birthday": "2023-01-01",
	"gender": "MALE",
	"email": "fulano@fiap.com",
	"password": "123456"
}'
```

***

### Address

Get all Address

```bash
curl --request GET \
  --url 'http://localhost:8080/api/watchwatt/address/all?=' \
  --header 'X-API-Version: 1'
```

Get address by id

```bash
curl --request GET \
  --url 'http://localhost:8080/api/watchwatt/address?id=1' \
  --header 'X-API-Version: 1'
```

Create address via cep

```bash
curl --request POST \
  --url http://localhost:8080/api/watchwatt/address/via_cep \
  --header 'Content-Type: application/json' \
  --header 'X-API-Version: 1' \
  --data '{
	"zip_code": "06535-045",
	"number": 10,
	"reference": "Next to UBS"
}'
```

Create address

```bash
curl --request POST \
  --url http://localhost:8080/api/watchwatt/address \
  --header 'Content-Type: application/json' \
  --header 'X-API-Version: 1' \
  --data '{
	"zip_code": "06535-055",
	"street": "avenida",
	"number": 10,
	"neighborhood": "santana de parnaiba",
	"city": "sp",
	"state": "sp",
	"reference": "unidade de saude de são paulo"
}'  
```

***

### Appliance

Create appliance

```bash
curl --request POST \
  --url http://localhost:8080/api/watchwatt/appliances \
  --header 'Content-Type: application/json' \
  --header 'X-API-Version: 1' \
  --data '{
	"name": "freeze",
	"model": "master blaster",
	"power": 1500
}' 
```

Get all appliance

```bash
curl --request GET \
  --url http://localhost:8080/api/watchwatt/appliances/all \
  --header 'X-API-Version: 1'
```

Get appliance by id

```bash
curl --request GET \
  --url 'http://localhost:8080/api/watchwatt/appliances?id=1' \
  --header 'X-API-Version: 1'
```
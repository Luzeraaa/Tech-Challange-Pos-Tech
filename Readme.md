![](https://i.imgur.com/tB3zVOi.png)
![Licença](https://img.shields.io/badge/license-MIT-green)
![Badge em Desenvolvimento](https://img.shields.io/badge/release%20date-july/23-yellow)
![Maven Version](https://img.shields.io/badge/maven-4.0.0-blue)
![Java Version](https://img.shields.io/badge/java-17-blue)

# <h1 align="center">WatchWatt</h1>
Apresentamos o **WatchWatt**, uma inovadora ferramenta que revolucionará a forma como você gerencia seu consumo energético. Com nosso sistema em sua mão, você pode se cadastrar rapidamente, cadastrar seus dependentes e registra seus equipamentos elétricos, atribuindo-os aos respectivos endereços. Acompanhe de perto o gasto energético de cada um deles, obtendo insights valiosos para economizar energia e reduzir suas contas. Simplifique sua vida, torne-se mais sustentável e economize com nossa ferramenta completa de gestão energética.

## 📄 Índice
* [Descrição do Projeto](#descrição-do-projeto)
* [Funcionalidades](#funcionalidades)
* [Acesso ao Projeto](#acesso-ao-projeto)
* [Tecnologias utilizadas](#tecnologias-utilizadas)
* [Pessoas Desenvolvedoras do Projeto](#pessoas-desenvolvedoras)

## ⚙️ Descrição do Projeto

O projeto consiste no desenvolvimento de um sistema web completo, com interfaces e APIs, para cadastro de Pessoas, Casas e Eletrodomésticos, com o objetivo principal de calcular o consumo mensal de energia. A empresa responsável pelo projeto é especializada em equipamentos de monitoramento de energia para uso residencial e comercial.

Os principais produtos oferecidos pela empresa são adaptadores elétricos que permitem monitorar o consumo de energia de aparelhos eletrônicos. Esses adaptadores são compatíveis com a maioria dos dispositivos eletroeletrônicos e eletrodomésticos. Eles se conectam a redes WiFi e enviam os dados de consumo para serviços em nuvem, onde são processados e apresentados em um painel de controle online.

A tecnologia utilizada nos equipamentos garante a precisão na medição de energia elétrica, e a interface é intuitiva e de fácil utilização. Os usuários podem monitorar o consumo em tempo real e acessar o histórico de consumo para identificar aparelhos com alto consumo e adotar medidas para reduzir o consumo de energia.

Os benefícios para os usuários são diversos, incluindo a possibilidade de economizar na conta de luz, contribuir para a preservação do meio ambiente e adotar práticas mais sustentáveis no consumo de energia.

Em resumo, o projeto visa desenvolver um sistema web completo para monitoramento e controle do consumo de energia, utilizando adaptadores elétricos conectados a redes WiFi e integrados a serviços em nuvem, proporcionando aos usuários informações precisas e úteis para reduzir o consumo e promover a eficiência energética.

## 🔨 Funcionalidades

Os endpoints e os dados necessários para consumo da API construída estão disponíveis no [tópico](#endpoints) relacionado abaixo.

✔️ Cadastro de usuários bem como seus dependentes:
* Os cadastro serão únicos, validados através do CPF e/ou e-mail utilizado durante o cadastro;
* Cada usuário poderá cadastrar seus endereços e respectivos equipamentos eletrodomésticos

✔️ Cadastro de Endereços
* O cadastro de endereço será realizado mediante interface, com a API [ViaCep](https://viacep.com.br), uma vez informado o CEP pelo usuário.
* Casos em que a API esteja indisponível ainda será possível que o usuário faça o cadastro manualmente.

✔️ Cadastro de Eletrodomésticos
* Cada usuário poderá cadastrar seus respectivos eletrodomésticos.

## 📁 Acesso ao projeto

Você pode [acessar o código fonte do projeto inicial](https://github.com/Luzeraaa/postech) ou [baixá-lo](https://github.com/Luzeraaa/postech/archive/refs/heads/main.zip).

## 🛠️ Abrir e rodar o projeto

Após baixar o projeto, você pode abrir com a IDE de preferência e configurar as variáveis de ambiente para acessar o banco de dados.

## Tecnologias utilizadas

- Java 17: versão Java que garante estabilidade e com recursos atualizados.
- Spring MVC & JPA: O Spring MVC é responsável pela camada de controle e apresentação do aplicativo, lidando com a interação do usuário, enquanto o JPA cuida da persistência de dados, abstraindo a camada de acesso a banco de dados.
- Maven: Gerenciador de dependências mais familiar ao grupo.
- Lombok: Biblioteca utilizada para gerenciamento das camadas de segurança (encriptação de dados sensíveis) e seus construtores.
- Banco de Dados H2: Banco de dados em memória para persistência dos dados durante execução da aplicação.
- Hibernate: Framework utilizado para gerenciamento das camadas de segurança.
- Regex: Validação de caracteres e máscaras de entrada.

## Arquitetura

A arquitetura do projeto tem como base o DDD (Domain-Driven Design) e MVC (Model View Controller) para definição das responsabilidade dos objetos utilizados na aplicação.

## Desafios

- Definir e compreender os relacionamentos
- Incluir as regras de validações
- Gerenciamento de exceptions para possíveis erros
- Definição da arquitetura do projeto (DDD/MVC/etc)

## 🙋 Pessoas Desenvolvedoras do Projeto

| [<img src="https://avatars.githubusercontent.com/u/42851702?v=4" width=115><br><sub>Lucas Mendes</sub>](https://github.com/Luzeraaa) | [<img src="https://avatars.githubusercontent.com/u/56560361?v=4" width=115><br><sub>Aderson Neto</sub>](https://github.com/avcneto) | [<img src="https://avatars.githubusercontent.com/u/19624216?v=4" width=115><br><sub>Felipe Chimin</sub>](https://github.com/flpchimin) | [<img src="https://avatars.githubusercontent.com/u/52970727?v=4" width=115><br><sub>Gustavo Makimori</sub>](https://github.com/gyfmaki) | [<img src="https://avatars.githubusercontent.com/u/88151987?v=4" width=115><br><sub>Pedro Paratelli</sub>](https://github.com/PedroParatelli) |
|:------------------------------------------------------------------------------------------------------------------------------------:|:-----------------------------------------------------------------------------------------------------------------------------------:|:--------------------------------------------------------------------------------------------------------------------------------------:|:---------------------------------------------------------------------------------------------------------------------------------------:|:---------------------------------------------------------------------------------------------------------------------------------------------:|

## Endpoints

***

### User:

Validate user and password

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

Update User

```bash
curl --request PUT \
  --url 'http://localhost:8080/api/watchwatt/user?id=1' \
  --header 'Content-Type: application/json' \
  --header 'X-API-Version: 1' \
  --data '{
	"cpf": "46714251220",
	"name": "Fulano de tal atualizado",
	"birthday": "2023-01-01",
	"gender": "MALE",
	"email": "fulano@fiap.com",
	"password": "123456"
}'

```
Delete User by id

```bash
curl --request DELETE \
  --url 'http://localhost:8080/api/watchwatt/user?id=1' \
  --header 'X-API-Version: 1'

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
Delete Address by id

```bash
curl --request DELETE \
  --url 'http://localhost:8080/api/watchwatt/address?id=1' \
  --header 'X-API-Version: 1'

```
Update Address by id
```bash
curl --request PUT \
  --url 'http://localhost:8080/api/watchwatt/address?id=1' \
  --header 'Content-Type: application/json' \
  --header 'X-API-Version: 1' \
  --data '{
    "id": 1,
    "zip_code": "70150-900",
    "street": "Praça dos Três Poderes",
    "number": 10,
    "neighborhood": "Zona Cívico-Administrativa",
    "city": "Brasília",
    "state": "DF",
    "reference": "Next to UBS"
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
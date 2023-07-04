![](https://i.imgur.com/tB3zVOi.png)
![Licença](https://img.shields.io/badge/license-MIT-green)
![Badge em Desenvolvimento](https://img.shields.io/badge/release%20date-july/23-yellow)
![Maven Version](https://img.shields.io/badge/maven-4.0.0-blue)
![Java Version](https://img.shields.io/badge/java-17-blue)

# <h1 align="center">WatchWatt</h1>

Apresentamos o **WatchWatt**, uma inovadora ferramenta que revolucionará a forma como você gerencia seu consumo
energético. Com nosso sistema em sua mão, você pode se cadastrar rapidamente, cadastrar seus dependentes e registra seus
equipamentos elétricos, atribuindo-os aos respectivos endereços. Acompanhe de perto o gasto energético de cada um deles,
obtendo insights valiosos para economizar energia e reduzir suas contas. Simplifique sua vida, torne-se mais sustentável
e economize com nossa ferramenta completa de gestão energética.

## 📄 Índice

* [Descrição do Projeto](#test)
* [Arquitetos Responsáveis](#arquitetos-responsáveis)
* [Funcionalidades](#funcionalidades)
* [Acesso ao Projeto](#acesso-ao-projeto)
* [Execução do Projeto](#execução-do-projeto)
* [Tecnologias utilizadas](#tecnologias-utilizadas)
* [Documentação Técnica](#documentação-técnica)
* [Desafios](#desafios)
* [Endpoints](#endpoints)

## Descrição do Projeto

O projeto consiste no desenvolvimento de um sistema web completo, com interfaces e APIs, para cadastro de Pessoas, Casas
e Eletrodomésticos, com o objetivo principal de calcular o consumo mensal de energia. A empresa responsável pelo projeto
é especializada em equipamentos de monitoramento de energia para uso residencial e comercial.

Os principais produtos oferecidos pela empresa são adaptadores elétricos que permitem monitorar o consumo de energia de
aparelhos eletrônicos. Esses adaptadores são compatíveis com a maioria dos dispositivos eletroeletrônicos e
eletrodomésticos. Eles se conectam a redes WiFi e enviam os dados de consumo para serviços em nuvem, onde são
processados e apresentados em um painel de controle online.

A tecnologia utilizada nos equipamentos garante a precisão na medição de energia elétrica, e a interface é intuitiva e
de fácil utilização. Os usuários podem monitorar o consumo em tempo real e acessar o histórico de consumo para
identificar aparelhos com alto consumo e adotar medidas para reduzir o consumo de energia.

Os benefícios para os usuários são diversos, incluindo a possibilidade de economizar na conta de luz, contribuir para a
preservação do meio ambiente e adotar práticas mais sustentáveis no consumo de energia.

Em resumo, o projeto visa desenvolver um sistema web completo para monitoramento e controle do consumo de energia,
utilizando adaptadores elétricos conectados a redes WiFi e integrados a serviços em nuvem, proporcionando aos usuários
informações precisas e úteis para reduzir o consumo e promover a eficiência energética.

## Arquitetos Responsáveis

| [<img src="https://avatars.githubusercontent.com/u/42851702?v=4" width=115><br><sub>Lucas Mendes</sub>](https://github.com/Luzeraaa) | [<img src="https://avatars.githubusercontent.com/u/56560361?v=4" width=115><br><sub>Aderson Neto</sub>](https://github.com/avcneto) | [<img src="https://avatars.githubusercontent.com/u/19624216?v=4" width=115><br><sub>Felipe Chimin</sub>](https://github.com/flpchimin) | [<img src="https://avatars.githubusercontent.com/u/52970727?v=4" width=115><br><sub>Gustavo Makimori</sub>](https://github.com/gyfmaki) | [<img src="https://avatars.githubusercontent.com/u/88151987?v=4" width=115><br><sub>Pedro Paratelli</sub>](https://github.com/PedroParatelli) |
|:------------------------------------------------------------------------------------------------------------------------------------:|:-----------------------------------------------------------------------------------------------------------------------------------:|:--------------------------------------------------------------------------------------------------------------------------------------:|:---------------------------------------------------------------------------------------------------------------------------------------:|:---------------------------------------------------------------------------------------------------------------------------------------------:|

## Funcionalidades

Os endpoints e os dados necessários para consumo da API construída estão disponíveis no [tópico](#endpoints) relacionado
abaixo.

Cadastro de usuários bem como seus dependentes:

* Os cadastro serão únicos, validados através do CPF e/ou e-mail utilizado durante o cadastro;
* Cada usuário poderá cadastrar seus endereços e respectivos equipamentos eletrodomésticos

Cadastro de Endereços

* O cadastro de endereço será realizado mediante interface, com a API [ViaCep](https://viacep.com.br), uma vez informado
  o CEP pelo usuário.
* Casos em que a API esteja indisponível ainda será possível que o usuário faça o cadastro manualmente.

Cadastro de Eletrodomésticos

* Cada usuário poderá cadastrar seus respectivos eletrodomésticos.

## Acesso ao projeto

Você pode [acessar o código fonte do projeto inicial](https://github.com/Luzeraaa/postech)
ou [baixá-lo](https://github.com/Luzeraaa/postech/archive/refs/heads/main.zip).

## Execução do Projeto

Após baixar o projeto, você pode abrir com a IDE de preferência e configurar as variáveis de ambiente para acessar o
banco de dados.

1. Fazer o [download](https://github.com/Luzeraaa/postech/archive/refs/heads/main.zip);
2. Abrir com IDE de preferência;
3. Configurar as varíaveis de ambiente para acessar o banco de dados:
    * _DATASOURCE_PASSWORD=fiap_
    * _DATASOURCE_USER=fiap_
4. Executar o projeto.

**Bonus**: Após a primeira execução um script SQL populará de forma automatica o banco de dados com 10 registros
pré determinados, a fim de otimizar o funcionamento do sistema.

## Tecnologias utilizadas

- Java 17: versão Java que garante estabilidade e com recursos atualizados.
- Spring MVC & JPA: O Spring MVC é responsável pela camada de controle e apresentação do aplicativo, lidando com a
  interação do usuário, enquanto o JPA cuida da persistência de dados, abstraindo a camada de acesso a banco de dados.
- Maven: Gerenciador de dependências mais familiar ao grupo.
- Lombok: Biblioteca utilizada para gerenciamento das camadas de segurança (encriptação de dados sensíveis) e seus
  construtores.
- Banco de Dados H2: Banco de dados em memória para persistência dos dados durante execução da aplicação.
- Hibernate: Framework utilizado para gerenciamento das camadas de segurança.
- @Validator: Validação de caracteres e máscaras de entrada.

## Documentação Técnica

A versão 17 do Java foi adotada uma vez que é a versão atualizada mais estável no tempo de desenvolvimento deste
projeto.
O Maven é amplamente adotado e possui uma estrutura mais simples, o que facilita a configuração e o gerenciamento de
dependências. Além disso, o Maven possui uma vasta biblioteca de plugins, integração com repositórios centrais e uma
documentação extensa, tornando-o uma escolha popular e confiável para a construção e gerenciamento de projetos Java.

A arquitetura utilizada se baseia na combinação de conceitos MVC (Model-View-Controller) e DDD (Domain Driven Design).
Ao combinar MVC e DDD, podemos obter os benefícios de ambos os conceitos. A arquitetura MVC pode ser usada para a
divisão das responsabilidades de apresentação e controle de fluxo da aplicação, enquanto o DDD pode ser usado para criar
um modelo de domínio rico e encapsulado.

Para determinar o banco de dados a ser utilizado foi levado em consideração a performance do mesmo juntamente com sua
usabilidade e compatibilidade com JBDC. O Banco H2 é um banco de dados SQL escrtio em Java, leve e simples de incorporar
em aplicativos Java. Suas ferramentas de desenvolvimento apresentam recursos avançados e inclui um console de
administração
baseado em navegador.Para gerencias as alterações no banco de dados optamos por utilizar o Flyway que é uma ferramenta
de migração de banco de dados que pode ser usada para gerenciar alterações em bancos de dados relacionais.
Ele é usado para garantir que as alterações no banco de dados sejam aplicadas de forma consistente e controlada.
O Flyway é uma ferramenta popular para gerenciar migrações de banco de dados em aplicativos Spring Boot.
Ele pode ser usado para criar e executar scripts SQL que atualizam o esquema do banco de dados, também pode ser usado
para gerenciar a versão do banco de dados e garantir que as alterações sejam aplicadas na ordem correta.

O Lombok é uma biblioteca para Java que permite reduzir a verbosidade do código, automatizando a geração de getters,
setters, construtores e outros métodos comuns. Utilizamos o Lombok no projeto para maior produtividade, reduzindo a
quantidade de código boilerplate a ser escrito e facilitando a manutenção do código ao eliminar tarefas repetitivas.
Além disto, a biblioteca contém a anotação Slf4j para logar erros dentro da aplicação, não a expondo ao usuário final.

Decidimos utilizar o Spring Security porque se trata de um framework de autenticação e autorização para aplicações Java.
O Security nos fornece recursos de segurança para proteger nossa aplicação contra ameaças de cibernéticas.
O Spring Boot Security é uma extensão do Spring Security que fornece recursos adicionais para proteger aplicativos
baseados em Spring Boot. A criptografia de senha é uma das
funcionalidades fornecidas pelo Spring Boot Security e
utilizamos para permite que as senhas sejam armazenadas com segurança no banco de dados, garantindo que elas não
possam ser lidas por usuários não autorizados.

O Hibernate é amplamente utilizado no desenvolvimento Java devido às suas vantagens significativas. Ele simplifica o
acesso a dados, abstraindo o mapeamento objeto-relacional e automatizando tarefas comuns, aumentando a produtividade dos
desenvolvedores. Além disso, oferece portabilidade, permitindo executar aplicativos em diferentes bancos de dados, e
suporta consultas flexíveis, cache e gerenciamento de transações, proporcionando um ambiente eficiente e robusto para o
desenvolvimento de aplicativos que interagem com bancos de dados relacionais.

O uso do @Validator com expressões regulares oferece uma maneira eficiente de validar e garantir a integridade dos dados
em aplicativos Java. Ao aplicar validações por meio de expressões regulares, é possível verificar se os dados inseridos
atendem a um determinado padrão, como formato de e-mail, número de telefone, CPF, entre outros. Isso ajuda a garantir a
consistência dos dados e reduz a possibilidade de erros ou entradas inválidas. O @Validator com expressões regulares é
uma abordagem poderosa e flexível para a validação de dados em aplicativos Java.

## Desafios

- Definir e compreender os relacionamentos
- Incluir as regras de validações
- Gerenciamento de exceptions para possíveis erros
- Definição da arquitetura do projeto (DDD/MVC/etc)

## Endpoints

***
Disclamer: para a propriedade 'gender' os valores possíveis são: 'MALE', 'FEMALE' or 'OTHERS'.<br> Para a propriedade "degree_kinship" os valores possíveis são: 'FATHER', 'MOTHER', 'SON','DAUGHTER','SISTER','BROTHER','HUSBAND','WIFE' or 'OTHERS'. 
### User:

Create User

```bash

curl --request POST \
  --url http://localhost:8080/api/watchwatt/user \
  --header 'Content-Type: application/json' \
  --header 'X-API-Version: 1' \
  --data '{
	"cpf": "99954627022",
	"name": "Vlad da Silva",
	"birthday": "2023-01-01",
	"gender": "MALE",
	"email": "teste@fiap.com",
	"password": "123456",
	"kinship": [
		{
			"name": "Igor Junior",
			"degree_kinship": "BROTHER"
		}
	]
}'

```


Get user by CPF number

```bash
curl --request GET \
  --url 'http://localhost:8080/api/watchwatt/user?cpf=83055117077' \
  --header 'X-API-Version: 1'
```

Get all users

```bash
curl --request GET \
  --url http://localhost:8080/api/watchwatt/user/all \
  --header 'X-API-Version: 1'
```

Update User by id

```bash
curl --request PUT \
  --url 'http://localhost:8080/api/watchwatt/user?id=1' \
  --header 'Content-Type: application/json' \
  --header 'X-API-Version: 1' \
  --data '{
	"cpf": "46714251220",
	"name": "João da Silva",
	"birthday": "2023-01-01",
	"gender": "MALE",
	"email": "fulano@fiap.com",
	"password": "123456"
}'

```
Validate CPF and password

```bash
curl --request GET \
  --url 'http://localhost:8080/api/watchwatt/user/validate_user?cpf=83055117077&password=123456' \
  --header 'X-API-Version: 1'
```

Delete User by id

```bash
curl --request DELETE \
  --url 'http://localhost:8080/api/watchwatt/user?id=1' \
  --header 'X-API-Version: 1'

```

Kinship
Get kinship by CPF number

```bash
curl --request GET \
  --url 'http://localhost:8080/api/watchwatt/kinship?cpf=83055117077' \
  --header 'X-API-Version: 1'
```

Add kinship by CPF number

```bash
curl --request POST \
  --url 'http://localhost:8080/api/watchwatt/kinship?cpf=83055117077' \
  --header 'Content-Type: application/json' \
  --header 'X-API-Version: 1' \
  --data '[
	{
		"name": "Renato",
		"degree_kinship": "SON"
	}
]'

```

Update kinship by CPF number and kinship id

```bash 
curl --request PUT \
  --url 'http://localhost:8080/api/watchwatt/kinship?userId=1&kinshipId=2' \
  --header 'Content-Type: application/json' \
  --header 'X-API-Version: 1' \
  --data '{
	"name": "João",
	"degree_kinship": "BROTHER"
}'
```

Delete kinship by users id and kinship id

```bash 
curl --request DELETE \
  --url 'http://localhost:8080/api/watchwatt/kinship?userId=1&kinshipId=1' \
  --header 'X-API-Version: 1'

```

***

### Address

Create Address by "ViaCEP" API

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

Create Address manually

```bash
curl --request POST \
  --url http://localhost:8080/api/watchwatt/address \
  --header 'Content-Type: application/json' \
  --header 'X-API-Version: 1' \
  --data '{
	"zip_code": "06535-055",
	"street": "Alecrins street",
	"number": 10,
	"neighborhood": "santana de parnaiba",
	"city": "sp",
	"state": "sp",
	"reference": "unidade de saude de são paulo"
}'  


```


Get all Address

```bash
curl --request GET \
  --url 'http://localhost:8080/api/watchwatt/address/all?=' \
  --header 'X-API-Version: 1'
```

Get Address by id

```bash
curl --request GET \
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

Delete Address by id

```bash
curl --request DELETE \
  --url 'http://localhost:8080/api/watchwatt/address?id=1' \
  --header 'X-API-Version: 1'

```

***

### Appliance

Create Appliance

```bash
curl --request POST \
  --url http://localhost:8080/api/watchwatt/appliances \
  --header 'Content-Type: application/json' \
  --header 'X-API-Version: 1' \
  --data '{
	"name": "freeze",
	"model": "Nimbus 2000",
	"power": 1500
}' 
```

Get all Appliance

```bash
curl --request GET \
  --url http://localhost:8080/api/watchwatt/appliances/all \
  --header 'X-API-Version: 1'
```

Get Appliance by id

```bash
curl --request GET \
  --url 'http://localhost:8080/api/watchwatt/appliances?id=1' \
  --header 'X-API-Version: 1'
```

Update Appliance by id

```bash
curl --request PUT 'http://localhost:8080/api/watchwatt/appliances?id=1' \
--header 'X-API-Version: 1' \
--header 'Content-Type: application/json' \
--data '{
	"name": "freezer updated",
	"model": "Nimbus 2000",
	"power": 2500
}'
```

Delete Appliance by id

```bash
curl --request DELETE \
  --url 'http://localhost:8080/api/watchwatt/appliances?id=1' \
  --header 'X-API-Version: 1'
```


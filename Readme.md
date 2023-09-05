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

* [Descrição do Projeto](#descrição-do-projeto)
* [Arquitetos Responsáveis](#arquitetos-responsáveis)
* [Funcionalidades](#funcionalidades)
* [Acesso ao Projeto](#acesso-ao-projeto)
* [Execução do Projeto](#execução-do-projeto)
* [Tecnologias utilizadas](#tecnologias-utilizadas)
* [Acesso ao Banco de Dados](#acesso-ao-banco-de-dados)
* [Relatório Técnico](#relatório-técnico)
* [Desafios](#desafios)
* [Documentação Técnica](#documentação-técnica)

## Descrição do Projeto

O projeto consiste no desenvolvimento de um sistema web completo, com interfaces e endpoints, para cadastro de Pessoas,
Endereços
e Eletrodomésticos, com o objetivo principal de calcular o consumo mensal de energia. A empresa responsável pelo projeto
é especializada em equipamentos de monitoramento de energia para uso residencial e comercial.

Os principais produtos oferecidos pela empresa são adaptadores elétricos que permitem monitorar o consumo de energia de
aparelhos eletrônicos. Esses adaptadores são compatíveis com a maioria dos dispositivos eletroeletrônicos e
eletrodomésticos. Eles se conectam a redes WiFi e enviam os dados de consumo para serviços em nuvem, onde são
processados e apresentados em um painel de controle online.

A tecnologia utilizada nos equipamentos garante a precisão na medição de energia elétrica, e a interface é intuitiva e
de fácil utilização. Os usuários podem monitorar o consumo em tempo real e acessar o histórico de consumo para
identificar aparelhos com alto consumo e adotar medidas para reduzir o congit adgit stsumo de energia.

Os benefícios para os usuários são diversos, incluindo a possibilidade de economizar na conta de luz, contribuir para a
preservação do meio ambiente e adotar práticas mais sustentáveis no consumo de energia.

Em resumo, o projeto visa desenvolver um sistema web completo para monitoramento e controle do consumo de energia,
utilizando adaptadores elétricos conectados a redes WiFi e integrados a serviços em nuvem, proporcionando aos usuários
informações precisas e úteis para reduzir o consumo e promover a eficiência energética.

## Arquitetos Responsáveis

| [<img src="https://avatars.githubusercontent.com/u/42851702?v=4" width=115><br><sub>Lucas Mendes</sub>](https://github.com/Luzeraaa) | [<img src="https://avatars.githubusercontent.com/u/56560361?v=4" width=115><br><sub>Aderson Neto</sub>](https://github.com/avcneto) | [<img src="https://avatars.githubusercontent.com/u/19624216?v=4" width=115><br><sub>Felipe Chimin</sub>](https://github.com/flpchimin) | [<img src="https://avatars.githubusercontent.com/u/52970727?v=4" width=115><br><sub>Gustavo Makimori</sub>](https://github.com/gyfmaki) | [<img src="https://avatars.githubusercontent.com/u/88151987?v=4" width=115><br><sub>Pedro Paratelli</sub>](https://github.com/PedroParatelli) |
|:------------------------------------------------------------------------------------------------------------------------------------:|:-----------------------------------------------------------------------------------------------------------------------------------:|:--------------------------------------------------------------------------------------------------------------------------------------:|:---------------------------------------------------------------------------------------------------------------------------------------:|:---------------------------------------------------------------------------------------------------------------------------------------------:|

## Funcionalidades

Os endpoints e os dados necessários para consumo da API construída estão disponíveis no [tópico](#documentação-técnica)
abaixo.

Cadastro de usuários bem como seus dependentes:

* Os cadastro serão únicos, validados através do CPF e/ou e-mail utilizado durante o cadastro;
* Cada usuário poderá cadastrar seus endereços, usuários dependentes (parentesco) e respectivos equipamentos eletrodomésticos

Cadastro de Endereços

* O cadastro de endereço será realizado mediante interface, com a API [ViaCep](https://viacep.com.br), uma vez informado
  o CEP pelo usuário.
* Casos em que a API esteja indisponível ainda será possível que o usuário faça o cadastro manualmente.

Cadastro de Eletrodomésticos

* Cada usuário poderá cadastrar seus respectivos eletrodomésticos por endereço cadastrado

## Acesso ao projeto

Você pode [acessar o código fonte do projeto inicial](https://github.com/Luzeraaa/postech)
ou [baixá-lo](https://github.com/Luzeraaa/postech/archive/refs/heads/main.zip).

## Execução do Projeto

Após baixar o projeto, você pode abrir com a IDE de preferência e configurar as variáveis de ambiente para acessar o
banco de dados.


1. Fazer o [download](https://github.com/Luzeraaa/postech/archive/refs/heads/main.zip);
2. Instalar Docker Desktop (Caso esteja em ambiente Windowns instalar WSL);
2. Abrir com IDE de preferência;
3. Executar via terminal:
    *  `docker-compose up`
    * Certifique-se de executar dentro do diretório do projeto "watchwatt" onde esta localizado o arquivo docker compose.
4. Configurar as varíaveis de ambiente para acessar o banco de dados:
    * _DATASOURCE_PASSWORD=fiap_
    * _DATASOURCE_USER=fiap_
    * _SECURITY_USER=fiap_
    * _SECURITY_PASSWORD=fiap_
    * _SECURITY_ROLE=ADMIN_
    * _JTW_TOKEN_KEY=watchwatt4d1381e44ae829040b6568e9e2b2cfa72c2f95946a04a760key_
    * _JWT_TOKEN_EXPIRATION=3600000_
5. Executar o projeto.
6. Os métodos devem ser executados na seguinte ordem:
   * Criação do usuário;
   * Login para resgate do JWT Token; 
   * Criação do endereço relacionado ao usuário;
   * Criação do eletrodoméstico relacionado ao endereço;


## Acesso ao Banco de Dados

A persistência de dados será realizado através do banco de dados PostgresSQL. Este banco irá rodar em container via Dokcer.
Maiores detalhes de versão da imagem e configurações de portas verificar arquivo:

* [docker-compose.yml](watchwatt/docker-compose.yml)

## Tecnologias utilizadas

- Java 17 (Versão atualizada e estável da linguagem Java)
- Maven (Ferramenta amplamente adotada para gerenciamento de dependências)
- Spring: Boot, Security, MVC, Data JPA, Web (Frameworks populares para desenvolvimento de aplicativos Java)
- Hibernate (Framework de mapeamento objeto-relacional para acesso a dados)
- JPA (Java Persistence API) (Especificação padrão para persistência de dados em Java)
- Lombok (Biblioteca para reduzir a verbosidade do código e automatizar tarefas comuns)
- Jakarta Bean Validation (Especificação para validação de dados em Java)
- JWT (Json Web token)
- Auth0
- Swagger & OpenAPI (Ferramentas e especificações para projetar, criar e documentar APIs RESTful)
- Docker
- PostgresSQL


<div style="display: inline_block"><br>
<img src=https://raw.githubusercontent.com/github/explore/5b3600551e122a3277c2c5368af2ad5725ffa9a1/topics/java/java.png width="65" height="60"
/>
<img src=https://www.vhv.rs/dpng/d/571-5718602_transparent-ubuntu-logo-png-logo-postman-icon-png.png width="60" height="55"
/>
<img src=https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSAaUBgVyY4CJWh02Lx0PuWeq4EcbeY0-3v0PUJ5BqTxIMAxgSvlkWLY9pKM8ZIo71s4xs&usqp=CAU width="60" height="55"
/>
<img src=https://maven.apache.org/images/maven-logo-black-on-white.png  width="60" height="55"
/>
<img src=https://oopy.lazyrockets.com/api/v2/notion/image?src=https:%2F%2Fs3-us-west-2.amazonaws.com%2Fsecure.notion-static.com%2F3ed7a304-a24b-4c45-831f-1755950e4260%2Flombok.png&blockId=552b6017-489d-4bcd-bb44-803f5e94bac9&width=256   width="60" height="55"
/>
<img src=https://th.bing.com/th/id/R.d8469eae9c8a4aa8ba0104a9d636d5f8?rik=WXdhpHKO0QTl6g&riu=http%3a%2f%2fhmkcode.github.io%2fimages%2fspring%2fspring.png&ehk=l%2b%2fhOIEAi407AyPHHjQT0NnUHU%2fH%2bjQzbnquLbAEdSI%3d&risl=&pid=ImgRaw&r=0 width="60" height="55" width="60" height="55"
/>
<img src=https://upload.wikimedia.org/wikipedia/commons/thumb/2/29/Postgresql_elephant.svg/540px-Postgresql_elephant.svg.png width="60" height="55" width="60" height="55"
/>
<img src=https://blog.cadumagalhaes.dev/assets/docker_moby_logo.png width="60" height="55" width="60" height="55"
/></div>

## Relatório Técnico

A arquitetura utilizada neste projeto baseia-se na combinação de conceitos MVC (Model-View-Controller) e DDD (Domain
Driven Design).
Essa combinação permite obter os benefícios de ambos os conceitos, utilizando a arquitetura MVC para a divisão das
responsabilidades de apresentação e controle de fluxo, e o DDD para criar um modelo de domínio encapsulado e rico.

A versão 17 do Java foi escolhida como base para o projeto devido à sua estabilidade e atualização no momento do
desenvolvimento. Para facilitar a configuração e o gerenciamento de dependências, o projeto adotou o Maven, que possui
uma estrutura simples e ampla biblioteca de plugins. Além disso, o Maven possui uma vasta integração com repositórios
centrais e uma
documentação extensa, tornando-o uma escolha popular e confiável para a construção e gerenciamento de projetos Java.

Para de reduzir a verbosidade e os famosos códigos boilerplates do código, além de automatizar a geração de getters,
setters, construtores e outros métodos comuns, o projeto utilizou o Lombok, uma biblioteca para Java. O Lombok também
fornece a anotação Slf4j para logar erros internos da aplicação, mantendo-os ocultos do usuário final.

O Spring Security foi escolhido como framework de autenticação e autorização para a aplicação Java, fornecendo recursos
de segurança contra ameaças cibernéticas. O Spring Boot Security, uma extensão do Spring Security, foi utilizado para
proteger aplicativos baseados em Spring Boot. A criptografia de senha foi implementada para armazenar as senhas de forma
segura no banco de dados, garantindo que não possam ser lidas por usuários não autorizados.

O Hibernate é amplamente utilizado no desenvolvimento Java devido às suas vantagens significativas. Ele simplifica o
acesso a dados, abstraindo o mapeamento objeto-relacional e automatizando tarefas comuns, aumentando a produtividade dos
desenvolvedores. Além disso, oferece portabilidade, permitindo executar aplicativos em diferentes bancos de dados, e
suporta consultas flexíveis, cache e gerenciamento de transações, proporcionando um ambiente eficiente e robusto para o
desenvolvimento de aplicativos que interagem com bancos de dados relacionais.

Para validar e garantir a integridade dos dados no aplicativo Java, foi utilizado o Jakarta Bean Validation (
anteriormente conhecida como Bean Validation 2.0).
Essa abordagem eficiente permite verificar se os dados inseridos atendem a padrões específicos, como formato de e-mail,
CPF, entre outros. O uso do @Validator com expressões regulares ajuda a manter a consistência dos
dados e reduzir erros ou entradas inválidas, oferecendo uma forma poderosa e flexível de validação de dados no projeto.

Para garantir a persistência de dados, foi implementada uma instância do PostgreSQL em um contêiner Docker,
proporcionando isolamento eficiente de responsabilidades, portabilidade, escalabilidade, facilidade de backup e
segurança, otimizando o desenvolvimento e a manutenção da aplicação.

Para garantir a segurança das APIs, adotamos o uso do JWT (JSON Web Token) como um mecanismo de geração de tokens. Isso
assegura autenticação única por usuário, com informações criptografadas no token, o que restringe o acesso somente a
usuários previamente registrados, tornando o sistema mais robusto contra ameaças de autenticação não autorizada.

Os relacionamentos definidos para esta API foram:

![img.png](watchwatt/src/main/resources/images/imgRelationships.png)

## Desafios

- Definir e compreender os relacionamentos entre usuários, eletrodomésticos e seus endereços.
- Incluir as regras de validações bem como seus regexs.
- Tratamento de exceções para possíveis erros durante o consumo das APIs.
- Definição da arquitetura do projeto (DDD/MVC/tecnologias e outros).
- Determinação das responsabilidades dos membros da equipe.
- Subir o bando de dados em container Docker.
- Realizar autenticação via JWT.

## Documentação Técnica

***

### Disclaimer

Documentação via SwaggerUI: [Link](http://localhost:8080/api/watchwatt/swagger-ui/index.html#)

Postman Collection: [Collection](watchwatt/src/main/resources/docs/Watch Watt.postman_collection.json)

![img.png](watchwatt/src/main/resources/images/imgSwagger.png)

Para a propriedade ``gender`` os valores possíveis são: ``MALE``, ``FEMALE`` ou ``OTHERS``.

Para a propriedade ``degree_kinship`` os valores possíveis
são: ``FATHER``, ``MOTHER``, ``SON``, ``DAUGHTER``, ``SISTER``, ``BROTHER``,
``HUSBAND``, ``WIFE`` ou ``OTHERS``.

Para as requisições que retornam uma lista com todos os itens é possível parametrizar as propriedades ``limit`` (número
de
limite retornados na consulta) e ``offset`` (qual página de registros a serem retornados) nos parâmetros da requisição.
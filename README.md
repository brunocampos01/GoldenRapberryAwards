# Golden Raspberry Awards
Aplicação **Spring Boot**, que processa dados de filmes (`movielist.csv`) e calcula os **produtores com o maior e o menor intervalo entre dois prêmios consecutivos**.

## Tecnologias Utilizadas
- Java 17+
- Spring Boot 3+
- Spring Data JPA
- H2 Database
- Maven
- JUnit

## Estrutura do Projeto
```
├── README.md
├── pom.xml
└── src
    ├── main
    │   ├── java
    │   │   └── com
    │   │       └── awards
    │   │           └── awards
    │   │               ├── AwardsApplication.java
    │   │               ├── config
    │   │               │   └── DataLoader.java
    │   │               ├── controller
    │   │               │   └── AwardController.java
    │   │               ├── dto
    │   │               │   ├── IntervalResponse.java
    │   │               │   └── ProducerInterval.java
    │   │               ├── model
    │   │               │   └── Movie.java
    │   │               ├── repository
    │   │               │   └── MovieRepository.java
    │   │               └── service
    │   │                   └── AwardService.java
    │   └── resources
    │       ├── application.properties
    │       ├── movielist.csv
    │       ├── static
    │       └── templates
    └── test
        └── java
            └── com
                └── awards
                    └── awards
                        ├── AwardsApplicationTests.java
                        └── integration
                            └── AwardIntegrationTest.java
```

## Configuração do Banco de Dados (H2)
O projeto usa um banco **H2 em memória**, criado automaticamente na inicialização. O arquivo `movielist.csv` é carregado pela classe `DataLoader.java` ao subir a aplicação.

**Console do H2 (opcional):**
Se quiser visualizar o banco em tempo real:
- Acesse: [http://localhost:8080/h2-console](http://localhost:8080/h2-console)
- JDBC URL: `jdbc:h2:mem:testdb`
- Usuário: `campos`
- Senha: *mantenha em branco*

---

## Como Executar o Projeto

### 1. Pré-requisitos
- Java 17+ instalado
- Git 
- Maven instalado

### 2. Clonar o repositório
```bash
git clone https://github.com/brunocampos01/GoldenRapberryAwards.git
cd GoldenRapberryAwards
```

### 3. Rodar a aplicação
```bash
mvn spring-boot:run
```

### 4. Acessar a API
GET [http://localhost:8080/awards/intervals](http://localhost:8080/awards/intervals)

## Como Rodar os Testes de Integração
Os testes de integração garantem que:
- O CSV é carregado corretamente no banco.
- A chamada GET para o endpoint `/awards/intervals` retorna um status HTTP 2xx de sucesso.
- O corpo da resposta (IntervalResponse) não é nulo.
- Os arrays min e max na resposta não estão vazios.
- O menor intervalo sendo um valor maior ou igual a 1.
- O maior intervalo (sendo um valor maior ou igual ao menor intervalo.

Execute os testes com:
```bash
mvn test
```

## Notes
- O banco de dados H2 é recriado a cada inicialização, garantindo isolamento nos testes.
- Todos os cálculos são baseados apenas nos filmes com campo winner = yes.
- Campos vazios no CSV são tratados como winner = no automaticamente.

---

<p  align="center">
	<br/>
	<a href="mailto:brunocampos01@gmail.com" target="_blank"><img src="https://github.com/brunocampos01/brunocampos01/blob/main/images/email.png" width="30">
	</a>
	<a href="https://stackoverflow.com/users/8329698/bruno-campos" target="_blank"><img src="https://github.com/brunocampos01/brunocampos01/blob/main/images/stackoverflow.png" width="30">
	</a>
	<a href="https://www.linkedin.com/in/brunocampos01" target="_blank"><img src="https://github.com/brunocampos01/brunocampos01/blob/main/images/linkedin.png" width="30">
	</a>
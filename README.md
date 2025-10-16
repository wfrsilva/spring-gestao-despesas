# spring-gestao-despesas
Spring Boot - Backend Gestao de Despesas - Daniele Leão


## CURSO DE SPRING BOOT | JAVA - Do Absoluto ZERO ao DEPLOY! - Daniele Leão #Java #Spring #SpringBoot

Neste vídeo te mostro como fazer seu primeiro sistema usando Spring boot, totalmente do zero até o deploy em produção!

https://www.youtube.com/watch?v=0V8OKTYNeU8


https://github.com/danileao/javadevweek




---


## Etapas do projeto
### Iniciar o projeto com dependencias
https://start.spring.io/

<img width="1534" height="778" alt="image" src="https://github.com/user-attachments/assets/9b6f0b5b-580b-40e6-9e21-05eaeabca445" />

https://start.spring.io/#!type=maven-project&language=java&platformVersion=3.5.6&packaging=jar&jvmVersion=17&groupId=dev.wfrsilva&artifactId=gestao-despesas&name=Gestao%20de%20Despesas%20Pessoais&description=Gestao%20de%20Despesas%20-%20Backend%20-%20Daniele%20Le%C3%A3o&packageName=dev.wfrsilva.gestao-despesas&dependencies=web,devtools,data-jpa,h2

### Importar arquivos do projeto na IDE
Arquivos baixados do [Spring Initializr]([https://start.spring.io/](https://start.spring.io/#!type=maven-project&language=java&platformVersion=3.5.6&packaging=jar&jvmVersion=17&groupId=dev.wfrsilva&artifactId=gestao-despesas&name=Gestao%20de%20Despesas%20Pessoais&description=Gestao%20de%20Despesas%20-%20Backend%20-%20Daniele%20Le%C3%A3o&packageName=dev.wfrsilva.gestao-despesas&dependencies=web,devtools,data-jpa,h2)) devem ser abertos na IDE.

[20:50 - Arrastar para VsCode](https://youtu.be/0V8OKTYNeU8?t=1250)


---


### H2 Console
Rodar o projeto e Abrir as configuraçoes do H2
[26:00 - Abrir H2 console](https://youtu.be/0V8OKTYNeU8?t=15600)

http://localhost:8080/h2-console/



---



### application.proprerties
`../src/main/resources/application.properties`
Só foi mostrado, ainda nao configurado. Será mais a frente.
[27:20 - application.proprerties](https://youtu.be/0V8OKTYNeU8?t=1640)


```properties
spring.application.name=Gestao de Despesas Pessoais
spring.application.
spring.application.
spring.application.
spring.application.
spring.application.
```


---



### Criando a primeira classe Controller
[38:15 - Primeira Controller](https://youtu.be/0V8OKTYNeU8?t=2295)

```java

  @RequestMapping("/javadevweek")
  @RestController
  public class PrimeiraController {

    @GetMapping("/helloword")
    public String helloWorld()
    {
      return "Olá Mundo";
    }//helloWorld
    
  }//PrimeiraController

```

#### Comando para rodar no WSL
wendel@wfrsilva.dev:~/cursosSpring/gestao-despesas$ `chmod +x mvnw`
wendel@wfrsilva.dev:~/cursosSpring/gestao-despesas$ `./mvnw` `spring-boot:run`
(...)



http://localhost:8080/javadevweek/helloworld

### Criando as pastas (pacotes)

[46:05 - Criar pacote controller](https://youtu.be/0V8OKTYNeU8?t=2765)

- Criar o pacote (pasta/diretorio) : `controller`
- Criar o pacote (pasta/diretorio) : `entity`
- Criar o pacote (pasta/diretorio) : `useCase`
- Criar o pacote (pasta/diretorio) : `repository`
- Criar o pacote (pasta/diretorio) : `custom_message` [01:45:55]
- Criar o pacote (pasta/diretorio) : `performance` [02:08:00]


---


### Criando GestaoDespesaController 
[46:55 Criar o GestaoDespesaController.java](https://youtu.be/0V8OKTYNeU8?t=2815)
- `../controller/GestaoDespesaController.java`
- [../danileao/../controller/GestaoDespesaController.java](https://github.com/danileao/javadevweek/blob/main/src/main/java/br/com/javadevweek/gestao_custos/controller/GestaoDespesaController.java)

```java

package dev.wfrsilva.gestao_despesas.controller;


@RequestMapping("/gestao")
@RestController
public class GestaoDespesaController(){

  @Autowired
  CadastroDespesaUseCase cadastroDespesaUseCase;

  @autowired
  BuscarDespesaUseCase buscarDespesaUseCase;


  @PostMapping("/create")
  public ResponseEntity<?> create(@RequestBody Despesa despesa)
  {



    try
    {
       var result =  cadastroDespesaUseCase.execute(despesa);
       return ResponseEntity.ok(result);
    }//try
    catch(IllegalArgumentException e)
    {
      var errorMessage = new ErrorMessage(e.getMessage(), "INVALID_PARAMS");
      return ResponseEntity.status(400).body(errorMessage);
    }//catch

  }//create


  @Getmapping("/{email}")
  public List<Despesa> findByEmailAndDate(@PathVariable String email, @RequestParam(required = false) LocalDate data)
  {
    System.out.println("Email: " + email);
    System.out.println("Data: " + data);

    return buscarDespesaUseCase.buscarPorEmailEData(email, data); 
   }//findByEmailAndDate



}//GestaoDespesasController

```


---


### Criando Despesa.java
[55:40 - Criar o Despesa.java](https://youtu.be/0V8OKTYNeU8?t=3340) 
- `../entity/Despesa.java`
- [../danileao/../entity/Despesa.java](https://github.com/danileao/javadevweek/blob/main/src/main/java/br/com/javadevweek/gestao_custos/entity/Despesa.java) 

```java

package dev.wfrsilva.gestao_despesas.entity;

@Entity
@Table(name="despesa")
public class Despesa{

  @Id
  @GeneratedValue(strategy = GenarationType.AUTO)
  private UUID id;

  @Column(nullable = false)
  private String descricao;

  @Column(nullable = false)
  private LocalDate data;

  @Column(nullable = false)
  private BigDecimal valor;

  @Column(length = 100, nullable = false)
  private String categoria;

  @Column(nullable = false)
  private String email;

  @CreationTimestamp
  private LocalDate data_criacao;
  
  //Criar GETs e SETs
  public UUID getId()
  {
    return id;
  }

public void setId(UUID id)
{
  this.id = id;
}

// Criar os demais set/get
//Criar um toString

}//Despesa

```


---


### Criando CadastroDespesaUseCase.java
[01:03:35 - Criar o CadastroDespesaUseCase.java](https://youtu.be/0V8OKTYNeU8?t=3815) 
- `../useCase/CadastroDespesaUseCase.java`
- [../danileao/../useCases/CadastroDespesaUseCase.java](https://github.com/danileao/javadevweek/blob/main/src/main/java/br/com/javadevweek/gestao_custos/useCases/CadastroDespesaUseCase.java)

```java

package dev.wfrsilva.gestao_despesas.useCase;


@Service
public class CadastroDespesaUseCase{
//controller recebe informacao e manda para o useCase

  @Autowired
  private DespesaRepository despesaRepository;
  
  public Despesa execute(Despesa despesa)
  {
    //System.out.println("Categoria " + despesa.getCategoria());
    //System.out.println("E-mail " + despesa.getEmail());
    //System.out.println(despesa);
  
    System.out.println("===== ANTES DE SALVAR =====");
    System.out.println(despesa);

    if(despesa.getCategoria() == null || despesa.getData == null || despesa.getDescricao() == null || despesa.getEmail() == null)
    {
      throw new IllegalArgumentException("Preencher todos os campos");
    }
    
    despesa = despesaRepository.save(despesa);

    System.out.println("===== DEPOIS DE SALVAR =====");
    System.out.println(despesa);

    return despesa;
      
  }//execute







}//CadastroDespesaUseCase

```

### Fazer um `request post` para testar.
[01:08:40 - post no Thunder Client](https://youtu.be/0V8OKTYNeU8?t=4120)

**NAO vai funcionar** - BuscarDespesaUseCase ainda nao foi criado!

ThunderClient ou outro (arquivos `post.http`).

POST > http://localhost:8080/gestao/create
Body 
JSON Content
```json
{
  "descricao": "Almoço de Segunda",
  "valor":45,
  "categoria":"refeição",
  "email":"wfrsilva@gmail.com",
  "data":"2025-06-09"
}
```


Status 200 OK
---

### Salvar no Banco de Dados - application.proprerties
[01:19:30 - Configurar Banco de Dados H2](https://youtu.be/0V8OKTYNeU8?t=4770)

```properties
spring.application.name=Gestao de Despesas Pessoais

# ===== CONFIGURACOES H2 ======
spring.datasource.url=jdbc:h2:file:./data/gestao-despesa
spring.datasource.driveClassName=org.h2.Driver
spring.datasource.username=sa
spring.datasource.password=
spring.jpa.database-platform=org.hibernate.dialect.H2Dialect


```
Ao salvar o `application.proprerties`, verificar se criou /data/gestao-despesa.mv.db
<img width="277" height="101" alt="image" src="https://github.com/user-attachments/assets/3f52e8c5-8b41-414f-b4d2-7e6a48c858e4" />

### Abrir H2-console

http://localhost:8080/h2-console/

JDBC URL: `jdbc:h2:file:./data/gestao-despesa`

User Name: `sa`

Password: ` `

<img width="829" height="668" alt="image" src="https://github.com/user-attachments/assets/70923154-81b0-4319-8a15-cc3639e22d97" />


### Spring Data para Tabelas no H2
[01:25:40 - Spring Data para criar as Tabelas no H2](https://youtu.be/0V8OKTYNeU8?t=5140)

```properties
(...)
spring.jpa.database-platform=org.hibernate.dialect.H2Dialect
spring.jpa.hibernate.dll-auto=update

```

---
### Criando DespesaRepository.java
[01:18:10 - Criar DespesaRepository.java](https://youtu.be/0V8OKTYNeU8?t=5292)
- `../repository/DespesaRepository.java`
- [../danileao/../repository/DespesaRepository.java](https://github.com/danileao/javadevweek/blob/main/src/main/java/br/com/javadevweek/gestao_custos/repository/DespesaRepository.java)

```java

package dev.wfrsilva.gestao_despesas.repository;


public interface DespesaRepository extends JpaRepository<Despesa, UUID>{

  List<Despesa> findByEmail(String email);

  List<Despesa> findByEmailAndData(String email, LocalDate data);


}//DespesaRepository


```

---

### Criando ErrorMessage.java
[01:46:00 - Criar ErrorMessage.java](https://youtu.be/0V8OKTYNeU8?t=6360)
- `../custom_message/ErrorMessage.java`
- [../danileao/../custom_messages/ErrorMessage.java](https://github.com/danileao/javadevweek/blob/main/src/main/java/br/com/javadevweek/gestao_custos/custom_messages/ErrorMessage.java)

```java

package dev.wfrsilva.gestao_despesas.custom_message;

public class ErrorMessage {

  private String message;
  private String type;

  public ErrorMessage(String message, String type)
  {
    this.messge = message;
    this.type = type;
  }// contructor allargs

  public String getMessage()
  {
    return message;
  }//getMessage

  public void setMessage(String message)
  {
    this.message = message;
  }//setMessage

  public String getType()
  {
    return type;
  }//getType

  public void setType(String type)
  {
    this.type = type;
  }

}//ErrorMessage




```

---





### Criando Busca
[01:48:40 - Criar Busca](https://youtu.be/0V8OKTYNeU8?t=6520)

Apos configurar a classe [GestaoDespesaController.java](https://github.com/wfrsilva/spring-gestao-despesas/edit/main/README.md#criando-gestaodespesacontroller), rodar o get:
ThunderClient ou outro (arquivos get.http).

GET > http://localhost:8080/gestao/wfrsilva@gmail.com

---







### Criando BuscarDespesaUseCase.java
[01:53:35 - Criar BuscarDespesaUseCase.java](https://youtu.be/0V8OKTYNeU8?t=6815)
- `../useCase/BuscarDespesaUseCase.java`
- [../danileao/../useCases/BuscarDespesaUseCase.java](https://github.com/danileao/javadevweek/blob/main/src/main/java/br/com/javadevweek/gestao_custos/useCases/BuscarDespesaUseCase.java)

```java

package dev.wfrsilva.gestao_despesas.useCase;

@Service
public class BuscarDespesaUseCase {

  @Autowired
  private DespesaRepository despesaRepository;

  public List<Despesa> execute(String email, LocalDate data)
  {
    List<Despesa> despesas;

    if(data != null)
    {
      despesas = despesaRepository.findByEmailAndData(email, data);
    }//if
    else
    {
      despesas = despesaRepository.findByEmail(email);
    }//else

  }//buscarPorEmailEData

  return despesas;
  
}//BuscarDespesaUseCase

```

- 
---

---
---








# [2025-10-08 Paramos 01:18:40](https://youtu.be/0V8OKTYNeU8?t=4720)

# [2025-10-09 Paramos 01:48:10](https://youtu.be/0V8OKTYNeU8?t=6490)

# [2025-10-10 Paramos 01:58:00](https://youtu.be/0V8OKTYNeU8?t=7080)
  Inivavel ficar alterando so no MD.
  Comecar a codar de verdade.


  ---
  ---

# 2025-10-16 - Corrigidos alguns javas

Post executando corretamente.

post.http

```
POST http://localhost:8080/gestao/create
Content-Type: application/json

{
  "descricao": "Almoço de Segunda",
  "valor":45,
  "categoria":"refeição",
  "email":"wfrsilva@gmail.com",
  "data":"2025-06-09"
}


###

POST http://localhost:8080/gestao/create
Content-Type: application/json

{
  "descricao": "Almoço de Terça",
  "valor":30,
  "categoria":"refeição",
  "email":"wfrsilva@gmail.com",
  "data":"2025-06-10"
}

###

POST http://localhost:8080/gestao/create
Content-Type: application/json

{
  "descricao": "Cafe de Quarta",
  "valor":15,
  "categoria":"refeição",
  "email":"wfrsilva@gmail.com",
  "data":"2025-06-11"
}


### verificar se post OK

GET http://localhost:8080/gestao/wfrsilva@gmail.com

```
<img width="690" height="400" alt="image" src="https://github.com/user-attachments/assets/f6164e7e-82fb-46aa-b741-4c362962d525" />



```
HTTP/1.1 200 
Content-Type: application/json
Transfer-Encoding: chunked
Date: Thu, 16 Oct 2025 03:08:08 GMT
Connection: close

{
  "id": "c03fde93-8003-4246-ac81-1f280bb76aa1",
  "descricao": "Cafe de Quarta",
  "data": "2025-06-11",
  "valor": 15,
  "categoria": "refeição",
  "email": "wfrsilva@gmail.com",
  "data_criacao": "2025-10-16"
}
```
<img width="594" height="733" alt="image" src="https://github.com/user-attachments/assets/a8bc7f1b-0315-4f11-bad6-fbb9610250b7" />


http://localhost:8080/h2-console/login.do?jsessionid=5c0904737795fb57c05c59046d35ca39

JDBC URL: `jdbc:h2:file:./data/gestao-despesa`

User Name: `sa`

Password: ` `



`SELECT * FROM DESPESA;`

| ID                                   | CATEGORIA | DATA       | DATA_CRIACAO | DESCRICAO         | EMAIL              | VALOR |
|--------------------------------------|------------|-------------|---------------|-------------------|--------------------|-------|
| 4fe08db3-dc76-4cd9-b8d9-373b71608620 | refeição   | 2025-06-09  | 2025-10-15    | Almoço de Segunda | wfrsilva@gmail.com | 45.00 |
| 9056c7cf-76ce-415f-9c3d-ee6fa0c60f1a | refeição   | 2025-06-10  | 2025-10-15    | Almoço de Terça   | wfrsilva@gmail.com | 30.00 |
| c03fde93-8003-4246-ac81-1f280bb76aa1 | refeição   | 2025-06-11  | 2025-10-16    | Cafe de Quarta    | wfrsilva@gmail.com | 15.00 |


(3 rows, 1 ms)

<img width="1079" height="495" alt="image" src="https://github.com/user-attachments/assets/82661efc-d14f-4a1f-bd99-3299743f1fd6" />


# GET http://localhost:8080/gestao/wfrsilva@gmail.com

`GET http://localhost:8080/gestao/wfrsilva@gmail.com`

<img width="1842" height="950" alt="image" src="https://github.com/user-attachments/assets/0db8321b-06c2-4a26-9074-81cf53be8aaa" />

```
HTTP/1.1 200 
Content-Disposition: inline;filename=f.txt
Content-Type: application/json
Transfer-Encoding: chunked
Date: Thu, 16 Oct 2025 20:22:45 GMT
Connection: close

[
  {
    "id": "4fe08db3-dc76-4cd9-b8d9-373b71608620",
    "descricao": "Almoço de Segunda",
    "data": "2025-06-09",
    "valor": 45.00,
    "categoria": "refeição",
    "email": "wfrsilva@gmail.com",
    "data_criacao": "2025-10-15"
  },
  {
    "id": "9056c7cf-76ce-415f-9c3d-ee6fa0c60f1a",
    "descricao": "Almoço de Terça",
    "data": "2025-06-10",
    "valor": 30.00,
    "categoria": "refeição",
    "email": "wfrsilva@gmail.com",
    "data_criacao": "2025-10-15"
  },
  {
    "id": "c03fde93-8003-4246-ac81-1f280bb76aa1",
    "descricao": "Cafe de Quarta",
    "data": "2025-06-11",
    "valor": 15.00,
    "categoria": "refeição",
    "email": "wfrsilva@gmail.com",
    "data_criacao": "2025-10-16"
  }
]

```


`GET http://localhost:8080/gestao/wfrsilva@gmail.com?data=2025-06-09`

<img width="1844" height="899" alt="image" src="https://github.com/user-attachments/assets/4f46fcff-aeaf-4db8-aa31-a453b3e066af" />


```
HTTP/1.1 200 
Content-Disposition: inline;filename=f.txt
Content-Type: application/json
Transfer-Encoding: chunked
Date: Thu, 16 Oct 2025 20:24:48 GMT
Connection: close

[
  {
    "id": "4fe08db3-dc76-4cd9-b8d9-373b71608620",
    "descricao": "Almoço de Segunda",
    "data": "2025-06-09",
    "valor": 45.00,
    "categoria": "refeição",
    "email": "wfrsilva@gmail.com",
    "data_criacao": "2025-10-15"
  }
]
```

---



# Melhoria de performance

[02:07:30:15 - Melhorando a performance](https://youtu.be/0V8OKTYNeU8?t=7650)

[02:08:30 - Criar o GestaoDeDespesaSeeder.java](https://youtu.be/0V8OKTYNeU8?t=7710) 
- `../performance/GestaoDeDespesaSeeder.java`
- [../danileao/../performance/GestaoDeDespesaSeeder.java](https://github.com/danileao/javadevweek/blob/main/src/main/java/br/com/javadevweek/gestao_custos/performance/GestaoDeDespesaSeeder.java)

<img width="1914" height="486" alt="image" src="https://github.com/user-attachments/assets/0c1651c2-3edb-48e0-8e39-089010e3746b" />



```
lable at 'jdbc:h2:file:./data/gestao-despesa'
2025-10-16T18:14:58.831-03:00  INFO 6695 --- [Gestao de Despesas Pessoais] [  restartedMain] o.s.b.d.a.OptionalLiveReloadServer       : LiveReload server is running on port 35729
2025-10-16T18:14:58.838-03:00  INFO 6695 --- [Gestao de Despesas Pessoais] [  restartedMain] o.s.b.w.embedded.tomcat.TomcatWebServer  : Tomcat started on port 8080 (http) with context path '/'
2025-10-16T18:14:58.843-03:00  INFO 6695 --- [Gestao de Despesas Pessoais] [  restartedMain] .w.g.GestaoDeDespesasPessoaisApplication : Started GestaoDeDespesasPessoaisApplication in 0.386 seconds (process running for 6339.843)
Rodando junco com a Aplicação
2025-10-16T18:14:58.845-03:00  INFO 6695 --- [Gestao de Despesas Pessoais] [  restartedMain] .ConditionEvaluationDeltaLoggingListener : Condition evaluation unchanged
```



<img width="1904" height="359" alt="image" src="https://github.com/user-attachments/assets/c1cb7305-df5a-4d9f-8777-29d20bd15d69" />


```
2025-10-16T19:29:05.934-03:00  INFO 6695 --- [Gestao de Despesas Pessoais] [  restartedMain] .w.g.GestaoDeDespesasPessoaisApplication : Started GestaoDeDespesasPessoaisApplication in 0.409 seconds (process running for 10625.657)
Iniciando seed
Finalizou seed
2025-10-16T19:29:16.348-03:00  INFO 6695 --- [Gestao de Despesas Pessoais] [  restartedMain] .ConditionEvaluationDeltaLoggingListener : Condition evaluation unchanged
```

http://localhost:8080/h2-console/login.do?jsessionid=7fec743b935a402b1cc4257223b43216

SELECT count(*) FROM DESPESA 

<img width="718" height="413" alt="image" src="https://github.com/user-attachments/assets/285caf60-6eea-47d7-ab78-0655240e857d" />


| COUNT(*) |
|----------|
| 150004 |
(1 row, 4 ms)


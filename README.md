# spring-gestao-despesas
Spring Boot - Backend Gestao de Despesas - Daniele Leão


## CURSO DE SPRING BOOT | JAVA - Do Absoluto ZERO ao DEPLOY! - Daniele Leão #Java #Spring #SpringBoot

Neste vídeo te mostro como fazer seu primeiro sistema usando Spring boot, totalmente do zero até o deploy em produção!

https://www.youtube.com/watch?v=0V8OKTYNeU8


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


---


### Criando GestaoDespesaController 
[46:55 Criar o GestaoDespesaController.java](https://youtu.be/0V8OKTYNeU8?t=2815)
- `../controller/GestaoDespesaController.java`


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

```java

package dev.wfrsilva.gestao_despesas.useCase;


@Service
public class CadastroDespesaUseCase{
//controller recebe informacao e manda para o useCase

  @Autowired
  private DespesaRepository despesaRepository;
  
  public void execute(Despesa despesa)
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
Password: ``

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
- `../useCase/BuscarDespesaUseCase.java.java`

```java

package dev.wfrsilva.gestao_despesas.useCase;

@Service
public class BuscarDespesaUseCase {

  @Autowired
  private DespesaRepository despesaRepository;

  public List<Despesa> buscarPorEmailEData(String email, LocalDate data)
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

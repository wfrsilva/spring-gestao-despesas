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

http://localhot:8080/javadevweek/helloworld

### Criando as pastas (pacotes)

[46:05 - Criar pacote controller](https://youtu.be/0V8OKTYNeU8?t=2765)

- Criar o pacote (pasta/diretorio) : controller
- Criar o pacote (pasta/diretorio) : entity
- Criar o pacote (pasta/diretorio) : useCase


---


### Criando GestaoDespesaController 
[46:55 Criar o GestaoDespesaController.java](https://youtu.be/0V8OKTYNeU8?t=2815)
- ../controller/GestaoDespesaController.java


```java

@RequestMapping("/gestao")
@RestController
public class GestaoDespesaController(){

  @Autowired
  CadastroDespesaUseCase cadastroDespesaUseCase;

  @PostMapping("/create")
  public void create(@RequestBody Despesa despesa)
  {
     cadastroDespesaUseCase.execute(despesa);
  }//create


}//GestaoDespesasController

```


---


### Criando Despesa.java
[55:40 - Criar o Despesa.java](https://youtu.be/0V8OKTYNeU8?t=3340) 
- ../entity/Despesa.java

```java

@Entity
@table(name="despesa")
public class Despesa{

  @Id
  @GeneratedValue(strategy = GenarationType.AUTO)
  private UUID id;

  private String descricao;
  private LocalDate data;
  private BigaDEcimal valor;
  private String categoria;
  private String email;

  @CreatDate
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


### Criando CadastrDespesaUseCase.java
[01:03:35 - Criar o CadastrDespesaUseCase.java](https://youtu.be/0V8OKTYNeU8?t=3815) 
- ../useCase/Despesa.java

```java

@Service
public class CadastroDespesaUseCase{
//controller recebe informacao e manda para o useCase

  public void execute(Despesa despesa)
  {
    System.out.println("Categoria " + despesa.getCategoria());
    System.out.println("E-mail " + despesa.getEmail());
  System.out.println(despesa);
  }//execute







}//CadastroDespesaUseCase

```

### Fazer um ``request post`` para testar.
[01:08:40 - post no Thunder Client](https://youtu.be/0V8OKTYNeU8?t=4120)

ThunderClient ou outro (arquivos post.http).

POST > http://localhost:8080/gestao/create
Body 
JSON Content
```json
{
  "descricao": "Almoço de Segunda",
  "valor":45,
  "categoria':"refeição",
  "email":"wfrsilva@gmail.com",
  "data":"2025-06-09"
}
```


Status 200 OK
---

# [2025-10-08 Paramos 01:18:40](https://youtu.be/0V8OKTYNeU8?t=4720)


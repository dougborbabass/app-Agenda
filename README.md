# app-Agenda

### Aplicativo implementa um CRUD utilizando a lib do Android Jetpack - ROOM

Criado relacionamento 1:n afins de estudo utilizando chave estrangeira para explorar os recursos da lib.

- RoomDatabase-> Banco de Dados
- Entity -> Tabelas do Banco de Dados
- DAO (Data Access Objects) -> Objetos para acessar e consultar as informações do Banco de Dados.

Formulário que permite adicionar alunos e adicionar infos (nome, sobrenome, telefones e email).


### Exemplo do código da entidade com relacionamento utilizado:
Aluno.class com relacionamento 1:n
```java
@Entity
public class Aluno implements Serializable {

    @PrimaryKey(autoGenerate = true)
    private int id = 0;
    private String nome;
    private String sobrenome;
    private String email;
    private Calendar momentoDeCadastro = Calendar.getInstance();
```

Telefone.class com relacionamento n:1
```java
@Entity
public class Telefone {

    @PrimaryKey(autoGenerate = true)
    private int id;
    private String numero;
    private TipoTelefone tipo;
    @ForeignKey(entity = Aluno.class,
            parentColumns = "id",
            childColumns = "alunoId",
            onUpdate = CASCADE,
            onDelete = CASCADE)
    private int alunoId;
```

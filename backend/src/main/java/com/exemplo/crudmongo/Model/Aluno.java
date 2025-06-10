// Define o pacote onde esta classe está localizada. Pacotes são usados para organizar as classes do projeto.
package com.exemplo.crudmongo.Model;

// Importações de bibliotecas externas que adicionam funcionalidades à nossa classe.
import lombok.Data; // Importa a anotação @Data da biblioteca Lombok. Ela gera automaticamente os métodos getters, setters, toString, equals e hashCode, o que economiza muito código.
import org.springframework.data.annotation.Id; // Importa a anotação @Id do Spring Data, usada para marcar o campo que será o identificador único do documento no MongoDB.
import org.springframework.data.mongodb.core.mapping.Document; // Importa a anotação @Document, que mapeia esta classe Java para uma coleção no MongoDB.

/**
 * A anotação @Data do Lombok gera automaticamente em tempo de compilação:
 * - Getters para todos os campos (ex: getId(), getNome())
 * - Setters para todos os campos (ex: setId(String id), setNome(String nome))
 * - Um método toString() útil para visualização dos dados do objeto.
 * - Métodos equals() e hashCode() para comparações de objetos.
 * - Um construtor que recebe todos os campos final.
 */
@Data
/**
 * A anotação @Document informa ao Spring Data MongoDB que esta classe representa um documento
 * que será armazenado em uma coleção do banco de dados.
 * "collection = "alunos"" especifica que o nome da coleção no MongoDB será "alunos".
 */
@Document(collection = "alunos")
public class Aluno {

    /**
     * A anotação @Id marca este campo como a chave primária do documento.
     * No MongoDB, a chave primária padrão se chama "_id". Esta anotação faz o mapeamento
     * entre o campo 'id' da nossa classe Java e o campo '_id' do documento no banco.
     */
    @Id
    private String id; // Campo para armazenar o ID único do aluno.

    // Campo para armazenar o nome do aluno.
    private String nome;

    // Campo para armazenar o Índice de Rendimento Acadêmico (IRA) do aluno.
    // Usamos Double para permitir valores com casas decimais.
    private Double ira;

    // Campo para armazenar o ID do curso ao qual o aluno está vinculado.
    // Este campo pode ser usado para criar um relacionamento com a coleção de Cursos.
    private String cursoId;

    // Campo para armazenar o ID do período de ingresso do aluno (ex: "2024.1").
    private String periodoIngressoId;

    // Não é necessário escrever manualmente os métodos getters e setters aqui
    // porque a anotação @Data do Lombok já faz isso por nós.
}
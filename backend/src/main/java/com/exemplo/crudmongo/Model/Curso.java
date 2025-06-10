// Define o pacote onde esta classe está localizada. Pacotes são usados para organizar as classes do projeto de forma lógica.
package com.exemplo.crudmongo.Model;

// Importações de bibliotecas externas que adicionam funcionalidades à nossa classe.
import lombok.Data; // Importa a anotação @Data da biblioteca Lombok.
import org.springframework.data.annotation.Id; // Importa a anotação @Id do Spring Data para marcar a chave primária.
import org.springframework.data.mongodb.core.mapping.Document; // Importa a anotação @Document para mapear a classe a uma coleção do MongoDB.

/**
 * A anotação @Data do Lombok é uma forma poderosa de reduzir código repetitivo (boilerplate).
 * Ela gera automaticamente em tempo de compilação:
 * - Getters para todos os campos (ex: getId(), getNome()).
 * - Setters para todos os campos (ex: setId(String id), setNome(String nome)).
 * - Um método toString() útil para facilitar a visualização dos dados do objeto.
 * - Métodos equals() e hashCode() para permitir comparações adequadas entre objetos.
 */
@Data
/**
 * A anotação @Document informa ao Spring Data MongoDB que esta classe representa um documento
 * que será armazenado em uma coleção do banco de dados.
 * "collection = "cursos"" especifica que o nome da coleção no MongoDB será "cursos".
 * Cada instância da classe Curso corresponderá a um documento nesta coleção.
 */
@Document(collection = "cursos")
public class Curso {

    /**
     * A anotação @Id marca este campo como a chave primária (identificador único) do documento.
     * No MongoDB, a chave primária padrão se chama "_id". Esta anotação faz o mapeamento
     * entre o campo 'id' da nossa classe Java e o campo '_id' do documento no banco.
     */
    @Id
    private String id; // Campo para armazenar o ID único do curso.

    // Campo para armazenar o nome do curso (ex: "Ciência da Computação").
    private String nome;

    // Campo para armazenar o nível do curso (ex: "Graduação", "Pós-graduação", "Técnico").
    private String nivel;

    // Campo para armazenar a modalidade do curso (ex: "Presencial", "EaD", "Híbrido").
    private String modalidade;

    // Campo para armazenar o turno em que o curso é oferecido (ex: "Matutino", "Vespertino", "Noturno", "Integral").
    private String turno;

    // Observação: Não é necessário escrever manualmente os métodos getters e setters aqui
    // porque a anotação @Data do Lombok já faz todo esse trabalho por nós.
}
// Define o pacote onde esta classe está localizada. Pacotes são usados para organizar as classes do projeto.
package com.exemplo.crudmongo.Model;

// Importações de bibliotecas externas que adicionam funcionalidades à nossa classe.
import lombok.Data; // Importa a anotação @Data da biblioteca Lombok para gerar código repetitivo automaticamente.
import org.springframework.data.annotation.Id; // Importa a anotação @Id do Spring Data para marcar a chave primária.
import org.springframework.data.mongodb.core.mapping.Document; // Importa a anotação @Document para mapear a classe a uma coleção do MongoDB.

/**
 * A anotação @Data do Lombok é uma forma conveniente de adicionar funcionalidades comuns a uma classe.
 * Ela gera automaticamente em tempo de compilação:
 * - Getters para todos os campos (ex: getId(), getProfessor()).
 * - Setters para todos os campos (ex: setId(String id), setProfessor(String professor)).
 * - Um método toString() que ajuda a visualizar os dados do objeto de forma legível.
 * - Métodos equals() e hashCode() para permitir comparações entre objetos.
 */
@Data
/**
 * A anotação @Document informa ao Spring Data MongoDB que esta classe representa um documento
 * que será armazenado em uma coleção do banco de dados.
 * "collection = "turmas"" especifica que o nome da coleção no MongoDB será "turmas".
 */
@Document(collection = "turmas")
public class Turma {

    /**
     * A anotação @Id marca este campo como a chave primária (identificador único) do documento.
     * No MongoDB, a chave primária padrão se chama "_id". Esta anotação faz o mapeamento
     * entre o campo 'id' da nossa classe Java e o campo '_id' do documento no banco.
     */
    @Id
    private String id; // Campo para armazenar o ID único da turma.

    // Campo para armazenar o ID da disciplina à qual esta turma está associada.
    // Pode ser usado para criar um relacionamento com a coleção de Disciplinas.
    private String disciplinaId;

    // Campo para armazenar o ano em que a turma foi ofertada.
    private Integer ano;

    // Campo para armazenar o semestre em que a turma foi ofertada (ex: 1 ou 2).
    private Integer semestre;

    // Campo para armazenar o nome ou o ID do professor responsável pela turma.
    private String professor;

    // Observação: Não é necessário escrever manualmente os métodos getters e setters aqui
    // porque a anotação @Data do Lombok já cuida disso para nós.
}
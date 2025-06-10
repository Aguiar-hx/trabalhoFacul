// Define o pacote onde esta classe está localizada. Pacotes são usados para organizar as classes do projeto.
package com.exemplo.crudmongo.Model;

// Importações de bibliotecas externas que adicionam funcionalidades à nossa classe.
import lombok.Data; // Importa a anotação @Data da biblioteca Lombok para gerar código repetitivo automaticamente.
import org.springframework.data.annotation.Id; // Importa a anotação @Id do Spring Data para marcar a chave primária.
import org.springframework.data.mongodb.core.mapping.Document; // Importa a anotação @Document para mapear a classe a uma coleção do MongoDB.

import java.util.List; // Importa a interface 'List' para trabalhar com listas de objetos, neste caso, listas de Strings.

/**
 * A anotação @Data do Lombok é uma forma conveniente de adicionar funcionalidades comuns a uma classe.
 * Ela gera automaticamente em tempo de compilação:
 * - Getters para todos os campos (ex: getId(), getCursoId()).
 * - Setters para todos os campos (ex: setId(String id), setCursoId(String cursoId)).
 * - Um método toString() que ajuda a visualizar os dados do objeto de forma legível.
 * - Métodos equals() e hashCode() para permitir comparações entre objetos.
 */
@Data
/**
 * A anotação @Document informa ao Spring Data MongoDB que esta classe representa um documento
 * que será armazenado em uma coleção do banco de dados.
 * "collection = "curriculos"" especifica que o nome da coleção no MongoDB será "curriculos".
 */
@Document(collection = "curriculos")
public class Curriculo {

    /**
     * A anotação @Id marca este campo como a chave primária (identificador único) do documento.
     * No MongoDB, a chave primária padrão se chama "_id". Esta anotação faz o mapeamento
     * entre o campo 'id' da nossa classe Java e o campo '_id' do documento no banco.
     */
    @Id
    private String id; // Campo para armazenar o ID único do currículo.

    // Campo para armazenar o ID do curso ao qual este currículo pertence.
    // Pode ser usado para criar um relacionamento com a coleção de Cursos.
    private String cursoId;

    // Campo para armazenar o ano de vigência do currículo.
    private Integer ano;

    // Campo para armazenar o semestre de vigência do currículo (ex: 1 ou 2).
    private Integer semestre;

    // Campo que armazena uma lista de Strings. Cada String é o ID de uma disciplina obrigatória.
    private List<String> disciplinasObrigatorias;

    // Campo que armazena uma lista de Strings. Cada String é o ID de uma disciplina optativa.
    private List<String> disciplinasOptativas;

    // Observação: Não é necessário escrever manualmente os métodos getters, setters, etc.,
    // porque a anotação @Data do Lombok já cuida disso para nós.
}
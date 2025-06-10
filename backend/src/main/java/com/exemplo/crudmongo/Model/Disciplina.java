// Define o pacote onde esta classe está localizada. Pacotes são usados para organizar as classes do projeto.
package com.exemplo.crudmongo.Model;

// Importações de bibliotecas externas que adicionam funcionalidades à nossa classe.
import lombok.Data; // Importa a anotação @Data da biblioteca Lombok para gerar código repetitivo automaticamente.
import org.springframework.data.annotation.Id; // Importa a anotação @Id do Spring Data para marcar a chave primária.
import org.springframework.data.mongodb.core.mapping.Document; // Importa a anotação @Document para mapear a classe a uma coleção do MongoDB.

/**
 * A anotação @Data do Lombok é uma forma conveniente de adicionar funcionalidades comuns a uma classe.
 * Ela gera automaticamente em tempo de compilação:
 * - Getters para todos os campos (ex: getId(), getNome()).
 * - Setters para todos os campos (ex: setId(String id), setNome(String nome)).
 * - Um método toString() que ajuda a visualizar os dados do objeto de forma legível.
 * - Métodos equals() e hashCode() para permitir comparações entre objetos.
 */
@Data
/**
 * A anotação @Document informa ao Spring Data MongoDB que esta classe representa um documento
 * que será armazenado em uma coleção do banco de dados.
 * "collection = "disciplinas"" especifica que o nome da coleção no MongoDB será "disciplinas".
 */
@Document(collection = "disciplinas")
public class Disciplina {

    /**
     * A anotação @Id marca este campo como a chave primária (identificador único) do documento.
     * No MongoDB, a chave primária padrão se chama "_id". Esta anotação faz o mapeamento
     * entre o campo 'id' da nossa classe Java e o campo '_id' do documento no banco.
     */
    @Id
    private String id; // Campo para armazenar o ID único da disciplina.

    // Campo para armazenar o nome da disciplina (ex: "Cálculo I", "Algoritmos").
    private String nome;

    // Campo para armazenar a carga horária total da disciplina, em horas.
    private Integer cargaHoraria;

    // Campo para armazenar a ementa da disciplina, que é a descrição do conteúdo que será abordado.
    private String ementa;

    // Observação: Não é necessário escrever manualmente os métodos getters e setters aqui
    // porque a anotação @Data do Lombok já cuida disso para nós.
}
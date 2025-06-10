// Define o pacote onde esta interface está localizada. Repositórios são responsáveis pela comunicação com o banco de dados.
package com.exemplo.crudmongo.repository;

// Importações de classes e anotações necessárias.
import com.exemplo.crudmongo.Model.Curso; // Importa o modelo de dados 'Curso' para que o repositório saiba com qual tipo de entidade ele está trabalhando.
import org.springframework.data.mongodb.repository.MongoRepository; // Importa a interface principal do Spring Data MongoDB.
import org.springframework.stereotype.Repository; // Importa a anotação @Repository, que marca a interface como um componente de persistência do Spring.

/**
 * A anotação @Repository indica ao Spring que esta interface é um "Repositório",
 * um componente responsável pelo acesso e manipulação de dados.
 * Embora seja opcional para interfaces que estendem MongoRepository (pois o Spring já as detecta),
 * é uma boa prática para clareza e para permitir a tradução de exceções específicas do banco de dados.
 */
@Repository
/**
 * Esta é a interface do Repositório para a entidade Curso.
 * Ao estender 'MongoRepository', esta interface herda automaticamente uma grande variedade
 * de métodos para realizar operações CRUD (Criar, Ler, Atualizar, Deletar) no banco de dados.
 * Não precisamos escrever a implementação desses métodos; o Spring Data MongoDB faz isso por nós em tempo de execução.
 *
 * @param <Curso> O tipo da entidade que este repositório gerencia.
 * @param <String> O tipo do campo ID da entidade (o campo anotado com @Id na classe Curso).
 */
public interface CursoRepository extends MongoRepository<Curso, String> {

    // A interface está vazia, mas já é extremamente poderosa!
    // Métodos herdados do MongoRepository incluem:
    // - save(Curso curso): Salva ou atualiza um curso.
    // - findById(String id): Busca um curso pelo seu ID.
    // - findAll(): Retorna uma lista de todos os cursos.
    // - deleteById(String id): Deleta um curso pelo seu ID.
    // - e muitos outros...

    // Você pode adicionar métodos de consulta customizados aqui se precisar no futuro.
    // O Spring Data MongoDB criará a consulta automaticamente com base no nome do método.
    // Exemplos:
    // List<Curso> findByNome(String nome); // Encontraria todos os cursos com um nome específico.
    // List<Curso> findByNivel(String nivel); // Encontraria todos os cursos de um determinado nível.
}
// Define o pacote onde esta classe está localizada. A camada de Serviço é responsável pela lógica de negócios da aplicação.
package com.exemplo.crudmongo.service;

// Importações de classes e anotações necessárias.
import com.exemplo.crudmongo.Model.Curso; // Importa o modelo de dados 'Curso'.
import com.exemplo.crudmongo.repository.CursoRepository; // Importa o repositório que faz a comunicação com o banco de dados para a entidade Curso.
import org.springframework.stereotype.Service; // Importa a anotação @Service, que marca a classe como um componente de serviço do Spring.

import java.util.List; // Importa a interface para trabalhar com listas.
import java.util.Optional; // Importa a classe Optional, que é uma forma elegante de lidar com valores que podem ser nulos, evitando NullPointerException.

/**
 * A anotação @Service indica ao Spring que esta classe é um "Serviço".
 * Ela contém a lógica de negócios da aplicação e atua como um intermediário
 * entre a camada de Controller (que lida com requisições web) e a camada de Repository (que lida com o banco de dados).
 */
@Service
public class CursoService {

    // Declaração final do repositório de curso. 'final' significa que ele deve ser inicializado no construtor.
    private final CursoRepository cursoRepository;

    /**
     * Construtor da classe CursoService.
     * O Spring utiliza este construtor para realizar a "Injeção de Dependência".
     * Ele automaticamente cria uma instância de CursoRepository e a "injeta" aqui,
     * permitindo que o serviço utilize os métodos do repositório para acessar o banco de dados.
     * @param cursoRepository A instância de CursoRepository fornecida pelo Spring.
     */
    public CursoService(CursoRepository cursoRepository) {
        this.cursoRepository = cursoRepository;
    }

    /**
     * Método para listar todos os cursos.
     * @return Uma lista com todos os cursos encontrados no banco de dados.
     */
    public List<Curso> listarTodos() {
        // Chama o método findAll() do repositório, que o Spring Data MongoDB implementa automaticamente.
        return cursoRepository.findAll();
    }

    /**
     * Método para buscar um único curso pelo seu ID.
     * @param id O ID do curso a ser buscado.
     * @return Um Optional contendo o Curso se ele for encontrado, ou um Optional vazio caso contrário.
     */
    public Optional<Curso> buscarPorId(String id) {
        // Chama o método findById() do repositório.
        return cursoRepository.findById(id);
    }

    /**
     * Método para salvar um novo curso ou atualizar um existente.
     * @param curso O objeto Curso a ser salvo no banco de dados.
     * @return O objeto Curso salvo (com o ID, caso seja um novo curso).
     */
    public Curso salvar(Curso curso) {
        // Aqui seria um bom lugar para adicionar validações ou lógica de negócios antes de salvar.
        // Por exemplo, verificar se já existe um curso com o mesmo nome.
        // Chama o método save() do repositório para persistir o objeto.
        return cursoRepository.save(curso);
    }

    /**
     * Método para atualizar os dados de um curso existente.
     * @param id O ID do curso a ser atualizado.
     * @param cursoAtualizado Um objeto Curso com os novos dados.
     * @return O objeto Curso com os dados atualizados.
     * @throws RuntimeException se nenhum curso for encontrado com o ID fornecido.
     */
    public Curso atualizar(String id, Curso cursoAtualizado) {
        // Primeiro, busca o curso pelo ID. O retorno é um Optional.
        return cursoRepository.findById(id)
                .map(cursoExistente -> { // O método map() é executado apenas se o curso for encontrado.
                    // Atualiza os campos do curso que já existe no banco com os dados do curso que veio na requisição.
                    cursoExistente.setNome(cursoAtualizado.getNome());
                    cursoExistente.setNivel(cursoAtualizado.getNivel());
                    cursoExistente.setModalidade(cursoAtualizado.getModalidade());
                    cursoExistente.setTurno(cursoAtualizado.getTurno());
                    // Salva o curso com os dados atualizados e o retorna.
                    return cursoRepository.save(cursoExistente);
                })
                // Se o findById() não encontrar nada, o orElseThrow() é executado.
                .orElseThrow(() -> new RuntimeException("Curso não encontrado com o id: " + id)); // Lança uma exceção informando que o curso não foi encontrado.
    }

    /**
     * Método para excluir um curso do banco de dados.
     * @param id O ID do curso a ser excluído.
     * @throws RuntimeException se nenhum curso for encontrado com o ID fornecido.
     */
    public void excluir(String id) {
        // Primeiro, verifica se um curso com o ID fornecido realmente existe.
        if (!cursoRepository.existsById(id)) {
            // Se não existe, lança uma exceção para informar o erro.
            throw new RuntimeException("Curso não encontrado com o id: " + id);
        }
        // Se existe, chama o método deleteById() do repositório para remover o curso.
        cursoRepository.deleteById(id);
    }
}
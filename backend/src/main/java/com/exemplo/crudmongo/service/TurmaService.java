// Define o pacote onde esta classe está localizada. A camada de Serviço é responsável pela lógica de negócios da aplicação.
package com.exemplo.crudmongo.service;

// Importações de classes e anotações necessárias.
import com.exemplo.crudmongo.Model.Turma; // Importa o modelo de dados 'Turma'.
import com.exemplo.crudmongo.repository.TurmaRepository; // Importa o repositório que faz a comunicação com o banco de dados para a entidade Turma.
import org.springframework.stereotype.Service; // Importa a anotação @Service, que marca a classe como um componente de serviço do Spring.

import java.util.List; // Importa a interface para trabalhar com listas.
import java.util.Optional; // Importa a classe Optional, que é uma forma elegante de lidar com valores que podem ser nulos, evitando NullPointerException.

/**
 * A anotação @Service indica ao Spring que esta classe é um "Serviço".
 * Ela contém a lógica de negócios da aplicação e atua como um intermediário
 * entre a camada de Controller (que lida com requisições web) e a camada de Repository (que lida com o banco de dados).
 */
@Service
public class TurmaService {

    // Declaração final do repositório de turma. 'final' significa que ele deve ser inicializado no construtor.
    private final TurmaRepository turmaRepository;

    /**
     * Construtor da classe TurmaService.
     * O Spring utiliza este construtor para realizar a "Injeção de Dependência".
     * Ele automaticamente cria uma instância de TurmaRepository e a "injeta" aqui,
     * permitindo que o serviço utilize os métodos do repositório para acessar o banco de dados.
     * @param turmaRepository A instância de TurmaRepository fornecida pelo Spring.
     */
    public TurmaService(TurmaRepository turmaRepository) {
        this.turmaRepository = turmaRepository;
    }

    /**
     * Método para listar todas as turmas.
     * @return Uma lista com todas as turmas encontradas no banco de dados.
     */
    public List<Turma> listarTodas() {
        // Chama o método findAll() do repositório, que o Spring Data MongoDB implementa automaticamente.
        return turmaRepository.findAll();
    }

    /**
     * Método para buscar uma única turma pelo seu ID.
     * @param id O ID da turma a ser buscada.
     * @return Um Optional contendo a Turma se ela for encontrada, ou um Optional vazio caso contrário.
     */
    public Optional<Turma> buscarPorId(String id) {
        // Chama o método findById() do repositório.
        return turmaRepository.findById(id);
    }

    /**
     * Método para salvar uma nova turma ou atualizar uma existente.
     * @param turma O objeto Turma a ser salvo no banco de dados.
     * @return O objeto Turma salvo (com o ID, caso seja uma nova turma).
     */
    public Turma salvar(Turma turma) {
        // Aqui seria um bom lugar para adicionar validações ou lógica de negócios antes de salvar.
        // Por exemplo, verificar se a disciplinaId e o professorId existem.
        // Chama o método save() do repositório para persistir o objeto.
        return turmaRepository.save(turma);
    }

    /**
     * Método para atualizar os dados de uma turma existente.
     * @param id O ID da turma a ser atualizada.
     * @param turmaAtualizada Um objeto Turma com os novos dados.
     * @return O objeto Turma com os dados atualizados.
     * @throws RuntimeException se nenhuma turma for encontrada com o ID fornecido.
     */
    public Turma atualizar(String id, Turma turmaAtualizada) {
        // Primeiro, busca a turma pelo ID. O retorno é um Optional.
        return turmaRepository.findById(id)
                .map(turmaExistente -> { // O método map() é executado apenas se a turma for encontrada.
                    // Atualiza os campos da turma que já existe no banco com os dados da turma que veio na requisição.
                    turmaExistente.setDisciplinaId(turmaAtualizada.getDisciplinaId());
                    turmaExistente.setAno(turmaAtualizada.getAno());
                    turmaExistente.setSemestre(turmaAtualizada.getSemestre());
                    turmaExistente.setProfessor(turmaAtualizada.getProfessor());
                    // Salva a turma com os dados atualizados e a retorna.
                    return turmaRepository.save(turmaExistente);
                })
                // Se o findById() não encontrar nada, o orElseThrow() é executado.
                .orElseThrow(() -> new RuntimeException("Turma não encontrada com o id: " + id)); // Lança uma exceção informando que a turma não foi encontrada.
    }

    /**
     * Método para excluir uma turma do banco de dados.
     * @param id O ID da turma a ser excluída.
     * @throws RuntimeException se nenhuma turma for encontrada com o ID fornecido.
     */
    public void excluir(String id) {
        // Primeiro, verifica se uma turma com o ID fornecido realmente existe.
        if (!turmaRepository.existsById(id)) {
            // Se não existe, lança uma exceção para informar o erro.
            throw new RuntimeException("Turma não encontrada com o id: " + id);
        }
        // Se existe, chama o método deleteById() do repositório para remover a turma.
        turmaRepository.deleteById(id);
    }
}
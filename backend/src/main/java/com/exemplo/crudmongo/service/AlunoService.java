// Define o pacote onde esta classe está localizada. A camada de Serviço é responsável pela lógica de negócios.
package com.exemplo.crudmongo.service;

// Importações de classes e anotações necessárias.
import com.exemplo.crudmongo.Model.Aluno; // Importa o modelo de dados 'Aluno'.
import com.exemplo.crudmongo.repository.AlunoRepository; // Importa o repositório que faz a comunicação com o banco de dados para a entidade Aluno.
import org.springframework.stereotype.Service; // Importa a anotação @Service, que marca a classe como um componente de serviço do Spring.

import java.util.List; // Importa a interface para trabalhar com listas.
import java.util.Optional; // Importa a classe Optional, que é uma forma de evitar erros de NullPointerException.

/**
 * A anotação @Service indica ao Spring que esta classe é um "Serviço".
 * Ela contém a lógica de negócios da aplicação e atua como um intermediário
 * entre a camada de Controller (que lida com requisições web) e a camada de Repository (que lida com o banco de dados).
 */
@Service
public class AlunoService {

    // Declaração final do repositório de aluno. 'final' significa que ele deve ser inicializado no construtor.
    private final AlunoRepository alunoRepository;

    /**
     * Construtor da classe AlunoService.
     * O Spring utiliza este construtor para realizar a "Injeção de Dependência".
     * Ele automaticamente cria uma instância de AlunoRepository e a "injeta" aqui,
     * permitindo que o serviço utilize os métodos do repositório.
     * @param alunoRepository A instância de AlunoRepository fornecida pelo Spring.
     */
    public AlunoService(AlunoRepository alunoRepository) {
        this.alunoRepository = alunoRepository;
    }

    /**
     * Método para listar todos os alunos.
     * @return Uma lista com todos os alunos encontrados no banco de dados.
     */
    public List<Aluno> listarTodos() {
        // Chama o método findAll() do repositório, que o Spring Data MongoDB implementa automaticamente.
        return alunoRepository.findAll();
    }

    /**
     * Método para buscar um único aluno pelo seu ID.
     * @param id O ID do aluno a ser buscado.
     * @return Um Optional contendo o Aluno se ele for encontrado, ou um Optional vazio caso contrário.
     * Usar Optional ajuda a evitar retornos nulos (null).
     */
    public Optional<Aluno> buscarPorId(String id) {
        // Chama o método findById() do repositório.
        return alunoRepository.findById(id);
    }

    /**
     * Método para salvar um novo aluno ou atualizar um existente.
     * @param aluno O objeto Aluno a ser salvo no banco de dados.
     * @return O objeto Aluno salvo (com o ID, caso seja um novo aluno).
     */
    public Aluno salvar(Aluno aluno) {
        // Aqui seria um bom lugar para adicionar validações ou lógica de negócios antes de salvar.
        // Por exemplo, verificar se o email do aluno já existe.
        // Chama o método save() do repositório para persistir o objeto.
        return alunoRepository.save(aluno);
    }

    /**
     * Método para atualizar os dados de um aluno existente.
     * @param id O ID do aluno a ser atualizado.
     * @param alunoAtualizado Um objeto Aluno com os novos dados.
     * @return O objeto Aluno com os dados atualizados.
     * @throws RuntimeException se nenhum aluno for encontrado com o ID fornecido.
     */
    public Aluno atualizar(String id, Aluno alunoAtualizado) {
        // Primeiro, busca o aluno pelo ID. O retorno é um Optional.
        return alunoRepository.findById(id)
                .map(alunoExistente -> { // O método map() é executado apenas se o aluno for encontrado.
                    // Atualiza os campos do aluno que já existe no banco com os dados do aluno que veio na requisição.
                    alunoExistente.setNome(alunoAtualizado.getNome());
                    alunoExistente.setIra(alunoAtualizado.getIra());
                    alunoExistente.setCursoId(alunoAtualizado.getCursoId());
                    alunoExistente.setPeriodoIngressoId(alunoAtualizado.getPeriodoIngressoId());
                    // Salva o aluno com os dados atualizados e o retorna.
                    return alunoRepository.save(alunoExistente);
                })
                // Se o findById() não encontrar nada, o orElseThrow() é executado.
                .orElseThrow(() -> new RuntimeException("Aluno não encontrado com o id: " + id)); // Lança uma exceção informando que o aluno não foi encontrado.
    }

    /**
     * Método para excluir um aluno do banco de dados.
     * @param id O ID do aluno a ser excluído.
     * @throws RuntimeException se nenhum aluno for encontrado com o ID fornecido.
     */
    public void excluir(String id) {
        // Primeiro, verifica se um aluno com o ID fornecido realmente existe.
        if (!alunoRepository.existsById(id)) {
            // Se não existe, lança uma exceção para informar o erro.
            throw new RuntimeException("Aluno não encontrado com o id: " + id);
        }
        // Se existe, chama o método deleteById() do repositório para remover o aluno.
        alunoRepository.deleteById(id);
    }
}
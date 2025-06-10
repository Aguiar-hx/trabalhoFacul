// Define o pacote onde esta classe está localizada. A camada de Serviço é responsável pela lógica de negócios da aplicação.
package com.exemplo.crudmongo.service;

// Importações de classes e anotações necessárias.
import com.exemplo.crudmongo.Model.Disciplina; // Importa o modelo de dados 'Disciplina'.
import com.exemplo.crudmongo.repository.DisciplinaRepository; // Importa o repositório que faz a comunicação com o banco de dados para a entidade Disciplina.
import org.springframework.stereotype.Service; // Importa a anotação @Service, que marca a classe como um componente de serviço do Spring.

import java.util.List; // Importa a interface para trabalhar com listas.
import java.util.Optional; // Importa a classe Optional, que é uma forma elegante de lidar com valores que podem ser nulos, evitando NullPointerException.

/**
 * A anotação @Service indica ao Spring que esta classe é um "Serviço".
 * Ela contém a lógica de negócios da aplicação e atua como um intermediário
 * entre a camada de Controller (que lida com requisições web) e a camada de Repository (que lida com o banco de dados).
 */
@Service
public class DisciplinaService {

    // Declaração final do repositório de disciplina. 'final' significa que ele deve ser inicializado no construtor.
    private final DisciplinaRepository disciplinaRepository;

    /**
     * Construtor da classe DisciplinaService.
     * O Spring utiliza este construtor para realizar a "Injeção de Dependência".
     * Ele automaticamente cria uma instância de DisciplinaRepository e a "injeta" aqui,
     * permitindo que o serviço utilize os métodos do repositório para acessar o banco de dados.
     * @param disciplinaRepository A instância de DisciplinaRepository fornecida pelo Spring.
     */
    public DisciplinaService(DisciplinaRepository disciplinaRepository) {
        this.disciplinaRepository = disciplinaRepository;
    }

    /**
     * Método para listar todas as disciplinas.
     * @return Uma lista com todas as disciplinas encontradas no banco de dados.
     */
    public List<Disciplina> listarTodas() {
        // Chama o método findAll() do repositório, que o Spring Data MongoDB implementa automaticamente.
        return disciplinaRepository.findAll();
    }

    /**
     * Método para buscar uma única disciplina pelo seu ID.
     * @param id O ID da disciplina a ser buscada.
     * @return Um Optional contendo a Disciplina se ela for encontrada, ou um Optional vazio caso contrário.
     */
    public Optional<Disciplina> buscarPorId(String id) {
        // Chama o método findById() do repositório.
        return disciplinaRepository.findById(id);
    }

    /**
     * Método para salvar uma nova disciplina ou atualizar uma existente.
     * @param disciplina O objeto Disciplina a ser salvo no banco de dados.
     * @return O objeto Disciplina salvo (com o ID, caso seja uma nova disciplina).
     */
    public Disciplina salvar(Disciplina disciplina) {
        // Aqui seria um bom lugar para adicionar validações ou lógica de negócios antes de salvar.
        // Chama o método save() do repositório para persistir o objeto.
        return disciplinaRepository.save(disciplina);
    }

    /**
     * Método para atualizar os dados de uma disciplina existente.
     * @param id O ID da disciplina a ser atualizada.
     * @param disciplinaAtualizada Um objeto Disciplina com os novos dados.
     * @return O objeto Disciplina com os dados atualizados.
     * @throws RuntimeException se nenhuma disciplina for encontrada com o ID fornecido.
     */
    public Disciplina atualizar(String id, Disciplina disciplinaAtualizada) {
        // Primeiro, busca a disciplina pelo ID. O retorno é um Optional.
        return disciplinaRepository.findById(id)
                .map(disciplinaExistente -> { // O método map() é executado apenas se a disciplina for encontrada.
                    // Atualiza os campos da disciplina que já existe no banco com os dados da disciplina que veio na requisição.
                    disciplinaExistente.setNome(disciplinaAtualizada.getNome());
                    disciplinaExistente.setCargaHoraria(disciplinaAtualizada.getCargaHoraria());
                    disciplinaExistente.setEmenta(disciplinaAtualizada.getEmenta());
                    // Salva a disciplina com os dados atualizados e a retorna.
                    return disciplinaRepository.save(disciplinaExistente);
                })
                // Se o findById() não encontrar nada, o orElseThrow() é executado.
                .orElseThrow(() -> new RuntimeException("Disciplina não encontrada com o id: " + id)); // Lança uma exceção informando que a disciplina não foi encontrada.
    }

    /**
     * Método para excluir uma disciplina do banco de dados.
     * @param id O ID da disciplina a ser excluída.
     * @throws RuntimeException se nenhuma disciplina for encontrada com o ID fornecido.
     */
    public void excluir(String id) {
        // Primeiro, verifica se uma disciplina com o ID fornecido realmente existe.
        if (!disciplinaRepository.existsById(id)) {
            // Se não existe, lança uma exceção para informar o erro.
            throw new RuntimeException("Disciplina não encontrada com o id: " + id);
        }
        // Se existe, chama o método deleteById() do repositório para remover a disciplina.
        disciplinaRepository.deleteById(id);
    }
}
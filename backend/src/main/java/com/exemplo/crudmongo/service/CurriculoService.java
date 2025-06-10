// Define o pacote onde esta classe está localizada. A camada de Serviço é responsável pela lógica de negócios da aplicação.
package com.exemplo.crudmongo.service;

// Importações de classes e anotações necessárias.
import com.exemplo.crudmongo.Model.Curriculo; // Importa o modelo de dados 'Curriculo'.
import com.exemplo.crudmongo.repository.CurriculoRepository; // Importa o repositório que faz a comunicação com o banco de dados para a entidade Curriculo.
import org.springframework.stereotype.Service; // Importa a anotação @Service, que marca a classe como um componente de serviço do Spring.

import java.util.List; // Importa a interface para trabalhar com listas.
import java.util.Optional; // Importa a classe Optional, que é uma forma elegante de lidar com valores que podem ser nulos, evitando NullPointerException.

/**
 * A anotação @Service indica ao Spring que esta classe é um "Serviço".
 * Ela contém a lógica de negócios da aplicação, atuando como uma ponte
 * entre a camada de Controller (que lida com requisições web) e a camada de Repository (que lida com o banco de dados).
 */
@Service
public class CurriculoService {

    // Declaração final do repositório de currículo. 'final' significa que ele deve ser inicializado no construtor.
    private final CurriculoRepository curriculoRepository;

    /**
     * Construtor da classe CurriculoService.
     * O Spring utiliza este construtor para realizar a "Injeção de Dependência".
     * Ele automaticamente cria uma instância de CurriculoRepository e a "injeta" aqui,
     * permitindo que o serviço utilize os métodos do repositório para acessar o banco de dados.
     * @param curriculoRepository A instância de CurriculoRepository fornecida pelo Spring.
     */
    public CurriculoService(CurriculoRepository curriculoRepository) {
        this.curriculoRepository = curriculoRepository;
    }

    /**
     * Método para listar todos os currículos.
     * @return Uma lista com todos os currículos encontrados no banco de dados.
     */
    public List<Curriculo> listarTodos() {
        // Chama o método findAll() do repositório, que o Spring Data MongoDB implementa automaticamente.
        return curriculoRepository.findAll();
    }

    /**
     * Método para buscar um único currículo pelo seu ID.
     * @param id O ID do currículo a ser buscado.
     * @return Um Optional contendo o Curriculo se ele for encontrado, ou um Optional vazio caso contrário.
     */
    public Optional<Curriculo> buscarPorId(String id) {
        // Chama o método findById() do repositório.
        return curriculoRepository.findById(id);
    }

    /**
     * Método para salvar um novo currículo ou atualizar um existente.
     * @param curriculo O objeto Curriculo a ser salvo no banco de dados.
     * @return O objeto Curriculo salvo (com o ID, caso seja um novo currículo).
     */
    public Curriculo salvar(Curriculo curriculo) {
        // Aqui seria um bom lugar para adicionar validações ou lógica de negócios antes de salvar.
        // Por exemplo, verificar se o cursoId existe.
        // Chama o método save() do repositório para persistir o objeto.
        return curriculoRepository.save(curriculo);
    }

    /**
     * Método para atualizar os dados de um currículo existente.
     * @param id O ID do currículo a ser atualizado.
     * @param curriculoAtualizado Um objeto Curriculo com os novos dados.
     * @return O objeto Curriculo com os dados atualizados.
     * @throws RuntimeException se nenhum currículo for encontrado com o ID fornecido.
     */
    public Curriculo atualizar(String id, Curriculo curriculoAtualizado) {
        // Primeiro, busca o currículo pelo ID. O retorno é um Optional.
        return curriculoRepository.findById(id)
                .map(curriculoExistente -> { // O método map() é executado apenas se o currículo for encontrado.
                    // Atualiza os campos do currículo que já existe no banco com os dados do currículo que veio na requisição.
                    curriculoExistente.setCursoId(curriculoAtualizado.getCursoId());
                    curriculoExistente.setAno(curriculoAtualizado.getAno());
                    curriculoExistente.setSemestre(curriculoAtualizado.getSemestre());
                    curriculoExistente.setDisciplinasObrigatorias(curriculoAtualizado.getDisciplinasObrigatorias());
                    curriculoExistente.setDisciplinasOptativas(curriculoAtualizado.getDisciplinasOptativas());
                    // Salva o currículo com os dados atualizados e o retorna.
                    return curriculoRepository.save(curriculoExistente);
                })
                // Se o findById() não encontrar nada, o orElseThrow() é executado.
                .orElseThrow(() -> new RuntimeException("Currículo não encontrado com o id: " + id)); // Lança uma exceção informando que o currículo não foi encontrado.
    }

    /**
     * Método para excluir um currículo do banco de dados.
     * @param id O ID do currículo a ser excluído.
     * @throws RuntimeException se nenhum currículo for encontrado com o ID fornecido.
     */
    public void excluir(String id) {
        // Primeiro, verifica se um currículo com o ID fornecido realmente existe para evitar tentar deletar algo que não existe.
        if (!curriculoRepository.existsById(id)) {
            // Se não existe, lança uma exceção para informar o erro.
            throw new RuntimeException("Currículo não encontrado com o id: " + id);
        }
        // Se existe, chama o método deleteById() do repositório para remover o currículo.
        curriculoRepository.deleteById(id);
    }
}
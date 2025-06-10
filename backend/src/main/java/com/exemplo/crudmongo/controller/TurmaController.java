// Define o pacote onde esta classe está localizada, uma prática essencial para a organização do projeto.
package com.exemplo.crudmongo.controller;

// Importações de classes e anotações necessárias de outras partes do projeto e do Spring Framework.
import com.exemplo.crudmongo.Model.Turma;         // Importa o modelo de dados 'Turma'.
import com.exemplo.crudmongo.service.TurmaService;    // Importa o serviço que contém a lógica de negócios para 'Turma'.
import org.springframework.http.HttpStatus;           // Usado para definir códigos de status HTTP, como "201 CREATED".
import org.springframework.http.ResponseEntity;       // Representa a resposta HTTP completa (status, cabeçalhos, corpo).
import org.springframework.web.bind.annotation.*;     // Importa as anotações do Spring para desenvolvimento web.

import java.util.List; // Importa a interface para trabalhar com listas de objetos.

/**
 * Esta é a classe Controller para a entidade Turma.
 * Ela atua como a camada de entrada para todas as requisições HTTP relacionadas a turmas.
 * Sua função é receber as chamadas do cliente (frontend), acionar a camada de serviço para
 * executar a lógica de negócios e, por fim, retornar uma resposta HTTP adequada.
 */
@RestController // Anotação que marca a classe como um controller REST. Os métodos aqui retornarão dados (geralmente em formato JSON) diretamente no corpo da resposta HTTP.
@RequestMapping("/api/turmas") // Define o caminho (URL) base para todos os endpoints neste controller. Todas as requisições devem começar com "/api/turmas".
@CrossOrigin(origins = "*") // Permite que requisições de qualquer origem (como o seu frontend em localhost:4200) acessem esta API, evitando problemas de CORS (Cross-Origin Resource Sharing) durante o desenvolvimento.
public class TurmaController {

    // Declaração final do serviço de turma, que será "injetado" pelo Spring.
    private final TurmaService turmaService;

    /**
     * Construtor da classe TurmaController.
     * O Spring utiliza este construtor para realizar a "Injeção de Dependência".
     * Ele cria e fornece automaticamente uma instância de TurmaService quando o controller é inicializado.
     * @param turmaService A instância de TurmaService gerenciada pelo Spring.
     */
    public TurmaController(TurmaService turmaService) {
        this.turmaService = turmaService;
    }

    /**
     * Método para listar todas as turmas.
     * Responde a requisições HTTP GET para o caminho base "/api/turmas".
     * @return Uma lista de todos os objetos Turma em formato JSON.
     */
    @GetMapping
    public List<Turma> listarTodasTurmas() {
        // Delega a responsabilidade de buscar os dados para a camada de serviço.
        return turmaService.listarTodas();
    }

    /**
     * Método para buscar uma única turma pelo seu ID.
     * Responde a requisições HTTP GET para "/api/turmas/{id}".
     * @param id O ID da turma, que é extraído da URL pela anotação @PathVariable.
     * @return Um ResponseEntity contendo a Turma encontrada com status 200 OK, ou um status 404 (Not Found) se nenhuma turma com esse ID for encontrada.
     */
    @GetMapping("/{id}")
    public ResponseEntity<Turma> buscarTurmaPorId(@PathVariable String id) {
        // Chama o serviço para buscar por ID e mapeia o resultado para uma resposta HTTP.
        return turmaService.buscarPorId(id)
                .map(ResponseEntity::ok) // Se encontrou, cria uma resposta 200 OK com a turma.
                .orElse(ResponseEntity.notFound().build()); // Caso contrário, cria uma resposta 404.
    }

    /**
     * Método para criar uma nova turma.
     * Responde a requisições HTTP POST para "/api/turmas".
     * @param turma O objeto Turma a ser criado, vindo do corpo (body) da requisição em formato JSON.
     * @return Um ResponseEntity com a turma recém-criada (com o novo ID) e o status 201 (Created).
     */
    @PostMapping
    public ResponseEntity<Turma> criarTurma(@RequestBody Turma turma) {
        // Chama o serviço para salvar a nova turma no banco de dados.
        Turma novaTurma = turmaService.salvar(turma);
        // Retorna uma resposta de sucesso com o status "Criado" (201) e a nova turma no corpo da resposta.
        return ResponseEntity.status(HttpStatus.CREATED).body(novaTurma);
    }

    /**
     * Método para atualizar uma turma existente.
     * Responde a requisições HTTP PUT para "/api/turmas/{id}".
     * @param id O ID da turma a ser atualizada, vindo da URL.
     * @param turma O objeto Turma com os dados atualizados, vindo do corpo da requisição.
     * @return Um ResponseEntity com a turma atualizada (status 200 OK), ou 404 (Not Found) se o ID não existir.
     */
    @PutMapping("/{id}")
    public ResponseEntity<Turma> atualizarTurma(@PathVariable String id, @RequestBody Turma turma) {
        try {
            // Tenta chamar o serviço para realizar a atualização.
            Turma turmaAtualizada = turmaService.atualizar(id, turma);
            return ResponseEntity.ok(turmaAtualizada);
        } catch (RuntimeException e) { // Captura uma exceção caso o serviço informe que a turma não foi encontrada.
            // Retorna um erro 404.
            return ResponseEntity.notFound().build();
        }
    }

    /**
     * Método para deletar uma turma.
     * Responde a requisições HTTP DELETE para "/api/turmas/{id}".
     * @param id O ID da turma a ser deletada, vindo da URL.
     * @return Um ResponseEntity com status 204 (No Content) indicando sucesso, ou 404 (Not Found) se o ID não existir.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluirTurma(@PathVariable String id) {
        try {
            // Tenta chamar o serviço para realizar a exclusão.
            turmaService.excluir(id);
            // Retorna uma resposta vazia com o status "Sem Conteúdo", que é o padrão para uma operação de DELETE bem-sucedida.
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) { // Captura a exceção se a turma a ser deletada não for encontrada.
            // Retorna um erro 404.
            return ResponseEntity.notFound().build();
        }
    }
}
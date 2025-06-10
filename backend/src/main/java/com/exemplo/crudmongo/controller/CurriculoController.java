// Define o pacote onde esta classe está localizada, mantendo o projeto organizado.
package com.exemplo.crudmongo.controller;

// Importações de classes e anotações necessárias de outras partes do projeto e do Spring Framework.
import com.exemplo.crudmongo.Model.Curriculo;     // Importa o modelo de dados 'Curriculo'.
import com.exemplo.crudmongo.service.CurriculoService; // Importa o serviço que lida com a lógica de negócios para 'Curriculo'.
import org.springframework.http.HttpStatus;           // Usado para definir códigos de status HTTP, como "201 CREATED".
import org.springframework.http.ResponseEntity;       // Representa toda a resposta HTTP (status, cabeçalhos, corpo).
import org.springframework.web.bind.annotation.*;     // Importa as anotações do Spring para web, como @RestController, @GetMapping, etc.

import java.util.List; // Importa a interface para trabalhar com listas de objetos.

/**
 * Esta é a classe Controller para a entidade Curriculo.
 * Ela serve como a porta de entrada para todas as requisições HTTP relacionadas a currículos.
 * Sua responsabilidade é receber as requisições, delegar a lógica para a camada de serviço e retornar uma resposta HTTP apropriada.
 */
@RestController // Anotação que marca a classe como um controller REST. Os métodos aqui retornarão dados (como JSON) diretamente no corpo da resposta.
@RequestMapping("/api/curriculos") // Define o caminho (URL) base para todos os endpoints deste controller. Todas as requisições devem começar com "/api/curriculos".
@CrossOrigin(origins = "*") // Permite que requisições de qualquer origem (qualquer frontend) acessem esta API. Essencial para o desenvolvimento com Angular em um servidor separado.
public class CurriculoController {

    // Declaração final do serviço de currículo, que será injetado pelo Spring.
    private final CurriculoService curriculoService;

    /**
     * Construtor do CurriculoController.
     * O Spring utiliza este construtor para realizar a "Injeção de Dependência".
     * Ele cria e fornece automaticamente uma instância de CurriculoService quando o controller é criado.
     * @param curriculoService A instância de CurriculoService fornecida pelo Spring.
     */
    public CurriculoController(CurriculoService curriculoService) {
        this.curriculoService = curriculoService;
    }

    /**
     * Método para listar todos os currículos.
     * Responde a requisições HTTP GET para o caminho base "/api/curriculos".
     * @return Uma lista de todos os objetos Curriculo em formato JSON.
     */
    @GetMapping
    public List<Curriculo> listarTodosCurriculos() {
        // Delega a chamada para o serviço, que buscará os dados no banco.
        return curriculoService.listarTodos();
    }

    /**
     * Método para buscar um único currículo pelo seu ID.
     * Responde a requisições HTTP GET para "/api/curriculos/{id}", onde {id} é uma variável na URL.
     * @param id O ID do currículo, extraído da URL pela anotação @PathVariable.
     * @return Um ResponseEntity com o Curriculo encontrado e status 200 OK, ou um status 404 (Not Found) se não for encontrado.
     */
    @GetMapping("/{id}")
    public ResponseEntity<Curriculo> buscarCurriculoPorId(@PathVariable String id) {
        return curriculoService.buscarPorId(id)
                .map(ResponseEntity::ok) // Se o serviço encontrar o currículo, retorna uma resposta 200 OK com o currículo.
                .orElse(ResponseEntity.notFound().build()); // Caso contrário, retorna uma resposta 404 Not Found.
    }

    /**
     * Método para criar um novo currículo.
     * Responde a requisições HTTP POST para "/api/curriculos".
     * @param curriculo O objeto Curriculo a ser criado, vindo do corpo (body) da requisição em formato JSON.
     * @return Um ResponseEntity com o currículo recém-criado (com o ID gerado) e o status 201 (Created).
     */
    @PostMapping
    public ResponseEntity<Curriculo> criarCurriculo(@RequestBody Curriculo curriculo) {
        // Chama o serviço para salvar o novo currículo.
        Curriculo novoCurriculo = curriculoService.salvar(curriculo);
        // Retorna uma resposta de sucesso com o status "Criado".
        return ResponseEntity.status(HttpStatus.CREATED).body(novoCurriculo);
    }

    /**
     * Método para atualizar um currículo existente.
     * Responde a requisições HTTP PUT para "/api/curriculos/{id}".
     * @param id O ID do currículo a ser atualizado, vindo da URL.
     * @param curriculo O objeto Curriculo com os dados atualizados, vindo do corpo da requisição.
     * @return Um ResponseEntity com o currículo atualizado e status 200 OK, ou 404 Not Found se o ID não existir.
     */
    @PutMapping("/{id}")
    public ResponseEntity<Curriculo> atualizarCurriculo(@PathVariable String id, @RequestBody Curriculo curriculo) {
        try {
            // Tenta chamar o serviço de atualização.
            Curriculo curriculoAtualizado = curriculoService.atualizar(id, curriculo);
            return ResponseEntity.ok(curriculoAtualizado);
        } catch (RuntimeException e) { // Captura a exceção que o serviço lança se o currículo não for encontrado.
            // Retorna um erro 404.
            return ResponseEntity.notFound().build();
        }
    }

    /**
     * Método para deletar um currículo.
     * Responde a requisições HTTP DELETE para "/api/curriculos/{id}".
     * @param id O ID do currículo a ser deletado, vindo da URL.
     * @return Um ResponseEntity com status 204 (No Content) indicando sucesso, ou 404 (Not Found) se o ID não existir.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluirCurriculo(@PathVariable String id) {
        try {
            // Tenta chamar o serviço de exclusão.
            curriculoService.excluir(id);
            // Retorna uma resposta vazia com o status "Sem Conteúdo", que é o padrão para um DELETE bem-sucedido.
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) { // Captura a exceção se o currículo a ser deletado não for encontrado.
            // Retorna um erro 404.
            return ResponseEntity.notFound().build();
        }
    }
}
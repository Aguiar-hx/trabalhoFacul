// Define o pacote onde esta classe está localizada.
package com.exemplo.crudmongo.controller;

// Importa as classes e anotações necessárias de outras partes do projeto e do Spring Framework.
import com.exemplo.crudmongo.Model.Aluno;         // Importa o modelo de dados 'Aluno'.
import com.exemplo.crudmongo.service.AlunoService;    // Importa o serviço que contém a lógica de negócios para 'Aluno'.
import org.springframework.http.HttpStatus;           // Usado para definir códigos de status HTTP, como "201 CREATED".
import org.springframework.http.ResponseEntity;       // Uma classe do Spring que representa toda a resposta HTTP (status, cabeçalhos e corpo).
import org.springframework.web.bind.annotation.*;     // Importa todas as anotações de mapeamento da web do Spring (RestController, GetMapping, etc.).

import java.util.List; // Importa a interface 'List' para trabalhar com listas de objetos.

/**
 * Esta é a classe Controller para a entidade Aluno.
 * Ela é a porta de entrada para todas as requisições HTTP relacionadas a alunos.
 * É responsável por receber as requisições, chamar a camada de serviço e retornar uma resposta.
 */
@RestController // Anotação que combina @Controller e @ResponseBody. Diz ao Spring que esta classe é um controller e que os retornos dos métodos devem ser convertidos para JSON e enviados no corpo da resposta.
@RequestMapping("/api/alunos") // Define o caminho (URL) base para todos os endpoints nesta classe. Todas as requisições para este controller devem começar com "/api/alunos".
@CrossOrigin(origins = "*") // Permite que requisições de qualquer origem (qualquer frontend, como o seu rodando em localhost:4200) possam acessar esta API. Importante para o desenvolvimento.
public class AlunoController {

    // Declaração final do serviço de aluno. 'final' significa que ele deve ser inicializado no construtor.
    private final AlunoService alunoService;

    /**
     * Construtor da classe AlunoController.
     * O Spring usa este construtor para realizar a "Injeção de Dependência".
     * Ele automaticamente cria uma instância de AlunoService e a "injeta" aqui.
     * @param alunoService A instância de AlunoService fornecida pelo Spring.
     */
    public AlunoController(AlunoService alunoService) {
        this.alunoService = alunoService;
    }

    /**
     * Método para listar todos os alunos.
     * Responde a requisições HTTP GET para o caminho base "/api/alunos".
     * @return Uma lista de todos os objetos Aluno em formato JSON.
     */
    @GetMapping // Mapeia requisições HTTP GET para este método.
    public List<Aluno> listarTodosAlunos() {
        // Chama o método 'listarTodos' do serviço para buscar os dados.
        return alunoService.listarTodos();
    }

    /**
     * Método para buscar um único aluno pelo seu ID.
     * Responde a requisições HTTP GET para "/api/alunos/{id}", onde {id} é uma variável.
     * @param id O ID do aluno, extraído da URL pela anotação @PathVariable.
     * @return Um ResponseEntity contendo o Aluno encontrado (com status 200 OK) ou um status 404 (Not Found) se nenhum aluno com esse ID for encontrado.
     */
    @GetMapping("/{id}") // Mapeia requisições GET para um subcaminho que contém uma variável 'id'.
    public ResponseEntity<Aluno> buscarAlunoPorId(@PathVariable String id) {
        // Chama o serviço para buscar um aluno pelo ID.
        return alunoService.buscarPorId(id)
                .map(ResponseEntity::ok) // Se o serviço retornar um Aluno, cria uma resposta com status 200 OK e o aluno no corpo.
                .orElse(ResponseEntity.notFound().build()); // Se o serviço não retornar nada, cria uma resposta com status 404 Not Found.
    }

    /**
     * Método para criar um novo aluno.
     * Responde a requisições HTTP POST para "/api/alunos".
     * @param aluno O objeto Aluno a ser criado, extraído do corpo (body) da requisição JSON pela anotação @RequestBody.
     * @return Um ResponseEntity com o aluno recém-criado (incluindo o ID gerado pelo banco) e o status 201 (Created).
     */
    @PostMapping // Mapeia requisições HTTP POST para este método.
    public ResponseEntity<Aluno> criarAluno(@RequestBody Aluno aluno) {
        // Chama o serviço para salvar o novo aluno no banco de dados.
        Aluno novoAluno = alunoService.salvar(aluno);
        // Retorna uma resposta com o status HTTP 201 (Created) e o novo aluno no corpo.
        return ResponseEntity.status(HttpStatus.CREATED).body(novoAluno);
    }

    /**
     * Método para atualizar um aluno existente.
     * Responde a requisições HTTP PUT para "/api/alunos/{id}".
     * @param id O ID do aluno a ser atualizado, vindo da URL.
     * @param aluno O objeto Aluno com os dados atualizados, vindo do corpo da requisição.
     * @return Um ResponseEntity com o aluno atualizado (status 200 OK) ou 404 (Not Found) se o ID não existir.
     */
    @PutMapping("/{id}") // Mapeia requisições HTTP PUT para este método.
    public ResponseEntity<Aluno> atualizarAluno(@PathVariable String id, @RequestBody Aluno aluno) {
        try {
            // Chama o serviço para atualizar o aluno.
            Aluno alunoAtualizado = alunoService.atualizar(id, aluno);
            // Retorna o aluno com os dados atualizados e status 200 OK.
            return ResponseEntity.ok(alunoAtualizado);
        } catch (RuntimeException e) { // Captura a exceção que o serviço pode lançar se o aluno não for encontrado.
            // Retorna uma resposta 404 Not Found.
            return ResponseEntity.notFound().build();
        }
    }

    /**
     * Método para deletar um aluno.
     * Responde a requisições HTTP DELETE para "/api/alunos/{id}".
     * @param id O ID do aluno a ser deletado, vindo da URL.
     * @return Um ResponseEntity com status 204 (No Content) em caso de sucesso, ou 404 (Not Found) se o ID não existir.
     */
    @DeleteMapping("/{id}") // Mapeia requisições HTTP DELETE para este método.
    public ResponseEntity<Void> excluirAluno(@PathVariable String id) {
        try {
            // Chama o serviço para excluir o aluno.
            alunoService.excluir(id);
            // Retorna uma resposta vazia com status 204 No Content, indicando sucesso na exclusão.
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) { // Captura a exceção se o aluno a ser deletado não for encontrado.
            // Retorna uma resposta 404 Not Found.
            return ResponseEntity.notFound().build();
        }
    }
}
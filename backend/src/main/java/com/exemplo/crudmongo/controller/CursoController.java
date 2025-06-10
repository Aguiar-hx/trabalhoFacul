// Define o pacote onde esta classe está localizada, ajudando a manter o projeto organizado.
package com.exemplo.crudmongo.controller;

// Importações de classes e anotações necessárias.
import com.exemplo.crudmongo.Model.Curso;         // Importa o modelo de dados 'Curso'.
import com.exemplo.crudmongo.service.CursoService;    // Importa o serviço que contém a lógica de negócios para 'Curso'.
import org.springframework.http.HttpStatus;           // Usado para definir códigos de status HTTP, como "201 CREATED".
import org.springframework.http.ResponseEntity;       // Representa a resposta HTTP completa (status, cabeçalhos, corpo).
import org.springframework.web.bind.annotation.*;     // Importa as anotações do Spring para web.

import java.util.List; // Importa a interface para trabalhar com listas.

/**
 * Esta é a classe Controller para a entidade Curso.
 * Funciona como a camada de entrada para requisições HTTP relacionadas a cursos.
 * Ela recebe as chamadas do frontend, utiliza a camada de serviço para processar a lógica
 * e retorna uma resposta HTTP adequada.
 */
@RestController // Anotação que marca a classe como um controller REST, onde os métodos retornam dados (como JSON) diretamente no corpo da resposta.
@RequestMapping("/api/cursos") // Define o caminho (URL) base para todos os endpoints deste controller. Todas as requisições devem começar com "/api/cursos".
@CrossOrigin(origins = "*") // Permite que requisições de qualquer origem (como o seu frontend em localhost:4200) acessem esta API.
public class CursoController {

    // Declaração final do serviço de curso, que será "injetado" pelo Spring.
    private final CursoService cursoService;

    /**
     * Construtor do CursoController.
     * O Spring utiliza este construtor para realizar a "Injeção de Dependência",
     * fornecendo automaticamente uma instância de CursoService.
     * @param cursoService A instância de CursoService gerenciada pelo Spring.
     */
    public CursoController(CursoService cursoService) {
        this.cursoService = cursoService;
    }

    /**
     * Método para listar todos os cursos.
     * Responde a requisições HTTP GET para "/api/cursos".
     * @return Uma lista de todos os objetos Curso em formato JSON.
     */
    @GetMapping
    public List<Curso> listarTodosCursos() {
        // Chama o método do serviço para buscar todos os cursos no banco de dados.
        return cursoService.listarTodos();
    }

    /**
     * Método para buscar um único curso pelo seu ID.
     * Responde a requisições HTTP GET para "/api/cursos/{id}".
     * @param id O ID do curso, extraído da URL pela anotação @PathVariable.
     * @return Um ResponseEntity contendo o Curso encontrado com status 200 OK, ou um status 404 (Not Found) se o ID não existir.
     */
    @GetMapping("/{id}")
    public ResponseEntity<Curso> buscarCursoPorId(@PathVariable String id) {
        // Chama o serviço para buscar por ID e mapeia o resultado para uma resposta HTTP.
        return cursoService.buscarPorId(id)
                .map(ResponseEntity::ok) // Se encontrou, cria uma resposta 200 OK com o curso.
                .orElse(ResponseEntity.notFound().build()); // Se não encontrou, cria uma resposta 404.
    }

    /**
     * Método para criar um novo curso.
     * Responde a requisições HTTP POST para "/api/cursos".
     * @param curso O objeto Curso a ser criado, vindo do corpo (body) da requisição em formato JSON.
     * @return Um ResponseEntity com o curso recém-criado (com ID) e o status 201 (Created).
     */
    @PostMapping
    public ResponseEntity<Curso> criarCurso(@RequestBody Curso curso) {
        // Chama o serviço para salvar o novo curso.
        Curso novoCurso = cursoService.salvar(curso);
        // Retorna uma resposta de sucesso com o status "Criado" e o objeto salvo no corpo.
        return ResponseEntity.status(HttpStatus.CREATED).body(novoCurso);
    }

    /**
     * Método para atualizar um curso existente.
     * Responde a requisições HTTP PUT para "/api/cursos/{id}".
     * @param id O ID do curso a ser atualizado, vindo da URL.
     * @param curso O objeto Curso com os dados atualizados, vindo do corpo da requisição.
     * @return Um ResponseEntity com o curso atualizado (status 200 OK), ou 404 (Not Found) se o ID não existir.
     */
    @PutMapping("/{id}")
    public ResponseEntity<Curso> atualizarCurso(@PathVariable String id, @RequestBody Curso curso) {
        try {
            // Tenta chamar o serviço para atualizar o curso.
            Curso cursoAtualizado = cursoService.atualizar(id, curso);
            return ResponseEntity.ok(cursoAtualizado);
        } catch (RuntimeException e) { // Captura a exceção se o serviço informar que o curso não foi encontrado.
            // Retorna um erro 404.
            return ResponseEntity.notFound().build();
        }
    }

    /**
     * Método para deletar um curso.
     * Responde a requisições HTTP DELETE para "/api/cursos/{id}".
     * @param id O ID do curso a ser deletado, vindo da URL.
     * @return Um ResponseEntity com status 204 (No Content) em caso de sucesso, ou 404 (Not Found) se o ID não existir.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluirCurso(@PathVariable String id) {
        try {
            // Tenta chamar o serviço para excluir o curso.
            cursoService.excluir(id);
            // Retorna uma resposta vazia com status "Sem Conteúdo", que é o padrão para um DELETE bem-sucedido.
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) { // Captura a exceção se o curso a ser deletado não for encontrado.
            // Retorna um erro 404.
            return ResponseEntity.notFound().build();
        }
    }
}
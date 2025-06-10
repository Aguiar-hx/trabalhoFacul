// Define o pacote onde esta classe está localizada, o que ajuda na organização do projeto.
package com.exemplo.crudmongo.controller;

// Importações de classes e anotações necessárias.
import com.exemplo.crudmongo.Model.Disciplina;    // Importa o modelo de dados 'Disciplina'.
import com.exemplo.crudmongo.service.DisciplinaService; // Importa o serviço que contém a lógica de negócios para 'Disciplina'.
import org.springframework.http.HttpStatus;           // Usado para definir códigos de status HTTP, como "201 CREATED".
import org.springframework.http.ResponseEntity;       // Representa a resposta HTTP completa (status, cabeçalhos, corpo).
import org.springframework.web.bind.annotation.*;     // Importa as anotações do Spring para desenvolvimento web.

import java.util.List; // Importa a interface para trabalhar com listas.

/**
 * Esta é a classe Controller para a entidade Disciplina.
 * Ela é responsável por receber as requisições HTTP do frontend (ou de qualquer cliente),
 * chamar a camada de serviço apropriada para processar a requisição e, por fim,
 * retornar uma resposta HTTP com os dados ou o status da operação.
 */
@RestController // Anotação que marca a classe como um controller REST. Isso significa que os métodos aqui retornarão dados (geralmente em formato JSON) diretamente no corpo da resposta.
@RequestMapping("/api/disciplinas") // Define o caminho (URL) base para todos os endpoints neste controller. Todas as requisições devem começar com "/api/disciplinas".
@CrossOrigin(origins = "*") // Permite que requisições de qualquer origem (qualquer frontend, como o seu rodando em localhost:4200) possam acessar esta API, evitando problemas de CORS (Cross-Origin Resource Sharing).
public class DisciplinaController {

    // Declaração final do serviço de disciplina. O 'final' garante que ele seja inicializado no construtor.
    private final DisciplinaService disciplinaService;

    /**
     * Construtor do DisciplinaController.
     * O Spring utiliza este construtor para fazer a "Injeção de Dependência",
     * fornecendo automaticamente uma instância de DisciplinaService quando este controller é criado.
     * @param disciplinaService A instância de DisciplinaService gerenciada pelo Spring.
     */
    public DisciplinaController(DisciplinaService disciplinaService) {
        this.disciplinaService = disciplinaService;
    }

    /**
     * Método para listar todas as disciplinas.
     * Responde a requisições HTTP GET para o caminho base "/api/disciplinas".
     * @return Uma lista de todos os objetos Disciplina em formato JSON.
     */
    @GetMapping
    public List<Disciplina> listarTodasDisciplinas() {
        // Delega a responsabilidade de buscar os dados para a camada de serviço.
        return disciplinaService.listarTodas();
    }

    /**
     * Método para buscar uma única disciplina pelo seu ID.
     * Responde a requisições HTTP GET para "/api/disciplinas/{id}", onde {id} é um valor dinâmico.
     * @param id O ID da disciplina, que é extraído da URL pela anotação @PathVariable.
     * @return Um ResponseEntity contendo a Disciplina encontrada com status 200 OK, ou um status 404 (Not Found) se nenhuma disciplina com o ID for encontrada.
     */
    @GetMapping("/{id}")
    public ResponseEntity<Disciplina> buscarDisciplinaPorId(@PathVariable String id) {
        // Chama o serviço para buscar por ID.
        return disciplinaService.buscarPorId(id)
                .map(ResponseEntity::ok) // Se o serviço encontrou a disciplina, cria uma resposta 200 OK com a disciplina no corpo.
                .orElse(ResponseEntity.notFound().build()); // Caso contrário, cria uma resposta 404 Not Found.
    }

    /**
     * Método para criar uma nova disciplina.
     * Responde a requisições HTTP POST para "/api/disciplinas".
     * @param disciplina O objeto Disciplina a ser criado, que vem do corpo (body) da requisição em formato JSON, mapeado pela anotação @RequestBody.
     * @return Um ResponseEntity com a disciplina recém-criada (incluindo seu novo ID) e o status 201 (Created).
     */
    @PostMapping
    public ResponseEntity<Disciplina> criarDisciplina(@RequestBody Disciplina disciplina) {
        // Chama o serviço para salvar a nova disciplina no banco de dados.
        Disciplina novaDisciplina = disciplinaService.salvar(disciplina);
        // Retorna uma resposta de sucesso com o status "Criado" (201) e a nova disciplina no corpo da resposta.
        return ResponseEntity.status(HttpStatus.CREATED).body(novaDisciplina);
    }

    /**
     * Método para atualizar uma disciplina existente.
     * Responde a requisições HTTP PUT para "/api/disciplinas/{id}".
     * @param id O ID da disciplina a ser atualizada, vindo da URL.
     * @param disciplina O objeto Disciplina com os dados atualizados, vindo do corpo da requisição.
     * @return Um ResponseEntity com a disciplina atualizada (status 200 OK), ou 404 (Not Found) se o ID não existir.
     */
    @PutMapping("/{id}")
    public ResponseEntity<Disciplina> atualizarDisciplina(@PathVariable String id, @RequestBody Disciplina disciplina) {
        try {
            // Tenta chamar o serviço para realizar a atualização.
            Disciplina disciplinaAtualizada = disciplinaService.atualizar(id, disciplina);
            return ResponseEntity.ok(disciplinaAtualizada);
        } catch (RuntimeException e) { // Captura uma exceção genérica caso o serviço informe que a disciplina não foi encontrada.
            // Retorna um erro 404.
            return ResponseEntity.notFound().build();
        }
    }

    /**
     * Método para deletar uma disciplina.
     * Responde a requisições HTTP DELETE para "/api/disciplinas/{id}".
     * @param id O ID da disciplina a ser deletada, vindo da URL.
     * @return Um ResponseEntity com status 204 (No Content) indicando sucesso na exclusão, ou 404 (Not Found) se o ID não existir.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluirDisciplina(@PathVariable String id) {
        try {
            // Tenta chamar o serviço para realizar a exclusão.
            disciplinaService.excluir(id);
            // Retorna uma resposta vazia com o status "Sem Conteúdo", que é o padrão para uma operação de DELETE bem-sucedida.
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) { // Captura a exceção se a disciplina a ser deletada não for encontrada.
            // Retorna um erro 404.
            return ResponseEntity.notFound().build();
        }
    }
}
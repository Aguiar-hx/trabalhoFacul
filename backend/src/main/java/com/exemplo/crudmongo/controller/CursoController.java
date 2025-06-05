package com.exemplo.crudmongo.controller;

import com.exemplo.crudmongo.Model.Curso;
import com.exemplo.crudmongo.service.CursoService; // Você precisará criar este serviço
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cursos") // Conforme seu OpenAPI
@CrossOrigin(origins = "*") // Permite requisições de qualquer origem, ajuste para produção
public class CursoController {

    private final CursoService cursoService;

    public CursoController(CursoService cursoService) {
        this.cursoService = cursoService;
    }

    @GetMapping
    public List<Curso> listarTodosCursos() {
        return cursoService.listarTodos();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Curso> buscarCursoPorId(@PathVariable String id) {
        return cursoService.buscarPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Curso> criarCurso(@RequestBody Curso curso) {
        Curso novoCurso = cursoService.salvar(curso);
        return ResponseEntity.status(HttpStatus.CREATED).body(novoCurso);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Curso> atualizarCurso(@PathVariable String id, @RequestBody Curso curso) {
        try {
            // Supondo que seu CursoService terá um método atualizar
            Curso cursoAtualizado = cursoService.atualizar(id, curso);
            return ResponseEntity.ok(cursoAtualizado);
        } catch (RuntimeException e) { // Trate exceções mais específicas se as tiver no serviço
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluirCurso(@PathVariable String id) {
        try {
            cursoService.excluir(id);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) { // Trate exceções mais específicas
            return ResponseEntity.notFound().build();
        }
    }
}
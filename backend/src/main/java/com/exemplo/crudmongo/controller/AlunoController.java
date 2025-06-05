package com.exemplo.crudmongo.controller;

import com.exemplo.crudmongo.Model.Aluno;
import com.exemplo.crudmongo.service.AlunoService; // Você precisará criar este serviço
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/alunos") // ALTERADO AQUI para incluir /api
@CrossOrigin(origins = "*")
public class AlunoController {

    private final AlunoService alunoService;

    public AlunoController(AlunoService alunoService) {
        this.alunoService = alunoService;
    }

    @GetMapping // Este agora responde a GET /api/alunos
    public List<Aluno> listarTodosAlunos() {
        return alunoService.listarTodos();
    }

    @GetMapping("/{id}") // Este responde a GET /api/alunos/{id}
    public ResponseEntity<Aluno> buscarAlunoPorId(@PathVariable String id) {
        return alunoService.buscarPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping // Este agora responde a POST /api/alunos
    public ResponseEntity<Aluno> criarAluno(@RequestBody Aluno aluno) {
        Aluno novoAluno = alunoService.salvar(aluno);
        return ResponseEntity.status(HttpStatus.CREATED).body(novoAluno);
    }

    @PutMapping("/{id}") // Este responde a PUT /api/alunos/{id}
    public ResponseEntity<Aluno> atualizarAluno(@PathVariable String id, @RequestBody Aluno aluno) {
        try {
            Aluno alunoAtualizado = alunoService.atualizar(id, aluno);
            return ResponseEntity.ok(alunoAtualizado);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}") // Este responde a DELETE /api/alunos/{id}
    public ResponseEntity<Void> excluirAluno(@PathVariable String id) {
        try {
            alunoService.excluir(id);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }
}
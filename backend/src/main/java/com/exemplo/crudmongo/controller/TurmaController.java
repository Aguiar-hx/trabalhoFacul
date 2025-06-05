package com.exemplo.crudmongo.controller;

import com.exemplo.crudmongo.Model.Turma;
import com.exemplo.crudmongo.service.TurmaService; // Você precisará criar este serviço
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/turmas") // Conforme seu OpenAPI
@CrossOrigin(origins = "*") // Ajuste para produção, se necessário
public class TurmaController {

    private final TurmaService turmaService;

    public TurmaController(TurmaService turmaService) {
        this.turmaService = turmaService;
    }

    @GetMapping
    public List<Turma> listarTodasTurmas() {
        return turmaService.listarTodas();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Turma> buscarTurmaPorId(@PathVariable String id) {
        return turmaService.buscarPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Turma> criarTurma(@RequestBody Turma turma) {
        Turma novaTurma = turmaService.salvar(turma);
        return ResponseEntity.status(HttpStatus.CREATED).body(novaTurma);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Turma> atualizarTurma(@PathVariable String id, @RequestBody Turma turma) {
        try {
            // Supondo que seu TurmaService terá um método atualizar
            Turma turmaAtualizada = turmaService.atualizar(id, turma);
            return ResponseEntity.ok(turmaAtualizada);
        } catch (RuntimeException e) { // Trate exceções mais específicas se as tiver no serviço
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluirTurma(@PathVariable String id) {
        try {
            turmaService.excluir(id);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) { // Trate exceções mais específicas
            return ResponseEntity.notFound().build();
        }
    }
}
package com.exemplo.crudmongo.controller;

import com.exemplo.crudmongo.Model.Disciplina;
import com.exemplo.crudmongo.service.DisciplinaService; // You will need to create this service
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/disciplinas") // As per your OpenAPI
@CrossOrigin(origins = "*") // Adjust for production if needed
public class DisciplinaController {

    private final DisciplinaService disciplinaService;

    public DisciplinaController(DisciplinaService disciplinaService) {
        this.disciplinaService = disciplinaService;
    }

    @GetMapping
    public List<Disciplina> listarTodasDisciplinas() {
        return disciplinaService.listarTodas();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Disciplina> buscarDisciplinaPorId(@PathVariable String id) {
        return disciplinaService.buscarPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Disciplina> criarDisciplina(@RequestBody Disciplina disciplina) {
        Disciplina novaDisciplina = disciplinaService.salvar(disciplina);
        return ResponseEntity.status(HttpStatus.CREATED).body(novaDisciplina);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Disciplina> atualizarDisciplina(@PathVariable String id, @RequestBody Disciplina disciplina) {
        try {
            // Assuming your DisciplinaService will have an update method
            Disciplina disciplinaAtualizada = disciplinaService.atualizar(id, disciplina);
            return ResponseEntity.ok(disciplinaAtualizada);
        } catch (RuntimeException e) { // Or a more specific exception from your service
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluirDisciplina(@PathVariable String id) {
        try {
            disciplinaService.excluir(id);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) { // Or a more specific exception
            return ResponseEntity.notFound().build();
        }
    }
}
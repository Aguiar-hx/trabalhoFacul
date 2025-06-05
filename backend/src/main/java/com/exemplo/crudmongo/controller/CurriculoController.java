package com.exemplo.crudmongo.controller;

import com.exemplo.crudmongo.Model.Curriculo;
import com.exemplo.crudmongo.service.CurriculoService; // Você precisará criar este serviço
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/curriculos") // Conforme seu OpenAPI
@CrossOrigin(origins = "*") // Permite requisições de qualquer origem, ajuste para produção
public class CurriculoController {

    private final CurriculoService curriculoService;

    public CurriculoController(CurriculoService curriculoService) {
        this.curriculoService = curriculoService;
    }

    @GetMapping
    public List<Curriculo> listarTodosCurriculos() {
        return curriculoService.listarTodos();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Curriculo> buscarCurriculoPorId(@PathVariable String id) {
        return curriculoService.buscarPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Curriculo> criarCurriculo(@RequestBody Curriculo curriculo) {
        Curriculo novoCurriculo = curriculoService.salvar(curriculo);
        return ResponseEntity.status(HttpStatus.CREATED).body(novoCurriculo);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Curriculo> atualizarCurriculo(@PathVariable String id, @RequestBody Curriculo curriculo) {
        try {
            // Supondo que seu CurriculoService terá um método atualizar similar ao PessoaService
            // que usa os campos do novo Curriculo.java
            Curriculo curriculoAtualizado = curriculoService.atualizar(id, curriculo);
            return ResponseEntity.ok(curriculoAtualizado);
        } catch (RuntimeException e) { // Trate exceções mais específicas se as tiver no serviço
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluirCurriculo(@PathVariable String id) {
        try {
            curriculoService.excluir(id);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) { // Trate exceções mais específicas
            return ResponseEntity.notFound().build();
        }
    }
}
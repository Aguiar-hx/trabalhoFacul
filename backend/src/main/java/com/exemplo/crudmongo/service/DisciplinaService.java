package com.exemplo.crudmongo.service;

import com.exemplo.crudmongo.Model.Disciplina;
import com.exemplo.crudmongo.repository.DisciplinaRepository; // Certifique-se de que este repositório exista
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DisciplinaService {

    private final DisciplinaRepository disciplinaRepository;

    public DisciplinaService(DisciplinaRepository disciplinaRepository) {
        this.disciplinaRepository = disciplinaRepository;
    }

    public List<Disciplina> listarTodas() {
        return disciplinaRepository.findAll();
    }

    public Optional<Disciplina> buscarPorId(String id) {
        return disciplinaRepository.findById(id);
    }

    public Disciplina salvar(Disciplina disciplina) {
        // Adicionar validações ou lógica de negócios aqui antes de salvar, se necessário
        return disciplinaRepository.save(disciplina);
    }

    public Disciplina atualizar(String id, Disciplina disciplinaAtualizada) {
        return disciplinaRepository.findById(id)
            .map(disciplinaExistente -> {
                disciplinaExistente.setNome(disciplinaAtualizada.getNome());
                disciplinaExistente.setCargaHoraria(disciplinaAtualizada.getCargaHoraria());
                disciplinaExistente.setEmenta(disciplinaAtualizada.getEmenta());
                // Se você decidir adicionar mais campos ao Disciplina.java
                // (como modalidade, codigo, preRequisitos, etc.),
                // atualize-os aqui também.
                return disciplinaRepository.save(disciplinaExistente);
            })
            .orElseThrow(() -> new RuntimeException("Disciplina não encontrada com o id: " + id)); // Ou uma exceção customizada
    }

    public void excluir(String id) {
        if (!disciplinaRepository.existsById(id)) {
            throw new RuntimeException("Disciplina não encontrada com o id: " + id); // Ou uma exceção customizada
        }
        disciplinaRepository.deleteById(id);
    }
}
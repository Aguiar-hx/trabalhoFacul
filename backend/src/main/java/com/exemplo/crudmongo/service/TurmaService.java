package com.exemplo.crudmongo.service;

import com.exemplo.crudmongo.Model.Turma;
import com.exemplo.crudmongo.repository.TurmaRepository; // Certifique-se de que este repositório exista
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TurmaService {

    private final TurmaRepository turmaRepository;

    public TurmaService(TurmaRepository turmaRepository) {
        this.turmaRepository = turmaRepository;
    }

    public List<Turma> listarTodas() {
        return turmaRepository.findAll();
    }

    public Optional<Turma> buscarPorId(String id) {
        return turmaRepository.findById(id);
    }

    public Turma salvar(Turma turma) {
        // Adicionar validações ou lógica de negócios aqui antes de salvar, se necessário
        return turmaRepository.save(turma);
    }

    public Turma atualizar(String id, Turma turmaAtualizada) {
        return turmaRepository.findById(id)
            .map(turmaExistente -> {
                turmaExistente.setDisciplinaId(turmaAtualizada.getDisciplinaId());
                turmaExistente.setAno(turmaAtualizada.getAno());
                turmaExistente.setSemestre(turmaAtualizada.getSemestre());
                turmaExistente.setProfessor(turmaAtualizada.getProfessor());
                // Se você decidir adicionar mais campos ao Turma.java
                // (como codigo, status, vagas, docentes lista, horarios, etc.),
                // atualize-os aqui também.
                return turmaRepository.save(turmaExistente);
            })
            .orElseThrow(() -> new RuntimeException("Turma não encontrada com o id: " + id)); // Ou uma exceção customizada
    }

    public void excluir(String id) {
        if (!turmaRepository.existsById(id)) {
            throw new RuntimeException("Turma não encontrada com o id: " + id); // Ou uma exceção customizada
        }
        turmaRepository.deleteById(id);
    }
}
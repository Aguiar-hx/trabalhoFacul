package com.exemplo.crudmongo.service;

import com.exemplo.crudmongo.Model.Curso;
import com.exemplo.crudmongo.repository.CursoRepository; // Certifique-se de que este repositório exista
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CursoService {

    private final CursoRepository cursoRepository;

    public CursoService(CursoRepository cursoRepository) {
        this.cursoRepository = cursoRepository;
    }

    public List<Curso> listarTodos() {
        return cursoRepository.findAll();
    }

    public Optional<Curso> buscarPorId(String id) {
        return cursoRepository.findById(id);
    }

    public Curso salvar(Curso curso) {
        // Adicionar validações ou lógica de negócios aqui antes de salvar, se necessário
        return cursoRepository.save(curso);
    }

    public Curso atualizar(String id, Curso cursoAtualizado) {
        return cursoRepository.findById(id)
            .map(cursoExistente -> {
                cursoExistente.setNome(cursoAtualizado.getNome());
                cursoExistente.setNivel(cursoAtualizado.getNivel());
                cursoExistente.setModalidade(cursoAtualizado.getModalidade());
                cursoExistente.setTurno(cursoAtualizado.getTurno());
                // Se você decidir adicionar mais campos (como sede, coordenadorId, etc.),
                // atualize-os aqui também.
                return cursoRepository.save(cursoExistente);
            })
            .orElseThrow(() -> new RuntimeException("Curso não encontrado com o id: " + id)); // Ou uma exceção customizada
    }

    public void excluir(String id) {
        if (!cursoRepository.existsById(id)) {
            throw new RuntimeException("Curso não encontrado com o id: " + id); // Ou uma exceção customizada
        }
        cursoRepository.deleteById(id);
    }
}
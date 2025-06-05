package com.exemplo.crudmongo.service;

import com.exemplo.crudmongo.Model.Aluno;
import com.exemplo.crudmongo.repository.AlunoRepository; // Certifique-se de que este repositório exista
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AlunoService {

    private final AlunoRepository alunoRepository;

    public AlunoService(AlunoRepository alunoRepository) {
        this.alunoRepository = alunoRepository;
    }

    public List<Aluno> listarTodos() {
        return alunoRepository.findAll();
    }

    public Optional<Aluno> buscarPorId(String id) {
        return alunoRepository.findById(id);
    }

    public Aluno salvar(Aluno aluno) {
        // Adicionar validações ou lógica de negócios aqui antes de salvar, se necessário
        return alunoRepository.save(aluno);
    }

    public Aluno atualizar(String id, Aluno alunoAtualizado) {
        return alunoRepository.findById(id)
            .map(alunoExistente -> {
                alunoExistente.setNome(alunoAtualizado.getNome());
                alunoExistente.setIra(alunoAtualizado.getIra());
                alunoExistente.setCursoId(alunoAtualizado.getCursoId());
                alunoExistente.setPeriodoIngressoId(alunoAtualizado.getPeriodoIngressoId());
                return alunoRepository.save(alunoExistente);
            })
            .orElseThrow(() -> new RuntimeException("Aluno não encontrado com o id: " + id)); // Ou uma exceção customizada
    }

    public void excluir(String id) {
        if (!alunoRepository.existsById(id)) {
            throw new RuntimeException("Aluno não encontrado com o id: " + id); // Ou uma exceção customizada
        }
        alunoRepository.deleteById(id);
    }
}
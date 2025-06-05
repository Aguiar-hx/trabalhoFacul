package com.exemplo.crudmongo.service;

import com.exemplo.crudmongo.Model.Curriculo;
import com.exemplo.crudmongo.repository.CurriculoRepository; // Você precisará criar este repositório
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CurriculoService {

    private final CurriculoRepository curriculoRepository;

    public CurriculoService(CurriculoRepository curriculoRepository) {
        this.curriculoRepository = curriculoRepository;
    }

    public List<Curriculo> listarTodos() {
        return curriculoRepository.findAll();
    }

    public Optional<Curriculo> buscarPorId(String id) {
        return curriculoRepository.findById(id);
    }

    public Curriculo salvar(Curriculo curriculo) {
        // Adicionar validações ou lógica de negócios aqui antes de salvar, se necessário
        return curriculoRepository.save(curriculo);
    }

    public Curriculo atualizar(String id, Curriculo curriculoAtualizado) {
        return curriculoRepository.findById(id)
            .map(curriculoExistente -> {
                curriculoExistente.setCursoId(curriculoAtualizado.getCursoId());
                curriculoExistente.setAno(curriculoAtualizado.getAno());
                curriculoExistente.setSemestre(curriculoAtualizado.getSemestre());
                curriculoExistente.setDisciplinasObrigatorias(curriculoAtualizado.getDisciplinasObrigatorias());
                curriculoExistente.setDisciplinasOptativas(curriculoAtualizado.getDisciplinasOptativas());
                // Adicionar outras lógicas de atualização aqui, se houver mais campos
                return curriculoRepository.save(curriculoExistente);
            })
            .orElseThrow(() -> new RuntimeException("Currículo não encontrado com o id: " + id)); // Ou uma exceção customizada
    }

    public void excluir(String id) {
        if (!curriculoRepository.existsById(id)) {
            throw new RuntimeException("Currículo não encontrado com o id: " + id); // Ou uma exceção customizada
        }
        curriculoRepository.deleteById(id);
    }
}
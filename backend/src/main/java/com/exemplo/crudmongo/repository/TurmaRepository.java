package com.exemplo.crudmongo.repository;

import com.exemplo.crudmongo.Model.Turma;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository // Opcional para interfaces que estendem MongoRepository, mas boa prática
public interface TurmaRepository extends MongoRepository<Turma, String> {
    // Você pode adicionar métodos de query customizados aqui se precisar no futuro
    // Exemplo: List<Turma> findByDisciplinaId(String disciplinaId);
    // Exemplo: List<Turma> findByAnoAndSemestre(Integer ano, Integer semestre);
}
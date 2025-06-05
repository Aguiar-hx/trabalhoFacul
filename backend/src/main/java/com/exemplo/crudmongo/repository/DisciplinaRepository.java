package com.exemplo.crudmongo.repository;

import com.exemplo.crudmongo.Model.Disciplina;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository // Optional for interfaces extending MongoRepository, but good practice
public interface DisciplinaRepository extends MongoRepository<Disciplina, String> {
    // You can add custom query methods here if needed in the future
    // Example: List<Disciplina> findByNomeContainingIgnoreCase(String nome);
    // Example: List<Disciplina> findByCargaHorariaGreaterThan(Integer cargaHoraria);
}
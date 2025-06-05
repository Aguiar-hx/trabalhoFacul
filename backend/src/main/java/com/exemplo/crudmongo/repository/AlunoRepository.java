package com.exemplo.crudmongo.repository;

import com.exemplo.crudmongo.Model.Aluno;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository // Opcional para interfaces que estendem MongoRepository, mas boa prática
public interface AlunoRepository extends MongoRepository<Aluno, String> {
    // Você pode adicionar métodos de query customizados aqui se precisar no futuro
    // Exemplo: List<Aluno> findByNomeContainingIgnoreCase(String nome);
    // Exemplo: List<Aluno> findByCursoId(String cursoId);
    // Exemplo: List<Aluno> findByIraGreaterThan(Double ira);
}
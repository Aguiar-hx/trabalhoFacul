package com.exemplo.crudmongo.repository;

import com.exemplo.crudmongo.Model.Curso;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository // Opcional para interfaces que estendem MongoRepository, mas boa prática
public interface CursoRepository extends MongoRepository<Curso, String> {
    // Você pode adicionar métodos de query customizados aqui se precisar no futuro
    // Exemplo: List<Curso> findByNome(String nome);
    // Exemplo: List<Curso> findByNivel(String nivel);
}
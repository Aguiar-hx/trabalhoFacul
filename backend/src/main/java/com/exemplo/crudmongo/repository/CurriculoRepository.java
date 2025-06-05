package com.exemplo.crudmongo.repository;

import com.exemplo.crudmongo.Model.Curriculo;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository // Opcional para interfaces que estendem MongoRepository, mas boa prática
public interface CurriculoRepository extends MongoRepository<Curriculo, String> {
    // Você pode adicionar métodos de query customizados aqui se precisar no futuro
    // Exemplo: List<Curriculo> findByCursoId(String cursoId);
    // Exemplo: List<Curriculo> findByAno(Integer ano);
}

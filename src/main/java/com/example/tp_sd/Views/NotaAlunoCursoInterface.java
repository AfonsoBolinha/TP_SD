package com.example.tp_sd.Views;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.Collection;
import java.util.List;

public interface NotaAlunoCursoInterface extends CrudRepository<NotaAlunoCursoEntity, Integer>{
    @Query(
            value = "SELECT * FROM nota_aluno_curso u WHERE u.Id = :userId",
            nativeQuery = true)
    Collection<NotaAlunoCursoEntity> minhasNotas(@Param("userId") Integer userId);
}
package com.example.tp_sd.Views;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.Collection;

public interface AlunocursoInterface extends CrudRepository<AlunocursoEntity, Integer> {
    @Query(
            value = "SELECT * FROM alunocurso u WHERE u.ID_Curso = :idCurso",
            nativeQuery = true)
    Collection<AlunocursoEntity> alunos(@Param("idCurso") int idCurso);

}

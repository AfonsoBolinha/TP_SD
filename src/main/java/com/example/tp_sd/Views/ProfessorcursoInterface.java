package com.example.tp_sd.Views;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.Collection;

public interface ProfessorcursoInterface extends CrudRepository<ProfessorcursoEntity, Integer> {
    @Query(
            value = "SELECT * FROM professorcurso u WHERE u.ID_Aluno = :userId",
            nativeQuery = true)
    Collection<ProfessorcursoEntity> minhasTurmas(@Param("userId") int userId);
}

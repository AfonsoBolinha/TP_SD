package com.example.tp_sd.Tabelas;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.ArrayList;
import java.util.List;

public interface CursoInterface extends CrudRepository<CursoEntity, Integer> {


}

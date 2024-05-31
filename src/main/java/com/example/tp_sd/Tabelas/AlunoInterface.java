package com.example.tp_sd.Tabelas;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface AlunoInterface extends CrudRepository<AlunoEntity, Integer>{
    AlunoEntity findByContacto(String contacto);
    //@Query("SELECT a FROM AlunoEntity a WHERE a.status = 2")
    List<AlunoEntity> findByStatus(Integer status);
}

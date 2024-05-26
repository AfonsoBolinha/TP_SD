package com.example.tp_sd.Tabelas;
import org.springframework.data.repository.CrudRepository;

public interface AlunoInterface extends CrudRepository<AlunoEntity, Integer>{
    AlunoEntity findByContacto(String contacto);
}

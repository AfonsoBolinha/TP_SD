package com.example.tp_sd;
import com.example.tp_sd.Tabelas.*;
import com.example.tp_sd.Tabelas.AlunoEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AlunoService {

    @Autowired
    private AlunoInterface alunoInterface;

    public boolean authenticate(String contacto, String password) {
        AlunoEntity aluno = alunoInterface.findByContacto(contacto);
        return aluno != null && aluno.getPassword().equals(password);
    }
}

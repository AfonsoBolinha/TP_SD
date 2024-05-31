package com.example.tp_sd;
import com.example.tp_sd.Tabelas.*;
import com.example.tp_sd.Tabelas.AlunoEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AlunoService {

    @Autowired
    private AlunoInterface alunoInterface;
    BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

    public boolean authenticate(String contacto, String password) {
        AlunoEntity aluno = alunoInterface.findByContacto(contacto);
        if (aluno != null) {
            return encoder.matches(password, aluno.getPassword());
        }
        return false;
    }

    public AlunoEntity getAluno(String contacto) {
        return alunoInterface.findByContacto(contacto);
    }
}

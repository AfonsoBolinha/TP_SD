package com.example.tp_sd;

import com.example.tp_sd.Tabelas.AlunoEntity;
import com.example.tp_sd.Tabelas.AlunoInterface;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Service
public class AdminService {
    private final AlunoInterface alunoInterface;
    BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

    public AdminService(AlunoInterface alunoInterface) {
        this.alunoInterface = alunoInterface;
    }

    public void createAdmin() {
        if(alunoInterface.findByContacto("000000000") == null) {
            AlunoEntity admin = new AlunoEntity();
            admin.setContacto("000000000");
            admin.setNome("Admin");
            admin.setProviniência("Admin Place");
            admin.setPassword(encoder.encode("admin"));
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd"); // Formato para data sem hora
            String dataNascimento = "0000-00-00";
            Date parsedDate;
            try {
                parsedDate = dateFormat.parse(dataNascimento);
            } catch (ParseException e) {
                throw new RuntimeException(e);
            }
            Timestamp timestamp = new Timestamp(parsedDate.getTime());
            admin.setDataNascimento(timestamp);
            admin.setStatus(3);
            alunoInterface.save(admin);
        }
    }
}

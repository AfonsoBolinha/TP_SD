package com.example.tp_sd.Views;

import jakarta.persistence.*;
import org.springframework.data.annotation.Immutable;

@Entity
@Immutable
@Table(name = "professorcurso", schema = "db_escolajardinagem", catalog = "")
public class ProfessorcursoEntity {
    @Basic
    @Column(name = "ID_Aluno")
    private int idAluno;
    @Basic
    @Column(name = "ID_Curso")
    private int idCurso;
    @Basic
    @Column(name = "Nome")
    private String nome;
    @Basic
    @Id
    @Column(name = "ID_REAL")
    private int idReal;

    public int getIdAluno() {
        return idAluno;
    }


    public int getIdCurso() {
        return idCurso;
    }


    public String getNome() {
        return nome;
    }


    public Object getIdReal() {
        return idReal;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ProfessorcursoEntity that = (ProfessorcursoEntity) o;

        if (idAluno != that.idAluno) return false;
        if (idCurso != that.idCurso) return false;
        if (nome != null ? !nome.equals(that.nome) : that.nome != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = idAluno;
        result = 31 * result + idCurso;
        result = 31 * result + (nome != null ? nome.hashCode() : 0);
        return result;
    }
}

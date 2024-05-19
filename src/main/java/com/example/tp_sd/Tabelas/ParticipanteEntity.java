package com.example.tp_sd.Tabelas;

import jakarta.persistence.*;

@Entity
@Table(name = "participante", schema = "db_escolajardinagem", catalog = "")
public class ParticipanteEntity {
    @Id
    private long id;
    @Basic
    @Column(name = "ID_Aluno")
    private int idAluno;
    @Basic
    @Column(name = "ID_Curso")
    private int idCurso;

    public int getIdAluno() {
        return idAluno;
    }

    public void setIdAluno(int idAluno) {
        this.idAluno = idAluno;
    }

    public int getIdCurso() {
        return idCurso;
    }

    public void setIdCurso(int idCurso) {
        this.idCurso = idCurso;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ParticipanteEntity that = (ParticipanteEntity) o;

        if (idAluno != that.idAluno) return false;
        if (idCurso != that.idCurso) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = idAluno;
        result = 31 * result + idCurso;
        return result;
    }
}

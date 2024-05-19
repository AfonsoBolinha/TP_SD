package com.example.tp_sd.Tabelas;

import jakarta.persistence.*;

@Entity
@Table(name = "nota", schema = "db_escolajardinagem", catalog = "")
public class NotaEntity {
    @Id
    private long id;

    @Basic
    @Column(name = "ID_Aluno")
    private int idAluno;
    @Basic
    @Column(name = "ID_Curso")
    private int idCurso;
    @Basic
    @Column(name = "Nota")
    private double nota;

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

    public double getNota() {
        return nota;
    }

    public void setNota(double nota) {
        this.nota = nota;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        NotaEntity that = (NotaEntity) o;

        if (idAluno != that.idAluno) return false;
        if (idCurso != that.idCurso) return false;
        if (Double.compare(that.nota, nota) != 0) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = idAluno;
        result = 31 * result + idCurso;
        temp = Double.doubleToLongBits(nota);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        return result;
    }
}

package com.example.tp_sd.Views;

import jakarta.persistence.*;
import org.hibernate.annotations.Immutable;

@Entity
@Immutable
@Table(name = "nota_aluno_curso", schema = "db_escolajardinagem", catalog = "")
public class NotaAlunoCursoEntity {
    @Basic
    @Column(name = "ID_Aluno", nullable = false)
    private Integer idAluno;
    @Basic
    @Column(name = "Aluno_Nome", nullable = false, length = 45)
    private String alunoNome;
    @Basic
    @Column(name = "Curso_Nome", nullable = false, length = 45)
    private String cursoNome;
    @Basic
    @Column(name = "Nota", nullable = false, precision = 0)
    private double nota;
    @Basic
    @Id
    @Column(name = "Id", nullable = false)
    private int id;

    public Integer getIdAluno() {
        return idAluno;
    }


    public String getAlunoNome() {
        return alunoNome;
    }


    public String getCursoNome() {
        return cursoNome;
    }


    public double getNota() {
        return nota;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        NotaAlunoCursoEntity that = (NotaAlunoCursoEntity) o;

        if (Double.compare(that.nota, nota) != 0) return false;
        if (alunoNome != null ? !alunoNome.equals(that.alunoNome) : that.alunoNome != null) return false;
        if (cursoNome != null ? !cursoNome.equals(that.cursoNome) : that.cursoNome != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = alunoNome != null ? alunoNome.hashCode() : 0;
        result = 31 * result + (cursoNome != null ? cursoNome.hashCode() : 0);
        temp = Double.doubleToLongBits(nota);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        return result;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}

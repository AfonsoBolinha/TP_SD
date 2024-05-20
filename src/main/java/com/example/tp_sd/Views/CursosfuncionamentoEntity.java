package com.example.tp_sd.Views;

import jakarta.persistence.*;
import org.hibernate.annotations.Immutable;

@Entity
@Immutable
@Table(name = "cursosfuncionamento", schema = "db_escolajardinagem", catalog = "")
public class CursosfuncionamentoEntity {
    @Id
    private int id;

    @Basic
    @Column(name = "Nome")
    private String nome;

    public String getNome() {
        return nome;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CursosfuncionamentoEntity that = (CursosfuncionamentoEntity) o;

        if (nome != null ? !nome.equals(that.nome) : that.nome != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return nome != null ? nome.hashCode() : 0;
    }
}

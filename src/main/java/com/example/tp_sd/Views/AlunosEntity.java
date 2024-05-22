package com.example.tp_sd.Views;

import jakarta.persistence.*;
import org.springframework.data.annotation.Immutable;

@Entity
@Immutable
@Table(name = "alunos", schema = "db_escolajardinagem", catalog = "")
public class AlunosEntity {
    @Basic
    @Id
    @Column(name = "ID")
    private int id;
    @Basic
    @Column(name = "Nome")
    private String nome;

    public int getId() {
        return id;
    }


    public String getNome() {
        return nome;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        AlunosEntity that = (AlunosEntity) o;

        if (id != that.id) return false;
        if (nome != null ? !nome.equals(that.nome) : that.nome != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (nome != null ? nome.hashCode() : 0);
        return result;
    }
}

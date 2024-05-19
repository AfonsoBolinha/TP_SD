package com.example.tp_sd.Views;

import jakarta.persistence.*;
import org.hibernate.annotations.Immutable;

@Entity
@Immutable
@Table(name = "idademedia", schema = "db_escolajardinagem", catalog = "")
public class IdademediaEntity {
    @Id
    private long id;

    @Basic
    @Column(name = "Nome")
    private String nome;
    @Basic
    @Column(name = "Idade")
    private Integer idade;

    public String getNome() {
        return nome;
    }


    public Integer getIdade() {
        return idade;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        IdademediaEntity that = (IdademediaEntity) o;

        if (nome != null ? !nome.equals(that.nome) : that.nome != null) return false;
        if (idade != null ? !idade.equals(that.idade) : that.idade != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = nome != null ? nome.hashCode() : 0;
        result = 31 * result + (idade != null ? idade.hashCode() : 0);
        return result;
    }
}

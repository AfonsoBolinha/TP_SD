package com.example.tp_sd.Views;

import jakarta.persistence.*;

@Entity
@Table(name = "naprovacoes", schema = "db_escolajardinagem", catalog = "")
public class NaprovacoesEntity {
    @Id
    @Basic
    @Column(name = "ID")
    private int id;
    @Basic
    @Column(name = "nome")
    private String nome;
    @Basic
    @Column(name = "Positivas")
    private long positivas;

    public int getId() {
        return id;
    }


    public String getNome() {
        return nome;
    }


    public long getPositivas() {
        return positivas;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        NaprovacoesEntity that = (NaprovacoesEntity) o;

        if (id != that.id) return false;
        if (positivas != that.positivas) return false;
        if (nome != null ? !nome.equals(that.nome) : that.nome != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (nome != null ? nome.hashCode() : 0);
        result = 31 * result + (int) (positivas ^ (positivas >>> 32));
        return result;
    }
}

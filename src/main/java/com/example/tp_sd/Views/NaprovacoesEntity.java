package com.example.tp_sd.Views;

import jakarta.persistence.*;
import org.hibernate.annotations.Immutable;

@Entity
@Immutable
@Table(name = "naprovacoes", schema = "db_escolajardinagem", catalog = "")
public class NaprovacoesEntity {
    @Id
    private long idA;
    @Basic
    @Column(name = "ID")
    private int id;
    @Basic
    @Column(name = "Positivas")
    private long positivas;

    public int getId() {
        return id;
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

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (int) (positivas ^ (positivas >>> 32));
        return result;
    }
}

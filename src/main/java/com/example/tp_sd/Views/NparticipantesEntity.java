package com.example.tp_sd.Views;

import jakarta.persistence.*;
import org.springframework.data.annotation.Immutable;

@Entity
@Immutable
@Table(name = "nparticipantes", schema = "db_escolajardinagem", catalog = "")
public class NparticipantesEntity {
    @Id
    private long id;

    @Basic
    @Column(name = "Nome")
    private String nome;
    @Basic
    @Column(name = "NParticipantes")
    private long nParticipantes;
    @Basic
    @Column(name = "Ano")
    private Integer ano;

    public String getNome() {
        return nome;
    }


    public long getnParticipantes() {
        return nParticipantes;
    }


    public Integer getAno() {
        return ano;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        NparticipantesEntity that = (NparticipantesEntity) o;

        if (nParticipantes != that.nParticipantes) return false;
        if (nome != null ? !nome.equals(that.nome) : that.nome != null) return false;
        if (ano != null ? !ano.equals(that.ano) : that.ano != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = nome != null ? nome.hashCode() : 0;
        result = 31 * result + (int) (nParticipantes ^ (nParticipantes >>> 32));
        result = 31 * result + (ano != null ? ano.hashCode() : 0);
        return result;
    }
}

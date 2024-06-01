package com.example.tp_sd.Views;

import jakarta.persistence.*;
import org.springframework.data.annotation.Immutable;

import java.sql.Timestamp;

@Entity
@Immutable
@Table(name = "alunocurso", schema = "db_escolajardinagem", catalog = "")
public class AlunocursoEntity {
    @Basic
    @Column(name = "Nome")
    private String nome;
    @Basic
    @Column(name = "Contacto")
    private String contacto;
    @Basic
    @Column(name = "DataNascimento")
    private Timestamp dataNascimento;
    @Basic
    @Column(name = "Proviniência")
    private String proviniência;
    @Basic
    @Column(name = "ID_Curso")
    private int idCurso;

    @Basic
    @Id
    @Column(name = "RowNum")
    private int rowNum;

    public String getNome() {
        return nome;
    }


    public String getContacto() {
        return contacto;
    }


    public Timestamp getDataNascimento() {
        return dataNascimento;
    }


    public String getProviniência() {
        return proviniência;
    }


    public int getIdCurso() {
        return idCurso;
    }


    public int getRowNum() {
        return rowNum;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        AlunocursoEntity that = (AlunocursoEntity) o;

        if (idCurso != that.idCurso) return false;
        if (nome != null ? !nome.equals(that.nome) : that.nome != null) return false;
        if (contacto != null ? !contacto.equals(that.contacto) : that.contacto != null) return false;
        if (dataNascimento != null ? !dataNascimento.equals(that.dataNascimento) : that.dataNascimento != null)
            return false;
        if (proviniência != null ? !proviniência.equals(that.proviniência) : that.proviniência != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = nome != null ? nome.hashCode() : 0;
        result = 31 * result + (contacto != null ? contacto.hashCode() : 0);
        result = 31 * result + (dataNascimento != null ? dataNascimento.hashCode() : 0);
        result = 31 * result + (proviniência != null ? proviniência.hashCode() : 0);
        result = 31 * result + idCurso;
        return result;
    }
}

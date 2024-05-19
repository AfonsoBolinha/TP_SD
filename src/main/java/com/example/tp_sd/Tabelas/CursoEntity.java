package com.example.tp_sd.Tabelas;

import jakarta.persistence.*;

@Entity
@Table(name = "curso", schema = "db_escolajardinagem", catalog = "")
public class CursoEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "ID")
    private int id;
    @Basic
    @Column(name = "Nome")
    private String nome;
    @Basic
    @Column(name = "Ano")
    private Integer ano;
    @Basic
    @Column(name = "ID_Professor")
    private Integer idProfessor;
    @Basic
    @Column(name = "NHoras")
    private Integer nHoras;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Integer getAno() {
        return ano;
    }

    public void setAno(Integer ano) {
        this.ano = ano;
    }

    public Integer getIdProfessor() {
        return idProfessor;
    }

    public void setIdProfessor(Integer idProfessor) {
        this.idProfessor = idProfessor;
    }

    public Integer getnHoras() {
        return nHoras;
    }

    public void setnHoras(Integer nHoras) {
        this.nHoras = nHoras;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CursoEntity that = (CursoEntity) o;

        if (id != that.id) return false;
        if (nome != null ? !nome.equals(that.nome) : that.nome != null) return false;
        if (ano != null ? !ano.equals(that.ano) : that.ano != null) return false;
        if (idProfessor != null ? !idProfessor.equals(that.idProfessor) : that.idProfessor != null) return false;
        if (nHoras != null ? !nHoras.equals(that.nHoras) : that.nHoras != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (nome != null ? nome.hashCode() : 0);
        result = 31 * result + (ano != null ? ano.hashCode() : 0);
        result = 31 * result + (idProfessor != null ? idProfessor.hashCode() : 0);
        result = 31 * result + (nHoras != null ? nHoras.hashCode() : 0);
        return result;
    }
}

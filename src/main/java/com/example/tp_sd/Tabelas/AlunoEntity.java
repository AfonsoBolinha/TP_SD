package com.example.tp_sd.Tabelas;

import jakarta.persistence.*;

import java.sql.Timestamp;

@Entity
@Table(name = "aluno", schema = "db_escolajardinagem", catalog = "")
public class AlunoEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "ID")
    private int id;
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
    @Column(name = "Status")
    private Integer status;
    @Column(name = "Password")
    private String password;

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

    public String getContacto() {
        return contacto;
    }

    public void setContacto(String contacto) {
        this.contacto = contacto;
    }

    public Timestamp getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(Timestamp dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public String getProviniência() {
        return proviniência;
    }

    public void setProviniência(String proviniência) {
        this.proviniência = proviniência;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public void setPassword(String password) {this.password = password; }

    public String getPassword() { return password; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        AlunoEntity that = (AlunoEntity) o;

        if (id != that.id) return false;
        if (nome != null ? !nome.equals(that.nome) : that.nome != null) return false;
        if (contacto != null ? !contacto.equals(that.contacto) : that.contacto != null) return false;
        if (dataNascimento != null ? !dataNascimento.equals(that.dataNascimento) : that.dataNascimento != null)
            return false;
        if (proviniência != null ? !proviniência.equals(that.proviniência) : that.proviniência != null) return false;
        if (status != null ? !status.equals(that.status) : that.status != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (nome != null ? nome.hashCode() : 0);
        result = 31 * result + (contacto != null ? contacto.hashCode() : 0);
        result = 31 * result + (dataNascimento != null ? dataNascimento.hashCode() : 0);
        result = 31 * result + (proviniência != null ? proviniência.hashCode() : 0);
        result = 31 * result + (status != null ? status.hashCode() : 0);
        return result;
    }
}

package com.example.tp_sd.Views;

import jakarta.persistence.*;
import org.springframework.data.annotation.Immutable;

@Entity
@Immutable
@Table(name = "proveniencias", schema = "db_escolajardinagem", catalog = "")
public class ProvenienciasEntity {
    @Basic
    @Column(name = "Proviniência")
    private String proviniência;

    @Id
    @Column(name = "ID")
    private long id;

    public String getProviniência() {
        return proviniência;
    }


    public long getId() {
        return id;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ProvenienciasEntity that = (ProvenienciasEntity) o;

        if (id != that.id) return false;
        if (proviniência != null ? !proviniência.equals(that.proviniência) : that.proviniência != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = proviniência != null ? proviniência.hashCode() : 0;
        result = 31 * result + (int) (id ^ (id >>> 32));
        return result;
    }
}

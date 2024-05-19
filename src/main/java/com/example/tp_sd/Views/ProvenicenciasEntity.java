package com.example.tp_sd.Views;

import jakarta.persistence.*;
import org.hibernate.annotations.Immutable;

@Entity
@Immutable
@Table(name = "provenicencias", schema = "db_escolajardinagem", catalog = "")
public class ProvenicenciasEntity {
    @Id
    private long id;
    @Basic
    @Column(name = "Proviniência")
    private String proviniência;

    public String getProviniência() {
        return proviniência;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ProvenicenciasEntity that = (ProvenicenciasEntity) o;

        if (proviniência != null ? !proviniência.equals(that.proviniência) : that.proviniência != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return proviniência != null ? proviniência.hashCode() : 0;
    }
}

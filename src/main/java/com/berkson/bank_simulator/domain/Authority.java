package com.berkson.bank_simulator.domain;

import jakarta.persistence.AttributeOverride;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;

import java.io.Serializable;
import java.util.Objects;

/**
 * Created By : Berkson Ximenes
 * Date : 06/12/2024
 **/

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "authorizations")
@AttributeOverride(name = "id", column = @Column(name = "authority_id"))
public class Authority extends BaseEntity implements GrantedAuthority, Serializable {

    @Column(name = "authority", nullable = false)
    private String authority;
    @Column(nullable = false)
    private String description;

    public Authority(Long id, String authority, String description) {
        super(id);
        this.authority = authority;
        this.description = description;
    }

    public Authority(String authority, String description) {
        this.authority = authority;
        this.description = description;
    }

    @Override
    public String getAuthority() {
        return authority;
    }

    @Override
    public int hashCode() {
        return Objects.hash(authority);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;

        if (!(obj instanceof Authority a)) return false;

        return Objects.equals(this.authority, a.authority);
    }
}
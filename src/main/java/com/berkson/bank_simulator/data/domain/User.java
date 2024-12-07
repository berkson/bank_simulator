package com.berkson.bank_simulator.data.domain;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.io.Serializable;
import java.text.Collator;
import java.util.*;

/**
 * Created By : Berkson Ximenes
 * Date : 06/12/2024
 **/

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "users")
@AttributeOverride(name = "id", column = @Column(name = "user_id"))
public class User extends AuditableEntity implements UserDetails, Serializable, Comparable<User> {

    @Column(name = "username", nullable = false)
    private String username;
    @Column(name = "age")
    private int age;
    @Column(name = "fullname", nullable = false)
    private String name;
    @Column(name = "password", nullable = false)
    @Getter(value = AccessLevel.NONE)
    private String password;
    @Column(name = "email", nullable = false)
    private String email;
    @OneToOne
    @JoinColumn(name = "account_id")
    private Account account;
    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(name = "permissions", joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "authority_id", referencedColumnName = "authority_id"))
    private List<Authority> authorities = new ArrayList<>();


    @Override
    public int compareTo(@NonNull User user) {
        Collator collator = Collator.getInstance(Locale.getDefault());
        collator.setStrength(Collator.PRIMARY);
        try {
            return collator.compare(this.name, user.name);
        } catch (Exception e) {
            return 0;
        }
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.authorities;
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.getId(), username);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;

        if (!(obj instanceof User user))
            return false;

        return Objects.equals(this.getId(), user.getId()) && Objects.equals(this.username, user.username);
    }
}

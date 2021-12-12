package com.neevin.lab4.models;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.Collection;
import java.util.List;

@Entity
@Table(name="users")
public class User implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String username;
    private String password;

    @JoinColumn(name = "points")
    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Point> points;


    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true; // TODO Rewrite
    }

    @Override
    public boolean isAccountNonLocked() {
        return true; // TODO Rewrite
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;  // TODO Rewrite
    }

    @Override
    public boolean isEnabled() {
        return true; // TODO Rewrite
    }

    // My
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setUsername(String login) {
        this.username = login;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<Point> getPoints() {
        return points;
    }

    public void setPoints(List<Point> points) {
        this.points = points;
    }
}

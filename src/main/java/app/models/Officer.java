package app.models;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.Collections;


@Entity
public class Officer implements UserDetails {

    @Id
    @GeneratedValue
    private Long id;

    @Column(unique = true)
    private String username;

    private String password;

    private LocalDateTime workStart;

    private LocalDateTime workEnd;


    public Officer() {}

    public String getUsername() {
        return username;
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

    public boolean isEnable() {
        return workEnd == null;
    }

    private GrantedAuthority createOneAuthority() {
        return new SimpleGrantedAuthority("ADMIN");
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.singletonList(createOneAuthority());
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public LocalDateTime getWorkStart() {
        return workStart;
    }

    public void setWorkStart(LocalDateTime workStart) {
        this.workStart = workStart;
    }

    public LocalDateTime getWorkEnd() {
        return workEnd;
    }

    public void setWorkEnd(LocalDateTime workEnd) {
        this.workEnd = workEnd;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }
}

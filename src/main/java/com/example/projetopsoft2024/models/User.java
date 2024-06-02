package com.example.projetopsoft2024.models;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;


@Schema(description = "User")
@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name="userprofile")
public class User implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long readernumber;

    @Column(name = "name", nullable = false, unique = false, updatable = false)
    private String name;

    @Email(message = "O email deve estar em um formato válido")
    @Column(name = "email", nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String password;

    @Enumerated(EnumType.STRING)
    private RoleUser role;

    @Column(name = "dateofbirth")
    private Date dateofbirth;

    @Column(name = "phonenumber", nullable = false)
    private Long phonenumber;

    @Column(name = "gdprconsent", nullable = true)
    private String gdprconsent;

    @Column(name = "list_interess", nullable = true)
    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(
            name = "user_genre",
            joinColumns = @JoinColumn(name = "reader_id"),
            inverseJoinColumns = @JoinColumn(name = "gender_id")
    )
    private List<Gender> genres = new ArrayList<>();

    @Column(name = "funny_quote")
    private String funnyQuote;


    public User(final String name, final String email, final Date dateofbirth, final Long phonenumber, final Long readernumber, final String gdprconsent,final String password, RoleUser role,final String funnyQuote) {
        setName(name);
        setEmail(email);
        setDateofbirth(dateofbirth);
        setPhonenumber(phonenumber);
        setReadernumber(readernumber);
        setGdprconsent(gdprconsent);
        setPassword(password);
        setRole(role);
        setFunnyQuote(funnyQuote);

    }
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        if(this.role== RoleUser.LIBRARIAN) return List.of(new SimpleGrantedAuthority("ROLE_LIBRARIAN"), new SimpleGrantedAuthority("ROLE_READER") );
        else return List.of(new SimpleGrantedAuthority("ROLE_READER"));
    }

    @Override
    public String getUsername() {
        return email;
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
}



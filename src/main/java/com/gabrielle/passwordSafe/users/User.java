package com.gabrielle.passwordSafe.users;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonView;
import com.gabrielle.passwordSafe.passwords.Password;
import com.gabrielle.passwordSafe.users.controllers.UserCreationDTO;
import com.gabrielle.passwordSafe.users.controllers.View;

@Entity
@Table(name="users")
public class User {
    @Id
    @Column(name = "usr_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonView({ View.User.class })
    private Integer id;

    @Column(name = "usr_name")
    @JsonView({ View.User.class })
    private String name;

    @Column(name = "usr_email")
    @JsonView({ View.User.class })
    private String email;

    @Column(name = "usr_master_password")
    @JsonView(View.User.class)
    private String masterPassword;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "user")
    public Set<Password> passwords;

    public static User create(UserCreationDTO userDTO) {
        User user = new User(userDTO.name, userDTO.email,  userDTO.masterPassword);
        user.passwords.add(userDTO.password);
        return user;
    }
    public User() {}

    public User(String name, String email, String masterPassword) {
        this.name = name;
        this.email = email;
        this.masterPassword = masterPassword;
        this.passwords = new HashSet<>();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMasterPassword() {
        return masterPassword;
    }

    public void setMasterPassword(String masterPassword) {
        this.masterPassword = masterPassword;
    }
}

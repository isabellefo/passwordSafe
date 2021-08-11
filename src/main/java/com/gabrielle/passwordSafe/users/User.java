package com.gabrielle.passwordSafe.users;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="users")
public class User {
    @Id
    private String name;

    @Column
    private String email;

    @Column
    private String master_password;

    public User(String name,String  email,String  master_password) {
        this.name = name;
        this.email = email;
        this.master_password = master_password;
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

    public String getMaster_password() {
        return master_password;
    }

    public void setMaster_password(String master_password) {
        this.master_password = master_password;
    }
}

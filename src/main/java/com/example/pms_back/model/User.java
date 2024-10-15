package com.example.pms_back.model;

import jakarta.persistence.*;

@Entity
@Table(name = "user", schema = "public")
public class User{

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name="id")
    private String id;

    @Column(name="name")
    private String name;

    @Column(name="password")
    private String password;

    @Column(name="email")
    private String email;

    public User() {}

    public User(String id, String name, String password, String email) {
        this.id = id;
        this.name = name;
        this.password = password;
        this.email = email;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public User fill(User user) {
        if (user.getName() != null)
            this.setName(user.getName());
        if (user.getPassword() != null)
            this.setPassword(user.getPassword());
        if (user.getEmail() != null)
            this.setEmail(user.getEmail());

        return this;
    }
}


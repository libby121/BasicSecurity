package com.example.basicSecurity.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import org.springframework.boot.autoconfigure.domain.EntityScan;

import java.util.UUID;

@Entity
@Table(name="users")
public class User {
    public User(String password, int id, String name) {
        this.password = password;
        this.id = id;
        this.name = name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    @Id
    private int id;
    private String password;

    public void setPassword(String password) {
        this.password = password;
    }

    private String name;

    public int getId() {
        return id;
    }

    public User(int id) {
        this.id = id;
    }

    public void setId(int id) {
        this.id = id;


    }

    public User(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public User() {
    }

    public String getName() {
        return name;
    }
}

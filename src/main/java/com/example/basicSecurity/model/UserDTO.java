package com.example.basicSecurity.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.UUID;

@Data
@NoArgsConstructor
public class UserDTO implements Serializable {

    private int id;
    private String name;
    private String password;

    public int getId(){
        return id;
    }
    public String getName(){
        return name;
    }
    public String getPassword(){
        return password;
    }
    public void setId(int id){
       this.id=id;
    }
    public void setName(String name){
        this.name=name;
    }

    public void setPassword(String password){
        this.password=password;
    }
}

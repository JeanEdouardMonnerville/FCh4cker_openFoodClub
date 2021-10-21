package cat.tecnocampus.courseproject.application.dtos;

import cat.tecnocampus.courseproject.domain.Order;
import cat.tecnocampus.courseproject.domain.Role;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class CostumerDTO {
    private String id;
    private String name;
    private String secondName;
    private String email;
    private String password;
    public Role role;

    public CostumerDTO() {
        this.id=UUID.randomUUID().toString();
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getSecondName() {
        return secondName;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public Role getRole() {
        return role;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSecondName(String secondName) {
        this.secondName = secondName;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setRole(Role role) {
        this.role = role;
    }
    
    
}

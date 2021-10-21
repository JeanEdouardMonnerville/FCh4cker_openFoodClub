package cat.tecnocampus.courseproject.domain;

import cat.tecnocampus.courseproject.application.dtos.OrderDTO;

import java.util.UUID;

public class Costumer {

    private String id;
    private String name;
    private String secondName;
    private String email;
    private String password;
    public Role role;

    public Costumer(String name, String secondName, String email,String password, Role role ) {
        this.id = UUID.randomUUID().toString();
        this.name = name;
        this.secondName = secondName;
        this.email = email;
        this.password=password;
        this.role=role;
    }

    public Costumer() {
    }
    
    public Role getRole() {
        return role;
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

    public String getSecondName() {
        return secondName;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public String getPassword() {
        return password;
    }

    public void setSecondName(String secondName) {
        this.secondName = secondName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}

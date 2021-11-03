package cat.tecnocampus.courseproject.application.dtos;



import java.util.UUID;


public class CustomerDTO {
    private String id;
    private String name;
    private String secondName;
    private String email;


    public CustomerDTO() {
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

    
    
}

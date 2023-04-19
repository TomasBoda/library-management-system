package main.model;

public class User {

    private String id;
    private String name;
    private String email;
    private String password;
    private int admin;

    public User(String id, String name, String email, String password, int admin) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.password = password;
        this.admin = admin;
    }

    public User(String name, String email, String password, int admin) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.admin = admin;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public int getAdmin() {
        return admin;
    }
}

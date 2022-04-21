package com.example.kata_pp_3_1_1.model;


import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;
    @Column(name = "name")
    @NotNull(message = "Поле \"Имя\" не должно быть пустым")
    @Size(min = 2, max = 45, message = "Имя должно быть не менее 2 символов и не должно превышать 45 символов")
    private String name;
    @Column(name = "last_name")
    @NotEmpty(message = "Поле \"Фамилия\" не должно быть пустым")
    @Size(min = 2, max = 45, message = "Фамилия должна быть не менее 2 символов и не должна превышать 45 символов")
    private String lastName;
    @Column(name = "email")
    @NotEmpty(message = "Поле email не должно быть пустым")
    @Email(message = "Некорректный email")
    private String email;

    public User() {
    }

    public User(String name, String lastName, String email) {
        this.name = name;
        this.lastName = lastName;
        this.email = email;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "id=" + id +
                ", name=" + name +
                ", lastName=" + lastName +
                ", email=" + email;
    }
}

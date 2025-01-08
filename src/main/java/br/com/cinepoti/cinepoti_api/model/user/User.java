package br.com.cinepoti.cinepoti_api.model.user;


import br.com.cinepoti.cinepoti_api.enums.Gender;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

@Entity
@Table(name = "CP_USER")

public class User implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "Login is required")
    @Size(min = 3, max = 50, message = "login must be between 3 and 50 characters")
    @Column(nullable = false, unique = true)
    private String login;


    @NotNull(message = "Password is required")
    @Size(min = 8, message = "Password must be at least 6 characters long")
    @Column(nullable = false)
    private String password;


    @NotNull(message = "Email is required")
    @Email(message = "Invalid email format")
    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = true)
    private String name;

    @Column(nullable = true, unique = true, length = 11)
    @Pattern(regexp = "\\d{11}", message = "CPF must have 11 digits")
    private String cpf;

    @Pattern(regexp = "\\d{10,15}", message = "Telephone must be between 10 and 15 digits")
    @Column(nullable = true)
    private String telephone;

    @Column(nullable = true)
    @Enumerated(EnumType.STRING) // Armazenando o nome do enum no banco
    private Gender gender;

    @Column(nullable = true)
    private LocalDate birthDate;

    public User() {}

    public User(Long id, LocalDate birthDate, Gender gender, String cpf, String telephone, String name, String email, String password, String login) {
        this.id = id;
        this.birthDate = birthDate;
        this.gender = gender;
        this.cpf = cpf;
        this.telephone = telephone;
        this.name = name;
        this.email = email;
        this.password = password;
        this.login = login;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword (String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail (String email) {
        this.email = email;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf (String cpf) {
        this.cpf = cpf;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(id, user.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}

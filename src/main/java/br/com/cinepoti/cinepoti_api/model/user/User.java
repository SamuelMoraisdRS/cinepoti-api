package br.com.cinepoti.cinepoti_api.model.user;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.io.Serializable;
import java.util.UUID;

@Entity
@Table(name = "tb_user")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class User implements Serializable {

    @Id
    @GeneratedValue
    private UUID id;

    @NotNull(message = "Username is required")
    @Size(min = 3, max = 50, message = "Username must be between 3 and 50 characters")
    private String userName;

    @NotNull(message = "Email is required")
    @Email(message = "Invalid email format")
    private String email;

    @NotNull(message = "Password is required")
    @Size(min = 6, message = "Password must be at least 6 characters long")
    private String password;


    public User(String userName, String email, String password) {
        this.userName = userName;
        this.email = email;
        this.password = password;
    }

    public User(UUID id,
                @NotNull(message = "Username is required")
                @Size(min = 3, max = 50, message = "Username must be between 3 and 50 characters") String userName,
                @NotNull(message = "Email is required") @Email(message = "Invalid email format")
                String email, @NotNull(message = "Password is required")
                @Size(min = 6, message = "Password must be at least 6") String password){
        this.id = id;
        this.userName = userName;
        this.email = email;
        this.password = password;
    }


    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public @NotNull(message = "Username is required") @Size(min = 3, max = 50, message = "Username must be between 3 and 50 characters") String getUserName() {
        return userName;
    }

    public void setUserName(@NotNull(message = "Username is required") @Size(min = 3, max = 50, message = "Username must be between 3 and 50 characters") String userName) {
        this.userName = userName;
    }

    public @NotNull(message = "Email is required") @Email(message = "Invalid email format") String getEmail() {
        return email;
    }

    public void setEmail(@NotNull(message = "Email is required") @Email(message = "Invalid email format") String email) {
        this.email = email;
    }

    public @NotNull(message = "Password is required") @Size(min = 6, message = "Password must be at least 6 characters long") String getPassword() {
        return password;
    }

    public void setPassword(@NotNull(message = "Password is required") @Size(min = 6, message = "Password must be at least 6 characters long") String password) {
        this.password = password;
    }
}

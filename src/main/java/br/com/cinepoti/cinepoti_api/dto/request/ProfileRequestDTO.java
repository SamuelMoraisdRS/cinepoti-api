package br.com.cinepoti.cinepoti_api.dto.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import org.hibernate.validator.constraints.br.CPF;

import java.time.LocalDate;

public record ProfileRequestDTO(
        @Size(min = 3, max = 50, message = "Username must be between 3 and 50 characters")
        String username,

        @Size(max = 50, message = "Name cannot exceed 50 characters")
        String name,

        @Pattern(regexp = "\\d{11}", message = "CPF must have 11 digits")
        @CPF
        String cpf,

        @Pattern(regexp = "\\d{10,15}", message = "Telephone must be between 10 and 15 digits")
        String phone,

        String gender,

        @JsonFormat(pattern = "dd-MM-yyyy")
        LocalDate birthdate

) { }

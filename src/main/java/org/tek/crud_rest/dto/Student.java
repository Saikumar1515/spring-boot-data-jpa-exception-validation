package org.jsp.crud_rest.dto;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.*;
import lombok.Data;

@Data
@Entity
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotBlank(message = "Name is mandatory")
    @Size(min = 3, max = 50, message = "Name must be between 3 and 50 characters")
    private String name;

    @Column(unique = true)
    @NotNull(message = "Mobile number is mandatory")
    @Digits(integer = 10, fraction = 0, message = "Mobile number must be 10 digits")
    private Long mobile;

    @NotNull(message = "Date of birth is mandatory")
    private LocalDate dob;

    @Min(value = 0, message = "Marks must be at least 0")
    @Max(value = 100, message = "Marks must be at most 100")
    private int maths;

    @Min(value = 0, message = "Marks must be at least 0")
    @Max(value = 100, message = "Marks must be at most 100")
    private int science;

    @Min(value = 0, message = "Marks must be at least 0")
    @Max(value = 100, message = "Marks must be at most 100")
    private int english;

    private double percentage;
    private String result;
}

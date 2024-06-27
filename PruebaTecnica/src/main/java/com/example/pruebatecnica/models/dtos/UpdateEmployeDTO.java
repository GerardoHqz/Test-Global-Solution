package com.example.pruebatecnica.models.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class UpdateEmployeDTO {
    @NotBlank(message = "Employee code cannot be blank")
    @Size(min = 4, max = 4, message = "Employee code must be exactly 4 characters")
    private String code;

    @NotBlank(message = "Name cannot be blank")
    @Size(min = 2, max = 50, message = "Name must be between 3 and 50 characters")
    private String name;

    @NotBlank(message = "Last name cannot be blank")
    @Size(min = 2, max = 50, message = "Last name must be between 2 and 50 characters")
    private String lastName;

    @NotBlank(message = "Address cannot be blank")
    @Size(min = 5, max = 100, message = "Address must be between 5 and 100 characters")
    private String address;

    @NotBlank(message = "Salary cannot be blank")
    @Pattern(regexp = "^\\d+(\\.\\d{1,2})?$", message = "Salary must be a valid number with up to two decimal places")
    private String salary;

    @NotBlank(message = "Department cannot be blank")
    @Size(min = 2, max = 50, message = "Department must be between 2 and 50 characters")
    private String department;
}

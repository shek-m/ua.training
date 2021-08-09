package com.example.taxservice.dto;

import lombok.*;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class UserDTO {

    @NotNull
    @NotEmpty
    @Size(min=2, max=30, message = "{register.validation.name}")
    private String name;

    @NotNull
    @NotEmpty
    @Size(min=2, max=45, message = "{register.validation.surname}")
    private String surname;

    @NotNull
    private String date;

    @NotNull
    @NotEmpty
    @Email(message = "{register.validation.email}")
    private String email;


    @NotNull
    @NotEmpty
    private String username;

    @NotNull
    @NotEmpty
    @Size(min=6, message = "{register.validation.password}")
    private String password;
}
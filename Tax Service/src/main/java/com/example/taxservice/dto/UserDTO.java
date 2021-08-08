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
    @Size(min=2, max=30, message = "incorrect name")
    private String name;

    @NotNull
    @NotEmpty
    @Size(min=2, max=45, message = "incorrect surname")
    private String surname;

    @NotNull
    private String date;

    @NotNull
    @NotEmpty
    @Email(message = "wrong email")
    private String email;


    @NotNull
    @NotEmpty
    private String username;

    @NotNull
    @NotEmpty
    @Size(min=6, message = "little pass")
    private String password;

}
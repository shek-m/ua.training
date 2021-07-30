package com.example.taxservice.entity;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class Note {
    private Long id;
    private String names;
    private String login;
}

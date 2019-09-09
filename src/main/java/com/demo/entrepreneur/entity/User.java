package com.demo.entrepreneur.entity;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
@ToString
public class User {
    private int id;
    private String login;
    private String password;
    private String firstName;
    private String lastName;
    private String role;


}

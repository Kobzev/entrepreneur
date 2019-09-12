package com.demo.entrepreneur.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@NoArgsConstructor
@Getter
@Setter
public class User {

    private Long id;
    private String login;
    private String password;
    private String firstName;
    private String lastName;

}

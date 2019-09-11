package com.demo.entrepreneur.dto;

import lombok.*;


@NoArgsConstructor
@Getter
@Setter

public class UserDTO {

    private Long id;
    private String login;
    private String password;
    private String firstName;
    private String lastName;


}

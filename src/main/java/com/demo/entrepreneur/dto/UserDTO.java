package com.demo.entrepreneur.dto;

import lombok.*;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter

public class UserDTO {

    private String login;
    private String password;
    private String email;
    private String firstName;
    private String lastName;


}

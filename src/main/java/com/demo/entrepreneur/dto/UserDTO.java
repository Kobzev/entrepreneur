package com.demo.entrepreneur.dto;

import lombok.*;

@Builder
@NoArgsConstructor
@Getter
@Setter

public class UserDTO {

    private String login;
    private String password;
    private String firstName;
    private String lastName;


}

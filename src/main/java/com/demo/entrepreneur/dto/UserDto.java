package com.demo.entrepreneur.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.media.Schema.AccessMode;
import lombok.*;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class UserDto {

    @Schema(description = "user login", required = true)
    @JsonProperty("login")
    private String login;

    @Schema(description = "user password", required = true, accessMode = AccessMode.WRITE_ONLY)
    @JsonProperty("password")
    private String password;

    @Schema(required = true)
    @JsonProperty("email")
    private String email;

    @Schema(required = true)
    @JsonProperty("firstName")
    private String firstName;

    @Schema(required = true)
    @JsonProperty("lastName")
    private String lastName;
}

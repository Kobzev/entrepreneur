package com.demo.entrepreneur.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.media.Schema.AccessMode;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class ResponseUserDto {

    @Schema(description = "user login", required = true, accessMode = AccessMode.READ_ONLY)
    @JsonProperty("login")
    private String login;
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

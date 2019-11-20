package com.demo.entrepreneur.dto.user;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
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
@JsonIgnoreProperties(ignoreUnknown = true)
public class RequestUserDto {

    @Schema(description = "user login", required = true)
    @JsonProperty("login")
    private String login;

    @Schema(description = "user password", accessMode = AccessMode.WRITE_ONLY)
    @JsonProperty(value = "password", required = true)
    private String password;

    @JsonProperty(value = "email", required = true)
    private String email;

    @JsonProperty(value = "firstName")
    private String firstName;

    @JsonProperty(value = "lastName")
    private String lastName;

    @JsonProperty(value = "approximateIncomeInUsd", required = true)
    private Integer approximateIncomeInUsd;

    @JsonProperty(value = "taxGroup", required = true)
    private Integer taxGroup;
}

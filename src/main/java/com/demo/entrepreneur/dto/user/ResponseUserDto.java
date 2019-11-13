package com.demo.entrepreneur.dto.user;

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

    @Schema(description = "user login", accessMode = AccessMode.READ_ONLY)
    @JsonProperty(value = "login", required = true)
    private String login;
    @JsonProperty(value = "email", required = true)
    private String email;
    @JsonProperty(value = "firstName", required = false)
    private String firstName;
    @JsonProperty(value = "lastName", required = false)
    private String lastName;

    @JsonProperty(value = "approximateIncomeInUsd", required = true)
    private Integer approximateIncomeInUsd;

    @JsonProperty(value = "taxGroup", required = true)
    private Integer taxGroup;
}

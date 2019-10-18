package com.demo.entrepreneur.rest;

import java.util.stream.Collectors;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.demo.entrepreneur.dto.PublicUserDto;
import com.demo.entrepreneur.dto.UserDto;
import com.demo.entrepreneur.mapping.mapper.impl.PublicUserMapper;
import com.demo.entrepreneur.service.UserService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "user", description = "the user API")
@RestController
@RequestMapping(path = "/rest/users")
public class UserController {

    @Autowired
    private UserService userService;
    @Autowired
    private PublicUserMapper publicUserMapper;

    @Operation(summary = "Get user by login")
    @ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Successful operation"),
            @ApiResponse(responseCode = "404", description = "Not found") })
    @GetMapping(path = "/{login}", produces = MediaType.APPLICATION_JSON_VALUE)
    public PublicUserDto getUserByLogin(
            @Parameter(description = "user login to identify the user", required = true) @PathVariable String login) {
        return publicUserMapper.entityToData(userService.getUserByLogin(login));
    }

    @Operation(summary = "Get all users")
    @ApiResponses(value = @ApiResponse(responseCode = "200", description = "Successful operation",
            content = @Content(array = @ArraySchema(schema = @Schema(implementation = UserDto.class)))))
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public Iterable<PublicUserDto> getAllUsers() {
        return userService.getAllUsers().stream().map(publicUserMapper::entityToData).collect(Collectors.toList());
    }

    @Operation(summary = "Create a new user")
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public PublicUserDto createUser(@RequestBody UserDto user) {
        return publicUserMapper.entityToData(userService.registerNewUser(user));
    }

    @Operation(summary = "Update the user by login")
    @ApiResponses(value = { @ApiResponse(responseCode = "200", description = "The user has been updated successfully"),
            @ApiResponse(responseCode = "404", description = "The user with specified login was not found") })
    @PutMapping(path = "/{login}", consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public PublicUserDto updateUserByLogin(
            @Parameter(description = "user login to identify the user", required = true) @PathVariable String login,
            @RequestBody UserDto user) {
        return publicUserMapper.entityToData(userService.updateUserByLogin(login, user));
    }

    @Operation(summary = "Delete the user by login")
    @ApiResponses(value = { @ApiResponse(responseCode = "204", description = "Successful operation"),
            @ApiResponse(responseCode = "404", description = "The user with specified login was not found") })
    @DeleteMapping(path = "/{login}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteUserByLogin(
            @Parameter(description = "user login to identify the user", required = true) @PathVariable String login) {
        userService.deleteUserByLogin(login);
    }

    @ExceptionHandler(EntityNotFoundException.class)
    @ResponseStatus(code = HttpStatus.NOT_FOUND)
    public void handleEntityNotFound() {
    }

}

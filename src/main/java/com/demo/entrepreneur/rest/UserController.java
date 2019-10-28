package com.demo.entrepreneur.rest;

import java.util.stream.Collectors;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import com.demo.entrepreneur.dto.ResponseUserDto;
import com.demo.entrepreneur.dto.RequestUserDto;
import com.demo.entrepreneur.mapping.mapper.impl.ResponseUserMapper;
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
    private ResponseUserMapper responseUserMapper;

    @Operation(summary = "Get user by login")
    @ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Successful operation"),
            @ApiResponse(responseCode = "404", description = "Not found") })
    @GetMapping(path = "/{login}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseUserDto getUserByLogin(
            @Parameter(description = "user login to identify the user", required = true) @PathVariable String login) {
        return responseUserMapper.entityToData(userService.getUserByLogin(login));
    }

    @Operation(summary = "Get all users")
    @ApiResponses(value = @ApiResponse(responseCode = "200", description = "Successful operation",
            content = @Content(array = @ArraySchema(schema = @Schema(implementation = RequestUserDto.class)))))
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public Iterable<ResponseUserDto> getAllUsers() {
        return userService.getAllUsers().stream().map(responseUserMapper::entityToData).collect(Collectors.toList());
    }

    @Operation(summary = "Create a new user")
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseUserDto createUser(@RequestBody RequestUserDto user) {
        return responseUserMapper.entityToData(userService.registerNewUser(user));
    }

    @Operation(summary = "Update the user by login")
    @ApiResponses(value = { @ApiResponse(responseCode = "200", description = "The user has been updated successfully"),
            @ApiResponse(responseCode = "404", description = "The user with specified login was not found") })
    @PutMapping(path = "/{login}", consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseUserDto updateUserByLogin(
            @Parameter(description = "user login to identify the user", required = true) @PathVariable String login,
            @RequestBody RequestUserDto user) {
        return responseUserMapper.entityToData(userService.updateUserByLogin(login, user));
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

    @GetMapping("/confirm-email")
    public void confirmUserEmail(@RequestParam String token) {
        userService.confirmEmail(token);
    }

    @ExceptionHandler(EntityNotFoundException.class)
    @ResponseStatus(code = HttpStatus.NOT_FOUND)
    public void handleEntityNotFound() {
    }



}

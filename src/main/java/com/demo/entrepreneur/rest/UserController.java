package com.demo.entrepreneur.rest;

import java.util.stream.Collectors;

import javax.persistence.EntityNotFoundException;

import com.demo.entrepreneur.model.ConfirmationToken;
import com.demo.entrepreneur.model.repository.ConfirmationTokenRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import com.demo.entrepreneur.dto.PublicUserDto;
import com.demo.entrepreneur.dto.UserDto;
import com.demo.entrepreneur.model.mapping.mapper.impl.PublicUserMapper;
import com.demo.entrepreneur.service.UserService;

@RestController
@RequestMapping("/rest/users")
public class UserController {

    @Autowired
    private UserService userService;
    @Autowired
    private PublicUserMapper publicUserMapper;

    @GetMapping("/{login}")
    public PublicUserDto getUserByLogin(@PathVariable String login) {
        return publicUserMapper.entityToData(userService.getUserByLogin(login));
    }

    @GetMapping
    public Iterable<PublicUserDto> getAllUsers() {
        return userService.getAllUsers().stream().map(publicUserMapper::entityToData).collect(Collectors.toList());
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public PublicUserDto createUser(@RequestBody UserDto user) {
        return publicUserMapper.entityToData(userService.registerNewUser(user));
    }

    @PutMapping("/{login}")
    public PublicUserDto updateUserByLogin(@PathVariable String login, @RequestBody UserDto user) {
        return publicUserMapper.entityToData(userService.updateUserByLogin(login, user));
    }

    @DeleteMapping("/{login}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteUserByLogin(@PathVariable String login) {
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

package com.demo.entrepreneur.rest.crud;

import java.util.stream.Collectors;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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

import com.demo.entrepreneur.dto.UserDto;
import com.demo.entrepreneur.model.mapping.mapper.impl.UserMapper;
import com.demo.entrepreneur.rest.UserController;
import com.demo.entrepreneur.service.UserService;

@RestController
@RequestMapping("/rest/users")
public class CrudUserController implements UserController {

	@Autowired
	private UserService userService;
	@Autowired
	private UserMapper userMapper;

	@GetMapping("/{login}")
	@Override
	public UserDto getUserByLogin(@PathVariable String login) {
		return userMapper.entityToData(userService.getUserByLogin(login));
	}

	@GetMapping
	@Override
	public Iterable<UserDto> getAllUsers() {
		return userService.getAllUsers().stream().map(userMapper::entityToData).collect(Collectors.toList());
	}

	@PutMapping
	@ResponseStatus(HttpStatus.CREATED)
	@Override
	public void createUser(@RequestBody UserDto user) {
		userService.registerNewUser(user);
	}

	@PostMapping("/{login}")
	@Override
	public UserDto updateUserByLogin(@PathVariable String login, @RequestBody UserDto user) {
		user.setLogin(login);
		return userMapper.entityToData(userService.updateUserByLogin(user));
	}

	@DeleteMapping("/{login}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	@Override
	public void deleteUserByLogin(@PathVariable String login) {
		userService.deleteUserByLogin(login);
	}

	@ExceptionHandler(EntityNotFoundException.class)
	@ResponseStatus(code = HttpStatus.NOT_FOUND)
	public void handleEntityNotFound() {
	}

}

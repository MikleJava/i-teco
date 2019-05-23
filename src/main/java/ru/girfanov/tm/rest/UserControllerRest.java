package ru.girfanov.tm.rest;

import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.girfanov.tm.api.service.IUserService;
import ru.girfanov.tm.dto.ProjectDto;
import ru.girfanov.tm.dto.UserDto;
import ru.girfanov.tm.entity.User;
import ru.girfanov.tm.exception.UserNotFoundException;

import java.security.Principal;

@RestController
@RequestMapping("/api/user")
public class UserControllerRest {

    @Autowired
    private IUserService userService;

    @GetMapping(value = "/show/{id}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<UserDto> show(@PathVariable final String id) {
        if(id == null || id.isEmpty()) return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        final UserDto userDto = castToUserDto(userService.findOne(id));
        return new ResponseEntity<>(userDto, HttpStatus.OK);
    }

    @PostMapping(value = "/create", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<UserDto> create(@RequestBody final UserDto userDto) {
        if(userDto == null) return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        userService.persist(castToUser(userDto));
        return new ResponseEntity<>(userDto, HttpStatus.OK);
    }

    @PutMapping(value = "/update/{id}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<UserDto> update(@PathVariable final String id) {
        if(id == null || id.isEmpty()) return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        final UserDto userDto = castToUserDto(userService.findOne(id));
        if(userDto == null) return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        try {
            userService.merge(id, castToUser(userDto));
        } catch (UserNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(userDto, HttpStatus.OK);
    }

    @DeleteMapping(value = "delete/{id}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<UserDto> delete(@PathVariable final String id) {
        if(id == null || id.isEmpty()) return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        final UserDto userDto = castToUserDto(userService.findOne(id));
        if(userDto == null) return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        try {
            userService.remove(id, castToUser(userDto));
        } catch (UserNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }

    protected static UserDto castToUserDto(@NotNull final User user) {
        final UserDto userDto = new UserDto();
        userDto.setId(user.getId());
        userDto.setLogin(user.getLogin());
        userDto.setPassword(user.getPassword());
        return userDto;
    }

    private User castToUser(@NotNull final UserDto userDto) {
        final User user = new User();
        user.setId(userDto.getId());
        user.setLogin(userDto.getLogin());
        user.setPassword(userDto.getPassword());
        return user;
    }
}

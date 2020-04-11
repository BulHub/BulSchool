package ru.itis.controllers.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.itis.dto.AdminUserDto;
import ru.itis.models.User;
import ru.itis.services.UserService;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/admin")
public class AdminRestController {

    private final UserService userService;

    @Autowired
    public AdminRestController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping(value = "/users/{email:.+}")
    public ResponseEntity<AdminUserDto> getUserByEmail(@PathVariable(name = "email") String email) {
        User user = userService.find(email);
        if (user == null) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        AdminUserDto result = AdminUserDto.fromUser(user);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @GetMapping("/users")
    public ResponseEntity<List<AdminUserDto>> getUsers() {
        List<User> users = userService.findAll();
        if (users.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        List<AdminUserDto> userDtoList = new ArrayList<>();
        for (User user : users) {
            userDtoList.add(AdminUserDto.fromUser(user));
        }
        return new ResponseEntity<>(userDtoList, HttpStatus.OK);
    }

    @PostMapping("/users/add")
    public ResponseEntity<User> addUser(@RequestBody User user) {
        userService.add(user);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/users/delete")
    public ResponseEntity<User> deleteUser(@RequestBody User user) {
        userService.delete(user);
        return ResponseEntity.ok().build();
    }

}

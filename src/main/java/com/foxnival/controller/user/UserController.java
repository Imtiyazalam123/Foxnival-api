package com.foxnival.controller.user;

import com.fasterxml.jackson.annotation.JsonView;
import com.foxnival.entity.User;
import com.foxnival.service.user.UserService;
import com.foxnival.view.Views;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    @Operation(summary = "Authenticate the user details while logging In.",
            description = "It will authenticate the user's username and password with database, Once user authenticate it will return the user details otherwise throw error.",
            responses = {
                    @ApiResponse(description = "Authenticated.", responseCode = "200", content = @Content),
                    @ApiResponse(description = "Unauthenticated.", responseCode = "401", content = @Content)
            }
    )
    @PostMapping(path = "/{username}/{password}/login", produces = MediaType.APPLICATION_JSON_VALUE)
    @JsonView(Views.User.class)
    public ResponseEntity<User> authenticateUser(@PathVariable(name = "username") String username, @PathVariable(name = "password") String password) {

        User user = userService.authenticateUser(username, password);

        if (user == null) {
            return new ResponseEntity<>(user, HttpStatus.UNAUTHORIZED);
        }

        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @GetMapping(path = "/fetchAll")
    @JsonView(Views.User.class)
    public ResponseEntity<List<User>> getAllUsers() {

        return new ResponseEntity<>(userService.getAllUsers(), HttpStatus.OK);
    }

}

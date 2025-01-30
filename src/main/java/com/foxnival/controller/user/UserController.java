package com.foxnival.controller.user;

import com.fasterxml.jackson.annotation.JsonView;
import com.foxnival.dto.UserDetailsDto;
import com.foxnival.dto.UserDto;
import com.foxnival.entity.User;
import com.foxnival.service.user.UserService;
import com.foxnival.view.Views;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
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
    @JsonView(Views.UserWithSubscriber.class)
    public ResponseEntity<User> authenticateUser(@PathVariable(name = "username") String username, @PathVariable(name = "password") String password) {

        User user = userService.authenticateUser(username, password);

        if (user == null) {
            return new ResponseEntity<>(user, HttpStatus.UNAUTHORIZED);
        }

        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @Operation(summary = "Create new user in user table",
            description = "It will create new user in USER table and throw error message in case user already exist with the same username.",
            responses = {
                    @ApiResponse(description = "Created.", responseCode = "201", content = @Content),
                    @ApiResponse(description = "Duplicate user.", responseCode = "400", content = @Content)
            }
    )
    @PostMapping(path = "/add", produces = MediaType.APPLICATION_JSON_VALUE)
    @JsonView(Views.User.class)
    public ResponseEntity<User> addUser(@RequestBody UserDto userDto) {

        return new ResponseEntity<>(userService.addUser(userDto), HttpStatus.CREATED);
    }

    @GetMapping(path = "/fetchAll")
    @JsonView(Views.User.class)
    public ResponseEntity<List<User>> getAllUsers() {

        return new ResponseEntity<>(userService.getAllUsers(), HttpStatus.OK);
    }

    @Operation(summary = "Fetch all users based subscriber",
            description = "It will retrieve all users based on provided subscriber id.",
            responses = {
                    @ApiResponse(description = "Successful.", responseCode = "200", content = @Content),
                    @ApiResponse(description = "Subscriber id not found.", responseCode = "400", content = @Content)
            }
    )
    @GetMapping(path = "/subscribers/{subscriberId}")
    @JsonView(Views.User.class)
    public ResponseEntity<List<User>> getAllUsersBySubscriberId(@PathVariable(name = "subscriberId") Long subscriberId) {

        return new ResponseEntity<>(userService.getAllUsersBySubscriberId(subscriberId), HttpStatus.OK);
    }

    @Operation(summary = "Delete user by ID",
            description = "It will delete the user based on the provided user ID.",
            responses = {
                    @ApiResponse(description = "Deleted.", responseCode = "200", content = @Content),
                    @ApiResponse(description = "User not found.", responseCode = "404", content = @Content)
            }
    )
    @DeleteMapping(path = "/{userId}")
    public ResponseEntity<Void> deleteUser(@PathVariable(name = "userId") Long userId) {
        userService.deleteUser(userId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @Operation(summary = "Update user details and password",
            description = "It will update user details and password",
            responses = {
                    @ApiResponse(description = "updated", responseCode = "201", content = @Content),
                    @ApiResponse(description = "User not found.", responseCode = "400", content = @Content)
            }
    )
    @PutMapping(path = "/updateUserDetails/{userId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<UserDetailsDto> updateUserDetails(
            @Parameter(description = "User id.")
            @PathVariable(name = "userId") Long userId,
            @RequestBody UserDetailsDto userDetailsDto) {
        return new ResponseEntity<>(userService.updateUserDetails(userId, userDetailsDto), HttpStatus.CREATED);
    }

    @Operation(summary = "Fetch user details based on user id.",
            description = "It will fetch the user details based on user id.",
            responses = {
                    @ApiResponse(description = "Successful.", responseCode = "200", content = @Content),
                    @ApiResponse(description = "User not found.", responseCode = "400", content = @Content)
            }
    )
    @GetMapping(path = "/{userId}", produces = MediaType.APPLICATION_JSON_VALUE)
    @JsonView(Views.UserWithSubscriber.class)
    public ResponseEntity<User> getUserById(
            @Parameter(description = "User id.")
            @PathVariable(name = "userId") Long userId
    ) {
        return new ResponseEntity<>(userService.fetchUserById(userId), HttpStatus.OK);
    }


}

package com.foxnival.controller.forgetpassword;

import com.foxnival.service.forgetpassword.ForgetPasswordService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@CrossOrigin("*")
@RestController
@RequestMapping("/")
public class ForgetPasswordController {

    @Autowired
    private ForgetPasswordService forgetPasswordService;

    @Operation(summary = "Send verification on user email.",
            description = "It will send the verification on on user mail.",
            responses = {
                    @ApiResponse(description = "Sent", responseCode = "200", content = @Content),
                    @ApiResponse(description = "Username not found.", responseCode = "400", content = @Content)
            }
    )
    @PostMapping(path = "/generateOtp/{username}", produces = MediaType.APPLICATION_JSON_VALUE)
    public boolean sendVerificationCode(
            @Parameter(description = "Username")
            @PathVariable(name = "username") String username
    ) {
        return forgetPasswordService.sendVerificationCode(username);
    }

    @Operation(summary = "Verify the provided otp for specific email",
            description = "It will verify the provided otp for specific email",
            responses = {
                    @ApiResponse(description = "verified.", responseCode = "200", content = @Content),
                    @ApiResponse(description = "Invalid Otp", responseCode = "400", content = @Content)
            }
    )
    @PostMapping(path = "/verifyOtp/{username}/otp/{otp}")
    public boolean verifyOtp(
            @Parameter(description = "Username")
            @PathVariable(name = "username") String username,
            @Parameter(description = "otp")
            @PathVariable(name = "otp") String otp
    ) {
        return forgetPasswordService.verifyOtp(username, otp);
    }

    @Operation(summary = "Rest the password for specific email/user",
            description = "It will Rest the password for specific email/user",
            responses = {
                    @ApiResponse(description = "verified.", responseCode = "200", content = @Content),
                    @ApiResponse(description = "Invalid username/email", responseCode = "400", content = @Content)
            }
    )

    @PutMapping(path = "/resetPassword/{username}/password/{password}")
    public boolean restPassword(
            @Parameter(description = "Username")
            @PathVariable(name = "username") String username,
            @Parameter(description = "new password.")
            @PathVariable(name = "password") String newPassword
    ) {
        return forgetPasswordService.resetPassword(username, newPassword);
    }
}

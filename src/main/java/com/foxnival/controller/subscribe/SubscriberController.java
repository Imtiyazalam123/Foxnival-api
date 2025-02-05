package com.foxnival.controller.subscribe;

import com.foxnival.dto.SubscribeDetailDto;
import com.foxnival.entity.SubscribeDetail;
import com.foxnival.service.subscribe.SubscriberService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/subscribe")
@CrossOrigin("*")
public class SubscriberController {

    @Autowired
    private SubscriberService subscriberService;

    @Operation(summary = "To subscribe the plan first time.",
            description = "It will create default user as owner role, Send the login credential to provided email And" +
                    "store the payment details whether it is failed or success.",
            responses = {
                    @ApiResponse(description = "Successful operation", responseCode = "200", content = @Content),
                    @ApiResponse(description = "Bad request.", responseCode = "400", content = @Content)
            }
    )
    @RequestMapping(path = "/createSubscriberUser", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<SubscribeDetail> createSubscription(
            @Parameter(description = "Request body for receiving user, subscribe and payment information.")
            @RequestBody SubscribeDetailDto subscribeDetailDto) {

        return new ResponseEntity<>(subscriberService.createSubscription(subscribeDetailDto), HttpStatus.CREATED);
    }

    @Operation(summary = "To check whether email already exist or not.",
            description = "This api will check whether email is already used by any active subscription or not, if yes then it will throw error.",
            responses = {
                    @ApiResponse(description = "Successful operation", responseCode = "200", content = @Content),
                    @ApiResponse(description = "Email already have active subscription.", responseCode = "400", content = @Content)
            }
    )
    @RequestMapping(path = "/checkEmail/{email}", method = RequestMethod.GET)
    public ResponseEntity<Void> checkEmail(
            @Parameter(description = "Email id.")
            @PathVariable(name = "email") String email) {

        subscriberService.checkEmail(email);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}

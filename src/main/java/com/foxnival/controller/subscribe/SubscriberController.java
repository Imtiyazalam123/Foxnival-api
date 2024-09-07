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

        return new ResponseEntity<>(subscriberService.createSubscription(subscribeDetailDto), HttpStatus.OK);
    }
}

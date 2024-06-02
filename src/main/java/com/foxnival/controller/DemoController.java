package com.foxnival.controller;

import com.foxnival.entity.Payload;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/demo")
public class DemoController {

    @RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<Payload> getPayload() {
       System.out.println("Api called.....");
        Payload payload = new Payload("imtiyaj", "Bhopal");

        return new ResponseEntity<Payload>(payload, HttpStatus.OK);
    }
}

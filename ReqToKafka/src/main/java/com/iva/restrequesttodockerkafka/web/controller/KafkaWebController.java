package com.iva.restrequesttodockerkafka.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.iva.restrequesttodockerkafka.service.KafkaSender;

@Controller
public class KafkaWebController {

    @Autowired
    KafkaSender kafkaSender;

 
    @PostMapping(path = "/kafka", consumes = "application/json", produces = "application/json")
    public ResponseEntity<String> sendToTopic( @RequestBody String message) {
    	System.out.println("System did receive post message: "+message);
    	kafkaSender.send( message);
        return ResponseEntity.ok("POST processed");
    }

    @GetMapping(path = "/kafka", produces = "application/json")
    public ResponseEntity<String>  testrequest() {
    	System.out.println("System did receive GET equest");
    	kafkaSender.send( "SomeTestMessageeeeeeeeeeee");
    	 return ResponseEntity.ok("GET processed");
    }

}

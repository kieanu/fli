package com.leepark.fli.controller.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;

import com.leepark.fli.model.firebase.service.FirebaseCloudMessageDataService;

@RestController
@CrossOrigin("*")
public class FirebasePushController {

    @Autowired
    FirebaseCloudMessageDataService service;
    
//    @PostMapping("/addToken")
//    public Integer addToken(@RequestBody User user) {
//    	logger.info("registToken : token:{}", user);
//        return service.addToken(user);
//    }
    
//    @PostMapping("/sendMessageWithData")
//    public void sendNotification(String token, String title, String body) throws IOException {
//		service.sendDataMessageTo(token, title, body);
//    }
}

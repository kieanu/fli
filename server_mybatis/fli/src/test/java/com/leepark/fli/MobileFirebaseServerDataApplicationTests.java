package com.leepark.fli;

import java.io.IOException;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.leepark.fli.model.firebase.service.FirebaseCloudMessageDataService;

@SpringBootTest
class MobileFirebaseServerDataApplicationTests {

    @Autowired
    FirebaseCloudMessageDataService dataService;

    //Notification아니라 Data에 데이터 담아서 전송함. 
    @Test
    void sendDataMessage() throws IOException {
        String token = dataService.select("gustjq7027@gmail.com", 0);
        System.out.println(token);
        String title = "테스트";
        String body = "테스트입니다";
        
//		한건 메시지
        dataService.sendDataMessageTo(token, title, body, null);
        
//		멀티 메시지        
//        dataService.addToken(token);
//        service.addToken(token1);
        
//        service.broadCastMessage("from 사무국1", "싸피 여러분 화이팅!!!!!!!!");
    }
}

package com.leepark.fli.model.firebase.service;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.http.HttpHeaders;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.dao.support.DaoSupport;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.util.Base64Utils;
import org.springframework.util.StreamUtils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.auth.oauth2.GoogleCredentials;
import com.leepark.fli.model.dto.User;
import com.leepark.fli.model.firebase.dao.FirebaseDao;
import com.leepark.fli.model.firebase.dto.FcmDataMessage;
import com.leepark.fli.model.firebase.dto.Message;
import com.leepark.fli.model.firebase.dto.Notification;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * FCM 알림 메시지 생성
 * background 대응을 위해서 data로 전송한다.
 */
@Service
@Component
public class FirebaseCloudMessageDataService {
	private static final Logger logger = LoggerFactory.getLogger(FirebaseCloudMessageDataService.class);

    public final ObjectMapper objectMapper;
    
    @Autowired
    private FirebaseDao firebasedao;

    public FirebaseCloudMessageDataService(ObjectMapper objectMapper){
    	this.objectMapper = objectMapper;
    }
    
    private final String API_URL = "https://fcm.googleapis.com/v1/projects/myfirebasechat-1659d/messages:send";
     
    // 클라이언트 토큰 등록
    public Integer addToken(User user) {
    	return firebasedao.addToken(user);
    }

    // 클라이언트 토큰 불러오기
    public String select(String userId, Integer userSns) {
    	return firebasedao.select(userId, userSns);
    }
    
    /**
     * FCM에 push 요청을 보낼 때 인증을 위해 Header에 포함시킬 AccessToken 생성
     * @return
     * @throws IOException
     */
    private String getAccessToken() throws IOException {
        String firebaseConfigPath = "firebase/firebase_service_key.json";

        // GoogleApi를 사용하기 위해 oAuth2를 이용해 인증한 대상을 나타내는객체
        GoogleCredentials googleCredentials = GoogleCredentials
                // 서버로부터 받은 service key 파일 활용
                .fromStream(new ClassPathResource(firebaseConfigPath).getInputStream())
                // 인증하는 서버에서 필요로 하는 권한 지정
                .createScoped(Arrays.asList("https://www.googleapis.com/auth/cloud-platform"));
        
        googleCredentials.refreshIfExpired();
        String token = googleCredentials.getAccessToken().getTokenValue();
        System.out.println("서버토큰" + token);
        return token;
    }
    

    /**
     * targetToken에 해당하는 device로 FCM 푸시 알림 전송
     * background 대응을 위해서 data로 전송한다.  
     * @param targetToken
     * @param title
     * @param body
     * @throws IOException
     */
    public void sendDataMessageTo(String targetToken, String title, String body, String img) throws IOException {
        String message = makeDataMessage(targetToken, title, body, img);
        logger.info("message : {}", message);
        OkHttpClient client = new OkHttpClient();
        RequestBody requestBody = RequestBody.create(message, MediaType.get("application/json; charset=utf-8"));
        Request request = new Request.Builder()
                .url(API_URL)
                .post(requestBody)
                // 전송 토큰 추가
                .addHeader(HttpHeaders.AUTHORIZATION, "Bearer " + getAccessToken())
                .addHeader(HttpHeaders.CONTENT_TYPE, "application/json; UTF-8")
                .build();

        Response response = client.newCall(request).execute();

        System.out.println(response.body().string());
    }
    
    /**
     * FCM 알림 메시지 생성
     * background 대응을 위해서 data로 전송한다.  
     * @param targetToken
     * @param title
     * @param body
     * @return
     * @throws JsonProcessingException
     */
    private String makeDataMessage(String targetToken, String title, String body,String img) throws JsonProcessingException, IOException {  
//    	Notification noti = new Notification(title, body, img);
    	Map<String,String> map = new HashMap<>();
    	map.put("title", title);
    	map.put("body", body);
    	map.put("image", img);
    	
    	Message message = new Message();
//    	message.setNotification(noti);
        message.setToken(targetToken);
        message.setData(map);
        
        FcmDataMessage fcmMessage = new FcmDataMessage(false, message);
        
        return objectMapper.writeValueAsString(fcmMessage);
    }
}

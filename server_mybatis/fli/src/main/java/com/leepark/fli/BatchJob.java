package com.leepark.fli;

import java.io.IOException;
import java.net.InetAddress;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.leepark.fli.model.dao.MusicDao;
import com.leepark.fli.model.dto.Music;
import com.leepark.fli.model.firebase.service.FirebaseCloudMessageDataService;

@Service
public class BatchJob {
    @Autowired
    private MusicDao musicDao;
    
    @Autowired
    FirebaseCloudMessageDataService dataService;
    
    private Integer currentCount = 0;
    
    @PostConstruct
    public void init() {
        currentCount = musicDao.selectCount();
    }
    
	@Scheduled(fixedDelay = 60000) // 1분마다 실행
	public void run() {
		System.out.println("랭킹 배치 실행");
		musicDao.batchRanking();
		int newCount = musicDao.selectCount();
		Music latestMusic = musicDao.select(currentCount+1);
		if(currentCount < newCount) {
			currentCount = newCount;
			try {
//				String realImg = "http://"+ InetAddress.getLocalHost().getHostAddress() + ":10000/fli/images/" + latestMusic.getMusicImg() +".jpg";
				String realImg = "http://52.79.233.234:10000/fli/images/" + latestMusic.getMusicImg() +".jpg";
				sendDataMessage(latestMusic.getMusicTitle(), latestMusic.getMusicDesc(), realImg);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

    //Notification아니라 Data에 데이터 담아서 전송함. 
    private void sendDataMessage(String title, String body,String img) throws IOException {
        String token = dataService.select("gustjq7027@gmail.com", 0);
        System.out.println(token);
        
//		한건 메시지
        dataService.sendDataMessageTo(token, title, body, img);
        
//		멀티 메시지        
//        dataService.addToken(token);
//        service.addToken(token1);
        
//        service.broadCastMessage("from 사무국1", "싸피 여러분 화이팅!!!!!!!!");
    }
}

package com.leepark.fli.controller.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.leepark.fli.model.dto.Music;
import com.leepark.fli.model.dto.User;
import com.leepark.fli.model.service.MusicService;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/music")
@CrossOrigin("*")
public class MusicRestController {
    // 클라이언트에 에러메시지 보내는 로직 구현 -> response로 클라이언트에 알려줄 수 있음
	
    @Autowired
    MusicService mService;

    @GetMapping("/{musicId}")
    @ApiOperation(value = "노래 1건 정보를 조회한다.", response = Music.class)
    public Music select(@PathVariable Integer musicId) {
        return mService.select(musicId);
    }
    
    @GetMapping("/all")
    @ApiOperation(value = "노래 전체 정보를 조회한다.", response = Music.class)
    public List<Music> selectAll() {
        return mService.selectAll();
    }

    @GetMapping("/genre/{genreId}")
    @ApiOperation(value = "장르 코드로 조회한 노래 전체 정보를 조회한다.", response = Music.class)
    public List<Music> selectByGenre(@PathVariable Integer genreId) {
        return mService.selectByGenre(genreId);
    }

    @GetMapping("/favorite")
    @ApiOperation(value = "좋아요 한 노래 전체 정보를 조회한다.", response = Music.class)
    public List<Music> selectByFavorite(@RequestBody User user) {
        return mService.selectByFavorite(user);
    }

}

package com.leepark.fli.controller.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.leepark.fli.model.dto.Favorite;
import com.leepark.fli.model.service.FavoriteService;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/favorite")
@CrossOrigin("*")
public class FavoriteRestController {
    // 클라이언트에 에러메시지 보내는 로직 구현 -> response로 클라이언트에 알려줄 수 있음
	
    @Autowired
    FavoriteService fService;

    @GetMapping("/selectAll/{userId}/{userSns}")
    @ApiOperation(value = "유저가 좋아요한 노래no를 모두 가져온다", response = Favorite.class)
    public List<Favorite> selectAll(@PathVariable String userId, @PathVariable Integer userSns) {
        return fService.selectAll(userId, userSns);
    }
    
    @GetMapping("/isFavorite/{userId}/{userSns}/{musicId}")
    @ApiOperation(value = "노래가 유저의 좋아요 목록에 이미 등록돼있는지 확인", response = Boolean.class)
    public Boolean isFavorite(@PathVariable String userId, @PathVariable Integer userSns, @PathVariable Integer musicId) {
    	return fService.isFavorite(userId, userSns, musicId);
    }
    
    @PostMapping("/insert")
    @ApiOperation(value = "노래를 유저의 좋아요 리스트에 추가한다.", response = Integer.class)
    public Integer insert(@RequestBody Favorite favorite) {
    	if(fService.isFavorite(favorite.getUserId(), favorite.getUserSns(), favorite.getMusicId()) == false) 
    		return fService.insert(favorite);
    	return -1;
    }

    @DeleteMapping("/delete")
    @ApiOperation(value = "해당 노래를 유저가 좋아요한 목록에서 삭제한다.", response = Integer.class)
    public Integer deleteUser(@RequestBody Favorite favorite) {
        return fService.delete(favorite);
    }
    
}

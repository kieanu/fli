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

import com.leepark.fli.model.dto.Playlist;
import com.leepark.fli.model.service.PlaylistContentService;
import com.leepark.fli.model.service.PlaylistService;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/playlist")
@CrossOrigin("*")
public class PlaylistRestController {
    // 클라이언트에 에러메시지 보내는 로직 구현 -> response로 클라이언트에 알려줄 수 있음
	
    @Autowired
    PlaylistService plService;
    
    @Autowired
    PlaylistContentService plcService;

    @PostMapping("/insert")
    @ApiOperation(value = "플레이리스트를 추가한다")
    public Integer insert(@RequestBody Playlist playlist) {
        return plService.insert(playlist);
    }
    
    @GetMapping("/{userId}/{userSns}")
    @ApiOperation(value = "플레이리스트 정보를 모두 가져온다")
    public List<Playlist> selectAll(@PathVariable String userId, @PathVariable Integer userSns) {
        return plService.selectAll(userId, userSns);
    }
    
    @DeleteMapping("/delete/{playlistId}")
    @ApiOperation(value = "플레이리스트 id에 따라 삭제한다")
    public Integer delete(@PathVariable Integer playlistId) {
        plcService.deleteAll(playlistId);
        return plService.delete(playlistId);
    }
    
//    @GetMapping("/{userId}/{userSns}")
//    @ApiOperation(value = "플레이리스트 정보를 모두 가져온다")
//    public List<PlaylistInfo> selectAll(@PathVariable String userId, @PathVariable Integer userSns) {
//        return plService.selectAll(userId, userSns);
//    }

}

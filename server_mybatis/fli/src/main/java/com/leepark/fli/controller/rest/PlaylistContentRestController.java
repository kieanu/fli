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

import com.leepark.fli.model.dto.Music;
import com.leepark.fli.model.dto.PlaylistContent;
import com.leepark.fli.model.dto.User;
import com.leepark.fli.model.service.MusicService;
import com.leepark.fli.model.service.PlaylistContentService;
import com.leepark.fli.model.service.PlaylistService;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/playlistContent")
@CrossOrigin("*")
public class PlaylistContentRestController {
    // 클라이언트에 에러메시지 보내는 로직 구현 -> response로 클라이언트에 알려줄 수 있음
	
    @Autowired
    PlaylistContentService plcService;

    @PostMapping("/insertMusic")
    @ApiOperation(value = "플레이리스트에 노래를 추가한다")
    public Integer insertMusic(@RequestBody PlaylistContent playlistContent) {
        return plcService.insertMusic(playlistContent);
    }
    
    @DeleteMapping("/deleteMusic")
    @ApiOperation(value = "플레이리스트의 노래를 삭제한다")
    public Integer deleteMusic(@RequestBody PlaylistContent playlistContent) {
        return plcService.deleteMusic(playlistContent);
    }

}

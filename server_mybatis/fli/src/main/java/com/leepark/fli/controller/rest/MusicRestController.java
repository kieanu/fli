package com.leepark.fli.controller.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.leepark.fli.model.dto.Favorite;
import com.leepark.fli.model.dto.Like;
import com.leepark.fli.model.dto.Music;
import com.leepark.fli.model.dto.User;
import com.leepark.fli.model.service.FavoriteService;
import com.leepark.fli.model.service.MusicService;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/music")
@CrossOrigin("*")
public class MusicRestController {
    // 클라이언트에 에러메시지 보내는 로직 구현 -> response로 클라이언트에 알려줄 수 있음
	
    @Autowired
    MusicService mService;
    
    @Autowired
    FavoriteService fService;

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
    
    @GetMapping("/allbyPaging")
    @ApiOperation(value = "노래 전체 정보를 1페이지 단위로 받아온다.(무한 스크롤)", response = Music.class)
    public List<Music> selectAllByPaging(Integer lastId, Integer count) {
        return mService.selectAllByPaging(lastId, count);
    }

    @GetMapping("/genre/{genreId}")
    @ApiOperation(value = "장르 코드로 조회한 노래 전체 정보를 조회한다.", response = Music.class)
    public List<Music> selectByGenre(@PathVariable Integer genreId) {
        return mService.selectByGenre(genreId);
    }

    @GetMapping("/favorite/{userId}/{userSns}")
    @ApiOperation(value = "좋아요 한 노래 전체 정보를 조회한다.", response = Music.class)
    public List<Music> selectByFavorite(@PathVariable String userId, @PathVariable Integer userSns) {
        return mService.selectByFavorite(userId, userSns);
    }
    
    @GetMapping("/playlist/{playlistId}")
    @ApiOperation(value = "플레이리스트 노래 정보를 조회한다.", response = Music.class)
    public List<Music> selectByPlaylist(@PathVariable Integer playlistId) {
        return mService.selectByPlaylist(playlistId);
    }
    
    @GetMapping("/allbyRank")
    @ApiOperation(value = "노래 전체 정보를 랭킹 순으로 가져온다.", response = Music.class)
    public List<Music> selectAllByRank() {
        return mService.selectAllByRank();
    }
    
    @GetMapping("/allbyRankbyPaging")
    @ApiOperation(value = "노래 전체 정보를 랭킹 순으로 1페이지 단위로 받아온다.(무한 스크롤)", response = Music.class)
    public List<Music> selectAllByRankByPaging(Integer lastRank, Integer count) {
        return mService.selectAllByPaging(lastRank, count);
    }

    @GetMapping("/selectByRank/{ranking}")
    @ApiOperation(value = "랭크 순 조회 (n순위 노래 목록 뽑아오기)", response = Music.class)
    public List<Music> selectByRank(@PathVariable Integer ranking) {
        return mService.selectByRank(ranking);
    }
    
    @GetMapping("/selectAllBySearch/{keyword}")
    @ApiOperation(value = "노래 조회 (keyword를 포함하는 노래 목록 뽑아오기)", response = Music.class)
    public List<Music> selectAllBySearch(@PathVariable String keyword) {
        return mService.selectAllBySearch(keyword);
    }
    
    @PostMapping("/updateLike")
    @ApiOperation(value = "좋아요 이벤트 요청이 오면 좋아요된 거면 -1 안된거면 +1 해준다")
    public Integer updateLike(@RequestBody Favorite favorite) {
        mService.updateLike(favorite);
        if(fService.isFavorite(favorite.getUserId(), favorite.getUserSns(), favorite.getMusicId()) == false)
        	return fService.insert(favorite);
        else
        	return fService.delete(favorite)-2;
    }

    
}

package com.leepark.fli.model.dto;

import java.util.Arrays;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.springframework.core.io.Resource;

import lombok.*;

@Getter
@Setter
public class Music {
    private Integer musicId;
    private Integer genreId; // 0 or 1
    private String musicTitle;
    private String musicDesc;
    private String singer;
    private Long musicDate;
    private Integer musicLike;
    private Integer musicRank;
    private String musicImg;
    private String realImg="";
    	
	public Music(Integer musicId, Integer genreId, String musicTitle, String musicDesc, String singer, Long musicDate,
			Integer musicLike, Integer musicRank, String musicImg, String realImg) {
		super();
		this.musicId = musicId;
		this.genreId = genreId;
		this.musicTitle = musicTitle;
		this.musicDesc = musicDesc;
		this.singer = singer;
		this.musicDate = musicDate;
		this.musicLike = musicLike;
		this.musicRank = musicRank;
		this.musicImg = musicImg;
		this.realImg = realImg;
	}

	public Music(Integer genreId, String musicTitle, String musicDesc, String singer, Long musicDate,
			Integer musicLike, Integer musicRank, String musicImg, String realImg) {
		super();
		this.genreId = genreId;
		this.musicTitle = musicTitle;
		this.musicDesc = musicDesc;
		this.singer = singer;
		this.musicDate = musicDate;
		this.musicLike = musicLike;
		this.musicRank = musicRank;
		this.musicImg = musicImg;
		this.realImg = realImg;
	}
	
	public Music(Music music,String realImg) {
		super();
		this.genreId = music.genreId;
		this.musicTitle = music.musicTitle;
		this.musicDesc = music.musicDesc;
		this.singer = music.singer;
		this.musicDate = music.musicDate;
		this.musicLike = music.musicLike;
		this.musicRank = music.musicRank;
		this.musicImg = music.musicImg;	
		this.realImg = realImg;
	}

	public Music() {}

	@Override
	public String toString() {
		return "Music [musicId=" + musicId + ", genreId=" + genreId + ", musicTitle=" + musicTitle + ", musicDesc="
				+ musicDesc + ", singer=" + singer + ", musicDate=" + musicDate + ", musicLike=" + musicLike
				+ ", musicRank=" + musicRank + ", musicImg=" + musicImg + ", realImg=" + realImg + "]";
	}

}
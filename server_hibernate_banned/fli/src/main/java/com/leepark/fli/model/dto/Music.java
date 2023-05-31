package com.leepark.fli.model.dto;

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
@Entity
@Table(name="musics")
public class Music {
	@Id
    private Integer musicId;
	@JoinColumn(name = "genre_id", foreignKey = @ForeignKey(name = "FK_Genres_TO_musics_1"))
    private Integer genreId; // 0 or 1
	@Column(name="music_title")
    private String musicTitle;
	@Column(name="music_desc")
    private String musicDesc;
	@Column(name="music_date")
    private Long musicDate;
	@Column(name="music_like")
    private Integer musicLike;
	@Column(name="music_rank")
    private Integer musicRank;
	@Column(name="music_img")
    private String musicImg;
    
	@Transient
    private byte[] realImg=null;
    
	public Music(Integer musicId, Integer genreId, String musicTitle, String musicDesc, Long musicDate,
			Integer musicLike, Integer musicRank, String musicImg) {
		super();
		this.musicId = musicId;
		this.genreId = genreId;
		this.musicTitle = musicTitle;
		this.musicDesc = musicDesc;
		this.musicDate = musicDate;
		this.musicLike = musicLike;
		this.musicRank = musicRank;
		this.musicImg = musicImg;
	}
	
	public Music(Integer genreId, String musicTitle, String musicDesc, Long musicDate,
			Integer musicLike, Integer musicRank, String musicImg) {
		super();
		this.genreId = genreId;
		this.musicTitle = musicTitle;
		this.musicDesc = musicDesc;
		this.musicDate = musicDate;
		this.musicLike = musicLike;
		this.musicRank = musicRank;
		this.musicImg = musicImg;	
	}
	
	public Music(Music music,byte[] realImg) {
		super();
		this.genreId = music.genreId;
		this.musicTitle = music.musicTitle;
		this.musicDesc = music.musicDesc;
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
				+ musicDesc + ", musicDate=" + musicDate + ", musicLike=" + musicLike + ", musicRank=" + musicRank
				+ ", musicImg=" + musicImg + "]";
	}

}
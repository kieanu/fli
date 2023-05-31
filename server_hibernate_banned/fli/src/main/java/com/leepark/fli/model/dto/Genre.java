package com.leepark.fli.model.dto;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.*;

@Getter
@Setter
@Entity
@Table(name="genres")
public class Genre {
	@Id
    private Integer genreId;
	@Column(name="genre_name")
    private String genreName;
    
	public Genre(Integer genreId, String genreName) {
		super();
		this.genreId = genreId;
		this.genreName = genreName;
	}

	public Genre(String genreName) {
		super();
		this.genreName = genreName;
	}
	
	public Genre() {}

	@Override
	public String toString() {
		return "Genre [genreId=" + genreId + ", genreName=" + genreName + "]";
	}

}
package com.leepark.fli.model.firebase.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Notification {
    private String title;
    private String body;
    private String image;
    
    public Notification() {}
    
	public Notification(String title, String body, String image) {
		super();
		this.title = title;
		this.body = body;
		this.image = image;
	}	 
}

package com.leepark.fli.model.firebase.dto;

import java.util.Map;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Message {
    private Notification notification;
    private String token;
    private Map<String, String> data;
    
    public Message() {}
    
	public Message(Notification notification, String token) {
		super();
		this.notification = notification;
		this.token = token;
	}

	public Message(Notification notification, String token, Map<String, String> data) {
		super();
		this.notification = notification;
		this.token = token;
		this.data = data;
	}

}

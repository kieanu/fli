package com.leepark.fli.model.firebase.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FcmDataMessage {
    private boolean validate_only;
    private Message message;
    
    public FcmDataMessage() {}
    
    public FcmDataMessage(boolean validate_only, Message message) {
		super();
		this.validate_only = validate_only;
		this.message = message;
	}
}

package com.niit.collaborationPlatform.model;

import java.io.Serializable;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Message implements Serializable {

private static final long serialVersionUID = 1L;

public static Logger log= LoggerFactory.getLogger(Message.class);

public String message;

public String getMessage() {
	return message;
}

public void setMessage(String message) {
	this.message = message;
}

public Message(String message)
{
	this.message=message;
	log.debug("Message ====> Starting of the Message constructor()");
}

}

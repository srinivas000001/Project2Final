package com.niit.collaborationPlatform.model;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class OutputMessage extends Message {

public Logger log= LoggerFactory.getLogger(OutputMessage.class);

private Date Date_time;

	public Date getDate_time() {
	return Date_time;
}

public void setDate_time(Date date_time) {
	Date_time = date_time;
}

	public OutputMessage(Message message, Date Date_time) {
		super(message.getMessage());
		this.Date_time=Date_time;
		log.debug("OutputMessage ====> Ending of the OutputMessage constructor()");
	}

	

}

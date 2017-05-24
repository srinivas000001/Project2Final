package com.niit.collaborationPlatform.controller;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;

import com.niit.collaborationPlatform.model.Message;
import com.niit.collaborationPlatform.model.OutputMessage;

public class ChatForumController {

	public static Logger log= LoggerFactory.getLogger(ChatForumController.class);
	
	
	@MessageMapping("/chatForum")
	@SendTo("topic/message")
	public OutputMessage sendMessage(Message message)
	{
		log.debug("ChatForumController==> Starting the method sendMessage");
		log.debug("Message is : " +message.getMessage());
		log.debug("ChatForumController==> Ending the method sendMessage");
		return new OutputMessage(message, new Date());
	}
}

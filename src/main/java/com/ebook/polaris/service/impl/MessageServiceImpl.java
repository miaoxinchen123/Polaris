package com.ebook.polaris.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ebook.polaris.dao.MessageDao;
import com.ebook.polaris.dao.UserDao;
import com.ebook.polaris.model.Message;
import com.ebook.polaris.model.User;
import com.ebook.polaris.service.MessageService;
import com.ebook.polaris.service.UserService;

@Service("messageService")  
public class MessageServiceImpl implements MessageService {  
  
    @Autowired  
    private MessageDao messageDao;

	public String saveMessage(Message message) {
		return messageDao.save(message);
	}  
  
  
}  
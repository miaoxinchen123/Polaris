package com.ebook.polaris.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ebook.polaris.dao.CreditsDao;
import com.ebook.polaris.model.Credits;
import com.ebook.polaris.service.CreditsService;

@Service("creditsService")  
public class CreditsServiceImpl implements CreditsService {  
  
    @Autowired  
    private CreditsDao creditsDao;


	public String saveCredits(Credits credits) {
		return creditsDao.save(credits);
	}  
  
  
}  
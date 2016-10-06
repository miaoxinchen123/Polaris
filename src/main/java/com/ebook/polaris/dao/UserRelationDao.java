package com.ebook.polaris.dao;

import com.ebook.polaris.model.UserRelation;

public interface UserRelationDao extends DefaultDao<UserRelation, String> {  
	public UserRelation queryUseRelationBySelfUser(String  selfUser);
}  

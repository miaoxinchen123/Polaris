package com.ebook.polaris.model;


import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.GenericGenerator;  


@Entity  
@Table(name = "user_relation", catalog = "polaris")  
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)  
public class UserRelation {
	
	private String Id;  
	private String selfUser; 
	private String fatherUser;
	private Date createTime;  
	
    @Id @GeneratedValue(generator="system-uuid")  
    @GenericGenerator(name="system-uuid", strategy = "uuid")  
	public String getId() {
		return Id;
	}
	public void setId(String id) {
		Id = id;
	}
	
	@Column(name = "self_user")
	public String getSelfUser() {
		return selfUser;
	}
	public void setSelfUser(String selfUser) {
		this.selfUser = selfUser;
	}
	@Column(name = "father_user")
	public String getFatherUser() {
		return fatherUser;
	}
	public void setFatherUser(String fatherUser) {
		this.fatherUser = fatherUser;
	}
	
	@Temporal(TemporalType.TIMESTAMP)  
    @Column(name = "create_time", length = 19)  
	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
   
}

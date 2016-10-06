package com.ebook.polaris.model;


import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;  


@Entity  
@Table(name = "user", catalog = "polaris")  
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)  
public class User {
    private String email;  
    private String password;  
    private String name;  
    private String backEmail; 
    private String invitationCode ;
    private String codeStatus ; // yes 激活 no 未激活
    private Integer rewardCredits;
    private Integer credits;  
    private Date createTime;  
    private Date updateTime;
    
    @Id  
    @Column(name = "email", unique = true, nullable = false, length = 32)  
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
	@Column(name = "password", nullable = false, length = 32)  
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	@Column(name ="name")
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	@Column(name ="back_email")
	public String getBackEmail() {
		return backEmail;
	}
	public void setBackEmail(String backEmail) {
		this.backEmail = backEmail;
	}
	@Column(name ="invitation_code")
	public String getInvitationCode() {
		return invitationCode;
	}
	public void setInvitationCode(String invitationCode) {
		this.invitationCode = invitationCode;
	}  
	
	@Column(name ="code_status")
	public String getCodeStatus() {
		return codeStatus;
	}
	public void setCodeStatus(String codeStatus) {
		this.codeStatus = codeStatus;
	}
	
	@Column(name ="reward_credits")
	public Integer getRewardCredits() {
		return rewardCredits;
	}
	public void setRewardCredits(Integer rewardCredits) {
		this.rewardCredits = rewardCredits;
	}
	
	@Column(name ="credits")
	public Integer getCredits() {
		return credits;
	}
	public void setCredits(Integer credits) {
		this.credits = credits;
	}
	
	
	@Temporal(TemporalType.TIMESTAMP)  
    @Column(name = "create_time", length = 19)  
	public Date getCreateTime() {
		return createTime;
	}
	

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	
	@Temporal(TemporalType.TIMESTAMP)  
    @Column(name = "update_time", length = 19)  
	public Date getUpdateTime() {
		return updateTime;
	}
	
	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}
	
}

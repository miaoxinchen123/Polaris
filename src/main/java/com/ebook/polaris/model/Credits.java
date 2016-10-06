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
@Table(name = "credits", catalog = "polaris")  
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE) 
public class Credits {
	private String Id;  
	private String creditsType; 
	private String email;
	private String bizNo;
	private int credits;
	private Date createTime;  
    private Date updateTime;
	
    @Id @GeneratedValue(generator="system-uuid")  
    @GenericGenerator(name="system-uuid", strategy = "uuid")  
	public String getId() {
		return Id;
	}
	public void setId(String id) {
		Id = id;
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
	
	/**
	 * undo 就是没有操作过入库 
	 * 
	 */
	 @Column(name = "credits_type")
	public String getCreditsType() {
		return creditsType;
	}
	public void setCreditsType(String creditsType) {
		this.creditsType = creditsType;
	}
	
	 @Column(name = "email")
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	 @Column(name = "bizNo")
	public String getBizNo() {
		return bizNo;
	}
	public void setBizNo(String bizNo) {
		this.bizNo = bizNo;
	}
	
	 @Column(name = "credits")
	public int getCredits() {
		return credits;
	}
	public void setCredits(int credits) {
		this.credits = credits;
	}
}
	

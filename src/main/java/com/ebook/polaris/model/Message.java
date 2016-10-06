package com.ebook.polaris.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;
import org.hibernate.annotations.GenericGenerator;


@Entity  
@Table(name = "message", catalog = "polaris")  
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE) 
public class Message {
	private String Id;  
	private String contactType; 
	private String orderId;
	private String message;
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
	
	 @Column(name = "contact_type")
	public String getContactType() {
		return contactType;
	}
	public void setContactType(String contactType) {
		this.contactType = contactType;
	}
	 @Column(name = "order_id")
	public String getOrderId() {
		return orderId;
	}
	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}
	
	 @Column(name = "message")
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
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
	

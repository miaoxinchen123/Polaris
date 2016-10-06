package com.ebook.polaris.dto;

import java.util.Date;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;  

/**
 * 用户DTO 
 */
public class UserDto {
    private String email;  
    private String password;  
    private String name;  
    private String backEmail;  
    private Integer credits; 
    private Integer rewardCredits;  
    private Date createTime;  
    private Date updateTime;
    private int cartCnt;
    private int code; //200 成功状态   201 已有相同账户存在   202 登陆密码错误   203无登录用户
    public int getCode() {
		return code;
	}
	public void setCode(int code) {
		this.code = code;
	}
	private String meassage;
	public String getMeassage() {
		return meassage;
	}
	public void setMeassage(String meassage) {
		this.meassage = meassage;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getBackEmail() {
		return backEmail;
	}
	public void setBackEmail(String backEmail) {
		this.backEmail = backEmail;
	}
	public Integer getCredits() {
		return credits;
	}
	public void setCredits(Integer credits) {
		this.credits = credits;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public Date getUpdateTime() {
		return updateTime;
	}
	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}
	public int getCartCnt() {
		return cartCnt;
	}
	public void setCartCnt(int cartCnt) {
		this.cartCnt = cartCnt;
	}
	public Integer getRewardCredits() {
		return rewardCredits;
	}
	public void setRewardCredits(Integer rewardCredits) {
		this.rewardCredits = rewardCredits;
	}
    
}

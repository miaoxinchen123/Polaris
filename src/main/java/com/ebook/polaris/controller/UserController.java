package com.ebook.polaris.controller;

import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.jboss.netty.util.internal.StringUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.ebook.polaris.dto.UserDto;
import com.ebook.polaris.model.Credits;
import com.ebook.polaris.model.Message;
import com.ebook.polaris.model.Order;
import com.ebook.polaris.model.Order.OrderEnumType;
import com.ebook.polaris.model.User;
import com.ebook.polaris.service.CreditsService;
import com.ebook.polaris.service.MessageService;
import com.ebook.polaris.service.OrderService;
import com.ebook.polaris.service.UserRelationService;
import com.ebook.polaris.service.UserService;

@Controller  
public class UserController {  
  
    private static final Logger LOGGER = Logger.getLogger(UserController.class);  
      
    @Autowired  
    private UserService userService;  
    @Autowired  
    private OrderService orderService;  
    @Autowired
    private MessageService messageService;
    @Autowired  
    private CreditsService creditsService;
    @Autowired
    private UserRelationService userRelationService;
    
    private UserDto userDto;
      
    @RequestMapping(value="/user/addUser", method = {RequestMethod.POST })  
    public @ResponseBody UserDto addUser(@RequestBody User user){  
        LOGGER.info("查询用户：" + user);  
        userDto = new UserDto();
        User fatherUser = null;
       
        //如果有填写邀请码，首先对邀请码进行判断
        if(null != user.getInvitationCode() && ""!= user.getInvitationCode()){
        	fatherUser = userService.queryUserByInvitionCode(user.getInvitationCode());
        	if(null==fatherUser){
        		BeanUtils.copyProperties(user,userDto);  
            	userDto.setCode(202);
            	userDto.setMeassage("请输入正确的邀请码");
            	return userDto;  
        	}
        }
        
        User userInfo = userService.get(user.getEmail()); 
        if(null == userInfo){
        	//新建用户并保存
        	user.setBackEmail(user.getEmail());
        	user.setCreateTime(new Date());
        	user.setUpdateTime(new Date());
        	user.setRewardCredits(new Integer(0));
        	user.setCredits(new Integer(0));
        	//UUID前6位
        	user.setInvitationCode(UUID.randomUUID().toString().substring(0,8));
        	user.setCodeStatus("no");
        	userService.save(user,fatherUser);
        	BeanUtils.copyProperties(user,userDto); 
        	//新用户购物车显示0
        	userDto.setCartCnt(0);
        	userDto.setCode(200);
        	//使用权限工具进行用户登录，登录成功后跳到shiro配置的successUrl中  
        	SecurityUtils.getSubject().login(new UsernamePasswordToken(user.getEmail(), user.getPassword())); 
        }else{
        	BeanUtils.copyProperties(user,userDto);  
        	userDto.setCode(201);
        	userDto.setMeassage("该用户已经被注册");
        }
        return userDto;  
    }  
    
    @RequestMapping(value="/user/queryLoginStatus", method = {RequestMethod.GET })  
    public @ResponseBody UserDto queryLoginStatus(){
    	userDto = new UserDto();
    	Subject currentUser = SecurityUtils.getSubject(); 
    	if(null == currentUser.getPrincipal()){
    		userDto.setCode(203);
        }else{
        	//获取用户信息
        	User userInfo = userService.get(currentUser.getPrincipal().toString());
        	//查询购物车件数
        	List<Order> orders=orderService.queryOrderByEmail(userInfo.getEmail(), OrderEnumType.getCartEnum());
        	int cartCount ;
        	if(null == orders || orders.isEmpty()){
        		cartCount =0;
        	}else{
        		cartCount =orders.get(0).getOrders().size();
        	}
        	
        	BeanUtils.copyProperties(userInfo,userDto);  
        	userDto.setCartCnt(cartCount);
        	userDto.setCode(200);
        }
        return userDto;  
    }  
    
    @RequestMapping(value="/user/logIn", method = {RequestMethod.POST })  
    public @ResponseBody UserDto logIn(@RequestBody User user){  
        LOGGER.info("查询用户：" + user);  
        userDto = new UserDto();
        User userInfo = userService.queryUserByEmailAndPassWord(user); 
        if(null == userInfo){
        	userDto.setCode(203);
        }else{
        	BeanUtils.copyProperties(userInfo,userDto);  
        	//使用权限工具进行用户登录，登录成功后跳到shiro配置的successUrl中  
        	SecurityUtils.getSubject().login(new UsernamePasswordToken(user.getEmail(), user.getPassword())); 
        	//查询购物车件数
        	List<Order> orders=orderService.queryOrderByEmail(userInfo.getEmail(),  OrderEnumType.getCartEnum());
        	int cartCount ;
        	if(null == orders || orders.isEmpty()){
        		cartCount =0;
        	}else{
        		cartCount =orders.get(0).getOrders().size();
        	}
        	userDto.setCartCnt(cartCount);
        	userDto.setCode(200);
        }
        return userDto;  
    }  
    
    /**
     * 保存联系信息 
     */
    @RequestMapping(value="/user/saveMessage", method = {RequestMethod.POST })  
    public @ResponseBody String saveMessage(@RequestBody Message message){  
    	message.setCreateTime(new Date());
    	message.setUpdateTime(new Date());
    	messageService.saveMessage(message);
        return "保存成功";  
    }  
    
    /**
     * 保存提现请求 
     */
    @RequestMapping(value="/user/withdraw", method = {RequestMethod.GET })  
    public @ResponseBody String withDraw(){  
    	Message message = new Message();
    	message.setContactType("reward");
    	message.setCreateTime(new Date());
    	message.setMessage("提取奖励积分");
    	Subject currentUser = SecurityUtils.getSubject(); 
    	//当前用户的id作为订单号
    	message.setOrderId(currentUser.getPrincipal().toString());
    	messageService.saveMessage(message);
        return "积分奖励将于一个星期后发至付款支付宝账户中，请注意查收";  
    }  
    
    /**
     * 判断有无权限进入充值中心
     */
    @RequestMapping(value="/user/credits", method = {RequestMethod.GET })  
    public @ResponseBody String validateCredits(){  
    	return "credits";  
    }  
    

    /**
     * 进入充值中心
     */
    @RequestMapping(value="/loadCredits", method = {RequestMethod.GET })  
    public  String loadCredits(Model model){ 
    	return "views/credits";  
    }  
    
    /**
     * 判断有无权限进入个人中心
     */
    @RequestMapping(value="/user/profile", method = {RequestMethod.GET })  
    public @ResponseBody String validateProfile(){  
    	return "profile";  
    }  
    

    /**
     * 进入个人中心
     */
    @RequestMapping(value="/loadProfile", method = {RequestMethod.GET })  
    public  String loadProfile(Model model){ 
    	userDto = new UserDto();
    	Subject currentUser = SecurityUtils.getSubject(); 
		if(currentUser == null || currentUser.getPrincipal() ==null){
			return "index";
		}
		User userInfo = userService.get(currentUser.getPrincipal().toString());
		model.addAttribute("user",userInfo);
    	return "views/profile";  
    }  
    
    
    @RequestMapping(value="/updateUser", method = {RequestMethod.POST })  
    public  String updateUser(Model model,HttpServletRequest request){ 
    	Subject currentUser = SecurityUtils.getSubject(); 
    	User userInfo = userService.get(currentUser.getPrincipal().toString());
		String backEmail = request.getParameter("backEmail");  
		String password = request.getParameter("password"); 
		String name = request.getParameter("name");  
		userInfo.setBackEmail(backEmail);
		userInfo.setPassword(password);
		userInfo.setName(name);
    	userService.save(userInfo);
		model.addAttribute("user",userInfo);
    	return "views/profile";  
    }  
    
    
    /**
     * 积分充值
     */
    @RequestMapping(value="/creditsBuy", method = {RequestMethod.POST })  
    public  @ResponseBody String creditsBuy(@RequestBody Credits credits){ 
    	Subject currentUser = SecurityUtils.getSubject(); 
    	if(null == currentUser.getPrincipal()){
    		return "请重新登录";
    	}
    	credits.setCreditsType("undo");
    	credits.setEmail(currentUser.getPrincipal().toString());
    	credits.setCreateTime(new Date());
    	credits.setUpdateTime(new Date());
    	creditsService.saveCredits(credits);
    	return "校验中，积分将于第二天到账";  
    }  
    
    @RequestMapping(value="/logOut", method = {RequestMethod.GET })
    public  ModelAndView logOut(){
	   SecurityUtils.getSubject().logout();  
	   ModelAndView modelAndView = new ModelAndView("/index");  
       return modelAndView;   
    }  
}  

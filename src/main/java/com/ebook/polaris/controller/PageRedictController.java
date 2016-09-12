package com.ebook.polaris.controller;

import org.apache.shiro.SecurityUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

/**
 * 页面跳转专用controller 
 */

@Controller 
public class PageRedictController {
	
	@RequestMapping(value="/gotoIndex",method=RequestMethod.GET)
	public ModelAndView index(){  
        ModelAndView modelAndView = new ModelAndView("/index");  
        return modelAndView;  
    }  
	
	@RequestMapping(value="/help",method=RequestMethod.GET)
	public ModelAndView help(){  
        ModelAndView modelAndView = new ModelAndView("/views/help");  
        return modelAndView;  
    }  
	
	@RequestMapping(value="/readerDownload",method=RequestMethod.GET)
	public ModelAndView readDownload(){  
        ModelAndView modelAndView = new ModelAndView("/views/reader_download");  
        return modelAndView;  
    }  
	
  

}

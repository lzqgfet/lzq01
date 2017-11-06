package com.lzq.controller;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.lzq.pojo.Mobile;
import com.lzq.service.MobileService;



@Controller
public class MobileController {
	@Resource
	MobileService mobileService;
	
	@RequestMapping("/findmobile")
	public String getMobile(Model model,String mobilenum){
		Mobile mobile = mobileService.getmobile(mobilenum);
		String msg=null;
		if(mobile!=null){
			msg=mobilenum+"\t"+mobile.getMobileArea()+mobile.getMobileType();
			model.addAttribute("msg", msg);
		}else{
			msg=mobile+" "+"该号码未收录";
			model.addAttribute("msg", msg);
		}
		
		return "index";
	}
}

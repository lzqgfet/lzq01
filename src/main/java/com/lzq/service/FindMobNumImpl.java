package com.lzq.service;

import javax.annotation.Resource;
import javax.jws.WebMethod;
import javax.jws.WebService;

import org.springframework.stereotype.Service;

import com.lzq.dao.MobileDao;
import com.lzq.pojo.Mobile;

@WebService
@Service
public class FindMobNumImpl implements FindMobNum{
	@Resource
	MobileDao mobileDao;
	@WebMethod
	public String findnum(String num) {
		String number= num.substring(0, 7);
		Mobile mobile = mobileDao.getmobile(number);
		String msg=null;
		if(mobile!=null){
			msg=num+"\t"+mobile.getMobileArea()+mobile.getMobileType();
		}else{
			msg=num+"改号码未收录";
		}
		
		
		return msg;
	}
	

}

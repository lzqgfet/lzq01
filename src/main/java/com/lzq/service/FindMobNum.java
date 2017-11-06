package com.lzq.service;

import javax.jws.WebMethod;
import javax.jws.WebService;

@WebService
public interface FindMobNum {
	@WebMethod
	public String findnum(String num);

}

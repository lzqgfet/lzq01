package com.lzq.service;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.lzq.dao.MobileDao;
import com.lzq.pojo.Mobile;
@Service
public class MobileServiceImpl implements MobileService {
	@Resource
	MobileDao mobileDao;

	public Mobile getmobile(int num) {
		String s = String.valueOf(num);
		int number = Integer.parseInt(s.substring(0, 7));
		Mobile mobile = mobileDao.getmobile(number);
		return mobile;
	}

}

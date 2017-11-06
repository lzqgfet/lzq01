package com.lzq.dao;

import org.apache.ibatis.annotations.Param;

import com.lzq.pojo.Mobile;

public interface MobileDao {
	Mobile getmobile(@Param("num")String num);
}

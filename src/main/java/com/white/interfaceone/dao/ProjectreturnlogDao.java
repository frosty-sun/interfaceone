package com.white.interfaceone.dao;

import com.white.interfaceone.entity.Projectreturnlog;

public interface ProjectreturnlogDao {
	
	/**
	 * 
	 * @Title: selectAll 
	 * @Description: TODO(查询所有信息) 
	 * @param @param bak
	 * @param @return    设定文件 
	 * @return Bank    返回类型 
	 * @throws
	 */
	Projectreturnlog selectAll(Projectreturnlog projectreturnlog);
	
	/**
	 * 
	 * @Title: insertAll 
	 * @Description: TODO(新增订单) 
	 * @param @param bak
	 * @param @return    设定文件 
	 * @return int    返回类型 
	 * @throws
	 */
	int insertAll(Projectreturnlog projectreturnlog);
	
	

}

package com.white.interfaceone.dao;

import com.white.interfaceone.entity.RechargeWater;

public interface RechargeWaterDao {
	

	/**
	 * 
	 * @Title: selectAll 
	 * @Description: TODO(查询) 
	 * @param @param rechargeWater
	 * @param @return    设定文件 
	 * @return RechargeWater    返回类型 
	 * @throws
	 */
	RechargeWater selectSerial(RechargeWater rechargeWater);
	
	/**
	 * 
	 * @Title: selectPhone 
	 * @Description: TODO(根据手机号查询) 
	 * @param @param rechargeWater
	 * @param @return    设定文件 
	 * @return RechargeWater    返回类型 
	 * @throws
	 */
	RechargeWater selectPhone(RechargeWater rechargeWater);
	
	
	/**
	 * 
	 * @Title: insertAll 
	 * @Description: TODO(插入) 
	 * @param @param rechargeWater
	 * @param @return    设定文件 
	 * @return int    返回类型 
	 * @throws
	 */
	int insertAll(RechargeWater rechargeWater);
}

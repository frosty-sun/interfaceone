package com.white.interfaceone.dao;

import com.white.interfaceone.entity.User;

public interface UserDao {
	
	/**
	 * 
	 * @Title: selectByUserUUID 
	 * @Description: TODO(查询token) 
	 * @param @param user
	 * @param @return    设定文件 
	 * @return User    返回类型 
	 * @throws
	 */
	User selectByUserUUID(User user);
	
	/**
	 * 
	 * @Title: selectByAmount 
	 * @Description: TODO(余额查询) 
	 * @param @param user
	 * @param @return    设定文件 
	 * @return User    返回类型 
	 * @throws
	 */
	User selectByAmount(User user);
	
	/**
	 * 
	 * @Title: updateUserUUIDtime 
	 * @Description: TODO(更新token时间) 
	 * @param @param user
	 * @param @return    设定文件 
	 * @return int    返回类型 
	 * @throws
	 */
	int updateUserUUIDtime(User user);
	
	/**
	 * 
	* @Title: addUser 
	* @Description: TODO(注册) 
	* @param @param user
	* @param @return    设定文件 
	* @return int    返回类型 
	* @throws
	 */
	int insertUser(User user);
	
	/**
	 * 
	* @Title: insertUserDetection 
	* @Description: TODO(用户重复检测) 
	* @param @param user
	* @param @return    设定文件 
	* @return int    返回类型 
	* @throws
	 */
	int insertUserDetection(User user);
	
	/**
	 * 
	* @Title: delUser 
	* @Description: TODO(注销) 
	* @param @param accountNumber
	* @param @return    设定文件 
	* @return int    返回类型 
	* @throws
	 */
	int delUser(String accountNumber);
	
	/**
	 * 
	* @Title: updateUser 
	* @Description: TODO(修改) 
	* @param @param user
	* @param @return    设定文件 
	* @return int    返回类型 
	* @throws
	 */
	int updateUser(User user);
	/**
	 * 
	 * @Title: updateUser 
	 * @Description: TODO(修改) 
	 * @param @param user
	 * @param @return    设定文件 
	 * @return int    返回类型 
	 * @throws
	 */
	int updateUserError(User user);
	
	/**
	 * 
	* @Title: selectByUser 
	* @Description: TODO(查询) 
	* @param @param user
	* @param @return    设定文件 
	* @return User    返回类型 
	* @throws
	 */
	User selectByUser(String user);
	/**
	 * 
	* @Title: selectByLogIn 
	* @Description: TODO(登录) 
	* @param @param user
	* @param @return    设定文件 
	* @return String    返回类型 
	* @throws
	 */
	User selectByLogIn(String user);
	
	/**
	 * 
	 * @Title: selectByPay 
	 * @Description: TODO(查询支付密码) 
	 * @param @param payPasswd
	 * @param @return    设定文件 
	 * @return User    返回类型 
	 * @throws
	 */
	User selectByPay(User user);
	/**
	 * 
	 * @Title: selectByPay 
	 * @Description: TODO(查询支付密码) 
	 * @param @param payPasswd
	 * @param @return    设定文件 
	 * @return User    返回类型 
	 * @throws
	 */
	User selectByUserIdCard(User user);
	/**
	 * 
	 * @Title: selectByPay 
	 * @Description: TODO(查询支付密码相关信息) 
	 * @param @param payPasswd
	 * @param @return    设定文件 
	 * @return User    返回类型 
	 * @throws
	 */
	User selectByUserPayPassWd(User user);
	
	
	
	/**	
	 * 
	* @Title: updateLoginPassword 
	* @Description: TODO(修改登录密码) 
	* @param @return    设定文件 
	* @return int    返回类型 
	* @throws
	 */
	int updateLoginPassword(User user);
	
	/**
	 * 
	 * @Title: updatePaymentPassword 
	 * @Description: TODO(修改支付密码) 
	 * @param @return    设定文件 
	 * @return int    返回类型 
	 * @throws
	 */
	int updatePaymentPassword(User user);
	
	/**
	 * 
	 * @Title: updateAmount 
	 * @Description: TODO(充值消费) 
	 * @param @param user
	 * @param @return    设定文件 
	 * @return int    返回类型 
	 * @throws
	 */
	int updateAmount(User user);
	

}

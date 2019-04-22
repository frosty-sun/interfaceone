package com.white.interfaceone.dao;

import com.white.interfaceone.entity.UserLogin;

public interface UserLoginDao {
	
	/**
	 * 
	* @Title: addUser 
	* @Description: TODO(插入记录) 
	* @param @param user
	* @param @return    设定文件 
	* @return int    返回类型 
	* @throws
	 */
	int insertUserLogin(UserLogin userLogin);
	
	
	/**
	 * 
	* @Title: delUser 
	* @Description: TODO(销毁记录) 
	* @param @param accountNumber
	* @param @return    设定文件 
	* @return int    返回类型 
	* @throws
	 */
	int delUserLogin(String param);
	
	/**
	 * 
	* @Title: updateUser 
	* @Description: TODO(修改记录) 
	* @param @param user
	* @param @return    设定文件 
	* @return int    返回类型 
	* @throws
	 */
	int updateUserLogin(UserLogin userLogin);
	
	/**
	 * 
	* @Title: selectByUser 
	* @Description: TODO(查询记录) 
	* @param @param user
	* @param @return    设定文件 
	* @return User    返回类型 
	* @throws
	 */
	UserLogin selectByUserLogin(UserLogin userLogin);
	
	/**
	 * 
	 * @Title: selectByUserLoginCount 
	 * @Description: TODO(验证是否存在) 
	 * @param @return    设定文件 
	 * @return UserLogin    返回类型 
	 * @throws
	 */
	int selectByUserLoginCount(String phone);

}

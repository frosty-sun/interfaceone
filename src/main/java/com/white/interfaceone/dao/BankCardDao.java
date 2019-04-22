package com.white.interfaceone.dao;

import java.util.List;

import com.white.interfaceone.entity.BankCard;

public interface BankCardDao {
	/**
	 * 
	* @Title: addUser 
	* @Description: TODO(绑定银行卡) 
	* @param @param user
	* @param @return    设定文件 
	* @return int    返回类型 
	* @throws
	 */
	int insertBankCard(BankCard bankCard);
	
	/**
	 * 
	* @Title: delUser 
	* @Description: TODO(解绑银行卡) 
	* @param @param accountNumber
	* @param @return    设定文件 
	* @return int    返回类型 
	* @throws
	 */
	int delBankCard(BankCard bankCard);
	
	/**
	 * 
	* @Title: updateUser 
	* @Description: TODO(修改) 
	* @param @param user
	* @param @return    设定文件 
	* @return int    返回类型 
	* @throws
	 */
	int updateBankCard(BankCard bankCard);
	
	/**
	 * 
	 * @Title: selectByBankCard 
	 * @Description: TODO(这里用一句话描述这个方法的作用) 
	 * @param @param str
	 * @param @return    设定文件 
	 * @return BankCard    返回类型 
	 * @throws
	 */
	BankCard selectByBankCard(String str);

	/**
	 * 
	* @Title: selectByBankCardVerification 
	* @Description: TODO(验证银行卡) 
	* @param @param bankCard
	* @param @return    设定文件 
	* @return int    返回类型 
	* @throws
	 */
	int selectByBankCardVerification(BankCard bankCard);
	
	/**
	 * 
	 * @Title: selectCardCount 
	 * @Description: TODO(查询银行卡绑定数量) 
	 * @param @param bankCard
	 * @param @return    设定文件 
	 * @return int    返回类型 
	 * @throws
	 */
	int selectCardCount(BankCard bankCard);
	
	/**
	 * 
	 * @Title: selectByBankCardAll 
	 * @Description: TODO(个人查询绑定的银行卡) 
	 * @param @param phone
	 * @param @return    设定文件 
	 * @return List<BankCard>    返回类型 
	 * @throws
	 */
	List<BankCard> selectByBankCardAll(String phone);
}

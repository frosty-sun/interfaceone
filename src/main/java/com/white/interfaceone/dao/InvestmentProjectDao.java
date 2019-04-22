package com.white.interfaceone.dao;

import java.util.List;
import java.util.Map;

import com.white.interfaceone.entity.InvestmentProject;

public interface InvestmentProjectDao {
	
	/**
	 * 
	 * @Title: selectAll 
	 * @Description: TODO(查询所有) 
	 * @param @param investmentProject
	 * @param @return    设定文件 
	 * @return InvestmentProject    返回类型 
	 * @throws
	 */
	InvestmentProject selectAll();
	
	
	/**
	 * 
	 * @Title: selectProjectId 
	 * @Description: TODO(根据ProjectId查询) 
	 * @param @param investmentProject
	 * @param @return    设定文件 
	 * @return InvestmentProject    返回类型 
	 * @throws
	 */
	List<InvestmentProject> selectProjectId(InvestmentProject investmentProject);
	
	
	/**
	 * 
	 * @Title: selectAccount 
	 * @Description: TODO(根据Account查询) 
	 * @param @param investmentProject
	 * @param @return    设定文件 
	 * @return InvestmentProject    返回类型 
	 * @throws
	 */
	List<InvestmentProject> selectAccount(InvestmentProject investmentProject);
	
	
	/**
	 * 
	 * @Title: selectInvestmentId 
	 * @Description: TODO(这里用一句话描述这个方法的作用) 
	 * @param @param investmentProject
	 * @param @return    设定文件 
	 * @return InvestmentProject    返回类型 
	 * @throws
	 */
	InvestmentProject selectInvestmentId(InvestmentProject investmentProject);
	
	
	
	/**
	 * 
	 * @Title: insertAll 
	 * @Description: TODO(插入新数据) 
	 * @param @param investmentProject
	 * @param @return    设定文件 
	 * @return int    返回类型 
	 * @throws
	 */
	int insertAll(InvestmentProject investmentProject);
	
	
	/**
	 * 
	 * @Title: updateAll 
	 * @Description: TODO(更新数据) 
	 * @param @param investmentProject
	 * @param @return    设定文件 
	 * @return InvestmentProject    返回类型 
	 * @throws
	 */
	int updateAll(InvestmentProject investmentProject);
	
	/**
	 * 
	 * @Title: updatePaymentStatus 
	 * @Description: TODO(回款状态更新) 
	 * @param @param investmentProject
	 * @param @return    设定文件 
	 * @return int    返回类型 
	 * @throws
	 */
	int updatePaymentStatus(InvestmentProject investmentProject);

}

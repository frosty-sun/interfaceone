package com.white.interfaceone.dao;

import java.util.List;

import com.white.interfaceone.entity.Project;

public interface ProjectDao {
	
	/**
	 * 
	 * @Title: selectAll 
	 * @Description: TODO(查询全部) 
	 * @param @param project
	 * @param @return    设定文件 
	 * @return Project    返回类型 
	 * @throws
	 */
	List<Project> selectAll();
	
	/**
	 * 
	 * @Title: selectPaybackAll 
	 * @Description: TODO(回款项目过滤查询) 
	 * @param @return    设定文件 
	 * @return List<Project>    返回类型 
	 * @throws
	 */
	List<Project> selectPaybackAll();
	
	/**
	 * 
	 * @Title: selectProjectId 
	 * @Description: TODO(根据ID查询) 
	 * @param @param project
	 * @param @return    设定文件 
	 * @return Project    返回类型 
	 * @throws
	 */
	Project selectProjectId(Project project);
	
	/**
	 * 
	 * @Title: insertAll 
	 * @Description: TODO(插入新数据) 
	 * @param @param project
	 * @param @return    设定文件 
	 * @return int    返回类型 
	 * @throws
	 */
	int insertAll(Project project);
	
	/**
	 * 
	 * @Title: updateAll 
	 * @Description: TODO(更新数据) 
	 * @param @param project
	 * @param @return    设定文件 
	 * @return Project    返回类型 
	 * @throws
	 */
	int updateAll(Project project);
	
	/**
	 * 
	 * @Title: updateProjectStatus 
	 * @Description: TODO(更新状态) 
	 * @param @param project
	 * @param @return    设定文件 
	 * @return int    返回类型 
	 * @throws
	 */
	int updateProjectStatus(Project project);
	
	/**
	 * 
	 * @Title: updateAmount 
	 * @Description: TODO(修改剩余投资额) 
	 * @param @param project
	 * @param @return    设定文件 
	 * @return Project    返回类型 
	 * @throws
	 */
	int updateAmount(Project project);
	/**
	 * 
	 * @Title: updateReleaseTime 
	 * @Description: TODO(修改时间) 
	 * @param @param project
	 * @param @return    设定文件 
	 * @return int    返回类型 
	 * @throws
	 */
	int updateReleaseTime(Project project);
	

}

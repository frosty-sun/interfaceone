package com.white.interfaceone.util;

import java.io.*;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

 
/**
 * Desc:properties文件获取工具类
 * Created by 
 */
public class PropertyUtil {
	
	private static Logger logger= LoggerFactory.getLogger(PropertyUtil.class);
	
private static Properties prop;
	
	public static void load(String path){
		//这里的path是项目文件的绝对路径
		//先获取项目绝对路径：Thread.currentThread().getContextClassLoader().getResource("").getPath();
		//然后在项目路径后面拼接"properties/sysConfig.properties";
		//拼接路径
		path = Thread.currentThread().getContextClassLoader().getResource("").getPath() + path;
		prop= new Properties();// 属性集合对象   
		FileInputStream fis;  
        try {  
        	System.out.println(path);
        	fis = new FileInputStream(path);
            prop.load(fis);
            fis.close();// 关闭流  
        } catch (IOException e) {
            e.printStackTrace();  
        }
	}

	
	
    //参数为要修改的文件路径  以及要修改的属性名和属性值  
    public static Boolean updatePro(String path,String key,String value){
    	if(prop==null){
    		load(path);
    		
    		logger.info("修改前重新加载一遍");
    	}
    	logger.info("获取添加或修改前的属性值："+key+"=" + prop.getProperty(key));
        prop.setProperty(key, value);   
        // 文件输出流   
        try {  
            FileOutputStream fos = new FileOutputStream(path);
            // 将Properties集合保存到流中   
            prop.store(fos, "Copyright (c) Boxcode Studio");   
            fos.close();// 关闭流   
        } catch (IOException e) {
            // TODO Auto-generated catch block  
            e.printStackTrace();  
            return false;  
        }
        logger.info("获取添加或修改后的属性值："+key+"=" + prop.getProperty(key));
        return true;  
    }  
  
  //参数为要修改的文件路径  以及要修改的属性名和属性值  
    public static String getPro(String path,String key){  
    	if(prop==null){
    		load(path);
    		logger.info("重新加载一遍");
    	}
        FileInputStream fis;  
        try {  
            fis = new FileInputStream(path);  
            prop.load(fis);// 将属性文件流装载到Properties对象中   
            fis.close();// 关闭流  
        } catch (IOException e) {
            e.printStackTrace();  
        }
        logger.info("查询到的"+key+"的值："+prop.getProperty(key));
        return prop.getProperty(key);  
    }
    
	
  	public static void main(String[] args) {
  		System.out.println(updatePro("sequenceConfig.properties", "sequence", "000000002"));
  		System.out.println(getPro("sequenceConfig.properties", "sequence"));
  	}
      
    
    
    
    
}
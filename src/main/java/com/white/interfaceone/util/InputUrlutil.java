package com.white.interfaceone.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.white.interfaceone.controller.UserBankCarController;

public class InputUrlutil {
	
	
	private static final Logger log= LoggerFactory.getLogger(UserBankCarController.class);
	
	
	public InputUrlutil() {
		super();
	}

	public static String getPropertyByName(String path, String name) {
		String result = "";

		try {
			// 通过java.util.ResourceBundle读取资源属性文件
			result = java.util.ResourceBundle.getBundle(path).getString(name);
			log.info("读取成功，银行接口 url:" + result);
		} catch (Exception e) {
			log.error(e.getMessage());
		}
		return result;
	}
}


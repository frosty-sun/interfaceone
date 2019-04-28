package com.white.interfaceone.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.white.interfaceone.entity.User;
import com.white.interfaceone.entity.UserLogin;
import com.white.interfaceone.service.ResponseMessage;
import com.white.interfaceone.util.ConstantParam;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class UtilMethod {
	
	
	private static  Logger log = LoggerFactory.getLogger(UtilMethod.class);
	
	
	
	
	/**
	 * 
	 * @Title: parseJSONToMap 
	 * @Description: TODO(json字符串格式化为Map) 
	 * @param @param jsonStr
	 * @param @return    设定文件 
	 * @return Map<String,Object>    返回类型 
	 * @throws
	 */
	@SuppressWarnings("unchecked")
	public Map<String, Object> parseJSONToMap(String jsonStr){    
        Map<String, Object> map = new HashMap<String, Object>();    
        JSONObject json = JSONObject.fromObject(jsonStr);    
        for(Object k : json.keySet()){    
            Object v = json.get(k);     
            if(v instanceof JSONArray){    
                List<Map<String, Object>> list = new ArrayList<Map<String,Object>>();    
                Iterator<JSONObject> it = ((JSONArray)v).iterator();    
                while(it.hasNext()){    
                    JSONObject json2 = it.next();    
                    list.add(parseJSONToMap(json2.toString()));    
                }    
                map.put(k.toString(), list);    
            } else {    
                map.put(k.toString(), v);    
            }    
        }    
        return map;    
    }
	
	/**
	 * 
	 * @Title: randomCode 
	 * @Description: TODO(每次调用生成一个随机数) 
	 * @param @return    设定文件 
	 * @return String    返回类型 
	 * @throws
	 */
	public static String randomCode() {
		Random rand = new Random();
		int randNumber =rand.nextInt(1000000 - 100000 + 1) + 100000;
		return String.valueOf(randNumber);
	}
	
	
	/**
	 * 
	 * @Title: main 
	 * @Description: TODO(定时器方法) 
	 * @param @param args    设定文件 
	 * @return map    返回类型 返回手机号为K，V为验证码 
	 * @throws
	 */
	
	public static void userLonginStatus(UserLogin param) {
		Random rand = new Random();
		int randNumber = rand.nextInt(1000000 - 100000 + 1) + 100000;
		param.setSmsVerificationCode(String.valueOf(randNumber));
		/*Runnable runnable = new Runnable() {
			public void run() {  
                
        		
            }  
        };  
        ScheduledExecutorService service = Executors  
                .newSingleThreadScheduledExecutor();  
        // 第二个参数为首次执行的延时时间，第三个参数为定时执行的间隔时间(时间为S)  SECONDS为设定的时间单位
        service.scheduleAtFixedRate(runnable, 1, 5, TimeUnit.SECONDS);*/
	}
	
	/**
	 * 
	 * @Title: unixString 
	 * @Description: TODO(获取系统当前时间戳) 
	 * @param @return    设定文件 
	 * @return Long    返回类型 
	 * @throws
	 */
	public static Long unixString () {
		return System.currentTimeMillis()/ConstantParam.ONE_THOUSAND_UNIX;
	}
	
	/**
	 * 
	 * @Title: getdingdan 
	 * @Description: TODO(生成唯一识别的流水号) 
	 * @param @return    设定文件 
	 * @return String    返回类型 
	 * @throws
	 */
	public static String getdingdan() {
		Date date = new Date();
		SimpleDateFormat sf = new SimpleDateFormat();
		sf = new SimpleDateFormat("yyyyMMdd");
		return sf.format(date);
	}
	
	/**
	 * 
	 * @Title: getdingdan 
	 * @Description: TODO(获取当前时间) 
	 * @param @return    设定文件 
	 * @return String    返回类型 
	 * @throws
	 */
	public static String getTime() {
		Date date = new Date();
		SimpleDateFormat sf = new SimpleDateFormat();
		sf = new SimpleDateFormat("yyyy:MM:dd HH:mm:ss");
		return sf.format(date);
	}
	/**
	 * 
	 * @Title: getdingdan 
	 * @Description: TODO(时间戳转换为时间) 
	 * @param @return    设定文件 
	 * @return String    返回类型 
	 * @throws
	 */
	public static String getTime(Long date) {
		SimpleDateFormat sf = new SimpleDateFormat();
		sf = new SimpleDateFormat("yyyy.MM.dd HH:mm:ss");
		return sf.format(date * 1000);
	}
	
	
	/**
	 * 
	 * @Title: uuidMd5Encryption 
	 * @Description: TODO(生成唯一标识的UUI token加密值) 
	 * @param @return    设定文件 
	 * @return String    返回类型 
	 * @throws
	 */
	public static String uuidMd5Encryption() {
		//日志打印bug，泄露token风险
		log.info("生成的UUID_token为：" + java.util.UUID.randomUUID());
		log.info("生成的加密加盐后的UUID_token为：" + md5Encryption(String.valueOf(java.util.UUID.randomUUID())));
		return md5Encryption(String.valueOf(java.util.UUID.randomUUID()));
	}
	
	/**
	 * 
	 * @Title: md5Encryption 
	 * @Description: TODO(32位加密加盐方法) 
	 * @param @return    设定文件 
	 * @return String    返回类型 
	 * @throws
	 */
	public static String md5Encryption(String passWord) {
		try {
            // 得到一个信息摘要器
            MessageDigest digest = MessageDigest.getInstance("md5");
            byte[] result = digest.digest(passWord.getBytes());
            StringBuffer buffer = new StringBuffer();
            // 把每一个byte 做一个与运算 0xff;
            for (byte b : result) {
                // 与运算
                int number = b & 0xff;// 加盐
                String str = Integer.toHexString(number);
                if (str.length() == 1) {
                    buffer.append("0");
                }
                buffer.append(str);
            }
            // 标准的md5加密后的结果
            return buffer.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return null;
        }
	}
	
	/**
	 * 
	 * @Title: getCationNumber 
	 * @Description: TODO(计算实名认证次数) 
	 * @param @param user
	 * @param @param code    设定文件 
	 * @return void    返回类型 
	 * @throws
	 */
	public static void getCationNumber(User user, User param ,int code) {
		if (code == 1) {
			param.setCertificationCode(ConstantParam.PARAM_SUCCESSFUL_CERTIFICATION);
			param.setCertificationNumber(user.getCertificationNumber() + ConstantParam.PARAM_CODE_IN_CERTIFICATION);
			param.setCertificationTime(unixString());
		}else {
			param.setCertificationCode(ConstantParam.PARAM_AUTHENTIATION_FAILD);
			param.setCertificationNumber(user.getCertificationNumber() + ConstantParam.PARAM_CODE_IN_CERTIFICATION);
			param.setCertificationTime(unixString());
		}
	}
	
	
	/**
	 * 
	 * @Title: getUnixToTime 
	 * @Description: TODO(将秒转换为时间) 
	 * @param @param unix
	 * @param @return    设定文件 
	 * @return String    返回类型 
	 * @throws
	 */
	public static Map<String, String> getUnixToTime(Long unix) {
		Map<String, String> map = new HashMap<>();
		Long unix1 = 0L;
		Long unix2 = 0L;
		unix1 = unix/60;
		unix2 = unix1/60;
		log.info("执行转换方法：将" + unix + "秒转换为" + String.valueOf(unix2 + "小时" + unix1%60 + "分"+ unix%60 + "秒"));
		StringBuffer time = new StringBuffer();
		StringBuffer time2 = new StringBuffer();
		if (unix >= 3600) {
			time.append(unix2 + "小时");
			time2.append(unix2 + " hours, ");
		}
		if(unix >= 60){
			time.append(unix1%60 + "分");
			time2.append(unix1%60 + " minutes, ");
		}
		map.put(ConstantParam.PARAM_ENGLISH, String.valueOf(time.append(unix % 60 + "秒")));
		map.put(ConstantParam.PARAM_CHINESE, String.valueOf(time2.append(unix % 60 + " seconds")));
		return map;
	}
	
	
	/**
     * 利用正则表达式判断字符串是否是数字
     * @param str
     * @return
     */
    public static boolean isNumeric(String str){
           Pattern pattern = Pattern.compile("[0-9]*");
           Matcher isNum = pattern.matcher(str);
           if( !isNum.matches() ){
               return false;
           }
           return true;
    }
    
    /**
     * 
     * @Title: upperAndLowerLimits 
     * @Description: TODO(判断充值金额) 
     * @param @param param
     * @param @return    设定文件 
     * @return Map<String,Object>    返回类型 
     * @throws
     */
    public static Map<String, Object> upperAndLowerLimits(String param) {
    	Integer ite = Integer.valueOf(param);
    	if (ite > 50000) {
    		ResponseMessage.rechargeFailedOne6();
		}
    	if (ite < 0.01) {
    		ResponseMessage.rechargeFailedOne5();
		}
		return null;
    }
    
    /**
     * 
     * @Title: getTwoDecimal 
     * @Description: TODO(保留两位小数，四舍五入) 
     * @param @param num
     * @param @return    设定文件 
     * @return double    返回类型 
     * @throws
     */
    public static Double getTwoDecimal(Double num) {
        DecimalFormat dFormat=new DecimalFormat("#.00");
        String yearString=dFormat.format(num);
        Double temp= Double.valueOf(yearString);
        return temp;
   }
    
    
    /**
    * 获得该月最后一天
    * @param year
    * @param month
    * @return
    */
    public static String getLastDayOfMonth(int year,int month){
            Calendar cal = Calendar.getInstance();
            //设置年份
            cal.set(Calendar.YEAR,year);
            //设置月份
            cal.set(Calendar.MONTH, month-1);
            //获取某月最大天数
            int lastDay = cal.getActualMaximum(Calendar.DAY_OF_MONTH);
            //设置日历中月份的最大天数
            cal.set(Calendar.DAY_OF_MONTH, lastDay);
            //格式化日期
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            String lastDayOfMonth = sdf.format(cal.getTime());
            return lastDayOfMonth;
        }
    
}

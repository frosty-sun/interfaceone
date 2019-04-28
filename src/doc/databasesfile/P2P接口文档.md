#### 接口参数

##### 银行卡信息表（interfaceone_bank_card）

| 表字段           | 接口字段       | 翻译值     |
| ---------------- | -------------- | ---------- |
| PHONE            | phone          | 手机号     |
| ID_CARD          | idCard         | 身份证号   |
| CARDHOLDER_NAME  | cardholderName | 持卡人姓名 |
| BANK_NAME        | bankName       | 银行卡归属 |
| BANK_CARD_NUMBER | bankCardNumber | 银行卡号   |

##### 用户信息表（interfaceone_user）

| 表字段                | 接口字段              | 翻译值                                                       |
| --------------------- | --------------------- | ------------------------------------------------------------ |
| PASSWD                | passWd                | 密码                                                         |
| USER_NAME             | userName              | 用户姓名                                                     |
| ID_CARD               | idCard                | 身份证号                                                     |
| PHONE                 | phone                 | 手机号                                                       |
| BALANCE               | balance               | 余额                                                         |
| PAYMENT_PASSWORD      | paymentPassword       | 支付密码                                                     |
| CURRENTUNIX           | currentUnix           | 当前时间                                                     |
| STRUUID               | token                 | token                                                        |
| CERTIFICATIONCODE     | certificationCode     | 身份证绑定状态（未认证：0，认证中：1，认证成功：2，认证失败：3） |
| CERTIFICATIONNUMBER   | certificationNumber   | 身份证绑定次数                                               |
| CERTIFICATIONTIME     | certificationTime     | 身份证绑定操作时间                                           |
| CERTIFICATIONUUIDTIME | certificationUUIDTime | token最后一次操作时间                                        |

##### 投资项目表（investment_project）

| 表字段                 | 接口字段               | 翻译值     |
| ---------------------- | ---------------------- | ---------- |
| INVESTMENTID           | investmentId           | 投资ID     |
| INVESTMENTAMOUNT       | investmentAmount       | 投资金额   |
| INVESTMENTSERIALNUMBER | investmentSerialNumber | 投资流水号 |
| INVESTMENTPROJECTID    | investmentProjectId    | 投资项目ID |
| LINKEDACCOUNT          | linkedAccount          | 关联账户   |
| INVESTMENTTIME         | investmentTime         | 投资时间   |
| BIDDINGSTATUS          | biddingStatus          | 投标状态   |

##### 项目表（project）

| 表字段             | 接口字段           | 翻译值   |
| ------------------ | ------------------ | -------- |
| PROJECTNAME        | projectName        | 项目名称 |
| PROJECTID          | projectId          | 项目id   |
| TOTALPROJECT       | totalProject       | 项目金额 |
| REMAININGAMOUNT    | remainingAmount    | 剩余额度 |
| PROJECTDEADLINE    | projectDeadline    | 项目期限 |
| ANNUALINTERESTRATE | annualInterestRate | 年利率   |
| EXTRAINTERESTRATE  | extraInterestRate  | 额外利率 |
| VALIDITYPERIOD     | validityPeriod     | 有效期   |
| RELEASETIME        | releaseTime        | 发布时间 |
| ENTRYTIME          | entryTime          | 录入时间 |
| PROJECTSTATUS      | projectStatus      | 项目状态 |

##### 金额变动表（projectreturnlog）

| 表字段                 | 接口字段               | 翻译值   |
| ---------------------- | ---------------------- | -------- |
| SERIALNUMBER           | serialNumber           | 流水号   |
| TRANSACTIONTIME        | transactionTime        | 交易时间 |
| BANKCARDNUMBER         | bankCardNumber         | 银行卡号 |
| AMOUNTOFTHETRANSACTION | amountOfTheTransaction | 交易金额 |
| ORDERNUMBER            | orderNumber            | 订单号   |
| TRADINGSTATUS          | tradingStatus          | 交易状态 |

##### 平台对接银行交易信息表（rechargewater）

| 表字段                 | 接口字段               | 翻译值   |
| ---------------------- | ---------------------- | -------- |
| PHONE                  | phone                  | 手机号   |
| SERIALNUMBER           | serialNumber           | 流水号   |
| TRANSACTIONTIME        | transactionTime        | 交易时间 |
| AMOUNTOFTHETRANSACTION | amountOfTheTransaction | 交易金额 |
| TRADINGSTATUS          | tradingStatus          | 交易状态 |

##### 银行对接客户方充值日志信息表（transactionlog）

| 表字段                 | 接口字段               | 翻译值   |
| ---------------------- | ---------------------- | -------- |
| SERIALNUMBER           | serialNumber           | 流水号   |
| TRANSACTIONTIME        | transactionTime        | 交易时间 |
| BANKCARDNUMBER         | bankCardNumber         | 银行卡号 |
| AMOUNTOFTHETRANSACTION | amountOfTheTransaction | 交易金额 |
| ORDERNUMBER            | orderNumber            | 订单号   |
| TRADINGSTATUS          | tradingStatus          | 交易状态 |

##### 短信验证码数据存储表（user_login）

|                     |                     |                  |
| ------------------- | ------------------- | ---------------- |
| SMSVERIFICATIONCODE | smsVerificationCode | 短信验证码       |
| CURRENTUNIX         | currentUnix         | 当前系统的时间戳 |
| NUMBER              | number              | 验证码获取次数   |
| PHONE               | phone               | 手机号           |


#### 接口列表

##### 明文加密调试接口

```json
请求方式:POST
URL:http://192.168.3.210:8080/p2p/encryption
参数:
	{
		"pwd":"admin"
	}
响应:
	{
		"pwd":"21232f297a57a5a743894a0e4a801fc3",
		"statusCode":"200"
	}
```

##### 手机验证码（已完成）
```json
请求方式:POST
URL:http://192.168.3.210:8080/p2p/SMSCode
参数：
	{
		"phone":"17629196006"
	}
	响应:
	{
        "data": {
            "smsVerificationCode": "500460",
            "number": 1,
            "phone": "17629196006"
        },
        "message": "短信验证码已成功获取。验证码有效时间为5分钟！",
        "statusCode": "2000"
    }
```
##### 注册（已完成）登录密码为至少8位
```json
请求方式:POST
URL:http://192.168.3.210:8080/p2p/register
参数:
	{
		"phone":"17629196006",
		"passWd":"admin@123",
		"passWord":"admin@123",
		"smsVerificationCode":"23123"
	}
响应:
	{
		"message":"success",
		"statusCode":"2000"
	}
```

##### 登录（已完成）
```json
	请求方式:POST
	URL:http://192.168.3.210:8080/p2p/longin
	请求:
		{
			"phone":"17629196006",	
			"passWd":"admin@123"
		}
	响应:
		{
            "data": {
                "token": "e15372119240e6d05f4f6b14b25d7349"
            },
            "statusCode": "2000",
            "message": "登录成功！"
        }
```
##### 实名认证（已完成）
```json
	请求方式:POST
	URL:http://192.168.3.210:8080/p2p/realcertification
	请求:
		{
			"token":"8d9b71cda7dcef07f568cc5bdd62474a",
			"phone":"17629196006",
			"userName":"Jack",
			"idCard":"152221198006203989"
		}
	响应:
		{
  			"data": {
    			"certificationCode": 2
  			},
  			"message": "绑定成功！",
  			"statusCode": "2000"
		}
```
##### 设置交易密码（已实现）交易密码为强制6位数字，会进行验证
```json
	请求方式:POST
	URL:http://192.168.3.210:8080/p2p/newpaypd
	请求:
		{
			"token":"8d9b71cda7dcef07f568cc5bdd62474a",
			"phone":"17629196006",
			"newPaymentPassword":"123123"
		}
	响应:
		{
  			"message": "支付密码设置成功！",
  			"statusCode": "2000"
		}
```
##### 绑定银行卡（已完成）
```json
	请求方式:POST
	URL:http://192.168.3.210:8080/p2p/bindingbankcard
	请求:
		{
			"token":"8d9b71cda7dcef07f568cc5bdd62474a",
			"idCard":"152221198006203989",
			"phone":"17629196006",
			"bankName":"Construction bank",
			"cardholderName":"Jack",
			"bankCardNumber":"6217001210024455220",
			"paymentPassword":"123123"
		}
	响应:
		{
			"message":"银行卡绑定成功！",
			"statusCode":"2000"
		}
```
##### 充值（已完成）
- 其中数据库中的金额“+”在平台库代表充值操作，“-”代表提现操作，第三方库“+”代表从平台提现到银行卡，“-”代表从银行卡扣款到平台账户
```json
	请求方式:POST
	URL:http://192.168.3.210:8080/p2p/recharge
	请求:
		{
			"token":"8d9b71cda7dcef07f568cc5bdd62474a",
			"phone":"17629196006",
			"bankCardNumber":"6217001210024455220",
			"paymentPassword":"353435",
			"number":"100"
		}
	响应:
		{
			"message":"充值成功",
			"statusCode":"2000"
		}
```
##### 发布项目（已实现）
```json
	请求方式:POST
	URL:http://192.168.3.210:8080/p2p/proRelease
	请求:
		{
			"token":"dfb0156dad84515abb396057cd233837",
			"phone":"17629196006",
			"projectName":"理财项目4",
			"totalProject":"2000000",
			"projectDeadline":"90",
			"annualInterestRate":"0.063",
			"extraInterestRate":"0.033",
			"releaseTime":"1538323200"
		}
	响应:
		{
			"message":"项目信息录入成功.",
			"statusCode":"2000"
		}
```
##### 全量项目查询（已实现）
```json
	请求方式:GET
	URL:http://192.168.3.210:8080/InterfaceOne/queryPro
	请求:GET请求，无参数
	响应:
		{
			"statusCode":"2000",
			"message":"Projectinformationwasenteredsuccessfully.",
			"data":[
				{
					"projectName":"理财项目",
					"projectId":"3be5a55b-ad96-4e56-ad3f-1c7af1502b6e",
					"totalProject":2000000,
					"remainingAmount":2000000,
					"projectDeadline":90,
					"annualInterestRate":6.23,
					"extraInterestRate":2.33,
					"validityPeriod":"2018:12:3000:00:00",
					"releaseTime":"2018:10:0100:00:00",
					"entryTime":"2018:08:27 16:05:10"
				}
			]
		}
```
##### 单个项目ID查询（已实现）
```json
	请求方式:POST
	URL:http://192.168.3.210:8080/p2p/queryProId
	请求:
		{
			"token":"eeab77d26532dbdc80c1ac5d4d099aec",	
			"phone":"17629196006",									
			"projectId":"87d156c7-e0a9-4002-8aef-4149e774493a"		
		}
	响应:
		{
			"statusCode":"2000",
			"message":"项目信息查询成功.",
			"data":[
				{
					"projectName":"理财项目",						
					"projectId":"87d156c7-e0a9-4002-8aef-4149e774493a",
					"totalProject":2000000,
					"remainingAmount":2000000,
					"projectDeadline":90,
					"annualInterestRate":6.23,
					"extraInterestRate":2.33,
					"validityPeriod":"2019:03:01 00:00:00",
					"releaseTime":"2018:12:01 00:00:00",
					"entryTime":"2018:10:17 17:41:03"
				}
			]
		}
```
##### 投资（已实现）
```json
	请求方式:POST
	URL:http://192.168.3.210:8080/p2p/investment
	请求:
		{
			"investmentAmount":"200000",								
			"investmentProjectId":"87d156c7-e0a9-4002-8aef-4149e774493a",	
			"linkedAccount":"17629196006",									
			"token":"eeab77d26532dbdc80c1ac5d4d099aec",						
			"paymentPassword":"353435"									
		}
	响应:
		{
			"message":"项目投资成功.",
			"statusCode":"2000"
		}
```
##### 投资信息查询（已实现）
```json
	请求方式:POST
	URL:http://192.168.3.210:8080/p2p/queryinvestment
	请求:
		{
    		"phone":"17629196006",
    		"token":"57382853144a00cdbbb3609465fa06d0"
		}
	响应:
		{
 			"statusCode": "2000",
  			"message": "投资信息查询成功.",
  			"data": [
    			{
      				"investmentAmount": 20000,							
      				"investmentId": "29e69af2-a4ad-4aa9-8098-c682089e3e36",	
     				"investmentTime": "2018.10.17 17:54:49",				
      				"investmentSerialNumber": "100000020181017000000019",	
      				"investmentProject": "理财项目4",					
      				"biddingStatus": null 								
    			}
  			]
		}
```
##### 债权转让计算（已实现）
```json
	请求方式:POST
	URL:http://192.168.3.210:8080/p2p/transferCalculation
	请求:
		{
			"phone":"17629196006",
			"investmentId":"29e69af2-a4ad-4aa9-8098-c682089e3e36",	
			"token":"57382853144a00cdbbb3609465fa06d0"				
		}
	响应:
		{
  			"statusCode": "2000",
 			"message": "债权转让信息计算成功.",
  			"data": {
    			"principal": 20000,
    			"income": 36932.82,
    			"interest": 16932.82,
    			"investmentId": "29e69af2-a4ad-4aa9-8098-c682089e3e36",
    			"day": 3219
  			}
		}
```
##### 债权转让（已实现）
```json
	请求方式:POST
	URL:http://192.168.3.210:8080/p2p/transfer
	请求:
		{
            "phone":"17629196006",
			"token":"57382853144a00cdbbb3609465fa06d0",				
			"investmentId":"29e69af2-a4ad-4aa9-8098-c682089e3e36",	
			"principal":"20000",									
			"interest":"16932.82"								
		}
	响应:
		{
			"statusCode":"2000",
			"message":"债权转让成功，利息已同步提取到余额."
		}
```
##### 回款（已实现）
- 此方法为内部定时循环调用检查，不提供外部接口
##### 消费、提现（已实现）
- 其中数据库中的金额“+”在平台库代表充值操作，“-”代表提现操作，第三方库“+”代表从平台提现到银行卡，“-”代表从银行卡扣款到平台账户
```json
	请求方式:POST
	URL:http://192.168.3.210:8080/p2p/consumption
	请求:
		{
			"token":"57382853144a00cdbbb3609465fa06d0",	
			"phone":"17629196006",						
			"bankCardNumber":"6217001210024455220",		
			"paymentPassword":"353435",					
			"balance":"100"								
		}
	响应:
		{
  			"message": "提现成功",
  			"statusCode": "2000"
		}
```
##### 解绑卡（已实现）
```json
	请求方式:POST
	URL:http://192.168.3.210:8080/p2p/ubcard
	请求:
		{
			"token":"57382853144a00cdbbb3609465fa06d0",
			"phone":"17629196006",					
			"bankCardNumber":"6217001210024455220",		
			"paymentPassword":"123123"				
		}
	响应:
		{
			"message": "解绑银行卡成功!",
			"statusCode": "2000"
		}
```
##### 修改支付、登录密码
```json
	请求方式:POST
	URL:http://192.168.3.210:8080/p2p/chpwd
	请求:
		{
			"user":"17629196006",						
			"token":"dfb0156dad84515abb396057cd233837",
			"originalPasswd":"admin@123",			
			"newPasswd":"12345678",					
			"repeatPasswd":"12345678"				
		}
	响应:
		{
			"message":"修改密码成功.",
			"statusCode":"2000"
		}
```
##### 注销账号（删除账号信息）
```json
	请求方式:POST
	URL:http://192.168.3.210:8080/p2p/Logout
	请求:
		{
	    	"phone":"17629196006",
	    	"token":"eec53a5576569004f574216a3bc30916",
	    	"passWd":"admin@123"
		}

	响应:
		{
	  		"statusCode": "2000",
	  		"message": "Account cancellation is successful!"
		}
```

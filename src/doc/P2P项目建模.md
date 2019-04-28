## <center>目录</center>
 - ### 1. 模块清单
- [<h4 id="module-DB_REVERSE_MYSQL-from">1.1. 逆向解析\_MYSQL</h4>](#module-DB_REVERSE_MYSQL "DB_REVERSE_MYSQL")
	- [<h5 id="module-DB_REVERSE_MYSQL-relation}-from">1.1.1. 关联关系</h5>](#module-DB_REVERSE_MYSQL-relation "关联关系")
	- [<h5 id="module-DB_REVERSE_MYSQL-tableList-from">1.1.2. 表清单</h5>](#module-DB_REVERSE_MYSQL-tableList "表清单")
	- [<h5 id="module-DB_REVERSE_MYSQL-tableColumnList-from">1.1.3. 表列清单</h5>](#module-DB_REVERSE_MYSQL-tableColumnList "表列清单")
		- [<h6 id="module-DB_REVERSE_MYSQL-tableColumnList-interfaceone_bank_card-from">1.1.3.1 interfaceone\_bank\_card【银行卡信息表】</h6>](#module-DB_REVERSE_MYSQL-tableColumnList-interfaceone_bank_card "interfaceone_bank_card")
		- [<h6 id="module-DB_REVERSE_MYSQL-tableColumnList-interfaceone_user-from">1.1.3.2 interfaceone\_user【用户信息表】</h6>](#module-DB_REVERSE_MYSQL-tableColumnList-interfaceone_user "interfaceone_user")
		- [<h6 id="module-DB_REVERSE_MYSQL-tableColumnList-investment_project-from">1.1.3.3 investment\_project【投资项目表】</h6>](#module-DB_REVERSE_MYSQL-tableColumnList-investment_project "investment_project")
		- [<h6 id="module-DB_REVERSE_MYSQL-tableColumnList-project-from">1.1.3.4 project【项目表】</h6>](#module-DB_REVERSE_MYSQL-tableColumnList-project "project")
		- [<h6 id="module-DB_REVERSE_MYSQL-tableColumnList-projectreturnlog-from">1.1.3.5 projectreturnlog【金额变动表】</h6>](#module-DB_REVERSE_MYSQL-tableColumnList-projectreturnlog "projectreturnlog")
		- [<h6 id="module-DB_REVERSE_MYSQL-tableColumnList-rechargewater-from">1.1.3.6 rechargewater【平台对接银行交易信息表】</h6>](#module-DB_REVERSE_MYSQL-tableColumnList-rechargewater "rechargewater")
		- [<h6 id="module-DB_REVERSE_MYSQL-tableColumnList-transactionlog-from">1.1.3.7 transactionlog【银行对接客户方充值日志信息表】</h6>](#module-DB_REVERSE_MYSQL-tableColumnList-transactionlog "transactionlog")
		- [<h6 id="module-DB_REVERSE_MYSQL-tableColumnList-user_login-from">1.1.3.8 user\_login【短信验证码数据存储表】</h6>](#module-DB_REVERSE_MYSQL-tableColumnList-user_login "user_login")
  ---

### 1. 模块清单
 - [<h4 id="module-DB_REVERSE_MYSQL">1.1. 逆向解析_MYSQL</h4>](#module-DB_REVERSE_MYSQL-from)
 - [<h5 id="module-DB_REVERSE_MYSQL-relation">1.1.1 关联关系</h5>](#module-DB_REVERSE_MYSQL-relation-from)
 ---


![DB_REVERSE_MYSQL-关系图](./P2P项目建模_files/DB_REVERSE_MYSQL.png)

 ---

 - [<h5 id="module-DB_REVERSE_MYSQL-tableList">1.1.2 表清单</h5>](#module-DB_REVERSE_MYSQL-tableList-from)

 ---

| 名称 | 代码 | 备注 |
| ------------ | ------------ | ------------ |
| 银行卡信息表 | interfaceone\_bank\_card |  |
| 用户信息表 | interfaceone\_user |  |
| 投资项目表 | investment\_project |  |
| 项目表 | project |  |
| 金额变动表 | projectreturnlog |  |
| 平台对接银行交易信息表 | rechargewater |  |
| 银行对接客户方充值日志信息表 | transactionlog |  |
| 短信验证码数据存储表 | user\_login |  |

 ---

 - [<h5 id="module-DB_REVERSE_MYSQL-tableColumnList">1.1.3 表列清单</h5>](#module-DB_REVERSE_MYSQL-tableColumnList-from)

 ---

 - [<h6 id="module-DB_REVERSE_MYSQL-tableColumnList-interfaceone_bank_card">interfaceone_bank_card【银行卡信息表】</h6>](#module-DB_REVERSE_MYSQL-tableColumnList-interfaceone_bank_card-from)

| 代码 | 名称 | 数据类型(MYSQL) | 主键 | 备注 |
| ------------ | ------------ | ------------ | ------------ | ------------ |
| PHONE | 手机号 | VARCHAR(255) |  |  |
| ID\_CARD | 身份证号 | VARCHAR(255) |  |  |
| CARDHOLDER\_NAME | 持卡人姓名 | VARCHAR(255) |  |  |
| BANK\_NAME | 银行卡归属 | VARCHAR(255) |  |  |
| BANK\_CARD\_NUMBER | 银行卡号 | VARCHAR(255) |  |  |

 ---

 - [<h6 id="module-DB_REVERSE_MYSQL-tableColumnList-interfaceone_user">interfaceone_user【用户信息表】</h6>](#module-DB_REVERSE_MYSQL-tableColumnList-interfaceone_user-from)

| 代码 | 名称 | 数据类型(MYSQL) | 主键 | 备注 |
| ------------ | ------------ | ------------ | ------------ | ------------ |
| PASSWD | 密码 | VARCHAR(255) |  |  |
| USER\_NAME | 用户姓名 | VARCHAR(255) |  |  |
| ID\_CARD | 身份证号 | VARCHAR(18) |  |  |
| PHONE | 手机号 | VARCHAR(255) |  |  |
| BALANCE | 余额 | DOUBLE(18,2) |  |  |
| PAYMENT\_PASSWORD | 支付密码 | VARCHAR(255) |  |  |
| CURRENTUNIX | 当前时间 | BIGINT(19) |  |  |
| STRUUID | token | VARCHAR(255) |  |  |
| CERTIFICATIONCODE | 身份证绑定状态 | INT(10) |  |  |
| CERTIFICATIONNUMBER | 身份证绑定次数 | INT(10) |  |  |
| CERTIFICATIONTIME | 身份证绑定操作时间 | BIGINT(19) |  |  |
| CERTIFICATIONUUIDTIME | token最后一次操作时间 | BIGINT(19) |  |  |

 ---

 - [<h6 id="module-DB_REVERSE_MYSQL-tableColumnList-investment_project">investment_project【投资项目表】</h6>](#module-DB_REVERSE_MYSQL-tableColumnList-investment_project-from)

| 代码 | 名称 | 数据类型(MYSQL) | 主键 | 备注 |
| ------------ | ------------ | ------------ | ------------ | ------------ |
| INVESTMENTID | 投资ID | VARCHAR(255) | √ |  |
| INVESTMENTAMOUNT | 投资金额 | DOUBLE(22) |  |  |
| INVESTMENTSERIALNUMBER | 投资流水号 | VARCHAR(255) |  |  |
| INVESTMENTPROJECTID | 投资项目ID | VARCHAR(255) |  |  |
| LINKEDACCOUNT | 关联账户 | VARCHAR(255) |  |  |
| INVESTMENTTIME | 投资时间 | BIGINT(19) |  |  |
| BIDDINGSTATUS | 投标状态 | INT(10) |  |  |

 ---

 - [<h6 id="module-DB_REVERSE_MYSQL-tableColumnList-project">project【项目表】</h6>](#module-DB_REVERSE_MYSQL-tableColumnList-project-from)

| 代码 | 名称 | 数据类型(MYSQL) | 主键 | 备注 |
| ------------ | ------------ | ------------ | ------------ | ------------ |
| PROJECTNAME | 项目名称 | VARCHAR(255) |  |  |
| PROJECTID | 项目id | VARCHAR(255) |  |  |
| TOTALPROJECT | 项目金额 | DOUBLE(22) |  |  |
| REMAININGAMOUNT | 剩余额度 | DOUBLE(22) |  |  |
| PROJECTDEADLINE | 项目期限 | VARCHAR(255) |  |  |
| ANNUALINTERESTRATE | 年利率 | DOUBLE(22) |  |  |
| EXTRAINTERESTRATE | 额外利率 | DOUBLE(22) |  |  |
| VALIDITYPERIOD | 有效期 | VARCHAR(255) |  |  |
| RELEASETIME | 发布时间 | BIGINT(19) |  |  |
| ENTRYTIME | 录入时间 | BIGINT(19) |  |  |
| PROJECTSTATUS | 项目状态 | BIGINT(19) |  | 0为开始回款，4为已回款结束，其中1为未开始回款， |

 ---

 - [<h6 id="module-DB_REVERSE_MYSQL-tableColumnList-projectreturnlog">projectreturnlog【金额变动表】</h6>](#module-DB_REVERSE_MYSQL-tableColumnList-projectreturnlog-from)

| 代码 | 名称 | 数据类型(MYSQL) | 主键 | 备注 |
| ------------ | ------------ | ------------ | ------------ | ------------ |
| SERIALNUMBER | 流水号 | VARCHAR(255) |  |  |
| TRANSACTIONTIME | 交易时间 | VARCHAR(255) |  |  |
| BANKCARDNUMBER | 银行卡号 | VARCHAR(255) |  |  |
| AMOUNTOFTHETRANSACTION | 交易金额 | VARCHAR(255) |  |  |
| ORDERNUMBER | 订单号 | VARCHAR(255) |  |  |
| TRADINGSTATUS | 交易状态 | INT(10) |  | 0交易失败 1交易成功 |

 ---

 - [<h6 id="module-DB_REVERSE_MYSQL-tableColumnList-rechargewater">rechargewater【平台对接银行交易信息表】</h6>](#module-DB_REVERSE_MYSQL-tableColumnList-rechargewater-from)

| 代码 | 名称 | 数据类型(MYSQL) | 主键 | 备注 |
| ------------ | ------------ | ------------ | ------------ | ------------ |
| PHONE | 手机号 | VARCHAR(255) |  |  |
| SERIALNUMBER | 流水号 | VARCHAR(255) |  |  |
| TRANSACTIONTIME | 交易时间 | VARCHAR(255) |  |  |
| AMOUNTOFTHETRANSACTION | 交易金额 | VARCHAR(255) |  |  |
| TRADINGSTATUS | 交易状态 | INT(10) |  | 0交易失败 1交易成功 |

 ---

 - [<h6 id="module-DB_REVERSE_MYSQL-tableColumnList-transactionlog">transactionlog【银行对接客户方充值日志信息表】</h6>](#module-DB_REVERSE_MYSQL-tableColumnList-transactionlog-from)

| 代码 | 名称 | 数据类型(MYSQL) | 主键 | 备注 |
| ------------ | ------------ | ------------ | ------------ | ------------ |
| SERIALNUMBER | 流水号 | VARCHAR(255) |  |  |
| TRANSACTIONTIME | 交易时间 | VARCHAR(255) |  |  |
| BANKCARDNUMBER | 银行卡号 | VARCHAR(255) |  |  |
| AMOUNTOFTHETRANSACTION | 交易金额 | VARCHAR(255) |  |  |
| ORDERNUMBER | 订单号 | VARCHAR(255) |  |  |
| TRADINGSTATUS | 交易状态 | INT(10) |  | 0交易失败 1交易成功 |

 ---

 - [<h6 id="module-DB_REVERSE_MYSQL-tableColumnList-user_login">user_login【短信验证码数据存储表】</h6>](#module-DB_REVERSE_MYSQL-tableColumnList-user_login-from)

| 代码 | 名称 | 数据类型(MYSQL) | 主键 | 备注 |
| ------------ | ------------ | ------------ | ------------ | ------------ |
| SMSVERIFICATIONCODE | 短信验证码 | VARCHAR(255) |  |  |
| CURRENTUNIX | 当前系统的时间戳 | BIGINT(19) |  |  |
| NUMBER | 验证码获取次数 | INT(10) |  |  |
| PHONE | 手机号 | VARCHAR(255) |  |  |

 ---


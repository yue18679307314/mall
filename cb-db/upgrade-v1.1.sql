##add by guwenshao 2018-07-21

drop table IF EXISTS `order_loan_apply`;
CREATE TABLE `order_loan_apply` (
  `LOAN_ID` int(11) NOT NULL AUTO_INCREMENT COMMENT '贷款id',
  `LOAN_CODE` varchar(32) NOT NULL COMMENT '贷款编号',
  `CUSTOMER_ID` int(11) NOT NULL COMMENT '客户id',
  `ORDER_ID` int(11) NOT NULL COMMENT '订单id',
  `LOAN_STATE` int(11) DEFAULT NULL COMMENT '贷款状态 0:申请贷款 1:贷款通过 2:贷款失败3：取消贷款 ',
  `LOAN_PRICE` double DEFAULT NULL COMMENT '贷款金额',
  `AUDIT_REMARK` varchar(255) DEFAULT NULL COMMENT '审核备注',
  `AUDIT_STATE` int(11) NOT NULL COMMENT '审核状态 0:待审核 1:审核通过，2：审核失败',
  `CREATE_TIME` datetime DEFAULT NULL COMMENT '申请时间',
  `UPDATE_TIME` datetime DEFAULT NULL COMMENT '更新时间',
  `REMARK` varchar(255) DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`LOAN_ID`) USING BTREE,
  KEY `FK_oql18xgkuy2497dxxwiogy1o` (`CUSTOMER_ID`) USING BTREE,
  CONSTRAINT `loan_apply_ibfk_2` FOREIGN KEY (`ORDER_ID`) REFERENCES `order_form` (`ORDER_ID`),
  CONSTRAINT `loan_apply_ibfk_3` FOREIGN KEY (`CUSTOMER_ID`) REFERENCES `customer` (`CUSTOMER_ID`)
) ENGINE=InnoDB COMMENT='贷款申请表';

##add by guwenshao 2018-07-23
alter table product add `RESERVED_STORE_NUM` int(11) DEFAULT 0 COMMENT '预占的库存数';

##add by wangteng 2018-07-23
alter table customer add CARD_TYPE VARCHAR(32) NOT NULL COMMENT '证件类型';
alter table customer add CUSTOMER_CARD_NO VARCHAR(32) NOT NULL COMMENT '证件号码';
alter table customer add CARD_POSITIVE_IMG VARCHAR(255) NOT NULL COMMENT '证件证明照';
alter table customer add CARD_NEGATIVE_IMG VARCHAR(255) NOT NULL COMMENT '证件反面照';
alter table customer add BANK_CARD_IMG VARCHAR(255) NOT NULL COMMENT '银行卡图片';
alter table customer add CUSTOMER_LEVEL INT(10) DEFAULT 1  COMMENT '等级';
alter table customer add LEVEL_CODE VARCHAR(255) DEFAULT NULL COMMENT '等级编码';
alter table customer add INVITATION_CODE VARCHAR(25) DEFAULT NULL COMMENT '邀请码';

##add by likang 2018-07-24
DROP TABLE IF EXISTS `attachment`;
CREATE TABLE `attachment` (
  `ATTACH_ID` int(11) NOT NULL AUTO_INCREMENT,
  `OBJECT_TYPE` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '业务对象类型',
  `OBJECT_ID` int(11) NOT NULL COMMENT '业务对象ID',
  `BUSINESS_SCENARIO` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '业务应用场景编码',
  `INPUT_ID` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '文件名ID',
  `FILE_PATH` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '访问路径',
  `FILE_NAME` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '附件名称',
  `FILE_TYPE` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '文件类型',
  `FILE_SIZE` int(11) DEFAULT NULL COMMENT '文件大小',
  `FS_GUID` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '文件存储系统标识',
  `CREATE_TIME` datetime DEFAULT NULL ON UPDATE current_timestamp() COMMENT '上传时间',
  `STAFF_ID` int(11) DEFAULT NULL COMMENT '上传人ID',
  `STAFF_NAME` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '上传人',
  `STATE` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '状态',
  `DESCRIPTION` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '描述',
  PRIMARY KEY (`ATTACH_ID`)
) ENGINE=InnoDB;

###add by guwenshao 2018-07-25
drop table IF EXISTS `customer_trading_record`;
CREATE TABLE `customer_trading_record` (
  `TRADE_RECORD_ID` int(11) NOT NULL  AUTO_INCREMENT COMMENT '交易流水id',
  `CUSTOMER_ID` int(11) NOT NULL COMMENT '客户id',
  `BUSINESS_TYPE` int(11) NOT NULL COMMENT '业务类型:0余额，1:贷款预期收益 2:贷款额度 3:贷款金额',
  `OPERATION_TYPE` int(11) NOT NULL COMMENT '操作类型:0增加，1减少',
  `AMOUNT` double DEFAULT 0 COMMENT '操作余额',
  `CREATE_TIME` datetime DEFAULT NULL COMMENT '创建时间',
  `REMARK` varchar(255) DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`TRADE_RECORD_ID`) USING BTREE,
  CONSTRAINT `customer_trading_record_ibfk_1` FOREIGN KEY (`CUSTOMER_ID`) REFERENCES `customer` (`CUSTOMER_ID`)
) ENGINE=InnoDB COMMENT='客户交易流水表';

##add by pengcong 2018-07-25
DROP TABLE IF EXISTS `floor_brand`;
CREATE TABLE `floor_brand`  (
  `BRAND_ID` int(11) NOT NULL,
  `FLOOR_ID` int(11) NOT NULL,
  `SORT_ORDER` int(11) NOT NULL,
  PRIMARY KEY (`BRAND_ID`, `FLOOR_ID`) USING BTREE,
  INDEX `FK_7tcdsbs43j61a2b9rc46ow49k`(`FLOOR_ID`) USING BTREE,
  CONSTRAINT `floor_brand_ibfk_1` FOREIGN KEY (`FLOOR_ID`) REFERENCES `home_floor` (`FLOOR_ID`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `floor_brand_ibfk_2` FOREIGN KEY (`BRAND_ID`) REFERENCES `brand` (`BRAND_ID`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB;


ALTER TABLE home_floor add BRAND_AMOUNT int(11)  default (0) not null;
ALTER TABLE category ADD COLUMN `LOWEST_PRICE` decimal(5, 2) default (0) NOT NULL COMMENT '最低价格';
ALTER TABLE category ADD COLUMN `HIGHEST_PRICE` decimal(5, 2) default (0) NOT NULL COMMENT '最高价格';

##add by wangteng 2018-07-25
alter table customer add POLICY bit(1) DEFAULT 0 COMMENT '是否买过保单';
##add by likang 2018-07-26
ALTER TABLE commodity ADD COLUMN `EXPLAIN_CONTENT` VARCHAR(4098)   COMMENT '商品说明内容';

###add by guwenshao 2018-07-26
alter table customer_trading_record modify column TRADE_RECORD_ID int auto_increment;

###update by wangteng 2018-07-26
DROP TABLE IF EXISTS `customer_wallet`;
CREATE TABLE `customer_wallet` (
  `WALLET_ID` int(11) NOT NULL AUTO_INCREMENT,
  `CUSTOMER_ID` int(11) NOT NULL COMMENT '客户钱包id',
  `AVAILABLE_BALANCE` double DEFAULT 0 COMMENT '可用余额',
  `EXPECTED_RETURN_AMOUNT` double DEFAULT 0 COMMENT '预期收益金额',
  `LOAN_QUOTA` double DEFAULT 0 COMMENT '可贷额度',
  `ARREARS_AMOUNT` double DEFAULT 0 COMMENT '欠款金额',
  `CREATE_TIME` datetime DEFAULT NULL COMMENT '创建时间',
  `UPDATE_TIME` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`WALLET_ID`) USING BTREE,
  KEY `CUSTOMER_ID` (`CUSTOMER_ID`),
  CONSTRAINT `customer_wallet_ibfk_1` FOREIGN KEY (`CUSTOMER_ID`) REFERENCES `customer` (`CUSTOMER_ID`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='客户钱包表';
####################################生产环境 2018-07-27
###add by wangteng 2018-07-27
alter table customer add  CUSTOMER_COUNTRY VARCHAR(50) DEFAULT NULL COMMENT '国籍';
alter table customer add  CUSTOMER_CARD_PEROID date DEFAULT NULL COMMENT '证件有效期';
alter table customer add  OCCUPATIONAL_CATEGORY VARCHAR(50) DEFAULT NULL COMMENT '职业类别';


##add by likang 2018-07-28
ALTER TABLE profile ADD COLUMN `IS_PICTURE` int(2) DEFAULT 0   COMMENT '是否是图片';

##add by tanggangyi 2018-07-28 修改客户密码长度为64
ALTER TABLE `crystal_ball`.`customer`
MODIFY COLUMN `PASSWORD` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL AFTER `MOBILE_CHECKED`;

##add by guwenshao 2018-07-30 添加退货审核时间和退款时间
ALTER TABLE `product_return` add  `AUDIT_TIME` datetime DEFAULT NULL COMMENT '审核时间';
ALTER TABLE `product_return` add  `REFUND_TIME` datetime DEFAULT NULL COMMENT '退款时间';

##add by tangou 2018-07-30 16:13:05 添加默认货品id
ALTER TABLE commodity ADD  DEFAULT_PRODUCT_ID INT(11) DEFAULT NULL COMMENT '默认货品';

####add by wangteng 2018-07-30
DROP TABLE IF EXISTS `rb_reimbursement`;
CREATE TABLE `rb_reimbursement` (
  `REIMBURSEMENT_ID` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `REIMBURSEMENT_NO` varchar(21) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '报账单号',
  `CUSTOMER_ID` int(11) NOT NULL COMMENT '用户',
  `AMOUNT` decimal(10,2) DEFAULT NULL COMMENT '报账总金额',
  `TAX` decimal(10,2) DEFAULT NULL COMMENT '税',
  `ORDER_AMOUNT` decimal(10,2) DEFAULT NULL COMMENT '报账订单总金额',
  `ORDER_STATE` int(11) DEFAULT NULL COMMENT '状态',
  `CREATE_TIME` datetime DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`REIMBURSEMENT_ID`),
  KEY `CUSTOMER_ID` (`CUSTOMER_ID`),
  CONSTRAINT `rb_reimbursement_ibfk_1` FOREIGN KEY (`CUSTOMER_ID`) REFERENCES `customer` (`CUSTOMER_ID`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='报账信息表';

DROP TABLE IF EXISTS `insurance_log`;
CREATE TABLE `insurance_log` (
  `INSURANCE_LOG_ID` int(10) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `ORDER_ID` int(10) NOT NULL COMMENT '保单ID',
  `ORDER_CODE` varchar(30) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '保单编号',
  `INSURED_NAME` varchar(25) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '被保人姓名',
  `INSURED_MOBILE` varchar(25) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '被保人手机号码',
  `POLICYHOLDER_NAME` varchar(30) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '投保人姓名',
  `POLICYHOLDER_MOBILE` varchar(25) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '投保人手机号码',
  `PRICE` int(10) DEFAULT NULL COMMENT '投保金额',
  `ORDER_STATE` int(10) DEFAULT NULL COMMENT '操作状态',
  `IP_ADDRESS` varchar(30) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT 'ip地址',
  `CREATE_NAME` varchar(30) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '操作人',
  `CREATE_OPER` varchar(20) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '操作人账号',
  `CREATE_TIME` datetime DEFAULT NULL COMMENT '操作时间',
  PRIMARY KEY (`INSURANCE_LOG_ID`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='保单操作日志';


SET FOREIGN_KEY_CHECKS=0;

####add by likang 2018-07-31
DROP TABLE IF EXISTS `bank_info`;
CREATE TABLE `bank_info` (
  `BANK_ID` int(5) NOT NULL AUTO_INCREMENT,
  `BANK_CARD_NUMBER` varchar(60) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '银行卡号',
  `EFFECTIVE_TIME` datetime DEFAULT '0000-00-00 00:00:00' ON UPDATE current_timestamp() COMMENT '有效期',
  `CARDHOLDER` varchar(60) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '持卡人',
  `CARD_TYPE` varchar(60) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '证件类型',
  `CUSTOMER_CARD_NO` varchar(60) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '证件号',
  `MOBILE` varchar(22) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '手机号',
  `CUSTOMER_ID` int(10) NOT NULL COMMENT '客户ID',
  `CREATE_TIME` datetime NOT NULL DEFAULT '0000-00-00 00:00:00' ON UPDATE current_timestamp() COMMENT '创建时间',
  PRIMARY KEY (`BANK_ID`),
  KEY `bank_info_fk` (`CUSTOMER_ID`),
  CONSTRAINT `bank_info_fk` FOREIGN KEY (`CUSTOMER_ID`) REFERENCES `customer` (`CUSTOMER_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

####add by wangteng 2018-07-31
DROP TABLE IF EXISTS `rb_reimbursement_order`;
CREATE TABLE `rb_reimbursement_order` (
  `REIMBURSEMENT_ORDER_ID` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `ORDER_ITEM_ID` int(11) DEFAULT NULL COMMENT '订单',
  `REIMBURSEMENT_ID` int(11) DEFAULT NULL COMMENT '报账信息',
  `CREATE_TIME` datetime DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`REIMBURSEMENT_ORDER_ID`),
  KEY `REIMBURSEMENT_ID` (`REIMBURSEMENT_ID`),
  KEY `reimbursement_order_ibfk_3` (`ORDER_ITEM_ID`),
  CONSTRAINT `rb_reimbursement_order_ibfk_2` FOREIGN KEY (`REIMBURSEMENT_ID`) REFERENCES `rb_reimbursement` (`REIMBURSEMENT_ID`),
  CONSTRAINT `rb_reimbursement_order_ibfk_3` FOREIGN KEY (`ORDER_ITEM_ID`) REFERENCES `order_item` (`ITEM_ID`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='报账订单表';

DROP TABLE IF EXISTS `rb_reimbursement_process`;
CREATE TABLE `rb_reimbursement_process` (
  `PROCESS_ID` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `REIMBURSEMENT_ID` int(11) NOT NULL COMMENT '报账信息',
  `USER_ID` int(11) DEFAULT NULL COMMENT '操作人',
  `REMARKS` varchar(100) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '备注',
  `ORDER_STATE` int(11) DEFAULT NULL COMMENT '操作状态',
  `CREATE_TIME` datetime DEFAULT NULL COMMENT '操作时间',
  PRIMARY KEY (`PROCESS_ID`),
  KEY `REIMBURSEMENT_ID` (`REIMBURSEMENT_ID`),
  KEY `USER_ID` (`USER_ID`),
  CONSTRAINT `rb_reimbursement_process_ibfk_1` FOREIGN KEY (`REIMBURSEMENT_ID`) REFERENCES `rb_reimbursement` (`REIMBURSEMENT_ID`),
  CONSTRAINT `rb_reimbursement_process_ibfk_2` FOREIGN KEY (`USER_ID`) REFERENCES `user_info` (`USER_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='报账审批表'

####add by likang 2018-08-03
ALTER TABLE `profile` add  `REMARKS` VARCHAR(255) DEFAULT NULL COMMENT '备注';
##add by likang 2018-08-04
ALTER TABLE insurance_product ADD COLUMN `ENABLED` int(20) DEFAULT 0   COMMENT '是否启用';







####add by dengchenggang 2018-08-03

-- ----------------------------
-- Table structure for finacial_bill
-- ----------------------------
DROP TABLE IF EXISTS `finacial_bill`;
CREATE TABLE `finacial_bill`  (
  `BILL_ID` int(11) NOT NULL AUTO_INCREMENT,
  `CUSTOMER_ID` int(11) NOT NULL,
  `TYPE` int(2) NOT NULL COMMENT '资金类型：1.支出，2.收入',
  `TRANSACTION_TYPE` int(2) NOT NULL COMMENT '交易类型：1.商品购买，2.报帐，3.提现，4.贷款，5.还款',
  `TRANSACTION_DESC` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '交易描述',
  `TRANSACTION_ID` int(11) NOT NULL COMMENT '交易ID',
  `AMOUNT` decimal(20, 4) NOT NULL COMMENT '账单金额',
  `CREATE_TIME` datetime(0) NOT NULL COMMENT '交易时间',
  PRIMARY KEY (`BILL_ID`) USING BTREE,
  INDEX `fk_bill_customer_id`(`CUSTOMER_ID`) USING BTREE,
  CONSTRAINT `fk_bill_customer_id` FOREIGN KEY (`CUSTOMER_ID`) REFERENCES `customer` (`CUSTOMER_ID`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for finacial_loan
-- ----------------------------
DROP TABLE IF EXISTS `finacial_loan`;
CREATE TABLE `finacial_loan`  (
  `LOAN_ID` int(11) NOT NULL AUTO_INCREMENT,
  `CUSTOMER_ID` int(11) DEFAULT NULL,
  `AMOUNT` decimal(20, 4) DEFAULT NULL COMMENT '贷款金额',
  `TERM` int(11) DEFAULT NULL COMMENT '还款期数',
  `INTEREST_RATE` decimal(20, 4) DEFAULT NULL COMMENT '贷款利率',
  `TYPE` int(2) DEFAULT NULL COMMENT '贷款类型：1.信用贷款，2.预期收益贷',
  `REPAY_DAY` int(11) DEFAULT NULL COMMENT '每月几日还款',
  `STATE` int(11) DEFAULT NULL COMMENT '贷款状态：1.申请，2.审核，3.发放',
  `CREATE_TIME` datetime(0) DEFAULT NULL COMMENT '贷款日期',
  `UPDATE_TIME` datetime(0) DEFAULT NULL COMMENT '更新日期，审核为审核日期，发放为发放日期',
  PRIMARY KEY (`LOAN_ID`) USING BTREE,
  INDEX `fk_loan_customer_id`(`CUSTOMER_ID`) USING BTREE,
  CONSTRAINT `fk_loan_customer_id` FOREIGN KEY (`CUSTOMER_ID`) REFERENCES `customer` (`CUSTOMER_ID`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for finacial_repayment
-- ----------------------------
DROP TABLE IF EXISTS `finacial_repayment`;
CREATE TABLE `finacial_repayment`  (
  `REPAYMENT_ID` int(11) NOT NULL,
  `CUSTOMER_ID` int(11) DEFAULT NULL,
  `LOAN_ID` int(11) DEFAULT NULL,
  `AMOUNT` decimal(20, 4) DEFAULT NULL COMMENT '还款金额',
  `SEQ` int(11) DEFAULT NULL COMMENT '第几期还款',
  `LATE_FEE` decimal(20, 4) DEFAULT NULL COMMENT '还款滞纳金',
  `INTEREST` decimal(20, 4) DEFAULT NULL COMMENT '还款利息',
  `CREATE_TIME` datetime(0) DEFAULT NULL COMMENT '还款时间',
  PRIMARY KEY (`REPAYMENT_ID`) USING BTREE,
  INDEX `fk_repayment_customer_id`(`CUSTOMER_ID`) USING BTREE,
  INDEX `fk_repayment_loan_id`(`LOAN_ID`) USING BTREE,
  CONSTRAINT `fk_repayment_customer_id` FOREIGN KEY (`CUSTOMER_ID`) REFERENCES `customer` (`CUSTOMER_ID`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `fk_repayment_loan_id` FOREIGN KEY (`LOAN_ID`) REFERENCES `finacial_loan` (`LOAN_ID`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for finacial_wallet
-- ----------------------------
DROP TABLE IF EXISTS `finacial_wallet`;
CREATE TABLE `finacial_wallet`  (
  `WALLET_ID` int(11) NOT NULL AUTO_INCREMENT,
  `CUSTOMER_ID` int(11) NOT NULL,
  `ASSETS` decimal(20, 4) NOT NULL COMMENT '资产总额',
  `BALANCE` decimal(20, 4) NOT NULL COMMENT '可用余额',
  `EXPECTED_AMOUNT` decimal(20, 4) NOT NULL COMMENT '预期收益',
  `DEBT_TOTAL` decimal(20, 4) NOT NULL COMMENT '总负责',
  `DEBT_EXPECTED` decimal(20, 4) NOT NULL COMMENT '预期收益贷',
  `DEBT_CREDIT` decimal(20, 4) NOT NULL COMMENT '信用贷',
  `CREDIT_AMOUNT` decimal(20, 4) NOT NULL COMMENT '信用额度',
  `FREEZING_AMOUNT` decimal(20, 4) NOT NULL COMMENT '冻结金额，例如提现未到账，资金划拨未审核等',
  `VERSION` int(11) NOT NULL COMMENT '版本号',
  PRIMARY KEY (`WALLET_ID`) USING BTREE,
  INDEX `fk_wallet_customer_id`(`CUSTOMER_ID`) USING BTREE,
  CONSTRAINT `fk_wallet_customer_id` FOREIGN KEY (`CUSTOMER_ID`) REFERENCES `customer` (`CUSTOMER_ID`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for finacial_wallet_log
-- ----------------------------
DROP TABLE IF EXISTS `finacial_wallet_log`;
CREATE TABLE `finacial_wallet_log`  (
  `WALLET_LOG_ID` int(11) NOT NULL,
  `WALLET_ID` int(11) NOT NULL,
  `CUSTOMER_ID` int(11) NOT NULL,
  `ASSETS` decimal(20, 4) NOT NULL COMMENT '资产总额',
  `BALANCE` decimal(20, 4) NOT NULL COMMENT '可用余额',
  `EXPECTED_AMOUNT` decimal(20, 4) NOT NULL COMMENT '预期收益',
  `DEBT_TOTAL` decimal(20, 4) NOT NULL COMMENT '总负债',
  `DEBT_EXPECTED` decimal(20, 4) NOT NULL COMMENT '预期收益贷',
  `DEBT_CREDIT` decimal(20, 4) NOT NULL COMMENT '信用贷',
  `CREDIT_AMOUNT` decimal(20, 4) NOT NULL COMMENT '信用额度',
  `FREEZING_AMOUNT` decimal(20, 4) NOT NULL COMMENT '冻结金额，例如提现未到账，资金划拨未审核等',
  `TYPE` int(2) NOT NULL COMMENT '操作类型',
  `AMOUNT` decimal(20, 4) NOT NULL COMMENT '变动金额',
  `VERSION` int(11) NOT NULL COMMENT '版本号',
  PRIMARY KEY (`WALLET_LOG_ID`) USING BTREE,
  INDEX `fk_wallet_log_wallet_id`(`WALLET_ID`) USING BTREE,
  INDEX `fk_wallet_log_customer_id`(`CUSTOMER_ID`) USING BTREE,
  CONSTRAINT `fk_wallet_log_customer_id` FOREIGN KEY (`CUSTOMER_ID`) REFERENCES `customer` (`CUSTOMER_ID`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `fk_wallet_log_wallet_id` FOREIGN KEY (`WALLET_ID`) REFERENCES `customer` (`CUSTOMER_ID`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for finacial_withdraw
-- ----------------------------
DROP TABLE IF EXISTS `finacial_withdraw`;
CREATE TABLE `finacial_withdraw`  (
  `WITHDRAW_ID` int(11) NOT NULL AUTO_INCREMENT,
  `CUSTOMER_ID` int(11) NOT NULL,
  `AMOUNT` decimal(20, 4) NOT NULL COMMENT '提现金额',
  `BANK_CARD_NO` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '关联银行卡号',
  `BANK_NAME` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '银行名称',
  `BRANCH_NAME` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '支行名称，可选',
  `CARD_HOLD_NAME` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '持卡人姓名',
  `STATE` int(11) DEFAULT NULL COMMENT '状态',
  `APPLY_DATE` datetime(0) DEFAULT NULL COMMENT '申请时间',
  `AUDIT_DATE` datetime(0) DEFAULT NULL COMMENT '审核时间',
  `GRANT_DATE` datetime(0) DEFAULT NULL COMMENT '发放时间',
  `AUDIT_OPERATOR` varchar(40) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '审核员',
  `GRANT_OPERATOR` varchar(40) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '发放元',
  `AUDIT_MESSAGE` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '审核意见',
  PRIMARY KEY (`WITHDRAW_ID`) USING BTREE,
  INDEX `fk_withdraw_customer_id`(`CUSTOMER_ID`) USING BTREE,
  CONSTRAINT `fk_withdraw_customer_id` FOREIGN KEY (`CUSTOMER_ID`) REFERENCES `customer` (`CUSTOMER_ID`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for rb_funds_pool
-- ----------------------------
DROP TABLE IF EXISTS `rb_funds_pool`;
CREATE TABLE `rb_funds_pool`  (
  `POOL_ID` int(11) NOT NULL AUTO_INCREMENT,
  `CATEGORY_ID` int(11) NOT NULL COMMENT '一级运营分类ID',
  `POOL_NAME` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '分类名+“资金池”',
  `FUNDS` decimal(20, 4) NOT NULL COMMENT '资金',
  `VERSION` int(11) NOT NULL COMMENT '版本号',
  PRIMARY KEY (`POOL_ID`) USING BTREE,
  INDEX `fk_fund_pool_category_id`(`CATEGORY_ID`) USING BTREE,
  CONSTRAINT `fk_fund_pool_category_id` FOREIGN KEY (`CATEGORY_ID`) REFERENCES `category` (`CATEGORY_ID`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for rb_funds_pool_log
-- ----------------------------
DROP TABLE IF EXISTS `rb_funds_pool_log`;
CREATE TABLE `rb_funds_pool_log`  (
  `POOL_LOG_ID` int(11) NOT NULL AUTO_INCREMENT,
  `POOL_ID` int(11) NOT NULL,
  `CATEGORY_ID` int(11) NOT NULL COMMENT '分类ID',
  `POOL_NAME` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '分类名+“资金池”',
  `FUNDS` decimal(20, 4) NOT NULL,
  `TYPE` int(2) NOT NULL COMMENT '类型：1.累计，2.报帐',
  `TRANSACTION_ID` int(11) NOT NULL COMMENT '交易ID,累计为订单号，报帐为报帐ID',
  `COMMODITY_ID` int(11) NOT NULL COMMENT '商品ID',
  `AMOUNT` decimal(20, 4) NOT NULL COMMENT '金额',
  `CREATE_TIME` datetime(0) NOT NULL COMMENT '操作时间',
  `VERSION` int(11) NOT NULL COMMENT '版本号',
  PRIMARY KEY (`POOL_LOG_ID`) USING BTREE,
  INDEX `fk_funds_pool_log_pool_id`(`POOL_ID`) USING BTREE,
  CONSTRAINT `fk_funds_pool_log_pool_id` FOREIGN KEY (`POOL_ID`) REFERENCES `rb_funds_pool` (`POOL_ID`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for rb_reimbursement
-- ----------------------------
DROP TABLE IF EXISTS `rb_reimbursement`;
CREATE TABLE `rb_reimbursement`  (
  `REIMBURSEMENT_ID` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `REIMBURSEMENT_NO` varchar(21) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '报账单号',
  `CUSTOMER_ID` int(11) NOT NULL COMMENT '用户',
  `AMOUNT` decimal(10, 2) DEFAULT NULL COMMENT '报账总金额',
  `TAX` decimal(10, 2) DEFAULT NULL COMMENT '税',
  `ORDER_AMOUNT` decimal(10, 2) DEFAULT NULL COMMENT '报账订单总金额',
  `ORDER_STATE` int(11) DEFAULT NULL COMMENT '状态',
  `CREATE_TIME` datetime(0) DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`REIMBURSEMENT_ID`) USING BTREE,
  INDEX `CUSTOMER_ID`(`CUSTOMER_ID`) USING BTREE,
  CONSTRAINT `rb_reimbursement_ibfk_1` FOREIGN KEY (`CUSTOMER_ID`) REFERENCES `customer` (`CUSTOMER_ID`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci COMMENT = '报账信息表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of rb_reimbursement
-- ----------------------------

-- ----------------------------
-- Table structure for rb_reimbursement_order
-- ----------------------------
DROP TABLE IF EXISTS `rb_reimbursement_order`;
CREATE TABLE `rb_reimbursement_order`  (
  `REIMBURSEMENT_ORDER_ID` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `ORDER_ITEM_ID` int(11) DEFAULT NULL COMMENT '订单',
  `REIMBURSEMENT_ID` int(11) DEFAULT NULL COMMENT '报账信息',
  `CREATE_TIME` datetime(0) DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`REIMBURSEMENT_ORDER_ID`) USING BTREE,
  INDEX `REIMBURSEMENT_ID`(`REIMBURSEMENT_ID`) USING BTREE,
  INDEX `reimbursement_order_ibfk_3`(`ORDER_ITEM_ID`) USING BTREE,
  CONSTRAINT `rb_reimbursement_order_ibfk_2` FOREIGN KEY (`REIMBURSEMENT_ID`) REFERENCES `rb_reimbursement` (`REIMBURSEMENT_ID`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `rb_reimbursement_order_ibfk_3` FOREIGN KEY (`ORDER_ITEM_ID`) REFERENCES `order_item` (`ITEM_ID`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of rb_reimbursement_order
-- ----------------------------

-- ----------------------------
-- Table structure for rb_reimbursement_process
-- ----------------------------
DROP TABLE IF EXISTS `rb_reimbursement_process`;
CREATE TABLE `rb_reimbursement_process`  (
  `PROCESS_ID` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `REIMBURSEMENT_ID` int(11) NOT NULL COMMENT '报账信息',
  `USER_ID` int(11) DEFAULT NULL COMMENT '操作人',
  `REMARKS` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '备注',
  `ORDER_STATE` int(11) DEFAULT NULL COMMENT '操作状态',
  `CREATE_TIME` datetime(0) DEFAULT NULL COMMENT '操作时间',
  PRIMARY KEY (`PROCESS_ID`) USING BTREE,
  INDEX `REIMBURSEMENT_ID`(`REIMBURSEMENT_ID`) USING BTREE,
  INDEX `USER_ID`(`USER_ID`) USING BTREE,
  CONSTRAINT `rb_reimbursement_process_ibfk_1` FOREIGN KEY (`REIMBURSEMENT_ID`) REFERENCES `rb_reimbursement` (`REIMBURSEMENT_ID`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `rb_reimbursement_process_ibfk_2` FOREIGN KEY (`USER_ID`) REFERENCES `user_info` (`USER_ID`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci COMMENT = '报账审批表' ROW_FORMAT = Dynamic;



####add by tanggangyi 2018-08-04
ALTER TABLE `crystal_ball`.`customer`
MODIFY COLUMN `CARD_TYPE` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL COMMENT '证件类型' AFTER `PRAISE_NUM`,
MODIFY COLUMN `CUSTOMER_CARD_NO` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL COMMENT '证件号码' AFTER `CARD_TYPE`,
MODIFY COLUMN `CARD_POSITIVE_IMG` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL COMMENT '证件证明照' AFTER `CUSTOMER_CARD_NO`,
MODIFY COLUMN `CARD_NEGATIVE_IMG` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL COMMENT '证件反面照' AFTER `CARD_POSITIVE_IMG`,
MODIFY COLUMN `BANK_CARD_IMG` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL COMMENT '银行卡图片' AFTER `CARD_NEGATIVE_IMG`;

###add by wangteng 2018-08-06
DROP TABLE IF EXISTS `customer_group`;
CREATE TABLE `customer_group` (
  `CUSTOMER_GROUP_ID` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `CUSTOMER_ID` int(11) NOT NULL COMMENT '创建人',
  `GROUP_NAME` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '群名称',
  `CREATE_TIME` datetime DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`CUSTOMER_GROUP_ID`),
  KEY `CUSTOMER_ID` (`CUSTOMER_ID`),
  CONSTRAINT `customergroup_ibfk_1` FOREIGN KEY (`CUSTOMER_ID`) REFERENCES `customer` (`CUSTOMER_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT ='用户群组';

DROP TABLE IF EXISTS `customer_group_user`;
CREATE TABLE `customer_group_user` (
  `CUSTOMER_GROUP_USER_ID` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `CUSTOMER_GROUP_ID` int(11) DEFAULT NULL COMMENT '群组',
  `CUSTOMER_ID` int(11) DEFAULT NULL COMMENT '用户',
  `CREATE_TIME` datetime DEFAULT NULL COMMENT '加入时间',
  PRIMARY KEY (`CUSTOMER_GROUP_USER_ID`),
  KEY `CUSTOMER_GROUP_ID` (`CUSTOMER_GROUP_ID`),
  KEY `CUSTOMER_ID` (`CUSTOMER_ID`),
  CONSTRAINT `customer_group_user_ibfk_1` FOREIGN KEY (`CUSTOMER_GROUP_ID`) REFERENCES `customer_group` (`CUSTOMER_GROUP_ID`) ON UPDATE NO ACTION,
  CONSTRAINT `customer_group_user_ibfk_2` FOREIGN KEY (`CUSTOMER_ID`) REFERENCES `customer` (`CUSTOMER_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT ='用户群组成员';

####add by likang 2018-08-06
alter table feedback modify column images varchar(9000);

##add by tangou 2018-08-06
ALTER TABLE commodity ADD COLUMN `SETTING_CONTENT` varchar(4098)  COMMENT '商品配置内容';

##add by tangou 2018-08-07
ALTER TABLE bank_info ADD COLUMN `BANK_NAME` varchar(60)  COMMENT '银行名称';


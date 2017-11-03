/*
Navicat MySQL Data Transfer

Source Server         : 开发192.168.1.17
Source Server Version : 50624
Source Host           : 192.168.1.17:3306
Source Database       : wsdb

Target Server Type    : MYSQL
Target Server Version : 50624
File Encoding         : 65001

Date: 2017-11-03 10:37:00
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for biz_direct_payinfo
-- ----------------------------
DROP TABLE IF EXISTS `biz_direct_payinfo`;
CREATE TABLE `biz_direct_payinfo` (
  `id` varchar(64) CHARACTER SET utf8mb4 NOT NULL COMMENT 'id',
  `spbilldate` datetime NOT NULL COMMENT '票据日期',
  `spaccnum` varchar(15) NOT NULL COMMENT '户号',
  `spstartdate` datetime NOT NULL COMMENT '起始日期',
  `spenddate` datetime NOT NULL COMMENT '截止日期',
  `spstartdisplay` float(20,0) NOT NULL COMMENT '起始读数',
  `spenddisplay` float(20,0) NOT NULL COMMENT '截止读数',
  `spprestartdisplay` float(20,0) NOT NULL COMMENT '老表起始读数',
  `sppreenddisplay` float(20,0) NOT NULL COMMENT '老表截止读数',
  `sprate` int(20) NOT NULL COMMENT '倍率',
  `sploss` float(20,0) NOT NULL COMMENT '损耗',
  `sptotalq` float(20,0) NOT NULL COMMENT '计费电量',
  `spbillprice` float(20,2) NOT NULL COMMENT '电费单价',
  `sptotalc` float(20,0) NOT NULL COMMENT '票面金额',
  `spbilltype` varchar(20) NOT NULL COMMENT '票据类型',
  `create_by` varchar(64) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT '创建者',
  `create_date` datetime NOT NULL COMMENT '创建时间',
  `update_by` varchar(64) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT '更新者',
  `update_date` datetime NOT NULL COMMENT '更新时间',
  `del_flag` char(1) NOT NULL COMMENT '是否删除标识',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='直供缴费信息';

-- ----------------------------
-- Records of biz_direct_payinfo
-- ----------------------------
INSERT INTO `biz_direct_payinfo` VALUES ('1', '2017-11-01 16:27:22', '1', '2017-11-01 16:27:24', '2017-11-01 16:27:16', '13333333', '1', '1', '1', '1', '1', '1', '0.00', '0', '', '2222', '2017-11-01 16:27:07', '1', '2017-11-01 16:37:48', '1');
INSERT INTO `biz_direct_payinfo` VALUES ('9e4937900f75477cbf75e8ba5e00ead3', '2017-11-01 16:37:54', '21', '2017-11-01 16:37:57', '2017-11-01 16:37:58', '2112', '12', '12', '12', '12', '21', '12', '0.00', '0', '', '1', '2017-11-01 16:38:05', '1', '2017-11-01 16:38:05', '0');

-- ----------------------------
-- Table structure for biz_direct_receiptinfo
-- ----------------------------
DROP TABLE IF EXISTS `biz_direct_receiptinfo`;
CREATE TABLE `biz_direct_receiptinfo` (
  `id` varchar(64) NOT NULL COMMENT 'id',
  `scoperator` varchar(6) NOT NULL COMMENT '运营商',
  `scaccnum` varchar(15) NOT NULL COMMENT '用电户号',
  `scsitenum` varchar(30) NOT NULL COMMENT '站点编码',
  `scbilldate` datetime NOT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '票据日期',
  `scpredate` datetime NOT NULL COMMENT '上期日期',
  `sccurdate` datetime NOT NULL COMMENT '本期日期',
  `scpredisplay` float(20,0) NOT NULL COMMENT '上期示度',
  `sccurdisplay` float(20,0) NOT NULL COMMENT '本期示度',
  `scprestartdisplay` float(20,0) NOT NULL COMMENT '老表起始读数',
  `scpreenddisplay` float(20,0) NOT NULL COMMENT '老表截止读数',
  `scrate` int(20) NOT NULL COMMENT '倍率',
  `scloss` float(20,0) NOT NULL COMMENT '损耗',
  `sctotalq` float(20,0) NOT NULL COMMENT '计费电量',
  `scprice` float(20,0) NOT NULL COMMENT '电费单价',
  `scparc` float(20,0) NOT NULL COMMENT '票面金额',
  `scpartype` varchar(20) NOT NULL COMMENT '票据类型',
  `scpartypet` varchar(20) NOT NULL COMMENT '铁塔开票类型',
  `sccmq` float(20,0) NOT NULL COMMENT '移动电流',
  `scctq` float(20,0) NOT NULL COMMENT '电信电流',
  `sccuq` float(20,0) NOT NULL COMMENT '联通电流',
  `sctotalc` float(20,0) NOT NULL COMMENT '分摊电费总额',
  `scpue` float(20,0) DEFAULT NULL COMMENT 'PUE',
  `sccheck` float(20,0) DEFAULT NULL COMMENT '核对金额',
  `scc` float(20,0) DEFAULT NULL COMMENT '回款金额',
  `scapportchange` varchar(5) DEFAULT NULL COMMENT '本月分摊比例是否发生变动',
  `scremarks` varchar(100) DEFAULT NULL COMMENT '备注',
  `create_by` varchar(64) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT '创建者',
  `create_date` datetime NOT NULL COMMENT '创建时间',
  `update_by` varchar(64) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT '更新者',
  `update_date` datetime NOT NULL COMMENT '更新时间',
  `del_flag` char(1) NOT NULL COMMENT '是否删除标识',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='直供回款信息';

-- ----------------------------
-- Records of biz_direct_receiptinfo
-- ----------------------------

-- ----------------------------
-- Table structure for biz_electric_shareinfo
-- ----------------------------
DROP TABLE IF EXISTS `biz_electric_shareinfo`;
CREATE TABLE `biz_electric_shareinfo` (
  `id` varchar(64) NOT NULL COMMENT 'id',
  `rositenum` varchar(30) NOT NULL COMMENT '站址编码',
  `rocmq` float(20,0) NOT NULL COMMENT '移动电流',
  `roctq` float(20,0) NOT NULL COMMENT '电信电流',
  `rocuq` float(20,0) NOT NULL COMMENT '联通电流',
  `rohistnum` varchar(30) DEFAULT NULL COMMENT '宿主站站址编码',
  `roupdatedate` datetime DEFAULT NULL COMMENT '更新日期',
  `roremarks` varchar(100) DEFAULT NULL COMMENT '备注',
  `create_by` varchar(64) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT '创建者',
  `create_date` datetime NOT NULL COMMENT '创建时间',
  `update_by` varchar(64) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT '更新者',
  `update_date` datetime NOT NULL COMMENT '更新时间',
  `del_flag` char(1) NOT NULL COMMENT '是否删除标识',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='电流分摊信息';

-- ----------------------------
-- Records of biz_electric_shareinfo
-- ----------------------------

-- ----------------------------
-- Table structure for biz_fsu_monitorinfo
-- ----------------------------
DROP TABLE IF EXISTS `biz_fsu_monitorinfo`;
CREATE TABLE `biz_fsu_monitorinfo` (
  `id` varchar(64) NOT NULL COMMENT 'id',
  `fsusitenum` varchar(30) NOT NULL COMMENT '站址编码',
  `fsuq` float(20,0) DEFAULT NULL COMMENT '监控电流',
  `fsudate` datetime NOT NULL DEFAULT '0000-00-00 00:00:00' COMMENT '采集日期',
  `create_by` varchar(64) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT '创建者',
  `create_date` datetime NOT NULL COMMENT '创建时间',
  `update_by` varchar(64) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT '更新者',
  `update_date` datetime NOT NULL COMMENT '更新时间',
  `del_flag` char(1) NOT NULL COMMENT '是否删除标识',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='FSU监控信息';

-- ----------------------------
-- Records of biz_fsu_monitorinfo
-- ----------------------------

-- ----------------------------
-- Table structure for biz_replace_checkinfo
-- ----------------------------
DROP TABLE IF EXISTS `biz_replace_checkinfo`;
CREATE TABLE `biz_replace_checkinfo` (
  `id` varchar(64) NOT NULL COMMENT 'id',
  `inspsitenum` varchar(30) NOT NULL COMMENT '站址编码',
  `inspdate` datetime NOT NULL COMMENT '巡检日期',
  `inspdisplay` float(20,0) NOT NULL COMMENT '抄表读数',
  `inspcmq` float(20,0) NOT NULL COMMENT '移动电流',
  `inspctq` float(20,0) NOT NULL COMMENT '电信电流',
  `inspcuq` float(20,0) NOT NULL COMMENT '联通电流',
  `insppeople` varchar(15) NOT NULL COMMENT '巡检人员',
  `inspremarks` varchar(100) DEFAULT NULL COMMENT '备注',
  `create_by` varchar(64) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT '创建者',
  `create_date` datetime NOT NULL COMMENT '创建时间',
  `update_by` varchar(64) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT '更新者',
  `update_date` datetime NOT NULL COMMENT '更新时间',
  `del_flag` char(1) NOT NULL COMMENT '是否删除标识',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='代维巡检信息';

-- ----------------------------
-- Records of biz_replace_checkinfo
-- ----------------------------

-- ----------------------------
-- Table structure for biz_site_baseinfo
-- ----------------------------
DROP TABLE IF EXISTS `biz_site_baseinfo`;
CREATE TABLE `biz_site_baseinfo` (
  `id` varchar(64) NOT NULL COMMENT 'id',
  `sidistrict` varchar(6) NOT NULL COMMENT '区县',
  `sisitenum` varchar(30) NOT NULL COMMENT '站址编码',
  `sisitename` varchar(30) NOT NULL COMMENT '站址名称',
  `sipropertyunit` varchar(6) DEFAULT NULL COMMENT '原产权单位',
  `siroomstyle` varchar(20) DEFAULT NULL COMMENT '机房类型',
  `siretain` varchar(6) DEFAULT NULL COMMENT '保留站信息',
  `sicmq` datetime DEFAULT NULL COMMENT '移动起租日期',
  `sictq` datetime DEFAULT NULL COMMENT '电信起租日期',
  `sicuq` datetime DEFAULT NULL COMMENT '联通起租日期',
  `create_by` varchar(64) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT '创建者',
  `create_date` datetime NOT NULL COMMENT '创建时间',
  `update_by` varchar(64) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT '更新者',
  `update_date` datetime NOT NULL COMMENT '更新时间',
  `del_flag` char(1) NOT NULL COMMENT '是否删除标识',
  PRIMARY KEY (`id`,`sisitenum`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='站址基础信息';

-- ----------------------------
-- Records of biz_site_baseinfo
-- ----------------------------

-- ----------------------------
-- Table structure for biz_site_meterinfo
-- ----------------------------
DROP TABLE IF EXISTS `biz_site_meterinfo`;
CREATE TABLE `biz_site_meterinfo` (
  `id` varchar(64) NOT NULL COMMENT 'id',
  `amsitenum` varchar(30) NOT NULL COMMENT '站址编码',
  `amnum` varchar(30) NOT NULL COMMENT '电表户号',
  `amstartdate` datetime NOT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '起始日期',
  `amremarks` varchar(100) DEFAULT NULL COMMENT '备注',
  `create_by` varchar(64) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT '创建者',
  `create_date` datetime NOT NULL COMMENT '创建时间',
  `update_by` varchar(64) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT '更新者',
  `update_date` datetime NOT NULL COMMENT '更新时间',
  `del_flag` char(1) NOT NULL COMMENT '是否删除标识',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='表站对应信息';

-- ----------------------------
-- Records of biz_site_meterinfo
-- ----------------------------

-- ----------------------------
-- Table structure for biz_transit_contractinfo
-- ----------------------------
DROP TABLE IF EXISTS `biz_transit_contractinfo`;
CREATE TABLE `biz_transit_contractinfo` (
  `id` varchar(64) NOT NULL COMMENT 'id',
  `tconsitename` varchar(30) NOT NULL COMMENT '站址编码',
  `tconsitenum` varchar(20) NOT NULL COMMENT '合同编码',
  `tconpa` varchar(20) NOT NULL COMMENT '供电方名称',
  `tconstartdate` datetime NOT NULL COMMENT '合同起始日期',
  `tconenddate` datetime NOT NULL COMMENT '合同截止日期',
  `tconprice` float(20,0) NOT NULL COMMENT '合同单价',
  `tcontype` varchar(20) NOT NULL COMMENT '票据类型',
  `tcremark` varchar(100) DEFAULT NULL COMMENT '备注',
  `create_by` varchar(64) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT '创建者',
  `create_date` datetime NOT NULL COMMENT '创建时间',
  `update_by` varchar(64) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT '更新者',
  `update_date` datetime NOT NULL COMMENT '更新时间',
  `del_flag` char(1) NOT NULL COMMENT '是否删除标识',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='转供合同信息';

-- ----------------------------
-- Records of biz_transit_contractinfo
-- ----------------------------

-- ----------------------------
-- Table structure for biz_transit_copymeterinfo
-- ----------------------------
DROP TABLE IF EXISTS `biz_transit_copymeterinfo`;
CREATE TABLE `biz_transit_copymeterinfo` (
  `id` varchar(64) NOT NULL COMMENT 'id',
  `tdsitenum` varchar(30) NOT NULL COMMENT '站址编码',
  `tddate` datetime NOT NULL COMMENT '抄表日期',
  `tddisplay` float(20,0) NOT NULL COMMENT '抄表读数',
  `tdperson` varchar(10) DEFAULT NULL COMMENT '抄表人',
  `tdremark` varchar(100) DEFAULT NULL COMMENT '抄表读数',
  `create_by` varchar(64) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT '创建者',
  `create_date` datetime NOT NULL COMMENT '创建时间',
  `update_by` varchar(64) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT '更新者',
  `update_date` datetime NOT NULL COMMENT '更新时间',
  `del_flag` char(1) NOT NULL COMMENT '是否删除标识',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='转供抄表信息';

-- ----------------------------
-- Records of biz_transit_copymeterinfo
-- ----------------------------

-- ----------------------------
-- Table structure for biz_transit_payinfo
-- ----------------------------
DROP TABLE IF EXISTS `biz_transit_payinfo`;
CREATE TABLE `biz_transit_payinfo` (
  `id` varchar(64) NOT NULL COMMENT 'id',
  `tpaccnum` datetime NOT NULL COMMENT '户号',
  `tppaydate` datetime NOT NULL COMMENT '缴费日期',
  `tpstartdate` datetime NOT NULL COMMENT '起始日期',
  `tpenddate` float(20,0) NOT NULL COMMENT '截止日期',
  `tpstartdisplay` float(20,0) NOT NULL COMMENT '起始读数',
  `tpenddisplay` float(20,0) NOT NULL COMMENT '截止读数',
  `tpprestartdisplay` float(20,0) NOT NULL COMMENT '老表起始读数',
  `tpperenddisplay` float(20,0) NOT NULL COMMENT '老表截止读数',
  `tprate` int(20) NOT NULL COMMENT '倍率',
  `tploss` float(20,0) NOT NULL COMMENT '损耗',
  `tptotalq` float(20,0) NOT NULL COMMENT '计费电量',
  `tpbillprice` float(20,2) NOT NULL COMMENT '电费单价',
  `tpmainc` float(20,0) NOT NULL COMMENT '票面金额',
  `tpdktax` float(20,0) NOT NULL DEFAULT '0' COMMENT '代开税金',
  `tpbilltype` varchar(20) NOT NULL COMMENT '票据类型（代开）',
  `tptotalc` float(20,0) NOT NULL COMMENT '总金额',
  `tpsupplier` varchar(20) DEFAULT NULL COMMENT '供电方名称',
  `tpdestdate` datetime DEFAULT NULL COMMENT '核销日期',
  `tpbillnum` varchar(30) DEFAULT NULL COMMENT '报账单号',
  `tppaynum` varchar(35) DEFAULT NULL COMMENT '支付单号',
  `tpdestnum` varchar(35) DEFAULT NULL COMMENT '核销单号',
  `create_by` varchar(64) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT '创建者',
  `create_date` datetime NOT NULL COMMENT '创建时间',
  `update_by` varchar(64) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT '更新者',
  `update_date` datetime NOT NULL COMMENT '更新时间',
  `del_flag` char(1) NOT NULL COMMENT '是否删除标识',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='转供缴费信息';

-- ----------------------------
-- Records of biz_transit_payinfo
-- ----------------------------

-- ----------------------------
-- Table structure for biz_transit_receiptinfo
-- ----------------------------
DROP TABLE IF EXISTS `biz_transit_receiptinfo`;
CREATE TABLE `biz_transit_receiptinfo` (
  `id` varchar(64) NOT NULL COMMENT 'id',
  `tcoperator` varchar(20) NOT NULL COMMENT '运营商',
  `tcaccnum` varchar(15) NOT NULL COMMENT '用电户号',
  `tcsitenum` varchar(30) NOT NULL COMMENT '站点编码',
  `tcpaydate` datetime NOT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '缴费日期',
  `tcpredate` datetime NOT NULL COMMENT '上期日期',
  `tccurdate` datetime NOT NULL COMMENT '本期日期',
  `tcpredisplay` float(20,0) NOT NULL COMMENT '起始读数',
  `tccurdisplay` float(20,0) NOT NULL COMMENT '截止读数',
  `tcprestartdisplay` float(20,0) NOT NULL COMMENT '老表起始读数',
  `tcpreenddisplay` float(20,0) NOT NULL COMMENT '老表截止读数',
  `tcrate` int(20) NOT NULL COMMENT '倍率',
  `tcloss` float(20,0) NOT NULL COMMENT '损耗',
  `tctotalq` float(20,0) NOT NULL COMMENT '计费电量',
  `tcpriceq` float(20,0) NOT NULL COMMENT '电费单价',
  `tcparc` float(20,0) NOT NULL COMMENT '票面金额',
  `tcpartype` varchar(20) NOT NULL COMMENT '票据类型',
  `tcpartypet` varchar(20) NOT NULL COMMENT '铁塔开票类型',
  `tctaxfactor` float(20,0) NOT NULL COMMENT '税负因子',
  `tccmq` float(20,0) NOT NULL COMMENT '移动电流',
  `tcctq` float(20,0) NOT NULL COMMENT '电信电流',
  `tccuq` float(20,0) NOT NULL COMMENT '联通电流',
  `tctotalc` float(20,0) NOT NULL COMMENT '分摊电费总额',
  `tcpue` float(20,0) NOT NULL COMMENT 'PUE',
  `tccheck` float(20,0) NOT NULL COMMENT '核对金额',
  `tcc` float(20,0) NOT NULL COMMENT '回款金额',
  `tcapportchange` varchar(1) DEFAULT NULL COMMENT '本月分摊比例是否发生变动',
  `tcremarks` varchar(100) DEFAULT NULL COMMENT '备注',
  `create_by` varchar(64) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT '创建者',
  `create_date` datetime NOT NULL COMMENT '创建时间',
  `update_by` varchar(64) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT '更新者',
  `update_date` datetime NOT NULL COMMENT '更新时间',
  `del_flag` char(1) NOT NULL COMMENT '是否删除标识',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='转供回款信息';

-- ----------------------------
-- Records of biz_transit_receiptinfo
-- ----------------------------

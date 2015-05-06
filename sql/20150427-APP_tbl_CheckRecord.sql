/***********************************
创建人:黄林
创建时间:20150427
功能:
1.业务审核记录主表

修改记录




**********************************/



if exists(select * from dbo.sysobjects  where name='APP_tbl_CheckRecord')  
    drop table APP_tbl_CheckRecord
GO


create table APP_tbl_CheckRecord
(
FrecordID int  identity(1,1), --记录ID
ACCTName  varchar (100), --帐套
DatabaseName  varchar (100), --帐套数据库
FTYPE varchar (100), --业务类型: 1业务审批，2销售指导价审批流，3承兑汇票审批，6新品开发审批流,7客户建档审批，8样品申请
FAPPBiller varchar (100),--APP制单人、申请人
Fbiller varchar (100),--K3制单人、申请人
FbillerID int, --K3制单人ID
BillType  varchar (100), --单据类型
BillName  varchar (100), --单据类型名称
Billno varchar(100),--单据号
FDescription varchar (500),--描述
RequestDate datetime,--请求时间
Fstatus int --状态，0未完成，1已完成
)




SELECT * FROM APP_tbl_CheckRecord


/***********************************
创建人:黄林
创建时间:20150427
功能:
1.业务审核记录附表，记录审核流转情况

修改记录




**********************************/



if exists(select * from dbo.sysobjects  where name='APP_tbl_CheckRecordDetail')  
    drop table APP_tbl_CheckRecordDetail
GO


create table APP_tbl_CheckRecordDetail
(
FrecordID int , --审核记录ID
APPUser varchar (100),--APPUser 审核人
K3userID int,--K3用户ID
K3user  varchar (100),--K3用户
CheckLevle int,--审核级次
Checkstatus varchar (100), -- 未处理，同意,驳回
CheckNote varchar (500), --审核意见
CheckDate datetime --审核时间
)





SELECT * FROM APP_tbl_CheckRecordDetail


/***********************************
创建人:黄林
创建时间:20150427
功能:
1.审批记录查询

修改记录




**********************************/



if exists(select * from dbo.sysobjects  where name='APP_PROC_CheckRecordQuery')  
    drop proc APP_PROC_CheckRecordQuery
GO

--exec APP_PROC_CheckRecordQuery '1','','张一',0,'','','','',0,0
--exec APP_PROC_CheckRecordQuery '1','','张一',0,'','','','',2,1



Create proc [dbo].APP_PROC_CheckRecordQuery
(
@FTYPE varchar(10),--业务类型: 0,消息,1业务审批，2销售指导价审批流，3承兑汇票审批，6新品开发审批流,7客户建档审批，8样品申请
@databaseName varchar(100),--数据库名称，可选
@AppUser varchar (100), --APP用户
@Status  int, --状态，0未完成，1已完成
@Billtype varchar (100) ,--单据类型
@Billno varchar (100), --单据号
@BeginDate datetime,--开始时间，过滤时使用，默认可小时间
@EndDate datetime,--截止时间，过滤时使用，默认可最大时间
@FrecordID int ,--审批记录ID ，查询明细是必填
@IsDetail int --是否查询明细 0 否，1是

)

as
Set Nocount on








--消息，普通业务、销售指导价、承兑汇票审批清单查询
if @FType in ('0','1','2','3') and @IsDetail=0
begin
	
	select t1.FrecordID,t1.Fbiller,t1.FAPPBiller,t1.BillName,t1.Billno,t1.FDescription,t1.RequestDate,t2.Checkstatus from APP_tbl_CheckRecord t1 
	Inner join APP_tbl_CheckRecordDetail t2 on t1.FrecordID=t2.FrecordID
	where t1.FTYPE =@FType and t1.BillName like '%'+@Billtype+'%'
	and t1.DatabaseName  like '%'+@databaseName+'%'
	and t1.Billno like '%'+@Billno+'%'
	and t2.APPUser=@AppUser
	and t1.fstatus =@Status
	order by t1.RequestDate
	


end


--消息,普通业务、销售指导价、承兑汇票审批明细查询
if @FType in ('0','1','2','3') and @IsDetail=1
begin
	
	select t1.FrecordID,t1.Fbiller,t1.BillName,t1.Billno,t1.FDescription,t1.RequestDate,t2.APPUser,t2.CheckLevle,t2.Checkstatus,t2.CheckNote,t2.CheckDate from APP_tbl_CheckRecord t1 
	left join APP_tbl_CheckRecordDetail t2 on t1.FrecordID=t2.FrecordID
	where t1.FTYPE =@FType 
	and t1.DatabaseName  like '%'+@databaseName+'%'
	and t1.FrecordID=@FrecordID
	order by t2.CheckLevle


end



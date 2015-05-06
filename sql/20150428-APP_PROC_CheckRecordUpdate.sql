/***********************************
创建人:黄林
创建时间:20150428
功能:
1.审批记录更新

修改记录




**********************************/



if exists(select * from dbo.sysobjects  where name='APP_PROC_CheckRecordUpdate')  
    drop proc APP_PROC_CheckRecordUpdate
GO

--exec APP_PROC_CheckRecordUpdate '','张一',2,1,'同意',''
--exec APP_PROC_CheckRecordUpdate '','张一',2,1,'已读',''




Create proc [dbo].APP_PROC_CheckRecordUpdate
(
@FTYPE varchar(10),--备用
@AppUser varchar (100), --APP用户
@FrecordID int ,--审批记录ID
@CheckLevle int,--审核级次
@Checkstatus varchar (100), -- 未处理，已读，同意,驳回
@CheckNote varchar (500) --审核意见

)

as
Set Nocount on
declare @MSG as varchar(500)
set  @MSG=''




if exists(select 1 from  APP_tbl_CheckRecordDetail where CheckLevle=@CheckLevle and APPUser=@AppUser and isnull(Checkstatus,'') <> @Checkstatus)
begin

	update t1 set t1.CheckDate=GETDATE(),t1.CheckNote=@CheckNote,t1.Checkstatus=@Checkstatus from  APP_tbl_CheckRecordDetail t1
	where t1.CheckLevle=@CheckLevle and t1.FrecordID=@FrecordID
	and t1.APPUser=@AppUser
	set  @MSG='处理完成'

end
else
begin
	set  @MSG='该用户不具备该级次审核权限或者该记录已处理'
end


select @MSG
/***********************************
������:����
����ʱ��:20150428
����:
1.������¼����

�޸ļ�¼




**********************************/



if exists(select * from dbo.sysobjects  where name='APP_PROC_CheckRecordUpdate')  
    drop proc APP_PROC_CheckRecordUpdate
GO

--exec APP_PROC_CheckRecordUpdate '','��һ',2,1,'ͬ��',''
--exec APP_PROC_CheckRecordUpdate '','��һ',2,1,'�Ѷ�',''




Create proc [dbo].APP_PROC_CheckRecordUpdate
(
@FTYPE varchar(10),--����
@AppUser varchar (100), --APP�û�
@FrecordID int ,--������¼ID
@CheckLevle int,--��˼���
@Checkstatus varchar (100), -- δ�����Ѷ���ͬ��,����
@CheckNote varchar (500) --������

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
	set  @MSG='�������'

end
else
begin
	set  @MSG='���û����߱��ü������Ȩ�޻��߸ü�¼�Ѵ���'
end


select @MSG
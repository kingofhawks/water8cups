/***********************************
������:����
����ʱ��:20150427
����:
1.������¼��ѯ

�޸ļ�¼




**********************************/



if exists(select * from dbo.sysobjects  where name='APP_PROC_CheckRecordQuery')  
    drop proc APP_PROC_CheckRecordQuery
GO

--exec APP_PROC_CheckRecordQuery '1','','��һ',0,'','','','',0,0
--exec APP_PROC_CheckRecordQuery '1','','��һ',0,'','','','',2,1



Create proc [dbo].APP_PROC_CheckRecordQuery
(
@FTYPE varchar(10),--ҵ������: 0,��Ϣ,1ҵ��������2����ָ������������3�жһ�Ʊ������6��Ʒ����������,7�ͻ�����������8��Ʒ����
@databaseName varchar(100),--���ݿ����ƣ���ѡ
@AppUser varchar (100), --APP�û�
@Status  int, --״̬��0δ��ɣ�1�����
@Billtype varchar (100) ,--��������
@Billno varchar (100), --���ݺ�
@BeginDate datetime,--��ʼʱ�䣬����ʱʹ�ã�Ĭ�Ͽ�Сʱ��
@EndDate datetime,--��ֹʱ�䣬����ʱʹ�ã�Ĭ�Ͽ����ʱ��
@FrecordID int ,--������¼ID ����ѯ��ϸ�Ǳ���
@IsDetail int --�Ƿ��ѯ��ϸ 0 ��1��

)

as
Set Nocount on








--��Ϣ����ͨҵ������ָ���ۡ��жһ�Ʊ�����嵥��ѯ
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


--��Ϣ,��ͨҵ������ָ���ۡ��жһ�Ʊ������ϸ��ѯ
if @FType in ('0','1','2','3') and @IsDetail=1
begin
	
	select t1.FrecordID,t1.Fbiller,t1.BillName,t1.Billno,t1.FDescription,t1.RequestDate,t2.APPUser,t2.CheckLevle,t2.Checkstatus,t2.CheckNote,t2.CheckDate from APP_tbl_CheckRecord t1 
	left join APP_tbl_CheckRecordDetail t2 on t1.FrecordID=t2.FrecordID
	where t1.FTYPE =@FType 
	and t1.DatabaseName  like '%'+@databaseName+'%'
	and t1.FrecordID=@FrecordID
	order by t2.CheckLevle


end



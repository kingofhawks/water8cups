/***********************************
������:����
����ʱ��:20150427
����:
1.ҵ����˼�¼������¼�����ת���

�޸ļ�¼




**********************************/



if exists(select * from dbo.sysobjects  where name='APP_tbl_CheckRecordDetail')  
    drop table APP_tbl_CheckRecordDetail
GO


create table APP_tbl_CheckRecordDetail
(
FrecordID int , --��˼�¼ID
APPUser varchar (100),--APPUser �����
K3userID int,--K3�û�ID
K3user  varchar (100),--K3�û�
CheckLevle int,--��˼���
Checkstatus varchar (100), -- δ����ͬ��,����
CheckNote varchar (500), --������
CheckDate datetime --���ʱ��
)





SELECT * FROM APP_tbl_CheckRecordDetail


/***********************************
������:����
����ʱ��:20150427
����:
1.ҵ����˼�¼����

�޸ļ�¼




**********************************/



if exists(select * from dbo.sysobjects  where name='APP_tbl_CheckRecord')  
    drop table APP_tbl_CheckRecord
GO


create table APP_tbl_CheckRecord
(
FrecordID int  identity(1,1), --��¼ID
ACCTName  varchar (100), --����
DatabaseName  varchar (100), --�������ݿ�
FTYPE varchar (100), --ҵ������: 1ҵ��������2����ָ������������3�жһ�Ʊ������6��Ʒ����������,7�ͻ�����������8��Ʒ����
FAPPBiller varchar (100),--APP�Ƶ��ˡ�������
Fbiller varchar (100),--K3�Ƶ��ˡ�������
FbillerID int, --K3�Ƶ���ID
BillType  varchar (100), --��������
BillName  varchar (100), --������������
Billno varchar(100),--���ݺ�
FDescription varchar (500),--����
RequestDate datetime,--����ʱ��
Fstatus int --״̬��0δ��ɣ�1�����
)




SELECT * FROM APP_tbl_CheckRecord


/***********************************
������:����
����ʱ��:20150330
����:
1.APP��K3�û���Ӧ��

�޸ļ�¼




**********************************/





if exists(select * from dbo.sysobjects  where name='REBA_tbl_APPUser')  
    drop table REBA_tbl_APPUser
GO



create table REBA_tbl_APPUser
(
Fid int  identity(1,1),
Fuser varchar(100),--APP�û���
FPhone varchar(100),--�绰
FK3user varchar(100),--K3�û���
FK3userID int,--K3�û�ID
Fpassword varchar(200),--����
FMenuList varchar(200),--�û��˵�Ȩ�� 9λ�ַ���0������ʾ��1������ʾ
--��1ҵ��������2����ָ������������3�жһ�Ʊ������4��Ϣ,5��ɲ�ѯ��6��Ʒ����������,7�ͻ�����������8��Ʒ���룬9����ת�����ߣ�
FStatus int ,--�û�״̬0,������1����


)


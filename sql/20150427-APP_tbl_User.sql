/***********************************
������:����
����ʱ��:20150427
����:
1.APP�û���

�޸ļ�¼




**********************************/





if exists(select * from dbo.sysobjects  where name='APP_tbl_User')  
    drop table APP_tbl_User
GO



create table APP_tbl_User
(
Fid int  identity(1,1),
Fuser varchar(100),--APP�û���
FPhone varchar(100),--�绰
Fpassword varchar(200),--����
FMenuList varchar(200),--�û��˵�Ȩ�� 9λ�ַ���0������ʾ��1������ʾ
--��1ҵ��������2����ָ������������3�жһ�Ʊ������4��Ϣ,5��ɲ�ѯ��6��Ʒ����������,7�ͻ�����������8��Ʒ���룬9����ת�����ߣ�
FStatus int ,--�û�״̬0,������1����


)







--�û���
insert into APP_tbl_User(Fuser,FPhone,Fpassword,FMenuList) values ('��һ','13800000000','123','111111111')
insert into APP_tbl_User(Fuser,FPhone,Fpassword,FMenuList) values ('�Ŷ�','13800000001','123','111111110')
insert into APP_tbl_User(Fuser,FPhone,Fpassword,FMenuList) values ('����','13800000002','123','111111101')
insert into APP_tbl_User(Fuser,FPhone,Fpassword,FMenuList) values ('����','13800000003','123','111111011')
insert into APP_tbl_User(Fuser,FPhone,Fpassword,FMenuList) values ('����','13800000004','123','111110111')
insert into APP_tbl_User(Fuser,FPhone,Fpassword,FMenuList) values ('����','13800000005','123','111101111')


declare @fid as int 



insert into APP_tbl_CheckRecord(ACCTName,DatabaseName,FTYPE,FAPPBiller,Fbiller,FbillerID,BillType,BillName,Billno,FDescription,RequestDate,Fstatus)
values('����1','databse1','1','��һ','��һK3',0,'A','�ɹ�����','order001','�ɹ������������ɹ����10000','2015-04-27',0)

set @fid=(SELECT IDENT_CURRENT('APP_tbl_CheckRecord'))

insert into APP_tbl_CheckRecordDetail(FrecordID,APPUser,K3userID,K3user,CheckLevle) values (@fid,'����',0,'����K3',1)
insert into APP_tbl_CheckRecordDetail(FrecordID,APPUser,K3userID,K3user,CheckLevle) values (@fid,'�Ŷ�',0,'�Ŷ�K3',2)
insert into APP_tbl_CheckRecordDetail(FrecordID,APPUser,K3userID,K3user,CheckLevle) values (@fid,'����',0,'����K3',3)


insert into APP_tbl_CheckRecord(ACCTName,DatabaseName,FTYPE,FAPPBiller,Fbiller,FbillerID,BillType,BillName,Billno,FDescription,RequestDate,Fstatus)
values('����1','databse1','1','�Ŷ�','�Ŷ�K3',0,'A','�ɹ�����','order002','�ɹ������������ɹ����10002','2015-04-22',0)
set @fid=(SELECT IDENT_CURRENT('APP_tbl_CheckRecord'))
insert into APP_tbl_CheckRecordDetail(FrecordID,APPUser,K3userID,K3user,CheckLevle) values (@fid,'��һ',0,'��һK3',1)
insert into APP_tbl_CheckRecordDetail(FrecordID,APPUser,K3userID,K3user,CheckLevle) values (@fid,'����',0,'����K3',2)
insert into APP_tbl_CheckRecordDetail(FrecordID,APPUser,K3userID,K3user,CheckLevle) values (@fid,'����',0,'����K3',3)


insert into APP_tbl_CheckRecord(ACCTName,DatabaseName,FTYPE,FAPPBiller,Fbiller,FbillerID,BillType,BillName,Billno,FDescription,RequestDate,Fstatus)
values('����1','databse1','1','����','����K3',0,'A','�ɹ�����','order003','�ɹ������������ɹ����10003','2015-04-23',0)
set @fid=(SELECT IDENT_CURRENT('APP_tbl_CheckRecord'))
insert into APP_tbl_CheckRecordDetail(FrecordID,APPUser,K3userID,K3user,CheckLevle) values (@fid,'��һ',0,'��һK3',1)
insert into APP_tbl_CheckRecordDetail(FrecordID,APPUser,K3userID,K3user,CheckLevle) values (@fid,'����',0,'����K3',2)
insert into APP_tbl_CheckRecordDetail(FrecordID,APPUser,K3userID,K3user,CheckLevle) values (@fid,'�Ŷ�',0,'�Ŷ�K3',3)

insert into APP_tbl_CheckRecord(ACCTName,DatabaseName,FTYPE,FAPPBiller,Fbiller,FbillerID,BillType,BillName,Billno,FDescription,RequestDate,Fstatus)
values('����1','databse1','1','����','����K3',0,'A','�ɹ�����','order004','�ɹ������������ɹ����10004','2015-04-24',0)
set @fid=(SELECT IDENT_CURRENT('APP_tbl_CheckRecord'))
insert into APP_tbl_CheckRecordDetail(FrecordID,APPUser,K3userID,K3user,CheckLevle) values (@fid,'��һ',0,'��һK3',1)
insert into APP_tbl_CheckRecordDetail(FrecordID,APPUser,K3userID,K3user,CheckLevle) values (@fid,'�Ŷ�',0,'�Ŷ�K3',2)
insert into APP_tbl_CheckRecordDetail(FrecordID,APPUser,K3userID,K3user,CheckLevle) values (@fid,'����',0,'����K3',3)

insert into APP_tbl_CheckRecord(ACCTName,DatabaseName,FTYPE,FAPPBiller,Fbiller,FbillerID,BillType,BillName,Billno,FDescription,RequestDate,Fstatus)
values('����1','databse1','1','����','����K3',0,'A','�ɹ�����','order005','�ɹ������������ɹ����10005','2015-04-25',0)
set @fid=(SELECT IDENT_CURRENT('APP_tbl_CheckRecord'))
insert into APP_tbl_CheckRecordDetail(FrecordID,APPUser,K3userID,K3user,CheckLevle) values (@fid,'��һ',0,'��һK3',1)
insert into APP_tbl_CheckRecordDetail(FrecordID,APPUser,K3userID,K3user,CheckLevle) values (@fid,'����',0,'����K3',2)
insert into APP_tbl_CheckRecordDetail(FrecordID,APPUser,K3userID,K3user,CheckLevle) values (@fid,'����',0,'����K3',3)



select*from APP_tbl_User
select*from APP_tbl_CheckRecord
select*from APP_tbl_CheckRecordDetail
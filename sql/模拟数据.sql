




--用户表
insert into APP_tbl_User(Fuser,FPhone,Fpassword,FMenuList) values ('张一','13800000000','123','111111111')
insert into APP_tbl_User(Fuser,FPhone,Fpassword,FMenuList) values ('张二','13800000001','123','111111110')
insert into APP_tbl_User(Fuser,FPhone,Fpassword,FMenuList) values ('张三','13800000002','123','111111101')
insert into APP_tbl_User(Fuser,FPhone,Fpassword,FMenuList) values ('张四','13800000003','123','111111011')
insert into APP_tbl_User(Fuser,FPhone,Fpassword,FMenuList) values ('张五','13800000004','123','111110111')
insert into APP_tbl_User(Fuser,FPhone,Fpassword,FMenuList) values ('张六','13800000005','123','111101111')


declare @fid as int 



insert into APP_tbl_CheckRecord(ACCTName,DatabaseName,FTYPE,FAPPBiller,Fbiller,FbillerID,BillType,BillName,Billno,FDescription,RequestDate,Fstatus)
values('帐套1','databse1','1','张一','张一K3',0,'A','采购订单','order001','采购订单审批，采购金额10000','2015-04-27',0)

set @fid=(SELECT IDENT_CURRENT('APP_tbl_CheckRecord'))

insert into APP_tbl_CheckRecordDetail(FrecordID,APPUser,K3userID,K3user,CheckLevle) values (@fid,'张四',0,'张四K3',1)
insert into APP_tbl_CheckRecordDetail(FrecordID,APPUser,K3userID,K3user,CheckLevle) values (@fid,'张二',0,'张二K3',2)
insert into APP_tbl_CheckRecordDetail(FrecordID,APPUser,K3userID,K3user,CheckLevle) values (@fid,'张三',0,'张三K3',3)


insert into APP_tbl_CheckRecord(ACCTName,DatabaseName,FTYPE,FAPPBiller,Fbiller,FbillerID,BillType,BillName,Billno,FDescription,RequestDate,Fstatus)
values('帐套1','databse1','1','张二','张二K3',0,'A','采购订单','order002','采购订单审批，采购金额10002','2015-04-22',0)
set @fid=(SELECT IDENT_CURRENT('APP_tbl_CheckRecord'))
insert into APP_tbl_CheckRecordDetail(FrecordID,APPUser,K3userID,K3user,CheckLevle) values (@fid,'张一',0,'张一K3',1)
insert into APP_tbl_CheckRecordDetail(FrecordID,APPUser,K3userID,K3user,CheckLevle) values (@fid,'张四',0,'张四K3',2)
insert into APP_tbl_CheckRecordDetail(FrecordID,APPUser,K3userID,K3user,CheckLevle) values (@fid,'张三',0,'张三K3',3)


insert into APP_tbl_CheckRecord(ACCTName,DatabaseName,FTYPE,FAPPBiller,Fbiller,FbillerID,BillType,BillName,Billno,FDescription,RequestDate,Fstatus)
values('帐套1','databse1','1','张三','张三K3',0,'A','采购订单','order003','采购订单审批，采购金额10003','2015-04-23',0)
set @fid=(SELECT IDENT_CURRENT('APP_tbl_CheckRecord'))
insert into APP_tbl_CheckRecordDetail(FrecordID,APPUser,K3userID,K3user,CheckLevle) values (@fid,'张一',0,'张一K3',1)
insert into APP_tbl_CheckRecordDetail(FrecordID,APPUser,K3userID,K3user,CheckLevle) values (@fid,'张四',0,'张四K3',2)
insert into APP_tbl_CheckRecordDetail(FrecordID,APPUser,K3userID,K3user,CheckLevle) values (@fid,'张二',0,'张二K3',3)

insert into APP_tbl_CheckRecord(ACCTName,DatabaseName,FTYPE,FAPPBiller,Fbiller,FbillerID,BillType,BillName,Billno,FDescription,RequestDate,Fstatus)
values('帐套1','databse1','1','张四','张四K3',0,'A','采购订单','order004','采购订单审批，采购金额10004','2015-04-24',0)
set @fid=(SELECT IDENT_CURRENT('APP_tbl_CheckRecord'))
insert into APP_tbl_CheckRecordDetail(FrecordID,APPUser,K3userID,K3user,CheckLevle) values (@fid,'张一',0,'张一K3',1)
insert into APP_tbl_CheckRecordDetail(FrecordID,APPUser,K3userID,K3user,CheckLevle) values (@fid,'张二',0,'张二K3',2)
insert into APP_tbl_CheckRecordDetail(FrecordID,APPUser,K3userID,K3user,CheckLevle) values (@fid,'张三',0,'张三K3',3)

insert into APP_tbl_CheckRecord(ACCTName,DatabaseName,FTYPE,FAPPBiller,Fbiller,FbillerID,BillType,BillName,Billno,FDescription,RequestDate,Fstatus)
values('帐套1','databse1','1','张五','张五K3',0,'A','采购订单','order005','采购订单审批，采购金额10005','2015-04-25',0)
set @fid=(SELECT IDENT_CURRENT('APP_tbl_CheckRecord'))
insert into APP_tbl_CheckRecordDetail(FrecordID,APPUser,K3userID,K3user,CheckLevle) values (@fid,'张一',0,'张一K3',1)
insert into APP_tbl_CheckRecordDetail(FrecordID,APPUser,K3userID,K3user,CheckLevle) values (@fid,'张四',0,'张四K3',2)
insert into APP_tbl_CheckRecordDetail(FrecordID,APPUser,K3userID,K3user,CheckLevle) values (@fid,'张三',0,'张三K3',3)



select*from APP_tbl_User
select*from APP_tbl_CheckRecord
select*from APP_tbl_CheckRecordDetail
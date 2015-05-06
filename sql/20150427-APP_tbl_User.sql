/***********************************
创建人:黄林
创建时间:20150427
功能:
1.APP用户表

修改记录




**********************************/





if exists(select * from dbo.sysobjects  where name='APP_tbl_User')  
    drop table APP_tbl_User
GO



create table APP_tbl_User
(
Fid int  identity(1,1),
Fuser varchar(100),--APP用户名
FPhone varchar(100),--电话
Fpassword varchar(200),--密码
FMenuList varchar(200),--用户菜单权限 9位字符，0代表不显示，1代表显示
--（1业务审批，2销售指导价审批流，3承兑汇票审批，4消息,5提成查询，6新品开发审批流,7客户建档审批，8样品申请，9代码转换工具）
FStatus int ,--用户状态0,正常，1禁用


)


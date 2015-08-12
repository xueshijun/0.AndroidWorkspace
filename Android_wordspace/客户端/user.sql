drop table userInfo
create table userInfo(
userId		int identity(1000,1),
userName	varchar(20) ,
userPass	varchar(20)
)

insert into userInfo(userName,userPass) values ('aaa','ccc')
insert into userInfo(userName,userPass) values ('aaa','bbb')
insert into userInfo(userName,userPass) values ('aaa','ddd')


select userId from userInfo where userName='aaa' and userPass='ccc' 

select * from userInfo

create table task(
userId		int,
goodsId		int identity(1,1),
goodsName	varchar(20) ,
goodsAddress	varchar(20) ,
goodsStatus		int
)

insert into task(userId,goodsName,goodsAddress,goodsStatus)values('1000','sss','ddd','0')
insert into task(userId,goodsName,goodsAddress,goodsStatus)values('1001','sss','ddsdd','0')
insert into task(userId,goodsName,goodsAddress,goodsStatus)values('1001','sss','dddfd','0')
insert into task(userId,goodsName,goodsAddress,goodsStatus)values('1001','sss','dddfd','0')
insert into task(userId,goodsName,goodsAddress,goodsStatus)values('1003','sss','dddfd','0')

update task set goodsStatus=1 where goodsId=2
select * from task


truncate table task
select goodsId,goodsName,goodsAddress,goodsStatus from task where userId='1000' and goodsStatus=0
-- -- 权限表 --
-- create table permission (
--     pid int(11) not null auto_increment,
--     name varchar(64) not null default '',
--     url varchar (64) default '',
--     primary key (pid)
-- ) ENGINE = InnoDB default charset = utf8;
--
-- insert into permission values (1,'add','');
-- insert into permission values (2,'del','');
-- insert into permission values (4,'select','');
-- insert into permission values (5,'update','');

-- 角色表 --
create table role(
    rid int(11) not null auto_increment,
    name varchar(64) not null default '',
    primary key (rid)
) ENGINE = InnoDB default charset = utf8;

insert into role values (1,'admin');
insert into role values (2,'custom');

-- 权限角色关系表 --
create table permission_role(
    rid int(11) not null,
    pid int(11) not null,
    key idx_rid(rid),
    key idx_pid(pid)
) ENGINE = InnoDB default charset = utf8;

insert into permission_role values (1,1);
insert into permission_role values (1,2);
insert into permission_role values (1,3);
insert into permission_role values (2,1);
insert into permission_role values (2,4);

-- 用户角色关系表 --
create table user_role(
    uid int(11) not null,
    rid int(11) not null,
    key idx_uid(uid),
    key idx_rid(rid)
) ENGINE = InnoDB default charset = utf8;

insert into user_role values (1,1);
insert into user_role values (2,2);
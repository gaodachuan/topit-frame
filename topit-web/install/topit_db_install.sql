drop table if exists SysModules;

/*==============================================================*/
/* Table: SysModules 系统模块表                                          */
/*==============================================================*/
create table SysModules
(
   Id                   int not null comment '模块ID。',
   Name                 varchar(128) not null comment '模块名称。',
   ModuleType           int not null comment '模块类型，1：C/S下的模块；2：B/S下的模块。',
   CategoryId           int not null comment '模块分类ID，该分类应支持树形。',
   Description          varchar(200) not null comment '模块描述。',
   AssemblePath         varchar(512) not null comment '程序集名称（或完整路径）。',
   ClassName            varchar(64) not null comment '模块入口类名称。',
   FunctionName         varchar(64) not null comment '入口函数名称。',
   InterfaceName        varchar(64) not null comment '表示模块入口类实现了哪个类。',
   CreateTime           datetime not null comment '创建时间。',
   CreateUser           varchar(50) not null,
   primary key (Id)
)




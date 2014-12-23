

/*==============================================================*/
/* Table: sys_option 系统选项表                                 */
/*==============================================================*/


INSERT INTO sys_option
(CategoryId,OptionKey,Line,OptionName,OptionValue,DataType,SortCategoryId,
Description,Creator,CreateTime,LastEditor,LastEditTime,Version)
VALUES
(1,'1',1,'读取事件通知间隔','1','int',0,
'间隔几分钟检查一次是否有系统事件通知收到',1,
'2014-11-20 09:00:00',1,sysdate(),1);

INSERT INTO sys_option
(CategoryId,OptionKey,Line,OptionName,OptionValue,DataType,SortCategoryId,
Description,Creator,CreateTime,LastEditor,LastEditTime,Version)
VALUES
(1,'2',2,'启用列表界面分页','0','int',1,
'启用列表界面分页',1,
'2014-11-20 09:00:00',1,sysdate(),1);

INSERT INTO sys_option
(CategoryId,OptionKey,Line,OptionName,OptionValue,DataType,SortCategoryId,
Description,Creator,CreateTime,LastEditor,LastEditTime,Version)
VALUES
(1,'3',3,'列表界面分页方式','多分页','String',0,
'列表界面分页方式',1,
'2014-11-20 09:00:00',1,sysdate(),1);
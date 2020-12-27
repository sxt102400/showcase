
DROP TABLE IF EXISTS `sys_role`;
CREATE TABLE `sys_role` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(50) NOT NULL COMMENT '角色名称',
  `type` varchar(1) DEFAULT NULL,
   status tinyint(1) DEFAULT 0 COMMENT '状态',
  PRIMARY KEY (`id`)
) COMMENT = '角色表';

DROP TABLE IF EXISTS `sys_user`;
CREATE TABLE `sys_user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(100)   DEFAULT NULL COMMENT '账号',
  `password` varchar(100)  DEFAULT NULL COMMENT '密码',
  `name` varchar(50) NOT NULL COMMENT '姓名',
   sex char(1) COMMENT '性别',
  `mobile` varchar(50) NOT NULL COMMENT '手机号',
    email varchar(200) COMMENT '邮箱',
   login_ip varchar(100) COMMENT '最后登陆IP',
  login_date datetime COMMENT '最后登陆时间',
  status tinyint(1) DEFAULT 0 COMMENT '状态',
  PRIMARY KEY (`id`)
)COMMENT = '用户表';

create table sys_permission
(
  id int(11) NOT NULL AUTO_INCREMENT COMMENT '编号',
  name varchar(100) NOT NULL COMMENT '权限名称',
  code varchar(100) NOT NULL COMMENT '权限编码',
  state char(1) DEFAULT '0'  COMMENT '状态',
  PRIMARY KEY (id)
)COMMENT = '权限表';

CREATE TABLE sys_menu
(
  id bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  parent_id bigint(20) NOT NULL COMMENT '父级编号',
  parent_ids varchar(2000) NOT NULL COMMENT '所有父级编号',
  name varchar(100) NOT NULL COMMENT '名称',
  sort decimal(10,0) NOT NULL COMMENT '排序',
  href varchar(2000) COMMENT '链接',
  target varchar(20) COMMENT '目标',
  icon varchar(100) COMMENT '图标',
  is_visible char(1) NOT NULL COMMENT '是否显示',
    permission varchar(100) COMMENT '权限标识',
  permission_code varchar(100) COMMENT '权限标识',

  state char(1) DEFAULT '0' NOT NULL COMMENT '状态',
  PRIMARY KEY (id)
) COMMENT = '菜单表'
ENGINE = InnoDB DEFAULT CHARSET = utf8;

DROP TABLE IF EXISTS `sys_user_role`;
CREATE TABLE `sys_user_role` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11),
  `role_id` int(11),
  PRIMARY KEY (`id`)
)COMMENT = '用户角色表';

DROP TABLE IF EXISTS `sys_role_permission`;
CREATE TABLE `sys_role_permission` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `role_id` int(11),
  `permission_id` int(11) ,
  PRIMARY KEY (`id`)
)COMMENT = '角色权限表';




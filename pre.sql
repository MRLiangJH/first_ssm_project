CREATE TABLE `user` (
                        `uid` int NOT NULL AUTO_INCREMENT,
                        `username` varchar(255) NOT NULL UNIQUE COMMENT '用户名',
                        `gender` enum('男','女') NOT NULL COMMENT '性别',
                        `password` varchar(45) NOT NULL COMMENT '密码',
                        `email` varchar(45) NOT NULL COMMENT '邮箱',
                        `register_time` datetime COMMENT '注册时间',
                        PRIMARY KEY (`uid`)
) ENGINE=INNODB default CHARSET=utf8mb4;


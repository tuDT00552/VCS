use bank;

INSERT INTO `bank`.`t_permission` (`permission_key`, `permission_name`,`deleted`) VALUES
 ('USER_CREATE', 'tạo user',0), ('USER_READ','xem user',0), ('USER_UPDATE', 'sửa user',0), ('USER_DELETE', 'xóa user',0);
INSERT INTO `bank`.`t_role` (`role_key`, `role_name`,`deleted`) VALUES
 ('ADMIN', 'Supper User',0), ('CUSTOMER', 'Khách',0);
INSERT INTO `bank`.`t_user` (`username`, `password`,`deleted`) VALUES
 ('tudt', '$2a$10$VObHxmxycf0.QJNXlwqEc.mFZ.iYkS5V4zAJ0xdIMSoEYoW18O7cu',0);
INSERT INTO `bank`.`t_role_permission` (`role_id`, `permission_id`) VALUES
 (1, 1), (1, 2), (1, 3), (1, 4);
INSERT INTO `bank`.`t_user_role` (`user_id`, `role_id`) VALUES
 (1, 1);
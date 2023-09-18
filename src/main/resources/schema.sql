Create TABLE User(
user_id int auto_increment primary key,
name nvarchar(255) not null,
age int not null
)


use learing_spring;
CREATE TABLE Task(
task_id int NOT NULL AUTO_INCREMENT PRIMARY KEY,
title nvarchar(100) NOT NULL,
content nvarchar(255) NOT NULL,
created_at datetime DEFAULT CURRENT_TIMESTAMP,
updated_at datetime 
)
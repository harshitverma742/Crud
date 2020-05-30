create database 'demo';
Use demo;

create table employee123(
id int(3) NOT NULL AUTO_INCREMENT,
employeename varchar(45),
address varchar(45),
dateofjoining date,
experience int(3),
dateofbirth date,
PRIMARY KEY(id)
);
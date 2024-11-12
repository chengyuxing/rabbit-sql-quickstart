/*
 Created by IntelliJ IDEA Rabbit-SQL plugin.
 User: chengyuxing
 Date: 2024/11/12
 Time: 20:43:14
 @@@
 Init sqlite datasource for test！
 @@@
 Typing "xql" keyword to get suggestions,
 e.g: "xql:new" will create a sql fragment.
*/

/*[createTable]*/
create table if not exists rabbit
(
    id       serial primary key,
    name     varchar(100),
    age      int,
    address  varchar(255),
    birthday timestamp default current_timestamp
);
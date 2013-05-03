CREATE TABLE DB2INST1.TBLNEWS  ( 
    NEWS_ITEM    	INTEGER GENERATED ALWAYS AS IDENTITY (START WITH 0, INCREMENT BY 1, CACHE 20) NOT NULL,
    DATE_BEGIN   	TIMESTAMP,
    DATE_END     	TIMESTAMP,
    TITLE        	VARCHAR(255),
    CONTENT      	VARCHAR(255),
    HEADER_COLOR 	VARCHAR(255),
    CONTENT_COLOR	VARCHAR(255),
    ISLOCKED     	VARCHAR(255),
    CONSTRAINT PK_USER_TEST PRIMARY KEY(NEWS_ITEM)
)
GO

insert into users (id, username, password, userrole) values (1, 'zk', 'zk', 'admin')
go

insert into products (id, productname, createDate, price, quantity, available, imgPath) values (1, 'Cookies', CURRENT_TIMESTAMP, 4.0, 30, 1, '/springdemo/image/cookie.jpg')
go
insert into products (id, productname, createDate, price, quantity, available, imgPath) values (2, 'Toast', CURRENT_TIMESTAMP, 3.0, 43, 1, '/springdemo/image/toast.jpg')
go
insert into products (id, productname, createDate, price, quantity, available, imgPath) values (3, 'Chocolate', CURRENT_TIMESTAMP, 5.1, 12, 1, '/springdemo/image/chocolate.jpg')
go
insert into products (id, productname, createDate, price, quantity, available, imgPath) values (4, 'Butter', CURRENT_TIMESTAMP, 2.5, 60, 1, '/springdemo/image/butter.jpg')
go
insert into products (id, productname, createDate, price, quantity, available, imgPath) values (5, 'Milk', CURRENT_TIMESTAMP, 3.1, 71, 1, '/springdemo/image/milk.jpg')
go
insert into products (id, productname, createDate, price, quantity, available, imgPath) values (6, 'Coffee Powder', CURRENT_TIMESTAMP, 10.4, 59, 1, '/springdemo/image/coffee.jpg')
go
insert into cartitems (id, prodId, userId, amount, createDate) values (1, 1, 1, 3, '2012-07-31 12:00:00')
go

insert into orders (id, userId, status, createDate, description) values (1, 1, 'processing', CURRENT TIMESTAMP, ' nobody at home in day time')
go
insert into OrderedItems (id, orderId, prodid, name, price, quantity) values (1, 1, 1, 'Cookies', 4.0, 10)
go
insert into OrderedItems (id, orderId, prodid, name, price, quantity) values (2, 1, 2, 'Toast', 3.1, 5)
go





CREATE DATABASE ShoppingDB

Go
USE [ShoppingDB]
GO
/****** Object:  Table [dbo].[Account]    Script Date: 12/04/2019 15:03:35 ******/
SET ANSI_NULLS ON -- 'is null' is equals to '= null', '<> null' is equals to 'is not null'
GO
SET QUOTED_IDENTIFIER ON -- the double quote is used to object name, not to string
GO
SET ANSI_PADDING ON -- used when creat and insert data to table, ms sql will not trim the trailing spaces when insert into varchar field or trailing nulls - varbinary field
GO
CREATE TABLE [dbo].[Account](
	[user_mail] [varchar](100) NOT NULL,
	[password] [varchar](64) NOT NULL,
	[account_role] [int] NOT NULL,
	[user_name] [nvarchar](50) NOT NULL,
	[user_address] [nvarchar](255) NULL,
	[user_phone] [varchar](10) NULL,
PRIMARY KEY CLUSTERED 
(
	[user_mail] ASC
)WITH (PAD_INDEX  = OFF, STATISTICS_NORECOMPUTE  = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS  = ON, ALLOW_PAGE_LOCKS  = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [dbo].[Products]    Script Date: 12/04/2019 15:03:35 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[Products](
	[product_id] [int] IDENTITY(1,1) NOT NULL,
	[product_name] [nvarchar](100) NOT NULL,
	[product_des] [nvarchar](255) NULL,
	[product_price] [float] NOT NULL,
	[product_img_source] [varchar](255) NULL,
	[product_type] [varchar](100) NULL,
	[product_brand] [varchar](100) NULL,
PRIMARY KEY CLUSTERED 
(
	[product_id] ASC
)WITH (PAD_INDEX  = OFF, STATISTICS_NORECOMPUTE  = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS  = ON, ALLOW_PAGE_LOCKS  = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [dbo].[Orders]    Script Date: 12/04/2019 15:03:35 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[Orders](
	[user_mail] [varchar](100) NULL,
	[order_id] [int] IDENTITY(1,1) NOT NULL,
	[order_status] [int] NULL,
	[order_date] [date] NOT NULL,
	[order_discount_code] [varchar](8) NULL,
	[order_address] [nvarchar](255) NOT NULL,
PRIMARY KEY CLUSTERED 
(
	[order_id] ASC
)WITH (PAD_INDEX  = OFF, STATISTICS_NORECOMPUTE  = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS  = ON, ALLOW_PAGE_LOCKS  = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
SET ANSI_PADDING OFF
GO

/****** Object:  Table [dbo].[Orders_detail]    Script Date: 12/04/2019 15:03:35 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Orders_detail](
	[order_id] [int] NOT NULL,
	[product_id] [int] NOT NULL,
	[amount_product] [int] NULL,
	[price_product] [float] NULL,
PRIMARY KEY CLUSTERED 
(
	[order_id] ASC,
	[product_id] ASC
)WITH (PAD_INDEX  = OFF, STATISTICS_NORECOMPUTE  = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS  = ON, ALLOW_PAGE_LOCKS  = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Default [DF__Orders__order_da__1273C1CD]    Script Date: 12/04/2019 15:03:35 ******/
ALTER TABLE [dbo].[Orders] ADD  DEFAULT (getdate()) FOR [order_date]
GO
/****** Object:  ForeignKey [FK__Orders_de__order__182C9B23]    Script Date: 12/04/2019 15:03:35 ******/
ALTER TABLE [dbo].[Orders_detail]  WITH CHECK ADD FOREIGN KEY([order_id])
REFERENCES [dbo].[Orders] ([order_id])
GO
/****** Object:  ForeignKey [FK__Orders_de__produ__1920BF5C]    Script Date: 12/04/2019 15:03:35 ******/
ALTER TABLE [dbo].[Orders_detail]  WITH CHECK ADD FOREIGN KEY([product_id])
REFERENCES [dbo].[Products] ([product_id])
GO
create proc whenFindBrand
@from int,
@to int,
@name Nvarchar(50)
as
begin
 SELECT p.product_id ,p.product_name, p.product_des, p.product_price, p.product_img_source, p.product_type, p.product_brand FROM ( 
  SELECT *, ROW_NUMBER() OVER (ORDER BY product_id) as row 
  FROM Products p where p.product_brand = @name
 ) p WHERE p.row >= @from and p.row <= @to
end
go
create procedure whenFindName
@from int,
@to int,
@name Nvarchar(50)
as
begin
 SELECT p.product_id ,p.product_name, p.product_des, p.product_price, p.product_img_source, p.product_type, p.product_brand FROM ( 
  SELECT *, ROW_NUMBER() OVER (ORDER BY product_id) as row 
  FROM Products p where p.product_name like '%' + @name + '%'
 ) p WHERE p.row >= @from and p.row <= @to
end
go
create proc listProduct
@from int,
@to int
as
begin
 SELECT p.product_id ,p.product_name, p.product_des, p.product_price, p.product_img_source, p.product_type, p.product_brand FROM ( 
  SELECT *, ROW_NUMBER() OVER (ORDER BY product_id) as row 
  FROM Products p
 ) p WHERE p.row >= @from and p.row <= @to
end
GO
create view order_detail_detail as
	select o.user_mail, o.order_id, p.product_name, po.amount_product, po.price_product
	from Orders as o, Products as p, Orders_detail as po
	where o.order_id = po.order_id and p.product_id = po.product_id
GO
create procedure GetProductFromDetail @orderID int as
begin
	select product_name from order_detail_detail
	where order_id = @orderID;
end
GO
--adding data to product table
insert into Products(product_name,product_des,product_price,product_img_source,product_type,product_brand)
values('iPhone 11 Pro Max 512GB',N'Màn hình: 6.5", Super Retina XDR
HĐH: iOS 13
CPU: Apple A13 Bionic 6 nhân
RAM: 4 GB, ROM: 512 GB
Camera: 3 camera 12 MP, Selfie: 12 MP',43.990,'https://cdn.tgdd.vn/Products/Images/42/210654/iphone-11-pro-max-512gb-gold-600x600.jpg','cellphone',
'apple')

insert into Products(product_name,product_des,product_price,product_img_source,product_type,product_brand)
values('iPhone 11 Pro Max 256GB',N'Màn hình: 6.5", Super Retina XDR
HĐH: iOS 13
CPU: Apple A13 Bionic 6 nhân
RAM: 4 GB, ROM: 512 GB
Camera: 3 camera 12 MP, Selfie: 12 MP',37.990,'https://cdn.tgdd.vn/Products/Images/42/210653/iphone-11-pro-max-256gb-black-600x600.jpg','cellphone',
'apple')

insert into Products(product_name,product_des,product_price,product_img_source,product_type,product_brand)
values('iPhone Xs Max 256GB',N'Màn hình: 6.5", Super Retina
HĐH: iOS 12
CPU: Apple A12 Bionic 6 nhân
RAM: 4 GB, ROM: 256 GB
Camera: Chính 12 MP & Phụ 12 MP, Selfie: 7 MP',32.990,'https://cdn.tgdd.vn/Products/Images/42/190322/iphone-xs-max-256gb-white-600x600.jpg','cellphone',
'apple')

insert into Products(product_name,product_des,product_price,product_img_source,product_type,product_brand)
values('iPhone X 256GB',N'Màn hình: 5.8", Super Retina
HĐH: iOS 12
CPU: Apple A11 Bionic 6 nhân
RAM: 3 GB, ROM: 256 GB
Camera: Chính 12 MP & Phụ 12 MP, Selfie: 7 MP',27.990,'https://cdn.tgdd.vn/Products/Images/42/190324/iphone-xs-256gb-white-600x600.jpg','cellphone',
'apple')

insert into Products(product_name,product_des,product_price,product_img_source,product_type,product_brand)
values('iPhone Xs 64GB',N'Màn hình: 5.8", Super Retina
HĐH: iOS 12
CPU: Apple A12 Bionic 6 nhân
RAM: 4 GB, ROM: 64 GB
Camera: Chính 12 MP & Phụ 12 MP, Selfie: 7 MP',24.990,'https://cdn.tgdd.vn/Products/Images/42/190321/iphone-xs-max-gold-600x600.jpg','cellphone',
'apple')

insert into Products(product_name,product_des,product_price,product_img_source,product_type,product_brand)
values('iPhone Xr 128GB',N'Màn hình: 6.1", Liquid Retina
HĐH: iOS 12
CPU: Apple A12 Bionic 6 nhân
RAM: 3 GB, ROM: 128 GB
Camera: 12 MP, Selfie: 7 MP',17.990,'https://cdn.tgdd.vn/Products/Images/42/191483/iphone-xr-128gb-red-600x600.jpg','cellphone',
'apple')

insert into Products(product_name,product_des,product_price,product_img_source,product_type,product_brand)
values('iPhone Xr 128GB',N'Màn hình: 6.1", Liquid Retina
HĐH: iOS 12
CPU: Apple A12 Bionic 6 nhân
RAM: 3 GB, ROM: 128 GB
Camera: 12 MP, Selfie: 7 MP',17.990,'https://cdn.tgdd.vn/Products/Images/42/191483/iphone-xr-128gb-red-600x600.jpg','cellphone',
'apple')

insert into Products(product_name,product_des,product_price,product_img_source,product_type,product_brand)
values('iPhone 8 Plus 64GB',N'Màn hình: 5.5", Retina HD
HĐH: iOS 12
CPU: Apple A11 Bionic 6 nhân
RAM: 3 GB, ROM: 64 GB
Camera: Chính 12 MP & Phụ 12 MP, Selfie: 7 MP',16.590,'https://cdn.tgdd.vn/Products/Images/42/114110/iphone-8-plus-hh-600x600.jpg','cellphone',
'apple')

insert into Products(product_name,product_des,product_price,product_img_source,product_type,product_brand)
values('iPhone 7 Plus 32GB',N'Màn hình: 5.5", Retina HD
HĐH: iOS 12
CPU: Apple A10 Fusion 4 nhân 64-bit
RAM: 3 GB, ROM: 32 GB
Camera: Chính 12 MP & Phụ 12 MP, Selfie: 7 MP',12.490,'https://cdn.tgdd.vn/Products/Images/42/78124/iphone-7-plus-32gb-gold-600x600.jpg','cellphone',
'apple')

insert into Products(product_name,product_des,product_price,product_img_source,product_type,product_brand)
values('iPhone 7 32GB',N'Màn hình: 4.7", Retina HD
HĐH: iOS 12
CPU: Apple A10 Fusion 4 nhân 64-bit
RAM: 2 GB, ROM: 32 GB
Camera: 12 MP, Selfie: 7 MP',10.490,'https://cdn.tgdd.vn/Products/Images/42/74110/iphone-7-gold-600x600.jpg','cellphone',
'apple')
insert into Products (product_name, product_des, product_price, product_img_source, product_type, product_brand) values
('Apple iPad 10.2 (2019)', 'IPS LCD;Li-Po 8827 mAh, non-removable (32.9 Wh);', 350.98, 'https://fdn2.gsmarena.com/vv/bigpic/apple-ipad7-102-inches.jpg', 'tablet', 'Apple'),
('Apple iPad 10.2 (2020)', 'Retina IPS LCD, 500 nits (typ);Li-Ion, non-removable (32.4 Wh);', 370.98, 'https://fdn2.gsmarena.com/vv/bigpic/apple-ipad8-102-inches-2020.jpg', 'tablet', 'Apple'),
('Apple iPad 2 CDMA', 'IPS LCD;Non-removable Li-Po 6930 mAh battery (25 Wh);', 200.98, 'https://fdn2.gsmarena.com/vv/bigpic/apple-ipad2-new.jpg', 'tablet', 'Apple'),
('Apple iPad 2 Wi-Fi', 'IPS LCD;Non-removable Li-Po 6930 mAh battery (25 Wh);', 200.98, 'https://fdn2.gsmarena.com/vv/bigpic/apple-ipad2-new.jpg', 'tablet', 'Apple'),
('Apple iPad 2 Wi-Fi + 3G', 'IPS LCD;Non-removable Li-Po 6930 mAh battery (25 Wh);', 370.98, 'https://fdn2.gsmarena.com/vv/bigpic/apple-ipad2-new.jpg', 'tablet', 'Apple'),
('Apple iPad 3 Wi-Fi', 'IPS LCD;Non-removable Li-Po 11560 mAh battery (42.5 Wh);', 290.98, 'https://fdn2.gsmarena.com/vv/bigpic/apple-ipad-3-new.jpg', 'tablet', 'Apple'),
('Apple iPad 3 Wi-Fi + Cellular', 'IPS LCD;Non-removable Li-Po 11560 mAh battery (42.5 Wh);', 400.98, 'https://fdn2.gsmarena.com/vv/bigpic/apple-ipad-3-new.jpg', 'tablet', 'Apple'),
('Apple iPad 4 Wi-Fi', 'IPS LCD;Li-Po 11560 mAh, non-removable (42.5 Wh);', 350.98, 'https://fdn2.gsmarena.com/vv/bigpic/apple-ipad-3-new.jpg', 'tablet', 'Apple'),
('Apple iPad 4 Wi-Fi + Cellular', 'IPS LCD;Li-Po 11560 mAh, non-removable (42.5 Wh);', 500.98, 'https://fdn2.gsmarena.com/vv/bigpic/apple-ipad-3-new.jpg', 'tablet', 'Apple'),
('Apple iPad 9.7 (2017)', 'IPS LCD;Li-Po 8827 mAh, non-removable (32.9 Wh);', 390.98, 'https://fdn2.gsmarena.com/vv/bigpic/apple-ipad-97-2017.jpg', 'tablet', 'Apple'),
('Apple iPad 9.7 (2018)', 'IPS LCD;Li-Po 8827 mAh, non-removable (32.9 Wh);', 350.98, 'https://fdn2.gsmarena.com/vv/bigpic/apple-ipad-97-2018.jpg', 'tablet', 'Apple'),
('Apple iPad Air', 'IPS LCD;Li-Po 8600 mAh, non-removable (32.4 Wh);', 350.98, 'https://fdn2.gsmarena.com/vv/bigpic/apple-ipad-air.jpg', 'tablet', 'Apple'),
('Apple iPad Air (2019)', 'IPS LCD;Li-Po 8134 mAh, non-removable (30.8 Wh);', 550.98, 'https://fdn2.gsmarena.com/vv/bigpic/apple-ipad-air3-2019.jpg', 'tablet', 'Apple'),
('Apple iPad Air (2020)', 'Liquid Retina IPS LCD, 500 nits (typ);Li-Ion 7606 mAh (28.93 Wh), non-removable;', 640.98, 'https://fdn2.gsmarena.com/vv/bigpic/apple-ipad-air4-2020.jpg', 'tablet', 'Apple'),
('Apple iPad Air 2', 'IPS LCD;Li-Po 7340 mAh, non-removable (27.62 Wh);', 440.98, 'https://fdn2.gsmarena.com/vv/bigpic/apple-ipad-air-2-new.jpg', 'tablet', 'Apple'),
('Apple iPad mini (2019)', 'IPS LCD;Li-Ion 5124 mAh, non-removable (19.1 Wh);', 450.98, 'https://fdn2.gsmarena.com/vv/bigpic/apple-ipad-mini-2019.jpg', 'tablet', 'Apple'),
('Apple iPad mini 2', 'IPS LCD;Li-Po 6470 mAh, non-removable (24.3 Wh);', 270.98, 'https://fdn2.gsmarena.com/vv/bigpic/apple-ipad-mini2.jpg', 'tablet', 'Apple'),
('Apple iPad mini 3', 'IPS LCD;Li-Po 6470 mAh, non-removable (24.3 Wh);', 400.98, 'https://fdn2.gsmarena.com/vv/bigpic/apple-ipad-mini-3-new.jpg', 'tablet', 'Apple'),
('Apple iPad mini 4 (2015)', 'IPS LCD;Li-Ion 5124 mAh, non-removable (19.1 Wh);', 360.98, 'https://fdn2.gsmarena.com/vv/bigpic/ipad-mini-41.jpg', 'tablet', 'Apple'),
('Apple iPad mini Wi-Fi', 'IPS LCD;Li-Po 4490 mAh, non-removable (16.7 Wh);', 200.98, 'https://fdn2.gsmarena.com/vv/bigpic/apple-ipad-mini-final.jpg', 'tablet', 'Apple'),
('Apple iPad mini Wi-Fi + Cellular', 'IPS LCD;Li-Po 4490 mAh, non-removable (16.7 Wh);', 300.98, 'https://fdn2.gsmarena.com/vv/bigpic/apple-ipad-mini-final.jpg', 'tablet', 'Apple'),
('Apple iPad Pro 10.5 (2017)', 'IPS LCD, 120Hz;Li-Ion 8134 mAh, non-removable (30.8 Wh);', 730.98, 'https://fdn2.gsmarena.com/vv/bigpic/apple-ipad-pro-105-2017.jpg', 'tablet', 'Apple'),
('Apple iPad Pro 11 (2018)', 'IPS LCD, 120Hz;Li-Po 7812 mAh, non-removable (29.45 Wh);', 880.98, 'https://fdn2.gsmarena.com/vv/bigpic/apple-ipad-pro-11-2018.jpg', 'tablet', 'Apple'),
('Apple iPad Pro 11 (2020)', 'IPS LCD, 120Hz, 600 nits (typ);Li-Po 7538 mAh (28.65 Wh), non-removable;', 800.98, 'https://fdn2.gsmarena.com/vv/bigpic/apple-ipad-pro-11-2020.jpg', 'tablet', 'Apple'),
('Apple iPad Pro 11 (2021)', 'Liquid Retina IPS LCD, 120Hz, HDR10, Dolby Vision, 600 nits (typ);Li-Po 7538 mAh (28.65 Wh), non-removable;', 870.98, 'https://fdn2.gsmarena.com/vv/bigpic/apple-ipad-pro-11-2021.jpg', 'tablet', 'Apple'),
('Apple iPad Pro 12.9 (2015)', 'IPS LCD;Li-Ion 10,307 mAh, non-removable (38.8 Wh);', 850.98, 'https://fdn2.gsmarena.com/vv/bigpic/apple-ipad-pro-.jpg', 'tablet', 'Apple'),
('Apple iPad Pro 12.9 (2017)', 'IPS LCD, 120Hz;Li-Ion 10,891 mAh, non-removable (41 Wh);', 900.98, 'https://fdn2.gsmarena.com/vv/bigpic/apple-ipad-pro-129-2017.jpg', 'tablet', 'Apple'),
('Apple iPad Pro 12.9 (2018)', 'IPS LCD, 120Hz;Li-Po 9720 mAh, non-removable (36.71 Wh);', 110.98, 'https://fdn2.gsmarena.com/vv/bigpic/apple-ipad-pro-129-2018.jpg', 'tablet', 'Apple'),
('Apple iPad Pro 12.9 (2020)', 'IPS LCD, 120Hz, 600 nits (typ);Li-Po 9720 mAh, non-removable (36.71 Wh);', 100.98, 'https://fdn2.gsmarena.com/vv/bigpic/apple-ipad-pro-12-2020.jpg', 'tablet', 'Apple'),
('Apple iPad Pro 12.9 (2021)', 'Liquid Retina XDR mini-LED LCD, 120Hz, HDR10, Dolby Vision, 1000 nits (typ), 1600 nits (peak);Li-Po (40.88 Wh), non-removable;', 120.98, 'https://fdn2.gsmarena.com/vv/bigpic/apple-ipad-pro-129-2021.jpg', 'tablet', 'Apple'),
('Apple iPad Pro 9.7 (2016)', 'IPS LCD;Li-Ion 7306 mAh, non-removable (27.9 Wh);', 690.98, 'https://fdn2.gsmarena.com/vv/bigpic/apple-ipad-pro-97.jpg', 'tablet', 'Apple'),
('Apple iPad Wi-Fi', 'IPS LCD;Non-removable Li-Po 6600 mAh battery (24.8 Wh);', 310.98, 'https://fdn2.gsmarena.com/vv/bigpic/apple-ipad-original.jpg', 'tablet', 'Apple'),
('Apple iPad Wi-Fi + 3G', 'IPS LCD;Non-removable Li-Po 6600 mAh battery (24.8 Wh);', 410.98, 'https://fdn2.gsmarena.com/vv/bigpic/apple-ipad-original.jpg', 'tablet', 'Apple'),
('Apple iPhone', 'TFT;Non-removable Li-Ion battery;', 420.98, 'https://fdn2.gsmarena.com/vv/bigpic/apple-iphone.gif', 'cellphone', 'Apple'),
('Apple iPhone 11', 'Liquid Retina IPS LCD, 625 nits (typ);Li-Ion 3110 mAh, non-removable (11.91 Wh);', 509.95, 'https://fdn2.gsmarena.com/vv/bigpic/apple-iphone-11.jpg', 'cellphone', 'Apple'),
('Apple iPhone 11 Pro', 'Super Retina XDR OLED, HDR10, Dolby Vision, 800 nits (typ), 1200 nits (peak);Li-Ion 3046 mAh, non-removable (11.67 Wh);', 89.99, 'https://fdn2.gsmarena.com/vv/bigpic/apple-iphone-11-pro-max-.jpg', 'cellphone', 'Apple'),
('Apple iPhone 11 Pro Max', 'Super Retina XDR OLED, HDR10, Dolby Vision, 800 nits (typ), 1200 nits (peak);Li-Ion 3969 mAh, non-removable (15.04 Wh);', 708.72, 'https://fdn2.gsmarena.com/vv/bigpic/apple-iphone-11-pro.jpg', 'cellphone', 'Apple'),
('Apple iPhone 12', 'Super Retina XDR OLED, HDR10, Dolby Vision, 625 nits (typ), 1200 nits (peak);Li-Ion 2815 mAh, non-removable (10.78 Wh);', 739.99, 'https://fdn2.gsmarena.com/vv/bigpic/apple-iphone-12.jpg', 'cellphone', 'Apple'),
('Apple iPhone 12 mini', 'Super Retina XDR OLED, HDR10, Dolby Vision, 625 nits (typ), 1200 nits (peak);Li-Ion 2227 mAh, non-removable;', 639.99, 'https://fdn2.gsmarena.com/vv/bigpic/apple-iphone-12-mini.jpg', 'cellphone', 'Apple'),
('Apple iPhone 12 Pro', 'Super Retina XDR OLED, HDR10, Dolby Vision, 800 nits (typ), 1200 nits (peak);Li-Ion 2815 mAh, non-removable (10.78 Wh);', 999.00, 'https://fdn2.gsmarena.com/vv/bigpic/apple-iphone-12-pro--.jpg', 'cellphone', 'Apple'),
('Apple iPhone 12 Pro Max', 'Super Retina XDR OLED, HDR10, Dolby Vision, 800 nits (typ), 1200 nits (peak);Li-Ion 3687 mAh, non-removable (14.13 Wh);', 1099.0, 'https://fdn2.gsmarena.com/vv/bigpic/apple-iphone-12-pro-max-.jpg', 'cellphone', 'Apple'),
('Apple iPhone 3G', 'TFT;Non-removable Li-Ion battery;', 90.98, 'https://fdn2.gsmarena.com/vv/bigpic/apple-iphone3g.jpg', 'cellphone', 'Apple'),
('Apple iPhone 3GS', 'TFT;Non-removable Li-Ion battery;', 110.98, 'https://fdn2.gsmarena.com/vv/bigpic/apple-iphone-3gs-ofic.jpg', 'cellphone', 'Apple'),
('Apple iPhone 4', 'IPS LCD;Non-removable Li-Po 1420 mAh battery;', 200.98, 'https://fdn2.gsmarena.com/vv/bigpic/apple-iphone-4-ofic-final.jpg', 'cellphone', 'Apple'),
('Apple iPhone 4 CDMA', 'IPS LCD;Non-removable Li-Po 1420 mAh battery;', 150.98, 'https://fdn2.gsmarena.com/vv/bigpic/apple-iphone4-cdma.jpg', 'cellphone', 'Apple'),
('Apple iPhone 4s', 'IPS LCD;Non-removable Li-Po 1432 mAh battery (5.3 Wh);', 190.98, 'https://fdn2.gsmarena.com/vv/bigpic/apple-iphone-4s-new.jpg', 'cellphone', 'Apple'),
('Apple iPhone 5', 'IPS LCD;Non-removable Li-Po 1440 mAh battery (5.45 Wh);', 340.98, 'https://fdn2.gsmarena.com/vv/bigpic/apple-iphone-5-ofic.jpg', 'cellphone', 'Apple'),
('Apple iPhone 5c', 'IPS LCD;Li-Po 1510 mAh, non-removable (5.73 Wh);', 300.98, 'https://fdn2.gsmarena.com/vv/bigpic/apple-iphone-5c-new2.jpg', 'cellphone', 'Apple'),
('Apple iPhone 5s', 'IPS LCD;Li-Po 1560 mAh, non-removable (5.92 Wh);', 330.98, 'https://fdn2.gsmarena.com/vv/bigpic/apple-iphone-5s-ofic.jpg', 'cellphone', 'Apple'),
('Apple iPhone 6', 'IPS LCD;Li-Po 1810 mAh, non-removable (6.9 Wh);', 100.98, 'https://fdn2.gsmarena.com/vv/bigpic/apple-iphone-6-4.jpg', 'cellphone', 'Apple'),
('Apple iPhone 6 Plus', 'IPS LCD;Li-Po 2915 mAh, non-removable (11.1 Wh);', 420.98, 'https://fdn2.gsmarena.com/vv/bigpic/apple-iphone-6-plus2.jpg', 'cellphone', 'Apple'),
('Apple iPhone 6s', 'IPS LCD;Li-Ion 1715 mAh, non-removable (6.91 Wh);', 230.98, 'https://fdn2.gsmarena.com/vv/bigpic/apple-iphone-6s1.jpg', 'cellphone', 'Apple'),
('Apple iPhone 6s Plus', 'IPS LCD;Li-Ion 2750 mAh, non-removable (10.45 Wh);', 240.98, 'https://fdn2.gsmarena.com/vv/bigpic/apple-iphone-6s-plus.jpg', 'cellphone', 'Apple'),
('Apple iPhone 7', 'Retina IPS LCD, 625 nits (typ);Li-Ion 1960 mAh, non-removable (7.45 Wh);', 180.98, 'https://fdn2.gsmarena.com/vv/bigpic/apple-iphone-7r4.jpg', 'cellphone', 'Apple'),
('Apple iPhone 7 Plus', 'Retina IPS LCD, 625 nits (typ);Li-Ion 2900 mAh, non-removable (11.1 Wh);', 320.98, 'https://fdn2.gsmarena.com/vv/bigpic/apple-iphone-7-plus-r2.jpg', 'cellphone', 'Apple'),
('Apple iPhone 8', 'Retina IPS LCD, 625 nits (typ);Li-Ion 1821 mAh, non-removable (6.96 Wh);', 210.98, 'https://fdn2.gsmarena.com/vv/bigpic/apple-iphone-8-new.jpg', 'cellphone', 'Apple'),
('Apple iPhone 8 Plus', 'Retina IPS LCD, 625 nits (typ);Li-Ion 2691 mAh, non-removable (10.28 Wh);', 340.98, 'https://fdn2.gsmarena.com/vv/bigpic/apple-iphone-8-plus-new.jpg', 'cellphone', 'Apple'),
('Apple iPhone SE', 'IPS LCD;Li-Po 1624 mAh, non-removable (6.21 Wh);', 140.98, 'https://fdn2.gsmarena.com/vv/bigpic/apple-iphone-5se-ofic.jpg', 'cellphone', 'Apple'),
('Apple iPhone SE (2020)', 'Retina IPS LCD, 625 nits (typ);Li-Ion 1821 mAh, non-removable (6.96 Wh);', 116.85, 'https://fdn2.gsmarena.com/vv/bigpic/apple-iphone-se-2020.jpg', 'cellphone', 'Apple'),
('Apple iPhone X', 'Super Retina OLED, HDR10, Dolby Vision, 625 nits (typ);Li-Ion 2716 mAh, non-removable (10.35 Wh);', 314.99, 'https://fdn2.gsmarena.com/vv/bigpic/apple-iphone-x.jpg', 'cellphone', 'Apple'),
('Apple iPhone XR', 'Liquid Retina IPS LCD, 625 nits (typ);Li-Ion 2942 mAh, non-removable (11.16 Wh);', 308.88, 'https://fdn2.gsmarena.com/vv/bigpic/apple-iphone-xr-new.jpg', 'cellphone', 'Apple'),
('Apple iPhone XS', 'Super Retina OLED, HDR10, Dolby Vision, 625 nits (typ);Li-Ion 2658 mAh, non-removable (10.13 Wh);', 365.00, 'https://fdn2.gsmarena.com/vv/bigpic/apple-iphone-xs-new.jpg', 'cellphone', 'Apple'),
('Apple iPhone XS Max', 'Super Retina OLED, HDR10, Dolby Vision, 625 nits (typ);Li-Ion 3174 mAh, non-removable (12.08 Wh);', 369.00, 'https://fdn2.gsmarena.com/vv/bigpic/apple-iphone-xs-max-new1.jpg', 'cellphone', 'Apple'),
('Apple Watch 38mm (1st gen)', 'Retina OLED, 450 nits (peak);Li-Ion 205 mAh, non-removable (0.78 Wh);', 650.98, 'https://fdn2.gsmarena.com/vv/bigpic/apple-watch-38mm.jpg', 'smartwatch', 'Apple'),
('Apple Watch 42mm (1st gen)', 'Retina OLED, 450 nits (peak);Li-Ion 250 mAh, non-removable (0.94 Wh);', 700.98, 'https://fdn2.gsmarena.com/vv/bigpic/apple-watch-42mm.jpg', 'smartwatch', 'Apple'),
('Apple Watch Edition 38mm (1st gen)', 'Retina OLED, 450 nits (peak);Li-Ion 205 mAh, non-removable (0.78 Wh);', 11000.98, 'https://fdn2.gsmarena.com/vv/bigpic/apple-watch-edition-38mm.jpg', 'smartwatch', 'Apple'),
('Apple Watch Edition 42mm (1st gen)', 'Retina OLED, 450 nits (peak);Li-Ion 250 mAh, non-removable (0.94 Wh);', 13000.98, 'https://fdn2.gsmarena.com/vv/bigpic/apple-watch-edition-42mm.jpg', 'smartwatch', 'Apple'),
('Apple Watch Edition Series 2 38mm', 'Retina OLED, 1000 nits (peak);Li-Ion 273 mAh, non-removable (1.03 Wh);', 1450.98, 'https://fdn2.gsmarena.com/vv/bigpic/apple-watch2-s2-edition-38mm.jpg', 'smartwatch', 'Apple'),
('Apple Watch Edition Series 2 42mm', 'Retina OLED, 1000 nits (peak);Li-Ion 334 mAh, non-removable (1.27 Wh);', 1500.98, 'https://fdn2.gsmarena.com/vv/bigpic/apple-watch2-edition-42mm.jpg', 'smartwatch', 'Apple'),
('Apple Watch Edition Series 3', 'Retina OLED, 1000 nits (peak);Li-Ion 341 mAh, non-removable (1.34 Wh);', 1450.98, 'https://fdn2.gsmarena.com/vv/bigpic/apple-watch-edition-series3.jpg', 'smartwatch', 'Apple'),
('Apple Watch Edition Series 5', 'Retina LTPO OLED, 1000 nits (peak);Li-Po 296 mAh (1.13 Wh), non-removable;', 850.98, 'https://fdn2.gsmarena.com/vv/bigpic/apple-watch-edition-series-5.jpg', 'smartwatch', 'Apple'),
('Apple Watch Edition Series 6', 'Retina LTPO OLED, 1000 nits (peak);Li-Ion 303.8 mAh (1.17 Wh), non-removable;', 810.98, 'https://fdn2.gsmarena.com/vv/bigpic/apple-watch-s6-titanium.jpg', 'smartwatch', 'Apple'),
('Apple Watch SE', 'Retina LTPO OLED, 1000 nits (peak);Li-Ion, non-removable;', 290.98, 'https://fdn2.gsmarena.com/vv/bigpic/apple-watch-se.jpg', 'smartwatch', 'Apple'),
('Apple Watch Series 1 Aluminum 38mm', 'Retina OLED, 450 nits (peak);Li-Ion 205 mAh, non-removable (0.78 Wh);', 270.98, 'https://fdn2.gsmarena.com/vv/bigpic/apple-watch2-s2-sport-38mm.jpg', 'smartwatch', 'Apple'),
('Apple Watch Series 1 Aluminum 42mm', 'Retina OLED, 450 nits (peak);Li-Ion 250 mAh, non-removable (0.94 Wh);', 300.98, 'https://fdn2.gsmarena.com/vv/bigpic/apple-watch1-sport-42mm.jpg', 'smartwatch', 'Apple'),
('Apple Watch Series 2 38mm', 'Retina OLED, 1000 nits (peak);Li-Ion 273 mAh, non-removable (1.03 Wh);', 650.98, 'https://fdn2.gsmarena.com/vv/bigpic/apple-watch2-s2-38mm.jpg', 'smartwatch', 'Apple'),
('Apple Watch Series 2 42mm', 'Retina OLED, 1000 nits (peak);Li-Ion 334 mAh, non-removable (1.27 Wh);', 700.98, 'https://fdn2.gsmarena.com/vv/bigpic/apple-watch2-s2-42mm.jpg', 'smartwatch', 'Apple'),
('Apple Watch Series 2 Aluminum 38mm', 'Retina OLED, 1000 nits (peak);Li-Ion 273 mAh, non-removable (1.03 Wh);', 420.98, 'https://fdn2.gsmarena.com/vv/bigpic/apple-watch2-s2-sport-38mm.jpg', 'smartwatch', 'Apple'),
('Apple Watch Series 2 Aluminum 42mm', 'Retina OLED, 1000 nits (peak);Li-Ion 334 mAh, non-removable (1.27 Wh);', 450.98, 'https://fdn2.gsmarena.com/vv/bigpic/apple-watch2-s2-sport-42mm.jpg', 'smartwatch', 'Apple'),
('Apple Watch Series 3', 'Retina OLED, 1000 nits (peak);Li-Ion 341 mAh, non-removable (1.34 Wh);', 700.98, 'https://fdn2.gsmarena.com/vv/bigpic/apple-watch-series3-.jpg', 'smartwatch', 'Apple'),
('Apple Watch Series 3 Aluminum', 'Retina OLED, 1000 nits (peak);Li-Ion 341 mAh, non-removable (1.34 Wh);', 480.98, 'https://fdn2.gsmarena.com/vv/bigpic/apple-watch-series3-sport-.jpg', 'smartwatch', 'Apple'),
('Apple Watch Series 4', 'Retina LTPO OLED, 1000 nits (peak);Li-Ion 292 mAh (1.12 Wh), non-removable;', 700.98, 'https://fdn2.gsmarena.com/vv/bigpic/apple-watch-series-4-steel.jpg', 'smartwatch', 'Apple'),
('Apple Watch Series 4 Aluminum', 'Retina LTPO OLED, 1000 nits (peak);Li-Ion 292 mAh (1.12 Wh), non-removable;', 430.98, 'https://fdn2.gsmarena.com/vv/bigpic/apple-watch-series-4-aluminum.jpg', 'smartwatch', 'Apple'),
('Apple Watch Series 5', 'Retina LTPO OLED, 1000 nits (peak);Li-Po 296 mAh (1.13 Wh), non-removable;', 750.98, 'https://fdn2.gsmarena.com/vv/bigpic/apple-watch-series-5.jpg', 'smartwatch', 'Apple'),
('Apple Watch Series 5 Aluminum', 'Retina LTPO OLED, 1000 nits (peak);Li-Po 296 mAh (1.13 Wh), non-removable;', 450.98, 'https://fdn2.gsmarena.com/vv/bigpic/apple-watch-aluminum-series-5.jpg', 'smartwatch', 'Apple'),
('Apple Watch Series 6', 'Retina LTPO OLED, 1000 nits (peak);Li-Ion 303.8 mAh (1.17 Wh), non-removable;', 710.98, 'https://fdn2.gsmarena.com/vv/bigpic/apple-watch-s6-steel.jpg', 'smartwatch', 'Apple'),
('Apple Watch Series 6 Aluminum', 'Retina LTPO OLED, 1000 nits (peak);Li-Ion 303.8 mAh (1.17 Wh), non-removable;', 420.98, 'https://fdn2.gsmarena.com/vv/bigpic/apple-watch-s6.jpg', 'smartwatch', 'Apple'),
('Apple Watch Sport 38mm (1st gen)', 'Retina OLED, 450 nits (peak);Li-Ion 205 mAh, non-removable (0.78 Wh);', 250.98, 'https://fdn2.gsmarena.com/vv/bigpic/apple-watch-sport-38mm.jpg', 'smartwatch', 'Apple'),
('Apple Watch Sport 42mm (1st gen)', 'Retina OLED, 450 nits (peak);Li-Ion 250 mAh, non-removable (0.94 Wh);', 250.98, 'https://fdn2.gsmarena.com/vv/bigpic/apple-watch-sport-42mm2.jpg', 'smartwatch', 'Apple'),
('Ericsson A1018s', 'Monochrome graphic;Removable NiMH 800 mAh battery;', 10.99, 'https://fdn2.gsmarena.com/vv/bigpic/er1018sb.gif', 'cellphone', 'Ericsson'),
('Ericsson A2618', 'Monochrome graphic;Removable NiMH battery;', 10.99, 'https://fdn2.gsmarena.com/vv/bigpic/era2618b.gif', 'cellphone', 'Ericsson'),
('Ericsson A2628', 'Monochrome graphic;Removable NiMH battery;', 10.99, 'https://fdn2.gsmarena.com/vv/bigpic/era2628.gif', 'cellphone', 'Ericsson'),
('Ericsson A3618', 'Monochrome graphic;Removable NiMH battery;', 10.99, 'https://fdn2.gsmarena.com/vv/bigpic/era3618.gif', 'cellphone', 'Ericsson'),
('Ericsson GA 318', 'Alphanumeric;Removable NiMH 1200 mAh battery;', 10.99, 'https://fdn2.gsmarena.com/vv/bigpic/erga318b.gif', 'cellphone', 'Ericsson'),
('Ericsson GA 628', 'Alphanumeric;Removable NiMH 1000 mAh battery;', 10.99, 'https://fdn2.gsmarena.com/vv/bigpic/erga628b.gif', 'cellphone', 'Ericsson'),
('Ericsson GF 337', 'Alphanumeric;Removable NiMH 915 mAh battery;', 10.99, 'https://fdn2.gsmarena.com/vv/bigpic/ergf337b.gif', 'cellphone', 'Ericsson'),
('Ericsson GF 388', 'Alphanumeric;Removable NiMH 915 mAh battery;', 10.99, 'https://fdn2.gsmarena.com/vv/bigpic/ergf388b.gif', 'cellphone', 'Ericsson'),
('Ericsson GF 768', 'Alphanumeric;Removable NiMH 550 mAh battery;', 10.99, 'https://fdn2.gsmarena.com/vv/bigpic/ergf768b.gif', 'cellphone', 'Ericsson'),
('Ericsson GF 788', 'Alphanumeric;Removable NiMH 550 mAh battery;', 10.99, 'https://fdn2.gsmarena.com/vv/bigpic/ergf788b.gif', 'cellphone', 'Ericsson'),
('Ericsson GF 788e', 'Alphanumeric;Removable NiMH 550 mAh battery;', 10.99, 'https://fdn2.gsmarena.com/vv/bigpic/erg788eb.gif', 'cellphone', 'Ericsson'),
('Ericsson GH 218', 'Alphanumeric;Removable NiMH battery;', 10.99, 'https://fdn2.gsmarena.com/vv/bigpic/ergh218b.gif', 'cellphone', 'Ericsson'),
('Ericsson GH 337', 'Alphanumeric;Removable NiMH 915 mAh battery;', 10.99, 'https://fdn2.gsmarena.com/vv/bigpic/ergh337b.gif', 'cellphone', 'Ericsson'),
('Ericsson GH 388', 'Alphanumeric;Removable NiMH 915 mAh battery;', 10.99, 'https://fdn2.gsmarena.com/vv/bigpic/ergh388b.gif', 'cellphone', 'Ericsson'),
('Ericsson GH 688', 'Alphanumeric;Removable NiMH 1200 mAh battery;', 10.99, 'https://fdn2.gsmarena.com/vv/bigpic/ergh688b.gif', 'cellphone', 'Ericsson'),
('Ericsson GO 118', 'Alphanumeric;Removable NiMH 1200 mAh battery;', 10.99, 'https://fdn2.gsmarena.com/vv/bigpic/ergo118b.gif', 'cellphone', 'Ericsson'),
('Ericsson GS 18', 'Alphanumeric;Removable NiMH 1200 mAh battery;', 10.99, 'https://fdn2.gsmarena.com/vv/bigpic/ergs18b.gif', 'cellphone', 'Ericsson'),
('Ericsson GS 337', 'Alphanumeric;Removable NiMH 915 mAh battery;', 10.99, 'https://fdn2.gsmarena.com/vv/bigpic/ergs337b.gif', 'cellphone', 'Ericsson'),
('Ericsson I 888', 'Alphanumeric;Removable NiMH 800 mAh battery;', 10.99, 'https://fdn2.gsmarena.com/vv/bigpic/eri888b.gif', 'cellphone', 'Ericsson'),
('Ericsson PF 768', 'Alphanumeric;Removable NiMH 550 mAh battery;', 10.99, 'https://fdn2.gsmarena.com/vv/bigpic/erpf768b.gif', 'cellphone', 'Ericsson'),
('Ericsson R250s PRO', 'Monochrome graphic;Removable battery;', 10.99, 'https://fdn2.gsmarena.com/vv/bigpic/err250sb.gif', 'cellphone', 'Ericsson'),
('Ericsson R310s', 'Monochrome graphic;Removable NiMH battery;', 10.99, 'https://fdn2.gsmarena.com/vv/bigpic/err310s.gif', 'cellphone', 'Ericsson'),
('Ericsson R320', 'Monochrome graphic;Removable NiMH battery;', 10.99, 'https://fdn2.gsmarena.com/vv/bigpic/err320b.gif', 'cellphone', 'Ericsson'),
('Ericsson R380', 'Monochrome resistive touchscreen;Removable NiMH battery;', 10.99, 'https://fdn2.gsmarena.com/vv/bigpic/err380b.gif', 'cellphone', 'Ericsson'),
('Ericsson R520m', 'Monochrome graphic;Removable NiMH battery;', 10.99, 'https://fdn2.gsmarena.com/vv/bigpic/err520m.gif', 'cellphone', 'Ericsson'),
('Ericsson R600', 'Grayscale graphic;Removable Li-Ion (BST-20);', 10.99, 'https://fdn2.gsmarena.com/vv/bigpic/err600.gif', 'cellphone', 'Ericsson'),
('Ericsson S 868', 'Alphanumeric;Removable NiMH 800 mAh battery;', 10.99, 'https://fdn2.gsmarena.com/vv/bigpic/ers868b.gif', 'cellphone', 'Ericsson'),
('Ericsson SH 888', 'Alphanumeric;Removable NiMH 800 mAh battery;', 10.99, 'https://fdn2.gsmarena.com/vv/bigpic/ersh888b.gif', 'cellphone', 'Ericsson'),
('Ericsson T10s', 'Monochrome graphic;Removable NiMH 750 mAh battery;', 10.99, 'https://fdn2.gsmarena.com/vv/bigpic/ert10sb.gif', 'cellphone', 'Ericsson'),
('Ericsson T18s', 'Monochrome graphic;Removable NiMH 750 mAh battery;', 10.99, 'https://fdn2.gsmarena.com/vv/bigpic/ert18sb.gif', 'cellphone', 'Ericsson'),
('Ericsson T20e', 'Monochrome graphic;Removable NiMH battery;', 10.99, 'https://fdn2.gsmarena.com/vv/bigpic/ert20e.gif', 'cellphone', 'Ericsson'),
('Ericsson T20s', 'Monochrome graphic;Removable NiMH battery;', 10.99, 'https://fdn2.gsmarena.com/vv/bigpic/ert20s.gif', 'cellphone', 'Ericsson'),
('Ericsson T28 World', 'Monochrome graphic;Removable Li-Po 500 mAh battery;', 10.99, 'https://fdn2.gsmarena.com/vv/bigpic/ert28wb.gif', 'cellphone', 'Ericsson'),
('Ericsson T28s', 'Monochrome graphic;Removable Li-Po 500 mAh battery;', 10.99, 'https://fdn2.gsmarena.com/vv/bigpic/ert28sb.gif', 'cellphone', 'Ericsson'),
('Ericsson T29s', 'Grayscale graphic;Removable NiMH battery;', 10.99, 'https://fdn2.gsmarena.com/vv/bigpic/ert29.gif', 'cellphone', 'Ericsson'),
('Ericsson T36', 'Grayscale graphic;Removable Li-Po battery;', 10.99, 'https://fdn2.gsmarena.com/vv/bigpic/ert36b.gif', 'cellphone', 'Ericsson'),
('Ericsson T39', 'Grayscale graphic;Removable NiMH battery;', 10.99, 'https://fdn2.gsmarena.com/vv/bigpic/ert39.gif', 'cellphone', 'Ericsson'),
('Ericsson T65', 'Grayscale graphic;Removable Li-Ion battery;', 10.99, 'https://fdn2.gsmarena.com/vv/bigpic/ert65.gif', 'cellphone', 'Ericsson'),
('Ericsson T66', 'Monochrome graphic;Removable NiMH battery;', 10.99, 'https://fdn2.gsmarena.com/vv/bigpic/ert66.gif', 'cellphone', 'Ericsson'),
('Ericsson T68', 'CSTN, 256 colors;Removable Li-Ion battery (BST-14);', 10.99, 'https://fdn2.gsmarena.com/vv/bigpic/ert68.gif', 'cellphone', 'Ericsson'),
('Samsung Galaxy Note 10.1 (2014)', 'Super clear LCD;Li-Po 8220 mAh, non-removable;', 370.98, 'https://fdn2.gsmarena.com/vv/bigpic/samsung-galaxy-note-101-2014-new.jpg', 'tablet', 'Samsung'),
('Samsung Galaxy Note 10.1 N8000', 'PLS;Non-removable Li-Ion 7000 mAh battery;', 320.98, 'https://fdn2.gsmarena.com/vv/bigpic/samsung-galaxy-note-101-n8000.jpg', 'tablet', 'Samsung'),
('Samsung Galaxy Note 10.1 N8010', 'PLS IPS;Non-removable Li-Ion 7000 mAh battery;', 350.98, 'https://fdn2.gsmarena.com/vv/bigpic/samsung-galaxy-note-101-n8000.jpg', 'tablet', 'Samsung'),
('Samsung Galaxy Note 3', 'Super AMOLED;Li-Ion 3200 mAh, removable;', 420.98, 'https://fdn2.gsmarena.com/vv/bigpic/samsung-galaxy-note-3.jpg', 'tablet', 'Samsung'),
('Samsung Galaxy Note 3 Neo', 'Super AMOLED;Li-Ion 3100 mAh, removable;', 320.98, 'https://fdn2.gsmarena.com/vv/bigpic/-samsung-galaxy-note-3-neo-new1.jpg', 'tablet', 'Samsung'),
('Samsung Galaxy Note 3 Neo Duos', 'Super AMOLED;Li-Ion 3100 mAh, removable;', 410.98, 'https://fdn2.gsmarena.com/vv/bigpic/-samsung-galaxy-note-3-neo-new1.jpg', 'tablet', 'Samsung'),
('Samsung Galaxy Note 4', 'Super AMOLED;Li-Ion 3220 mAh, removable;', 350.98, 'https://fdn2.gsmarena.com/vv/bigpic/samsung-galaxy-note-4-new.jpg', 'tablet', 'Samsung'),
('Samsung Galaxy Note 4 (USA)', 'Super AMOLED;Li-Ion 3220 mAh, removable;', 250.98, 'https://fdn2.gsmarena.com/vv/bigpic/samsung-galaxy-note-4-cdma.jpg', 'tablet', 'Samsung'),
('Samsung Galaxy Note 4 Duos', 'Super AMOLED;Li-Ion 3000 mAh, removable;', 300.98, 'https://fdn2.gsmarena.com/vv/bigpic/samsung-galaxy-note-4-duos-sm-n9100.jpg', 'tablet', 'Samsung'),
('Samsung Galaxy Note 8.0', 'TFT;Li-Ion 4600 mAh, non-removable;', 320.98, 'https://fdn2.gsmarena.com/vv/bigpic/samsung-galaxy-note-80-n5100.jpg', 'tablet', 'Samsung'),
('Samsung Galaxy Note 8.0 Wi-Fi', 'TFT;Li-Ion 4600 mAh, non-removable;', 280.98, 'https://fdn2.gsmarena.com/vv/bigpic/samsung-galaxy-note-80-n5110.jpg', 'tablet', 'Samsung'),
('Samsung Galaxy Note Edge', 'Super AMOLED;Li-Ion 3000 mAh, removable;', 300.98, 'https://fdn2.gsmarena.com/vv/bigpic/samsung-galaxy-note-edge1.jpg', 'tablet', 'Samsung'),
('Samsung Galaxy Note FE', 'Super AMOLED, HDR10;Li-Ion 3200 mAh, non-removable;', 500.98, 'https://fdn2.gsmarena.com/vv/bigpic/samsung-galaxy-note-fe1.jpg', 'tablet', 'Samsung'),
('Samsung Galaxy Note I717', 'Super AMOLED;Removable Li-Ion 2500 mAh battery;', 350.98, 'https://fdn2.gsmarena.com/vv/bigpic/samsung-galaxy-note-lte-new.jpg', 'tablet', 'Samsung'),
('Samsung Galaxy Note II CDMA', 'Super AMOLED;Li-Ion 3100 mAh, removable;', 350.98, 'https://fdn2.gsmarena.com/vv/bigpic/samsung-galaxy-note-ii-cdma.jpg', 'tablet', 'Samsung'),
('Samsung Galaxy Note II N7100', 'Super AMOLED;Removable Li-Ion 3100 mAh battery;', 300.98, 'https://fdn2.gsmarena.com/vv/bigpic/samsung-galaxy-note-ii-n7100-new.jpg', 'tablet', 'Samsung'),
('Samsung Galaxy Note LTE 10.1 N8020', 'IPS LCD;Li-Ion 7000 mAh, non-removable;', 390.98, 'https://fdn2.gsmarena.com/vv/bigpic/samsung-galaxy-note-101-lte-n8020.jpg', 'tablet', 'Samsung'),
('Samsung Galaxy Note N7000', 'Super AMOLED;Removable Li-Ion 2500 mAh battery;', 270.98, 'https://fdn2.gsmarena.com/vv/bigpic/samsung-galaxy-note.jpg', 'tablet', 'Samsung'),
('Samsung Galaxy Note Pro 12.2', 'Super clear LCD;Li-Ion 9500 mAh, non-removable;', 540.98, 'https://fdn2.gsmarena.com/vv/bigpic/samsung-note-pro-122.jpg', 'tablet', 'Samsung'),
('Samsung Galaxy Note Pro 12.2 3G', 'Super clear LCD;Li-Ion 9500 mAh, non-removable;', 640.98, 'https://fdn2.gsmarena.com/vv/bigpic/samsung-note-pro-122.jpg', 'tablet', 'Samsung'),
('Samsung Galaxy Note Pro 12.2 LTE', 'Super clear LCD;Li-Ion 9500 mAh, non-removable;', 670.98, 'https://fdn2.gsmarena.com/vv/bigpic/samsung-note-pro-122.jpg', 'tablet', 'Samsung'),
('Samsung Galaxy Note T879', 'Super AMOLED;Removable Li-Ion 2500 mAh battery;', 350.98, 'https://fdn2.gsmarena.com/vv/bigpic/samsung-galaxy-note-t-mobile-sgh-t879.jpg', 'tablet', 'Samsung'),
('Samsung Galaxy Note10', 'Dynamic AMOLED, HDR10+;Li-Ion 3500 mAh, non-removable;', 0.98, 'https://fdn2.gsmarena.com/vv/bigpic/samsung-galaxy-note10-.jpg', 'tablet', 'Samsung'),
('Samsung Galaxy Note10 5G', 'Dynamic AMOLED, HDR10+;Li-Ion 3500 mAh, non-removable;', 110.98, 'https://fdn2.gsmarena.com/vv/bigpic/samsung-galaxy-note10-.jpg', 'tablet', 'Samsung'),
('Samsung Galaxy Note10 Lite', 'Super AMOLED, HDR;Li-Po 4500 mAh, non-removable;', 449.00, 'https://fdn2.gsmarena.com/vv/bigpic/sasmung-galaxy-note10-lite-r.jpg', 'tablet', 'Samsung'),
('Samsung Galaxy Note10+', 'Dynamic AMOLED, HDR10+;Li-Ion 4300 mAh, non-removable;', 99.98, 'https://fdn2.gsmarena.com/vv/bigpic/samsung-galaxy-note10-plus-.jpg', 'tablet', 'Samsung'),
('Samsung Galaxy Note10+ 5G', 'Dynamic AMOLED, HDR10+;Li-Ion 4300 mAh, non-removable;',  539.9, 'https://fdn2.gsmarena.com/vv/bigpic/samsung-galaxy-note10-plus-.jpg', 'tablet', 'Samsung'),
('Samsung Galaxy Note20', 'Super AMOLED Plus, HDR10+;Li-Ion 4300 mAh, non-removable;', 99.98, 'https://fdn2.gsmarena.com/vv/bigpic/samsung-galaxy-note20-5g-r.jpg', 'tablet', 'Samsung'),
('Samsung Galaxy Note20 5G', 'Super AMOLED Plus, HDR10+;Li-Ion 4300 mAh, non-removable;', 93.98, 'https://fdn2.gsmarena.com/vv/bigpic/samsung-galaxy-note20-5g-r.jpg', 'tablet', 'Samsung'),
('Samsung Galaxy Note20 Ultra', 'Dynamic AMOLED 2X, 120Hz, HDR10+;Li-Ion 4500 mAh, non-removable;',  979.5, 'https://fdn2.gsmarena.com/vv/bigpic/samsung-galaxy-note20-ultra-.jpg', 'tablet', 'Samsung'),
('Samsung Galaxy Note20 Ultra 5G', 'Dynamic AMOLED 2X, 120Hz, HDR10+;Li-Ion 4500 mAh, non-removable;', 1049.99, 'https://fdn2.gsmarena.com/vv/bigpic/samsung-galaxy-note20-ultra-.jpg', 'tablet', 'Samsung'),
('Samsung Galaxy Note5', 'Super AMOLED;Li-Po 3000 mAh, non-removable;', 450.98, 'https://fdn2.gsmarena.com/vv/bigpic/samsung-galaxy-note5.jpg', 'tablet', 'Samsung'),
('Samsung Galaxy Note5 (USA)', 'Super AMOLED;Li-Ion 3000 mAh, non-removable;', 450.98, 'https://fdn2.gsmarena.com/vv/bigpic/samsung-galaxy-note5.jpg', 'tablet', 'Samsung'),
('Samsung Galaxy Note5 Duos', 'Super AMOLED;Li-Po 3000 mAh, non-removable;', 450.98, 'https://fdn2.gsmarena.com/vv/bigpic/samsung-galaxy-note5.jpg', 'tablet', 'Samsung'),
('Samsung Galaxy Note7', 'Super AMOLED, HDR10;Li-Ion 3500 mAh, non-removable;', 850.98, 'https://fdn2.gsmarena.com/vv/bigpic/samsung-galaxy-note7.jpg', 'tablet', 'Samsung'),
('Samsung Galaxy Note7 (USA)', 'Super AMOLED, HDR10;Li-Ion 3500 mAh, non-removable;', 760.98, 'https://fdn2.gsmarena.com/vv/bigpic/samsung-galaxy-note7-usa.jpg', 'tablet', 'Samsung'),
('Samsung Galaxy Note8', 'Super AMOLED, HDR10;Li-Ion 3300 mAh, non-removable;', 500.98, 'https://fdn2.gsmarena.com/vv/bigpic/samsung-galaxy-note-8-sm-n950.jpg', 'tablet', 'Samsung'),
('Samsung Galaxy Note9', 'Super AMOLED, HDR10;Li-Ion 4000 mAh, non-removable;', 262.27, 'https://fdn2.gsmarena.com/vv/bigpic/samsung-galaxy-note9-r1.jpg', 'tablet', 'Samsung')
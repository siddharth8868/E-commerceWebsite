DROP DATABASE IF EXISTS NBAD;
CREATE DATABASE NBAD;
USE NBAD;
CREATE TABLE PRODUCT(
productCode VARCHAR(10),
productName VARCHAR(50),
catalogCategory VARCHAR(50),
price decimal,
description VARCHAR(2000), 
imageUrl VARCHAR(100),
PRIMARY KEY (productCode)
);

CREATE TABLE USER (
userID int NOT NULL AUTO_INCREMENT,
firstName VARCHAR(50),
lastName VARCHAR(50),
emailAddr VARCHAR(100),
address1Field VARCHAR(80),
address2Field VARCHAR(80),
city VARCHAR(50),
state VARCHAR(50),
zipPostalCode VARCHAR(50),
country VARCHAR(50),
pass VARCHAR(150),
role VARCHAR(15),
salt VARCHAR(100),
PRIMARY KEY (userID),
UNIQUE (emailAddr)
);

CREATE TABLE ORDERITEM(
orderNumber int(11) NOT NULL,
productCode VARCHAR(10) NOT NULL,
quantity int(10) NOT NULL,
PRIMARY KEY(orderNumber,productCode)
);

CREATE TABLE ORDERS(
orderNumber int(11) NOT NULL AUTO_INCREMENT,
dates date NOT NULL,
userID int(11) NOT NULL,
taxRate float(11,2) NOT NULL,
totalCost float(50,2) NOT NULL,
paid boolean NOT NULL,
PRIMARY KEY(orderNumber)
);

INSERT INTO USER VALUES(null,"rohini","kolan","rohinireddy.kolan@gmail.com","9535 university terrace drive,","Apt L","charlotte","NC","28262","USA","056f5044958fe4b40f49af2a82a51673c81d682ed6a8c629b7e5065931164126","customer","SPrqbZ7lCvZ+lk3oq4+pgQrJkNlzdB3tvtzTA23tkaM=");

INSERT INTO PRODUCT values("ApIphone6","iphone6","Apple",650,"<table class=\"tablespef\">
	<tr>
	<td>Technology</td>
	<td>GSM/CDMA/LTE</td>
	</tr>
	
	<tr>
	<td>OS</td>
	<td>IOS 8</td>
	</tr>
	
	<tr>
	<td>Size</td>
	<td>4.7 inches</td>
	</tr>
	
	<tr>
	<td>Camera</td>
	<td>8 MP</td>
	</tr>
	
	<tr>
	<td>Memory</td>
	<td>Internal 1GB RAM</td>
	</tr>
	
	<tr>
	<td>Battery</td>
	<td>Non-removable Li-Po 1810 mAh battery (6.9 Wh)</td>
	</tr>
	
	<tr>
	<td>Sensors</td>
	<td>Accelerometer, gyro, proximity, compass, barometer</td>
	</tr>
	
	<tr>
	<td>Wi-Fi</td>
	<td>Wi-Fi 802.11 a/b/g/n/ac, dual-band, hotspot</td>
	</tr>
    
	</table>","images/iphone/iphone6.jpg");
    
INSERT INTO PRODUCT values("ApIphone5s","iphone5s","Apple",550,"<table class=\"tablespef\">
	<tr>
	<td>Technology</td>
	<td>GSM/CDMA/LTE</td>
	</tr>
	
	<tr>
	<td>OS</td>
	<td>IOS 7</td>
	</tr>
	
	<tr>
	<td>Size</td>
	<td>4.0 inches</td>
	</tr>
	
	<tr>
	<td>Camera</td>
	<td>8 MP</td>
	</tr>
	
	<tr>
	<td>Memory</td>
	<td>Internal 1GB RAM</td>
	</tr>
	
	<tr>
	<td>Battery</td>
	<td>Non-removable Li-Po 1560 mAh battery (6.9 Wh)</td>
	</tr>
	
	
	<tr>
	<td>Sensors</td>
	<td>Accelerometer, gyro, proximity, compass, barometer</td>
	</tr>
	
	<tr>
	<td>Wi-Fi</td>
	<td>Wi-Fi 802.11 a/b/g/n/ac, dual-band, hotspot</td>
	</tr>
	
	
	</table>","images/iphone/iphone5s.jpg");
INSERT INTO PRODUCT values("ApIphone5c","iphone5c","Apple",450,"<table class=\"tablespef\">
	<tr>
	<td>Technology</td>
	<td>GSM / CDMA / HSPA / LTE</td>
	</tr>
	
	<tr>
	<td>OS</td>
	<td>iOS 7, upgradable to iOS 7.1.2, upgradable to iOS 8.1.3</td>
	</tr>
	
	<tr>
	<td>Size</td>
	<td>4.0 inches</td>
	</tr>
	
	<tr>
	<td>Camera</td>
	<td>8 MP, 3264 x 2448 pixels, autofocus, LED flash</td>
	</tr>
	
	<tr>
	<td>Memory</td>
	<td>8/16/32 GB, 1 GB RAM</td>
	</tr>
	
	<tr>
	<td>Battery</td>
	<td>Non-removable Li-Po 1510 mAh battery (5.73 Wh)</td>
	</tr>
	
	
	<tr>
	<td>Sensors</td>
	<td>Accelerometer, gyro, proximity, compass, barometer</td>
	</tr>
	
	<tr>
	<td>Wi-Fi</td>
	<td>Wi-Fi 802.11 a/b/g/n/ac, dual-band, hotspot</td>
	</tr>
	
	
	</table>","images/iphone/iphone5c.png");
INSERT INTO PRODUCT values("Sagalaxys5","galaxys5","Samsung",600,"<table class=\"tablespef\">
	<tr>
	<td>Technology</td>
	<td>GSM/CDMA/LTE</td>
	</tr>
	
	<tr>
	<td>OS</td>
	<td>Android OS, v4.4.2 (KitKat), upgradable to v5.0 (Lollipop)</td>
	</tr>
	
	<tr>
	<td>Size</td>
	<td>5.1 inches</td>
	</tr>
	
	<tr>
	<td>Camera</td>
	<td>16 MP</td>
	</tr>
	
	<tr>
	<td>Memory</td>
	<td>16/32 GB, 2 GB RAM</td>
	</tr>
	
	<tr>
	<td>Battery</td>
	<td>Li-Ion 2800 mAh battery</td>
	</tr>
	
	
	<tr>
	<td>Sensors</td>
	<td>Accelerometer, gyro, proximity, compass, barometer</td>
	</tr>
	
	<tr>
	<td>Wi-Fi</td>
	<td>Wi-Fi 802.11 a/b/g/n/ac, dual-band, hotspot</td>
	</tr>
	
	
	</table>","images/samsung/galaxy%20S5.jpg");
INSERT INTO PRODUCT values("Sagalaxys4","galaxys4","Samsung",550,"<table class=\"tablespef\">
	<tr>
	<td>Technology</td>
	<td>GSM/HSPA</td>
	</tr>
	
	<tr>
	<td>OS</td>
	<td>Android OS, v4.2.2 (Jelly Bean), upgradable to v5.0 (Lollipop)</td>
	</tr>
	
	<tr>
	<td>Size</td>
	<td>5.0 inches</td>
	</tr>
	
	<tr>
	<td>Camera</td>
	<td>13 MP, 4128 x 3096 pixels, autofocus, LED flash</td>
	</tr>
	
	<tr>
	<td>Memory</td>
	<td>16/32/64 GB, 2 GB RAM</td>
	</tr>
	
	<tr>
	<td>Battery</td>
	<td>Li-Ion 2600 mAh battery</td>
	</tr>
	
	
	<tr>
	<td>Sensors</td>
	<td>Accelerometer, gyro, proximity, compass, barometer</td>
	</tr>
	
	<tr>
	<td>Wi-Fi</td>
	<td>Wi-Fi 802.11 a/b/g/n/ac, dual-band, hotspot</td>
	</tr>
	
	
	</table>","images/samsung/galaxy%20S4%20mini.jpg");
INSERT INTO PRODUCT values("Satablet3","tablet3","Samsung",450,"<table class=\"tablespef\">
	<tr>
	<td>Technology</td>
	<td>GSM/HSPA/LTE</td>
	</tr>
	
	<tr>
	<td>OS</td>
	<td>Android OS, v4.1.2 (Jelly Bean)</td>
	</tr>
	
	<tr>
	<td>Size</td>
	<td>7.0 inches</td>
	</tr>
	
	<tr>
	<td>Camera</td>
	<td>3.15 MP, 2048 x 1536 pixels, autofocus</td>
	</tr>
	
	<tr>
	<td>Memory</td>
	<td>8/16 GB, 1 GB RAM</td>
	</tr>
	
	<tr>
	<td>Battery</td>
	<td>Non-removable Li-Ion 4000 mAh battery</td>
	</tr>
	
	
	<tr>
	<td>Sensors</td>
	<td>Accelerometer, gyro, proximity, compass, barometer</td>
	</tr>
	
	<tr>
	<td>Wi-Fi</td>
	<td>Wi-Fi 802.11 a/b/g/n/ac, dual-band, hotspot</td>
	</tr>
	
	
	</table>","images/samsung/tablet%203.jpg");
    
    
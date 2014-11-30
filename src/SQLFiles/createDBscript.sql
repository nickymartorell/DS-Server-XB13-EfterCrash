CREATE DATABASE IF NOT EXISTS cbscalendar;
use cbscalendar;
SET SESSION FOREIGN_KEY_CHECKS=0;

CREATE TABLE IF NOT EXISTS events
(
	id int NOT NULL AUTO_INCREMENT,
	type VARCHAR(100) NOT NULL,
	location VARCHAR (100),
	start datetime NOT NULL,
	end datetime NOT NULL,
	name varchar(100) NOT NULL,
	PRIMARY KEY (id)
);

CREATE TABLE users (
  userid int(11) NOT NULL AUTO_INCREMENT,
  email varchar(40) NOT NULL,
  active tinyint(4) DEFAULT 1,
  password varchar(200) NOT NULL,
  admin BOOLEAN NOT NULL,
  PRIMARY KEY (userid)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

INSERT INTO users (userid, email, active, password, admin)
VALUES
  (1,'admin@admin.dk',1,'admin', 1),
  (2,'mamu13ag@student.cbs.dk',1,'hej123',0);

--forecast
CREATE TABLE forecast (
  date datetime NOT NULL,
  des varchar(200) NOT NULL,
  cels varchar(20) NOT NULL

) 

--Forecast
CREATE TABLE IF NOT EXISTS quote
(
	quote varchar(300) NOT NULL,
	autor varchar (100) NOT NULL,
	topic varchar(100) NOT NULL
);

/* Create Tables */
CREATE TABLE IF NOT EXISTS Calender
(
	CalenderID int NOT NULL AUTO_INCREMENT,
	Name varchar(255) NOT NULL,
	Active tinyint,
	CreatedBy varchar(255) NOT NULL,
	-- 1 = public
	-- 2 = private
	PrivatePublic tinyint NOT NULL COMMENT '1 = public
	2 = private',
	PRIMARY KEY (CalenderID)
);

CREATE TABLE IF NOT EXISTS locationdata
(
	locationdataid int NOT NULL AUTO_INCREMENT,
	longitude int NOT NULL,
	latitude int UNIQUE,
	PRIMARY KEY (locationdataid)
);
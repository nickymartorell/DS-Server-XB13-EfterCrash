CREATE DATABASE IF NOT EXISTS cbscalendar;
use cbscalendar;
SET SESSION FOREIGN_KEY_CHECKS=0;

DROP TABLE events;

CREATE TABLE IF NOT EXISTS events
(
	id int NOT NULL AUTO_INCREMENT,
	type VARCHAR(100),
	activityid varchar(255),
	location VARCHAR (100),
	createdby varchar(255),
	start datetime,
	end datetime,
	description VARCHAR (200),
	calendarid VARCHAR (200),
	PRIMARY KEY (id)
);
set global max_connections = 2000000000;



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
--QOTD
CREATE TABLE IF NOT EXISTS quote
(
	quote varchar(300) NOT NULL,
	autor varchar (100) NOT NULL,
	topic varchar(100) NOT NULL
);

DROP TABLE notes;
--created datetime NOT NULL,
CREATE TABLE IF NOT EXISTS notes
(
	noteid int NOT NULL AUTO_INCREMENT,
	eventid int NOT NULL,
  	note varchar(200) NOT NULL,
	createdby VARCHAR(100),
	isActive boolean,
	PRIMARY KEY (noteid)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
INSERT INTO notes (noteid, eventid, note, createdby, isActive)
VALUES
  (10,11,'Hej med dig','Stefan', 1),
;


CREATE TABLE IF NOT EXISTS roles
(
	roleid int NOT NULL AUTO_INCREMENT,
	userid int NOT NULL,
	type varchar(200) NOT NULL,
	PRIMARY KEY (roleid)
);

/* Create calendar */
CREATE TABLE IF NOT EXISTS Calender
(
	CalenderID int NOT NULL AUTO_INCREMENT,
	Name varchar(255) NOT NULL,
	Active tinyint,
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
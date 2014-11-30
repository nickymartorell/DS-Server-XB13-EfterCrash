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

CREATE TABLE IF NOT EXISTS users
(
	userid int NOT NULL AUTO_INCREMENT,
	email varchar(40) NOT NULL,
	active boolean,
	created datetime NOT NULL DEFAULT NOW(),
	password varchar(200) NOT NULL,
	PRIMARY KEY (userid)
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


DROP TABLE qoute


//forecast
CREATE TABLE forecast (
  date datetime NOT NULL,
  des varchar(200) NOT NULL,
  cels varchar(20) NOT NULL

) 

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



CREATE TABLE IF NOT EXISTS quote
(
	quote varchar(300) NOT NULL,
	autor varchar (100) NOT NULL,
	topic varchar(100) NOT NULL
);





CREATE TABLE IF NOT EXISTS locationdata
(
	locationdataid int NOT NULL AUTO_INCREMENT,
	longitude int NOT NULL,
	latitude int UNIQUE,
	PRIMARY KEY (locationdataid)
);


CREATE TABLE IF NOT EXISTS notes
(
	noteId int NOT NULL AUTO_INCREMENT,
	eventId int NOT NULL,
	createdBy varchar(255) NOT NULL,
	text text,
	dateTime datetime NOT NULL,
	active bit,
	PRIMARY KEY (noteid)
);



CREATE TABLE IF NOT EXISTS roles
(
	roleid int NOT NULL AUTO_INCREMENT,
	userid int NOT NULL,
	type varchar(200) NOT NULL,
	PRIMARY KEY (roleid)
);


CREATE TABLE IF NOT EXISTS userevents
(
	userid int NOT NULL,
	CalenderID int NOT NULL
);


CREATE TABLE IF NOT EXISTS users
(
	userid int NOT NULL AUTO_INCREMENT,
	email varchar(40) NOT NULL,
	active boolean,
	created datetime NOT NULL DEFAULT NOW(),
	password varchar(200) NOT NULL,
	PRIMARY KEY (userid)
);

/* Create Dummy Account */

INSERT INTO `cbscalendar`.`users`
(`email`,
`active`,
`password`)
VALUES
("admin@admin.dk",
true,
"d6YSr320JnLXlp8YYxUcNQ==");



/* Create Foreign Keys */

ALTER TABLE events
	ADD FOREIGN KEY (CalenderID)
	REFERENCES Calender (CalenderID)
	ON UPDATE RESTRICT
;


ALTER TABLE userevents
	ADD FOREIGN KEY (CalenderID)
	REFERENCES Calender (CalenderID)
	ON UPDATE RESTRICT
;


ALTER TABLE notes
	ADD FOREIGN KEY (eventid)
	REFERENCES events (eventid)
	ON UPDATE RESTRICT
;


ALTER TABLE events
	ADD FOREIGN KEY (location)
	REFERENCES locationdata (locationdataid)
	ON UPDATE RESTRICT
;


ALTER TABLE events
	ADD FOREIGN KEY (createdby)
	REFERENCES users (userid)
	ON UPDATE RESTRICT
;


ALTER TABLE roles
	ADD FOREIGN KEY (userid)
	REFERENCES users (userid)
	ON UPDATE RESTRICT
;


ALTER TABLE userevents
	ADD FOREIGN KEY (userid)
	REFERENCES users (userid)
	ON UPDATE RESTRICT
;


ALTER TABLE notes
	ADD FOREIGN KEY (createdby)
	REFERENCES users (userid)
	ON UPDATE RESTRICT
;


CREATE DATABASE IF NOT EXISTS cbscalendar;
use cbscalendar;
SET SESSION FOREIGN_KEY_CHECKS=0;

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


CREATE TABLE IF NOT EXISTS dailyupdate
(
	date datetime NOT NULL UNIQUE,
	apparentTemperature double,
	summary text,
	windspeed double,
	qotd varchar(300) NOT NULL,
	PRIMARY KEY (date)
);


CREATE TABLE IF NOT EXISTS events
(
	eventid int NOT NULL AUTO_INCREMENT,
	type int NOT NULL,
	location int,
	createdby int NOT NULL,
	start datetime NOT NULL,
	end datetime NOT NULL,
	name varchar(0) NOT NULL,
	text text NOT NULL,
	-- Decides wether the event is an import-event or user created
	-- 
	customevent boolean COMMENT 'Decides wether the event is an import-event or user created
',
	CalenderID int NOT NULL,
	PRIMARY KEY (eventid)
);


CREATE TABLE IF NOT EXISTS locationdata
(
	locationdataid int NOT NULL AUTO_INCREMENT,
	longitude int NOT NULL,
	latitude int UNIQUE,
	PRIMARY KEY (locationdataid)
);


CREATE TABLE IF NOT EXISTS notes
(
	noteid int NOT NULL AUTO_INCREMENT,
	eventid int NOT NULL,
	createdby int NOT NULL,
	text text,
	created datetime NOT NULL,
	PRIMARY KEY (noteid)
);


CREATE TABLE IF NOT EXISTS roles
(
	roleid int NOT NULL AUTO_INCREMENT,
	userid int NOT NULL,
	type varchar(200) NOT NULL,
	PRIMARY KEY (roleid)
);


CREATE TABLE IF NOT EXISTS userevents
(
	userid int NOT NULL,
	CalenderID int NOT NULL
);


CREATE TABLE IF NOT EXISTS users
(
	userid int NOT NULL AUTO_INCREMENT,
	email varchar(40) NOT NULL,
	active boolean,
	created datetime,
	password varchar(200) NOT NULL,
	PRIMARY KEY (userid)
);



/* Create Foreign Keys */

ALTER TABLE events
	ADD FOREIGN KEY (CalenderID)
	REFERENCES Calender (CalenderID)
	ON UPDATE RESTRICT
;


ALTER TABLE userevents
	ADD FOREIGN KEY (CalenderID)
	REFERENCES Calender (CalenderID)
	ON UPDATE RESTRICT
;


ALTER TABLE notes
	ADD FOREIGN KEY (eventid)
	REFERENCES events (eventid)
	ON UPDATE RESTRICT
;


ALTER TABLE events
	ADD FOREIGN KEY (location)
	REFERENCES locationdata (locationdataid)
	ON UPDATE RESTRICT
;


ALTER TABLE events
	ADD FOREIGN KEY (createdby)
	REFERENCES users (userid)
	ON UPDATE RESTRICT
;


ALTER TABLE roles
	ADD FOREIGN KEY (userid)
	REFERENCES users (userid)
	ON UPDATE RESTRICT
;


ALTER TABLE userevents
	ADD FOREIGN KEY (userid)
	REFERENCES users (userid)
	ON UPDATE RESTRICT
;


ALTER TABLE notes
	ADD FOREIGN KEY (createdby)
	REFERENCES users (userid)
	ON UPDATE RESTRICT
;

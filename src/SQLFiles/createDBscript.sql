CREATE DATABASE IF NOT EXISTS cbscalendar;
use cbscalendar;
SET SESSION FOREIGN_KEY_CHECKS=0;

DROP TABLE events;

CREATE TABLE IF NOT EXISTS events
(
	id int NOT NULL AUTO_INCREMENT,
	type VARCHAR(100) NOT NULL,
	activityid varchar(255) NOT NULL,
	location VARCHAR (100) NOT NULL,
	createdby varchar(255),
	start datetime NOT NULL,
	end datetime NOT NULL,
	description VARCHAR (200) NOT NULL,
	calendarid VARCHAR (200) NOT NULL,
	PRIMARY KEY (id)
)ENGINE=InnoDB DEFAULT CHARSET=latin1;

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
DROP TABLE quote;

CREATE TABLE IF NOT EXISTS quote
(
	quote varchar(300) NOT NULL,
	author varchar (100) NOT NULL,
	topic varchar(100) NOT NULL
);

DROP TABLE notes;
--created datetime NOT NULL,
CREATE TABLE IF NOT EXISTS notes
(
	noteid int NOT NULL AUTO_INCREMENT,
	eventid int NOT NULL,
	text text,	
	createdby VARCHAR(100),
	isActive boolean,
	PRIMARY KEY (noteid)
);

ALTER TABLE notes
	ADD FOREIGN KEY (createdby)
	REFERENCES users (userid)
	ON UPDATE RESTRICT
;


CREATE TABLE IF NOT EXISTS roles
(
	roleid int NOT NULL AUTO_INCREMENT,
	userid int NOT NULL,
	type varchar(200) NOT NULL,
	PRIMARY KEY (roleid)
);

DROP TABLE userevents;

CREATE TABLE IF NOT EXISTS userevents
(
	userid INT(200)
	
)ENGINE=InnoDB DEFAULT CHARSET=latin1;
calendarid INT(200)
ALTER TABLE userevents
	ADD FOREIGN KEY (calendarid)
	REFERENCES calendar (KalId)
	ON UPDATE RESTRICT;

ALTER TABLE userevents
	ADD FOREIGN KEY (userid)
	REFERENCES users (userid)
	ON UPDATE RESTRICT;

INSERT INTO cbscalendar.locationdata (longitude, latitude, location_Name) VALUES ('12.500160', '55.685454', 'Flintholm');
INSERT INTO cbscalendar.locationdata (longitude, latitude, location_Name) VALUES ('12.529438', '55.681603', 'Solbjerg Plads');
INSERT INTO cbscalendar.locationdata (longitude, latitude, location_Name) VALUES ('12.524756', '55.680476', 'Dit-Lab');
INSERT INTO cbscalendar.locationdata (longitude, latitude, location_Name) VALUES ('12.521812', '55.677934', 'Porcelaenshaven');
INSERT INTO cbscalendar.locationdata (longitude, latitude, location_Name) VALUES ('12.524964', '55.681074', 'Kilen');
INSERT INTO cbscalendar.locationdata (longitude, latitude, location_Name) VALUES ('12.515375', '55.683510', 'Dalgas Have');
INSERT INTO cbscalendar.locationdata (longitude, latitude, location_Name) VALUES ('12.433252', '55.235522', 'Falkoner Bio');


DROP TABLE locationdata;

/* Create calendar */
CREATE TABLE IF NOT EXISTS calendar
(
	KalId int NOT NULL AUTO_INCREMENT,
	Name varchar(255) NOT NULL,
	Active tinyint(1) DEFAULT '1',
	CreatedBy varchar(255) NOT NULL,
	PrivatePublic tinyint(1) NOT NULL DEFAULT '1' COMMENT '1 = public
	2 = private',
	PRIMARY KEY (KalId)
);

CREATE TABLE IF NOT EXISTS locationdata
(
	locationdataid int NOT NULL AUTO_INCREMENT,
	longitude decimal (8,6) NOT NULL,
	latitude decimal (8,6) NOT NULL,
	location_name varchar(255),
	PRIMARY KEY (locationdataid)
);

INSERT INTO cbscalendar.calendar (Name, CreatedBy) VALUES ('Makrooekonomi (XA)', 'admin@cbs.dk');
INSERT INTO cbscalendar.calendar (Name, CreatedBy) VALUES ('Makrooekonomi (XB)', 'admin@cbs.dk');
INSERT INTO cbscalendar.calendar (Name, CreatedBy) VALUES ('Makrooekonomi (LA)', 'admin@cbs.dk');
INSERT INTO cbscalendar.calendar (Name, CreatedBy) VALUES ('Distribuerede Systemer (LA)', 'admin@cbs.dk');
INSERT INTO cbscalendar.calendar (Name, CreatedBy) VALUES ('Ledelse af IS - forandring, innovation og viden (XB)', 'admin@cbs.dk');
INSERT INTO cbscalendar.calendar (Name, CreatedBy) VALUES ('Ledelse af IS - forandring, innovation og viden (XA)', 'admin@cbs.dk');
INSERT INTO cbscalendar.calendar (Name, CreatedBy) VALUES ('Ledelse af IS - forandring, innovation og viden (LA)', 'admin@cbs.dk');
INSERT INTO cbscalendar.calendar (Name, CreatedBy) VALUES ('Virksomhedens oekonomiske styring(3)(LA)', 'admin@cbs.dk');
DROP SCHEMA cbscalendar;

CREATE DATABASE IF NOT EXISTS cbscalendar;
use cbscalendar;
SET SESSION FOREIGN_KEY_CHECKS=0;

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
	customevent boolean,
	aktiv boolean,
	PRIMARY KEY (id)

	)
	ENGINE=InnoDB DEFAULT CHARSET=latin1;
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
  (2,'mamu13ag',1,'hej123',0);


CREATE TABLE forecast (
  date datetime NOT NULL,
  des varchar(200) NOT NULL,
  cels varchar(20) NOT NULL); 


CREATE TABLE IF NOT EXISTS quote
(
	quote varchar(300) NOT NULL,
	author varchar (100) NOT NULL,
	topic varchar(100) NOT NULL);

CREATE TABLE IF NOT EXISTS notes
(
	noteid int NOT NULL AUTO_INCREMENT,
	eventid int NOT NULL,
  	note varchar(500) NOT NULL,
	createdby VARCHAR(100),
	isActive boolean,
	PRIMARY KEY (noteid)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

INSERT INTO notes (noteid, eventid, note, createdby, isActive)
VALUES             (1,11,'Hej med dig','Stefan', 1);

CREATE TABLE IF NOT EXISTS userevents
(
	userid INT(200),
	calendarid INT(200)
)ENGINE=InnoDB DEFAULT CHARSET=latin1;

CREATE TABLE IF NOT EXISTS calendar
(
	calendarId int NOT NULL AUTO_INCREMENT,
	Name varchar(255) NOT NULL,
	Active tinyint(1) DEFAULT '1',
	CreatedBy varchar(255) NOT NULL,
	PrivatePublic tinyint(1) NOT NULL DEFAULT '1' COMMENT '1 = public
	2 = private',
	PRIMARY KEY (calendarId)
);

CREATE TABLE IF NOT EXISTS locationdata
(
	locationdataid int NOT NULL AUTO_INCREMENT,
	longitude decimal (8,6) NOT NULL,
	latitude decimal (8,6) NOT NULL,
	location_name varchar(255),
	PRIMARY KEY (locationdataid)
);


INSERT INTO cbscalendar.locationdata (longitude, latitude, location_Name) VALUES ('12.500160', '55.685454', 'Flintholm');
INSERT INTO cbscalendar.locationdata (longitude, latitude, location_Name) VALUES ('12.529438', '55.681603', 'Solbjerg Plads');
INSERT INTO cbscalendar.locationdata (longitude, latitude, location_Name) VALUES ('12.524756', '55.680476', 'Dit-Lab');
INSERT INTO cbscalendar.locationdata (longitude, latitude, location_Name) VALUES ('12.521812', '55.677934', 'Porcelaenshaven');
INSERT INTO cbscalendar.locationdata (longitude, latitude, location_Name) VALUES ('12.524964', '55.681074', 'Kilen');
INSERT INTO cbscalendar.locationdata (longitude, latitude, location_Name) VALUES ('12.515375', '55.683510', 'Dalgas Have');
INSERT INTO cbscalendar.locationdata (longitude, latitude, location_Name) VALUES ('12.433252', '55.235522', 'Falkoner Bio');

INSERT INTO cbscalendar.calendar (Name, CreatedBy) VALUES ('Makrooekonomi (LA)', 'admin@cbs.dk');
INSERT INTO cbscalendar.calendar (Name, CreatedBy) VALUES ('Makrooekonomi (XB)', 'admin@cbs.dk');
INSERT INTO cbscalendar.calendar (Name, CreatedBy) VALUES ('Makrooekonomi (XA)', 'admin@cbs.dk');
INSERT INTO cbscalendar.calendar (Name, CreatedBy) VALUES ('Distribuerede Systemer (LA)', 'admin@cbs.dk');
INSERT INTO cbscalendar.calendar (Name, CreatedBy) VALUES ('Virksomhedens oekonomiske styring(3)(LA)', 'admin@cbs.dk');
INSERT INTO cbscalendar.calendar (Name, CreatedBy) VALUES ('Ledelse af IS - forandring, innovation og viden (LA)', 'admin@cbs.dk');
INSERT INTO cbscalendar.calendar (Name, CreatedBy) VALUES ('Ledelse af IS - forandring, innovation og viden (XB)', 'admin@cbs.dk');
INSERT INTO cbscalendar.calendar (Name, CreatedBy) VALUES ('Ledelse af IS - forandring, innovation og viden (XA)', 'admin@cbs.dk');
	

-- SCHEMA: logbook
CREATE SCHEMA logbook;
--    AUTHORIZATION postgres;

COMMENT ON SCHEMA logbook
    IS 'Scheme for logbook';

-- Table: logbook.users
/* CREATE TABLE logbook.users
(
    id integer NOT NULL,
    name text,
    CONSTRAINT users_pkey PRIMARY KEY (id)
);*/

-- Table: logbook.logmessage
CREATE TABLE logbook.logmessages
(
    id integer NOT NULL,
    severity text,
    subsystem text,
    message text,
    CONSTRAINT logmessage_pkey PRIMARY KEY (id)
);

-- Table: logbook.alert
CREATE TABLE logbook.alerts
(
    id integer NOT NULL,
    severity text,
    subsystem text,
    message text,
    "state" text,
    "createdOn" timestamp without time zone,    
    "ownerId" integer, -- if the alert is created by user
    "createdBy" text, -- if the alert is created by system
    logmessageIds integer[], -- list of logmessage ids from which the alert came from
    CONSTRAINT alert_pkey PRIMARY KEY (id)
);

-- Table: logbook.notes
CREATE TABLE logbook.notes
(
    id integer NOT NULL,
    "ownerId" integer NOT NULL,
   	"alertId" integer NOT NULL,
    message text,
    "createdOn" timestamp without time zone,
    CONSTRAINT note_pkey PRIMARY KEY (id),
    CONSTRAINT alert_fk FOREIGN KEY ("alertId")
        REFERENCES logbook.alerts (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
);

-- Table: logbook.transitions
CREATE TABLE logbook.transitions
(
    id integer NOT NULL,
    "ownerId" integer NOT NULL,
   	"alertId" integer NOT NULL,
    "startState" text,
    "endState" text,
    "createdOn" timestamp without time zone,
    CONSTRAINT transition_pkey PRIMARY KEY (id),
    CONSTRAINT alert_fk FOREIGN KEY ("alertId")
        REFERENCES logbook.alerts (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
);
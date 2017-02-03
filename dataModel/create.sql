-- SCHEMA: logbook
CREATE SCHEMA logbook;
--    AUTHORIZATION postgres;

COMMENT ON SCHEMA logbook
    IS 'Scheme for logbook';

-- Table: logbook.logmessage
CREATE TABLE logbook.logmessages
(
    id integer NOT NULL,
    severity text NOT NULL,
    subsystem text NOT NULL,
    message text NOT NULL,
    CONSTRAINT logmessage_pkey PRIMARY KEY (id)
);

-- Table: logbook.alert
CREATE TABLE logbook.alerts
(
    id integer NOT NULL,
    severity text NOT NULL,
    subsystem text NOT NULL,
    message text NOT NULL,
    "state" text NOT NULL,
    "createdOn" timestamp without time zone NOT NULL,
    "ownerId" integer, -- can be null when first created by system
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
    message text NOT NULL,
    "createdOn" timestamp without time zone NOT NULL,
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
    "startState" text NOT NULL,
    "endState" text NOT NULL,
    "createdOn" timestamp without time zone NOT NULL,
    CONSTRAINT transition_pkey PRIMARY KEY (id),
    CONSTRAINT alert_fk FOREIGN KEY ("alertId")
        REFERENCES logbook.alerts (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
);

-- Table: logbook.alertOwnerHistory
CREATE TABLE logbook.alertOwnerHistory
(
    id integer NOT NULL,
    "ownerId" integer NOT NULL,
    "newOwnerId" integer NOT NULL,
    "alertId" integer NOT NULL,
    "createdOn" timestamp without time zone NOT NULL,
    CONSTRAINT alertowners_pkey PRIMARY KEY (id),
    CONSTRAINT alert_fk FOREIGN KEY ("alertId")
        REFERENCES logbook.alerts (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
);
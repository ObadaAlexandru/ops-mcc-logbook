-- SCHEMA: logbook
CREATE SCHEMA logbook;
--    AUTHORIZATION postgres;

COMMENT ON SCHEMA logbook
    IS 'Scheme for logbook';

-- Table: logbook.logmessage
CREATE TABLE logbook.logmessages
(
    "logId" integer NOT NULL,
    severity text NOT NULL,
    subsystem text NOT NULL,
    message text NOT NULL,
    "createdOn" timestamp without time zone NOT NULL,
    "downloadedOn" timestamp without time zone NOT NULL,
    CONSTRAINT logmessage_pkey PRIMARY KEY ("logId")
);

-- Table: logbook.alert
CREATE TABLE logbook.alerts
(
    "alertId" integer NOT NULL,
    severity text NOT NULL,
    subsystem text NOT NULL,
    message text NOT NULL,
    "state" text NOT NULL,
    "createdOn" timestamp without time zone NOT NULL,
    "ownerId" integer, -- can be null when first created by system
    "createdBy" text, -- if the alert is created by system
    logmessageIds integer[], -- list of logmessage ids from which the alert came from
    CONSTRAINT alert_pkey PRIMARY KEY ("alertId")
);

-- Table: logbook.notes
CREATE TABLE logbook.notes
(
    "noteId" integer NOT NULL,
    "ownerId" integer NOT NULL,
    "alertId" integer NOT NULL,
    message text NOT NULL,
    "createdOn" timestamp without time zone NOT NULL,
    CONSTRAINT note_pkey PRIMARY KEY ("noteId"),
    CONSTRAINT alert_fk FOREIGN KEY ("alertId")
        REFERENCES logbook.alerts ("alertId") MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
);

-- Table: logbook.transitions
CREATE TABLE logbook.transitions
(
    "transitionId" integer NOT NULL,
    "ownerId" integer NOT NULL,
    "alertId" integer NOT NULL,
    "startState" text NOT NULL,
    "endState" text NOT NULL,
    "createdOn" timestamp without time zone NOT NULL,
    CONSTRAINT transition_pkey PRIMARY KEY ("transitionId"),
    CONSTRAINT alert_fk FOREIGN KEY ("alertId")
        REFERENCES logbook.alerts ("alertId") MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
);

-- Table: logbook.alertOwnerHistory
CREATE TABLE logbook.alertOwnerHistory
(
    "alertHistoryId" integer NOT NULL,
    "oldOwnerId" integer NOT NULL,
    "newOwnerId" integer NOT NULL,
    "alertId" integer NOT NULL,
    "createdOn" timestamp without time zone NOT NULL,
    CONSTRAINT alertowners_pkey PRIMARY KEY ("alertHistoryId"),
    CONSTRAINT alert_fk FOREIGN KEY ("alertId")
        REFERENCES logbook.alerts ("alertId") MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
);
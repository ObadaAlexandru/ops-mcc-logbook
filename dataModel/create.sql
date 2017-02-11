-- SCHEMA: logbook
CREATE SCHEMA logbook;
--    AUTHORIZATION postgres;

COMMENT ON SCHEMA logbook
    IS 'Scheme for logbook';

-- Table: logbook.logmessages
CREATE TABLE logbook.logmessages
(
    "logId" bigserial NOT NULL,
    severity text NOT NULL,
    subsystem text NOT NULL,
    message text NOT NULL,
    "createdOn" timestamp without time zone NOT NULL,
    "downloadedOn" timestamp without time zone NOT NULL,
    CONSTRAINT log_pkey PRIMARY KEY ("logId")
);

-- Table: logbook.alert
CREATE TABLE logbook.alerts
(
    "alertId" bigserial NOT NULL,
    severity text NOT NULL,
    subsystem text NOT NULL,
    message text NOT NULL,
    "state" text NOT NULL,
    "createdOn" timestamp without time zone NOT NULL,
    "ownerId" bigint, -- can be null when first created by system
    "createdBy" text, -- if the alert is created by system
    CONSTRAINT alert_pkey PRIMARY KEY ("alertId")
);

CREATE TABLE logbook.alert_log
(
    "alertId" bigint NOT NULL,
    "logId" bigint NOT NULL,
    CONSTRAINT alert_log_pkey PRIMARY KEY ("alertId", "logId"),
    CONSTRAINT alert_fk FOREIGN KEY ("alertId")
        REFERENCES logbook.alerts ("alertId") MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION,
    CONSTRAINT logId_fk FOREIGN KEY ("logId")
        REFERENCES logbook.logmessages ("logId") MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION

);

-- Table: logbook.notes
CREATE TABLE logbook.notes
(
    "noteId" bigserial NOT NULL,
    "ownerId" bigint NOT NULL,
    "alertId" bigint NOT NULL,
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
    "transitionId" bigserial NOT NULL,
    "ownerId" bigint NOT NULL,
    "alertId" bigint NOT NULL,
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
CREATE TABLE logbook."alertOwnerHistory"
(
    "alertHistoryId" bigserial NOT NULL,
    "oldOwnerId" bigint NOT NULL,
    "newOwnerId" bigint NOT NULL,
    "alertId" bigint NOT NULL,
    "createdOn" timestamp without time zone NOT NULL,
    CONSTRAINT alertowners_pkey PRIMARY KEY ("alertHistoryId"),
    CONSTRAINT alert_fk FOREIGN KEY ("alertId")
        REFERENCES logbook.alerts ("alertId") MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
);
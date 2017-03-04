-- SCHEMA: logbook
CREATE SCHEMA LOGBOOK;
--    AUTHORIZATION postgres;

COMMENT ON SCHEMA LOGBOOK
    IS 'Scheme for logbook';

CREATE SEQUENCE hibernate_sequence START 1 INCREMENT BY 1;

-- Table: logbook.logmessages
CREATE TABLE LOGBOOK.LOG_MESSAGES
(
    LOG_ID bigserial NOT NULL,
    SEVERITY text NOT NULL,
    SUBSYSTEM text NOT NULL,
    MESSAGE text NOT NULL,
    CREATED_ON timestamp without time zone NOT NULL,
    DOWNLOADED_ON timestamp without time zone NOT NULL,
    CONSTRAINT log_pkey PRIMARY KEY (LOG_ID)
);

-- Table: logbook.alert
CREATE TABLE LOGBOOK.ALERTS
(
    ALERT_ID bigserial NOT NULL,
    SEVERITY text NOT NULL,
    SUBSYSTEM text NOT NULL,
    MESSAGE text NOT NULL,
    STATE text NOT NULL,
    CREATED_ON timestamp without time zone NOT NULL,
    ASSIGNEE_ID bigint, -- can be null when first created by system
    CONSTRAINT alert_pkey PRIMARY KEY (ALERT_ID)
);

-- Table: logbook.notes
CREATE TABLE LOGBOOK.NOTES
(
    NOTE_ID bigserial NOT NULL,
    AUTHOR_ID bigint NOT NULL,
    ALERT_ID bigint NOT NULL,
    MESSAGE text NOT NULL,
    CREATED_ON timestamp without time zone NOT NULL,
    CONSTRAINT note_pkey PRIMARY KEY (NOTE_ID),
    CONSTRAINT alert_fk FOREIGN KEY (ALERT_ID)
        REFERENCES LOGBOOK.ALERTS (ALERT_ID) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
);

-- Table: logbook.transitions
CREATE TABLE LOGBOOK.TRANSITIONS
(
    TRANSITION_ID bigserial NOT NULL,
    EXECUTED_BY bigint NOT NULL,
    ALERT_ID bigint NOT NULL,
    START_STATE text NOT NULL,
    END_STATE text NOT NULL,
    CREATED_ON timestamp without time zone NOT NULL,
    CONSTRAINT transition_pkey PRIMARY KEY (TRANSITION_ID),
    CONSTRAINT alert_fk FOREIGN KEY (ALERT_ID)
        REFERENCES LOGBOOK.ALERTS (ALERT_ID) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
);

-- Table: logbook.alertOwnerHistory
CREATE TABLE LOGBOOK.ASSIGNEE_HISTORY
(
    ASSIGNEE_HISTORY_ID bigserial NOT NULL,
    EXECUTED_BY bigint NOT NULL,
    OLD_ASSIGNEE_ID bigint NOT NULL,
    NEW_ASSIGNEE_ID bigint NOT NULL,
    ALERT_ID bigint NOT NULL,
    CREATED_ON timestamp without time zone NOT NULL,
    CONSTRAINT alertassignee_pkey PRIMARY KEY (ASSIGNEE_HISTORY_ID),
    CONSTRAINT alert_fk FOREIGN KEY (ALERT_ID)
        REFERENCES LOGBOOK.ALERTS (ALERT_ID) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
);
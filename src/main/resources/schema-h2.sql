DROP SCHEMA IF EXISTS LOGBOOK;

-- SCHEMA: logbook
CREATE SCHEMA LOGBOOK;

CREATE SEQUENCE hibernate_sequence START 1 INCREMENT BY 1;

-- Table: logbook.logmessages
CREATE TABLE LOGBOOK.LOG_MESSAGES
(
  LOG_ID        bigserial NOT NULL PRIMARY KEY,
  SEVERITY      text      NOT NULL,
  SUBSYSTEM     text      NOT NULL,
  MESSAGE       text      NOT NULL,
  CREATED_ON    TIMESTAMP NOT NULL,
  DOWNLOADED_ON TIMESTAMP NOT NULL
);

-- Table: logbook.alerts
CREATE TABLE LOGBOOK.ALERTS
(
  ALERT_ID    bigserial NOT NULL PRIMARY KEY,
  SEVERITY    text      NOT NULL,
  SUBSYSTEM   text      NOT NULL,
  MESSAGE     text      NOT NULL,
  STATE       text      NOT NULL,
  CREATED_ON  TIMESTAMP NOT NULL,
  ASSIGNEE_ID BIGINT -- can be null when first created by system
);

-- Table: logbook.notes
CREATE TABLE LOGBOOK.NOTES
(
  NOTE_ID    bigserial NOT NULL PRIMARY KEY,
  AUTHOR_ID  BIGINT    NOT NULL,
  ALERT_ID   BIGINT    NOT NULL,
  MESSAGE    text      NOT NULL,
  CREATED_ON TIMESTAMP NOT NULL
);

-- Table: logbook.transitions
CREATE TABLE LOGBOOK.TRANSITIONS
(
  TRANSITION_ID bigserial NOT NULL PRIMARY KEY,
  EXECUTED_BY   BIGINT    NOT NULL,
  ALERT_ID      BIGINT    NOT NULL,
  START_STATE   text      NOT NULL,
  END_STATE     text      NOT NULL,
  CREATED_ON    TIMESTAMP NOT NULL
);

-- Table: logbook.assignee_history
CREATE TABLE LOGBOOK.ASSIGNEE_HISTORY
(
  ASSIGNEE_HISTORY_ID bigserial NOT NULL PRIMARY KEY,
  EXECUTED_BY         BIGINT    NOT NULL,
  OLD_ASSIGNEE_ID     BIGINT    NOT NULL,
  NEW_ASSIGNEE_ID     BIGINT    NOT NULL,
  ALERT_ID            BIGINT    NOT NULL,
  CREATED_ON          TIMESTAMP NOT NULL
);
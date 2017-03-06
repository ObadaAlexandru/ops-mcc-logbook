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
    CREATED_ON timestamp NOT NULL,
    DOWNLOADED_ON timestamp NOT NULL
);
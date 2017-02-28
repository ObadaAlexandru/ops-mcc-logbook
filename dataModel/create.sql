-- SCHEMA: logbook
CREATE SCHEMA logbook;
--    AUTHORIZATION postgres;

COMMENT ON SCHEMA logbook
    IS 'Scheme for logbook';

CREATE SEQUENCE hibernate_sequence START 1 INCREMENT BY 1;

-- Table: logbook.logmessages
CREATE TABLE logbook.logmessages
(
    log_id bigserial NOT NULL,
    severity text NOT NULL,
    subsystem text NOT NULL,
    message text NOT NULL,
    created_on timestamp without time zone NOT NULL,
    downloaded_on timestamp without time zone NOT NULL,
    CONSTRAINT log_pkey PRIMARY KEY (log_id)
);

-- Table: logbook.alert
CREATE TABLE logbook.alerts
(
    alert_id bigserial NOT NULL,
    severity text NOT NULL,
    subsystem text NOT NULL,
    message text NOT NULL,
    "state" text NOT NULL,
    created_on timestamp without time zone NOT NULL,
    owner_id bigint, -- can be null when first created by system
    created_by text, -- if the alert is created by system
    CONSTRAINT alert_pkey PRIMARY KEY (alert_id)
);

CREATE TABLE logbook.alert_log
(
    alert_id bigint NOT NULL,
    log_id bigint NOT NULL,
    CONSTRAINT alert_log_pkey PRIMARY KEY (alert_id, log_id),
    CONSTRAINT alert_fk FOREIGN KEY (alert_id)
        REFERENCES logbook.alerts (alert_id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION,
    CONSTRAINT log_id_fk FOREIGN KEY (log_id)
        REFERENCES logbook.logmessages (log_id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION

);

-- Table: logbook.notes
CREATE TABLE logbook.notes
(
    note_id bigserial NOT NULL,
    owner_id bigint NOT NULL,
    alert_id bigint NOT NULL,
    message text NOT NULL,
    created_on timestamp without time zone NOT NULL,
    CONSTRAINT note_pkey PRIMARY KEY (note_id),
    CONSTRAINT alert_fk FOREIGN KEY (alert_id)
        REFERENCES logbook.alerts (alert_id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
);

-- Table: logbook.transitions
CREATE TABLE logbook.transitions
(
    transition_id bigserial NOT NULL,
    owner_id bigint NOT NULL,
    alert_id bigint NOT NULL,
    start_state text NOT NULL,
    end_state text NOT NULL,
    created_on timestamp without time zone NOT NULL,
    CONSTRAINT transition_pkey PRIMARY KEY (transition_id),
    CONSTRAINT alert_fk FOREIGN KEY (alert_id)
        REFERENCES logbook.alerts (alert_id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
);

-- Table: logbook.alertOwnerHistory
CREATE TABLE logbook."ownerHistory"
(
    owner_history_id bigserial NOT NULL,
    owner_id bigint NOT NULL,
    oldOwner_id bigint NOT NULL,
    new_owner_id bigint NOT NULL,
    alert_id bigint NOT NULL,
    created_on timestamp without time zone NOT NULL,
    CONSTRAINT alertowners_pkey PRIMARY KEY (owner_history_id),
    CONSTRAINT alert_fk FOREIGN KEY (alert_id)
        REFERENCES logbook.alerts (alert_id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
);
-- INSERT LOG MESSAGES
INSERT INTO logbook.log_messages (log_id, severity, subsystem, message, created_on, downloaded_on)
VALUES (0, 'D', 'COM', 'test message1', '2015-01-05T00:00:00', '2017-03-05T00:00:00');
INSERT INTO logbook.log_messages (log_id, severity, subsystem, message, created_on, downloaded_on)
VALUES (1, 'D', 'THM', 'test message2', '2015-01-05T00:00:00', '2017-03-05T00:00:00');
INSERT INTO logbook.log_messages (log_id, severity, subsystem, message, created_on, downloaded_on)
VALUES (2, 'W', 'COM', 'test message3', '2015-03-05T00:10:00', '2017-03-05T00:10:00');
INSERT INTO logbook.log_messages (log_id, severity, subsystem, message, created_on, downloaded_on)
VALUES (3, 'C', 'COM', 'test message4', '2015-03-05T00:30:00', '2017-03-05T00:30:00');
INSERT INTO logbook.log_messages (log_id, severity, subsystem, message, created_on, downloaded_on)
VALUES (4, 'C', 'COM', 'test message5', '2015-05-05T01:00:00', '2017-03-05T01:00:00');
INSERT INTO logbook.log_messages (log_id, severity, subsystem, message, created_on, downloaded_on)
VALUES (5, 'W', 'COM', 'test message6', '2015-05-05T01:30:00', '2015-05-05T01:30:00');
INSERT INTO logbook.log_messages (log_id, severity, subsystem, message, created_on, downloaded_on)
VALUES (6, 'W', 'COM', 'test message6', CURRENT_TIMESTAMP(), '2017-03-05T01:30:00');
INSERT INTO logbook.log_messages (log_id, severity, subsystem, message, created_on, downloaded_on)
VALUES (7, 'W', 'COM', 'test message6', CURRENT_TIMESTAMP(), '2017-03-05T01:30:00');
INSERT INTO logbook.log_messages (log_id, severity, subsystem, message, created_on, downloaded_on)
VALUES (8, 'C', 'COM', 'test message6', CURRENT_TIMESTAMP(), '2017-03-05T01:30:00');
INSERT INTO logbook.log_messages (log_id, severity, subsystem, message, created_on, downloaded_on)
VALUES (9, 'C', 'COM', 'test message6', CURRENT_TIMESTAMP(), '2017-03-05T01:30:00');
INSERT INTO logbook.log_messages (log_id, severity, subsystem, message, created_on, downloaded_on)
VALUES (10, 'C', 'COM', 'test message6', CURRENT_TIMESTAMP(), '2017-03-05T01:30:00');

-- INSERT ALERTS
INSERT INTO logbook.alerts (alert_id, severity, subsystem, message, state, created_on, assignee_id)
VALUES (0, 'W', 'COM', 'test message0', 'N', '2015-01-05T00:00:00', 1);
INSERT INTO logbook.alerts (alert_id, severity, subsystem, message, state, created_on, assignee_id)
VALUES (1, 'W', 'THM', 'test message1', 'N', '2015-01-05T00:10:00', 1);
INSERT INTO logbook.alerts (alert_id, severity, subsystem, message, state, created_on, assignee_id)
VALUES (2, 'C', 'THM', 'test message2', 'A', '2015-03-05T00:10:00', 2);
INSERT INTO logbook.alerts (alert_id, severity, subsystem, message, state, created_on, assignee_id)
VALUES (3, 'C', 'COM', 'test message3', 'R', '2015-03-05T00:30:00', 3);
INSERT INTO logbook.alerts (alert_id, severity, subsystem, message, state, created_on, assignee_id)
VALUES (4, 'W', 'EPS', 'test message4', 'A', '2015-05-05T00:30:00', 4);
INSERT INTO logbook.alerts (alert_id, severity, subsystem, message, state, created_on, assignee_id)
VALUES (5, 'W', 'COM', 'test message5', 'A', '2015-05-05T01:00:00', 2);
INSERT INTO logbook.alerts (alert_id, severity, subsystem, message, state, created_on, assignee_id)
VALUES (6, 'W', 'COM', 'test message0', 'N', CURRENT_TIMESTAMP(), 1);
INSERT INTO logbook.alerts (alert_id, severity, subsystem, message, state, created_on, assignee_id)
VALUES (7, 'W', 'THM', 'test message1', 'N', CURRENT_TIMESTAMP(), 1);
INSERT INTO logbook.alerts (alert_id, severity, subsystem, message, state, created_on, assignee_id)
VALUES (8, 'C', 'THM', 'test message2', 'A', CURRENT_TIMESTAMP(), 2);
INSERT INTO logbook.alerts (alert_id, severity, subsystem, message, state, created_on, assignee_id)
VALUES (9, 'C', 'COM', 'test message3', 'R', CURRENT_TIMESTAMP(), 3);
INSERT INTO logbook.alerts (alert_id, severity, subsystem, message, state, created_on, assignee_id)
VALUES (100, 'W', 'EPS', 'test message4', 'A', CURRENT_TIMESTAMP(), 4);
INSERT INTO logbook.alerts (alert_id, severity, subsystem, message, state, created_on, assignee_id)
VALUES (101, 'W', 'COM', 'test message5', 'A', CURRENT_TIMESTAMP(), 2);

-- INSERT NOTES
-- INSERT TRANSITIONS
-- INSERT ASSIGNEE_HISTORY

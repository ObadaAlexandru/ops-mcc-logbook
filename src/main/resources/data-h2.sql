INSERT INTO logbook.log_messages (
  log_id, severity, subsystem, message, created_on, downloaded_on)
VALUES (0, 'D', 'COM', 'test message1', '2015-01-05T16:15:25', '2017-03-05T16:15:25');
INSERT INTO logbook.log_messages (
  log_id, severity, subsystem, message, created_on, downloaded_on)
VALUES (1, 'D', 'THM', 'test message2', '2015-01-05T16:15:25', '2017-03-05T16:15:25');
INSERT INTO logbook.log_messages (
  log_id, severity, subsystem, message, created_on, downloaded_on)
VALUES (2, 'W', 'COM', 'test message3', '2015-03-05T16:16:25', '2017-03-05T16:16:25');
INSERT INTO logbook.log_messages (
  log_id, severity, subsystem, message, created_on, downloaded_on)
VALUES (3, 'C', 'COM', 'test message4', '2015-03-05T16:17:25', '2017-03-05T16:17:25');
INSERT INTO logbook.log_messages (
  log_id, severity, subsystem, message, created_on, downloaded_on)
VALUES (4, 'C', 'COM', 'test message5', '2015-05-05T16:22:25', '2017-03-05T16:22:25');
INSERT INTO logbook.log_messages (
  log_id, severity, subsystem, message, created_on, downloaded_on)
VALUES (5, 'W', 'COM', 'test message6', '2015-05-05T16:30:25', '2017-03-05T16:30:25');
INSERT INTO logbook.log_messages (
  log_id, severity, subsystem, message, created_on, downloaded_on)
VALUES (6, 'W', 'COM', 'test message6', CURRENT_TIMESTAMP(), '2017-03-05T16:30:25');
INSERT INTO logbook.log_messages (
  log_id, severity, subsystem, message, created_on, downloaded_on)
VALUES (7, 'W', 'COM', 'test message6', CURRENT_TIMESTAMP(), '2017-03-05T16:30:25');
INSERT INTO logbook.log_messages (
  log_id, severity, subsystem, message, created_on, downloaded_on)
VALUES (8, 'C', 'COM', 'test message6', CURRENT_TIMESTAMP(), '2017-03-05T16:30:25');
INSERT INTO logbook.log_messages (
  log_id, severity, subsystem, message, created_on, downloaded_on)
VALUES (9, 'C', 'COM', 'test message6', CURRENT_TIMESTAMP(), '2017-03-05T16:30:25');
INSERT INTO logbook.log_messages (
  log_id, severity, subsystem, message, created_on, downloaded_on)
VALUES (10, 'C', 'COM', 'test message6', CURRENT_TIMESTAMP(), '2017-03-05T16:30:25');

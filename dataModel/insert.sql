-- Populate logbook.logmessages
INSERT INTO logbook.logmessages(
	id, severity, subsystem, message)
	VALUES (1, 'warning', 'THM', 'Sensor X over specified temp');
INSERT INTO logbook.logmessages(
	id, severity, subsystem, message)
	VALUES (2, 'info', 'COM', 'Establised connection to groundstation');

-- Populate logbook.alerts
INSERT INTO logbook.alerts(
	id, severity, subsystem, message, "state", "createdOn", "ownerId", "createdBy", logmessageids)
	VALUES (1, 'critical', 'THM', 'OVERHEATING! HOUSTON, WE HAVE GOT A PROBLEM', 'new', '2017-02-01 20:34:06', null, 'system', '{0}');
INSERT INTO logbook.alerts(
	id, severity, subsystem, message, "state", "createdOn", "ownerId", "createdBy", logmessageids)
	VALUES (2, 'warning', 'EPS', 'Testing alert system', 'ack', '2017-02-01 20:35:28', 1, null, '{0,1}');
    
-- Populate logbook.notes
INSERT INTO logbook.notes(
	id, "ownerId", "alertId", message, "createdOn")
	VALUES (1, 1, 1, '3-4, 3-4 Houston, do you copy?', '2017-02-01 20:40:32');
INSERT INTO logbook.notes(
	id, "ownerId", "alertId", message, "createdOn")
	VALUES (2, 1, 2, 'Comment on EPS warning alert', '2017-02-01 21:43:10');
    
-- Populate logbook.transitions
INSERT INTO logbook.transitions(
	id, "ownerId", "alertId", "startState", "endState", "createdOn")
	VALUES (1, 1, 2, 'new', 'ack', '2017-02-01 20:45:30');
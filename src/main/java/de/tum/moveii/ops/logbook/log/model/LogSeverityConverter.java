package de.tum.moveii.ops.logbook.log.model;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

/**
 * Created by Constantin Costescu on 08-Feb-17.
 */

@Converter
public class LogSeverityConverter implements AttributeConverter<LogSeverity, String> {

    @Override
    public String convertToDatabaseColumn(LogSeverity attribute) {
        switch (attribute) {
            case DEBUG:
                return "D";
            case INFO:
                return "I";
            case WARNING:
                return "W";
            case ERROR:
                return "E";
            case CRITICAL:
                return "C";
            default:
                throw new IllegalArgumentException("<LogSeverity> Unknown attribute " + attribute);
        }
    }

    @Override
    public LogSeverity convertToEntityAttribute(String dbData) {
        switch (dbData) {
            case "D":
                return LogSeverity.DEBUG;
            case "I":
                return LogSeverity.INFO;
            case "W":
                return LogSeverity.WARNING;
            case "E":
                return LogSeverity.ERROR;
            case "C":
                return LogSeverity.CRITICAL;
            default:
                throw new IllegalArgumentException("<LogSeverity> Unknown entity " + dbData);
        }
    }
}

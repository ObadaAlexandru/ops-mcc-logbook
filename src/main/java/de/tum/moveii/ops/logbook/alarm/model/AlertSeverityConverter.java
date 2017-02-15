package de.tum.moveii.ops.logbook.alarm.model;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

/**
 * Created by Constantin Costescu on 08-Feb-17.
 */

@Converter
public class AlertSeverityConverter implements AttributeConverter<AlertSeverity, String> {

    @Override
    public String convertToDatabaseColumn(AlertSeverity attribute) {
        switch (attribute) {
            case CRITICAL:
                return "C";
            case WARNING:
                return "W";
            default:
                throw new IllegalArgumentException("<AlertSeverity> Unknown attribute " + attribute);
        }
    }

    @Override
    public AlertSeverity convertToEntityAttribute(String dbData) {
        switch (dbData) {
            case "C":
                return AlertSeverity.CRITICAL;
            case "W":
                return AlertSeverity.WARNING;
            default:
                throw new IllegalArgumentException("<AlertSeverity> Unknown entity " + dbData);
        }
    }
}

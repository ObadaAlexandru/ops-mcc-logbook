package de.tum.moveii.ops.logbook.alarm.model;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

/**
 * Created by Constantin Costescu on 08-Feb-17.
 */

@Converter
public class AlertStateConverter implements AttributeConverter<AlertState, String> {

    @Override
    public String convertToDatabaseColumn(AlertState attribute) {
        switch (attribute) {
            case NEW:
                return "N";
            case ACKNOWLEDGED:
                return "A";
            case RESOLVED:
                return "R";
            default:
                throw new IllegalArgumentException("<AlertState> Unknown attribute " + attribute);
        }
    }

    @Override
    public AlertState convertToEntityAttribute(String dbData) {
        switch (dbData) {
            case "N":
                return AlertState.NEW;
            case "A":
                return AlertState.ACKNOWLEDGED;
            case "R":
                return AlertState.RESOLVED;
            default:
                throw new IllegalArgumentException("<AlertState> Unknown entity" + dbData);
        }
    }
}

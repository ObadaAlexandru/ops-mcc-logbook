package de.tum.moveii.ops.logbook.api.mapper;

import de.tum.moveii.ops.logbook.alarm.model.OwnerHistory;
import de.tum.moveii.ops.logbook.api.message.OwnerHistoryMessage;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;

/**
 * Created by Constantin Costescu on 12-Feb-17.
 */
@Component
public class OwnerHistoryMapper implements ResourceMapper<OwnerHistoryMessage, OwnerHistory> {
    @Override
    public OwnerHistory toResource(OwnerHistoryMessage message) {
        OwnerHistory ownerHistory = OwnerHistory.builder()
                .ownerId(message.getOwner())
                .oldOwnerId(message.getOwner())
                .newOwnerId(message.getNewOwner())
                .createdOn(Timestamp.valueOf(message.getCreatedOn()))
                .build();

        return ownerHistory;
    }

    @Override
    public OwnerHistoryMessage toMessage(OwnerHistory resource) {
        OwnerHistoryMessage ownerHistoryMessage = OwnerHistoryMessage.builder()
                .owner(resource.getOwnerId())
                .oldOwner(resource.getOldOwnerId())
                .newOwner(resource.getNewOwnerId())
                .createdOn(resource.getCreatedOn().toLocalDateTime())
                .build();

        return ownerHistoryMessage;
    }
}

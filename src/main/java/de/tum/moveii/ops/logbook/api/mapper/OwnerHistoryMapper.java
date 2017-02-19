package de.tum.moveii.ops.logbook.api.mapper;

import de.tum.moveii.ops.logbook.alert.model.OwnerHistory;
import de.tum.moveii.ops.logbook.api.message.OwnerHistoryMessage;
import org.springframework.stereotype.Component;

import javax.validation.constraints.NotNull;
import java.sql.Timestamp;

/**
 * Created by Constantin Costescu on 12-Feb-17.
 */
@Component
public class OwnerHistoryMapper implements ResourceMapper<OwnerHistoryMessage, OwnerHistory> {
    @Override
    public OwnerHistory toResource(@NotNull OwnerHistoryMessage message) {
        return OwnerHistory.builder()
                .ownerHistoryId(message.getOwnerHistoryId())
                .ownerId(message.getOwner())
                .oldOwnerId(message.getOwner())
                .newOwnerId(message.getNewOwner())
                .createdOn(Timestamp.valueOf(message.getCreatedOn()))
                .build();
    }

    @Override
    public OwnerHistoryMessage toMessage(@NotNull OwnerHistory resource) {
        return OwnerHistoryMessage.builder()
                .ownerHistoryId(resource.getOwnerHistoryId())
                .owner(resource.getOwnerId())
                .oldOwner(resource.getOldOwnerId())
                .newOwner(resource.getNewOwnerId())
                .createdOn(resource.getCreatedOn().toLocalDateTime())
                .build();
    }
}

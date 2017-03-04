package de.tum.moveii.ops.logbook.api.mapper;

import de.tum.moveii.ops.logbook.alert.model.AssigneeHistory;
import de.tum.moveii.ops.logbook.api.message.AssigneeHistoryMessage;
import org.springframework.stereotype.Component;

import javax.validation.constraints.NotNull;

/**
 * Created by Constantin Costescu on 12-Feb-17.
 */
@Component
public class AssigneeHistoryMapper implements ResourceMapper<AssigneeHistoryMessage, AssigneeHistory> {
    @Override
    public AssigneeHistory toResource(@NotNull AssigneeHistoryMessage message) {
        return AssigneeHistory.builder()
                .executedBy(message.getExecutedBy())
                .oldAssigneeId(message.getExecutedBy())
                .newAssigneeId(message.getNewAssignee())
                .createdOn(message.getCreatedOn())
                .build();
    }

    @Override
    public AssigneeHistoryMessage toMessage(@NotNull AssigneeHistory resource) {
        return AssigneeHistoryMessage.builder()
                .executedBy(resource.getExecutedBy())
                .oldAssignee(resource.getOldAssigneeId())
                .newAssignee(resource.getNewAssigneeId())
                .createdOn(resource.getCreatedOn())
                .build();
    }
}
package de.tum.moveii.ops.logbook.api.message;

import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

/**
 * Created by Constantin Costescu on 11-Feb-17.
 */
@Data
@Builder
public class OwnerHistoryMessage {
    @NotNull
    private Long oldOwner;
    @NotNull
    private Long newOwner;
    @NotNull
    private Long owner;
    @NotNull
    private LocalDateTime createdOn;
}

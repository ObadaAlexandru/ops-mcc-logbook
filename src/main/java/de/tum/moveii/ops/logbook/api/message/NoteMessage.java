package de.tum.moveii.ops.logbook.api.message;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

/**
 * Created by Alexandru Obada on 31/01/17.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class NoteMessage {
    private Long author;
    @NotNull
    private String message;

    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime createdOn;
}

package de.tum.moveii.ops.logbook.user;

import lombok.NonNull;

/**
 * Created by Alexandru Obada on 04/03/17.
 */
public interface UserService {
    Long getCurrentUser();
    boolean exists(@NonNull Long userId);
    Long getStandByUser();
}

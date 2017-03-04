package de.tum.moveii.ops.logbook.user;


import lombok.NonNull;
import org.springframework.stereotype.Service;

/**
 * Created by Alexandru Obada on 04/03/17.
 */
@Service
public class UserServiceImpl implements UserService {
    @Override
    public Long getCurrentUser() {
        //TODO Consume user service
        return 5L;
    }

    @Override
    public boolean exists(@NonNull Long userId) {
        //TODO Consume user service
        return true;
    }

    @Override
    public Long getStandByUser() {
        //TODO Consume user service
        return 6L;
    }
}

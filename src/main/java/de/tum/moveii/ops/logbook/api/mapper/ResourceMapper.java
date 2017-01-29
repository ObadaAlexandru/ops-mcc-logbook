package de.tum.moveii.ops.logbook.api.mapper;

import de.tum.moveii.ops.logbook.api.message.Message;

/**
 * Created by Alexandru Obada on 29/01/17.
 */
public interface ResourceMapper<M extends Message, R> {
    R toResource(M message);
    M toMessage(R resource);
}

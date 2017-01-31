package de.tum.moveii.ops.logbook.api.mapper;

/**
 * Created by Alexandru Obada on 29/01/17.
 */
public interface ResourceMapper<M, R> {
    R toResource(M message);
    M toMessage(R resource);
}

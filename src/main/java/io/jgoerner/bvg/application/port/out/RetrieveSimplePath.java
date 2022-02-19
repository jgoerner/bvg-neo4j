package io.jgoerner.bvg.application.port.out;

import io.jgoerner.bvg.domain.Route;

/**
 * Simple path being the connection between A and B
 * without further filtering or such
 */
public interface RetrieveSimplePath {

    Route retrieveSimplePath(String from, String to);

}

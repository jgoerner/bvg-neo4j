package io.jgoerner.bvg.application.port.out;

import io.jgoerner.bvg.domain.Route;
import io.jgoerner.bvg.domain.Station;

/**
 * Simple path being the connection between A and B
 * without further filtering or such
 */
public interface RetrieveSimplePath {

    Route retrieveSimplePath(Station from, Station to);

}

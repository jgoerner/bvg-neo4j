package io.jgoerner.bvg.application.port.out;

import io.jgoerner.bvg.domain.Route;

/**
 * Fastest path being the connection between A and B
 * taking into consideration the duration on that way
 */
public interface RetrieveFastestPath {

    Route retrieveFastestPath(String from, String to);

}

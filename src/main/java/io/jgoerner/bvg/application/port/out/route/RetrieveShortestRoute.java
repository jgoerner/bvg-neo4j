package io.jgoerner.bvg.application.port.out.route;

import io.jgoerner.bvg.domain.Route;

/**
 * Simple route being the connection between A and B
 * without further filtering or such
 */
public interface RetrieveShortestRoute {

    Route retrieveShortestRoute(String from, String to);

}

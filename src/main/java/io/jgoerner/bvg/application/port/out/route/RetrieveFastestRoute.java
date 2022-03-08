package io.jgoerner.bvg.application.port.out.route;

import io.jgoerner.bvg.domain.Route;

/**
 * Fastest route being the connection between A and B
 * taking into consideration the duration on that way
 */
public interface RetrieveFastestRoute {

    Route retrieveFastestRoute(String from, String to);

}

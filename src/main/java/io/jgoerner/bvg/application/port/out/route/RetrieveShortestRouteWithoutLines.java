package io.jgoerner.bvg.application.port.out.route;

import io.jgoerner.bvg.domain.Line;
import io.jgoerner.bvg.domain.Route;

import java.util.Collection;

/**
 * Shortest route here being the connection between A and B
 * without the lines specified in args
 */
public interface RetrieveShortestRouteWithoutLines {

    Route retrieveShortestRouteWithoutLines(String from, String to, Collection<Line> blacklistedLines);

}

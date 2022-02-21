package io.jgoerner.bvg.application.port.out;

import io.jgoerner.bvg.domain.Line;
import io.jgoerner.bvg.domain.Route;

import java.util.Collection;

/**
 * Shortest path here being the connection between A and B
 * without the lines specified in args
 */
public interface RetrieveShortestPathWithoutLines {

    Route retrieveBlacklistedLinesShortestPath(String from, String to, Collection<Line> blacklistedLines);

}

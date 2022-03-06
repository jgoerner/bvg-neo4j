package io.jgoerner.bvg.application.port.out;

import io.jgoerner.bvg.domain.Route;

/**
 * Simple path being the connection between A and B
 * without further filtering or such
 */
public interface RetrieveShortestPath {

    Route retrieveShortestPath(String from, String to);

}

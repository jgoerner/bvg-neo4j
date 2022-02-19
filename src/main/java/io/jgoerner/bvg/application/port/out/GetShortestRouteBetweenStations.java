package io.jgoerner.bvg.application.port.out;

import io.jgoerner.bvg.domain.Route;

public interface GetShortestRouteBetweenStations {
    Route getShortestRouteBetweenStations(String from, String to);

}

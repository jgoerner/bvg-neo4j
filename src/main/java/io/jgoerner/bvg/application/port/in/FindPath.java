package io.jgoerner.bvg.application.port.in;

import io.jgoerner.bvg.domain.Route;
import io.jgoerner.bvg.domain.Station;


public interface FindPath {

    Route execute(Station from, Station to);
}

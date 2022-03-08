package io.jgoerner.bvg.application.port.in;

import io.jgoerner.bvg.application.types.RouteFindingOptions;
import io.jgoerner.bvg.domain.Route;

public interface FindRoute {

    Route findRoute(String from, String to, RouteFindingOptions options);

    String getName();
}

package io.jgoerner.bvg.application.service.route;

import io.jgoerner.bvg.application.port.in.FindRoute;
import io.jgoerner.bvg.application.port.out.route.RetrieveShortestRoute;
import io.jgoerner.bvg.application.types.RouteFindingOptions;
import io.jgoerner.bvg.domain.Route;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ShortestRouteFinder implements FindRoute {

    Logger log = LoggerFactory.getLogger(ShortestRouteFinder.class);

    @Autowired
    private RetrieveShortestRoute shortestPathRetriever;

    @Override
    public Route findRoute(String from, String to, RouteFindingOptions options) {
        log.info("Finding shortest path from {} to {}", from, to);
        return this.shortestPathRetriever.retrieveShortestRoute(from, to);
    }

    @Override
    public String getName() {
        return "shortest";
    }
}

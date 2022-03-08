package io.jgoerner.bvg.application.service.route;

import io.jgoerner.bvg.application.port.in.FindRoute;
import io.jgoerner.bvg.application.port.out.route.RetrieveFastestRoute;
import io.jgoerner.bvg.application.types.RouteFindingOptions;
import io.jgoerner.bvg.domain.Route;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class FastestRouteFinder implements FindRoute {

    Logger log = LoggerFactory.getLogger(FastestRouteFinder.class);

    @Autowired
    private RetrieveFastestRoute fastestPathRetriever;

    @Override
    public Route findRoute(String from, String to, RouteFindingOptions options) {
        log.info("Finding fastest path from {} to {}", from, to);
        return this.fastestPathRetriever.retrieveFastestRoute(from, to);
    }

    @Override
    public String getName() {
        return "fastest";
    }
}

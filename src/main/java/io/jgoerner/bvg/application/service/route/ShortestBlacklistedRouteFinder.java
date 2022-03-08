package io.jgoerner.bvg.application.service.route;

import io.jgoerner.bvg.application.port.in.FindRoute;
import io.jgoerner.bvg.application.port.out.RetrieveShortestPathWithoutLines;
import io.jgoerner.bvg.application.types.RouteFindingOption;
import io.jgoerner.bvg.application.types.RouteFindingOptions;
import io.jgoerner.bvg.domain.Line;
import io.jgoerner.bvg.domain.Route;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ShortestBlacklistedRouteFinder implements FindRoute {

    Logger log = LoggerFactory.getLogger(ShortestBlacklistedRouteFinder.class);

    @Autowired
    private RetrieveShortestPathWithoutLines shortestPathBlacklistedRetriever;

    @Override
    public Route findRoute(String from, String to, RouteFindingOptions options) {

        try {
            List<Line> exclude = (List<Line>) options.options().get(RouteFindingOption.EXCLUDE_LINES);
            log.info("Finding shortest path from {} to {} without {}", from, to, exclude);

            return this.shortestPathBlacklistedRetriever.retrieveShortestPathWithoutLines(from, to, exclude);

        } catch (Error e) {
            return Route.withoutSegments();
        }

    }

    @Override
    public String getName() {
        return "shortest-blacklisted";
    }
}

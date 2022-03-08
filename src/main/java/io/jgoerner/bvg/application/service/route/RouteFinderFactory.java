package io.jgoerner.bvg.application.service.route;

import io.jgoerner.bvg.application.port.in.FindRoute;
import io.jgoerner.bvg.domain.Line;
import io.jgoerner.bvg.domain.RouteStrategy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class RouteFinderFactory {

    @Autowired
    private List<FindRoute> routeFindingStrategies;

    private static final Map<String, FindRoute> routeFindingStrategyCache = new HashMap<>();

    @PostConstruct
    public void initRouteFindingCache() {
        for (FindRoute strategy : routeFindingStrategies) {
            routeFindingStrategyCache.put(strategy.getName(), strategy);
        }
    }

    public static FindRoute getRouteFinder(RouteStrategy strategy, List<Line> exclude) {
        String key = switch (strategy) {
            case fastest -> "fastest";
            case shortest -> exclude.size() == 0 ? "shortest" : "shortest-blacklisted";
        };

        return routeFindingStrategyCache.get(key);
    }
}

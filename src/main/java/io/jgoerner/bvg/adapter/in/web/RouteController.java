package io.jgoerner.bvg.adapter.in.web;

import io.jgoerner.bvg.application.service.route.RouteFinderFactory;
import io.jgoerner.bvg.application.types.RouteFindingOption;
import io.jgoerner.bvg.application.types.RouteFindingOptions;
import io.jgoerner.bvg.domain.Line;
import io.jgoerner.bvg.domain.RouteStrategy;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;


@RestController
@RequestMapping("/route")
public class RouteController {

    @RequestMapping(path = "", method = RequestMethod.GET)
    public Object retrieveRoute(
            @RequestParam("from") String from,
            @RequestParam("to") String to,
            @RequestParam(value = "exclude", required = false, defaultValue = "") List<Line> exclude,
            @RequestParam(value = "summarized", required = false, defaultValue = "false") Boolean summarized,
            @RequestParam(value = "strategy", required = false, defaultValue = "shortest") RouteStrategy strategy
    ) {
        var routeFinder = RouteFinderFactory.getRouteFinder(strategy, exclude);

        var route = routeFinder.findRoute(from, to,
                new RouteFindingOptions(Map.of(
                        RouteFindingOption.EXCLUDE_LINES, exclude
                ))
        );

        return summarized ? route.withSummary(strategy) : route;

    }
}

package io.jgoerner.bvg.adapter.in.web;


import io.jgoerner.bvg.application.port.out.RetrieveShortestPath;
import io.jgoerner.bvg.application.port.out.RetrieveShortestPathWithoutLines;
import io.jgoerner.bvg.application.service.SegmentService;
import io.jgoerner.bvg.domain.Line;
import io.jgoerner.bvg.domain.Route;
import io.jgoerner.bvg.domain.RouteStrategy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Objects;


@RestController
@RequestMapping("/route")
public class PathController {

    @Autowired
    private RetrieveShortestPath shortestPathRetriever;

    @Autowired
    private RetrieveShortestPathWithoutLines shortestPathWithoutLinesRetriever;

    Logger log = LoggerFactory.getLogger(SegmentService.class);

    @RequestMapping(path = "", method = RequestMethod.GET)
    public Object retrievePath(
            @RequestParam("from") String from,
            @RequestParam("to") String to,
            @RequestParam(value = "exclude", required = false) List<Line> exclude,
            @RequestParam(value = "summarized", required = false, defaultValue = "false") Boolean summarized,
            @RequestParam(value = "strategy", required = false, defaultValue = "shortest") RouteStrategy strategy
    ) {
        // CQRS-ish shortcut straight to out ports, skipping the use cases
        Route route;

        if (Objects.isNull(exclude)) {
            route = shortestPathRetriever.retrieveShortestPath(from, to);
        } else {
            route = shortestPathWithoutLinesRetriever.retrieveShortestPathWithoutLines(from, to, exclude);
        }

        return summarized ? route.withSummary(strategy) : route;

    }
}

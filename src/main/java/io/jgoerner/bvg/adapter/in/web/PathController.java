package io.jgoerner.bvg.adapter.in.web;


import io.jgoerner.bvg.application.port.out.RetrieveShortestPath;
import io.jgoerner.bvg.domain.Route;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/route")
public class PathController {

    @Autowired
    private RetrieveShortestPath simplePathRetriever;

    @RequestMapping(path = "", method = RequestMethod.GET)
    public Route test(@RequestParam("from") String from, @RequestParam("to") String to) {
        // CQRS-ish shortcut straight to out ports
        return simplePathRetriever.retrieveSimplePath(from, to);
    }
}

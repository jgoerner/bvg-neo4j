package io.jgoerner.bvg.domain;

import java.util.List;

public record Route(List<Segment> segments) {
    public RouteWithSummary withSummary(RouteStrategy strategy) {
        return new RouteWithSummary(
                this.segments,
                this.segments.size() > 0 ? this.segments.size() + 1 : 0,
                this.segments.stream().mapToInt(Segment::duration).sum(),
                strategy);
    }
}

record RouteWithSummary(List<Segment> segments, Integer stations, Integer duration, RouteStrategy strategy) {
}

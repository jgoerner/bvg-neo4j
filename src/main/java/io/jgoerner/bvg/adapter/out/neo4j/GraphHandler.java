package io.jgoerner.bvg.adapter.out.neo4j;

import io.jgoerner.bvg.application.port.out.DeleteAllSegments;
import io.jgoerner.bvg.application.port.out.SaveSegment;
import io.jgoerner.bvg.domain.Segment;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Component
public class GraphHandler implements SaveSegment, DeleteAllSegments {

    private final Logger log = LoggerFactory.getLogger(GraphHandler.class);

    @Autowired
    private StationRepository stationRepository;

    @Override
    public Segment save(Segment segment) {
        // retrieve start & End
        log.info("Retrieving first node, {}", segment.from().name());
        var startStationNode = stationRepository
                .findByName(segment.from().name());

        var startStation = startStationNode.orElseGet(() -> new StationEntity(segment.from().name()));
        if (startStationNode.isEmpty()) {
            log.info("Saving {}", startStation);
            stationRepository.save(startStation);
        }
        log.info("Saved {}", startStation);
        log.info("Retrieving second node, {}", segment.to().name());
        var endStationNode = stationRepository
                .findByName(segment.to().name());
        var endStation = endStationNode.orElseGet(() -> new StationEntity(segment.to().name()));
        if (endStationNode.isEmpty()) stationRepository.save(endStation);

        // add segment to start
        log.info("Adding connection to start node");
        startStation.addConnection(endStation);

        // save start
        log.info("Saving first node");
        stationRepository.save(startStation);
        log.info("First node saved");

        // add segment to end
        log.info("Adding connection to end node");
        endStation.addConnection(startStation);

        // add segment to end
        log.info("Saving connection to last node");
        stationRepository.save(endStation);
        log.info("Connection on last node saved");

        // save end
        return segment;
    }

    @Override
    public void deleteAll() {
        try {
            log.info("Deleting all Segments");
            stationRepository.deleteAll();
        } catch (Exception e) {
            log.info("Could not delete all segments");
        }
    }
}


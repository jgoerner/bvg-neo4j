package io.jgoerner.bvg.adapter.out.neo4j;

import io.jgoerner.bvg.application.port.out.*;
import io.jgoerner.bvg.domain.Line;
import io.jgoerner.bvg.domain.Route;
import io.jgoerner.bvg.domain.Segment;
import org.neo4j.driver.AuthTokens;
import org.neo4j.driver.Driver;
import org.neo4j.driver.GraphDatabase;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.neo4j.core.Neo4jClient;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;


/**
 * @soundtrack Daft Punk - Doin' it right
 */
@Component
public class GraphHandler implements SaveSegment, DeleteAllSegments, RetrieveShortestPath, GetAllStationsOnShortestPath, RetrieveShortestPathWithoutLines {

    private final Logger log = LoggerFactory.getLogger(GraphHandler.class);

    @Autowired
    private StationRepository stationRepository;

    @Value("${spring.neo4j.uri}")
    private String URI;

    @Value("${spring.neo4j.authentication.username}")
    private String USERNAME;

    @Value("${spring.neo4j.authentication.password}")
    private String PASSWORD;

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
        startStation.addConnection(endStation, segment.line().name(), segment.duration());

        // save start
        log.info("Saving first node");
        stationRepository.save(startStation);
        log.info("First node saved");

        // add segment to end
        log.info("Adding connection to end node");
        endStation.addConnection(startStation, segment.line().name(), segment.duration());

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

    @Override
    public Route retrieveShortestPath(String from, String to) {
        var route = getClient().query("""
                        MATCH
                        	p=shortestPath(
                        		(n {name: $from })-[*..100]->(m {name: $to })
                        	)
                        RETURN
                        	nodes(p) as stations,
                        	relationships(p) as lines
                        """)
                .bind(from).to("from")
                .bind(to).to("to")
                .fetchAs(Route.class)
                .mappedBy((typeSystem, record) -> {
                    var stations = record.get("stations").asList(v -> new StationEntity(v.get("name").asString()));
                    var lines = record.get("lines").asList(v -> new Connection(v.get("line").asString(), v.get("duration").asInt()));

                    var segments = new ArrayList<Segment>();

                    StationEntity fromStation;
                    StationEntity toStation;
                    Connection connection;

                    for (int i = 0; i < lines.size(); i++) {
                        fromStation = stations.get(i);
                        toStation = stations.get(i + 1);
                        connection = lines.get(i);

                        segments.add(
                                Segment.builder()
                                        .from(fromStation.getName())
                                        .to(toStation.getName())
                                        .line(connection.getLine())
                                        .duration(connection.getDuration())
                        );

                    }
                    return new Route(segments);
                })
                .one();
        return route.orElse(new Route(new ArrayList<>()));
    }

    @Override
    public List<StationEntity> getAllStationsOnShortestPath(String from, String to) {
        return stationRepository.findStationsOnShortestPath(from, to).orElse(new ArrayList<>());
    }

    private Neo4jClient getClient() {
        Driver driver = GraphDatabase
                .driver(URI, AuthTokens.basic(USERNAME, PASSWORD));
        return Neo4jClient.create(driver);
    }

    @Override
    public Route retrieveShortestPathWithoutLines(String from, String to, Collection<Line> blacklistedLines) {
        var route = getClient().query("""
                        MATCH
                        	p=shortestPath(
                        		(n {name: $from })-[*..100]->(m {name: $to })
                        	)
                        WHERE ALL(
                            connection IN RELATIONSHIPS(p)
                            WHERE NOT connection.line IN $blacklistedLines
                        )
                        RETURN
                        	nodes(p) as stations,
                        	relationships(p) as lines
                        """)
                .bind(from).to("from")
                .bind(to).to("to")
                .bind(
                        blacklistedLines
                                .stream()
                                .map(Line::toString)
                                .toList()
                ).to("blacklistedLines")
                .fetchAs(Route.class)
                .mappedBy((typeSystem, record) -> {
                    var stations = record.get("stations").asList(v -> new StationEntity(v.get("name").asString()));
                    var lines = record.get("lines").asList(v -> new Connection(v.get("line").asString(), v.get("duration").asInt()));

                    var segments = new ArrayList<Segment>();

                    StationEntity fromStation;
                    StationEntity toStation;
                    Connection connection;

                    for (int i = 0; i < lines.size(); i++) {
                        fromStation = stations.get(i);
                        toStation = stations.get(i + 1);
                        connection = lines.get(i);

                        segments.add(
                                Segment.builder()
                                        .from(fromStation.getName())
                                        .to(toStation.getName())
                                        .line(connection.getLine())
                                        .duration(connection.getDuration())
                        );

                    }
                    return new Route(segments);
                })
                .one();
        return route.orElse(new Route(new ArrayList<>()));
    }
}


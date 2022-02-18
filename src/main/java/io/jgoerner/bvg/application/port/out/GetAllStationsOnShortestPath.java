package io.jgoerner.bvg.application.port.out;

import io.jgoerner.bvg.adapter.out.neo4j.StationEntity;

import java.util.List;

public interface GetAllStationsOnShortestPath {
    List<StationEntity> getAllStationsOnShortestPath(String from, String to);
}

package io.jgoerner.bvg.adapter.out.neo4j;

import io.jgoerner.bvg.domain.Station;
import org.springframework.data.neo4j.core.schema.*;

import java.util.ArrayList;
import java.util.List;


@Node
public class StationEntity {

    @Id
    String name;

    @Relationship
    List<StationEntity> connections;

    public StationEntity(String name) {
        this.connections = new ArrayList<>();
        this.name = name;
    }

    void addConnection(StationEntity entity) {
        this.connections.add(entity);
    }

}

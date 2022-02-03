package io.jgoerner.bvg.adapter.out.neo4j;


import org.springframework.data.neo4j.core.schema.Id;
import org.springframework.data.neo4j.core.schema.Node;
import org.springframework.data.neo4j.core.schema.Relationship;

import java.util.ArrayList;
import java.util.List;


@Node
public class StationEntity {

    @Id
    String name;

    @Relationship
    List<Connection> connections;

    public StationEntity(String name) {
        this.connections = new ArrayList<>();
        this.name = name;
    }

    void addConnection(StationEntity entity, String line, Integer duration) {
        this.connections.add(new Connection(entity, line, duration));
    }

}


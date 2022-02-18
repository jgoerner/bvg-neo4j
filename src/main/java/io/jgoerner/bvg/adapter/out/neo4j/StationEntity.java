package io.jgoerner.bvg.adapter.out.neo4j;


import org.springframework.data.neo4j.core.schema.Id;
import org.springframework.data.neo4j.core.schema.Node;
import org.springframework.data.neo4j.core.schema.Relationship;

import java.util.List;


@Node
public final class StationEntity {

    @Id
    private final String name;

    @Relationship
    private final List<Connection> connections;

    public StationEntity(String name, List<Connection> connections) {
        this.name = name;
        this.connections = connections;
    }

    void addConnection(StationEntity entity, String line, Integer duration) {
        this.connections.add(new Connection(entity, line, duration));
    }

    @Override
    public String toString() {
        return "StationEntity{" +
                "name='" + name + '\'' +
                '}';
    }
}


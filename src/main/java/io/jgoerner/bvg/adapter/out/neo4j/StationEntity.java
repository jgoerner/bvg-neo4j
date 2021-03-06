package io.jgoerner.bvg.adapter.out.neo4j;


import org.springframework.data.neo4j.core.schema.Id;
import org.springframework.data.neo4j.core.schema.Node;
import org.springframework.data.neo4j.core.schema.Relationship;

import java.util.ArrayList;
import java.util.List;


@Node
public final class StationEntity {

    @Id
    private final String name;

    @Relationship
    private List<Connection> connections;

    public StationEntity(String name) {
        this.name = name;
        this.connections = new ArrayList<>();
    }

    public void setConnections(List<Connection> connections) {
        this.connections = connections;
    }

    public void addConnection(StationEntity entity, String line, Integer duration) {
        var connection = new Connection(line, duration);
        connection.setTo(entity);
        this.connections.add(connection);
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "StationEntity{" +
                "name='" + name + '\'' +
                '}';
    }

}


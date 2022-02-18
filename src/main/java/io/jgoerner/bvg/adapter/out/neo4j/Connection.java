package io.jgoerner.bvg.adapter.out.neo4j;

import org.springframework.data.neo4j.core.schema.GeneratedValue;
import org.springframework.data.neo4j.core.schema.Id;
import org.springframework.data.neo4j.core.schema.RelationshipProperties;
import org.springframework.data.neo4j.core.schema.TargetNode;

@RelationshipProperties
public class Connection {

    @Id
    @GeneratedValue
    private Long id;

    @TargetNode
    private final StationEntity to;

    private final String line;
    private final Integer duration;

    public Connection(StationEntity to, String line, Integer duration) {
        this.to = to;
        this.line = line;
        this.duration = duration;
    }

    @Override
    public String toString() {
        return "Connection{" +
                "line='" + line + '\'' +
                ", duration=" + duration +
                '}';
    }
}

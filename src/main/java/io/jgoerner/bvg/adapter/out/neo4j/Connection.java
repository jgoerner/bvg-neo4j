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
    private StationEntity to;

    private final String line;
    private final Integer duration;

    public Connection(String line, Integer duration) {
        this.line = line;
        this.duration = duration;
    }

    public void setTo(StationEntity to) {
        this.to = to;
    }

    public String getLine() {
        return line;
    }

    public Integer getDuration() {
        return duration;
    }

    @Override
    public String toString() {
        return "Connection{" +
                "line='" + line + '\'' +
                ", duration=" + duration +
                '}';
    }
}

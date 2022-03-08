package io.jgoerner.bvg.adapter.out.neo4j;


import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface StationRepository extends Neo4jRepository<StationEntity, String> {

    Optional<StationEntity> findByName(String name);
}

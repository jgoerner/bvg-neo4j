package io.jgoerner.bvg.application.port.in;


import io.jgoerner.bvg.domain.Connection;

public interface UpsertConnection {
   
   Connection upsert(Connection connection);

}

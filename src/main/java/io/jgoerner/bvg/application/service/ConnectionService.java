package io.jgoerner.bvg.application.service;

import io.jgoerner.bvg.application.port.in.UpsertConnection;
import io.jgoerner.bvg.domain.Connection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class ConnectionService implements UpsertConnection {

    Logger log = LoggerFactory.getLogger(ConnectionService.class);

    @Override
    public Connection upsert(Connection connection) {

        log.info("UPSERTING {}", connection);

        return connection;
    }
}

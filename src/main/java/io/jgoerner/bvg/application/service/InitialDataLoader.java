package io.jgoerner.bvg.application.service;

import io.jgoerner.bvg.domain.Connection;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Path;
import java.util.Objects;
import java.util.stream.StreamSupport;


@Component
public class InitialDataLoader implements CommandLineRunner {

    Logger log = LoggerFactory.getLogger(InitialDataLoader.class);

    @Value("${data.directory}")
    private String DATA_DIR;

    @Value("${data.file.name}")
    private String FILE_NAME;

    @Value("${data.file.header}")
    private String RAW_HEADER;

    @Override
    public void run(String... args) throws Exception {
        var lines = readFile(FILE_NAME);
        StreamSupport.stream(lines.spliterator(), false)
                .map(Connection::fromCSVRecord)
                .map(Connection::toString)
                .forEach(log::info);
        // TODO utilize the parsed records
    }

    private Iterable<CSVRecord> readFile(String filename) throws IOException {
        // File prep
        var classLoader = InitialDataLoader.class.getClassLoader();
        var file = new File(Objects.requireNonNull(classLoader.getResource(Path.of(DATA_DIR, filename).toString())).getFile());
        var reader = new FileReader(file);

        // CSV Reading
        return CSVFormat
                .Builder
                .create()
                .setHeader(RAW_HEADER.split(","))
                .build()
                .parse(reader);
    }
}

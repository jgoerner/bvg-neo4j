package io.jgoerner.bvg.adapter.in.event;

import io.jgoerner.bvg.application.port.in.CreateSegment;
import io.jgoerner.bvg.application.port.out.segment.DeleteAllSegments;
import io.jgoerner.bvg.domain.Segment;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
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

    private final Logger log = LoggerFactory.getLogger(InitialDataLoader.class);

    @Value("${data.directory}")
    private String DATA_DIR;

    @Value("${data.file.name}")
    private String FILE_NAME;

    @Value("${data.file.header}")
    private String RAW_HEADER;

    @Value("${data.rebuildAtStart}")
    private Boolean REBUILD_AT_START;

    @Autowired
    private CreateSegment segmentCreator;

    @Autowired
    private DeleteAllSegments segmentDeleter;

    @Override
    public void run(String... args) throws Exception {

        if (REBUILD_AT_START) {
            segmentDeleter.deleteAll();
            var lines = readFile(FILE_NAME);
            StreamSupport.stream(lines.spliterator(), false)
                    .map(record -> Segment.builder()
                            .from(record.get("from"))
                            .to(record.get("to"))
                            .line(record.get("line"))
                            .duration(Integer.valueOf(record.get("duration"))))
                    .forEach(this.segmentCreator::create);
        }
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
                .setSkipHeaderRecord(true)
                .build()
                .parse(reader);
    }
}

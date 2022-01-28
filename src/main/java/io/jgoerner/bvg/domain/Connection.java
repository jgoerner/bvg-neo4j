package io.jgoerner.bvg.domain;

import org.apache.commons.csv.CSVRecord;

public record Connection(String from, String to, String line, Integer duration) {

    public static Connection fromCSVRecord(CSVRecord record) {
        return new Connection(
                record.get("from"),
                record.get("to"),
                record.get("line"),
                Integer.valueOf(record.get("duration"))
        );
    }
}

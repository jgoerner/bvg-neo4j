package io.jgoerner.bvg.domain;

public record Segment(Station from, Station to, Line line, Integer duration) {


    public interface WithFrom {
        WithTo from(String from);

        interface WithTo {
            WithLine to(String from);

            interface WithLine {
                WithDuration line(String line);

                interface WithDuration {
                    Segment duration(Integer duration);
                }
            }
        }
    }


    public static WithFrom builder() {
        return from
                -> to
                -> line
                -> duration
                -> new Segment(new Station(from), new Station(to), Line.valueOf(line), duration);
    }


}



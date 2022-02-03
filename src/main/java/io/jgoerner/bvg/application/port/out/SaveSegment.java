package io.jgoerner.bvg.application.port.out;

import io.jgoerner.bvg.domain.Segment;

public interface SaveSegment {

    Segment save(Segment segment);

}

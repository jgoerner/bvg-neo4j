package io.jgoerner.bvg.application.port.out.segment;

import io.jgoerner.bvg.domain.Segment;

public interface SaveSegment {

    Segment save(Segment segment);

}

package io.jgoerner.bvg.application.port.in;


import io.jgoerner.bvg.domain.Segment;

public interface CreateSegment {

   Segment create(Segment segment);

}

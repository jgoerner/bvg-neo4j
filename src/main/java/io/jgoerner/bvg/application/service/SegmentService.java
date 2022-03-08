package io.jgoerner.bvg.application.service;

import io.jgoerner.bvg.application.port.in.CreateSegment;
import io.jgoerner.bvg.application.port.out.segment.SaveSegment;
import io.jgoerner.bvg.domain.Segment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SegmentService implements CreateSegment {


    @Autowired
    private SaveSegment segmentSaver;

    @Override
    public Segment create(Segment segment) {
        this.segmentSaver.save(segment);
        return segment;
    }
}

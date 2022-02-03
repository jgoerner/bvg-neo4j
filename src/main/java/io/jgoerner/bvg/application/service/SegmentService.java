package io.jgoerner.bvg.application.service;

import io.jgoerner.bvg.application.port.in.CreateSegment;
import io.jgoerner.bvg.application.port.out.SaveSegment;
import io.jgoerner.bvg.domain.Segment;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SegmentService implements CreateSegment {

    Logger log = LoggerFactory.getLogger(SegmentService.class);

    @Autowired
    private SaveSegment segmentSaver;

    @Override
    public Segment create(Segment segment) {
        this.segmentSaver.save(segment);
        return segment;
    }
}

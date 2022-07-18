package io.saliou.msscyerbaservice.model;

import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.util.List;

public class YerbaPagedList extends PageImpl<YerbaDto> {

    public YerbaPagedList(List<YerbaDto> content, Pageable pageable, long total) {
        super(content, pageable, total);
    }

    public YerbaPagedList(List<YerbaDto> content) {
        super(content);
    }
}

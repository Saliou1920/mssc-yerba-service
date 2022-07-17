package io.saliou.msscyerbaservice.model;

import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.util.List;

public class YerbaPagedList extends PageImpl<Yerba> {

    public YerbaPagedList(List<Yerba> content, Pageable pageable, long total) {
        super(content, pageable, total);
    }

    public YerbaPagedList(List<Yerba> content) {
        super(content);
    }
}

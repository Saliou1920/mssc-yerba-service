package io.saliou.msscyerbaservice.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.JsonNode;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.io.Serializable;
import java.util.List;

public class YerbaPagedList extends PageImpl<YerbaDto> implements Serializable {

    private static final long serialVersionUID = 4820107782256053179L;

    @JsonCreator(mode = JsonCreator.Mode.PROPERTIES)
    public YerbaPagedList(@JsonProperty("content") List<YerbaDto> content,
                          @JsonProperty("number") int number,
                          @JsonProperty("size") int size,
                          @JsonProperty("pageable") JsonNode pageable,
                          @JsonProperty("totalElements") long totalElements,
                          @JsonProperty("totalPages") int totalPages,
                          @JsonProperty("first") boolean first,
                          @JsonProperty("last") boolean last,
                          @JsonProperty("sort") JsonNode sort,
                          @JsonProperty("numberOfElements") int numberOfElements ) {
        super(content, PageRequest.of(number, size), totalElements);
    }

    public YerbaPagedList(List<YerbaDto> content, Pageable pageable, long total) {
        super(content, pageable, total);
    }

    public YerbaPagedList(List<YerbaDto> content) {
        super(content);
    }
}

package io.saliou.msscyerbaservice.service;

import io.saliou.msscyerbaservice.model.YerbaDto;
import io.saliou.msscyerbaservice.model.YerbaPagedList;
import io.saliou.msscyerbaservice.model.YerbaTypeEnum;
import org.springframework.data.domain.PageRequest;

import java.util.UUID;

public interface YerbaService {
    YerbaDto getYerbaById(UUID id, boolean showInventoryOnHand);

    YerbaDto createYerba(YerbaDto yerbaDto);

    YerbaDto updateYerba(UUID id, YerbaDto yerbaDto);

    YerbaPagedList listYerba(YerbaTypeEnum yerbaType, PageRequest of, Boolean showInventoryOnHand);

    YerbaDto getYerbaByUpc(String yerbaUpc, Boolean showInventoryOnHand);
}

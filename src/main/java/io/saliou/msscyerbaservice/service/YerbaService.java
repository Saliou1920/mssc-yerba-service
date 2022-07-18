package io.saliou.msscyerbaservice.service;

import io.saliou.msscyerbaservice.model.YerbaDto;

import java.util.UUID;

public interface YerbaService {
    YerbaDto getYerbaById(UUID id);

    YerbaDto createYerba(YerbaDto yerbaDto);

    YerbaDto updateYerba(UUID id, YerbaDto yerbaDto);
}

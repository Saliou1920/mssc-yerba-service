package io.saliou.msscyerbaservice.service;

import io.saliou.msscyerbaservice.model.YerbaDto;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class YerbaServiceImpl implements YerbaService {
    @Override
    public YerbaDto getYerbaById(UUID id) {
        return null;
    }

    @Override
    public YerbaDto createYerba(YerbaDto yerbaDto) {
        return null;
    }

    @Override
    public YerbaDto updateYerba(UUID id, YerbaDto yerbaDto) {
        return null;
    }
}

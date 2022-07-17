package io.saliou.msscyerbaservice.service;

import io.saliou.msscyerbaservice.model.Yerba;

import java.util.UUID;

public interface YerbaService {
    Yerba getYerbaById(UUID id);

    Yerba createYerba(Yerba yerba);

    Yerba updateYerba(UUID id, Yerba yerba);
}

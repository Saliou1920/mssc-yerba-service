package io.saliou.msscyerbaservice.service;

import io.saliou.msscyerbaservice.domain.Yerba;
import io.saliou.msscyerbaservice.errors.NotfoundException;
import io.saliou.msscyerbaservice.mappers.YerbaMapper;
import io.saliou.msscyerbaservice.model.YerbaDto;
import io.saliou.msscyerbaservice.repository.YerbaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class YerbaServiceImpl implements YerbaService {

    private final YerbaRepository yerbaRepository;
    private final YerbaMapper yerbaMapper;

    @Override
    public YerbaDto getYerbaById(UUID id) {
        return yerbaMapper.yerbaToYerbaDto(
                yerbaRepository.findById(id).orElseThrow(() -> new NotfoundException("Yerba not found"))
        );
    }

    @Override
    public YerbaDto createYerba(YerbaDto yerbaDto) {
        return yerbaMapper.yerbaToYerbaDto(
                yerbaRepository.save(yerbaMapper.yerbaDtoToYerba(yerbaDto))
        );
    }

    @Override
    public YerbaDto updateYerba(UUID id, YerbaDto yerbaDto) {
        Yerba yerba = yerbaRepository.findById(id).orElseThrow(() -> new NotfoundException("Yerba not found"));

        yerba.setName(yerbaDto.getName());
        yerba.setYerbaType(yerbaDto.getYerbaType());
        yerba.setPrice(yerbaDto.getPrice());
        yerba.setUpc(yerbaDto.getUpc());

        return yerbaMapper.yerbaToYerbaDto(yerbaRepository.save(yerba));
    }
}

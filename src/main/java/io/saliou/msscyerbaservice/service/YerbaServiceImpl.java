package io.saliou.msscyerbaservice.service;

import io.saliou.msscyerbaservice.domain.Yerba;
import io.saliou.msscyerbaservice.errors.NotfoundException;
import io.saliou.msscyerbaservice.mappers.YerbaMapper;
import io.saliou.msscyerbaservice.model.YerbaDto;
import io.saliou.msscyerbaservice.model.YerbaPagedList;
import io.saliou.msscyerbaservice.model.YerbaTypeEnum;
import io.saliou.msscyerbaservice.repository.YerbaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class YerbaServiceImpl implements YerbaService {

    private final YerbaRepository yerbaRepository;
    private final YerbaMapper yerbaMapper;

    @Cacheable(cacheNames = "yerbaCache", condition = "#showInventoryOnHand == false")
    @Override
    public YerbaDto getYerbaById(UUID id, boolean showInventoryOnHand) {
        if (showInventoryOnHand) {
            return yerbaMapper.yerbaToYerbaDtoWithInventory(yerbaRepository
                    .findById(id).orElseThrow(() -> new NotfoundException("Yerba not found"))
            );
        }
        return yerbaMapper.yerbaToYerbaDto(yerbaRepository
                .findById(id).orElseThrow(() -> new NotfoundException("Yerba not found"))
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

    @Cacheable(cacheNames = "yerbaListCache", condition = "#showInventoryOnHand == false")
    @Override
    public YerbaPagedList listYerba(YerbaTypeEnum yerbaType, PageRequest of, Boolean showInventoryOnHand) {
        YerbaPagedList yerbaPagedList;
        Page<Yerba> yerbas;
        if (yerbaType == null) {
            yerbas = yerbaRepository.findAll(of);
        } else {
            yerbas = yerbaRepository.findAllByYerbaType(yerbaType, of);
        }

        if (showInventoryOnHand) {
            return yerbaPagedList = new YerbaPagedList(yerbas.getContent()
                    .stream()
                    .map(yerbaMapper::yerbaToYerbaDtoWithInventory)
                    .collect(Collectors.toList()),
                    PageRequest.of(
                            yerbas.getPageable().getPageNumber(),
                            yerbas.getPageable().getPageSize()),
                    yerbas.getTotalElements());
        }
        return yerbaPagedList = new YerbaPagedList(yerbas.getContent()
                .stream()
                .map(yerbaMapper::yerbaToYerbaDto)
                .collect(Collectors.toList()),
                PageRequest.of(
                        yerbas.getPageable().getPageNumber(),
                        yerbas.getPageable().getPageSize()),
                yerbas.getTotalElements());
    }

    @Override
    public YerbaDto getYerbaByUpc(String yerbaUpc, Boolean showInventoryOnHand) {
        if (showInventoryOnHand) {
            return yerbaMapper.yerbaToYerbaDtoWithInventory(yerbaRepository
                    .findByUpc(yerbaUpc).orElseThrow(() -> new NotfoundException("Yerba not found"))
            );
        }
        return yerbaMapper.yerbaToYerbaDto(yerbaRepository
                .findByUpc(yerbaUpc).orElseThrow(() -> new NotfoundException("Yerba not found"))
        );
    }
}

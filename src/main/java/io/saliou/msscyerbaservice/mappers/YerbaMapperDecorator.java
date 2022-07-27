package io.saliou.msscyerbaservice.mappers;

import io.saliou.msscyerbaservice.domain.Yerba;
import io.saliou.msscyerbaservice.model.YerbaDto;
import io.saliou.msscyerbaservice.service.inventory.YerbaInventoryService;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public abstract class YerbaMapperDecorator implements YerbaMapper {
    private final YerbaMapper yerbaMapper;
    private final YerbaInventoryService yerbaInventoryService;

    @Override
    public YerbaDto yerbaToYerbaDto(Yerba yerba) {
        return yerbaMapper.yerbaToYerbaDto(yerba);
    }

    @Override
    public Yerba yerbaDtoToYerba(YerbaDto yerbaDto) {
        return yerbaMapper.yerbaDtoToYerba(yerbaDto);
    }

    @Override
    public YerbaDto yerbaToYerbaDtoWithInventory(Yerba yerba) {
        YerbaDto yerbaDto = yerbaMapper.yerbaToYerbaDto(yerba);
        yerbaDto.setQuantity(yerbaInventoryService.getOnHandInventory(yerba.getId()));
        return yerbaDto;
    }
}

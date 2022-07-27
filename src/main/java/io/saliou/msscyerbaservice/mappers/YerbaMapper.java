package io.saliou.msscyerbaservice.mappers;

import io.saliou.msscyerbaservice.domain.Yerba;
import io.saliou.msscyerbaservice.model.YerbaDto;
import org.mapstruct.Mapper;

@Mapper(uses = {DateMapper.class}, componentModel = "spring")
public interface YerbaMapper {

        YerbaDto yerbaToYerbaDto(Yerba yerba);
        YerbaDto yerbaToYerbaDtoWithInventory(Yerba yerba);

        Yerba yerbaDtoToYerba(YerbaDto yerbaDto);

}

package io.saliou.msscyerbaservice.mappers;

import io.saliou.msscyerbaservice.domain.Yerba;
import io.saliou.msscyerbaservice.model.YerbaDto;
import org.mapstruct.Mapper;

@Mapper
public interface YerbaMapper {

        YerbaDto yerbaToYerbaDto(Yerba yerba);

        Yerba yerbaDtoToYerba(YerbaDto yerbaDto);

}

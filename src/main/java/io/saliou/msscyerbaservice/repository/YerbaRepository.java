package io.saliou.msscyerbaservice.repository;

import io.saliou.msscyerbaservice.domain.Yerba;
import io.saliou.msscyerbaservice.model.YerbaTypeEnum;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.UUID;

public interface YerbaRepository extends PagingAndSortingRepository<Yerba, UUID> {
    Page<Yerba> findAllByYerbaType(YerbaTypeEnum yerbaType, Pageable pageable);
}

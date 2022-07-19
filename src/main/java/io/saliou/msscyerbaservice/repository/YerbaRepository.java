package io.saliou.msscyerbaservice.repository;

import io.saliou.msscyerbaservice.domain.Yerba;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.UUID;

public interface YerbaRepository extends PagingAndSortingRepository<Yerba, UUID> {
}

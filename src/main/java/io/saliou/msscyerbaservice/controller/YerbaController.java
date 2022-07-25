package io.saliou.msscyerbaservice.controller;

import io.saliou.msscyerbaservice.model.YerbaDto;
import io.saliou.msscyerbaservice.model.YerbaPagedList;
import io.saliou.msscyerbaservice.model.YerbaTypeEnum;
import io.saliou.msscyerbaservice.service.YerbaService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.UUID;

@Valid
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/yerba")
public class YerbaController {

    private static final Integer DEFAULT_PAGE_SIZE = 25;
    private static final Integer DEFAULT_PAGE_NUMBER = 1;
    private final YerbaService yerbaService;

    @GetMapping("/")
    public ResponseEntity<YerbaPagedList> listYerba(
            @RequestParam(value = "pageSize", required = false ) Integer pageSize,
            @RequestParam(value = "pageNumber", required = false ) Integer pageNumber,
            @RequestParam(value = "yerbaType", required = false ) YerbaTypeEnum yerbaType){
        if (pageSize == null || pageSize < 1) {
            pageSize = DEFAULT_PAGE_SIZE;
        }
        if (pageNumber == null || pageNumber < 1) {
            pageNumber = DEFAULT_PAGE_NUMBER;
        }
        return ResponseEntity.ok(yerbaService.listYerba(yerbaType, PageRequest.of(pageNumber, pageSize)));
    }

    @GetMapping("{id}")
    public ResponseEntity<YerbaDto> getYerbaById(@NotNull @PathVariable UUID id) {
        return ResponseEntity.ok(yerbaService.getYerbaById(id));
    }

    @PostMapping
    public ResponseEntity<YerbaDto> createYerba(@Valid @RequestBody YerbaDto yerbaDto) {
        return ResponseEntity.ok(yerbaService.createYerba(yerbaDto));
    }

    @PutMapping("{id}")
    public ResponseEntity<YerbaDto> updateYerba(@PathVariable UUID id, @Valid @RequestBody YerbaDto yerbaDto) {
        return ResponseEntity.ok(yerbaService.updateYerba(id, yerbaDto));
    }
}

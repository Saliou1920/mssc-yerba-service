package io.saliou.msscyerbaservice.controller;

import io.saliou.msscyerbaservice.model.YerbaDto;
import io.saliou.msscyerbaservice.service.YerbaService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
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
    private final YerbaService yerbaService;

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

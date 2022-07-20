package io.saliou.msscyerbaservice.controller;

import io.saliou.msscyerbaservice.model.YerbaDto;
import io.saliou.msscyerbaservice.service.YerbaService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@Valid
@RestController
@RequestMapping("/api/v1/yerba")
public class YerbaController {

    private final YerbaService yerbaService;

    @Autowired
    public YerbaController(YerbaService yerbaService) {
        this.yerbaService = yerbaService;
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

package io.saliou.msscyerbaservice.controller;

import io.saliou.msscyerbaservice.model.YerbaDto;
import io.saliou.msscyerbaservice.service.YerbaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1/yerba")
public class YerbaController {

    private final YerbaService yerbaService;

    @Autowired
    public YerbaController(YerbaService yerbaService) {
        this.yerbaService = yerbaService;
    }

    @GetMapping("{id}")
    public ResponseEntity<YerbaDto> getYerbaById(@PathVariable UUID id) {
        return ResponseEntity.ok(yerbaService.getYerbaById(id));
    }

    @PostMapping
    public ResponseEntity<YerbaDto> createYerba(@RequestBody YerbaDto yerbaDto) {
        return ResponseEntity.ok(yerbaService.createYerba(yerbaDto));
    }

    @PutMapping("{id}")
    public ResponseEntity<YerbaDto> updateYerba(@PathVariable UUID id, @RequestBody YerbaDto yerbaDto) {
        return ResponseEntity.ok(yerbaService.updateYerba(id, yerbaDto));
    }
}

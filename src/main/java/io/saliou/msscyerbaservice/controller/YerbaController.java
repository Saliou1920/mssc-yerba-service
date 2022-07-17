package io.saliou.msscyerbaservice.controller;

import io.saliou.msscyerbaservice.model.Yerba;
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
    public ResponseEntity<Yerba> getYerbaById(@PathVariable UUID id) {
        return ResponseEntity.ok(yerbaService.getYerbaById(id));
    }

    @PostMapping
    public ResponseEntity<Yerba> createYerba(@RequestBody Yerba yerba) {
        return ResponseEntity.ok(yerbaService.createYerba(yerba));
    }

    @PutMapping("{id}")
    public ResponseEntity<Yerba> updateYerba(@PathVariable UUID id, @RequestBody Yerba yerba) {
        return ResponseEntity.ok(yerbaService.updateYerba(id, yerba));
    }
}

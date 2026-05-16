package com.example.alloservice.controller;


import com.example.alloservice.dto.*;
import com.example.alloservice.service.OffreService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/offres")
@RequiredArgsConstructor
public class OffreController {

    private final OffreService offreService;

    @PostMapping("/user/{userId}")
    public ResponseEntity<OffreDto> create(@PathVariable Long userId,
                                           @Valid @RequestBody OffreRequest req) {
        return ResponseEntity.status(HttpStatus.CREATED).body(offreService.create(req, userId));
    }

    @GetMapping
    public ResponseEntity<List<OffreDto>> getAll() {
        return ResponseEntity.ok(offreService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<OffreDto> getById(@PathVariable Long id) {
        return ResponseEntity.ok(offreService.getById(id));
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<OffreDto>> getByUser(@PathVariable Long userId) {
        return ResponseEntity.ok(offreService.getByUser(userId));
    }

    @GetMapping("/category/{categoryId}")
    public ResponseEntity<List<OffreDto>> getByCategory(@PathVariable Long categoryId) {
        return ResponseEntity.ok(offreService.getByCategory(categoryId));
    }

    @GetMapping("/search")
    public ResponseEntity<List<OffreDto>> search(@RequestParam String keyword) {
        return ResponseEntity.ok(offreService.search(keyword));
    }

    @PutMapping("/{id}")
    public ResponseEntity<OffreDto> update(@PathVariable Long id,
                                           @Valid @RequestBody OffreRequest req) {
        return ResponseEntity.ok(offreService.update(id, req));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        offreService.delete(id);
        return ResponseEntity.noContent().build();
    }
}

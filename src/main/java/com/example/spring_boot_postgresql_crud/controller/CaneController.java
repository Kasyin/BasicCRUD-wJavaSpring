package com.example.spring_boot_postgresql_crud.controller;

import com.example.spring_boot_postgresql_crud.model.CaneDTO;
import com.example.spring_boot_postgresql_crud.service.CaneService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/cani")
public class CaneController {

    private final CaneService caneService;

    public CaneController(CaneService caneService) {
        this.caneService = caneService;
    }

    @GetMapping
    public List<CaneDTO> getAllCani() {
        return caneService.getAllCani();
    }

    @GetMapping("/{id}")
    public ResponseEntity<CaneDTO> getCaneById(@PathVariable Long id) {
        Optional<CaneDTO> cane = caneService.getCaneById(id);
        return cane.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public CaneDTO createCane(@RequestBody CaneDTO caneDTO) {
        System.out.println(caneDTO.toString());
        return caneService.saveCane(caneDTO);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CaneDTO> updateCane(@PathVariable Long id, @RequestBody CaneDTO caneDTO) {
        try {
            CaneDTO updatedCane = caneService.updateCane(id, caneDTO);
            return ResponseEntity.ok(updatedCane);
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<CaneDTO> deleteCane(@PathVariable Long id) {
        caneService.deleteCane(id);
        return ResponseEntity.noContent().build();
    }
}

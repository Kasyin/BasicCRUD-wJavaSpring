package com.example.spring_boot_postgresql_crud.controller;

import com.example.spring_boot_postgresql_crud.response.CaneResponseHandler;

import com.example.spring_boot_postgresql_crud.model.Cane;
import com.example.spring_boot_postgresql_crud.model.CaneDTO;
import com.example.spring_boot_postgresql_crud.service.CaneService;
import jakarta.servlet.http.HttpSession;
import org.springframework.http.HttpStatus;
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
    public ResponseEntity<Object> getAllCani(HttpSession session) {
        List<CaneDTO> cani = caneService.getAllCani();
        return CaneResponseHandler.handleCaneResponse(cani, HttpStatus.OK, session);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getCaneById(@PathVariable Long id, HttpSession session) {
        Optional<CaneDTO> cane = caneService.getCaneById(id);
        if (cane.isEmpty()) { return CaneResponseHandler.handleCaneResponse((CaneDTO)null, HttpStatus.NOT_FOUND, session); }
        return CaneResponseHandler.handleCaneResponse(cane.get(), HttpStatus.OK, session);
    }

    @PostMapping
    public CaneDTO createCane(@RequestBody CaneDTO caneDTO) {
        return caneService.saveCane(caneDTO);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> updateCane(@PathVariable Long id, @RequestBody CaneDTO caneDTO, HttpSession session) {
        try {
            CaneDTO updatedCane = caneService.updateCane(id, caneDTO);
            return CaneResponseHandler.handleCaneResponse(updatedCane, HttpStatus.OK, session);
        } catch (Exception e) {
            return CaneResponseHandler.handleCaneResponse((CaneDTO)null, HttpStatus.NOT_FOUND, session);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteCane(@PathVariable Long id, HttpSession session) {
        try {
            caneService.deleteCane(id);
            return CaneResponseHandler.handleCaneResponse((CaneDTO)null, HttpStatus.OK, session);
        } catch (Exception e) {
            return CaneResponseHandler.handleCaneResponse((CaneDTO)null, HttpStatus.NOT_FOUND, session);
        }
    }
}

package com.example.spring_boot_postgresql_crud.service;

import com.example.spring_boot_postgresql_crud.model.CaneDTO;

import java.util.List;
import java.util.Optional;

public interface CaneService {
    List<CaneDTO> getAllCani();
    Optional<CaneDTO> getCaneById(Long id);
    CaneDTO saveCane(CaneDTO caneDTO);
    CaneDTO updateCane(Long id, CaneDTO caneDTO);
    void deleteCane(Long id);
}

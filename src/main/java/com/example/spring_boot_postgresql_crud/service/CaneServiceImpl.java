package com.example.spring_boot_postgresql_crud.service;

import com.example.spring_boot_postgresql_crud.model.Cane;
import com.example.spring_boot_postgresql_crud.model.CaneDTO;
import com.example.spring_boot_postgresql_crud.repository.CaneRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CaneServiceImpl implements CaneService {
    private final CaneRepository caneRepository;

    public CaneServiceImpl(CaneRepository caneRepository) {
        this.caneRepository = caneRepository;
    }

    public List<CaneDTO> getAllCani() {
        return caneRepository.findAll().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public Optional<CaneDTO> getCaneById(Long id) {
        return caneRepository.findById(id).map(this::convertToDTO);
    }

    public CaneDTO saveCane(CaneDTO caneDTO) {
        Cane cane = convertToEntity(caneDTO);
        Cane savedCane = caneRepository.save(cane);
        return convertToDTO(savedCane);
    }

    public CaneDTO updateCane(Long id, CaneDTO caneDTO) {
        Cane cane = caneRepository.findById(id).orElseThrow();
        cane.setName(caneDTO.name());
        Cane updatedCane = caneRepository.save(cane);
        return convertToDTO(updatedCane);
    }

    public void deleteCane(Long id) {
        caneRepository.deleteById(id);
    }

    private CaneDTO convertToDTO(Cane cane) {
        return new CaneDTO(cane.getId(), cane.getName());
    }

    private Cane convertToEntity(CaneDTO caneDTO) {
        Cane cane = new Cane();
        cane.setName(caneDTO.name());
        return cane;

    }
}

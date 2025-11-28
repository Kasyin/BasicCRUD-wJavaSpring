package com.example.spring_boot_postgresql_crud.response;

import com.example.spring_boot_postgresql_crud.model.Cane;
import com.example.spring_boot_postgresql_crud.model.CaneDTO;
import jakarta.servlet.http.HttpSession;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CaneResponseHandler {
    public static ResponseEntity<Object> handleCaneResponse(List<CaneDTO> cani, HttpStatus status, HttpSession session) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("status", status);
        map.put("user", session.getAttribute("user"));
        map.put("cani", cani);
        return new ResponseEntity<Object>(map, status);
    }

    public static ResponseEntity<Object> handleCaneResponse(CaneDTO cane, HttpStatus status, HttpSession session) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("status", status);
        map.put("user", session.getAttribute("user"));
        map.put("cane", cane);
        return new ResponseEntity<Object>(map, status);
    }
}

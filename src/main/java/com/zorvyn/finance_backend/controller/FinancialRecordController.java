package com.zorvyn.finance_backend.controller;

import com.zorvyn.finance_backend.model.*;
import com.zorvyn.finance_backend.service.FinancialRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.zorvyn.finance_backend.model.Role;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/records")
public class FinancialRecordController {

    @Autowired
    private FinancialRecordService service;

    @PostMapping
    public FinancialRecord create(
            @RequestBody FinancialRecord record,
            @RequestParam Role role) {

        if (role != Role.ADMIN) {
            throw new RuntimeException("Only ADMIN can create records");
        }

        return service.createRecord(record);
    }
    @PutMapping("/{id}")
    public FinancialRecord update(
            @PathVariable Long id,
            @RequestBody FinancialRecord record,
            @RequestParam Role role) {

        if (role != Role.ADMIN) {
            throw new RuntimeException("Only ADMIN can update records");
        }

        return service.updateRecord(id, record);
    }
    @DeleteMapping("/{id}")
    public void delete(
            @PathVariable Long id,
            @RequestParam Role role) {

        if (role != Role.ADMIN) {
            throw new RuntimeException("Only ADMIN can delete records");
        }

        service.deleteRecord(id);
    }

    @GetMapping
    public List<FinancialRecord> getAll(@RequestParam Role role) {
        return service.getAllRecords();
    }

    @GetMapping("/type")
    public List<FinancialRecord> getByType(@RequestParam RecordType type) {
        return service.getByType(type);
    }

    @GetMapping("/category")
    public List<FinancialRecord> getByCategory(@RequestParam String category) {
        return service.getByCategory(category);
    }

   @GetMapping("/summary")
    public Map<String, Object> getSummary(@RequestParam Role role) {

        if (role == Role.VIEWER) {
            throw new RuntimeException("VIEWER cannot access summary");
        }

        return service.getSummary();
    }
    
}
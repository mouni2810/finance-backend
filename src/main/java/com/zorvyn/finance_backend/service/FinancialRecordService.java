package com.zorvyn.finance_backend.service;

import com.zorvyn.finance_backend.model.*;
import com.zorvyn.finance_backend.repository.FinancialRecordRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class FinancialRecordService {

    @Autowired
    private FinancialRecordRepository repository;

    public FinancialRecord createRecord(FinancialRecord record) {
        return repository.save(record);
    }
    public FinancialRecord updateRecord(Long id, FinancialRecord updated) {
        FinancialRecord record = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Record not found"));

        record.setAmount(updated.getAmount());
        record.setCategory(updated.getCategory());
        record.setType(updated.getType());
        record.setDate(updated.getDate());
        record.setDescription(updated.getDescription());

        return repository.save(record);
    }
    public void deleteRecord(Long id) {
        FinancialRecord record = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Record not found"));

        repository.delete(record);
    }
    public List<FinancialRecord> getAllRecords() {
        return repository.findAll();
    }

    public List<FinancialRecord> getByType(RecordType type) {
        return repository.findByType(type);
    }

    public List<FinancialRecord> getByCategory(String category) {
        return repository.findByCategory(category);
    }

    public Map<String, Object> getSummary() {
        List<FinancialRecord> records = repository.findAll();

        double income = 0;
        double expense = 0;

        Map<String, Double> categoryMap = new HashMap<>();

        for (FinancialRecord r : records) {
            if (r.getType() == RecordType.INCOME) {
                income += r.getAmount();
            } else {
                expense += r.getAmount();
            }

            categoryMap.put(
                r.getCategory(),
                categoryMap.getOrDefault(r.getCategory(), 0.0) + r.getAmount()
            );
        }

        Map<String, Object> result = new HashMap<>();
        result.put("totalIncome", income);
        result.put("totalExpense", expense);
        result.put("netBalance", income - expense);
        result.put("categoryWise", categoryMap);

        return result;
    }
}
package com.zorvyn.finance_backend.repository;

import com.zorvyn.finance_backend.model.FinancialRecord;
import com.zorvyn.finance_backend.model.RecordType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FinancialRecordRepository extends JpaRepository<FinancialRecord, Long> {

    List<FinancialRecord> findByType(RecordType type);

    List<FinancialRecord> findByCategory(String category);
}
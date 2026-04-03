package backendassignment.example.myassignment.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import backendassignment.example.myassignment.Entity.FinancialRecord;

import java.util.List;

public interface RecordRepository extends JpaRepository<FinancialRecord, Long> {

    List<FinancialRecord> findByType(String type);
}
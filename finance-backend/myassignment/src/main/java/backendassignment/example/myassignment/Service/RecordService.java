package backendassignment.example.myassignment.Service;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import backendassignment.example.myassignment.Entity.FinancialRecord;
import backendassignment.example.myassignment.repository.RecordRepository;

import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

@Service
public class RecordService {

    @Autowired
    private RecordRepository repo;

    public FinancialRecord save(FinancialRecord record){
        return repo.save(record);
    }

    public List<FinancialRecord> getAll(){
        return repo.findAll();
    }

    public List<FinancialRecord> getByType(String type){
        return repo.findByType(type);
    }

    public FinancialRecord update(Long id, FinancialRecord recordDetails) {
        FinancialRecord record = repo.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Record not found"));
        record.setAmount(recordDetails.getAmount());
        record.setType(recordDetails.getType());
        record.setCategory(recordDetails.getCategory());
        record.setDate(recordDetails.getDate());
        record.setDescription(recordDetails.getDescription());
        return repo.save(record);
    }

    public void delete(Long id) {
        if (!repo.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Record not found");
        }
        repo.deleteById(id);
    }
}
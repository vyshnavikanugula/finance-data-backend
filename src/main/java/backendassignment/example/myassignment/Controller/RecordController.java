package backendassignment.example.myassignment.Controller;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

        import backendassignment.example.myassignment.Entity.FinancialRecord;
import backendassignment.example.myassignment.Service.RecordService;



import java.util.List;
import java.util.Map;
import java.util.HashMap;
import jakarta.validation.Valid;
import backendassignment.example.myassignment.Exception.AccessDeniedException;

@RestController
@RequestMapping("/records")
public class RecordController {

    @Autowired
    private RecordService service;

    //  CREATE RECORD
    @PostMapping
    public FinancialRecord create(
            @RequestHeader(value = "X-ROLE", defaultValue = "VIEWER") String role,
            @Valid @RequestBody FinancialRecord record){

        // 🔒 Role-based access
        if("VIEWER".equalsIgnoreCase(role) || "ANALYST".equalsIgnoreCase(role)){
            throw new AccessDeniedException("Only admin can create records");
        }

        return service.save(record);
    }

    // GET ALL RECORDS
    @GetMapping
    public List<FinancialRecord> getAll(@RequestHeader(value = "X-ROLE", defaultValue = "VIEWER") String role){
        if("VIEWER".equalsIgnoreCase(role)){
            throw new AccessDeniedException("Viewers cannot view individual records");
        }
        return service.getAll();
    }

    // FILTER BY TYPE
    @GetMapping("/type")
    public List<FinancialRecord> getByType(
            @RequestHeader(value = "X-ROLE", defaultValue = "VIEWER") String role,
            @RequestParam String type){
        if("VIEWER".equalsIgnoreCase(role)){
            throw new AccessDeniedException("Viewers cannot view individual records");
        }
        return service.getByType(type);
    }

    // UPDATE RECORD
    @PutMapping("/{id}")
    public FinancialRecord update(
            @RequestHeader(value = "X-ROLE", defaultValue = "VIEWER") String role,
            @PathVariable Long id,
            @Valid @RequestBody FinancialRecord recordDetails) {

        if (!"ADMIN".equalsIgnoreCase(role)) {
            throw new AccessDeniedException("Only admin can update records");
        }

        return service.update(id, recordDetails);
    }

    // DELETE RECORD
    @DeleteMapping("/{id}")
    public void delete(
            @RequestHeader(value = "X-ROLE", defaultValue = "VIEWER") String role,
            @PathVariable Long id) {

        if (!"ADMIN".equalsIgnoreCase(role)) {
            throw new AccessDeniedException("Only admin can delete records");
        }

        service.delete(id);
    }

    // DASHBOARD SUMMARY
    @GetMapping("/summary")
    public Map<String, Double> getSummary(@RequestHeader(value = "X-ROLE", defaultValue = "VIEWER") String role){
        // Everyone (VIEWER, ANALYST, ADMIN) can view summary
        List<FinancialRecord> records = service.getAll();

        double income = records.stream()
                .filter(r -> "INCOME".equalsIgnoreCase(r.getType()))
                .mapToDouble(FinancialRecord::getAmount)
                .sum();

        double expense = records.stream()
                .filter(r -> "EXPENSE".equalsIgnoreCase(r.getType()))
                .mapToDouble(FinancialRecord::getAmount)
                .sum();

        Map<String, Double> result = new HashMap<>();
        result.put("totalIncome", income);
        result.put("totalExpense", expense);
        result.put("netBalance", income - expense);

        return result;
    }
}
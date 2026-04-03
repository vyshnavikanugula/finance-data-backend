package backendassignment.example.myassignment.Service;




import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import backendassignment.example.myassignment.Entity.User;
import backendassignment.example.myassignment.repository.UserRepository;

import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

@Service
public class UserService {

    @Autowired
    private UserRepository repo;

    public User save(User user){
        return repo.save(user);
    }

    public List<User> getAll(){
        return repo.findAll();
    }

    public User update(Long id, User userDetails) {
        User user = repo.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found"));
        user.setName(userDetails.getName());
        user.setEmail(userDetails.getEmail());
        user.setRole(userDetails.getRole());
        user.setStatus(userDetails.getStatus());
        return repo.save(user);
    }

    public void delete(Long id) {
        if (!repo.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found");
        }
        repo.deleteById(id);
    }
}
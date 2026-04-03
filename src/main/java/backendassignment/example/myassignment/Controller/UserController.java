package backendassignment.example.myassignment.Controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import backendassignment.example.myassignment.Entity.User;
import backendassignment.example.myassignment.Service.UserService;
import backendassignment.example.myassignment.Exception.AccessDeniedException;
import jakarta.validation.Valid;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService service;

    // CREATE USER (ADMIN ONLY)
    @PostMapping
    public User create(
            @RequestHeader(value = "X-ROLE", defaultValue = "VIEWER") String role,
            @Valid @RequestBody User user){

        if(!"ADMIN".equalsIgnoreCase(role)){
            throw new AccessDeniedException("Only admin can create users");
        }

        return service.save(user);
    }

    // GET USERS (ADMIN/ANALYST)
    @GetMapping
    public List<User> getAll(@RequestHeader(value = "X-ROLE", defaultValue = "VIEWER") String role){
        if("VIEWER".equalsIgnoreCase(role)){
            throw new AccessDeniedException("Viewers cannot view user list");
        }
        return service.getAll();
    }

    // UPDATE USER (ADMIN ONLY)
    @PutMapping("/{id}")
    public User update(
            @RequestHeader(value = "X-ROLE", defaultValue = "VIEWER") String role,
            @PathVariable Long id,
            @Valid @RequestBody User userDetails) {

        if (!"ADMIN".equalsIgnoreCase(role)) {
            throw new AccessDeniedException("Only admin can update users");
        }

        return service.update(id, userDetails);
    }

    // DELETE USER (ADMIN ONLY)
    @DeleteMapping("/{id}")
    public void delete(
            @RequestHeader(value = "X-ROLE", defaultValue = "VIEWER") String role,
            @PathVariable Long id) {

        if (!"ADMIN".equalsIgnoreCase(role)) {
            throw new AccessDeniedException("Only admin can delete users");
        }

        service.delete(id);
    }
}

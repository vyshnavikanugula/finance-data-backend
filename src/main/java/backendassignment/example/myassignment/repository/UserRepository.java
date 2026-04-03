package backendassignment.example.myassignment.repository;

import backendassignment.example.myassignment.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}



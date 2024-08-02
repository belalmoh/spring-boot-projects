package project.library.data.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import project.library.data.entities.User;

public interface UserRepository extends JpaRepository<User, Long> {
}

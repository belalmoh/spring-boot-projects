package project.library.data.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import project.library.data.entities.Book;

public interface BookRepository extends JpaRepository<Book, Long> {
}

package project.library.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import project.library.data.entities.Book;
import project.library.data.entities.User;
import project.library.data.repositories.BookRepository;
import project.library.data.repositories.UserRepository;

import java.util.List;

@Service
public class BookService {

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private UserRepository userRepository;

    public List<Book> findAll() {
        return this.bookRepository.findAll();
    }

    public Book findById(Long id) {
        return this.bookRepository.findById(id).orElse(null);
    }

    public Book save(Book book) {
        return this.bookRepository.save(book);
    }

    public void deleteById(Long id) {
        this.bookRepository.deleteById(id);
    }

    public Book burrowBook(Long bookId, Long userId) {
        Book book = this.bookRepository.findById(bookId).orElse(null);
        User user = this.userRepository.findById(userId).orElse(null);
        if (book != null && user != null && !book.isBorrowed()) {
            book.setBorrowedBy(user);
            book.setBorrowed(true);
            return this.bookRepository.save(book);
        }
        return null;
    }

    public Book returnBook(Long bookId) {
        Book book = this.bookRepository.findById(bookId).orElse(null);
        if (book != null && book.isBorrowed()) {
            book.setBorrowedBy(null);
            book.setBorrowed(false);
            return this.bookRepository.save(book);
        }
        return null;
    }
}

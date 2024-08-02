package project.library.web.api;

import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import project.library.service.BookService;

import project.library.data.entities.Book;
import java.util.List;

@RestController
@RequestMapping("/api/books")
public class BookApi {

    @Autowired
    private BookService bookService;

    @GetMapping
    public List<Book> findAll() {
        return this.bookService.findAll();
    }

    @GetMapping("/{id}")
    public Book findById(@PathVariable  Long id) {
        return this.bookService.findById(id);
    }

    @PostMapping
    public Book save(@RequestBody Book book) {
        return this.bookService.save(book);
    }

    @PutMapping("/{id}/borrow/{userId}")
    public ResponseEntity<Book> borrowBook(@PathVariable Long id, @PathVariable Long userId) {
        Book borrowedBook = this.bookService.burrowBook(id, userId);
        if(borrowedBook != null) {
            return ResponseEntity.ok(borrowedBook);
        } else {
            return ResponseEntity.badRequest().build();
        }
    }

    @PutMapping("/{id}/return")
    public ResponseEntity<Book> returnBook(Long id) {
        Book returnedBook = this.bookService.returnBook(id);
        if(returnedBook != null) {
            return ResponseEntity.ok(returnedBook);
        } else {
            return ResponseEntity.badRequest().build();
        }
    }

    @PutMapping("/{id}")
    public Book update(@PathVariable Long id, @RequestBody Book book) {
        Book existingBook = this.bookService.findById(id);
        if(existingBook == null) {
            return null;
        }

        existingBook.setBorrowed(book.isBorrowed());
        existingBook.setBorrowedBy(book.getBorrowedBy());
        existingBook.setAuthor(book.getAuthor());
        existingBook.setTitle(book.getTitle());

        return this.bookService.save(existingBook);
    }

    @DeleteMapping("/{id}")
    public void deleteById(Long id) {
        this.bookService.deleteById(id);
    }
}

package servc.books.controller;

import jakarta.validation.constraints.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import servc.books.model.BookDTO;
import servc.books.service.BookService;

import java.util.Arrays;
import java.util.List;

@RestController
@Validated
@RequestMapping("/api/v1/books")
public class BookController {

    @Autowired
    BookService bookService;

    @GetMapping
    public List<BookDTO> getAllBooks() {
        return bookService.GetAllBooks();
    }

    @GetMapping(params = {"take", "skip"})
    public List<BookDTO> getPartOfBooks(@NotNull Integer take, @NotNull Integer skip) {
        return bookService.GetPartOfBooks(take, skip);
    }

    @GetMapping(params = {"sort"})
    public List<BookDTO> getSortedBooks(@NotNull String[] sort){
        return bookService.GetSortedBooks(Arrays.stream(sort).toList());
    }

    @GetMapping("/{id}")
    public ResponseEntity<BookDTO> getBookById(@PathVariable Integer id){
        return ResponseEntity.ok(bookService.GetBookByID(id));
    }

    @PostMapping
    public ResponseEntity<BookDTO> addBook(@RequestBody BookDTO book){
        return ResponseEntity.ok(bookService.AddBook(book));
    }

    @PutMapping("/{id}")
    public ResponseEntity<BookDTO> updateBookByID(@PathVariable Integer id, @RequestBody BookDTO book){
        return ResponseEntity.ok(bookService.UpdateBook(id, book));
    }

    @DeleteMapping("/{id}")
    public void deleteBookByID(@PathVariable Integer id){
        bookService.DeleteBookByID(id);
    }
}

package servc.books.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import servc.books.model.Book;
import servc.books.service.BookService;

import java.util.List;

@RestController
@RequestMapping("/api/v1/books")
public class BookController {

    @Autowired
    BookService bookService;

    @GetMapping
    public List<Book> GetAllBooks() {
        return bookService.GetAllBooks();
    }

    @GetMapping(params = {"take", "skip"})
    public List<Book> GetPartOfBooks(@RequestParam int take, @RequestParam int skip) {
        return bookService.GetPartOfBooks(take, skip);
    }

    @GetMapping(params = {"sort"})
    public List<Book> GetSortedBooks(@RequestParam List<String> sort){
        return bookService.GetSortedBooks(sort);
    }

    @GetMapping("/{id}")
    public Book GetBookById(@PathVariable Integer id){
        return bookService.GetBookByID(id);
    }

    @PostMapping
    public Book AddBook(@RequestBody Book book){
        return bookService.AddBook(book);
    }

    @PutMapping("/{id}")
    public Book UpdateBookByID(@PathVariable Integer id, @RequestBody Book book){
        return bookService.UpdateBook(id, book);
    }

    @DeleteMapping("/{id}")
    public void DeleteBookByID(@PathVariable Integer id){
        bookService.DeleteBookByID(id);
    }
}

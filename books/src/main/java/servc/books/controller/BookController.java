package servc.books.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import servc.books.model.BookDTO;
import servc.books.service.BookService;

import java.util.List;

@RestController
@RequestMapping("/api/v1/books")
public class BookController {

    @Autowired
    BookService bookService;

    @GetMapping
    public List<BookDTO> GetAllBooks() {
        return bookService.GetAllBooks();
    }

    @GetMapping(params = {"take", "skip"})
    public List<BookDTO> GetPartOfBooks(@RequestParam int take, @RequestParam int skip) {
        return bookService.GetPartOfBooks(take, skip);
    }

    @GetMapping(params = {"sort"})
    public List<BookDTO> GetSortedBooks(@RequestParam List<String> sort){
        return bookService.GetSortedBooks(sort);
    }

    @GetMapping("/{id}")
    public BookDTO GetBookById(@PathVariable Integer id){
        return bookService.GetBookByID(id);
    }

    @PostMapping
    public BookDTO AddBook(@RequestBody BookDTO book){
        return bookService.AddBook(book);
    }

    @PutMapping("/{id}")
    public BookDTO UpdateBookByID(@PathVariable Integer id, @RequestBody BookDTO book){
        return bookService.UpdateBook(id, book);
    }

    @DeleteMapping("/{id}")
    public void DeleteBookByID(@PathVariable Integer id){
        bookService.DeleteBookByID(id);
    }
}

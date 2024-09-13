package servc.books.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import servc.books.model.tbl_Books;
import servc.books.service.BookService;

import java.util.List;

@RestController
@RequestMapping("/api/v1/books")
public class BookController {

    @Autowired
    BookService bookService;

    @GetMapping
    public List<tbl_Books> GetAllBooks() {
        return bookService.GetAllBooks();
    }

    @GetMapping(params = {"take", "skip"})
    public List<tbl_Books> GetPartOfBooks(@RequestParam int take, @RequestParam int skip) {
        return bookService.GetPartOfBooks(take, skip);
    }

    @GetMapping(params = {"sort"})
    public List<tbl_Books> GetSortedBooks(@RequestParam List<String> sort){
        return bookService.GetSortedBooks(sort);
    }

    @GetMapping("/{id}")
    public tbl_Books GetBookById(@PathVariable Integer id){
        return bookService.GetBookByID(id);
    }

    @PostMapping
    public tbl_Books AddBook(@RequestBody tbl_Books book){
        return bookService.AddBook(book);
    }

    @PutMapping("/{id}")
    public tbl_Books UpdateBookByID(@PathVariable Integer id, @RequestBody tbl_Books book){
        return bookService.UpdateBook(id, book);
    }

    @DeleteMapping("/{id}")
    public void DeleteBookByID(@PathVariable Integer id){
        bookService.DeleteBookByID(id);
    }
}

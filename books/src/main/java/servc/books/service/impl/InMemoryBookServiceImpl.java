package servc.books.service.impl;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import servc.books.model.Book;
import servc.books.repository.InMemoryBookDAO;
import servc.books.service.BookService;

import java.util.List;
import java.util.stream.IntStream;

@Service
@AllArgsConstructor
public class InMemoryBookServiceImpl implements BookService {
    private final InMemoryBookDAO repo;

    public List<Book> GetAllBooks(){
        return repo.GetAllBooks();
    }

    public List<Book> GetPartOfBooks(int take, int skip){
        return repo.GetPartOfBooks(take, skip);
    }
    public List<Book> GetSortedBooks(List<String> sort){
        return repo.GetSortedBooks(sort);
    }

    public Book GetBookByID(Integer id){
        return repo.GetBookByID(id);
    }
    public Book AddBook(Book book) {
        return repo.AddBook(book);
    }

    public Book UpdateBook(Integer id, Book book) {
        return repo.UpdateBook(id, book);
    }

    public void DeleteBookByID(Integer id) {
        repo.DeleteBookByID(id);
    }
}
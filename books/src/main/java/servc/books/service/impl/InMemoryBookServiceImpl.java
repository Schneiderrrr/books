package servc.books.service.impl;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import servc.books.model.BookDTO;
import servc.books.repository.InMemoryBookDAO;
import servc.books.service.BookService;

import java.util.List;

@Service
@AllArgsConstructor
public class InMemoryBookServiceImpl implements BookService {
    private final InMemoryBookDAO repo;

    public List<BookDTO> GetAllBooks(){
        return repo.GetAllBooks();
    }

    public List<BookDTO> GetPartOfBooks(int take, int skip){
        return repo.GetPartOfBooks(take, skip);
    }
    public List<BookDTO> GetSortedBooks(List<String> sort){
        return repo.GetSortedBooks(sort);
    }

    public BookDTO GetBookByID(Integer id){
        return repo.GetBookByID(id);
    }
    public BookDTO AddBook(BookDTO book) {
        return repo.AddBook(book);
    }

    public BookDTO UpdateBook(Integer id, BookDTO book) {
        return repo.UpdateBook(id, book);
    }

    public void DeleteBookByID(Integer id) {
        repo.DeleteBookByID(id);
    }
}
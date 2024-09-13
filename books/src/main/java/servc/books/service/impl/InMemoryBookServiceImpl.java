package servc.books.service.impl;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import servc.books.model.tbl_Books;
import servc.books.repository.InMemoryBookDAO;
import servc.books.service.BookService;

import java.util.List;

@Service
@AllArgsConstructor
public class InMemoryBookServiceImpl implements BookService {
    private final InMemoryBookDAO repo;

    public List<tbl_Books> GetAllBooks(){
        return repo.GetAllBooks();
    }

    public List<tbl_Books> GetPartOfBooks(int take, int skip){
        return repo.GetPartOfBooks(take, skip);
    }
    public List<tbl_Books> GetSortedBooks(List<String> sort){
        return repo.GetSortedBooks(sort);
    }

    public tbl_Books GetBookByID(Integer id){
        return repo.GetBookByID(id);
    }
    public tbl_Books AddBook(tbl_Books book) {
        return repo.AddBook(book);
    }

    public tbl_Books UpdateBook(Integer id, tbl_Books book) {
        return repo.UpdateBook(id, book);
    }

    public void DeleteBookByID(Integer id) {
        repo.DeleteBookByID(id);
    }
}
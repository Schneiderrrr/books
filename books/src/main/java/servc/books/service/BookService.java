package servc.books.service;

import servc.books.model.tbl_Books;

import java.util.List;

public interface BookService {
    List<tbl_Books> GetAllBooks();
    List<tbl_Books> GetPartOfBooks(int take, int skip);
    List<tbl_Books> GetSortedBooks(List<String> sort);
    tbl_Books GetBookByID(Integer id);
    tbl_Books AddBook(tbl_Books book);
    tbl_Books UpdateBook(Integer id, tbl_Books book);
    void DeleteBookByID(Integer id);
}

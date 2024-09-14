package servc.books.service;

import servc.books.model.tbl_BooksDTO;

import java.util.List;

public interface BookService {
    List<tbl_BooksDTO> GetAllBooks();
    List<tbl_BooksDTO> GetPartOfBooks(int take, int skip);
    List<tbl_BooksDTO> GetSortedBooks(List<String> sort);
    tbl_BooksDTO GetBookByID(Integer id);
    tbl_BooksDTO AddBook(tbl_BooksDTO book);
    tbl_BooksDTO UpdateBook(Integer id, tbl_BooksDTO book);
    void DeleteBookByID(Integer id);
}

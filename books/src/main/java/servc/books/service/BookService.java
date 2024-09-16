package servc.books.service;

import servc.books.model.BookDTO;

import java.util.List;

public interface BookService {
    List<BookDTO> GetAllBooks();
    List<BookDTO> GetPartOfBooks(int take, int skip);
    List<BookDTO> GetSortedBooks(List<String> sort);
    BookDTO GetBookByID(Integer id);
    BookDTO AddBook(BookDTO book);
    BookDTO UpdateBook(Integer id, BookDTO book);
    void DeleteBookByID(Integer id);
}

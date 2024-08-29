package servc.books.service;

import servc.books.model.Book;

import java.util.List;

public interface BookService {
    List<Book> GetAllBooks();
    Book GetBookByID(Integer id);
    Book AddBook(Book book);
    Book UpdateBook(Integer id, Book book);
    void DeleteBookByID(Integer id);
}

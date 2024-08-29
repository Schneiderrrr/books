package servc.books.repository;

import org.springframework.stereotype.Repository;
import servc.books.model.Book;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

@Repository
public class InMemoryBookDAO {
    private final List<Book> BOOKS = new ArrayList<Book>();

    public List<Book> GetAllBooks(){
        return BOOKS;
    }

    public Book GetBookByID(Integer id){
        return BOOKS.stream()
                .filter(elem -> elem.getID().equals(id))
                .findFirst()
                .orElse(null);
    }

    public Book AddBook(Book book) {
        BOOKS.add(book);
        return book;
    }

    public Book UpdateBook(Integer id, Book book) {
        var updatedBook = GetBookByID(id);

        if (updatedBook != null){
            var bookIdx = IntStream.range(0, BOOKS.size())
                    .filter(index -> BOOKS.get(index).getID().equals(id))
                    .findFirst()
                    .orElse( -1);

            BOOKS.set(bookIdx, book);
            return book;
        }

        return null;
    }

    public void DeleteBookByID(Integer id) {
        var deletedBook = GetBookByID(id);
        if (deletedBook != null) {
            BOOKS.remove(deletedBook);
        }
    }
}
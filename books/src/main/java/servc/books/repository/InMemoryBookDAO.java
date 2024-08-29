package servc.books.repository;

import org.springframework.stereotype.Repository;
import servc.books.model.Book;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.IntStream;

@Repository
public class InMemoryBookDAO {
    private final List<Book> BOOKS = new ArrayList<Book>();

    public List<Book> GetAllBooks() {
        return BOOKS;
    }

    public List<Book> GetPartOfBooks(int take, int skip) {
        return BOOKS.stream().skip(skip).limit(take).toList();
    }

    public List<Book> GetSortedBooks(List<String> sort) {
        List<Book> books = BOOKS;

        for (String sortType : sort) {
            String colName = sortType.split(":")[0];
            String dirName = sortType.split(":")[1];

            boolean reverseSort = switch (dirName) {
                case "desc" -> true;
                case "asc" -> false;
                default -> throw new IllegalStateException("Unexpected value: " + dirName);
            };

            switch (colName.toLowerCase()){
                case "name":
                    if (reverseSort)
                        books.sort(Comparator.comparing(Book::getName).reversed());
                    else
                        books.sort(Comparator.comparing(Book::getName));
                    break;
                case "isbn":
                    if (reverseSort)
                        books.sort(Comparator.comparing(Book::getISBN).reversed());
                    else
                        books.sort(Comparator.comparing(Book::getISBN));
                    break;
                case "author":
                    if (reverseSort)
                        books.sort(Comparator.comparing(Book::getAuthor).reversed());
                    else
                        books.sort(Comparator.comparing(Book::getAuthor));
                    break;
                case "releasedate":
                    if (reverseSort)
                        books.sort(Comparator.comparing(Book::getReleaseDate).reversed());
                    else
                        books.sort(Comparator.comparing(Book::getReleaseDate));
                    break;
            }
        };
        return books;
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
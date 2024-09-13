package servc.books.repository;

import org.springframework.stereotype.Repository;
import servc.books.model.tbl_Books;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.IntStream;

@Repository
public class InMemoryBookDAO {
    private final List<tbl_Books> BOOKS = new ArrayList<tbl_Books>();

    public List<tbl_Books> GetAllBooks() {
        return BOOKS;
    }

    public List<tbl_Books> GetPartOfBooks(int take, int skip) {
        return BOOKS.stream().skip(skip).limit(take).toList();
    }

    public List<tbl_Books> GetSortedBooks(List<String> sort) {
        List<tbl_Books> books = BOOKS;

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
                        books.sort(Comparator.comparing(tbl_Books::getName).reversed());
                    else
                        books.sort(Comparator.comparing(tbl_Books::getName));
                    break;
                case "isbn":
                    if (reverseSort)
                        books.sort(Comparator.comparing(tbl_Books::getISBN).reversed());
                    else
                        books.sort(Comparator.comparing(tbl_Books::getISBN));
                    break;
                case "author":
                    if (reverseSort)
                        books.sort(Comparator.comparing(tbl_Books::getAuthor).reversed());
                    else
                        books.sort(Comparator.comparing(tbl_Books::getAuthor));
                    break;
                case "releasedate":
                    if (reverseSort)
                        books.sort(Comparator.comparing(tbl_Books::getReleaseDate).reversed());
                    else
                        books.sort(Comparator.comparing(tbl_Books::getReleaseDate));
                    break;
            }
        };
        return books;
    }

    public tbl_Books GetBookByID(Integer id){
        return BOOKS.stream()
                .filter(elem -> elem.getID().equals(id))
                .findFirst()
                .orElse(null);
    }

    public tbl_Books AddBook(tbl_Books book) {
        BOOKS.add(book);
        return book;
    }

    public tbl_Books UpdateBook(Integer id, tbl_Books book) {
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
package servc.books.repository;

import org.springframework.stereotype.Repository;
import servc.books.model.tbl_Books;
import servc.books.model.tbl_BooksDTO;
import servc.books.service.tbl_BooksDTOMapper;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Repository
public class InMemoryBookDAO {
    private final List<tbl_Books> BOOKS = new ArrayList<tbl_Books>();
    private final tbl_BooksDTOMapper mapper = new tbl_BooksDTOMapper();

    public List<tbl_BooksDTO> GetAllBooks() {
        return BOOKS.stream()
                .map(mapper)
                .collect(Collectors.toList());
    }

    public List<tbl_BooksDTO> GetPartOfBooks(int take, int skip) {
        return BOOKS.stream()
                .skip(skip).limit(take)
                .map(mapper)
                .toList();
    }

    public List<tbl_BooksDTO> GetSortedBooks(List<String> sort) {
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
        return books.stream().map(mapper).toList();
    }

    public tbl_BooksDTO GetBookByID(Integer id){
        return BOOKS.stream()
                .filter(elem -> elem.getID().equals(id))
                .findFirst()
                .map(mapper)
                .orElse(null);
    }

    public tbl_BooksDTO AddBook(tbl_BooksDTO booksDTO){
        if (BOOKS.add(mapper.getEntity(booksDTO)))
            return booksDTO;
        else
            return null;
    }

    public tbl_BooksDTO UpdateBook(Integer id, tbl_BooksDTO booksDTO) {
        var updatedBook = GetBookByID(id);

        if (updatedBook != null){
            var bookIdx = IntStream.range(0, BOOKS.size())
                    .filter(index -> BOOKS.get(index).getID().equals(id))
                    .findFirst()
                    .orElse( -1);

            if (BOOKS.set(bookIdx, mapper.getEntity(booksDTO)) != null)
                return booksDTO;
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
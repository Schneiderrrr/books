package servc.books.repository;

import org.springframework.stereotype.Repository;
import servc.books.model.Book;
import servc.books.model.BookDTO;
import servc.books.service.BookMapper;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Repository
public class InMemoryBookDAO {
    private final List<Book> BOOKS = new ArrayList<Book>();
    private final BookMapper mapper;

    public InMemoryBookDAO(BookMapper mapper) {
        this.mapper = mapper;
    }

    public List<BookDTO> GetAllBooks() {
        return BOOKS.stream()
                .map(mapper::EntityToDto)
                .collect(Collectors.toList());
    }

    public List<BookDTO> GetPartOfBooks(int take, int skip) {
        return BOOKS.stream()
                .skip(skip).limit(take)
                .map(mapper::EntityToDto)
                .toList();
    }

    public List<BookDTO> GetSortedBooks(List<String> sort) {
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
                        books.sort(Comparator.comparing(Book::getIsbn).reversed());
                    else
                        books.sort(Comparator.comparing(Book::getIsbn));
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
        return books.stream().map(mapper::EntityToDto).toList();
    }

    public BookDTO GetBookByID(Integer id){
        return BOOKS.stream()
                .filter(elem -> elem.getId().equals(id))
                .findFirst()
                .map(mapper::EntityToDto)
                .orElse(null);
    }

    public BookDTO AddBook(BookDTO booksDTO){
        if (BOOKS.add(mapper.DtoToEntity(booksDTO)))
            return booksDTO;
        else
            return null;
    }

    public BookDTO UpdateBook(Integer id, BookDTO booksDTO) {
        var updatedBook = GetBookByID(id);

        if (updatedBook != null){
            var bookIdx = IntStream.range(0, BOOKS.size())
                    .filter(index -> BOOKS.get(index).getId().equals(id))
                    .findFirst()
                    .orElse( -1);

            if (BOOKS.set(bookIdx, mapper.DtoToEntity(booksDTO)) != null)
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
package servc.books.service.impl;

import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Primary;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import servc.books.model.tbl_Books;
import servc.books.repository.BookRepository;
import servc.books.service.BookService;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
@Primary
public class BookServiceImpl implements BookService {
    private final BookRepository repository;

    @Override
    public List<tbl_Books> GetAllBooks() {
        return repository.findAll();
    }

    @Override
    public List<tbl_Books> GetPartOfBooks(int take, int skip) {
        return repository.findAll().stream().skip(skip).limit(take).toList();
    }

    @Override
    public List<tbl_Books> GetSortedBooks(List<String> sort) {
        List<Sort.Order> orders = new ArrayList();

        for (String sortType : sort){
            String colName = sortType.split(":")[0];
            String dirName = sortType.split(":")[1];

            boolean reverseSort = switch (dirName) {
                case "desc" -> true;
                case "asc" -> false;
                default -> throw new IllegalStateException("Unexpected value: " + dirName);
            };

            orders.add(new Sort.Order(reverseSort ? Sort.Direction.DESC : Sort.Direction.ASC, colName));
        }

        // TODO: ERROR -- "Could not resolve attribute"
        return repository.findAll(Sort.by(orders));
    }

    @Override
    public tbl_Books GetBookByID(Integer id) {
        return repository.findById(id).orElse(null);
    }

    @Override
    public tbl_Books AddBook(tbl_Books book) {
        return repository.save(book);
    }

    @Override
    public tbl_Books UpdateBook(Integer id, tbl_Books book) {
        return repository.save(book);
    }

    @Override
    public void DeleteBookByID(Integer id) {
        repository.deleteById(id);
    }
}

package servc.books.service.impl;

import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Primary;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import servc.books.model.BookDTO;
import servc.books.repository.BookRepository;
import servc.books.service.BookService;
import servc.books.service.BookMapper;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
@Primary
public class BookServiceImpl implements BookService {
    private final BookRepository repository;
    private final BookMapper mapper;

    @Override
    public List<BookDTO> GetAllBooks() {
        return repository.findAll()
                .stream()
                .map(mapper::EntityToDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<BookDTO> GetPartOfBooks(int take, int skip) {
        return repository.findAll()
                .stream().skip(skip).limit(take)
                .map(mapper::EntityToDto)
                .toList();
    }

    @Override
    public List<BookDTO> GetSortedBooks(List<String> sort) {
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
        return repository.findAll(Sort.by(orders))
                .stream().map(mapper::EntityToDto)
                .toList();
    }

    @Override
    public BookDTO GetBookByID(Integer id) {
        return repository.findById(id).map(mapper::EntityToDto).orElse(null);
    }

    @Override
    public BookDTO AddBook(BookDTO booksDTO) {
        return mapper.EntityToDto(repository.save(mapper.DtoToEntity(booksDTO)));
    }

    @Override
    public BookDTO UpdateBook(Integer id, BookDTO booksDTO) {
        return mapper.EntityToDto(repository.save(mapper.DtoToEntity(booksDTO)));
    }

    @Override
    public void DeleteBookByID(Integer id) {
        repository.deleteById(id);
    }
}

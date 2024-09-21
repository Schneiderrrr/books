package servc.books.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import servc.books.model.Book;
import servc.books.service.ParseSortList;

import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<Book, Integer>, ParseSortList {
    Book findByIsbn(String isbn);
}


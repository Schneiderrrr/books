package servc.books.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import servc.books.model.tbl_Books;

@Repository
public interface BookRepository extends JpaRepository<tbl_Books, Integer> {
}


package servc.books.service;

import org.springframework.stereotype.Service;
import servc.books.model.tbl_Books;
import servc.books.model.tbl_BooksDTO;

import java.util.function.Function;

@Service
public class tbl_BooksDTOMapper implements Function<tbl_Books, tbl_BooksDTO> {
    @Override
    public tbl_BooksDTO apply(tbl_Books tbl_books) {
        return new tbl_BooksDTO(
                tbl_books.getID(),
                tbl_books.getName(),
                tbl_books.getISBN(),
                tbl_books.getAuthor(),
                tbl_books.getReleaseDate());
    }

    public tbl_Books getEntity(tbl_BooksDTO booksDTO) {
        tbl_Books books = new tbl_Books();
        books.setID(booksDTO.ID());
        books.setAuthor(booksDTO.Author());
        books.setName(booksDTO.Name());
        books.setISBN(booksDTO.ISBN());
        books.setReleaseDate(booksDTO.ReleaseDate());

        return books;
    }
}

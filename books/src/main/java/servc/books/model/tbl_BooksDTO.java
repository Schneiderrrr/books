package servc.books.model;

import java.time.LocalDate;

public record tbl_BooksDTO (
        Integer ID,
        String Name,
        String ISBN,
        String Author,
        LocalDate ReleaseDate
) {

}

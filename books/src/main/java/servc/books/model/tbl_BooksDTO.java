package servc.books.model;

import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class tbl_BooksDTO {
    @Id
    private Integer ID;
    private String Name;
    private String ISBN;
    private String Author;
    private LocalDate ReleaseDate;
}

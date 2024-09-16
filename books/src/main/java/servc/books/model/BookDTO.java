package servc.books.model;

import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class BookDTO {
    @Id
    private Integer id;
    private String name;
    private String isbn;
    private String author;
    private LocalDate releaseDate;
}

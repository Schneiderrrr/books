package servc.books.model;

import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

public record BookDTO (
    Integer id,
    String name,
    String isbn,
    String author,
    LocalDate releaseDate
) {}

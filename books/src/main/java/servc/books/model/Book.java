package servc.books.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@Entity
@Table
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(updatable = false, nullable = false)
    private Integer id;
    @Column(name = "name", nullable = false)
    private String name;
    @Column(name = "isbn", nullable = false, unique = true, updatable = false)
    private String isbn;
    @Column(name = "author")
    private String author;
    @Column(name = "release_date")
    private LocalDate releaseDate;
}
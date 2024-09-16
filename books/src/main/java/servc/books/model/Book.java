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
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "name")
    private String name;
    @Column(name = "isbn")
    private String isbn;
    @Column(name = "author")
    private String author;
    @Column(name = "release_date")
    private LocalDate releaseDate;
}
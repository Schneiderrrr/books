package servc.books.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.*;

import java.time.LocalDate;

@Data
@Entity
public class tbl_Books {
    @Id
    @GeneratedValue
    private Integer ID;
    @Column(name = "name")
    private String Name;
    @Column(name = "isbn")
    private String ISBN;
    @Column(name = "author")
    private String Author;
    @Column(name = "release_date")
    private LocalDate ReleaseDate;
}
package servc.books.model;

import lombok.*;

import java.time.LocalDate;

@Data
@Builder
public class Book {
    @NonNull
    private Integer ID;
    @NonNull
    private String Name;
    @NonNull
    private String ISBN;
    @NonNull
    private String Author;
    @NonNull
    private LocalDate ReleaseDate;
}

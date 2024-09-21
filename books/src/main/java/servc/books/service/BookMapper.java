package servc.books.service;

import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import servc.books.model.Book;
import servc.books.model.BookDTO;

@Mapper(componentModel = "spring")
public interface BookMapper {
    BookDTO EntityToDto(Book books);
    @InheritInverseConfiguration
    Book DtoToEntity(BookDTO booksDTO);
}
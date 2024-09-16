package servc.books.service;

import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import servc.books.model.Book;
import servc.books.model.BookDTO;

@Mapper
public interface BookMapper {
    BookMapper INSTANCE = Mappers.getMapper(BookMapper.class);

    BookDTO EntityToDto(Book books);
    @InheritInverseConfiguration
    Book DtoToEntity(BookDTO booksDTO);
}
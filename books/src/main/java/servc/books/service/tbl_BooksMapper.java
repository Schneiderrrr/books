package servc.books.service;

import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import servc.books.model.tbl_Books;
import servc.books.model.tbl_BooksDTO;

@Mapper
public interface tbl_BooksMapper {
    tbl_BooksMapper INSTANCE = Mappers.getMapper(tbl_BooksMapper.class);

    tbl_BooksDTO EntityToDto(tbl_Books books);
    @InheritInverseConfiguration
    tbl_Books DtoToEntity(tbl_BooksDTO booksDTO);
}
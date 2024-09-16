package servc.books;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import servc.books.service.tbl_BooksMapper;

@SpringBootApplication
public class BooksApplication {

	public static void main(String[] args) {
		SpringApplication.run(BooksApplication.class, args);
	}

	@Bean
	tbl_BooksMapper tbl_BooksMapper() {
		return tbl_BooksMapper.INSTANCE;
	}

}

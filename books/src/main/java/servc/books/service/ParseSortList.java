package servc.books.service;

import org.springframework.data.domain.Sort;

import java.util.List;

public interface ParseSortList {
    Sort parse(List<String> sort);
}

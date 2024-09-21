package servc.books.service.impl;

import org.springframework.data.domain.Sort;
import servc.books.service.ParseSortList;

import java.util.ArrayList;
import java.util.List;

public class ParseSortListImpl implements ParseSortList {
    @Override
    public Sort parse(List<String> sort) {
        List<Sort.Order> orders = new ArrayList();

        for (String sortType : sort){
            String colName = sortType.split(":")[0];
            String dirName = sortType.split(":")[1];

            boolean reverseSort = switch (dirName) {
                case "desc" -> true;
                case "asc" -> false;
                default -> throw new IllegalStateException("Unexpected value: " + dirName);
            };

            orders.add(new Sort.Order(reverseSort ? Sort.Direction.DESC : Sort.Direction.ASC, colName));
        }

        return Sort.by(orders);
    }
}

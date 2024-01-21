package com.restApi.BasicRestApplication.utils;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Order;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class Utils {

    // Private constructor to prevent instantiation
    private Utils() {
        throw new IllegalStateException("Utility class");
    }

    public static Pageable createPageableBasedOnPageAndSizeAndSorting(Integer page, Integer size, List<SortItem> sortItems) {
        List<Order> orders = new ArrayList<>();

        if(sortItems != null) {
            for (SortItem sortItem : sortItems) {
                orders.add(new Order(sortItem.getDirection(), sortItem.getField()));
            }
        }

        return PageRequest.of(
                Optional.ofNullable(page).orElse(0),
                Optional.ofNullable(size).orElse(10),
                Sort.by(orders)
        );
    }

}

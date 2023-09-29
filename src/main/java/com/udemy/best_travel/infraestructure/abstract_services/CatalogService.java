package com.udemy.best_travel.infraestructure.abstract_services;

import com.udemy.best_travel.util.SortType;
import org.springframework.data.domain.Page;

import java.math.BigDecimal;
import java.util.Set;

public interface CatalogService<R> {

    String FIELD_BY_SORT = "price";
    Page<R> readAll(Integer page, Integer size, SortType sortType);
    Set<R> readLessPrice(BigDecimal price);
    Set<R> readBetweenPrice(BigDecimal min, BigDecimal max);
}

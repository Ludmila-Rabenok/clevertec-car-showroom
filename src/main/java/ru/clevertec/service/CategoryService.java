package ru.clevertec.service;

import ru.clevertec.entity.Category;

import java.util.List;

public interface CategoryService {
    List<Category> findAll();

    Category findById(Long id);

    Category save(Category category);

    boolean remove(Category category);

    Category update(Category category);
}

package ru.clevertec.repository;

import ru.clevertec.entity.Category;

import java.util.List;

public interface CategoryRepository {
    List<Category> findAll();

    Category findById(Long id);

    Category save(Category category);

    boolean remove(Category category);

    Category update(Category category);
}
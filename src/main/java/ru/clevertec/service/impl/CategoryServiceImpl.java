package ru.clevertec.service.impl;

import ru.clevertec.entity.Category;
import ru.clevertec.repository.CategoryRepository;
import ru.clevertec.service.CategoryService;

import java.util.List;

public class CategoryServiceImpl implements CategoryService {
    private final CategoryRepository categoryRepository;

    public CategoryServiceImpl(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public List<Category> findAll() {
        return categoryRepository.findAll();
    }

    @Override
    public Category findById(Long id) {
        return categoryRepository.findById(id);
    }

    @Override
    public Category save(Category category) {
        return categoryRepository.save(category);
    }

    @Override
    public boolean remove(Category category) {
        return categoryRepository.remove(category);
    }

    @Override
    public Category update(Category category) {
        return categoryRepository.update(category);
    }
}

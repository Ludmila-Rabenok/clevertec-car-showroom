package ru.clevertec.repository.impl;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import ru.clevertec.entity.Category;
import ru.clevertec.exception.RepositoryException;
import ru.clevertec.repository.CategoryRepository;
import ru.clevertec.util.HibernateUtil;

import java.util.List;

public class CategoryRepositoryImpl implements CategoryRepository {
    private final Session session = HibernateUtil.getSession();

    @Override
    public List<Category> findAll() {
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            List<Category> categories = session.createQuery("SELECT c FROM Category c", Category.class)
                    .getResultList();
            transaction.commit();
            return categories;
        } catch (HibernateException e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new RepositoryException(e);
        } finally {
            session.close();
        }
    }

    @Override
    public Category findById(Long id) {
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            Category category = session.get(Category.class, id);
            transaction.commit();
            return category;
        } catch (HibernateException e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new RepositoryException(e);
        } finally {
            session.close();
        }
    }

    @Override
    public Category save(Category category) {
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            session.persist(category);
            transaction.commit();

        } catch (HibernateException e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new RepositoryException(e);
        } finally {
            session.close();
        }
        return category;
    }

    @Override
    public boolean remove(Category category) {
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            Category findCategory = session.find(Category.class, category.getId());
            if (findCategory == null) {
                transaction.rollback();
                return false;
            }
            session.remove(category);
            transaction.commit();
            return true;
        } catch (HibernateException e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new RepositoryException(e);
        } finally {
            session.close();
        }
    }

    @Override
    public Category update(Category category) {
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            Category mergeCategory = session.merge(category);
            transaction.commit();
            return mergeCategory;
        } catch (HibernateException e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new RepositoryException(e);
        } finally {
            session.close();
        }
    }
}
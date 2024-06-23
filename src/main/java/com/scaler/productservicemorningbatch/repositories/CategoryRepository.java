package com.scaler.productservicemorningbatch.repositories;

import com.scaler.productservicemorningbatch.models.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CategoryRepository
        extends JpaRepository<Category, Long> {

    Category save(Category category);

    Optional<Category> findById(Long Id);

    List<Category> findAllByTitle(String title);

    void deleteById(Long id);
}

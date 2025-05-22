package com.poly.thuviendatn.Repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.poly.thuviendatn.Model.Category;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {
}


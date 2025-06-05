package com.poly.thuviendatn.Repository;

import com.poly.thuviendatn.Model.TacGia;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TacGiaRepository extends JpaRepository<TacGia, Integer> {
}
package com.poly.thuviendatn.Repository;

import com.poly.thuviendatn.Model.DocGia;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DocGiaRepository extends JpaRepository<DocGia, Integer> {
    Page<DocGia> findByTenDocGiaContainingIgnoreCase(String tenDocGia, Pageable pageable);
    Page<DocGia> findByTenDocGiaContainingIgnoreCaseOrCccdContainingIgnoreCase(String tenDocGia, String cccd, Pageable pageable);
}
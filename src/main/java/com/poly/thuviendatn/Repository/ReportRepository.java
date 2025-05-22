package com.poly.thuviendatn.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.poly.thuviendatn.Model.Report;

@Repository
public interface ReportRepository extends JpaRepository<Report, Long> {
}


package com.poly.thuviendatn.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.poly.thuviendatn.Model.Employee;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {
}


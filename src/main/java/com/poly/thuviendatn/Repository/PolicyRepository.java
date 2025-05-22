package com.poly.thuviendatn.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.poly.thuviendatn.Model.Policy;

@Repository
public interface PolicyRepository extends JpaRepository<Policy, Long> {
    
}


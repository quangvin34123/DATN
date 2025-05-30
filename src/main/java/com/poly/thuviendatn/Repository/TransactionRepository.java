package com.poly.thuviendatn.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.poly.thuviendatn.Model.Transaction;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Long> {
}


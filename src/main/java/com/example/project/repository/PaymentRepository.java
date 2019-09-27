package com.example.project.repository;


import com.example.project.model.Payment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PaymentRepository extends JpaRepository<Payment, Long> {

    List<Payment> findByUserId(Long id);

    @Query(value = "SELECT SUM(amount) FROM demo.payment WHERE user_id=?1 GROUP BY user_id;", nativeQuery = true)
    Double getTotalPayments(Long id);
}

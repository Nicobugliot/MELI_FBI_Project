package com.example.project.repository;

import com.example.project.model.Invoice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;


public interface InvoiceRepository extends JpaRepository<Invoice, Long> {

    @Query(value = "SELECT * FROM demo.invoice WHERE user_id=?1 AND month=?2 AND year=?3", nativeQuery = true)
    Invoice userInvoiceByMonthAndYear(Long user_id, Integer month, Integer year);

}

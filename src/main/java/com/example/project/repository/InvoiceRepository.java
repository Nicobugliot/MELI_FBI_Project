package com.example.project.repository;

import com.example.project.model.Invoice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;


public interface InvoiceRepository extends JpaRepository<Invoice, Long> {

    @Query(value = "SELECT * FROM demo.invoice WHERE user_id=?1 AND month=?2 AND year=?3", nativeQuery = true)
    Invoice userInvoiceByMonthAndYear(Long user_id, Integer month, Integer year);

    @Query(value = "SELECT * FROM demo.invoice WHERE user_id=?1", nativeQuery = true)
    List<Invoice> findInvoiceByUserId(Long user_id);
}

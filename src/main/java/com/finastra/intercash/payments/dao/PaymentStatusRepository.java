package com.finastra.intercash.payments.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.finastra.intercash.payments.entity.PaymentStatus;

/**
 * @author Vipin.Mangalkar
 *
 */

@Repository
public interface PaymentStatusRepository extends JpaRepository<PaymentStatus, Long> {
	
	@Query("SELECT PaymentStatus FROM PaymentStatus PaymentStatus WHERE PaymentStatus.payId = :payId ORDER BY PaymentStatus.serialId ")
	List<PaymentStatus> findByPayId(String payId);
}

package com.finastra.intercash.payments.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.finastra.intercash.payments.entity.PaymentInitiation;

/**
 * @author Vipin.Mangalkar
 *
 */
@Repository
public interface PaymentInitiationRepository extends JpaRepository<PaymentInitiation, String> {
	List<PaymentInitiation> findAllByStatus(String status);
}

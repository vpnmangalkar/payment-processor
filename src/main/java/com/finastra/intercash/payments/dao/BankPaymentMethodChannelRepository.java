package com.finastra.intercash.payments.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.finastra.intercash.payments.entity.BankPaymentMethod;

/**
 * @author Vipin.Mangalkar
 *
 */
@Repository
public interface BankPaymentMethodChannelRepository extends JpaRepository<BankPaymentMethod, Long> {

	List<BankPaymentMethod> findByBankId(String bankId);

	BankPaymentMethod findByBankIdAndPaymentMode(String bankId, String paymentMode);
}

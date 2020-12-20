package com.finastra.intercash.payments.dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.finastra.intercash.payments.entity.BankFileConfig;


/**
 * @author Vipin.Mangalkar
 *
 */
@Repository
public interface BankFileConfigRepository extends JpaRepository<BankFileConfig, Long> {
	Optional<BankFileConfig> findByBankId(String bankId);
}

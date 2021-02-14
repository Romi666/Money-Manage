package com.money.manage.repository;

import com.money.manage.model.Wallet;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WalletRepository extends JpaRepository<Wallet, Long> {

    Wallet findByUserId(Long userId);

    boolean existsByUserId(Long userId);
}

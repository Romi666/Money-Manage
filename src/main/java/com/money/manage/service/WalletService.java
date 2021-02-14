package com.money.manage.service;

import com.money.manage.exceptions.CustomException;
import com.money.manage.model.Wallet;
import com.money.manage.repository.WalletRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
public class WalletService {

    @Autowired
    WalletRepository walletRepository;

    public Wallet create(Wallet wallet) {
        if(!walletRepository.existsByUserId(wallet.getUserId())) {
            walletRepository.save(wallet);
            return wallet;
        }else {
            throw new CustomException("Wallet already exist", HttpStatus.UNPROCESSABLE_ENTITY);
        }
    }

    public Wallet getWallet(Long userId) {
        Wallet wallet = walletRepository.findByUserId(userId);
        if (wallet == null) {
            throw new CustomException("Wallet not found", HttpStatus.NOT_FOUND);
        }
        return wallet;
    }
}

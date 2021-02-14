package com.money.manage.service;

import com.money.manage.dto.HistoryDataDTO;
import com.money.manage.exceptions.CustomException;
import com.money.manage.model.History;
import com.money.manage.model.Wallet;
import com.money.manage.repository.HistoryRepository;
import com.money.manage.repository.WalletRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class HistoryService {

    @Autowired
    HistoryRepository historyRepository;

    @Autowired
    WalletRepository walletRepository;

    public History incomeHistory(HistoryDataDTO history) {
        ModelMapper modelMapper = new ModelMapper();
        Wallet checkWallet = walletRepository.findByUserId(history.getUserId());
        if (checkWallet == null) {
            throw new CustomException("Wallet not found", HttpStatus.NOT_FOUND);
        }
        checkWallet.setBalance(checkWallet.getBalance() + history.getAmount());
        walletRepository.save(checkWallet);
        history.setType("IN");
        return historyRepository.save(modelMapper.map(history, History.class));
    }

    public History outcomeHistory(HistoryDataDTO history) {
        ModelMapper modelMapper = new ModelMapper();
        Wallet checkWallet = walletRepository.findByUserId(history.getUserId());
        if (checkWallet == null) {
            throw new CustomException("Wallet not found", HttpStatus.NOT_FOUND);
        }
        checkWallet.setBalance(checkWallet.getBalance() - history.getAmount());
        walletRepository.save(checkWallet);
        history.setType("OUT");
        return historyRepository.save(modelMapper.map(history, History.class));
    }

    public List<History> getListHistory(Long userId) {
        List<History> list = new ArrayList<>();
        historyRepository.findByUserId(userId)
                .stream().forEach(value -> {
                    list.add(value);
        });
        return list;
    }
}

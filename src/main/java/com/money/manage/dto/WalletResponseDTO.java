package com.money.manage.dto;

import io.swagger.annotations.ApiModelProperty;

public class WalletResponseDTO {

    @ApiModelProperty(position = 0)
    private Long userId;

    @ApiModelProperty(position = 1)
    private Double balance;

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Double getBalance() {
        return balance;
    }

    public void setBalance(Double balance) {
        this.balance = balance;
    }
}

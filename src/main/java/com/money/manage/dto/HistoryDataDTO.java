package com.money.manage.dto;

import io.swagger.annotations.ApiModelProperty;

public class HistoryDataDTO {
    @ApiModelProperty(position = 0)
    private Long userId;

    @ApiModelProperty(position = 1)
    private String type;

    @ApiModelProperty(position = 2)
    private String description;

    @ApiModelProperty(position = 3)
    private Double amount;

    public HistoryDataDTO() {
    }

    public HistoryDataDTO(Long userId, String type, String description, Double amount) {
        this.userId = userId;
        this.type = type;
        this.description = description;
        this.amount = amount;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }
}

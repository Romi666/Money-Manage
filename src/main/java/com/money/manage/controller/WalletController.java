package com.money.manage.controller;

import com.money.manage.dto.WalletDataDTO;
import com.money.manage.model.Response;
import com.money.manage.model.Wallet;
import com.money.manage.service.WalletService;
import io.swagger.annotations.*;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/wallet")
@Api(tags = "wallet")
public class WalletController {
    @Autowired
    WalletService walletService;

    @PostMapping("/create")
    @ApiOperation(value = "Create Wallet")
    @ApiResponses(value = {
            @ApiResponse(code = 400, message = "Something went wrong"),
            @ApiResponse(code = 422, message = "User doesn't exist")})
    public Response<Wallet> create(@ApiParam("Create wallet") @RequestBody WalletDataDTO wallet) {
        ModelMapper modelMapper = new ModelMapper();
        Wallet walletData = walletService.create(modelMapper.map(wallet, Wallet.class));
        return Response.success(walletData);
    }

    @PostMapping("/detail")
    @ApiOperation(value = "Detail Wallet")
    @ApiResponses(value = {
            @ApiResponse(code = 400, message = "Something went wrong"),
            @ApiResponse(code = 422, message = "Wallet doesn't exist")})
    public Response<Wallet> detail(@ApiParam("user_id") @RequestParam Long userId) {
        return Response.success(walletService.getWallet(userId));
    }
}

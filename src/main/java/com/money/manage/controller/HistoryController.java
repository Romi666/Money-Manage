package com.money.manage.controller;

import com.money.manage.dto.HistoryDataDTO;
import com.money.manage.model.History;
import com.money.manage.model.Response;
import com.money.manage.service.HistoryService;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/history")
@Api(tags = "history")
public class HistoryController {
    @Autowired
    HistoryService historyService;

    @PostMapping("/income")
    @ApiOperation(value = "Income balance")
    @ApiResponses(value = {
            @ApiResponse(code = 400, message = "Something went wrong"),
            @ApiResponse(code = 422, message = "Wallet doesn't exist")})
    public Response<History> income(@ApiParam("Add Income History") @RequestBody HistoryDataDTO history) {
        return Response.success(historyService.incomeHistory(history));
    }

    @PostMapping("/outcome")
    @ApiOperation(value = "Outcome balance")
    @ApiResponses(value = {
            @ApiResponse(code = 400, message = "Something went wrong"),
            @ApiResponse(code = 422, message = "Wallet doesn't exist")})
    public Response<History> outcome(@ApiParam("Add Income History") @RequestBody HistoryDataDTO history) {
        return Response.success(historyService.outcomeHistory(history));
    }

    @GetMapping("")
    @ApiOperation(value = "History")
    @ApiResponses(value = {
            @ApiResponse(code = 400, message = "Something went wrong"),
            @ApiResponse(code = 422, message = "Wallet doesn't exist")})
    public Response<List<History>> getListHistory(@ApiParam("user_id") @RequestParam Long userId){
        return Response.success(historyService.getListHistory(userId));
    }
}

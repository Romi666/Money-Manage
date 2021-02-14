package com.money.manage.controller;

import com.money.manage.dto.UserDataDTO;
import com.money.manage.dto.UserResponseDTO;
import com.money.manage.model.Response;
import com.money.manage.model.User;
import com.money.manage.service.UserService;
import io.swagger.annotations.*;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
@Api(tags = "users")
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping("/signin")
    @ApiOperation(value = "${UserController.signin}")
    @ApiResponses(value = {
            @ApiResponse(code = 400, message = "Something went wrong"),
            @ApiResponse(code = 422, message = "Invalid username/password")})
    public Response<String> login(
            @ApiParam("username") @RequestParam String username,
            @ApiParam("password") @RequestParam String password) {
        return Response.success(userService.signin(username, password));
    }

    @PostMapping("/signup")
    @ApiOperation(value = "${UserController.signup}")
    @ApiResponses(value = {
            @ApiResponse(code = 400, message = "Something went wrong"),
            @ApiResponse(code = 403, message = "Access denied"),
            @ApiResponse(code = 422, message = "Username already in use")})
    public Response<String> signup(@ApiParam("Signup user") @RequestBody UserDataDTO user) {
        ModelMapper modelMapper = new ModelMapper();
        return Response.success(userService.signup(modelMapper.map(user, User.class)));
    }

    @DeleteMapping(value = "/{username}")
    @ApiOperation(value = "${UserController.delete}", authorizations = {@Authorization(value = "apiKey")})
    @ApiResponses(value = {
            @ApiResponse(code = 400, message = "Something went wrong"), //
            @ApiResponse(code = 403, message = "Access denied"), //
            @ApiResponse(code = 404, message = "The user doesn't exist"), //
            @ApiResponse(code = 500, message = "Expired or invalid JWT token")})
    public Response<String> delete(@ApiParam("username") @PathVariable String username) {
        userService.delete(username);
        return Response.success(username);
    }

    @GetMapping(value = "/{username}")
    @ApiOperation(value = "${UserController.search}", response = UserResponseDTO.class, authorizations = {@Authorization(value = "apiKey")})
    @ApiResponses(value = {
            @ApiResponse(code = 400, message = "Something went wrong"), //
            @ApiResponse(code = 403, message = "Access denied"), //
            @ApiResponse(code = 404, message = "The user doesn't exist"), //
            @ApiResponse(code = 500, message = "Expired or invalid JWT token")})
    public Response<UserResponseDTO> search(@ApiParam("username") @PathVariable String username) {
        ModelMapper modelMapper = new ModelMapper();
        return Response.success(modelMapper.map(userService.search(username), UserResponseDTO.class));
    }
}

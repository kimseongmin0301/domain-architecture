package com.example.proto.domain.Admin.controller;

import com.example.proto.domain.Admin.service.AdminService;
import com.example.proto.global.common.ResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class AdminController {

    private final AdminService adminService;

    @GetMapping("/{id}")
    public ResponseDto<?> findById(@PathVariable long id) {
        return adminService.findById(id);
    }
}

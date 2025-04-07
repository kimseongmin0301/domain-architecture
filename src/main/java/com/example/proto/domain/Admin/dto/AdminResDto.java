package com.example.proto.domain.Admin.dto;

import com.example.proto.domain.Admin.entity.Admin;

public record AdminResDto(Long id, String name, String email) {

    public static AdminResDto toDto (Admin admin) {
        return new AdminResDto(admin.getId(), admin.getName(), admin.getEmail());
    }
}

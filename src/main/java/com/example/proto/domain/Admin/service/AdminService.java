package com.example.proto.domain.Admin.service;

import com.example.proto.domain.Admin.dto.AdminResDto;
import com.example.proto.domain.Admin.entity.Admin;
import com.example.proto.domain.Admin.repository.AdminRepository;
import com.example.proto.global.common.ResponseDto;
import com.example.proto.global.error.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AdminService {
    private final AdminRepository adminRepository;

    public ResponseDto<AdminResDto> findById(Long id) {
        Admin admin = adminRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("해당 어드민을 찾을 수 없습니다."));

        AdminResDto dto = AdminResDto.toDto(admin);
        return ResponseDto.success(dto, "어드민을 찾았습니다.");
    }
}

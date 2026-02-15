package com.operis.portal.dto;

import lombok.*;

import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CreateUserDTO {
    private String name;
    private String email;
    private String password;
    private UUID companyId; // empresa obrigatória
    private UUID roleId;    // papel na empresa
}

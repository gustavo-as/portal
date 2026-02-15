package com.operis.portal.dto;

import lombok.*;

import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserCompanyDTO {
    private UUID id;
    private UUID userId;
    private UUID companyId;
    private UUID roleId;
    private boolean active;
}

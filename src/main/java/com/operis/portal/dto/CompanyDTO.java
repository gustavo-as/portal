package com.operis.portal.dto;

import lombok.*;

import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CompanyDTO {
    private UUID id;
    private String name;
    private String idFiscal;
    private boolean active;
}

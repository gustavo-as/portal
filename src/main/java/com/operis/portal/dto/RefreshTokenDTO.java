package com.operis.portal.dto;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RefreshTokenDTO {
    private String refreshToken;
}

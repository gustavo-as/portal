package com.operis.portal.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@Entity
@Table(name = "company")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Company {

    @Id
    @GeneratedValue
    private UUID id;

    @Column(nullable = false, unique = true)
    private String name;

    // TVA or CNPJ
    @Column(nullable = false, unique = true)
    private String idFiscal;

    @Column(nullable = false)
    private boolean active = true;
}

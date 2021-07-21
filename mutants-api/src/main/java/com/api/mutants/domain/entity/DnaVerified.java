package com.api.mutants.domain.entity;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Entity
@Data
@Builder(toBuilder = true)
@AllArgsConstructor(access = AccessLevel.PUBLIC)
@NoArgsConstructor(access = AccessLevel.PUBLIC)
@Table(name = "dna_verified", schema = "mutants_analytics")
public class DnaVerified {

    @Id
    private Long id;

    private String dna;

    @Column(name = "is_mutant")
    private Boolean isMutant;

    @Column(name = "date_create", updatable = false)
    protected Date createDate;

    @Column(name = "date_modification", insertable = false)
    protected Date modificationDate;
}

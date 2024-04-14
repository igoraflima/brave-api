package com.brave.braveapi.entidades.base;

import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.util.Date;

@SuperBuilder
@MappedSuperclass
@NoArgsConstructor
@AllArgsConstructor
public abstract class BaseEntity {
    @Builder.Default
    @Column(name = "created_date")
    private Date createdDate = new Date();

    @Builder.Default
    @Column(name = "updated_date")
    private Date updatedDate = new Date();

    @Builder.Default
    @Column(name = "deleted")
    private Boolean deleted = false;
}

package com.example.demo.entity.template;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
@Getter
@Setter
@MappedSuperclass
public abstract class AbsLongWithAuditEntity extends AbsAuditingEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
}

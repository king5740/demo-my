package com.example.demo.entity.template;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
@Getter
@Setter
@MappedSuperclass
public abstract class AbsLongEntity extends AbsTimestampEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
}

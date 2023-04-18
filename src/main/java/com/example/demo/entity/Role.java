package com.example.demo.entity;

import com.example.demo.entity.enums.PageEnum;
import com.example.demo.entity.enums.PermissionEnum;
import com.example.demo.entity.enums.RoleEnum;
import com.example.demo.entity.template.AbsIntegerEntity;
import lombok.*;

import javax.persistence.*;
import java.util.Set;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Role extends AbsIntegerEntity {

    @Column(unique = true, nullable = false)
    @Enumerated(EnumType.STRING)
    private RoleEnum name;

    @Column(length = 500)
    private String description;

    @CollectionTable(name = "role_permission",
            joinColumns =
            @JoinColumn(name = "role_id", referencedColumnName = "id"))
    @ElementCollection
    @Enumerated(EnumType.STRING)
    @Column(name = "permission", nullable = false)
    private Set<PermissionEnum> permissions;

}

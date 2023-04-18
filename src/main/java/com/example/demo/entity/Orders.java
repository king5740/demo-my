package com.example.demo.entity;

import com.example.demo.entity.template.AbsLongWithAuditEntity;
import lombok.*;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.Column;
import javax.persistence.Entity;
import java.sql.Timestamp;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@DynamicInsert
@DynamicUpdate
@Builder
@Where(clause = "deleted = false")
@SQLDelete(sql = "UPDATE orders SET deleted = true where id = ?")
public class Orders extends AbsLongWithAuditEntity {
    @Column(nullable = false)
    String firstName;
    @Column(nullable = false)
    String lastName;
    @Column(nullable = false)
    Long numberOfOrders;
    @Column(nullable = false)
    String phoneNumber;
    @Column(nullable = false)
    String address;
    @Column(nullable = false)
    LocalDateTime orderDate;

}

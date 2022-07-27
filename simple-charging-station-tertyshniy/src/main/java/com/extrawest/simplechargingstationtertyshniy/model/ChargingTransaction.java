package com.extrawest.simplechargingstationtertyshniy.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity(name = "charging_transactions")
@Data
@NoArgsConstructor
public class ChargingTransaction {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    @ManyToOne
    @JoinColumn(name = "charge_point_id", nullable = false)
    private ChargePoint chargePoint;
    @OneToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;
}

package com.extrawest.simplechargingstationtertyshniy.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity(name = "charge_points")
@Data
@NoArgsConstructor
public class ChargePoint {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(name = "charge_point_model", nullable = false)
    private String chargePointModel;
    @ManyToOne
    @JoinColumn(name = "location_id", nullable = false)
    private Location location;
    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    public ChargePoint(String chargePointModel, Location location, User user) {
        this.chargePointModel = chargePointModel;
        this.location = location;
        this.user = user;
    }

}

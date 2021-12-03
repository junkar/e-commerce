package com.siq.ecommerce.buyingservice.model;

import lombok.*;

import javax.persistence.*;
import java.io.*;

@Setter
@Getter
@Entity
@NoArgsConstructor
public class Shipment implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="shipment_seq")
    @SequenceGenerator(name = "shipment_seq",sequenceName = "shipment_seq_table")
    @Column(name = "id", unique = true, nullable = false)
    @Setter(AccessLevel.NONE)
    private long id;

    private String cityCode;
    private String location;

    public Shipment(String cityCode, String location) {
        this.cityCode = cityCode;
        this.location = location;
    }

    public Shipment(Shipment copyFrom) {
        this.cityCode = copyFrom.cityCode;
        this.location = copyFrom.location;
    }
}

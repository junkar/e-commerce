package com.siq.ecommerce.buyingservice.model;

import com.siq.ecommerce.buyingservice.enums.*;
import lombok.*;

import javax.persistence.*;
import java.io.*;
import java.util.*;

@Setter
@Getter
@Entity
@NoArgsConstructor
public class Buying implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="buying_seq")
    @SequenceGenerator(name = "buying_seq",sequenceName = "buying_seq_table")
    @Column(name = "id", unique = true, nullable = false)
    @Setter(AccessLevel.NONE)
    public long id;

    @Enumerated(EnumType.ORDINAL)
    private Status status = Status.PENDING;

    @Setter(AccessLevel.NONE)
    private Date createdDate = new Date();

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "shipment_id", referencedColumnName = "id")
    private Shipment shipment;

}

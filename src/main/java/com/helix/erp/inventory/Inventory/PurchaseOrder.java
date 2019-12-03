package com.helix.erp.inventory.Inventory;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@EntityListeners(AuditingEntityListener.class)
public class PurchaseOrder {

    public PurchaseOrder(Organization supplier) {
        this.supplier = supplier;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    Long poNo;

    @ManyToOne(cascade = CascadeType.MERGE)
    Organization supplier;

    @Column
    @CreationTimestamp
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    LocalDateTime createdAt;

    @CreatedBy
    String createdBy;

    @Column
    Float totalAmount;

    @OneToMany(mappedBy = "purchaseOrder")
    List<GRN> grns;

    @OneToMany
    List<Stock> items;


}

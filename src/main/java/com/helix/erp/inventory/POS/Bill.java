package com.helix.erp.inventory.POS;

import com.helix.erp.inventory.Inventory.Item;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Entity
@Data
public class Bill {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column
    String customer = "Walk-In-Customer";

    @Column
    Float total = 0F;

    @Column
    Float paidAmount = 0F;

    @OneToMany(cascade = CascadeType.ALL)
    Set<BillItem> items = new HashSet<>();

    void complete() {
        for (BillItem item : items) {
            item.item.setStock(item.item.getStock() - item.qty);
        }
        this.setStatus(BillStatus.COMPLETED);
    }

    public Float getTotal() {
        total = 0F;
        for (BillItem item : items) {
            total = +item.getSubTotal();
        }
        return total;
    }

    @Column
    BillStatus status = BillStatus.PENDING;

    public BillPaymentStatus paymentStatus() {
        if (paidAmount >= getTotal())
            return BillPaymentStatus.FULLY_PAID;
        if (paidAmount > 0F)
            return BillPaymentStatus.PARTIALLY_PAID;
        return BillPaymentStatus.UNPAID;
    }

    @CreationTimestamp
    LocalDateTime startTime;

    @UpdateTimestamp
    LocalDateTime lastUpdated;

    public void pay(Float amount) {
        this.paidAmount = paidAmount + amount;
    }

    public void addItem(Item item) {
        for (BillItem billItem : items) {
            if (billItem.getItem().getId().equals(item.getId())) {
                billItem.increaseQty(1);
                return;
            }
        }
        this.items.add(new BillItem(item));
    }

    public Integer getQuantity(Item item) {
        for (BillItem billItem : items) {
            if (billItem.getItem().getId().equals(item.getId())) {
                return billItem.qty;

            }

        }
        return 0;
    }

    public void removeItem(Item item) {
        for (BillItem billItem : items) {
            if (billItem.getItem().getId().equals(item.getId())) {
                this.items.remove(billItem);
                return;
            }
        }
    }
}

@Entity
@Data
@NoArgsConstructor
class BillItem {
    public BillItem(Item item) {
        this.item = item;
        qty = 1;
    }

    @Id
    @GeneratedValue
    UUID id;

    @OneToOne
    Item item;

    @Column
    Integer qty;

    public Float getSubTotal() {
        return item.getPrice() * qty;
    }

    public void increaseQty(int no) {
        qty = qty + no;
    }

}


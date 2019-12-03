package com.helix.erp.inventory.Inventory;


import lombok.Data;

@Data
public class ContactInfo {
    String lineOne;
    String lineTwo;
    String city;
    String postalCode;

    String email;

    String telFixed;
    String telMobile;


    public String getAddressSingle() {
        return this.lineOne + ", " + lineTwo + ", " + city;
    }

    public String getTel() {
        if (telFixed != null) {
            return telFixed;
        } else {
            return telMobile;
        }
    }

}

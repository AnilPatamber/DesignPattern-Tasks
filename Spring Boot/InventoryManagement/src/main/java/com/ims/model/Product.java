package com.ims.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Calendar;
import java.util.Date;

@Entity
@Data
public class Product {
    @Id
    @GeneratedValue
    private int id;
    private String description;
    private double weight;
    private double price;
    private Date manufacturingDate;
    private int usedBeforeMonths;
    private Date expiryDate;
    //To Calculate Expiry date based on Manufacturing Date and Used before month property
    public void calculateExpiryDate() {
        Calendar cal = Calendar.getInstance();
        cal.setTime(manufacturingDate);
        cal.add(Calendar.MONTH,usedBeforeMonths);
        expiryDate = cal.getTime();
    }
}

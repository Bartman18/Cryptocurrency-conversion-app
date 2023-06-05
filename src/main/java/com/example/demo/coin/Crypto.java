package com.example.demo.coin;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table
public class Crypto {
    @Id

    @SequenceGenerator(
            name = "crypto_sequence",
            sequenceName =   "crypto_sequence",
            allocationSize = 1
    )

    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "crypto_sequence"
    )

    private String id;



    private String name;
    private double current_price;
    private LocalDate dob;


    public Crypto(String id,   String name,
                  double current_price, LocalDate dob) {
        this.id = id;


        this.name = name;
        this.current_price = current_price;
        this.dob = dob;
    }



    public String getId() {
        return id;
    }




    public String getName() {
        return name;
    }

    public double getCurrent_price() {
        return current_price;
    }

    public LocalDate getDob() {
        return dob;
    }

    @Override
    public String toString() {
        return "Crypto{" +
                "id='" + id + '\'' +

                ", name='" + name + '\'' +
                ", current_price=" + current_price +
                ", dob=" + dob +
                '}';
    }
}

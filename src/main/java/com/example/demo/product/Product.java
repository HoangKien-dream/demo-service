package com.example.demo.product;

import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Product {
    private int id;
    private String name;
    private String thumbnail;
    private double price;
    private int status;
}

package com.nettm.packer.model;

import java.util.Objects;

public class Item {

    private Integer id;
    private Double weight;
    private Double price;

    public Item(int id, Double weight, Double price) {
        this.id = id;
        this.weight = weight;
        this.price = price;
    }

    public int getId() {
        return id;
    }

    public Double getWeight() {
        return weight;
    }

    public Double getPrice() {
        return price;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setWeight(Double weight) {
        this.weight = weight;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Item item = (Item) obj;
        return id.equals(item.id) &&
                Objects.equals(weight, item.weight) &&
                Objects.equals(price, item.price);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, weight, price);
    }
}

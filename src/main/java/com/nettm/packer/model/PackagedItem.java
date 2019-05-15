package com.nettm.packer.model;

import com.nettm.packer.Constants;
import com.nettm.packer.exceptions.APIException;

import java.util.ArrayList;
import java.util.List;

public class PackagedItem {

    private List<Item> items = new ArrayList<>();
    private double weight = 0d;
    private double price = 0d;
    private double weightCapacity;

    public PackagedItem(double weightCapacity) {
        this.weightCapacity = weightCapacity;
    }

    public double getWeight() {
        return weight;
    }

    public double getPrice() {
        return price;
    }

    public double getWeightCapacity() {
        return weightCapacity;
    }

    public void addItem(Item item) throws APIException {
        weight += item.getWeight();
        if (weight > weightCapacity || weight > Constants.MAX_ALLOWED_WEIGHT){
            throw new APIException("weight out of range");
        }

        price += item.getPrice();
        if (price > Constants.MAX_ALLOWED_COST){
            throw new APIException("weight out of range");
        }

        items.add(item);
    }
    @Override
    public String toString (){

        StringBuilder itemsString = new StringBuilder();
        boolean first = true;

        for (Item item: items){
            if (!first){
                itemsString.append(",");
            }
            itemsString.append(item.getId());
            first = false;
        }
        return "w:" + weight + " p:" + price +" items[" + itemsString + "]";
    }
}


package com.nettm.packer.model;

import java.util.ArrayList;
import java.util.List;

public class PackageOption {

    private Long weightCapacity;
    private List<Item> candidateItemsList = new ArrayList<>();

    public Long getWeightCapacity() {
        return weightCapacity;
    }

    public List<Item> getCandidateItemsList() {
        return candidateItemsList;
    }

    public void setWeightCapacity(Long weightCapacity) {
        this.weightCapacity = weightCapacity;
    }

    public void setCandidateItemsList(List<Item> candidateItemsList) {
        this.candidateItemsList = candidateItemsList;
    }

    public void addItem( Item item){
        candidateItemsList.add(item);
    }


}

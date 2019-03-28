package com.mobiquityinc.packer;

import com.mobiquityinc.packer.exceptions.APIException;
import com.mobiquityinc.packer.model.Item;
import com.mobiquityinc.packer.model.PackageOption;

import java.util.ArrayList;
import java.util.List;

public class Parser {

    public List<PackageOption> parse (String file){

        List<PackageOption> packageOptions = new ArrayList<>();

        String[] lines = file.split("\n");

        for (String line : lines){

            if (line.length() >0) {
                try {
                    packageOptions.add(getPackageItem(line));
                } catch (APIException e) {
                    e.printStackTrace();
                }
            }
        }
        return packageOptions;
    }

    private Item parseItem(String itemDefinition){

        String[] itemValues = itemDefinition.replaceAll("\\(", "").replaceAll("\\)","").replaceAll("€","").split(",");

        return new Item(Integer.parseInt(itemValues[0].trim()), Double.parseDouble(itemValues[1].trim()), Double.parseDouble(itemValues[2].trim()));
    }

    private PackageOption getPackageItem (String line) throws APIException {

        PackageOption packageOption = new PackageOption();

        if (!line.contains(":")){

            throw new APIException("input data has error(s)");
        }

        String[] values = line.split(":");

        packageOption.setWeightCapacity( Long.parseLong (values[0].trim()));
        packageOption.setCandidateItemsList(populateCandidateItems(values[1]));

        return packageOption;
    }

    private List<Item> populateCandidateItems(String candidateItemsString) {

        List<Item> candidateItems = new ArrayList<>();
        String[] items = candidateItemsString.split(" ");

        for (String itemsDefinition : items){

            if (itemsDefinition.length() > 0) {
                candidateItems.add(parseItem(itemsDefinition));
            }
        }

        return candidateItems;

    }

}

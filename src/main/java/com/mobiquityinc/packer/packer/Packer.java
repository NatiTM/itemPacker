package com.mobiquityinc.packer.packer;

import com.mobiquityinc.packer.FileUtil;
import com.mobiquityinc.packer.Parser;
import com.mobiquityinc.packer.exceptions.APIException;
import com.mobiquityinc.packer.model.Item;
import com.mobiquityinc.packer.model.PackagedItem;
import com.mobiquityinc.packer.model.PackageOption;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Component
public class Packer {

    private FileUtil fileUtil;
    private Parser parser;

    @Autowired
    public Packer(FileUtil fileUtil, Parser parser) {
        this.fileUtil = fileUtil;
        this.parser = parser;
    }

    private PackagedItem getOptimizedPackageItem(PackageOption packageOption) {

        List<PackagedItem> packagedItems = new ArrayList<>();

        List<Item> candidateItemsList = packageOption.getCandidateItemsList();
        long set_size = candidateItemsList.size();
        long powerSetSize = (long)Math.pow(2, set_size);
        int counter, j;

        for(counter = 0; counter <
                powerSetSize; counter++)
        {
            PackagedItem packagedItem = new PackagedItem(packageOption.getWeightCapacity());
            try {

                for(j = 0; j < set_size; j++)
                {
                    if((counter & (1 << j)) > 0) {
                        packagedItem.addItem(candidateItemsList.get(j));
                    }
                }
                packagedItems.add(packagedItem);

            } catch (APIException e){
                //System.out.println(e.getMessage());
            }

        }

        return chooseBestPackage(packagedItems);

    }

    private PackagedItem chooseBestPackage(List<PackagedItem> packagedItems){

        PackagedItem bestItem = new PackagedItem(0);
        for (PackagedItem item : packagedItems){

            if (item.getPrice() > bestItem.getPrice() || (item.getPrice() == bestItem.getPrice() && item.getWeight() < bestItem.getWeight())){
                bestItem = item;
            }
        }
        return bestItem;
    }

    public String pack(String filePath) throws APIException {

        String data = "";
        try {

            data = fileUtil.read (filePath);
        } catch (IOException e) {
            e.printStackTrace();
        }

        List <PackageOption> packageOptions =  parser.parse(data);

        StringBuilder builder = new StringBuilder(data);
        for (PackageOption packageOption : packageOptions){
            PackagedItem packagedItem = getOptimizedPackageItem(packageOption);
            builder.append(packagedItem.toString()).append("\n");
        }
        return builder.toString();
    }

}

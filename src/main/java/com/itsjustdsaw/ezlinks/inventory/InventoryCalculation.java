package com.itsjustdsaw.ezlinks.inventory;

import com.itsjustdsaw.ezlinks.EzLinks;

import java.util.List;

public class InventoryCalculation {

    //Linking To Main To Access Config
    private EzLinks plugin;
    public InventoryCalculation(EzLinks instance) {
        this.plugin = instance;
    }

    public static int InventSize(List<LinkSites> numberOfLinks){

        int inventSize = 0;

        if(numberOfLinks.size() < 9){
            inventSize = 54;
        }

        return inventSize;
    }

}

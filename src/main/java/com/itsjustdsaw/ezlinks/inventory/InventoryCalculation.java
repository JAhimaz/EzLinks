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
        if(numberOfLinks.size() <= 54){
            if(numberOfLinks.size() <= 45){
                if(numberOfLinks.size() <= 36){
                    if(numberOfLinks.size() <= 27){
                        if(numberOfLinks.size() <= 18){
                            if(numberOfLinks.size() <= 9){
                                return 9;
                            }
                            return 18;
                        }
                        return 27;
                    }
                    return 36;
                }
                return 45;
            }
            return 54;
        }
        return 0;
    }

}

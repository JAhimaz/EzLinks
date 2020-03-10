package com.itsjustdsaw.ezlinks.misc;

import java.net.MalformedURLException;
import java.net.URL;

public class MiscFiles {
    public static String isEnabledString(Boolean bool){
        if(bool){
            return "Enabled";
        }else{
            return "Disabled";
        }
    }

    public static boolean urlChecker(String urlStr){
        try {
            URL url = new URL(urlStr);
            return true;
        }
        catch (MalformedURLException e) {
            return false;
        }
    }
}

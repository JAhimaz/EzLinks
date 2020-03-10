package com.itsjustdsaw.ezlinks.misc;

public class BooleanToToggled {
    public static String isEnabledString(Boolean bool){
        if(bool){
            return "Enabled";
        }else{
            return "Disabled";
        }
    }
}

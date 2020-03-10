package com.itsjustdsaw.ezlinks.inventory;

import org.bukkit.Material;

public class LinkSites {
    private String name;
    private String url;

    public LinkSites(String name, String url) {
        this.name = name;
        this.url = url;

    }

    public String getName() {
        return this.name;
    }

    public String getUrl() {
        return this.url;
    }

}

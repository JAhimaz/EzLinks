package com.itsjustdsaw.ezlinks.inventory;

public class LinkSites {
    private String name;
    private String url;
    private String material;

    public LinkSites(String name, String url, String material) {
        this.name = name;
        this.url = url;
        this.material = material;
    }

    public String getName() {
        return this.name;
    }

    public String getUrl() {
        return this.url;
    }

    public String getMaterial() {
        return this.material;
    }
}

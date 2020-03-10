package com.itsjustdsaw.ezlinks.inventory;

import com.itsjustdsaw.ezlinks.EzLinks;
import com.itsjustdsaw.ezlinks.misc.MiscFiles;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;

public class LinkInventory implements InventoryHolder, Listener {

    private List<LinkSites> websites;
    private Inventory linkMenu;

    //Linking To Main To Access Config
    private EzLinks plugin;

    public LinkInventory(EzLinks instance, int size, String title, List<LinkSites> sites) {
        this.plugin = instance;
        websites = sites;
        linkMenu = Bukkit.createInventory(this, size, title);
    }

    @Override
    public Inventory getInventory() {
        return linkMenu;
    }

    public void initializeItems() {
        for(int i = 0; i < websites.size(); i++){
            linkMenu.addItem(createLinkItem(websites.get(i)));
        }
    }

    private ItemStack createLinkItem(LinkSites website){
        Material websiteMaterial;
        if(MiscFiles.urlChecker(website.getUrl())){
            websiteMaterial = Material.EMERALD_BLOCK;

            ItemStack item = new ItemStack(websiteMaterial, 1);
            ItemMeta meta = item.getItemMeta();
            meta.setDisplayName("§f§l" + website.getName());
            meta.setLocalizedName(website.getName());
            item.setItemMeta(meta);

            return item;
        }else{
            websiteMaterial = Material.REDSTONE_BLOCK;
            List<String> lore = new ArrayList<>();
            lore.add("Invalid Website URL");

            ItemStack item = new ItemStack(websiteMaterial, 1);
            ItemMeta meta = item.getItemMeta();
            meta.setDisplayName("§f§l" + website.getName());
            meta.setLocalizedName(website.getName());
            meta.setLore(lore);
            item.setItemMeta(meta);

            return item;
        }
    }

    public void openInventory(Player p) {
        p.openInventory(linkMenu);
    }

    @EventHandler
    public void onInventoryClick(InventoryClickEvent e) {
        if (e.getInventory().getHolder() != this) {
            return;
        }
        if (e.getClick().equals(ClickType.NUMBER_KEY)){
            e.setCancelled(true);
        }
        e.setCancelled(true);

        Player p = (Player) e.getWhoClicked();
        ItemStack clickedItem = e.getCurrentItem();

        if (clickedItem == null || clickedItem.getType() == Material.AIR) return;

        for(int i = 0; i < websites.size(); i++){
            if(clickedItem.getItemMeta().getLocalizedName().equals(websites.get(i).getName())){
                p.sendMessage(ChatColor.BLUE + clickedItem.getItemMeta().getLocalizedName() + ":");
                p.sendMessage(ChatColor.UNDERLINE + websites.get(i).getUrl());
                e.getWhoClicked().closeInventory();
            }
        }
    }
}

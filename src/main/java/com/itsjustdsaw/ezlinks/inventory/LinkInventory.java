package com.itsjustdsaw.ezlinks.inventory;

import com.itsjustdsaw.ezlinks.EzLinks;
import org.bukkit.Bukkit;
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

    private Inventory linkMenu;

    //Linking To Main To Access Config
    private EzLinks plugin;

    public LinkInventory(EzLinks instance, int size, String title) {
        this.plugin = instance;
        linkMenu = Bukkit.createInventory(this, size, title);
    }

    @Override
    public Inventory getInventory() {
        return linkMenu;
    }

    public void initializeItems(List<LinkSites> websites) {
            for(int i = 0; i < websites.size(); i++){
            linkMenu.addItem(createLinkItem(websites.get(i)));
        }
    }

    private ItemStack createLinkItem(LinkSites website){
        ItemStack item = new ItemStack(website.getMaterial(), 1);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName("§f§l" + website.getName());
        meta.setLocalizedName(website.getName());
        item.setItemMeta(meta);

        return item;
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

        p.sendMessage("You clicked on " + clickedItem.getItemMeta().getDisplayName());
    }
}

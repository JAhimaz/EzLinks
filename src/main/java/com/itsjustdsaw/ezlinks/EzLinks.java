package com.itsjustdsaw.ezlinks;

import com.itsjustdsaw.ezlinks.inventory.InventoryCalculation;
import com.itsjustdsaw.ezlinks.inventory.LinkInventory;
import com.itsjustdsaw.ezlinks.inventory.LinkSites;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;
import java.util.List;

public final class EzLinks extends JavaPlugin {

    private static LinkInventory linksMenu;
    private List<LinkSites> sites = new ArrayList<LinkSites>();

    @Override
    public void onEnable() {
        // Plugin startup logic
        loadConfig();
        loadPlugin();
        enabledMessage();
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(command.getName().equals("links")){
            if(sender instanceof Player){
                Player player = (Player) sender;
                linksMenu.openInventory(player);
            }
        }
        return true;
    }

    public void loadConfig(){
        final FileConfiguration config = this.getConfig();

        getConfig().options().copyDefaults(true);
        //Placeholders

        saveDefaultConfig();
    }

    private void loadPlugin(){
        InventoryCalculation inventCalc = new InventoryCalculation(this);
        createLinkInventory();
        getServer().getPluginManager().registerEvents(linksMenu, this);
    }

    private void createLinkInventory() {
        String inventTitle = ChatColor.translateAlternateColorCodes('&', getConfig().getString("Link-Menu-Title").replace("%Server%", getServer().getIp()));


        for(String key : getConfig().getConfigurationSection("Links").getKeys(false)){
            sites.add(new LinkSites(key, getConfig().getString("Links." + key + ".LinkURL"), getConfig().getString("Links." + key + ".DisplayItem")));
        }

        int inventSize = InventoryCalculation.InventSize(sites);

        linksMenu = new LinkInventory(this, inventSize, inventTitle);
        linksMenu.initializeItems(sites);
    }

    private void enabledMessage(){
        System.out.println("=======================================================");
        System.out.println("Currently Linked Websites:");
        for(int i = 0; i < sites.size(); i++){
            System.out.println(sites.get(i).getName() + ": " + ChatColor.GREEN + "LOADED");
        }
        System.out.println("LinkMenu Has Been Enabled! Thank You For Using My Plugin");
        System.out.println("=======================================================");
    }
}

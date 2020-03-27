package com.itsjustdsaw.ezlinks;

import com.itsjustdsaw.ezlinks.inventory.InventoryCalculation;
import com.itsjustdsaw.ezlinks.inventory.LinkInventory;
import com.itsjustdsaw.ezlinks.inventory.LinkSites;
import com.itsjustdsaw.ezlinks.misc.MiscFiles;
import com.itsjustdsaw.ezlinks.player.PlayerJoinMessage;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
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
        siteCheck();
        System.out.println("LinkMenu Has Been Enabled! Thank You For Using My Plugin");
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(command.getName().equals("ezlinks")){
            if(sender instanceof ConsoleCommandSender){
                if(args.length == 0){
                    System.out.println("Sorry You Cannot Run This From The Console!");
                }else if (args.length == 1){
                    if (args[0].equalsIgnoreCase("reload")) {
                        sites.clear();
                        reloadConfig();
                        loadPlugin();
                        siteCheck();
                        System.out.println(ChatColor.translateAlternateColorCodes('&', getConfig().getString("plugin-prefix")) + " Reloaded The Config!");
                    }else if (args[0].equalsIgnoreCase("help")) {
                        System.out.println(ChatColor.AQUA + "\n==========================================");
                        System.out.println(ChatColor.AQUA + "/links|ezlinks|urls");
                        System.out.println(ChatColor.AQUA + "/links|ezlinks|urls reload");
                        System.out.println(ChatColor.AQUA + "/links|ezlinks|urls help");
                        System.out.println(ChatColor.AQUA + "==========================================\n");
                    }
                }
            }
            if(sender instanceof Player){
                Player player = (Player) sender;
                if(args.length == 0){
                    linksMenu.openInventory(player);
                }
                else if(args.length == 1){
                    if (args[0].equalsIgnoreCase("reload") && player.hasPermission("ezlinks.reload")) {
                        sites.clear();
                        reloadConfig();
                        loadPlugin();
                        siteCheck();
                        player.sendMessage(ChatColor.translateAlternateColorCodes('&', getConfig().getString("plugin-prefix")) + " Reloaded The Config!");
                    }else if(args[0].equalsIgnoreCase("reload") && !(player.hasPermission("ezlinks.reload"))){
                        player.sendMessage(ChatColor.RED + "Sorry, You Don't Have Permission To Access That Command!");
                    }
                    if (args[0].equalsIgnoreCase("help")) {
                        player.sendMessage(ChatColor.AQUA + "\n==========================================\n");
                        player.sendMessage(ChatColor.AQUA + "EzLinks v1.0.0 by JAhimaz\n");
                        player.sendMessage(ChatColor.AQUA + "/links|ezlinks|urls");
                        player.sendMessage(ChatColor.AQUA + "/links|ezlinks|urls reload");
                        player.sendMessage(ChatColor.AQUA + "/links|ezlinks|urls help");
                        player.sendMessage(ChatColor.AQUA + "\n==========================================\n");
                    }
                }else{
                    player.sendMessage("Invalid Command? Do /ezlinks help for Commands");
                }
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
        PlayerJoinMessage playerJoin = new PlayerJoinMessage(this);
        createLinkInventory();
        getServer().getPluginManager().registerEvents(linksMenu, this);
        getServer().getPluginManager().registerEvents(playerJoin, this);
    }

    private void createLinkInventory() {
        String inventTitle = ChatColor.translateAlternateColorCodes('&', getConfig().getString("link-menu-title"));

        for(String key : getConfig().getConfigurationSection("Links").getKeys(false)){
            sites.add(new LinkSites(key, getConfig().getString("Links." + key + ".LinkURL")));
        }

        int inventSize = InventoryCalculation.InventSize(sites);

        linksMenu = new LinkInventory(this, inventSize, inventTitle, sites);
        linksMenu.initializeItems();
    }

    private void siteCheck(){
        System.out.println("=======================================================");
        System.out.println("Currently Linked Websites:");
        for(int i = 0; i < sites.size(); i++){
            if(MiscFiles.urlChecker(sites.get(i).getUrl())){
                System.out.println(sites.get(i).getName() + ": " + ChatColor.GREEN + "LOADED");
            }else{
                System.out.println(sites.get(i).getName() + ": " + ChatColor.RED + "NOT CONFIGURED PROPERLY");
            }
        }
        System.out.println("=======================================================");
    }


}

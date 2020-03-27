package com.itsjustdsaw.ezlinks.player;

import com.itsjustdsaw.ezlinks.EzLinks;
import com.itsjustdsaw.ezlinks.inventory.LinkSites;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import java.util.List;

public class PlayerJoinMessage implements Listener {

    private EzLinks plugin;

    public PlayerJoinMessage(EzLinks instance) {
        this.plugin = instance;
    }

    @EventHandler
    public void OnPlayerJoin(PlayerJoinEvent e){
        if(plugin.getConfig().getBoolean("enable-join-message")){
            String joinMessage = ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString("join-message"));

            Player player = e.getPlayer();
            player.sendMessage(joinMessage);
        }
    }

}

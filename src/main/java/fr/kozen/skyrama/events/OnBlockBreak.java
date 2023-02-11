package fr.kozen.skyrama.events;

import fr.kozen.skyrama.Skyrama;
import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;

public class OnBlockBreak implements Listener {

    @EventHandler
    public void onBlockBreak(BlockBreakEvent event) {

        if(Skyrama.getGridManager().isInPlayerIsland(event.getPlayer(), event.getBlock().getLocation()) == 1) {
            if(event.getPlayer().hasPermission(Skyrama.getPermissionsManager().getString("island-perm-break")) || event.getPlayer().hasPermission(Skyrama.getPermissionsManager().getString("island-perm-admin"))){

                event.setCancelled(false);
            }else{
                event.getPlayer().sendMessage(Skyrama.getLocaleManager().getString("player-break"));
                event.setCancelled(true);
            }
        }
    }
}
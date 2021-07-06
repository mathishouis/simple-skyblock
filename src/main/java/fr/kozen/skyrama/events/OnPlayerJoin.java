package fr.kozen.skyrama.events;

import fr.kozen.skyrama.Skyrama;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.player.PlayerJoinEvent;

public class OnPlayerJoin implements Listener {

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {

        if(Skyrama.getPlugin(Skyrama.class).getConfig().getBoolean("island.spawnIslandLogin")) {
            if (Skyrama.getIslandManager().getPlayerIsland(event.getPlayer()) != null) {
                Skyrama.getIslandManager().getPlayerIsland(event.getPlayer()).getSpawn().setWorld(Bukkit.getWorld((String) Skyrama.getPlugin(Skyrama.class).getConfig().get("general.world")));
                event.getPlayer().teleport(Skyrama.getIslandManager().getPlayerIsland(event.getPlayer()).getSpawn());
            }
        }

    }

}

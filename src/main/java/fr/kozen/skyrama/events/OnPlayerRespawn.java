package fr.kozen.skyrama.events;

import fr.kozen.skyrama.Skyrama;
import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerRespawnEvent;

public class OnPlayerRespawn implements Listener {

    @EventHandler
    public void onPlayerRespawn(PlayerRespawnEvent event) {

        if(Skyrama.getPlugin(Skyrama.class).getConfig().getBoolean("island.respawnIsland")) {
            if (Skyrama.getIslandManager().getPlayerIsland(event.getPlayer()) != null) {
                Skyrama.getIslandManager().getPlayerIsland(event.getPlayer()).getSpawn().setWorld(Bukkit.getWorld((String) Skyrama.getPlugin(Skyrama.class).getConfig().get("general.world")));
                event.setRespawnLocation(Skyrama.getIslandManager().getPlayerIsland(event.getPlayer()).getSpawn());
                event.getPlayer().teleport(Skyrama.getIslandManager().getPlayerIsland(event.getPlayer()).getSpawn());
            }
        }

    }

}

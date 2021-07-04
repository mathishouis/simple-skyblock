package fr.kozen.skyrama.events;

import fr.kozen.skyrama.Skyrama;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityTargetEvent;

public class OnEntityDamageByEntity implements Listener {

    @EventHandler
    public void onEntityAttack(EntityDamageByEntityEvent event) {

        if(event.getDamager() instanceof Player) {

            Player player = ((Player) event.getDamager()).getPlayer();

            assert player != null;
            if(!Skyrama.getGridManager().isInPlayerIsland(player, player.getLocation())) {
                event.setCancelled(true);
            }

        }


    }

}

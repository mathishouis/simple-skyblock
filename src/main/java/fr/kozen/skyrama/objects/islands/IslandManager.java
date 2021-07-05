package fr.kozen.skyrama.objects.islands;

import fr.kozen.skyrama.Skyrama;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.block.Biome;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.Map;

public class IslandManager {

    public Map<Integer, Island> islands;

    public IslandManager() { this.initialise(); };

    public void initialise() {

        this.islands = new HashMap<>();

    }

    public void loadIslands() {

        this.islands = IslandDao.getIslands();

    }

    public void create(Player owner) {

        int islandId = IslandDao.addIsland();
        IslandDao.addPlayer(owner, islandId, 2);

        Location location = Skyrama.getGridManager().getCenterFromId(islandId);
        Location spawn = new Location(Bukkit.getWorld((String) Skyrama.getPlugin(Skyrama.class).getConfig().get("general.world")), location.getBlockX(), 100, location.getBlockZ() + 2);

        Bukkit.getLogger().info("x: " + spawn.getX());
        Bukkit.getLogger().info("y: " + spawn.getY());
        Bukkit.getLogger().info("z: " + spawn.getZ());

        Island island = new Island(islandId, Biome.BADLANDS, 0, spawn);
        this.islands.put(islandId, island);

        owner.sendMessage(ChatColor.GREEN + "Creating island...");

        island.setSpawn(spawn);
        island.save();


        Skyrama.getSchematicManager().load((String) Skyrama.getPlugin(Skyrama.class).getConfig().get("island.schematic"), Bukkit.getWorld((String) Skyrama.getPlugin(Skyrama.class).getConfig().get("general.world")), location.getX(), location.getY(), location.getZ());
        owner.teleport(spawn);

    }

}

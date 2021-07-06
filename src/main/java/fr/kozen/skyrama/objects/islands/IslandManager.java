package fr.kozen.skyrama.objects.islands;

import fr.kozen.skyrama.Skyrama;
import fr.kozen.skyrama.types.Rank;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.OfflinePlayer;
import org.bukkit.block.Biome;
import org.bukkit.entity.Player;

import java.util.*;

public class IslandManager {

    public Set<Island> islands;

    public IslandManager() { this.initialise(); };

    public void initialise() {

        this.islands = new HashSet<>();

    }

    public void loadIslands() {

        this.islands = IslandDao.getIslands();
        this.islands.forEach(island -> Bukkit.getLogger().info(String.valueOf(island.getId())));

    }

    public void create(OfflinePlayer owner) {

        int islandId = IslandDao.addIsland();

        Location location = Skyrama.getGridManager().getCenterFromId(islandId);
        Location spawn = new Location(Bukkit.getWorld(Skyrama.getPlugin(Skyrama.class).getConfig().getString("general.world")), location.getBlockX(), 100, location.getBlockZ() + 2);

        Bukkit.getLogger().info("x: " + spawn.getX());
        Bukkit.getLogger().info("y: " + spawn.getY());
        Bukkit.getLogger().info("z: " + spawn.getZ());

        Island island = new Island(islandId, Biome.BADLANDS, 0, spawn);
        this.islands.add(island);
        island.addPlayer(owner, Rank.OWNER);

        owner.getPlayer().sendMessage(ChatColor.GREEN + "Creating island...");

        island.setSpawn(spawn);
        island.save();


        Skyrama.getSchematicManager().load(Skyrama.getPlugin(Skyrama.class).getConfig().getString("island.schematic"), location.getX(), location.getY(), location.getZ());
        owner.getPlayer().teleport(spawn);

    }

    public Island getIslandOwnedBy(OfflinePlayer player) {

        return this.getIslands().stream().filter(island -> island.getOwner().equals(player)).findAny().orElse(null);

    }

    public Island getPlayerIsland(OfflinePlayer player) {

        return this.getIslands().stream().filter(island -> island.getPlayers().containsKey(player)).findAny().orElse(null);

    }

    public Set<Island> getIslands() {

        return this.islands;

    }

}

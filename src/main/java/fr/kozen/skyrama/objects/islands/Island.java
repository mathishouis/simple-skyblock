package fr.kozen.skyrama.objects.islands;

import fr.kozen.skyrama.types.Rank;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.OfflinePlayer;
import org.bukkit.block.Biome;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Island {

    private int id;
    private Biome biome;
    private int extensionLevel;
    private Map<OfflinePlayer, Rank> players;
    private Location spawn;
    private Map<Player, Player> invites;

    public Island(int id, Biome biome, int extensionLevel, Location spawn) {

        this.id = id;
        this.biome = biome;
        this.extensionLevel = extensionLevel;
        this.players = IslandDao.getPlayers(id);
        this.spawn = spawn;
        this.invites = new HashMap<>();
        //Bukkit.getLogger().info(this.getOwner().getName());

    }

    public int getId() {

        return this.id;

    }

    public Biome getBiome() {

        return this.biome;

    }

    public int getExtensionLevel() {

        return this.extensionLevel;

    }

    public OfflinePlayer getOwner() {

        return this.getPlayers().entrySet().stream().filter(entry -> entry.getValue().equals(Rank.OWNER)).findAny().get().getKey();

    }

    public Rank getRank(OfflinePlayer player) {

        return this.getPlayers().get(player);

    }

    public void removePlayer(OfflinePlayer player) {

        this.getPlayers().remove(player);
        IslandDao.removePlayer(player);

    }

    public void addPlayer(OfflinePlayer player, Rank rank) {

        this.getPlayers().put(player, rank);
        IslandDao.addPlayer(player, this, rank);

    }


    public Map<OfflinePlayer, Rank> getPlayers() {

        return this.players;

    }

    public Map<Player, Player> getInvites() {

        return this.invites;

    }

    public Location getSpawn() {

        return this.spawn;

    }

    public void setSpawn(Location spawn) {

        this.spawn = spawn;

    }

    public void save() {
        IslandDao.save(this);
    }


}

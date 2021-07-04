package fr.kozen.skyrama.objects.islands;

import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.block.Biome;
import org.bukkit.entity.Player;

import java.util.List;
import java.util.UUID;

public class Island {

    private int id;
    private Biome biome;
    private int extensionLevel;
    private List<Player> players;
    private Location spawn;

    public Island(int id, Biome biome, int extensionLevel, Location spawn) {

        this.id = id;
        this.biome = biome;
        this.extensionLevel = extensionLevel;
        this.players = IslandDao.getPlayers(id);
        this.spawn = spawn;

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

    public List<Player> getPlayers() {

        return this.players;

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

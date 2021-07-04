package fr.kozen.skyrama.objects.islands;

import fr.kozen.skyrama.Skyrama;
import fr.kozen.skyrama.storage.SqlManager;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.block.Biome;
import org.bukkit.entity.Player;

import java.sql.*;
import java.util.*;

public class IslandDao {

    public static Map<Integer, Island> getIslands() {

        Bukkit.getLogger().info("Gettings islands...");

        Map<Integer, Island> islands = new HashMap<>();

        try (Connection conn = Skyrama.getSqlManager().getConnection(); PreparedStatement stmt = conn.prepareStatement(
                "SELECT * FROM islands"
        )) {
            ResultSet resultSet = stmt.executeQuery();
            while (resultSet.next()) {
                islands.put(resultSet.getInt("id"), new Island(
                        resultSet.getInt("id"),
                        Biome.BAMBOO_JUNGLE,
                        resultSet.getInt("extension_level"),
                        new Location(Bukkit.getWorld((String) Skyrama.getPlugin(Skyrama.class).getConfig().get("general.world")), resultSet.getFloat("spawn_x"), resultSet.getFloat("spawn_y"), resultSet.getFloat("spawn_z"), resultSet.getFloat("spawn_yaw"), resultSet.getFloat("spawn_pitch"))
                        ));
            }

        } catch (SQLException e) {
            Bukkit.getLogger().info("Something went wrong. " + e);
        }

        return islands;
    }

    public static List<Player> getPlayers(int islandId) {

        List<Player> players = new ArrayList<>();

        try (Connection conn = Skyrama.getSqlManager().getConnection(); PreparedStatement stmt = conn.prepareStatement(
                "SELECT * FROM islands_users WHERE island_id = ?"
        )) {
            stmt.setInt(1, islandId);
            ResultSet resultSet = stmt.executeQuery();
            while (resultSet.next()) {
                players.add(Bukkit.getPlayer(UUID.fromString(resultSet.getString("uuid"))));
            }

        } catch (SQLException e) {
            Bukkit.getLogger().info("Something went wrong. " + e);
        }

        return players;

    }

    public static Island getIslandById(int islandId) {

        List<Player> players = new ArrayList<>();

        try (Connection conn = Skyrama.getSqlManager().getConnection(); PreparedStatement stmt = conn.prepareStatement(
                "SELECT * FROM islands WHERE id = ?"
        )) {
            stmt.setInt(1, islandId);
            ResultSet resultSet = stmt.executeQuery();
           if (resultSet.next()) {
                return new Island(
                        resultSet.getInt("id"),
                        Biome.BADLANDS,
                        resultSet.getInt("extension_level"),
                        new Location(Bukkit.getWorld((String) Skyrama.getPlugin(Skyrama.class).getConfig().get("general.world")), resultSet.getFloat("spawn_x"), resultSet.getFloat("spawn_y"), resultSet.getFloat("spawn_z"), resultSet.getFloat("spawn_yaw"), resultSet.getFloat("spawn_pitch"))
                );
            }

        } catch (SQLException e) {
            Bukkit.getLogger().info("Something went wrong. " + e);
        }

        return null;

    }

    public static int addIsland() {

        try (Connection conn = Skyrama.getSqlManager().getConnection(); PreparedStatement stmt = conn.prepareStatement(
                "INSERT INTO islands(biome, extension_level) VALUES(?, ?);",
                Statement.RETURN_GENERATED_KEYS)) {
            stmt.setString(1, "DEFAULT");
            stmt.setInt(2, 0);
            stmt.executeUpdate();


            try (ResultSet generatedKeys = stmt.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    return generatedKeys.getInt(1);
                }
                else {
                    throw new SQLException("Creating user failed, no ID obtained.");
                }
            }
        } catch (SQLException e) {
            Bukkit.getLogger().info("Something went wrong. " + e);
        }

        return -1;

    }

    public static void save(Island island) {

        try (Connection conn = Skyrama.getSqlManager().getConnection(); PreparedStatement stmt = conn.prepareStatement(
                "UPDATE islands SET biome = ?, extension_level = ?, spawn_x = ?, spawn_y = ?, spawn_z = ?, spawn_yaw = ?, spawn_pitch = ? WHERE id = ?;"
        )) {
            stmt.setString(1, String.valueOf(island.getBiome()));
            stmt.setInt(2, island.getExtensionLevel());
            stmt.setFloat(3, island.getSpawn().getBlockX());
            stmt.setFloat(4, island.getSpawn().getBlockY());
            stmt.setFloat(5, island.getSpawn().getBlockZ());
            stmt.setFloat(6, island.getSpawn().getYaw());
            stmt.setFloat(7, island.getSpawn().getPitch());
            stmt.setInt(8, island.getId());

            stmt.executeUpdate();

        } catch (SQLException e) {
            Bukkit.getLogger().info("Something went wrong. " + e);
        }

    }

    public static void addPlayer(Player player, int islandId, int rank) {

        try (Connection conn = Skyrama.getSqlManager().getConnection(); PreparedStatement stmt = conn.prepareStatement(
                "INSERT INTO islands_users(uuid, island_id, rank) VALUES(?, ?, ?);")) {
            stmt.setString(1, player.getUniqueId().toString());
            stmt.setInt(2, islandId);
            stmt.setInt(3, rank);
            stmt.execute();

        } catch (SQLException e) {
            Bukkit.getLogger().info("Something went wrong. " + e);
        }

    }

    public static boolean haveIsland(Player player) {

        try (Connection conn = Skyrama.getSqlManager().getConnection(); PreparedStatement stmt = conn.prepareStatement(
                "SELECT * FROM islands_users WHERE uuid = ?"
        )) {
            stmt.setString(1, player.getUniqueId().toString());
            ResultSet resultSet = stmt.executeQuery();
            if (resultSet.next()) {
                return true;
            }

        } catch (SQLException e) {
            Bukkit.getLogger().info("Something went wrong. " + e);
        }

        return false;

    }

    public static Island getIslandByPlayer(Player player) {

        try (Connection conn = Skyrama.getSqlManager().getConnection(); PreparedStatement stmt = conn.prepareStatement(
                "SELECT * FROM islands_users WHERE uuid = ?"
        )) {
            stmt.setString(1, player.getUniqueId().toString());
            ResultSet resultSet = stmt.executeQuery();
            if (resultSet.next()) {
                return Skyrama.getIslandManager().islands.get(resultSet.getInt("island_id"));
            }

        } catch (SQLException e) {
            Bukkit.getLogger().info("Something went wrong. " + e);
        }

        return null;

    }
}

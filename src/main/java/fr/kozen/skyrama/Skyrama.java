package fr.kozen.skyrama;

import fr.kozen.skyrama.commands.CommandManager;
import fr.kozen.skyrama.events.*;
import fr.kozen.skyrama.objects.grids.GridManager;
import fr.kozen.skyrama.objects.islands.IslandManager;
import fr.kozen.skyrama.objects.schematics.SchematicManager;
import fr.kozen.skyrama.storage.SqlManager;
import org.bukkit.plugin.java.JavaPlugin;

public final class Skyrama extends JavaPlugin {

    private static GridManager gridManager;
    private static SqlManager sqlManager;
    private static IslandManager islandManager;
    private static SchematicManager schematicManager;

    @Override
    public void onEnable() {

        this.initConfig();
        this.initObjects();
        this.initEvents();
        this.initCommands();

    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    public void initConfig() {

        getConfig().options().copyDefaults(true);
        saveConfig();

    }

    public void initCommands() {

        getCommand("island").setExecutor(new CommandManager());

    }

    public void initObjects() {

        gridManager = new GridManager();
        sqlManager = new SqlManager();
        islandManager = new IslandManager();
        schematicManager = new SchematicManager();

        islandManager.loadIslands();

    }

    public void initEvents() {

        getServer().getPluginManager().registerEvents(new OnBlockBreak(), this);
        getServer().getPluginManager().registerEvents(new OnBlockPlace(), this);
        getServer().getPluginManager().registerEvents(new OnEntityTarget(), this);
        getServer().getPluginManager().registerEvents(new OnPlayerDamage(), this);
        getServer().getPluginManager().registerEvents(new OnEntityDamageByEntity(), this);
        getServer().getPluginManager().registerEvents(new OnBlockClick(), this);

    }

    public static GridManager getGridManager() {

        return gridManager;

    }

    public static SqlManager getSqlManager() {

        return sqlManager;

    }

    public static IslandManager getIslandManager() {

        return islandManager;

    }

    public static SchematicManager getSchematicManager() {

        return schematicManager;

    }

}
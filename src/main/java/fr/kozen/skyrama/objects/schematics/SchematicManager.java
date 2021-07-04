package fr.kozen.skyrama.objects.schematics;


import com.sk89q.worldedit.EditSession;
import com.sk89q.worldedit.WorldEdit;
import com.sk89q.worldedit.WorldEditException;
import com.sk89q.worldedit.bukkit.BukkitAdapter;
import com.sk89q.worldedit.extent.clipboard.Clipboard;
import com.sk89q.worldedit.extent.clipboard.io.ClipboardFormat;
import com.sk89q.worldedit.extent.clipboard.io.ClipboardFormats;
import com.sk89q.worldedit.extent.clipboard.io.ClipboardReader;
import com.sk89q.worldedit.function.operation.Operation;
import com.sk89q.worldedit.function.operation.Operations;
import com.sk89q.worldedit.math.BlockVector3;
import com.sk89q.worldedit.session.ClipboardHolder;
import fr.kozen.skyrama.Skyrama;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.plugin.Plugin;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Objects;

public class SchematicManager {

    public SchematicManager() { this.initialise(); }

    public void initialise() {

        Plugin worldEditPlugin = Bukkit.getPluginManager().getPlugin("WorldEdit");

    }

    public void load(String name, World world, double x, double y, double z) {

        File file = new File(Skyrama.getPlugin(Skyrama.class).getDataFolder() + File.separator + "/schematics/"+name+".schematic");
        ClipboardFormat format = ClipboardFormats.findByFile(file);


            try (ClipboardReader reader = format.getReader(new FileInputStream(file))) {
                Clipboard clipboard = reader.read();
                try(EditSession editSession = WorldEdit.getInstance().getEditSessionFactory().getEditSession(BukkitAdapter.adapt(Objects.requireNonNull(Bukkit.getWorld((String) Skyrama.getPlugin(Skyrama.class).getConfig().get("general.world")))), -1)) {
                    Operation operation = new ClipboardHolder(clipboard)
                            .createPaste(editSession)
                            .to(BlockVector3.at(x - Math.floor(clipboard.getDimensions().getX() / 2), y  - Math.floor(clipboard.getDimensions().getY() / 2), z  - Math.floor(clipboard.getDimensions().getZ() / 2)))
                            .ignoreAirBlocks(false)
                            .build();
                    Operations.complete(operation);
                    Bukkit.getLogger().info("complete");
                } catch (WorldEditException e) {
                    e.printStackTrace();
                }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}

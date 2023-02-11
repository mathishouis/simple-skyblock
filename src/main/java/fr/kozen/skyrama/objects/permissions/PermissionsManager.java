package fr.kozen.skyrama.objects.permissions;

import fr.kozen.skyrama.Skyrama;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;

public class PermissionsManager {

        private YamlConfiguration file;

        public PermissionsManager() { this.initialise(); }

        public void initialise() {

            this.file = YamlConfiguration.loadConfiguration(new File("permissions.yml"));

        }

        public String getString(String path) {
            return this.file.getString(path);
        }
    }
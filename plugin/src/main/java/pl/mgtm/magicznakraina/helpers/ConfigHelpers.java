package pl.mgtm.magicznakraina.helpers;

import org.bukkit.configuration.file.FileConfiguration;
import pl.mgtm.magicznakraina.MagicznaKraina;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class ConfigHelpers {
    public ConfigHelpers () {
        throw new RuntimeException("Cannot initialize helper class!");
    }

    public static void createDefaultPlayerConfig(UUID playerUUID) {
        MagicznaKraina plugin = MagicznaKraina.getInstance();

        double defaultHearts = plugin.getConfig().getDouble("defaultHearts");

        FileConfiguration cfg = plugin.getConfig();

        if (!cfg.contains("users." + playerUUID)) {
            cfg.createSection("users." + playerUUID);
        }

        if (!cfg.contains("users." + playerUUID + ".hearts")) {
            cfg.set("users." + playerUUID + ".hearts", defaultHearts);
        }
        if (!cfg.contains("users." + playerUUID + ".heartsLevel")) {
            cfg.set("users." + playerUUID + ".heartsLevel", 0);
        }
        if (!cfg.contains("users." + playerUUID + ".zeroHeartsBanned")) {
            cfg.set("users." + playerUUID + ".zeroHeartsBanned", false);
        }


        plugin.saveConfig();
    }
    public static void addPlayerKit(UUID playerUUID, String kitname) {
        MagicznaKraina plugin = MagicznaKraina.getInstance();

        if (!plugin.getConfig().contains("users." + playerUUID)) {
            ConfigHelpers.createDefaultPlayerConfig(playerUUID);
        }

        List<String> kits = new ArrayList<>();

        if (plugin.getConfig().contains("users." + playerUUID + ".kits")) {
            kits = (List<String>) plugin.getConfig().getList("users." + playerUUID + ".kits");
        }

        if (!kits.contains(kitname)) {
            kits.add(kitname);
        }

        // Save new data
        plugin.getConfig().set("users." + playerUUID + ".kits", kits);

        // Save config
        plugin.saveConfig();
    }


    public static boolean playerUsedKit(UUID playerUUID, String kitname) {
        MagicznaKraina plugin = MagicznaKraina.getInstance();

        if (!plugin.getConfig().contains("users." + playerUUID)) {
            ConfigHelpers.createDefaultPlayerConfig(playerUUID);
        }

        List<String> kits = new ArrayList<>();

        if (plugin.getConfig().contains("users." + playerUUID + ".kits")) {
            kits = (List<String>) plugin.getConfig().getList("users." + playerUUID + ".kits");
        }

        if (!kits.contains(kitname)) {
            return false;
        }

        return true;
    }

    public static void setPlayerZeroHeartsBan(UUID playerUUID, boolean state) {
        MagicznaKraina plugin = MagicznaKraina.getInstance();

        if (!userExist(playerUUID)) {
            ConfigHelpers.createDefaultPlayerConfig(playerUUID);
        }

        // Save new data
        plugin.getConfig().set("users." + playerUUID + ".zeroHeartsBanned", state);

        // Save config
        plugin.saveConfig();
    }

    public static boolean getPlayerZeroHeartsBan(UUID playerUUID) {
        MagicznaKraina plugin = MagicznaKraina.getInstance();

        if (!userExist(playerUUID)) {
            ConfigHelpers.createDefaultPlayerConfig(playerUUID);
        }

        // Save new data
        return plugin.getConfig().getBoolean("users." + playerUUID + ".zeroHeartsBanned");
    }

    private static boolean userExist(UUID playerUUID) {
        MagicznaKraina plugin = MagicznaKraina.getInstance();
        return plugin.getConfig().contains("users." + playerUUID);
    }

}

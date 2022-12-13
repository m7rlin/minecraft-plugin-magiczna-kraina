package pl.mgtm.magicznakraina.helpers;

import pl.mgtm.magicznakraina.MagicznaKraina;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class ConfigHelpers {

    public static void createDefaultPlayerConfig(UUID playerUUID) {
        MagicznaKraina plugin = MagicznaKraina.getInstance();

        double defaultHearts = plugin.getConfig().getDouble("defaultHearts");

        if (!plugin.getConfig().contains("users." + playerUUID + ".hearts")) {
            plugin.getConfig().set("users." + playerUUID + ".hearts", defaultHearts);
            plugin.getConfig().set("users." + playerUUID + ".heartsLevel", 0);

            plugin.saveConfig();
        }
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

        if (!kits.contains(kitname)) { kits.add(kitname); }

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

        if (!kits.contains(kitname)) { return false; }

        return true;
    }

}

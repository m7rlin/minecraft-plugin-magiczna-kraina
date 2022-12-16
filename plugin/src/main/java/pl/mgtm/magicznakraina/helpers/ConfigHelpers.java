package pl.mgtm.magicznakraina.helpers;

import org.bukkit.Material;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Item;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;
import pl.mgtm.magicznakraina.MagicznaKraina;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class ConfigHelpers {
    public ConfigHelpers () {
        throw new RuntimeException("Cannot initialize helper class!");
    }

    public static void loadConfig() {
        MagicznaKraina plugin = MagicznaKraina.getInstance();
        plugin.getConfig().options().copyDefaults(true);
        plugin.saveConfig();

        plugin.setConfig(plugin.getConfig());
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
    public static void createDefaultPlayerReviveConfig() {
        MagicznaKraina plugin = MagicznaKraina.getInstance();

        String key = "playerRevive";

        FileConfiguration cfg = plugin.getConfig();

        if (!cfg.contains(key)) {
           // cfg.createSection(key);
            cfg.options().copyDefaults(true);
            loadConfig();
            return;
        }

        if (!cfg.contains(key + ".enabled") || !cfg.contains(key + ".banOnZeroHearts") || !cfg.contains(key + ".items") || !cfg.contains(key + ".level")) {
            loadConfig();
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

    public static boolean userExist(UUID playerUUID) {
        MagicznaKraina plugin = MagicznaKraina.getInstance();
        return plugin.getConfig().contains("users." + playerUUID);
    }

    public static List<String> getPlayerReviveItemsList() {
        MagicznaKraina plugin = MagicznaKraina.getInstance();

        ConfigHelpers.createDefaultPlayerReviveConfig();

        List<String> itemsList = (List<String>) plugin.getConfig().getList("playerRevive.items");

        return itemsList;
    }

    public static ArrayList<ItemStack> getPlayerReviveItems() {
        MagicznaKraina plugin = MagicznaKraina.getInstance();

        List<String> itemsList = getPlayerReviveItemsList();

        // Maksymalnie 10 przedmiotow
        ArrayList<ItemStack> items = new ArrayList<ItemStack>();

        for (String x : itemsList) {
            String materialName = x.split(",")[0];
            Material itemMaterial = Material.matchMaterial(materialName);

            if (itemMaterial != null) {
                Integer amount;

                try {
                    amount =  Integer.parseInt(x.split(",")[1]);
                } catch (NumberFormatException e) {
                    amount = 1;
                }

                items.add(new ItemStack(itemMaterial, amount));
            }
        }

        return items;
    }

    public static int getPlayerReviveLevel() {
        MagicznaKraina plugin = MagicznaKraina.getInstance();

        ConfigHelpers.createDefaultPlayerReviveConfig();

        return plugin.getConfig().getInt("playerRevive.level", 50);
    }

}

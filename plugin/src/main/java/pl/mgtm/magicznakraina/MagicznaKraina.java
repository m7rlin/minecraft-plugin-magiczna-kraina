package pl.mgtm.magicznakraina;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import pl.mgtm.magicznakraina.commands.*;
import pl.mgtm.magicznakraina.events.DeathEvent;
import pl.mgtm.magicznakraina.events.JoinServerEvent;
import pl.mgtm.magicznakraina.events.RespawnEvent;
import pl.mgtm.magicznakraina.modules.protect_chests.ProtectChests;

import java.util.ArrayList;
import java.util.HashMap;

public final class MagicznaKraina extends JavaPlugin {

    private static MagicznaKraina instance;

    private FileConfiguration config;

    // Przechowuje wszystkich graczy, do ktorych chcemy sie przeteleportowac
    // Player = gracz do ktorego chca sie przeteleportowac np. kris
    // ArrayList<Player> = lista graczy, ktorzy wyslali prosbe o teleportacje
    private HashMap<Player, ArrayList<Player>> tpa = new HashMap<Player, ArrayList<Player>>();

    // Lokalizacja spawnu serwera
    private Location spawnLocation;

    private HashMap<String, String> messages = new HashMap<>();

    // Wyswietla pomocnicze informacje
    // Przydatne podczas tworzenia pluginow
    private boolean debug = false;


    @Override
    public void onEnable() {
        // ===================================================== //
        //                                                       //
        //                       Plugin info                     //
        //                                                       //
        // ===================================================== //

        String pluginAuthor = getDescription().getAuthors().toString()
                .replace("[", "")  // remove the right bracket
                .replace("]", "")  // remove the left bracket
                .trim();           // remove trailing spaces from partially initialized arrays

        // Plugin startup logic
        getLogger().info(ChatColor.GREEN + "Plugin " + getName() + " v" + getDescription().getVersion() + " loading...");
        getLogger().info("Author: " + pluginAuthor);

        setInstance(this);

        getServer().getPluginManager().registerEvents(new JoinServerEvent(), this);
        getServer().getPluginManager().registerEvents(new RespawnEvent(), this);
        getServer().getPluginManager().registerEvents(new DeathEvent(), this);
        //getServer().getPluginManager().registerEvents(new ExplosiveArrowEvent(), this);
        //getServer().getPluginManager().registerEvents(new SuperPickaxeEvent(), this);

        getCommand("tpa").setExecutor(new TpaCommand());
        getCommand("tpaccept").setExecutor(new TpaCommand());
        getCommand("tpdeny").setExecutor(new TpaCommand());
        getCommand("serduszko").setExecutor(new SerduszkoCommand());
        getCommand("sethome").setExecutor(new SetHomeCommand());
        getCommand("home").setExecutor(new HomeCommand());
        getCommand("spawn").setExecutor(new SpawnCommand());
        getCommand("setspawn").setExecutor(new SetSpawnCommand());
        getCommand("enderchest").setExecutor(new EnderchestCommand());
        getCommand("kit").setExecutor(new KitCommand());
        getCommand("heal").setExecutor(new HealCommand());
        getCommand("broadcast").setExecutor(new BroadcastCommand());

        // ===================================================== //
        //                                                       //
        //                  Rejestracja modulow                  //
        //                                                       //
        // ===================================================== //

        // ProtectChests
        new ProtectChests();

        // Zaladuj config
        loadConfig();
        // Zaladuj spawn
        loadSpawn();

        messages.put("leaveMessage", ChatColor.translateAlternateColorCodes('&', getConfig().getString("message.leaveMessage")));
        messages.put("joinMessage", ChatColor.translateAlternateColorCodes('&', getConfig().getString("message.joinMessage")));

        // Get debug informations
        debug = getConfig().getBoolean("debug");

        getLogger().info(ChatColor.GREEN + "Plugin loaded!");



    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
        getLogger().info(ChatColor.GREEN + "Plugin shutdown.");
    }

    private void loadSpawn() {
        if (config.contains("spawn.x")) {
            String worldName = config.getString("spawn.world");
            double x = config.getDouble("spawn.x");
            double y = config.getDouble("spawn.y");
            double z = config.getDouble("spawn.z");
            float pitch = (float) config.getDouble("spawn.pitch");
            float yaw = (float) config.getDouble("spawn.yaw");

            spawnLocation = new Location(getServer().getWorld(worldName), x, y, z, yaw, pitch);
        }
    }

    private void loadConfig() {
        getConfig().options().copyDefaults(true);
        saveConfig();

        config = getConfig();
    }

    // Get plugin instance
    public static MagicznaKraina getInstance() {
        return instance;
    }

    // Set plugin instance
    private static void setInstance(MagicznaKraina instance) {
        MagicznaKraina.instance = instance;
    }

    public HashMap<Player, ArrayList<Player>> getTpa() {
        return tpa;
    }

    public HashMap<String, String> getMessages() {
        return messages;
    }

    public Location getSpawnLocation() {
        return spawnLocation;
    }

    public void setSpawnLocation(Location spawnLocation) {
        this.spawnLocation = spawnLocation;
    }

    public void setSpawnLocation(World world, double x, double y, double z, float yaw, float pitch) {
        this.spawnLocation = new Location(world, x, y, z, yaw, pitch);
    }

    public void setDebug(boolean debug) {
        this.debug = debug;
    }

    public boolean getDebug() {
        return this.debug;
    }
}

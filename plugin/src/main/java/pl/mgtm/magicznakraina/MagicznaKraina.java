package pl.mgtm.magicznakraina;

import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;
import pl.mgtm.magicznakraina.commands.*;
import pl.mgtm.magicznakraina.events.DeathEvent;
import pl.mgtm.magicznakraina.events.JoinServerEvent;
import pl.mgtm.magicznakraina.events.RespawnEvent;
import pl.mgtm.magicznakraina.modules.protect_chests.ProtectChests;
import pl.mgtm.magicznakraina.services.SpawnService;

import java.util.HashMap;

public final class MagicznaKraina extends JavaPlugin {
    private static MagicznaKraina instance;

    private FileConfiguration config;

    public SpawnService spawnService;

    private HashMap<String, String> messages = new HashMap<>();

    // TODO: Instrumenty i profiler
    private boolean debug = false;


    @Override
    public void onEnable() {
        // Remove brackets and trailing spaces from partially initialized arrays
        String pluginAuthor = getDescription().getAuthors().toString()
                .replace("[", "")
                .replace("]", "")
                .trim();

        getLogger().info("Loading MagicznaKraina (v" + getDescription().getVersion() + " - " + pluginAuthor + ")");

        setInstance(this);

        getServer().getPluginManager().registerEvents(new JoinServerEvent(), this);
        getServer().getPluginManager().registerEvents(new RespawnEvent(), this);
        getServer().getPluginManager().registerEvents(new DeathEvent(), this);
        //getServer().getPluginManager().registerEvents(new ExplosiveArrowEvent(), this);
        //getServer().getPluginManager().registerEvents(new SuperPickaxeEvent(), this);

        // TODO: Command handler?
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
        getCommand("guitest").setExecutor(new GuiTestingCommand());

        // ===================================================== //
        //                                                       //
        //                  Rejestracja modulow                  //
        //                                                       //
        // ===================================================== //

        this.spawnService = new SpawnService();

        new ProtectChests();
        loadConfig();

        this.spawnService.loadSpawnLocation();

        messages.put("leaveMessage", ChatColor.translateAlternateColorCodes('&', getConfig().getString("message.leaveMessage")));
        messages.put("joinMessage", ChatColor.translateAlternateColorCodes('&', getConfig().getString("message.joinMessage")));

        debug = getConfig().getBoolean("debug");

        getLogger().info("MagicznaKraina has been successfully loaded!");
    }

    @Override
    public void onDisable() {
        getLogger().info("MagicznaKraina has been shutdown!");
    }

    // TODO: Move it somewhere else
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

    // TODO: Move it somewhere else
    public HashMap<String, String> getMessages() {
        return messages;
    }

    // TODO: ... nothing to explain in here
    public void setDebug(boolean debug) {
        this.debug = debug;
    }

    public boolean getDebug() {
        return this.debug;
    }
}

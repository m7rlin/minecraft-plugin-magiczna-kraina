package pl.mgtm.magicznakraina;

import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;
import pl.mgtm.magicznakraina.commands.*;
import pl.mgtm.magicznakraina.events.RespawnEvent;
import pl.mgtm.magicznakraina.modules.welcome.WelcomeModule;
import pl.mgtm.magicznakraina.modules.welcome.events.WelcomeMessageEvent;
import pl.mgtm.magicznakraina.helpers.ConfigHelpers;
import pl.mgtm.magicznakraina.modules.clever_sleep.CleverSleepModule;
import pl.mgtm.magicznakraina.modules.kits.KitsModule;
import pl.mgtm.magicznakraina.modules.protect_chests.ProtectedChestsModule;
import pl.mgtm.magicznakraina.modules.serduszko.SerduszkoModule;
import pl.mgtm.magicznakraina.modules.vanish.VanishModule;
import pl.mgtm.magicznakraina.services.SpawnService;
import pl.mgtm.magicznakraina.services.TeleportationService;

import java.util.HashMap;

public final class MagicznaKraina extends JavaPlugin {
    private static MagicznaKraina instance;

    private FileConfiguration config;

    public SpawnService spawnService;
    public TeleportationService teleportationService;

    public SerduszkoModule serduszkoModule;



    @Override
    public void onEnable() {
        // Remove brackets and trailing spaces from partially initialized arrays
        String pluginAuthor = getDescription().getAuthors().toString()
                .replace("[", "")
                .replace("]", "")
                .trim();

        getLogger().info("Loading MagicznaKraina (v" + getDescription().getVersion() + " - " + pluginAuthor + ")");

        setInstance(this);

        PluginManager pm = getServer().getPluginManager();

        // Register event listeners
        pm.registerEvents(new RespawnEvent(), this);

        // Register commands
        //getCommand("test").setExecutor(new TestCommand()); // comment on production
        getCommand("tpa").setExecutor(new TpaCommand());
        getCommand("tpaccept").setExecutor(new TpaCommand());
        getCommand("tpdeny").setExecutor(new TpaCommand());
        getCommand("sethome").setExecutor(new SetHomeCommand());
        getCommand("home").setExecutor(new HomeCommand());
        getCommand("spawn").setExecutor(new SpawnCommand());
        getCommand("setspawn").setExecutor(new SetSpawnCommand());
        getCommand("enderchest").setExecutor(new EnderchestCommand());
        getCommand("heal").setExecutor(new HealCommand());
        getCommand("broadcast").setExecutor(new BroadcastCommand());
        getCommand("alert").setExecutor(new AlertCommand());
        getCommand("gm").setExecutor(new GamemodeCommand());

        // Initialize services
        this.spawnService = new SpawnService();
        this.spawnService.loadSpawnLocation();

        this.teleportationService = new TeleportationService();

        // Initialize "Protected Chests" module
        new ProtectedChestsModule();
        // Initialize "Kits" module
        new KitsModule();
        // Initialize "Serduszko" module
        serduszkoModule = new SerduszkoModule();
        // Initialize "Cleever Sleep" module
        new CleverSleepModule();
        // Initialize "Vanish" module
        new VanishModule();
        // Initialize "Welcome" module
        new WelcomeModule();

        // Load config
        ConfigHelpers.loadConfig();

        getLogger().info("MagicznaKraina has been successfully loaded!");
    }

    @Override
    public void onDisable() {
        getLogger().info("MagicznaKraina has been shutdown!");
    }

    public void setConfig(FileConfiguration config) {
        this.config = config;
    }

    // Get plugin instance
    public static MagicznaKraina getInstance() {
        return instance;
    }

    // Set plugin instance
    private static void setInstance(MagicznaKraina instance) {
        MagicznaKraina.instance = instance;
    }


}

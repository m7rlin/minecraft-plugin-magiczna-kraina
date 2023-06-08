package pl.mgtm.magicznakraina;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;
import pl.mgtm.magicznakraina.api.config.ConfigAPI;
import pl.mgtm.magicznakraina.api.config.style.CommentStyle;
import pl.mgtm.magicznakraina.api.config.style.NameStyle;
import pl.mgtm.magicznakraina.commands.*;
import pl.mgtm.magicznakraina.config.MainConfig;
import pl.mgtm.magicznakraina.config.UsersConfig;
import pl.mgtm.magicznakraina.events.RespawnEvent;
import pl.mgtm.magicznakraina.helpers.ConfigHelpers;
import pl.mgtm.magicznakraina.module.PluginModuleManager;
import pl.mgtm.magicznakraina.modules.clever_sleep.CleverSleepModule;
import pl.mgtm.magicznakraina.modules.kits.KitsModule;
import pl.mgtm.magicznakraina.modules.protect_chests.ProtectedChestsModule;
import pl.mgtm.magicznakraina.modules.reset_worlds.ResetWorldsModule;
import pl.mgtm.magicznakraina.modules.serduszko.SerduszkoModule;
import pl.mgtm.magicznakraina.modules.vanish.VanishModule;
import pl.mgtm.magicznakraina.modules.welcome.WelcomeModule;
import pl.mgtm.magicznakraina.services.SpawnService;
import pl.mgtm.magicznakraina.services.TeleportationService;

public final class MagicznaKraina extends JavaPlugin {
    private static MagicznaKraina instance;

    private static PluginModuleManager pluginModuleManager;

    private FileConfiguration config;

    public SpawnService spawnService;
    public TeleportationService teleportationService;

    public SerduszkoModule serduszkoModule;

    private static MainConfig mainConfig;
    private static UsersConfig userConfig;


    @Override
    public void onEnable() {
        // Remove brackets and trailing spaces from partially initialized arrays
        String pluginAuthor = getDescription().getAuthors().toString()
                .replace("[", "")
                .replace("]", "")
                .trim();

        getLogger().info("Loading MagicznaKraina (v" + getDescription().getVersion() + " - " + pluginAuthor + ")");

        setInstance(this);

        // Register modules
        registerModules();

        // Set MAIN plugin config
        mainConfig = ConfigAPI.init(MainConfig.class, NameStyle.UNDERSCORE, CommentStyle.ABOVE_CONTENT, false, this);
        userConfig = ConfigAPI.init(UsersConfig.class, NameStyle.UNDERSCORE, CommentStyle.ABOVE_CONTENT, false, this);

        PluginManager pm = getServer().getPluginManager();

        // Register event listeners
        pm.registerEvents(new RespawnEvent(), this);
        //pm.registerEvents(new ExplosiveArrowEvent(), this); // do not register on production

        // Register commands
        getCommand("test").setExecutor(new TestCommand()); // comment on production
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
        getCommand("fly").setExecutor(new FlyCommand());

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
        new ResetWorldsModule();

        // Load config
        //ConfigHelpers.loadConfig();

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

    private void registerModules() {
        pluginModuleManager = new PluginModuleManager();
        //pluginModuleManager.registerModule();
    }

    public static MainConfig getMainConfig() {
        return mainConfig;
    }
    public static UsersConfig getUserConfig() {
        return userConfig;
    }


}

package pl.mgtm.magicznakraina;

import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;
import pl.mgtm.magicznakraina.api.config.ConfigAPI;
import pl.mgtm.magicznakraina.api.config.style.CommentStyle;
import pl.mgtm.magicznakraina.api.config.style.NameStyle;
import pl.mgtm.magicznakraina.commands.*;
import pl.mgtm.magicznakraina.config.KitsConfig;
import pl.mgtm.magicznakraina.config.MainConfig;
import pl.mgtm.magicznakraina.config.UsersConfig;
import pl.mgtm.magicznakraina.module.PluginModuleManager;
import pl.mgtm.magicznakraina.modules.clever_sleep.CleverSleepModule;
import pl.mgtm.magicznakraina.modules.home.HomeModule;
import pl.mgtm.magicznakraina.modules.home.commands.HomeCommand;
import pl.mgtm.magicznakraina.modules.home.commands.SetHomeCommand;
import pl.mgtm.magicznakraina.modules.kits.KitsModule;
import pl.mgtm.magicznakraina.modules.protect_chests.ProtectedChestsModule;
import pl.mgtm.magicznakraina.modules.reset_worlds.ResetWorldsModule;
import pl.mgtm.magicznakraina.modules.serduszko.SerduszkoModule;
import pl.mgtm.magicznakraina.modules.spawn.SpawnModule;
import pl.mgtm.magicznakraina.modules.vanish.VanishModule;
import pl.mgtm.magicznakraina.modules.welcome.WelcomeModule;
import pl.mgtm.magicznakraina.services.TeleportationService;

public final class MagicznaKraina extends JavaPlugin {
    private static MagicznaKraina instance;

    private static PluginModuleManager pluginModuleManager;
    public TeleportationService teleportationService;

    public SerduszkoModule serduszkoModule;

    private static MainConfig mainConfig;
    private static UsersConfig userConfig;
    private static KitsConfig kitsConfig;

    public static final boolean ConfigAPIDebug = false;


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
        kitsConfig = ConfigAPI.init(KitsConfig.class, NameStyle.UNDERSCORE, CommentStyle.ABOVE_CONTENT, false, this);

        PluginManager pm = getServer().getPluginManager();


        // Register event listeners
        //pm.registerEvents(new ExplosiveArrowEvent(), this); // do not register on production

        // Register commands
        getCommand("test").setExecutor(new TestCommand()); // comment on production
        getCommand("tpa").setExecutor(new TpaCommand());
        getCommand("tpaccept").setExecutor(new TpaCommand());
        getCommand("tpdeny").setExecutor(new TpaCommand());
        getCommand("sethome").setExecutor(new SetHomeCommand());
        getCommand("home").setExecutor(new HomeCommand());
        getCommand("enderchest").setExecutor(new EnderchestCommand());
        getCommand("heal").setExecutor(new HealCommand());
        getCommand("broadcast").setExecutor(new BroadcastCommand());
        getCommand("alert").setExecutor(new AlertCommand());
        getCommand("gm").setExecutor(new GamemodeCommand());
        getCommand("fly").setExecutor(new FlyCommand());


        this.teleportationService = new TeleportationService();

        // Loading modules
        new ProtectedChestsModule();
        new KitsModule();
        serduszkoModule = new SerduszkoModule();
        new CleverSleepModule();
        new VanishModule();
        new WelcomeModule();
        new ResetWorldsModule();
        new SpawnModule();
        new HomeModule();

        getLogger().info("MagicznaKraina has been successfully loaded!");
    }

    @Override
    public void onDisable() {
        getLogger().info("MagicznaKraina has been shutdown!");
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
    public static KitsConfig getKitsConfig() {
        return kitsConfig;
    }



}

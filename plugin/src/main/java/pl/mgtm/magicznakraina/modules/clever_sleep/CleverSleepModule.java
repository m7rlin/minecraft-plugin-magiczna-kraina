package pl.mgtm.magicznakraina.modules.clever_sleep;

import org.bukkit.plugin.PluginManager;
import pl.mgtm.magicznakraina.MagicznaKraina;
import pl.mgtm.magicznakraina.modules.clever_sleep.events.BedEvent;


public class CleverSleepModule {

    private final MagicznaKraina pl = MagicznaKraina.getInstance();


    public CleverSleepModule() {
        PluginManager pm = pl.getServer().getPluginManager();

        // Rejestracja zdarzeń
        pm.registerEvents(new BedEvent(), pl);
        //pm.registerEvents(new DeathEvent(), plugin);
        //pm.registerEvents(new PreLoginEvent(), plugin);

        // Rejestracja komend
        //plugin.getCommand(SerduszkoCommand.class.getAnnotation(CommandInfo.class).name()).setExecutor(new SerduszkoCommand());
        //plugin.getCommand(ReviveCommand.class.getAnnotation(CommandInfo.class).name()).setExecutor(new ReviveCommand());
    }


}

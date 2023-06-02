package pl.mgtm.magicznakraina.modules.clever_sleep;

import org.bukkit.plugin.PluginManager;
import pl.mgtm.magicznakraina.MagicznaKraina;
import pl.mgtm.magicznakraina.modules.clever_sleep.events.BedEvent;


public class CleverSleepModule {

    private final MagicznaKraina pl = MagicznaKraina.getInstance();


    public CleverSleepModule() {
        PluginManager pm = pl.getServer().getPluginManager();

        // Register events
        pm.registerEvents(new BedEvent(), pl);
    }


}

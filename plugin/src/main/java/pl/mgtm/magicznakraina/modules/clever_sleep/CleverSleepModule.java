package pl.mgtm.magicznakraina.modules.clever_sleep;

import org.bukkit.plugin.PluginManager;
import pl.mgtm.magicznakraina.MagicznaKraina;
import pl.mgtm.magicznakraina.module.PluginModule;
import pl.mgtm.magicznakraina.modules.clever_sleep.events.BedEvent;


public class CleverSleepModule extends PluginModule {

    private final MagicznaKraina pl = MagicznaKraina.getInstance();


    public CleverSleepModule() {
        super();

        // Register events
        super.registerEvents(new BedEvent());
    }


}

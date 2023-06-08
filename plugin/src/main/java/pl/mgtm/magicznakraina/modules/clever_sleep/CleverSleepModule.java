package pl.mgtm.magicznakraina.modules.clever_sleep;

import pl.mgtm.magicznakraina.MagicznaKraina;
import pl.mgtm.magicznakraina.module.ModuleInfo;
import pl.mgtm.magicznakraina.module.PluginModule;
import pl.mgtm.magicznakraina.modules.clever_sleep.events.BedEvent;

@ModuleInfo(name = "clever_sleep")
public class CleverSleepModule extends PluginModule {

    private final MagicznaKraina pl = MagicznaKraina.getInstance();


    public CleverSleepModule() {
        super();

        // Register events
        super.registerEvents(new BedEvent());
    }


}

package pl.mgtm.magicznakraina.modules.protect_chests;

import pl.mgtm.magicznakraina.MagicznaKraina;
import pl.mgtm.magicznakraina.module.ModuleInfo;
import pl.mgtm.magicznakraina.module.PluginModule;
import pl.mgtm.magicznakraina.modules.protect_chests.commands.LockCommand;
import pl.mgtm.magicznakraina.modules.protect_chests.events.BreakChest;
import pl.mgtm.magicznakraina.modules.protect_chests.events.OpenChest;
import pl.mgtm.magicznakraina.modules.protect_chests.events.PlaceChest;

@ModuleInfo(name = "protected_chests")
public class ProtectedChestsModule extends PluginModule {

    private final MagicznaKraina pl = MagicznaKraina.getInstance();

    public ProtectedChestsModule() {
        super();

        // Register commands
        super.registerCommand(new LockCommand());

        // Register events
        super.registerEvents(new OpenChest());
        super.registerEvents(new PlaceChest());
        super.registerEvents(new BreakChest());
    }
}

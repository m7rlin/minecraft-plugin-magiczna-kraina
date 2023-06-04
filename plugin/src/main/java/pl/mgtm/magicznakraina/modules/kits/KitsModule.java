package pl.mgtm.magicznakraina.modules.kits;

import pl.mgtm.magicznakraina.MagicznaKraina;
import pl.mgtm.magicznakraina.module.PluginModule;
import pl.mgtm.magicznakraina.modules.kits.commands.KitCommand;


public class KitsModule extends PluginModule {

    private final MagicznaKraina pl = MagicznaKraina.getInstance();

    public KitsModule() {
        super();

        // Register commands
        super.registerCommand(new KitCommand());
    }
}

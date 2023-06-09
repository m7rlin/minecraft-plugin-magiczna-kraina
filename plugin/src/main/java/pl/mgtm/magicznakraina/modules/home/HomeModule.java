package pl.mgtm.magicznakraina.modules.home;

import pl.mgtm.magicznakraina.MagicznaKraina;
import pl.mgtm.magicznakraina.module.ModuleInfo;
import pl.mgtm.magicznakraina.module.PluginModule;
import pl.mgtm.magicznakraina.modules.home.commands.HomeCommand;
import pl.mgtm.magicznakraina.modules.home.commands.SetHomeCommand;

@ModuleInfo(name = "home")
public class HomeModule extends PluginModule {

    private final MagicznaKraina pl = MagicznaKraina.getInstance();


    public HomeModule() {
        super();

        // Register commands
        super.registerCommand(new HomeCommand());
        super.registerCommand(new SetHomeCommand());

        // Register events

    }
}

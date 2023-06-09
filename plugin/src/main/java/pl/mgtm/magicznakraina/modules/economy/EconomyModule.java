package pl.mgtm.magicznakraina.modules.economy;

import pl.mgtm.magicznakraina.MagicznaKraina;
import pl.mgtm.magicznakraina.module.ModuleInfo;
import pl.mgtm.magicznakraina.module.PluginModule;
import pl.mgtm.magicznakraina.modules.economy.commands.MoneyCommand;

@ModuleInfo(name = "economy")
public class EconomyModule extends PluginModule {

    private final MagicznaKraina pl = MagicznaKraina.getInstance();


    public EconomyModule() {
        super();

        super.registerCommand(new MoneyCommand());
    }
}

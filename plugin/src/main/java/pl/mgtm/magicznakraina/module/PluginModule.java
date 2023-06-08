package pl.mgtm.magicznakraina.module;

import org.bukkit.event.HandlerList;
import org.bukkit.event.Listener;
import pl.mgtm.magicznakraina.MagicznaKraina;
import pl.mgtm.magicznakraina.command.PluginCommand;

import java.util.Objects;

public abstract class PluginModule {

    private final ModuleInfo moduleInfo;
    private final MagicznaKraina pl = MagicznaKraina.getInstance();

    public PluginModule() {
        moduleInfo = getClass().getDeclaredAnnotation(ModuleInfo.class);
        Objects.requireNonNull(moduleInfo, "Moduły muszą mieć przypisaną adnotacje 'ModuleInfo'!");
    }

    public ModuleInfo getModuleInfo() {
        return moduleInfo;
    }

    public void enable() {
        pl.getLogger().info("");
    }

    public void disable() {

    }

    public void registerCommand(PluginCommand command) {
        pl.getCommand(command.getCommandInfo().name()).setExecutor(command);
    }


    public void unregisterCommand(PluginCommand command) {
        pl.getCommand(command.getCommandInfo().name()).unregister(pl.getServer().getCommandMap());
    }

    public void registerEvents(Listener listener) {
        pl.getServer().getPluginManager().registerEvents(listener, pl);
    }

    public void unregisterEvents(Listener listener) {
        HandlerList.unregisterAll(listener);
    }
}

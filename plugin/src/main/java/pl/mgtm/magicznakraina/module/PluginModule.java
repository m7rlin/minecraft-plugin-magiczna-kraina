package pl.mgtm.magicznakraina.module;

import org.bukkit.command.CommandExecutor;
import org.bukkit.event.HandlerList;
import org.bukkit.event.Listener;
import pl.mgtm.magicznakraina.MagicznaKraina;
import pl.mgtm.magicznakraina.command.PluginCommand;

public abstract class PluginModule {
    private final MagicznaKraina pl = MagicznaKraina.getInstance();

    public PluginModule() {

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

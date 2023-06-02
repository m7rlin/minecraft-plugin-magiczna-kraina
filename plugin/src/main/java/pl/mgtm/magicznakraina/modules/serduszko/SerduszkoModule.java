package pl.mgtm.magicznakraina.modules.serduszko;

import org.bukkit.ChatColor;
import org.bukkit.plugin.PluginManager;
import pl.mgtm.magicznakraina.MagicznaKraina;
import pl.mgtm.magicznakraina.command.CommandInfo;
import pl.mgtm.magicznakraina.modules.serduszko.commands.ReviveCommand;
import pl.mgtm.magicznakraina.modules.serduszko.commands.SerduszkoCommand;
import pl.mgtm.magicznakraina.modules.serduszko.events.DeathEvent;
import pl.mgtm.magicznakraina.modules.serduszko.events.JoinServerEvent;
import pl.mgtm.magicznakraina.modules.serduszko.events.PreLoginEvent;


public class SerduszkoModule {

    private MagicznaKraina plugin = MagicznaKraina.getInstance();

    private String bannedPlayerMessage = ChatColor.GRAY + "Straciłeś wszystkie serduszka!\nJeżeli chcesz grać dalej, gracze mogą cie " + ChatColor.GOLD + "wskrzeszyć!\n" + ChatColor.GRAY + "Po więcej informacji dołącz na naszego Discorda\n\n" + ChatColor.AQUA + "https://discord.mgtm.pl/ " + ChatColor.RED + "\n\nZostałeś wykluczony z serwera!";

    public SerduszkoModule() {
        PluginManager pm = plugin.getServer().getPluginManager();

        // Rejestracja zdarzeń
        pm.registerEvents(new JoinServerEvent(), plugin);
        pm.registerEvents(new DeathEvent(), plugin);
        pm.registerEvents(new PreLoginEvent(), plugin);

        // Rejestracja komend
        plugin.getCommand(SerduszkoCommand.class.getAnnotation(CommandInfo.class).name()).setExecutor(new SerduszkoCommand());
        plugin.getCommand(ReviveCommand.class.getAnnotation(CommandInfo.class).name()).setExecutor(new ReviveCommand());
    }

    public String getBannedPlayerMessage() {
        return this.bannedPlayerMessage;
    }
}

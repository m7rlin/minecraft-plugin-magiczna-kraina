package pl.mgtm.magicznakraina.commands;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.TextComponent;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import pl.mgtm.magicznakraina.MagicznaKraina;
import pl.mgtm.magicznakraina.command.CommandInfo;
import pl.mgtm.magicznakraina.command.PluginCommand;
import pl.mgtm.magicznakraina.modules.welcome.WelcomeModule;

@CommandInfo(name = "broadcast", permission = "mgtm.broadcast", requiresPlayer = false, usage = "/bc <wiadomość>")
public class BroadcastCommand extends PluginCommand {

    private final MagicznaKraina pl = MagicznaKraina.getInstance();
    @Override
    public void execute(CommandSender sender, String[] args) {
        super.execute(sender, args);

        TextComponent message = Component.text(ChatColor.translateAlternateColorCodes('&', stringArrayToString(args)));

        if (args.length != 0) {
            pl.getServer().broadcast(message);
        } else {
            super.commandUsage(sender);
        }
    }

    protected String stringArrayToString(String[] args) {
        StringBuilder stringBuilder = new StringBuilder();

        for (int i = 0; i < args.length; i++) {
            stringBuilder.append(args[i]);
            if (i != args.length -1) {
                stringBuilder.append(" ");
            }
        }
        return stringBuilder.toString();
    }
}

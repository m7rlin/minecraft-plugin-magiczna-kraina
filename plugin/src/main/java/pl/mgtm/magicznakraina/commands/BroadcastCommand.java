package pl.mgtm.magicznakraina.commands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import pl.mgtm.magicznakraina.command.CommandInfo;
import pl.mgtm.magicznakraina.command.PluginCommand;

@CommandInfo(name = "broadcast", permission = "mgtm.broadcast", requiresPlayer = false)
public class BroadcastCommand extends PluginCommand {
    @Override
    public void execute(CommandSender sender, String[] args) {
        super.execute(sender, args);

        if (args.length != 0) {
            for (Player player : Bukkit.getOnlinePlayers()) {
                player.sendMessage(ChatColor.translateAlternateColorCodes('&', stringArrayToString(args)));
            }
        } else {
            sender.sendMessage(ChatColor.RED + "/bc <wiadomość>");
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

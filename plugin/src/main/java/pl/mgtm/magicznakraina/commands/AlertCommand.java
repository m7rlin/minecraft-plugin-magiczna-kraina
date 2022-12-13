package pl.mgtm.magicznakraina.commands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Sound;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import pl.mgtm.magicznakraina.command.CommandInfo;
import pl.mgtm.magicznakraina.command.PluginCommand;

@CommandInfo(name = "alert", permission = "mgtm.alert", requiresPlayer = false)
public class AlertCommand extends PluginCommand {
    @Override
    public void execute(CommandSender sender, String[] args) {
        super.execute(sender, args);

        if (args.length != 0) {
            for (Player player : Bukkit.getOnlinePlayers()) {
                alertPlayer(player, stringArrayToString(args));
            }
        } else {
            sender.sendMessage(ChatColor.RED + "/alert <wiadomość>");
        }
    }

    private void alertPlayer(Player player, String text) {
        // Send title for 10 seconds
        player.sendTitle(ChatColor.YELLOW + "" + ChatColor.BOLD + "ALERT!", text, 5, 200, 5);
        player.playSound(player.getLocation(), Sound.ENTITY_PLAYER_LEVELUP, 1.0f, 0.0f);
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

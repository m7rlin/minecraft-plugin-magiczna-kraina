package pl.mgtm.magicznakraina.templates;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import pl.mgtm.magicznakraina.templates.CommandInfo;

import java.util.Objects;

// TODO: Try to make it more user friendly
public abstract class PluginCommand implements CommandExecutor {
    private final CommandInfo commandInfo;

    public PluginCommand() {
        commandInfo = getClass().getDeclaredAnnotation(CommandInfo.class);
        Objects.requireNonNull(commandInfo, "Komendy muszą mieć przypisaną adnotacje 'CommandInfo'!");
    }

    public CommandInfo getCommandInfo() {
        return commandInfo;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!commandInfo.permission().isEmpty()) {
            if (!sender.hasPermission(commandInfo.permission())) {
                insufficientPermissions(sender);
                return true;
            }
        }

        if (commandInfo.requiresPlayer()) {
            if (!isPlayer(sender)) {
                sender.sendMessage(ChatColor.RED + "Tylko gracz może użyć tej komendy.");
                return true;
            }

            execute((Player) sender, args);
            return true;
        }

        execute(sender, args);
        return true;
    }

    public boolean isPlayer(CommandSender sender) {
        return sender instanceof Player;
    }

    public void insufficientPermissions(CommandSender sender) {
        sender.sendMessage(ChatColor.RED + "Nie masz uprawnien do wykonania tej komendy.");
    }

    public void execute(Player player, String[] args) {
    }

    public void execute(CommandSender sender, String[] args) {
    }
}

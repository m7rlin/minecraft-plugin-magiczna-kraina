package pl.mgtm.magicznakraina.command;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabExecutor;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public abstract class PluginCommand implements TabExecutor {
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

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String label, String[] args) {
        // Check if player has permissions
        if (!commandInfo.permission().isEmpty()) {
            if (!sender.hasPermission(commandInfo.permission())) {
                // Return empty list
                return new ArrayList<>();
            }
        }

        List<String> arguments = tabAutocomplete(sender,  command, label, args);

        return arguments;
    }

    public List<String> tabAutocomplete(CommandSender sender, Command command, String label, String[] args) {
        return null;
    }

    public boolean isPlayer(CommandSender sender) {
        return sender instanceof Player;
    }

    public void insufficientPermissions(CommandSender sender) {
        sender.sendMessage(ChatColor.RED + "Nie masz uprawnień do wykonania tej komendy.");
    }

    public void commandUsage(CommandSender sender) {
        sender.sendMessage(ChatColor.RED + "Użycie: " + commandInfo.usage().toString());
    }

    public void execute(Player player, String[] args) {
    }

    public void execute(CommandSender sender, String[] args) {
    }
}

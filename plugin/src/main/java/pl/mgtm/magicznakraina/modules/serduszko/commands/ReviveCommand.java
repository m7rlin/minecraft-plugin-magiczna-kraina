package pl.mgtm.magicznakraina.modules.serduszko.commands;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.TextComponent;
import net.kyori.adventure.text.format.NamedTextColor;
import org.bukkit.ChatColor;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;
import pl.mgtm.magicznakraina.MagicznaKraina;
import pl.mgtm.magicznakraina.command.CommandInfo;
import pl.mgtm.magicznakraina.command.PluginCommand;
import pl.mgtm.magicznakraina.config.User;

import java.util.HashMap;
import java.util.List;

@CommandInfo(name = "revive", permission = "", requiresPlayer = false)
public class ReviveCommand extends PluginCommand {

    private MagicznaKraina pl = MagicznaKraina.getInstance();

    @Override
    public void execute(CommandSender sender, String[] args) {
        super.execute(sender, args);

        // Nie podano gracza
        if (args.length == 0) {
            sender.sendMessage(ChatColor.RED + "Podaj gracza, którego chcesz wskrzesić.");
            return;
        }

        HashMap<String, User> users = pl.getUserConfig().getUsers();
        if (users == null) users = new HashMap<>();


        String revivePlayerName = args[0];
        OfflinePlayer reviveOffilinePlayer = pl.getServer().getOfflinePlayer(revivePlayerName);

        User revivePlayer = users.get(reviveOffilinePlayer.getUniqueId().toString());

        if (revivePlayer == null) {
            sender.sendMessage(ChatColor.RED + "Możesz wskrzesić TYLKO gracza, który grał już na serwerze.");
            return;
        }

        boolean isPlayerBanned = revivePlayer.getBannedOnZeroHearts();
        // Check if player is dead
        if (!isPlayerBanned) {
            sender.sendMessage(ChatColor.RED + "Możesz wskrzesić TYLKO gracza, który poległ.");
            return;
        }

        // Sender is player
        if (super.isPlayer(sender)) {
            Player player = (Player) sender;

            List<ItemStack> requiredItems = pl.getMainConfig().getSerduszkoModule().getReviveItems();
            int requiredLevel = pl.getMainConfig().getSerduszkoModule().getReviveLevel();

            // Validate user
            if (!validateUser(player, requiredItems, requiredLevel)) return;

            PlayerInventory playerInventory = player.getInventory();

            if (requiredItems.size() != 0) {
                // Remove items from players inventory
                playerInventory.removeItem(requiredItems.toArray(new ItemStack[0]));
            }

            // Update player level
            if (requiredLevel > 0) {
                player.setLevel(player.getLevel() - requiredLevel);
            }

            // Revive player
            revivePlayer.setBannedOnZeroHearts(false);

            player.sendMessage(ChatColor.GREEN + "Pomyślnie wskrzeszono gracza '" + revivePlayerName + "'.");
        } else {
            // Revive player
            revivePlayer.setBannedOnZeroHearts(false);
            sender.sendMessage(ChatColor.GREEN + "Pomyślnie wskrzeszono gracza '" + revivePlayerName + "'.");
        }

        pl.getUserConfig().setUsers(users);
    }

    private boolean validateUser(Player player, List<ItemStack> requiredItems, int requiredLevel) {
        // Sprawdz czy gracz posiada wymagany level
        if (requiredLevel > 0) {
            if (player.getLevel() < requiredLevel) {
                player.sendMessage(ChatColor.RED + "Nie posiadasz wystarczającego poziomu doświadczenia, aby wskrzesić gracza.");
                player.sendMessage(getItemsMessage(requiredItems, requiredLevel));
                return false;
            }
        }

        if (requiredItems.size() != 0) {
            // Sprawdz wymagania (przedmioty itp.)
            for (ItemStack item : requiredItems) {
                if (!player.getInventory().containsAtLeast(item, item.getAmount())) {
                    player.sendMessage(ChatColor.RED + "Nie posiadasz wystarczającej ilości przedmiotów, aby wskrzesić gracza.");
                    player.sendMessage(getItemsMessage(requiredItems, requiredLevel));
                    return false;
                }

            }
        }

        return true;
    }

    private TextComponent getItemsMessage(List<ItemStack> requiredItems, int requiredLevel) {
        TextComponent textComponent = Component.text("Aby wkrzesić gracza musisz spełnić następujące wymogi:", NamedTextColor.GRAY)
                .append(Component.newline())
                .append(Component.text("Przedmioty:", NamedTextColor.GRAY))
                .append(Component.newline());

        for (ItemStack item2 : requiredItems) {

            textComponent = textComponent
                    .append(Component.text("- "))
                    .append(Component.text(item2.getAmount(), NamedTextColor.GREEN))
                    .append(Component.text("x ", NamedTextColor.GREEN))
                    .append(item2.displayName())
                    .append(Component.newline());
        }

        if (requiredLevel > 0) {
            textComponent = textComponent
                    .append(Component.newline())
                    .append(Component.text("Poziom:\n", NamedTextColor.GRAY))
                    .append(Component.text("- "))
                    .append(Component.text(requiredLevel, NamedTextColor.GREEN))
                    .append(Component.text(" poziom doświadczenia.", NamedTextColor.GRAY));
        }

        return textComponent;
    }

}

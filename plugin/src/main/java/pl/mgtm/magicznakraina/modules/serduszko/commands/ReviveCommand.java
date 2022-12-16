package pl.mgtm.magicznakraina.modules.serduszko.commands;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.ComponentBuilder;
import net.kyori.adventure.text.TextComponent;
import net.kyori.adventure.text.format.NamedTextColor;
import net.kyori.adventure.text.format.TextColor;
import net.kyori.adventure.text.format.TextDecoration;
import net.md_5.bungee.api.chat.BaseComponent;
import org.bukkit.ChatColor;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Item;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;
import org.checkerframework.checker.units.qual.C;
import pl.mgtm.magicznakraina.MagicznaKraina;
import pl.mgtm.magicznakraina.command.CommandInfo;
import pl.mgtm.magicznakraina.command.PluginCommand;
import pl.mgtm.magicznakraina.helpers.ConfigHelpers;

import java.time.format.TextStyle;
import java.util.ArrayList;

@CommandInfo(name = "revive", permission = "", requiresPlayer = false)
public class ReviveCommand extends PluginCommand {

    private MagicznaKraina plugin = MagicznaKraina.getInstance();

    @Override
    public void execute(CommandSender sender, String[] args) {
        super.execute(sender, args);

        // Nie podano gracza
        if (args.length == 0) {
            sender.sendMessage(ChatColor.RED + "Podaj gracza, którego chcesz wskrzesić.");
            return;
        }

        ConfigHelpers.createDefaultPlayerReviveConfig();

        String revivePlayerName = args[0];
        OfflinePlayer reviveOffilinePlayer = plugin.getServer().getOfflinePlayer(revivePlayerName);

        if (!ConfigHelpers.userExist(reviveOffilinePlayer.getUniqueId())) {
            sender.sendMessage(ChatColor.RED + "Możesz wskrzesić TYLKO gracza, który grał już na serwerze.");
            return;
        }

        boolean isPlayerBanned = ConfigHelpers.getPlayerZeroHeartsBan(reviveOffilinePlayer.getUniqueId());
        // Sprawdz czy gracz jest martwy
        if (!isPlayerBanned) {
            sender.sendMessage(ChatColor.RED + "Możesz wskrzesić TYLKO gracza, który poległ.");
            return;
        }

        // Jest graczem
        if (super.isPlayer(sender)) {
            Player player = (Player) sender;

            ArrayList<ItemStack> requiredItems = ConfigHelpers.getPlayerReviveItems();
            int requiredLevel = ConfigHelpers.getPlayerReviveLevel();

            // Validate user
            if (!validateUser(player, requiredItems, requiredLevel)) return;

            PlayerInventory playerInventory = player.getInventory();

            if (requiredItems.size() != 0) {
                // Usun przedmioty z ekwipunku gracza
                playerInventory.removeItem(requiredItems.toArray(new ItemStack[0]));
            }

            // Zaktualizuj poziom gracza
            if (requiredLevel > 0) {
                player.setLevel(player.getLevel() - requiredLevel);
            }


            // Wskrzes gracza
            ConfigHelpers.setPlayerZeroHeartsBan(reviveOffilinePlayer.getUniqueId(), false);

            player.sendMessage(ChatColor.GREEN + "Pomyślnie wskrzeszono gracza '" + revivePlayerName + "'.");


        } else {
            // Wskrzes gracza
            ConfigHelpers.setPlayerZeroHeartsBan(reviveOffilinePlayer.getUniqueId(), false);
            sender.sendMessage(ChatColor.GREEN + "Pomyślnie wskrzeszono gracza '" + revivePlayerName + "'.");
        }


    }

    private boolean validateUser(Player player, ArrayList<ItemStack> requiredItems, int requiredLevel) {
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

    private TextComponent getItemsMessage(ArrayList<ItemStack> requiredItems, int requiredLevel) {
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

package pl.mgtm.magicznakraina.modules.vanish.commands;

import net.kyori.adventure.text.Component;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import pl.mgtm.magicznakraina.MagicznaKraina;
import pl.mgtm.magicznakraina.command.CommandInfo;
import pl.mgtm.magicznakraina.command.PluginCommand;
import pl.mgtm.magicznakraina.helpers.ServerHelper;
import pl.mgtm.magicznakraina.modules.vanish.VanishModule;
import pl.mgtm.magicznakraina.modules.welcome.WelcomeModule;

import java.util.*;

@CommandInfo(name = "vanish", permission = "mgtm.vanish", requiresPlayer = true, usage = "/vanish [on|off] [gracz]")
public class VanishCommand extends PluginCommand {
    private MagicznaKraina pl = MagicznaKraina.getInstance();
    private Map<UUID, Boolean> vanishedPlayers = VanishModule.getVanishedPlayers();

    @Override
    public void execute(Player player, String[] args) {
        super.execute(player, args);

        if (args.length > 2) {
            super.commandUsage(player);
            return;
        }

        UUID playerUUID = player.getUniqueId();

        // Toggle sender's visibility
        if (args.length == 0) {
            if (vanishedPlayers.containsKey(playerUUID) && vanishedPlayers.get(playerUUID)) {
                // Player is already vanished, make them visible again
                vanishedPlayers.put(playerUUID, false);
                for (Player onlinePlayer : pl.getServer().getOnlinePlayers()) {
                    onlinePlayer.showPlayer(pl, player);
                }
                player.sendMessage(ChatColor.GREEN + "Jesteś teraz widoczny.");
            } else {
                // Vanish the player
                vanishedPlayers.put(playerUUID, true);
                for (Player onlinePlayer : pl.getServer().getOnlinePlayers()) {
                    onlinePlayer.hidePlayer(pl, player);
                }
                player.sendMessage(ChatColor.GREEN + "Jesteś teraz niewidzialny.");
            }
        } else {
            Component joinMessage = WelcomeModule.getJoinMessage(player);
            Component leaveMessage = WelcomeModule.getLeaveMessage(player);

            if (args[0].equalsIgnoreCase("login")) {
                pl.getServer().broadcast(joinMessage);
            } else if (args[0].equalsIgnoreCase("logout")) {
                pl.getServer().broadcast(leaveMessage);
            } else {
                // Toggle specific player's visibility
                String playerName = args[1];
                Player targetPlayer = pl.getServer().getPlayerExact(playerName);

                if (targetPlayer == null) {
                    player.sendMessage(ChatColor.RED + "Podany gracz nie jest online.");
                    return;
                }

                UUID targetUUID = targetPlayer.getUniqueId();

                if (args[0].equalsIgnoreCase("on")) {
                    if (vanishedPlayers.containsKey(targetUUID) && vanishedPlayers.get(targetUUID)) {
                        // Player is already vanished
                        player.sendMessage(targetPlayer.getName() + " jest już ukryty.");
                    } else {
                        // Vanish the player
                        vanishedPlayers.put(targetUUID, true);
                        for (Player onlinePlayer : pl.getServer().getOnlinePlayers()) {
                            onlinePlayer.hidePlayer(pl, targetPlayer);
                        }
                        player.sendMessage(ChatColor.GOLD + targetPlayer.getName() + ChatColor.RED + " jest teraz ukryty.");
                    }
                } else if (args[0].equalsIgnoreCase("off")) {
                    if (vanishedPlayers.containsKey(targetUUID) && !vanishedPlayers.get(targetUUID)) {
                        // Player is already visible
                        player.sendMessage(ChatColor.GOLD +targetPlayer.getName() + ChatColor.RED + " jest już widoczny.");
                    } else {
                        // Make the player visible
                        vanishedPlayers.put(targetUUID, false);
                        for (Player onlinePlayer : pl.getServer().getOnlinePlayers()) {
                            onlinePlayer.showPlayer(pl, targetPlayer);
                        }
                        player.sendMessage(ChatColor.GOLD +targetPlayer.getName() + ChatColor.RED + " jest teraz widoczny.");
                    }
                } else {
                   super.commandUsage(player);
                }
            }
        }
    }

    @Override
    public List<String> tabAutocomplete(CommandSender sender, Command command, String label, String[] args) {
        super.tabAutocomplete(sender,command,label,args);

        List<String> subcommands = Arrays.asList("login", "logout", "on", "off");

        if (args.length == 1) {
            return subcommands;
        } else if (args.length == 2) {
            if (args[0].equalsIgnoreCase("on") || args[0].equalsIgnoreCase("off")) {
                return ServerHelper.getOnlinePlayerNames();
            }
        }

        return new ArrayList<>();
    }

}

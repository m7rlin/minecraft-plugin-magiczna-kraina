package pl.mgtm.magicznakraina.commands;

import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import pl.mgtm.magicznakraina.MagicznaKraina;
import pl.mgtm.magicznakraina.command.CommandInfo;
import pl.mgtm.magicznakraina.command.PluginCommand;
import pl.mgtm.magicznakraina.helpers.ServerHelper;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@CommandInfo(name = "gm", permission = "mgtm.gm", requiresPlayer = true, usage = "/gm <tryb gry> [gracz]")
public class GamemodeCommand extends PluginCommand {

    private final MagicznaKraina pl = MagicznaKraina.getInstance();
    private final List<String> gamemodes = Arrays.asList("0", "1", "2", "3", "survival", "creative", "adventure", "spectator");

    @Override
    public void execute(Player player, String[] args) {
        super.execute(player, args);

        if (args.length < 1 || args.length > 2) {
            super.commandUsage(player);
            return;
        }
        
        String gamemodeArg = args[0];

        GameMode gameMode;
        if (gamemodeArg.equalsIgnoreCase("survival") || gamemodeArg.equalsIgnoreCase("0")) {
            gameMode = GameMode.SURVIVAL;
        } else if (gamemodeArg.equalsIgnoreCase("creative") || gamemodeArg.equalsIgnoreCase("1")) {
            gameMode = GameMode.CREATIVE;
        } else if (gamemodeArg.equalsIgnoreCase("adventure") || gamemodeArg.equalsIgnoreCase("2")) {
            gameMode = GameMode.ADVENTURE;
        } else if (gamemodeArg.equalsIgnoreCase("spectator") || gamemodeArg.equalsIgnoreCase("3")) {
            gameMode = GameMode.SPECTATOR;
        } else {
            player.sendMessage(ChatColor.RED + "Nieprawidłowa wartość lub nazwa trybu gry. Użyj " + String.join(", ", gamemodes) + ".");
            return;
        }

        Player targetPlayer = (args.length == 2) ? pl.getServer().getPlayer(args[1]) : player;

        if (targetPlayer == null || !targetPlayer.isOnline()) {
            player.sendMessage(ChatColor.RED + "Podany gracz nie jest online.");
            return;
        }

        targetPlayer.setGameMode(gameMode);
        player.sendMessage(ChatColor.GRAY + "Zmieniono tryb gracza " + ChatColor.GREEN + targetPlayer.getName() + ChatColor.GRAY + " na " + ChatColor.GREEN + gameMode.toString() + ChatColor.GRAY + ".");
    }

    @Override
    public List<String> tabAutocomplete(CommandSender sender, Command command, String label, String[] args) {
        super.tabAutocomplete(sender,command,label,args);

        List<String> playerNames = ServerHelper.getOnlinePlayerNames();
        
        if (args.length == 1) {
            return gamemodes;
        } else if (args.length == 2) {
            return playerNames;
        }

        return new ArrayList<>();
    }
}

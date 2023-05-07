package pl.mgtm.magicznakraina.commands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import pl.mgtm.magicznakraina.MagicznaKraina;

// TODO: Refactor this code (its decent, but there is a lot of room for improvements)
public class TpaCommand implements CommandExecutor {
    private final MagicznaKraina plugin = MagicznaKraina.getInstance();

    private Player target;
    private Inventory inventory;

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String s, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage("§cTylko gracz może użyć tej komendy.");
            return false;
        }

        Player player = (Player) sender;

        if (cmd.getName().equalsIgnoreCase("tpa")) {
            if (args.length == 1) {
                return invokeTeleportationRequest(player, args);
            } else {
                player.sendMessage(ChatColor.DARK_GRAY + "Musisz określić do kogo chcesz wysłać prośbę o teleportację!");
                player.sendMessage(ChatColor.GRAY + "Użycie: /tpaccept <gracz>");
                return true;
            }
        }

        if (cmd.getName().equalsIgnoreCase("tpaccept")) {
            if (args.length < 1) {
                player.sendMessage(ChatColor.DARK_GRAY + "Musisz określić kogo prośbę o teleportację chcesz przyjąć!");
                player.sendMessage(ChatColor.GRAY + "Użycie: /tpaccept <gracz>");

                return false;
            }

            target = Bukkit.getPlayer(args[0]);

            if (target == null || !target.isOnline()) {
                player.sendMessage(ChatColor.RED + "Gracz od którego próbujesz przyjąć prośbę o teleportację nie jest online!");
                player.playSound(player.getLocation(), Sound.BLOCK_NOTE_BLOCK_BASS, 1f, 0f);

                return false;
            }

            if (!this.plugin.teleportationService.checkTeleportationRequests(target.getUniqueId(), player.getUniqueId())) {
                player.sendMessage(ChatColor.RED + "Brak prośby o teleportację od tego gracza!");
                player.playSound(player.getLocation(), Sound.BLOCK_NOTE_BLOCK_BASS, 1.0f, 0f);

                return false;
            }

            player.sendMessage(ChatColor.DARK_GRAY + "Teleportowanie gracza " + ChatColor.GREEN + target.getName());

            this.plugin.teleportationService.removeTeleportationRequest(target.getUniqueId(), player.getUniqueId());

            target.teleport(player.getLocation());

            target.sendMessage(ChatColor.GREEN + "Teleportowano do gracza " + player.getName());

            player.sendMessage(ChatColor.GREEN + "Teleportowano " + player.getName() + " pomyślnie!");
            player.playSound(player.getLocation(), Sound.BLOCK_NOTE_BLOCK_PLING, 1.0f, 2.0f);

            return true;
        }

        if (cmd.getName().equalsIgnoreCase("tpdeny")) {
            if (args.length < 1) {
                player.sendMessage(ChatColor.DARK_GRAY + "Wpisz gracza, od którego prośbę o teleportację chcesz anulować.");
                player.sendMessage(ChatColor.GRAY + "Użycie: /tpaccept <gracz>");

                return false;
            }

            target = Bukkit.getPlayer(args[0]);

            if (!this.plugin.teleportationService.checkTeleportationRequests(target.getUniqueId(), player.getUniqueId())) {
                player.sendMessage(ChatColor.RED + "Brak prośby o teleportację od tego gracza!");
                player.playSound(player.getLocation(), Sound.BLOCK_NOTE_BLOCK_BASS, 1.0f, 0.0f);

                return false;
            }

            this.plugin.teleportationService.removeTeleportationRequest(target.getUniqueId(), player.getUniqueId());

            target.sendMessage(ChatColor.RED + "Twoja prośba o teleportację do " + player.getName() + "została anulowana!");

            player.sendMessage(ChatColor.GREEN + "Anulowano prośbę o teleportację!");
            player.playSound(player.getLocation(), Sound.BLOCK_NOTE_BLOCK_PLING, 1.0f, 2.0f);

            return true;
        }

        return false;
    }

    private boolean invokeTeleportationRequest(Player player, String[] args) {
        target = Bukkit.getPlayer(args[0]);

        if (target == null || !target.isOnline()) {
            player.sendMessage(ChatColor.RED + "Gracz do którego próbujesz się teleportować nie jest online!");
            player.playSound(player.getLocation(), Sound.BLOCK_NOTE_BLOCK_BASS, 1.0f, 0.0f);

            return false;
        }

        if (target.getUniqueId().equals(player.getUniqueId())) {
            player.sendMessage(ChatColor.RED + "Nie możesz teleportować się do samego siebie!");
            player.playSound(player.getLocation(), Sound.BLOCK_NOTE_BLOCK_BASS, 1.0f, 0.0f);

            return false;
        }

        if (this.plugin.teleportationService.checkTeleportationRequests(player.getUniqueId(), target.getUniqueId())) {
            player.sendMessage(ChatColor.GREEN + "Ten gracz posiada już oczkującą prośbę o teleportację od Ciebie!");
            player.playSound(player.getLocation(), Sound.BLOCK_NOTE_BLOCK_BASS, 1.0f, 0.0f);

            return false;
        }

        this.plugin.teleportationService.createTeleporationRequest(player.getUniqueId(), target.getUniqueId());

        player.sendMessage(ChatColor.DARK_GRAY + "Wysłano prośbę o teleportację do gracza " + ChatColor.GREEN + target.getName());

        target.sendMessage(ChatColor.GREEN + player.getName() + " chce się do ciebie teleportować.");
        target.sendMessage(ChatColor.DARK_GRAY + "Wpisz /tpaccept lub /tpdeny aby wykonać pożądaną reakcję, lub wpisz /tpa");

        return true;
    }
}


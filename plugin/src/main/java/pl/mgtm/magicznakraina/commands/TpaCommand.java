package pl.mgtm.magicznakraina.commands;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import pl.mgtm.magicznakraina.MagicznaKraina;

import java.util.ArrayList;

public class TpaCommand implements CommandExecutor {

    private MagicznaKraina pl = MagicznaKraina.getInstance();

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String s, String[] args) {
        // Komenda użyta w konsoli zwraca błąd
        if (!(sender instanceof Player)) {
            sender.sendMessage("§cTylko gracz może użyć tej komendy.");
            return true;
        }

        Player p = (Player) sender;

        if (cmd.getName().equalsIgnoreCase("tpa")) {

            if (args.length == 1) {
                // Pobierz gracza do ktorego chcesz sie teleportowac
                Player target = pl.getServer().getPlayer(args[0]);
                // Sprawdz czy gracz jest dostepny na serwerze
                if (target != null && target.isOnline()) {
                    // Gracz wysylajacy komende nie jest graczek do ktorego chcesz sie teleportowac
                    if (target.getName().equals(p.getName())) {
                        p.sendMessage("§cNie możesz teleportować się do siebie. Wybierz innego gracza.");
                        return true;
                    }

                    // Sprawdz czy wyslales juz prosbe o teleprtacje
                    if (pl.getTpa().containsKey(p)) {

                        if (!pl.getTpa().get(target).contains(p)) {
                            pl.getTpa().get(target).add(p);
                        } else {
                            p.sendMessage(ChatColor.GREEN + "Ten gracz posiada już oczkującą prośbę o teleportację od Ciebie.");
                            return true;
                        }

                    } else {
                        ArrayList<Player> req = new ArrayList<Player>();
                        req.add(p);
                        pl.getTpa().put(target, req);
                    }

                    p.sendMessage("§aWysłano prośbę o teleportację do " + target.getName());
                    target.sendMessage("§a" + p.getName() + " chce się do Ciebie teleportować. Wpisz /tpaccept aby zaakceptować.");
                } else {
                    p.sendMessage("§cTen gracz nie jest online.");
                }

            } else {
                p.sendMessage("§c/tpa <gracz>");
            }

            return true;
        }


        if (cmd.getName().equalsIgnoreCase("tpaccept")) {

            if (args.length == 1) {

                Player target = pl.getServer().getPlayer(args[0]);

                if (target != null && target.isOnline()) {
                    p.sendMessage(pl.getTpa().get(p).toString());
                    if (pl.getTpa().containsKey(p)) {
                        if (pl.getTpa().get(p).contains(target)) {
                            pl.getTpa().get(p).remove(target);
                            target.teleport(p.getLocation());
                            p.sendMessage(ChatColor.GREEN + "Akceptowano prośbę o teleportację od " + ChatColor.BLUE + target.getName() + ChatColor.GREEN + ".");
                            target.sendMessage(ChatColor.GREEN + "Gracz " + ChatColor.BLUE + p.getName() + ChatColor.GREEN +  " akceptował prośbę o teleportację.");
                        } else {
                            p.sendMessage(ChatColor.RED + "Ten gracz nie wysłał prośby o teleportację do Ciebie.");
                        }

                    } else {
                        p.sendMessage(ChatColor.RED + "Ten gracz nie wysłał prośby o teleportację do Ciebie.");
                    }

                } else {
                    p.sendMessage(ChatColor.RED + "Ten gracz nie jest online.");
                }

            } else {
                p.sendMessage(ChatColor.RED + "/tpaccept <gracz>");
            }
            return true;
        }

        if (cmd.getName().equalsIgnoreCase("tpdeny")) {

            if (args.length == 1) {

                Player target = pl.getServer().getPlayer(args[0]);

                if (target != null && target.isOnline()) {

                    if (pl.getTpa().containsKey(p)) {
                        if (pl.getTpa().get(p).contains(target)) {
                            pl.getTpa().get(p).remove(target);
                            p.sendMessage(ChatColor.GREEN + "Odrzucono prośbę o teleportację od " + ChatColor.BLUE + target.getName() + ChatColor.GREEN + ".");
                            target.sendMessage(ChatColor.GREEN + "Gracz " + ChatColor.BLUE + p.getName() + ChatColor.GREEN +  " odrzucił prośbę o teleportację.");
                        } else {
                            p.sendMessage(ChatColor.RED + "Ten gracz nie wysłał prośby o teleportację do Ciebie.");
                        }

                    } else {
                        p.sendMessage(ChatColor.RED + "Ten gracz nie wysłał prośby o teleportację do Ciebie.");
                    }

                } else {
                    p.sendMessage(ChatColor.RED + "Ten gracz nie jest online.");
                }

            } else {
                p.sendMessage(ChatColor.RED + "/tpdeny <gracz>");
            }
            return true;
        }
        return false;
    }
}

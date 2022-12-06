package pl.mgtm.magicznakraina.commands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.Inventory;
import pl.mgtm.magicznakraina.MagicznaKraina;

import static pl.mgtm.magicznakraina.helpers.GuiHelpers.createItemWithLore;

public class TpaCommand implements CommandExecutor {
    private MagicznaKraina plugin = MagicznaKraina.getInstance();

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
                return invokeTeleportation(player, args);
            } else {
                handleTelelportationGUI(player);
                return true;
            }
        }

        if (cmd.getName().equalsIgnoreCase("tpaccept")) {
            player.sendMessage("tpaccept - work in progress");
            return true;
        }

        if (cmd.getName().equalsIgnoreCase("tpdeny")) {
            player.sendMessage("tpdeny - work in progress");
            return true;
        }

        return false;
    }

    private boolean invokeTeleportation(Player player, String[] args) {
        target = Bukkit.getPlayer(args[0]);

        if (target == null || !target.isOnline()) {
            player.sendMessage(ChatColor.RED + "Gracz do którego próbujesz się teleportować nie jest online!");
            return false;
        }

        if (target.getUniqueId().equals(player.getUniqueId())) {
            player.sendMessage(ChatColor.RED + "Nie możesz teleportować się do samego siebie!");
            return false;
        }

        if (this.plugin.teleportationService.checkTeleportationRequests(player.getUniqueId(), target.getUniqueId())) {
            player.sendMessage(ChatColor.GREEN + "Ten gracz posiada już oczkującą prośbę o teleportację od Ciebie!");
            return false;
        }

        this.plugin.teleportationService.createTeleporationRequest(player.getUniqueId(), target.getUniqueId());

        player.sendMessage(ChatColor.DARK_GRAY + "Wysłano prośbę o teleportację do gracza " + ChatColor.GREEN + target.getName());

        target.sendMessage(ChatColor.GREEN + player.getName() + " chce się do ciebie teleportować.");
        target.sendMessage(ChatColor.DARK_GRAY + "Wpisz /tpaccept lub /tpdeny aby wykonać pożądaną reakcję, lub wpisz /tpa");

        return true;
    }

    // TODO: IMPLEMENT InventoryClickEvent
    private void handleTelelportationGUI(Player player) {
        inventory = Bukkit.createInventory(player, InventoryType.CHEST, "Menu teleportacji");

        for (int i = 18; i < InventoryType.CHEST.getDefaultSize(); i++) {
            inventory.setItem(i, createItemWithLore(Material.GRAY_STAINED_GLASS_PANE, 1, " "));
        }

        inventory.setItem(20, createItemWithLore(Material.ARROW, 1, ChatColor.DARK_PURPLE + "Co to jest?", "§r§7Znajdujesz się w menu", "§r§7teleportacji. Kiedy klikniesz na", "§r§7jedną z główek, prośba o", "§r§7teleportację zostanie", "§r§7wysłana do danej osoby."));
        inventory.setItem(24, createItemWithLore(Material.BARRIER, 1, ChatColor.RED + "Wyjdź"));

        player.openInventory(inventory);
    }
}


package pl.mgtm.magicznakraina.commands;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Item;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import pl.mgtm.magicznakraina.MagicznaKraina;
import pl.mgtm.magicznakraina.command.CommandInfo;
import pl.mgtm.magicznakraina.command.PluginCommand;
import pl.mgtm.magicznakraina.config.SerduszkoModuleConfig;
import pl.mgtm.magicznakraina.config.User;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@CommandInfo(name = "test", permission = "", requiresPlayer = true)
public class TestCommand extends PluginCommand {
    private MagicznaKraina pl = MagicznaKraina.getInstance();

    @Override
    public void execute(Player player, String[] args) {
        super.execute(player, args);

        if (args.length > 0) {
            player.sendMessage("test command executed!");
            if (args[0].equalsIgnoreCase("get")) {
                player.sendMessage("....................get....................");
                HashMap<String, User> users = pl.getUserConfig().getUsers();

                player.sendMessage("cache: "+ pl.getUserConfig().getBukkitConfiguration().getCache().toString());

                if (users == null) {
                    player.sendMessage(ChatColor.RED + "users is null");
                    return;
                };

                User userconfig = users.get(player.getUniqueId().toString());

                player.sendMessage("getHearts: " + userconfig.getHearts());
                //player.sendMessage("home: " + userconfig.getHome());
                player.sendMessage("newusers: " + userconfig.getHearts());
                userconfig.setHearts(userconfig.getHearts() + 1);

                player.sendMessage("newusers2: " + userconfig.getHearts());
                player.sendMessage("test" + pl.getUserConfig().getTest());


                pl.getUserConfig().setUsers(users);
            } else if (args[0].equalsIgnoreCase("set")) {
                player.sendMessage("....................set....................");
                HashMap<String, User> users = new HashMap<>();

                users.put(player.getUniqueId().toString(), new User(player.getName()));
                pl.getUserConfig().setUsers(users);
            } else if (args[0].equalsIgnoreCase("home")) {
                HashMap<String, User> users = pl.getUserConfig().getUsers();

                if (users == null) {
                    player.sendMessage(ChatColor.RED + "users is null");
                    return;
                };

                User userconfig = users.get(player.getUniqueId().toString());

                userconfig.setHome(player.getLocation());


                pl.getUserConfig().setUsers(users);
            } else if (args[0].equalsIgnoreCase("item")) {

                SerduszkoModuleConfig config = pl.getMainConfig().getSerduszkoModule();
                List<ItemStack> items = config.getReviveItems();
                items.add(new ItemStack(Material.GOLDEN_APPLE, 2));
                pl.getMainConfig().setSerduszkoModule(config);

                player.sendMessage(ChatColor.GREEN + "new item has been added");
            } else if (args[0].equalsIgnoreCase("item2")) {

                SerduszkoModuleConfig config = pl.getMainConfig().getSerduszkoModule();
                List<ItemStack> items = config.getReviveItems();
                for (ItemStack item : items)
                {
                    player.sendMessage("item: " + item.getType().name());
                }

                player.sendMessage(ChatColor.GREEN + "new item has been added");
            }
        }

        // Remove item
        //ItemStack item = new ItemStack(Material.CHEST, 10);
        //player.getInventory().removeItem(item);

        /*ArrayList<ItemStack> items2 = new ArrayList<>();
        items2.add(new ItemStack(Material.CHEST, 10));
        items2.add(new ItemStack(Material.CHEST, 10));

        player.getInventory().removeItem(items2.toArray(new ItemStack[0]));*/

        // player.sendMessage(pl.getServer().getOfflinePlayer("merlin"));

    }
}

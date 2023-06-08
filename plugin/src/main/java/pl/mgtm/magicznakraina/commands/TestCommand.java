package pl.mgtm.magicznakraina.commands;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import pl.mgtm.magicznakraina.MagicznaKraina;
import pl.mgtm.magicznakraina.command.CommandInfo;
import pl.mgtm.magicznakraina.command.PluginCommand;
import pl.mgtm.magicznakraina.config.User;

import java.util.HashMap;
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
                HashMap<String, User> users2 = new HashMap<>();

                users2.put(player.getUniqueId().toString(), new User(player.getName()));
                //pl.getUserConfig().setUsers(users);
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

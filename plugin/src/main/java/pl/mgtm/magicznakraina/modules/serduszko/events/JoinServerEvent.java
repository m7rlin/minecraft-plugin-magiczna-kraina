package pl.mgtm.magicznakraina.modules.serduszko.events;

import org.bukkit.attribute.Attribute;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import pl.mgtm.magicznakraina.MagicznaKraina;
import pl.mgtm.magicznakraina.config.User;

import java.util.HashMap;

public class JoinServerEvent implements Listener {
    private MagicznaKraina pl = MagicznaKraina.getInstance();

    @EventHandler
    public void onPlayerJoinServer(PlayerJoinEvent event) {
        Player player = event.getPlayer();

        String uuid = player.getUniqueId().toString();

        HashMap<String, User> users = pl.getUserConfig().getUsers();
        if (users == null) users = new HashMap<>();

        // Add new user to config
        if (users.get(uuid) == null) {
            users.put(uuid, new User(player.getName()));
            pl.getUserConfig().setUsers(users);
        }

        double playerMaxHealth = pl.getUserConfig().getUsers().get(uuid).getHearts();
        player.getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(playerMaxHealth);
    }

}

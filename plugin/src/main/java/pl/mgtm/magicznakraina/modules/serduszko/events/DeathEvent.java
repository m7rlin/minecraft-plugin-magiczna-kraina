package pl.mgtm.magicznakraina.modules.serduszko.events;

import org.bukkit.attribute.Attribute;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import pl.mgtm.magicznakraina.MagicznaKraina;
import pl.mgtm.magicznakraina.config.User;

import java.util.HashMap;

public class DeathEvent implements Listener {
    private MagicznaKraina pl = MagicznaKraina.getInstance();

    @EventHandler
    public void onPlayerDeath(PlayerDeathEvent event) {
        Player player = event.getEntity().getPlayer();

        HashMap<String, User> users = pl.getUserConfig().getUsers();
        if (users == null) users = new HashMap<>();

        User user = users.get(player.getUniqueId().toString());

        double playerHearts = user.getHearts();

        if (playerHearts <= 1) {
            user.setBannedOnZeroHearts(true);
            pl.getUserConfig().setUsers(users);
            player.kickPlayer(pl.serduszkoModule.getBannedPlayerMessage());
            return;
        }

        // Reduce max health by 1HP
        user.removeHearts(1.0);

        player.getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(user.getHearts());
        pl.getUserConfig().setUsers(users);
    }
}

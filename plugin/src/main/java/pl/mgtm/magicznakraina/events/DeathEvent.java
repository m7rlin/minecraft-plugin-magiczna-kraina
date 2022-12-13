package pl.mgtm.magicznakraina.events;

import org.bukkit.attribute.Attribute;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import pl.mgtm.magicznakraina.MagicznaKraina;

public class DeathEvent implements Listener {
    private MagicznaKraina plugin = MagicznaKraina.getInstance();

    @EventHandler
    public void onPlayerDeath(PlayerDeathEvent event) {
        Player player = event.getEntity().getPlayer();

        double playerHearts = plugin.getConfig().getInt("users." + player.getUniqueId() + ".hearts");

        if (playerHearts <= 1) {
            // TODO: Zbanuj gracza
            return;
        };

        // Zmniejsz zycie o pol serduszka
        plugin.getConfig().set("users." + player.getUniqueId() + ".hearts", playerHearts - 1.0);

        player.getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(playerHearts - 1.0);

        plugin.saveConfig();
    }
}

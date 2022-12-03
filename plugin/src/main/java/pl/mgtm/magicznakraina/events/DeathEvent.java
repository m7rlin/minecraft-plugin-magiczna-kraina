package pl.mgtm.magicznakraina.events;

import org.bukkit.attribute.Attribute;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import pl.mgtm.magicznakraina.MagicznaKraina;

public class DeathEvent implements Listener {
    private MagicznaKraina pl = MagicznaKraina.getInstance();

    @EventHandler
    public void onPlayerDeath(PlayerDeathEvent e) {
        Player p = e.getEntity().getPlayer();

        double playerHearts = pl.getConfig().getInt("users." + p.getUniqueId() + ".hearts");

        if (playerHearts <= 1) return;

        // Zmniejsz zycie o pol serduszka
        pl.getConfig().set("users." + p.getUniqueId() + ".hearts", playerHearts - 1.0);

        p.getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(playerHearts - 1.0);

        pl.saveConfig();
    }
}

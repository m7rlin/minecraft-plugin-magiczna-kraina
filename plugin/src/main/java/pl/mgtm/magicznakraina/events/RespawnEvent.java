package pl.mgtm.magicznakraina.events;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerRespawnEvent;
import pl.mgtm.magicznakraina.MagicznaKraina;

public class RespawnEvent implements Listener {

    private MagicznaKraina pl = MagicznaKraina.getInstance();

    @EventHandler
    public void onPlayerRespawn(PlayerRespawnEvent e) {
        Player p = e.getPlayer();
        // Przenieś gracza na spawn serwera
        if (pl.getSpawnLocation() != null && pl.getConfig().getBoolean("spawn.teleportOnRespawn", true)) {
            e.setRespawnLocation(pl.getSpawnLocation());
        }
    }
}

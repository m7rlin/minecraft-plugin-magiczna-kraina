package pl.mgtm.magicznakraina.events;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerRespawnEvent;
import pl.mgtm.magicznakraina.MagicznaKraina;

public class RespawnEvent implements Listener {
    private MagicznaKraina plugin = MagicznaKraina.getInstance();

    @EventHandler
    public void onPlayerRespawn(PlayerRespawnEvent event) {
        Player player = event.getPlayer();
        // Jezeli spawn istnieje i teleportOnRespawn jest true, wtedy teleportujemy gracza na spawn
        if (plugin.spawnService.getSpawnLocation() != null && plugin.getConfig().getBoolean("spawn.teleportOnRespawn", true)) {
            event.setRespawnLocation(plugin.spawnService.getSpawnLocation());
        }
    }
}

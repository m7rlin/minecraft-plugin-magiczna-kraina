package pl.mgtm.magicznakraina.modules.spawn.events;

import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerRespawnEvent;
import pl.mgtm.magicznakraina.MagicznaKraina;

public class RespawnEvent implements Listener {
    private MagicznaKraina pl = MagicznaKraina.getInstance();


    @EventHandler
    public void onPlayerRespawn(PlayerRespawnEvent event) {
        Player player = event.getPlayer();

        boolean teleportOnRespawn = pl.getMainConfig().getSpawnModule().getTeleportOnRespawn();
        Location spawnLocation = pl.getMainConfig().getSpawnModule().getSpawn();

        // Spawn exists and "teleportOnRespawn" is true then teleport the player
        if (spawnLocation != null && teleportOnRespawn) {
            event.setRespawnLocation(spawnLocation);
        }
    }
}

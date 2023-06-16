package pl.mgtm.magicznakraina.modules.spawn.events;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import pl.mgtm.magicznakraina.MagicznaKraina;
import pl.mgtm.magicznakraina.config.SpawnModuleConfig;

public class JoinServerEvent implements Listener {
    private MagicznaKraina pl = MagicznaKraina.getInstance();

    @EventHandler
    public void onPlayerJoinServer(PlayerJoinEvent event) {
        Player player = event.getPlayer();

        String uuid = player.getUniqueId().toString();

        SpawnModuleConfig config = pl.getMainConfig().getSpawnModule();



        // Check if spawn is enabled
        if (config.getEnabled() && config.getSpawnEnabled()) {
            if (config.getSpawn() != null) {
                // Check if player played on this server
                if (player.getFirstPlayed() != 0) return;
                // Teleport player to spawn
                player.teleport(config.getSpawn());
            }
        }

    }

}

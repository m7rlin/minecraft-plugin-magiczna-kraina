package pl.mgtm.magicznakraina.modules.vanish.events;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import pl.mgtm.magicznakraina.MagicznaKraina;
import pl.mgtm.magicznakraina.modules.vanish.VanishModule;

import java.util.Map;
import java.util.UUID;

public class JoinLeaveEvent implements Listener {
    private final MagicznaKraina pl = MagicznaKraina.getInstance();
    private Map<UUID, Boolean> vanishedPlayers = VanishModule.getVanishedPlayers();

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();

        for (Map.Entry<UUID, Boolean> entry : vanishedPlayers.entrySet()) {
            if (entry.getValue()) {
                Player vanishedPlayer = pl.getServer().getPlayer(entry.getKey());
                player.hidePlayer(pl, vanishedPlayer);
            }
        }
    }

    @EventHandler
    public void onPlayerQuit(PlayerQuitEvent event) {
        Player player = event.getPlayer();
        vanishedPlayers.remove(player.getUniqueId());
    }
}

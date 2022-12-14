package pl.mgtm.magicznakraina.modules.serduszko.events;

import org.bukkit.attribute.Attribute;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerLoginEvent;
import pl.mgtm.magicznakraina.MagicznaKraina;
import pl.mgtm.magicznakraina.helpers.ConfigHelpers;

public class PreLoginEvent implements Listener {
    private MagicznaKraina plugin = MagicznaKraina.getInstance();

    @EventHandler
    public void onPlayerJoinServer(PlayerLoginEvent event) {
        Player player = event.getPlayer();

        if (ConfigHelpers.getPlayerZeroHeartsBan(player.getUniqueId()) == true) {
            event.disallow(PlayerLoginEvent.Result.KICK_OTHER, plugin.serduszkoModule.getBannedPlayerMessage());
        }
    }

}

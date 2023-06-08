package pl.mgtm.magicznakraina.modules.serduszko.events;

import org.bukkit.attribute.Attribute;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import pl.mgtm.magicznakraina.MagicznaKraina;
import pl.mgtm.magicznakraina.helpers.ConfigHelpers;

public class JoinServerEvent implements Listener {
    private MagicznaKraina pl = MagicznaKraina.getInstance();

    @EventHandler
    public void onPlayerJoinServer(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        
        double defaultHearts = pl.getMainConfig().getSerduszkoModule().getDefaultHearts();

        double playerHP = pl.getConfig().getDouble("users." + player.getUniqueId() + ".hp");

        ConfigHelpers.createDefaultPlayerConfig(player.getUniqueId());

        double playerMaxHealth = pl.getConfig().getDouble("users." + player.getUniqueId() + ".hearts");

        player.getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(playerMaxHealth);
    }

}

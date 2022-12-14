package pl.mgtm.magicznakraina.modules.serduszko.events;

import org.bukkit.ChatColor;
import org.bukkit.attribute.Attribute;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import pl.mgtm.magicznakraina.MagicznaKraina;
import pl.mgtm.magicznakraina.helpers.ConfigHelpers;

public class JoinServerEvent implements Listener {
    private MagicznaKraina plugin = MagicznaKraina.getInstance();

    @EventHandler
    public void onPlayerJoinServer(PlayerJoinEvent event) {
        Player player = event.getPlayer();

        double defaultHearts = this.plugin.getConfig().getDouble("defaultHearts");
        double playerHP = this.plugin.getConfig().getDouble("users." + player.getUniqueId() + ".hp");

        ConfigHelpers.createDefaultPlayerConfig(player.getUniqueId());

        double playerMaxHealth = this.plugin.getConfig().getDouble("users." + player.getUniqueId() + ".hearts");

        player.getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(playerMaxHealth);
    }

}

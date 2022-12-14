package pl.mgtm.magicznakraina.events;

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
    public void onPlayerJoinServer(PlayerJoinEvent e) {
        Player p = e.getPlayer();

        String eventMessage = plugin.getMessages().get("joinMessage").replace("{username}", p.getName());
        e.setJoinMessage(eventMessage);

        p.sendMessage(ChatColor.GREEN + "Grasz na Świątecznym serwerze MagicTM!\nŻyczymy miłej zabawy!");

        double defaultHearts = plugin.getConfig().getDouble("defaultHearts");
        double playerHP = plugin.getConfig().getDouble("users." + p.getUniqueId() + ".hp");

        ConfigHelpers.createDefaultPlayerConfig(p.getUniqueId());

        double playerMaxHealth = plugin.getConfig().getDouble("users." + p.getUniqueId() + ".hearts");

        p.getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(playerMaxHealth);
    }

    @EventHandler
    public void onPlayerLeaveServer(PlayerQuitEvent e) {
        Player p = e.getPlayer();

        String eventMessage = plugin.getMessages().get("leaveMessage").replace("{username}", p.getName());
        e.setQuitMessage(eventMessage);
    }
}

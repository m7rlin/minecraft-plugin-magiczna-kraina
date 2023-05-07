package pl.mgtm.magicznakraina.events;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import pl.mgtm.magicznakraina.MagicznaKraina;

// TODO: Refactor this
public class WelcomeMessageEvent implements Listener {
    private MagicznaKraina plugin = MagicznaKraina.getInstance();

    @EventHandler
    public void onPlayerJoinServer(PlayerJoinEvent event) {
        Player player = event.getPlayer();

        String eventMessage = this.plugin.getMessages().get("joinMessage").replace("{username}", player.getName());
        event.setJoinMessage(eventMessage);

        player.sendMessage(ChatColor.GREEN + "Grasz na Świątecznym serwerze MagicTM!\nŻyczymy miłej zabawy!");
    }

    @EventHandler
    public void onPlayerLeaveServer(PlayerQuitEvent event) {
        Player player = event.getPlayer();

        String eventMessage = plugin.getMessages().get("leaveMessage").replace("{username}", player.getName());
        event.setQuitMessage(eventMessage);
    }
}

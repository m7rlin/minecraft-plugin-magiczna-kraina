package pl.mgtm.magicznakraina.events;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import pl.mgtm.magicznakraina.MagicznaKraina;

public class WelcomeMessageEvent implements Listener {
    private MagicznaKraina pl = MagicznaKraina.getInstance();

    @EventHandler
    public void onPlayerJoinServer(PlayerJoinEvent event) {
        Player player = event.getPlayer();

        String eventMessage = this.pl.getMessages().get("joinMessage").replace("{username}", player.getName());
        event.setJoinMessage(eventMessage);

        player.sendMessage(ChatColor.GREEN + "Grasz na Świątecznym serwerze MagicTM!\nŻyczymy miłej zabawy!");
    }

    @EventHandler
    public void onPlayerLeaveServer(PlayerQuitEvent event) {
        Player player = event.getPlayer();

        String eventMessage = pl.getMessages().get("leaveMessage").replace("{username}", player.getName());
        event.setQuitMessage(eventMessage);
    }
}

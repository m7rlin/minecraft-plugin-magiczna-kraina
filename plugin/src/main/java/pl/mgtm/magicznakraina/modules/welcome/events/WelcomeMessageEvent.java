package pl.mgtm.magicznakraina.modules.welcome.events;

import net.kyori.adventure.text.Component;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import pl.mgtm.magicznakraina.MagicznaKraina;
import pl.mgtm.magicznakraina.modules.welcome.WelcomeModule;

public class WelcomeMessageEvent implements Listener {
    private MagicznaKraina pl = MagicznaKraina.getInstance();

    @EventHandler
    public void onPlayerJoinServer(PlayerJoinEvent event) {
        Player player = event.getPlayer();

        Component eventMessage = WelcomeModule.getJoinMessage(player);
        event.joinMessage(eventMessage);
    }

    @EventHandler
    public void onPlayerLeaveServer(PlayerQuitEvent event) {
        Player player = event.getPlayer();

        Component eventMessage = WelcomeModule.getLeaveMessage(player);
        event.quitMessage(eventMessage);
    }


}

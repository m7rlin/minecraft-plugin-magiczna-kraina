package pl.mgtm.magicznakraina.modules.reset_worlds.events;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerPortalEvent;
import pl.mgtm.magicznakraina.MagicznaKraina;
import pl.mgtm.magicznakraina.modules.reset_worlds.ResetWorldsModule;

public class PortalEvent implements Listener {
    private final MagicznaKraina pl = MagicznaKraina.getInstance();
    private ResetWorldsModule module;

    public PortalEvent(ResetWorldsModule module) {
        this.module = module;
    }

    @EventHandler
    public void onPlayerPortalEnter(PlayerPortalEvent event) {

        if (event.getPlayer() instanceof Player != true) return;

        Player p = event.getPlayer();

        if (module.getNetherDisabled()) {
            p.sendMessage(ChatColor.RED + "Nether jest wyłączony. Nether jest resetowany...");
            event.setCancelled(true);
        }


    }

}

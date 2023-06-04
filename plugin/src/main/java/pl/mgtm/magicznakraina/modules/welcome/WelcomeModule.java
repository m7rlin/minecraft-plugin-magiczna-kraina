package pl.mgtm.magicznakraina.modules.welcome;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.minimessage.MiniMessage;
import net.kyori.adventure.text.minimessage.tag.resolver.Placeholder;
import org.bukkit.entity.Player;
import org.bukkit.plugin.PluginManager;
import pl.mgtm.magicznakraina.MagicznaKraina;
import pl.mgtm.magicznakraina.modules.welcome.events.WelcomeMessageEvent;

import java.util.HashMap;


public class WelcomeModule {

    private final MagicznaKraina pl = MagicznaKraina.getInstance();

    private static HashMap<String, String> messages = new HashMap<>();


    public WelcomeModule() {
        PluginManager pm = pl.getServer().getPluginManager();

        String joinMesssage = pl.getConfig().getString("join-message", "");
        String leaveMessage = pl.getConfig().getString("leave-message", "");

        pl.getLogger().info(joinMesssage + " " + leaveMessage);

        messages.put("leave-message", leaveMessage);
        messages.put("join-message", joinMesssage);

        pl.getLogger().info(messages.toString());

        // Register events
        pm.registerEvents(new WelcomeMessageEvent(), pl);
    }

    public static HashMap<String, String> getMessages() {
        return messages;
    }

    public static Component getJoinMessage(Player player) {
        Component message = MiniMessage.miniMessage().deserialize(messages.get("join-message"), Placeholder.parsed("user", player.getName()));
        return message;
    }

    public static Component getLeaveMessage(Player player) {
        Component message = MiniMessage.miniMessage().deserialize(messages.get("leave-message"), Placeholder.parsed("user", player.getName()));
        return message;
    }

}

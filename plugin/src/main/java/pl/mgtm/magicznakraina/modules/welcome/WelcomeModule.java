package pl.mgtm.magicznakraina.modules.welcome;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.minimessage.MiniMessage;
import net.kyori.adventure.text.minimessage.tag.resolver.Placeholder;
import org.bukkit.entity.Player;
import pl.mgtm.magicznakraina.MagicznaKraina;
import pl.mgtm.magicznakraina.module.ModuleInfo;
import pl.mgtm.magicznakraina.module.PluginModule;
import pl.mgtm.magicznakraina.modules.welcome.events.WelcomeMessageEvent;

import java.util.HashMap;

@ModuleInfo(name = "welcome")
public class WelcomeModule extends PluginModule {

    private final MagicznaKraina pl = MagicznaKraina.getInstance();

    private static HashMap<String, String> messages = new HashMap<>();


    public WelcomeModule() {
        super();

        String joinMesssage = pl.getMainConfig().getWelcomeModule().getJoinMessage();
        String leaveMessage = pl.getMainConfig().getWelcomeModule().getLeaveMessage();

        //pl.getLogger().info(joinMesssage + " " + leaveMessage); // Debug

        messages.put("leave-message", leaveMessage);
        messages.put("join-message", joinMesssage);

        //pl.getLogger().info(messages.toString()); // Debug

        // Register events
        super.registerEvents(new WelcomeMessageEvent());
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

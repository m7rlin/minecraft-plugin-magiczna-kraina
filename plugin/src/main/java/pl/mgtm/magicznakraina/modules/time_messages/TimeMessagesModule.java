package pl.mgtm.magicznakraina.modules.time_messages;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.minimessage.MiniMessage;
import org.bukkit.Bukkit;
import org.bukkit.scheduler.BukkitRunnable;
import pl.mgtm.magicznakraina.MagicznaKraina;
import pl.mgtm.magicznakraina.config.TimeMessage;
import pl.mgtm.magicznakraina.config.TimeMessagesModuleConfig;
import pl.mgtm.magicznakraina.module.ModuleInfo;
import pl.mgtm.magicznakraina.module.PluginModule;

import java.util.List;

@ModuleInfo(name = "time_messages")
public class TimeMessagesModule extends PluginModule {

    private final MagicznaKraina pl = MagicznaKraina.getInstance();


    public TimeMessagesModule() {
        super();


        TimeMessagesModuleConfig config = pl.getMainConfig().getTimeMessagesModule();
        List<TimeMessage> timeMessages = config.getMessages();

        String prefix = config.getPrefix();
        String suffix = config.getSuffix();

        for (TimeMessage timeMessage : timeMessages) {
            List<String> content = timeMessage.getContent();
            int interval = timeMessage.getInterval();

            if (!timeMessage.getEnabled()) continue;
            new BukkitRunnable() {
                @Override
                public void run() {
                    Component message = MiniMessage.miniMessage().deserialize((prefix != null && !prefix.isEmpty() ? prefix + "<reset>" : "") + String.join("\n", content) + (suffix != null && !suffix.isEmpty() ? "reset" + suffix : ""));

                    // Broadcast the regular message without any action
                    Bukkit.broadcast(message);
                }

            }.runTaskTimerAsynchronously(pl, 0L, 20L * interval);


        }

    }


}

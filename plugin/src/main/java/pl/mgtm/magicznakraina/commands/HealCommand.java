package pl.mgtm.magicznakraina.commands;

import org.bukkit.ChatColor;
import org.bukkit.attribute.Attribute;
import org.bukkit.entity.Player;
import pl.mgtm.magicznakraina.templates.CommandInfo;
import pl.mgtm.magicznakraina.templates.PluginCommand;

import java.util.Random;

// TODO: Add different messages
@CommandInfo(name = "heal", permission = "mgtm.heal", requiresPlayer = true)
public class HealCommand extends PluginCommand {
    @Override
    public void execute(Player player, String[] args) {
        super.execute(player, args);

        String[] messages = {
                "Ale faza!",
                "Łoooo magia :o",
                "Czy ty to widzisz :O",
                "Co tu się stało :OOO"
        };

        String message = ChatColor.GREEN + messages[new Random().nextInt(messages.length-1)];

        player.setHealth(player.getAttribute(Attribute.GENERIC_MAX_HEALTH).getValue());
        player.setFoodLevel(20);

        player.sendMessage(message);
    }
}

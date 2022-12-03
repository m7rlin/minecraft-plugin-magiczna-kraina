package pl.mgtm.magicznakraina.commands;

import org.bukkit.ChatColor;
import org.bukkit.attribute.Attribute;
import org.bukkit.entity.Player;
import pl.mgtm.magicznakraina.command.CommandInfo;
import pl.mgtm.magicznakraina.command.PluginCommand;

import java.util.ArrayList;
import java.util.Random;

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

        String message = ChatColor.DARK_GRAY + messages[new Random().nextInt(messages.length)];

        player.setHealth(player.getAttribute(Attribute.GENERIC_MAX_HEALTH).getValue());
        player.setFoodLevel(20);

        player.sendMessage(message);
    }
}

package pl.mgtm.magicznakraina.modules.serduszko.commands;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import pl.mgtm.magicznakraina.MagicznaKraina;
import pl.mgtm.magicznakraina.command.CommandInfo;
import pl.mgtm.magicznakraina.command.PluginCommand;
import pl.mgtm.magicznakraina.helpers.ConfigHelpers;

@CommandInfo(name = "revive", permission = "", requiresPlayer = true)
public class ReviveCommand extends PluginCommand {

    private MagicznaKraina plugin = MagicznaKraina.getInstance();

    @Override
    public void execute(Player player, String[] args) {
        super.execute(player, args);

        ConfigHelpers.setPlayerZeroHeartsBan(player.getUniqueId(), false);

        player.sendMessage(ChatColor.RED + "Nie posiadasz wystarczającej ilości przedmiotów, aby wskrzesić gracza.");


        player.sendMessage(ChatColor.GRAY + "Możesz wskrzeciś TYLKO gracza, który poległ.");
        player.sendMessage(ChatColor.GREEN + "Pomyślnie wskrzeszono gracza " + ".");

    }


}

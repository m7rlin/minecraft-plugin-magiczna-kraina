package pl.mgtm.magicznakraina.modules.serduszko.commands;

import org.bukkit.entity.Player;
import pl.mgtm.magicznakraina.MagicznaKraina;
import pl.mgtm.magicznakraina.command.CommandInfo;
import pl.mgtm.magicznakraina.command.PluginCommand;

@CommandInfo(name = "revive", permission = "", requiresPlayer = true)
public class ReviveCommand extends PluginCommand {

    private MagicznaKraina plugin = MagicznaKraina.getInstance();

    @Override
    public void execute(Player player, String[] args) {
        super.execute(player, args);



    }


}

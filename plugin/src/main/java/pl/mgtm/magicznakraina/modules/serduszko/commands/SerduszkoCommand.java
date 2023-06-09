package pl.mgtm.magicznakraina.modules.serduszko.commands;

import org.bukkit.entity.Player;
import pl.mgtm.magicznakraina.MagicznaKraina;
import pl.mgtm.magicznakraina.command.CommandInfo;
import pl.mgtm.magicznakraina.command.PluginCommand;
import pl.mgtm.magicznakraina.config.User;

import java.util.HashMap;

@CommandInfo(name = "serduszko", permission = "", requiresPlayer = true)
public class SerduszkoCommand extends PluginCommand {

    private MagicznaKraina pl = MagicznaKraina.getInstance();

    Integer[] levels = pl.getMainConfig().getSerduszkoModule().getLevels();

    @Override
    public void execute(Player player, String[] args) {
        super.execute(player, args);

        HashMap<String, User> users = pl.getUserConfig().getUsers();
        User user = users.get(player.getUniqueId().toString());

        double playerHearts = user.getHearts();

        if (playerHearts >= 40.0) {
            player.sendMessage("§bOsiągnąłeś(-aś) już maksymanlny poziom serduszek.");
            return;
        }

        int playerHeartsLevel = user.getHeartsLevel();

        int playerLevel = player.getLevel();

        int maxLevels = levels.length - 1;

        if (playerHeartsLevel > maxLevels) {
            playerHeartsLevel = maxLevels;
        }

        int nextLevel = levels[playerHeartsLevel];
        int nextPlayerLevelRequired = nextLevel;
        if (maxLevels == playerHeartsLevel) {
            nextPlayerLevelRequired = levels[maxLevels];
        }

        if (playerLevel >= nextLevel) {

            user.addHearts(1.0);
            if (playerHeartsLevel < levels.length) {
                user.addHeartsLevel(1);
            }

            player.setMaxHealth(user.getHearts());
            player.setLevel(player.getLevel() - (levels[playerHeartsLevel] / 2));
            player.sendMessage("§aGratulacje! Dostajesz kolejne serduszko. Oby tak dalej.");
            player.sendMessage("Do nastepnego serduszka potrzebujesz §a" + nextPlayerLevelRequired + "§r poziomow XP.");

            pl.getUserConfig().setUsers(users);
        } else {
            player.sendMessage("§cNie posiadasz wystarczająco dużo poziomu doświadczenia, aby dostać kolejne serduszko.");
            player.sendMessage("Do nastepnego serduszka potrzebujesz §a" + nextPlayerLevelRequired + "§r poziomow XP.");
        }
    }

}

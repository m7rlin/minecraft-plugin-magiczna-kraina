package pl.mgtm.magicznakraina.modules.serduszko.commands;

import org.bukkit.entity.Player;
import pl.mgtm.magicznakraina.MagicznaKraina;
import pl.mgtm.magicznakraina.command.CommandInfo;
import pl.mgtm.magicznakraina.command.PluginCommand;

@CommandInfo(name = "serduszko", permission = "", requiresPlayer = true)
public class SerduszkoCommand extends PluginCommand {

    private MagicznaKraina plugin = MagicznaKraina.getInstance();

    int[] levels = {1, 1, 1, 2, 3, 3, 4, 5, 5, 5, 5, 6, 7, 8, 9, 10, 10, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 20, 20, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30, 30, 30, 30, 31, 32, 33, 34, 35, 36, 37, 38, 38, 40};

    @Override
    public void execute(Player player, String[] args) {
        super.execute(player, args);

        double playerHearts = plugin.getConfig().getInt("users." + player.getUniqueId() + ".hearts");

        if (playerHearts >= 40.0) {
            player.sendMessage("§bOsiągnąłeś(-aś) już maksymanlny poziom serduszek.");
            return;
        }

        int playerHeartsLevel = plugin.getConfig().getInt("users." + player.getUniqueId() + ".heartsLevel");

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
            addHearts(player, playerHearts, playerHeartsLevel);
        } else {
            player.sendMessage("§cNie posiadasz wystarczająco dużo poziomu doświadczenia, aby dostać kolejne serduszko.");
            player.sendMessage("Do nastepnego serduszka potrzebujesz §a" + nextPlayerLevelRequired + "§r poziomow XP.");
        }

    }

    private void addHearts(Player p, double playerHearts, int playerHeartsLevel) {
        plugin.getConfig().set("users." + p.getUniqueId() + ".hearts", playerHearts + 1.0);

        int maxLevels = levels.length - 1;

        int nextLevel = levels[playerHeartsLevel];
        int nextPlayerLevelRequired = nextLevel;
        if (maxLevels == playerHeartsLevel) {
            nextPlayerLevelRequired = levels[maxLevels];
        }

        if (playerHeartsLevel < levels.length) {
            plugin.getConfig().set("users." + p.getUniqueId() + ".heartsLevel", playerHeartsLevel + 1);
        }

        p.setMaxHealth(playerHearts + 1.0);
        p.setLevel(p.getLevel() - (levels[playerHeartsLevel] / 2));
        p.sendMessage("§aGratulacje! Dostajesz kolejne serduszko. Oby tak dalej.");
        p.sendMessage("Do nastepnego serduszka potrzebujesz §a" + nextPlayerLevelRequired + "§r poziomow XP.");
        plugin.saveConfig();
    }
}

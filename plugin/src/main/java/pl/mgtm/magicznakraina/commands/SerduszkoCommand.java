package pl.mgtm.magicznakraina.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import pl.mgtm.magicznakraina.MagicznaKraina;

public class SerduszkoCommand implements CommandExecutor {

    private MagicznaKraina pl = MagicznaKraina.getInstance();

    int[] levels = {1, 1, 1, 2, 3, 3, 4, 5, 5, 5, 5, 6, 7, 8, 9, 10, 10, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 20, 20, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30, 30, 30, 30, 31, 32, 33, 34, 35, 36, 37, 38, 38, 40};

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String s, String[] args) {

        if (cmd.getName().equalsIgnoreCase("serduszko")) {
            if (sender instanceof Player) {
                Player p = (Player) sender;

                double playerHearts = pl.getConfig().getInt("users." + p.getUniqueId() + ".hearts");

                if (playerHearts >= 40.0) {
                    p.sendMessage("§bOsiągnąłeś(-aś) już maksymanlny poziom serduszek.");
                    return true;
                }

                int playerHeartsLevel = pl.getConfig().getInt("users." + p.getUniqueId() + ".heartsLevel");


                int playerLevel = p.getLevel();

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
                    addHearts(p, playerHearts, playerHeartsLevel);
                } else {
                    p.sendMessage("§cNie posiadasz wystarczająco dużo poziomu doświadczenia, aby dostać kolejne serduszko.");
                    p.sendMessage("Do nastepnego serduszka potrzebujesz §a" + nextPlayerLevelRequired + "§r poziomow XP.");
                }
            }
            return true;
        }

        return false;
    }

    private void addHearts(Player p, double playerHearts, int playerHeartsLevel) {
        pl.getConfig().set("users." + p.getUniqueId() + ".hearts", playerHearts + 1.0);

        int maxLevels = levels.length - 1;

        int nextLevel = levels[playerHeartsLevel];
        int nextPlayerLevelRequired = nextLevel;
        if (maxLevels == playerHeartsLevel) {
            nextPlayerLevelRequired = levels[maxLevels];
        }

        if (playerHeartsLevel < levels.length) {
            pl.getConfig().set("users." + p.getUniqueId() + ".heartsLevel", playerHeartsLevel + 1);
        }

        p.setMaxHealth(playerHearts + 1.0);
        p.setLevel(p.getLevel() - (levels[playerHeartsLevel] / 2));
        p.sendMessage("§aGratulacje! Dostajesz kolejne serduszko. Oby tak dalej.");
        p.sendMessage("Do nastepnego serduszka potrzebujesz §a" + nextPlayerLevelRequired + "§r poziomow XP.");
        pl.saveConfig();
    }
}

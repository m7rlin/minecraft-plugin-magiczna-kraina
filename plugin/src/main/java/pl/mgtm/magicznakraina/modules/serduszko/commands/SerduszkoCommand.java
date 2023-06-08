package pl.mgtm.magicznakraina.modules.serduszko.commands;

import org.bukkit.entity.Player;
import pl.mgtm.magicznakraina.MagicznaKraina;
import pl.mgtm.magicznakraina.command.CommandInfo;
import pl.mgtm.magicznakraina.command.PluginCommand;

@CommandInfo(name = "serduszko", permission = "", requiresPlayer = true)
public class SerduszkoCommand extends PluginCommand {

    private MagicznaKraina pl = MagicznaKraina.getInstance();

    Integer[] levels = pl.getMainConfig().getSerduszkoModule().getLevels();

    @Override
    public void execute(Player player, String[] args) {
        super.execute(player, args);

        double playerHearts = pl.getConfig().getInt("users." + player.getUniqueId() + ".hearts");

        if (playerHearts >= 40.0) {
            player.sendMessage("§bOsiągnąłeś(-aś) już maksymanlny poziom serduszek.");
            return;
        }

        int playerHeartsLevel = pl.getConfig().getInt("users." + player.getUniqueId() + ".heartsLevel");

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

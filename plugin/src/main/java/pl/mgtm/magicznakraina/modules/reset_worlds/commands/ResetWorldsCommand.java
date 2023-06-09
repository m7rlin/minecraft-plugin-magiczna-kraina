package pl.mgtm.magicznakraina.modules.reset_worlds.commands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.World;
import org.bukkit.WorldCreator;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import pl.mgtm.magicznakraina.MagicznaKraina;
import pl.mgtm.magicznakraina.command.CommandInfo;
import pl.mgtm.magicznakraina.command.PluginCommand;
import pl.mgtm.magicznakraina.modules.reset_worlds.ResetWorldsModule;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@CommandInfo(name = "resetworlds", permission = "mgtm.resetworlds", requiresPlayer = true, usage = "/resetworlds [netherSeed endSeed]")
public class ResetWorldsCommand extends PluginCommand {

    private final MagicznaKraina pl = MagicznaKraina.getInstance();
    private ResetWorldsModule module;
    private long netherSeed = 0;
    private long endSeed = 0;

    public ResetWorldsCommand(ResetWorldsModule module) {
        this.module = module;
    }

    @Override
    public void execute(Player player, String[] args) {
        super.execute(player, args);


        if (args.length == 2) {
            try {
                netherSeed = Long.parseLong(args[0]);
                endSeed = Long.parseLong(args[1]);
            } catch (NumberFormatException e) {
                player.sendMessage(ChatColor.RED + "Nieprawidłowe wartości seeda. Podaj prawidłowe długie wartości całkowite.");
                return;
            }
        } else if (args.length != 0) {
            super.commandUsage(player);
            return;
        } else {
            Random random = new Random();
            netherSeed = random.nextLong();
            endSeed = random.nextLong();
        }

        //TODO: async

        module.setEndDisabled(true);
        module.setNetherDisabled(true);

        unloadWorld("world_nether");
        unloadWorld("world_the_end");
        deleteWorldFolder("world_nether");
        deleteWorldFolder("world_the_end");
        resetWorld("world_nether", World.Environment.NETHER, netherSeed);
        resetWorld("world_the_end", World.Environment.THE_END, endSeed);


        if (player instanceof Player) {
            player.sendMessage(ChatColor.GREEN + "Nether i End zostały zresetowane.");
            module.setEndDisabled(false);
            module.setNetherDisabled(false);
        }
    }

    @Override
    public List<String> tabAutocomplete(CommandSender sender, Command command, String label, String[] args) {
        super.tabAutocomplete(sender, command, label, args);

        return new ArrayList<>();
    }

    private void unloadWorld(String worldName) {
        World world = Bukkit.getWorld(worldName);
        if (world != null) {
            for (Player player : world.getPlayers()) {
                player.sendMessage(ChatColor.BLUE + "Świat jest resetowany. Teleportowanie na spawn.");
                player.teleport(Bukkit.getWorlds().get(0).getSpawnLocation());
            }
            Bukkit.unloadWorld(world, false);
            Bukkit.getWorlds().remove(world);
        }
    }

    private void deleteWorldFolder(String worldName) {
        File worldFolder = new File(worldName);
        if (worldFolder.exists() && worldFolder.isDirectory()) {
            File[] worldFiles = worldFolder.listFiles();
            if (worldFiles != null) {
                for (File file : worldFiles) {
                    if (file.isDirectory()) {
                        deleteFolder(file);
                    } else {
                        file.delete();
                    }
                }
            }
            worldFolder.delete();
        }
    }

    private void deleteFolder(File folder) {
        File[] files = folder.listFiles();
        if (files != null) {
            for (File file : files) {
                if (file.isDirectory()) {
                    deleteFolder(file);
                } else {
                    file.delete();
                }
            }
        }
        folder.delete();
    }

    private void resetWorld(String worldName, World.Environment environment, long seed) {
        WorldCreator worldCreator = new WorldCreator(worldName).environment(environment);
        if (seed != 0) {
            worldCreator.seed(seed);
        }
        Bukkit.createWorld(worldCreator);
    }

}

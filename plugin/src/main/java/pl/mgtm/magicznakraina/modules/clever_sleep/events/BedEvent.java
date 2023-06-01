package pl.mgtm.magicznakraina.modules.clever_sleep.events;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerBedEnterEvent;
import org.bukkit.scheduler.BukkitRunnable;
import pl.mgtm.magicznakraina.MagicznaKraina;

public class BedEvent implements Listener {
    private final MagicznaKraina pl = MagicznaKraina.getInstance();

    //TODO: add this to config
    private final int requiredPercentage = 40;

    @EventHandler
    public void onPlayerBedEnter(PlayerBedEnterEvent event) {
        // Check if the event happened in the overworld
        World world = event.getBed().getWorld();
        if (!world.getEnvironment().equals(World.Environment.NORMAL)) {
            return;
        }

        Player player = event.getPlayer();

        //player.sendMessage("Moze spac?: " + event.getBedEnterResult().name()); // Debug


        // Check if it's currently night
        if (!event.getBedEnterResult().equals(PlayerBedEnterEvent.BedEnterResult.OK)) {
            player.sendMessage(ChatColor.YELLOW + "Możesz spać tylko w nocy.");
            return;
        }

        // Check if the required percentage is met
        if (canPlayerSleep()) {
            new BukkitRunnable() {
                @Override
                public void run() {
                    // If the player is not sleeping
                    if (!player.isSleeping()) {
                        return;
                    }
                    world.setTime(0);
                    world.setStorm(false);
                    world.setThundering(false);

                    // Broadcast the message to all players
                    Bukkit.broadcastMessage(ChatColor.YELLOW + "Dzień dobry! Słońce wschodzi.");
                }
            }.runTaskLater(pl, 20); // Delay the time change by 1s tick
        } else {
            // Send a message to the player who entered the bed
            player.sendMessage(ChatColor.YELLOW + "Potrzebujesz co najmniej " + requiredPercentage +
                    "% graczy w łóżkach, aby pominąć noc.");
        }

    }

    private boolean canPlayerSleep() {
        // Get the percentage of players in bed
        int totalPlayers = Bukkit.getOnlinePlayers().size();
        int playersInBed = getPlayersInBed() + 1;

        double percentageInBed = getPercentagePlayersInBed(playersInBed, totalPlayers);

        return percentageInBed >= requiredPercentage;
    }

    private double getPercentagePlayersInBed(int playersInBed, int totalPlayers) {
        return (double) playersInBed / totalPlayers * 100;
    }

    private int getPlayersInBed() {
        int playersInBed = 0;

        for (Player onlinePlayer : Bukkit.getOnlinePlayers()) {
            if (onlinePlayer.isSleeping()) {
                playersInBed++;
            }
        }

        return playersInBed;
    }
}

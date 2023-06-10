package pl.mgtm.magicznakraina.config;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import pl.mgtm.magicznakraina.api.config.annotation.Comment;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class SerduszkoModuleConfig implements Serializable {

    // Module status on/off
    private boolean enabled;
    // Can players be revived
    private boolean playerReviveEnabled;
    // Default hearts when new user joined the server
    private double defaultHearts;
    // Max hearts player can get
    private double heartsLimit;
    // When player hearts reach 0 HP he will be banned.
    private boolean banOnZeroHearts = true;
    // Items required for revive. The items will be taken away.
    // If the list is empty it will take 50 levels by default to revive a player.
    private List<ItemStack> reviveItems;
    // Revive level
    private int reviveLevel = 30;
    //
    private Integer[] levels = {1, 1, 2, 2, 2, 3, 3, 3, 4, 4, 5, 7, 10, 11, 12, 13, 14, 15, 16, 17, 20, 22, 24, 26, 27, 28, 29, 30, 31, 33, 35, 36, 37, 38, 39, 40};

    public SerduszkoModuleConfig() {
    }

    public SerduszkoModuleConfig(boolean moduleEnabled, boolean playerReviveEnabled, double defaultHearts, double heartsLimit, boolean banOnZeroHearts) {
        this.enabled = moduleEnabled;
        this.playerReviveEnabled = playerReviveEnabled;

        this.setHeartsLimit(heartsLimit);
        this.setDefaultHearts(defaultHearts);
        this.setBanOnZeroHearts(banOnZeroHearts);

        List<ItemStack> reviveItems = new ArrayList<>();
        reviveItems.add(new ItemStack(Material.DIAMOND, 64));
        reviveItems.add(new ItemStack(Material.GOLD_INGOT, 32));
        reviveItems.add(new ItemStack(Material.GOLDEN_APPLE, 2));
        reviveItems.add(new ItemStack(Material.ENDER_PEARL, 4));
        this.reviveItems = reviveItems;
    }

    public boolean getEnabled() {
        return enabled;
    }

    void setPlayerReviveEnabled(boolean playerReviveEnabled) {
        this.playerReviveEnabled = playerReviveEnabled;
    }

    public boolean getPlayerReviveEnabled() {
        return playerReviveEnabled;
    }

    public void setDefaultHearts(double defaultHearts) {
        this.defaultHearts = defaultHearts;
    }

    @Comment("Default hearts when new user joined the server")
    public double getDefaultHearts() {
        return defaultHearts;
    }

    public void setHeartsLimit(double heartsLimit) {
        this.heartsLimit = heartsLimit;
    }

    @Comment("Max hearts player can get")
    public double getHeartsLimit() {
        return heartsLimit;
    }

    public int getReviveLevel() {
        return reviveLevel;
    }

    public List<ItemStack> getReviveItems() {
        return reviveItems;
    }

    public void addReviveItem(ItemStack item) {
        this.reviveItems.add(item);
    }

    public Integer[] getLevels() {
        return levels;
    }

    public boolean getBanOnZeroHearts() {
        return banOnZeroHearts;
    }

    public void setBanOnZeroHearts(boolean status) {
        banOnZeroHearts = status;
    }
}

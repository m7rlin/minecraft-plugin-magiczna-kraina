package pl.mgtm.magicznakraina.api.gui;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.minimessage.MiniMessage;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.HandlerList;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import pl.mgtm.magicznakraina.MagicznaKraina;

import java.util.HashMap;
import java.util.Map;

public class GUIAPI {

    private static Map<Player, GUI> playerGUIs;
    private static Map<Player, GUIEventHandler> eventHandlers;

    // Open a GUI for a specific player
    public static void openGUI(Player player, GUI gui) {
        if (playerGUIs == null) {
            playerGUIs = new HashMap<>();
        }
        if (eventHandlers == null) {
            eventHandlers = new HashMap<>();
        }

        Inventory inventory = Bukkit.createInventory(player, gui.getSize(), gui.getParsedTitle());
        inventory.setContents(gui.getItems());
        player.openInventory(inventory);
        playerGUIs.put(player, gui);

        GUIEventHandler eventHandler = new GUIEventHandler(player);
        eventHandlers.put(player, eventHandler);
        Bukkit.getPluginManager().registerEvents(eventHandler, MagicznaKraina.getInstance());
    }


    // Handle GUI clicks
    public static void handleGUIClick(Player player, int slot, boolean isRightClick) {
        if (playerGUIs != null && playerGUIs.containsKey(player)) {
            GUI gui = playerGUIs.get(player);
            if (gui != null) {
                GUIAction action = gui.getClickAction(slot);
                if (action != null) {
                    action.execute(player, isRightClick);
                }
            }
        }
    }
    // Close a GUI for a specific player
    public static void closeGUI(Player player) {
        if (eventHandlers != null && eventHandlers.containsKey(player)) {
            GUIEventHandler eventHandler = eventHandlers.get(player);
            HandlerList.unregisterAll(eventHandler);
            eventHandlers.remove(player);
        }
        if (playerGUIs != null) {
            playerGUIs.remove(player);
        }
    }

    // GUI class representing a graphical user interface
    public static class GUI {
        private int size;
        private String title;
        private ItemStack[] items;
        private Map<Integer, GUIAction> clickActions;

        public GUI(String title, int size) {
            this.size = size;
            this.title = title;
            this.items = new ItemStack[size];
            this.clickActions = new HashMap<>();
        }

        public int getSize() {
            return size;
        }

        public String getTitle() {
            return title;
        }

        public Component getParsedTitle() {
            return MiniMessage.miniMessage().deserialize(title);
        }

        public ItemStack[] getItems() {
            return items;
        }

        public void setItem(int slot, GUIItem item) {
            items[slot] = item.getItemStack();
        }

        public void setClickAction(int slot, GUIAction action) {
            clickActions.put(slot, action);
        }

        public Map<Integer, GUIAction> getClickActions() {
            return clickActions;
        }

        public GUIAction getClickAction(int slot) {
            return clickActions.get(slot);
        }
    }

    // GUI event handler class
    private static class GUIEventHandler implements Listener {
        private Player player;

        public GUIEventHandler(Player player) {
            this.player = player;
        }

        @EventHandler
        public void onGUIClick(InventoryClickEvent event) {

            if (event.getWhoClicked() instanceof Player) {
                Player player = (Player) event.getWhoClicked();
                if (player.equals(this.player)) {
                    event.setCancelled(true); // Prevent the item from being taken
                    player.updateInventory();

                    int slot = event.getRawSlot();
                    boolean isRightClick = event.isRightClick();
                    handleGUIClick(player, slot, isRightClick);
                }
            }
        }

        @EventHandler
        public void onGUIClose(InventoryCloseEvent event) {
            if (event.getPlayer() instanceof Player) {
                Player player = (Player) event.getPlayer();
                if (player.equals(this.player)) {
                    closeGUI(player);
                }
            }
        }
    }

    // Interface for GUI click actions
    public interface GUIAction {
        void execute(Player player, boolean isRightClick);
    }
}
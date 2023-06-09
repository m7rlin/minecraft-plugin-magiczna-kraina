package pl.mgtm.magicznakraina.api.economy;

import org.bukkit.OfflinePlayer;

import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.Map;

public class EconomyAPI {

    private Map<String, Double> balances;
    private double tax;
    private TaxType taxType;
    private String currencySymbol;
    private DecimalFormat decimalFormat;

    public EconomyAPI(double tax, TaxType taxType, String currencySymbol, DecimalFormats decimalFormat) {
        this.balances = new HashMap<>();
        this.tax = tax;
        this.taxType = taxType;
        this.currencySymbol = currencySymbol;
        this.decimalFormat = decimalFormat.getDecimalFormat();
    }

    public double getBalance(OfflinePlayer player) {
        String playerName = player.getUniqueId().toString();
        if (balances.containsKey(playerName)) {
            return balances.get(playerName);
        }
        return 0.0; // Default balance if player does not exist
    }

    public void deposit(OfflinePlayer player, double amount) {
        double balance = getBalance(player);
        balance += amount;
        balances.put(player.getName(), balance);
    }

    public void withdraw(OfflinePlayer player, double amount) {
        double balance = getBalance(player);
        balance -= amount;
        if (balance < 0) {
            balance = 0;
        }
        balances.put(player.getName(), balance);
    }

    public boolean has(OfflinePlayer player, double amount) {
        return getBalance(player) >= amount;
    }

    public boolean has(OfflinePlayer player, String worldName, double amount) {
        // Implementation-specific logic for checking balance in a specific world
        // If the economy plugin does not support per-world balances, the global balance will be returned
        return has(player, amount);
    }

    public void sendMoney(OfflinePlayer sender, OfflinePlayer recipient, double amount) {
        double senderBalance = getBalance(sender);
        double recipientBalance = getBalance(recipient);

        double senderTax = calculateTax(amount);
        double taxedAmount = amount - senderTax;

        if (senderBalance >= amount) {
            withdraw(sender, amount);
            deposit(recipient, taxedAmount);
        }
    }

    private double calculateTax(double amount) {
        if (taxType == TaxType.FIXED_AMOUNT) {
            return tax;
        } else if (taxType == TaxType.PERCENTAGE) {
            return amount * (tax / 100);
        } else if (taxType == TaxType.COMBINED) {
            return tax + (amount * (tax / 100));
        }
        return 0.0;
    }

    public String formatBalance(double amount) {
        return currencySymbol + decimalFormat.format(amount);
    }

    public void createAccount(OfflinePlayer player, double initialBalance) {
        String playerName = player.getUniqueId().toString();
        if (!balances.containsKey(playerName)) {
            balances.put(playerName, initialBalance);
        }
    }

    public void createAccount(String playerName, double initialBalance) {
        if (!balances.containsKey(playerName)) {
            balances.put(playerName, initialBalance);
        }
    }

    public void removeAccount(OfflinePlayer player) {
        String playerName = player.getUniqueId().toString();
        if (balances.containsKey(playerName)) {
            balances.remove(playerName);
        }
    }

    public void removeAccount(String playerName) {
        if (balances.containsKey(playerName)) {
            balances.remove(playerName);
        }
    }

    public boolean hasAccount(OfflinePlayer player) {
        String playerName = player.getUniqueId().toString();
        return balances.containsKey(playerName);
    }

}
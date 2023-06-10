package pl.mgtm.magicznakraina.config;

import java.io.Serializable;

public class EconomyModuleConfig implements Serializable {

    // Module status on/off
    private boolean enabled;

    private int newAccountBalance = 0;

    private String currencySymbol = "$";

    public EconomyModuleConfig() {
    }

    public EconomyModuleConfig(boolean moduleEnabled) {
        this.enabled = moduleEnabled;
    }

    public boolean getEnabled() {
        return enabled;
    }

    public void setEnabled(boolean status) {
        enabled = status;
    }

    public void setNewAccountBalance(int balance) {
        newAccountBalance = balance;
    }

    public int getNewAccountBalance() {
        return newAccountBalance;
    }

    public void setCurrencySymbol(String symbol) {
        currencySymbol = symbol;
    }

    public String getCurrencySymbol() {
        return currencySymbol;
    }


}

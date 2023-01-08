package de.mxscha.en.endoflife.utils.manager.money.api;

import org.bukkit.entity.Player;

public interface IMoneyAPI {

    Double getMoney(Player player);
    void setMoney(Player player, double amount);
    void removeMoney(Player player, double amount);
    void addMoney(Player player, double amount);
}

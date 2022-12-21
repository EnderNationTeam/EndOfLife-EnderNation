package de.mxscha.en.endoflife.utils.scoreboard.manager.money.api;

import org.bukkit.entity.Player;

import java.util.UUID;

public interface IMoneyAPI {

    Integer getMoney(Player player);
    void setMoney(Player player, int amount);
    void removeMoney(Player player, int amount);
    void addMoney(Player player, int amount);
}

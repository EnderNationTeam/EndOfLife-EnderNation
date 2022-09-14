package de.mxscha.endernationendoflife.utils.money;

import java.util.UUID;

public interface IMoneyAPI {

    Integer getMoney(UUID uuid);
    void setMoney(UUID uuid, int amount);
    void removeMoney(UUID uuid, int amount);
    void addMoney(UUID uuid, int amount);
}

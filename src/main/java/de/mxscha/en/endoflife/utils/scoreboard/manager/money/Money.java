package de.mxscha.en.endoflife.utils.scoreboard.manager.money;

import de.mxscha.en.endoflife.utils.scoreboard.manager.money.api.MoneyAPI;

public class Money {

    private static MoneyAPI api;

    public static MoneyAPI getApi() {
        return api;
    }

    public static void setApi(MoneyAPI api) {
        Money.api = api;
    }
}

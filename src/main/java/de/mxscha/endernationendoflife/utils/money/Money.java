package de.mxscha.endernationendoflife.utils.money;

public class Money {

    private static MoneyAPI api;

    public static MoneyAPI getApi() {
        return api;
    }

    public static void setApi(MoneyAPI api) {
        Money.api = api;
    }
}

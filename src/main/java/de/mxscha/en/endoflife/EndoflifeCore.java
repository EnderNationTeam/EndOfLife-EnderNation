package de.mxscha.en.endoflife;

import de.mxscha.en.endoflife.utils.scoreboard.manager.backpack.config.BackpackAPI;
import de.mxscha.en.endoflife.utils.scoreboard.manager.item.clear.ClearLagManager;
import de.mxscha.en.endoflife.utils.scoreboard.manager.job.JobActionBarInfoManager;
import de.mxscha.en.endoflife.utils.scoreboard.manager.job.entity.Shop;
import de.mxscha.en.endoflife.utils.scoreboard.manager.region.Region;
import de.mxscha.en.endoflife.utils.scoreboard.manager.backpack.manager.BackpackManager;
import de.mxscha.en.endoflife.utils.scoreboard.manager.job.entity.Employer;
import de.mxscha.en.endoflife.utils.scoreboard.manager.job.api.JobAPI;
import de.mxscha.en.endoflife.utils.scoreboard.manager.job.entity.Delivery;
import de.mxscha.en.endoflife.utils.scoreboard.manager.location.LocationsConfig;
import de.mxscha.en.endoflife.utils.scoreboard.manager.money.Money;
import de.mxscha.en.endoflife.utils.scoreboard.manager.money.api.MoneyAPI;
import de.mxscha.en.endoflife.utils.database.mysql.MySQL;
import de.mxscha.en.endoflife.utils.Register;
import org.bukkit.plugin.java.JavaPlugin;

public final class EndoflifeCore extends JavaPlugin {

    private static EndoflifeCore instance;
    private BackpackManager backpackManager;
    private MySQL mySQL;
    private MoneyAPI moneyAPI;
    private JobAPI jobAPI;
    private BackpackAPI backpackAPI;
    private Region regionManager;
    private Shop entityManager;

    @Override
    public void onEnable() {
        instance = this;
        Register.init(instance);
        backpackAPI = new BackpackAPI();
        backpackAPI.createTables();
        moneyAPI = new MoneyAPI();
        jobAPI = new JobAPI();
        regionManager = new Region();
        entityManager = new Shop();
        backpackManager = new BackpackManager();
        moneyAPI.createTables();
        jobAPI.createTables();
        Money.setApi(moneyAPI);
        entityManager.spawn();
        ClearLagManager.start();
        Delivery.registerMaps();
        JobActionBarInfoManager.send();
    }

    @Override
    public void onLoad() {
        initMySQL();
    }

    private void initMySQL() {
        this.mySQL = MySQL.newBuilder().withUrl("localhost")
                .withPort(3306)
                .withDatabase("EndOfLife")
                .withUser("EndOfLife")
                .withPassword("")
                .create();
    }

    @Override
    public void onDisable() {
        backpackManager.save();
        LocationsConfig.save();
        Employer.despawn();
        Delivery.despawnAccept();
        entityManager.despawn();
    }

    public static EndoflifeCore getInstance() {
        return instance;
    }

    public Region getRegionManager() {
        return regionManager;
    }

    public BackpackManager getBackpackManager() {
        return backpackManager;
    }

    public MoneyAPI getMoneyAPI() {
        return moneyAPI;
    }

    public JobAPI getJobAPI() {
        return jobAPI;
    }

    public MySQL getMySQL() {
        return mySQL;
    }
}
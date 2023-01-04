package de.mxscha.en.endoflife;

import de.mxscha.en.endoflife.utils.manager.backpack.config.BackpackAPI;
import de.mxscha.en.endoflife.utils.manager.home.HomeManager;
import de.mxscha.en.endoflife.utils.manager.item.clear.ClearLagManager;
import de.mxscha.en.endoflife.utils.manager.job.JobActionBarInfoManager;
import de.mxscha.en.endoflife.utils.manager.job.entity.Shop;
import de.mxscha.en.endoflife.utils.manager.job.entity.ToolSmith;
import de.mxscha.en.endoflife.utils.manager.region.Region;
import de.mxscha.en.endoflife.utils.manager.backpack.manager.BackpackManager;
import de.mxscha.en.endoflife.utils.manager.job.entity.Employer;
import de.mxscha.en.endoflife.utils.manager.job.api.JobAPI;
import de.mxscha.en.endoflife.utils.manager.job.entity.Delivery;
import de.mxscha.en.endoflife.utils.manager.location.LocationsConfig;
import de.mxscha.en.endoflife.utils.manager.money.Money;
import de.mxscha.en.endoflife.utils.manager.money.api.MoneyAPI;
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
    private HomeManager homeManager;

    Shop shop = new Shop();
    ToolSmith smith = new ToolSmith();
    Employer employer = new Employer();
    Delivery delivery = new Delivery();

    @Override
    public void onEnable() {
        instance = this;
        Register.init(instance);
        backpackAPI = new BackpackAPI();
        backpackAPI.createTables();
        moneyAPI = new MoneyAPI();
        jobAPI = new JobAPI();
        regionManager = new Region();
        backpackManager = new BackpackManager();
        homeManager = new HomeManager();

        moneyAPI.createTables();
        // homeManager.createTables();
        jobAPI.createTables();

        shop.spawn();
        smith.spawn();
        employer.spawn();
        delivery.spawn();

        Money.setApi(moneyAPI);

        ClearLagManager.start();
        Delivery.registerXpMaps();
        Delivery.registerMoneyMaps();
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
        employer.despawn();
        smith.despawn();
        shop.despawn();
        delivery.despawn();
    }

    public HomeManager getHomeManager() {
        return homeManager;
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
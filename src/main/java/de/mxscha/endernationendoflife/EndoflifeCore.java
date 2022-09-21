package de.mxscha.endernationendoflife;

import de.mxscha.endernationendoflife.utils.EntityManager;
import de.mxscha.endernationendoflife.utils.RegionManager;
import de.mxscha.endernationendoflife.utils.backpack.BackpackConfig;
import de.mxscha.endernationendoflife.utils.backpack.BackpackManager;
import de.mxscha.endernationendoflife.utils.jobs.Employer;
import de.mxscha.endernationendoflife.utils.jobs.JobAPI;
import de.mxscha.endernationendoflife.utils.jobs.delivery.Delivery;
import de.mxscha.endernationendoflife.utils.locations.LocationsConfig;
import de.mxscha.endernationendoflife.utils.money.Money;
import de.mxscha.endernationendoflife.utils.money.MoneyAPI;
import de.mxscha.endernationendoflife.utils.money.MySQL.MySQL;
import org.bukkit.plugin.java.JavaPlugin;

public final class EndoflifeCore extends JavaPlugin {

    private static EndoflifeCore instance;
    private BackpackManager backpackManager;
    private MySQL mySQL;
    private MoneyAPI moneyAPI;
    private JobAPI jobAPI;
    private RegionManager regionManager;
    private EntityManager entityManager;

    @Override
    public void onEnable() {
        instance = this;
        Register.init(instance);
        backpackManager = new BackpackManager();
        moneyAPI = new MoneyAPI();
        jobAPI = new JobAPI();
        regionManager = new RegionManager();
        entityManager = new EntityManager();
        moneyAPI.createTables();
        jobAPI.createTables();
        Money.setApi(moneyAPI);
        entityManager.spawn();
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
        BackpackConfig.save();
        LocationsConfig.save();
        Employer.despawn();
        Delivery.despawnAccept();
        entityManager.despawn();
    }

    public static EndoflifeCore getInstance() {
        return instance;
    }

    public RegionManager getRegionManager() {
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
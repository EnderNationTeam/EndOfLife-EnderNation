package de.mxscha.en.endoflife;

import de.mxscha.en.endoflife.utils.manager.backpack.config.BackpackAPI;
import de.mxscha.en.endoflife.utils.manager.chat.Messages;
import de.mxscha.en.endoflife.utils.manager.home.HomeManager;
import de.mxscha.en.endoflife.utils.manager.item.clear.ClearLagManager;
import de.mxscha.en.endoflife.utils.manager.job.JobActionBarInfoManager;
import de.mxscha.en.endoflife.utils.manager.job.entity.*;
import de.mxscha.en.endoflife.utils.manager.region.Region;
import de.mxscha.en.endoflife.utils.manager.backpack.manager.BackpackManager;
import de.mxscha.en.endoflife.utils.manager.job.api.JobAPI;
import de.mxscha.en.endoflife.utils.manager.location.LocationsConfig;
import de.mxscha.en.endoflife.utils.manager.money.Money;
import de.mxscha.en.endoflife.utils.manager.money.api.MoneyAPI;
import de.mxscha.en.endoflife.utils.database.mysql.MySQL;
import de.mxscha.en.endoflife.utils.Register;
import org.bukkit.Bukkit;
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
    RandomTeleport randomTeleport = new RandomTeleport();

    @Override
    public void onEnable() {
        instance = this;
        initMySQLConfig();
        Delivery.registerXpMaps();
        Delivery.registerMoneyMaps();
        Register.init(instance);
        backpackAPI = new BackpackAPI();
        backpackAPI.createTables();
        moneyAPI = new MoneyAPI();
        jobAPI = new JobAPI();
        regionManager = new Region();
        backpackManager = new BackpackManager();
        homeManager = new HomeManager();

        moneyAPI.createTables();
        homeManager.createTables();
        jobAPI.createTables();

        shop.spawn();
        smith.spawn();
        employer.spawn();
        delivery.spawn();
        randomTeleport.spawn();

        Money.setApi(moneyAPI);

        // ClearLagManager.start();
        JobActionBarInfoManager.send();
    }

    @Override
    public void onLoad() {
        connectToMySQL();
    }

    private void initMySQLConfig() {
        if (!getConfig().contains("MySQL.url")) {
            getConfig().set("MySQL.url", "url");
            getConfig().set("MySQL.port", "port");
            getConfig().set("MySQL.database", "database");
            getConfig().set("MySQL.user", "user");
            getConfig().set("MySQL.password", "password");
        }
        saveConfig();
    }

    private void connectToMySQL() {
        String url = "";
        int port = 0;
        String database = "";
        String user = "";
        String password = "";
        try {
            url = getConfig().getString("MySQL.url");
            port = getConfig().getInt("MySQL.port");
            database = getConfig().getString("MySQL.database");
            user = getConfig().getString("MySQL.user");
            password = getConfig().getString("MySQL.password");
        } catch (Exception e) {
            Bukkit.getConsoleSender().sendMessage(Messages.PREFIX.get() + "Verbinden der Daten ist etwas schiefgelaufen!");
        }
        if (password == null)
            password = "";
        this.mySQL = MySQL.newBuilder().withUrl(url).withPort(port).withDatabase(database).withUser(user).withPassword(password).create();
    }

    @Override
    public void onDisable() {
        backpackManager.save();
        LocationsConfig.save();
        employer.despawn();
        smith.despawn();
        shop.despawn();
        delivery.despawn();
        randomTeleport.despawn();
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